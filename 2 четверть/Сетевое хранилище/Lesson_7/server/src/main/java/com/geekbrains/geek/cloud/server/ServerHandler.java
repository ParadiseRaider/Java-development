package com.geekbrains.geek.cloud.server;

import com.geekbrains.geek.cloud.common.ProtoFileSender;
import com.geekbrains.geek.cloud.common.ProtoHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.file.Paths;


public class ServerHandler extends ChannelInboundHandlerAdapter {
    String pathto;
    ProtoHandler ph;
    MainServerNetty msn;

    public ServerHandler(MainServerNetty msn) {
        this.msn = msn;
        this.pathto = "server_repository/";
        this.ph = new ProtoHandler();
        ph.setPathTo(pathto);
        ph.setServer(true);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ph.channelRead(ctx, msg);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        if (ph.getUser()!=null) {
            ProtoFileSender.sendCommand(msn.serverFiles(ph.getUser()), ctx.channel(), future -> {
                if (!future.isSuccess()) {
                    future.cause().printStackTrace();
                }
                if (future.isSuccess()) {
                    System.out.println("Команда списка файлов на сервере успешно передана");
                }
            });
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
