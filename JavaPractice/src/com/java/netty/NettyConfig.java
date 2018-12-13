package com.java.netty;
/**
 * 全局配置类
 * @author qiuzhiwen
 *
 */

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class NettyConfig {
	//存储每一个客户端接入进来的配置
	public static ChannelGroup group=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
