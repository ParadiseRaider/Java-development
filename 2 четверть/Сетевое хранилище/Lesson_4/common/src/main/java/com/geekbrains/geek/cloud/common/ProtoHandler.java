package com.geekbrains.geek.cloud.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.nio.file.Paths;

public class ProtoHandler extends ChannelInboundHandlerAdapter {
    public enum State {
        IDLE, NAME_LENGTH, NAME, FILE_LENGTH, FILE
    }

    public enum StateCom {
        IDLE, COM_LENGTH, COM_NAME
    }

    private String pathTo;
    private String commandLine;
    private State currentState = State.IDLE;
    private StateCom currentStateCom = StateCom.IDLE;
    private int nextLength;
    private int nextLengthCom;
    private long fileLength;
    private long receivedFileLength;
    private BufferedOutputStream out;

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;

        if (currentState==State.IDLE && currentStateCom==StateCom.IDLE) {
            byte readed = buf.readByte();
            if (readed == 15) {
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
                out = new BufferedOutputStream(new FileOutputStream(pathTo + new String(fileName)));
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
                if (receivedFileLength>=fileLength) {
                    currentState = State.IDLE;
                    receivedFileLength=0;
                    System.out.println("Фаил: фаил полностью принят");
                    out.close();
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
                if (cmd[0].equals("/download")) {
                    ProtoFileSender.sendFile(Paths.get(pathTo + cmd[1]), ctx.channel(), future -> {
                        if (!future.isSuccess()) {
                            future.cause().printStackTrace();
                        }
                        if (future.isSuccess()) {
                            System.out.println("Команда на скачивание файла "+cmd[1]+" прошла успешно");
                        }
                    });
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
}
