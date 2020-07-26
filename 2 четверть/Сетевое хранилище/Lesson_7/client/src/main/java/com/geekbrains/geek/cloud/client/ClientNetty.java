package com.geekbrains.geek.cloud.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;


public class ClientNetty {
    private Channel clientChannel;
    private ClientController cc;

    public ClientNetty(ClientController cc) {
        this.cc = cc;
    }

    public void run() {
        new Thread(()->{
            EventLoopGroup group = new NioEventLoopGroup();
            try {
                Bootstrap b = new Bootstrap();
                b.group(group);
                b.channel(NioSocketChannel.class);
                b.remoteAddress(new InetSocketAddress("localhost",8189));
                b.handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ClientHandler(ClientNetty.this));
                        clientChannel = ch;
                    }
                });
                ChannelFuture f = b.connect().sync();
                f.channel().closeFuture().sync();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    group.shutdownGracefully().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public Channel getClientChannel() {
        return clientChannel;
    }

    public ClientController getClientController() {
        return cc;
    }
}
