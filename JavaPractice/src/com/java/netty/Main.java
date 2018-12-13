package com.java.netty;
/**
 * 程序启动类
 * @author qiuzhiwen
 *
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Main {

	@SuppressWarnings({ })
	public static void main(String[] args) {
		EventLoopGroup boosGroup=null;
		EventLoopGroup workGroup=null;
		try {
			boosGroup=new NioEventLoopGroup();
			workGroup=new NioEventLoopGroup();
			ServerBootstrap sb=new ServerBootstrap();
			sb.group(boosGroup,workGroup);
			sb.channel(NioServerSocketChannel.class);
			sb.childHandler(new WebSocketChannelHandler());
			System.out.println("服务端开启，等待客户端连接...");
			Channel ch = sb.bind(8888).sync().channel();
			ch.closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			//优雅地退出程序
			if(boosGroup!=null) {
				boosGroup.shutdownGracefully();
			}
			if(workGroup!=null) {
				boosGroup.shutdownGracefully();
			}
		}
	}
}
