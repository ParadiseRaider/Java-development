package com.geekbrains.geek.cloud.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ProtoHandler extends ChannelInboundHandlerAdapter {
    public enum State {
        IDLE, NAME_LENGTH, NAME, FILE_LENGTH, FILE
    }

    public enum StateCom {
        IDLE, COM_LENGTH, COM_NAME
    }

    private String pathTo;
    private String user;
    private String commandLine;
    private State currentState = State.IDLE;
    private StateCom currentStateCom = StateCom.IDLE;
    private int nextLength;
    private int nextLengthCom;
    private long fileLength;
    private long receivedFileLength;
    private BufferedOutputStream out;
    private boolean isServer = false;
    private String errorMsg;

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;

        if (currentState==State.IDLE && currentStateCom==StateCom.IDLE) {
            errorMsg=null;
            byte readed = buf.readByte();
            if (readed == 15) {
                receivedFileLength=0;
                currentState = State.NAME_LENGTH;
                System.out.println("Файл: старт передачи файла");
            }
            if (readed == 16) {
                currentStateCom = StateCom.COM_LENGTH;
                System.out.println("Команда: старт передачи команды");
            }
        }

        if (currentState==State.NAME_LENGTH) {
            if (buf.readableBytes()>=4) {
                nextLength = buf.readInt();
                System.out.println("Фаил: получена длина имени файла");
                currentState = State.NAME;
            }
        }

        if (currentState==State.NAME) {
            if (buf.readableBytes()>=nextLength) {
                byte[] fileName = new byte[nextLength];
                buf.readBytes(fileName);
                System.out.println("Фаил: имя файла принято - " + new String(fileName));
                if (isServer) {
                    out = new BufferedOutputStream(new FileOutputStream(pathTo + user + "/" + new String(fileName)));
                } else {
                    out = new BufferedOutputStream(new FileOutputStream(pathTo + new String(fileName)));
                }
                currentState = State.FILE_LENGTH;
            }
        }

        if (currentState==State.FILE_LENGTH) {
            if (buf.readableBytes()>=8) {
                fileLength = buf.readLong();
                System.out.println("Фаил: получена длина данных файла");
                currentState = State.FILE;
            }
        }

        if (currentState==State.FILE) {
            while (buf.readableBytes()>0) {
                out.write(buf.readByte());
                receivedFileLength++;
                if (receivedFileLength==fileLength) {
                    currentState = State.IDLE;
                    System.out.println("Фаил: фаил полностью принят");
                    out.close();
                    break;
                }
            }
        }

        if (currentStateCom==StateCom.COM_LENGTH) {
            if (buf.readableBytes()>=4) {
                nextLengthCom = buf.readInt();
                System.out.println("Команда: получена длина команды");
                currentStateCom = StateCom.COM_NAME;
            }
        }

        if (currentStateCom==StateCom.COM_NAME) {
            if (buf.readableBytes()>=nextLengthCom) {
                byte[] fileName = new byte[nextLengthCom];
                buf.readBytes(fileName);
                commandLine = new String(fileName);
                System.out.println("Команда: команда принята - " + commandLine);
                currentStateCom = StateCom.IDLE;
                String[] cmd = commandLine.split(" ");

                //Команды с клиента на сервер
                if (cmd[0].equals("/download")) {
                    ProtoFileSender.sendFile(Paths.get(pathTo + user + "/" + cmd[1]), ctx.channel(), future -> {
                        if (!future.isSuccess()) {
                            future.cause().printStackTrace();
                        }
                        if (future.isSuccess()) {
                            System.out.println("Команда на скачивание файла "+cmd[1]+" прошла успешно");
                        }
                    });
                }
                if (cmd[0].equals("/delete")) {
                    Files.delete(Paths.get(pathTo + user + "/" + cmd[1]));
                }
                if (cmd[0].equals("/auth")) {
                    String currentNick = AuthService.getNickByLoginAndPass(cmd[1], cmd[2]);
                    if (currentNick!=null) {
                        user = currentNick;
                        ProtoFileSender.sendCommand("/authok " + currentNick, ctx.channel(), null);
                    } else {
                        ProtoFileSender.sendCommand("/err enter", ctx.channel(), null);
                    }
                }
                if (cmd[0].equals("/regin")) {
                    String currentNick = AuthService.getNickByLogin(cmd[1]);
                    if (currentNick==null) {
                        AuthService.registerPerson(cmd[1],cmd[2],cmd[3]);
                        user = cmd[3];
                        ProtoFileSender.sendCommand("/authok " + cmd[3], ctx.channel(), null);
                    } else {
                        ProtoFileSender.sendCommand("/err regin", ctx.channel(), null);
                    }
                }

                //Команды с серверка на клиент
                if (cmd[0].equals("/authok")) {
                    user = cmd[1];
                }

                if (cmd[0].equals("/err")) {
                    if (cmd[1].equals("enter")) errorMsg = "Не правильный ввод логина, пароля!";
                    if (cmd[1].equals("regin")) errorMsg = "Ошибка создания нового пользователя\nДанный пользователь уже существует.";
                }
            }
        }

        buf.release();
    }

    public void setPathTo(String to) {
        pathTo = to;
    }

    public String getCommand() {
        return commandLine;
    }

    public String getUser() {
        return user;
    }

    public void setServer(boolean server) {
        isServer = server;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
