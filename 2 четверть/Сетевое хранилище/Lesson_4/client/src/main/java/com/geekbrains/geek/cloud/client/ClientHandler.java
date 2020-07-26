package com.geekbrains.geek.cloud.client;

import com.geekbrains.geek.cloud.common.ProtoHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    String pathTo;
    ProtoHandler ph;
    ClientNetty cn;

    public ClientHandler(ClientNetty cn) {
        this.cn = cn;
        this.pathTo = "client_repository/";
        ph = new ProtoHandler();
        ph.setPathTo(pathTo);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ph.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        cn.getClientController().refreshLocalFilesList();
        cn.getClientController().refreshServerFileList(ph.getCommand().split(" "));
    }

    public ProtoHandler getProtoHandler() {
        return ph;
    }
}
