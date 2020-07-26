package com.geekbrains.geek.cloud.server;

import com.geekbrains.geek.cloud.common.AuthService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainServerNetty {
    public static final Path REPOSITORY_ROOT = Paths.get("server_repository");

    public void run() throws Exception {
        AuthService.connect();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ServerHandler(MainServerNetty.this));
                        }
                    })
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture f = b.bind(8189).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public String serverFiles(String user) throws IOException {
        Path rootDir = REPOSITORY_ROOT.resolve(user);
        if (Files.notExists(rootDir))
            Files.createDirectory(rootDir);
        StringBuilder listFiles = new StringBuilder();
        Files.list(Paths.get("server_repository",user)).map(p -> p.getFileName().toString()).forEach(o -> listFiles.append(o+" "));
        return listFiles.toString();
    }

    public static void main(String[] args) throws Exception {
        MainServerNetty netty = new MainServerNetty();
        netty.run();
    }
}
