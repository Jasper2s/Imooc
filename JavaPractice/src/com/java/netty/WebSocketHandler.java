package com.java.netty;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;
/**
 * 接收/处理/响应客户端websocket请求的核心处理类
 * @author qiuzhiwen
 *
 */
public class WebSocketHandler extends SimpleChannelInboundHandler<Object>{

	private WebSocketServerHandshaker handShaker;
	private static final String WEB_SOCKET_URL="ws://localhost:8888/websocket";
	//客户端与服务端创建连接端时候调用
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		NettyConfig.group.add(ctx.channel());
		System.out.println("客户端与服务端连接开启！");
	}

	//客户端与服务端断开连接的时候调用
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		NettyConfig.group.remove(ctx.channel());
		System.out.println("客户端与服务端连接断开！");
	}

	//服务端接收客户端发送过来的数据之后调用
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	//工程出现异常的时候调用
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	//服务端处理客户端websocket请求的核心方法
	@Override
	protected void messageReceived(ChannelHandlerContext context, Object msg) throws Exception {
		//处理客户端向服务器发起http握手请求的业务
		if(msg instanceof FullHttpRequest) {
			handHttpRequest(context, (FullHttpRequest)msg);
		}else if(msg instanceof WebSocketFrame) {
			//处理websocket连接业务
			handWebsocketFrame(context, (WebSocketFrame)msg);
		}
	}
	
	/**
	 * 处理客户端与服务端之前的websocket业务
	 * @param context
	 * @param frame
	 */
	@SuppressWarnings("unused")
	private void handWebsocketFrame(ChannelHandlerContext context,WebSocketFrame frame) {
		//判断是否关闭websocket的指令
		if(frame instanceof CloseWebSocketFrame) {
			handShaker.close(context.channel(), (CloseWebSocketFrame)frame.retain());
		}
		//判断是否为ping消息
		if(frame instanceof PingWebSocketFrame) {
			context.channel().write(new PongWebSocketFrame(frame.content().retain()));
			return;
		}
		//判断是否是二进制消息
		if(!(frame instanceof TextWebSocketFrame)) {
			System.out.println("不支持二进制消息！");
			//throw new RuntimeException("["+this.getClass().getName()+"]不支持消息！");
		}
		//返回应答消息
		//获取客户端向服务器发送的消息
		if(!(frame instanceof CloseWebSocketFrame)) {
			String request=((TextWebSocketFrame)frame).text();//强转之前需要判断
			System.out.println("服务端收到的客户端消息==>>"+request);
			TextWebSocketFrame tws=new TextWebSocketFrame(new Date().toString()+context.channel().id()+"===>>>"+request);
			//群发，服务端向每个连接上的客户端群发消息
			NettyConfig.group.writeAndFlush(tws);
		}
	}
	
	/**
	 * 处理客户端向服务器发起http握手请求的业务
	 * @param context
	 * @param request
	 */
	@SuppressWarnings("unused")
	private void handHttpRequest(ChannelHandlerContext context,FullHttpRequest request) {
		if(!request.getDecoderResult().isSuccess()||!"websocket".equals(request.headers().get("Upgrade"))) {
			sendHttpResponse(context, request, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
			return;
		}
		WebSocketServerHandshakerFactory wsFactory=new WebSocketServerHandshakerFactory(WEB_SOCKET_URL, null, false);
		handShaker=wsFactory.newHandshaker(request);
		if(handShaker==null) {
			WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(context.channel());
		}else {
			handShaker.handshake(context.channel(), request);
		}
	}
	
	/**
	 * 服务端向客户端响应消息
	 * @param context
	 * @param request
	 * @param response
	 */
	private void sendHttpResponse(ChannelHandlerContext context,FullHttpRequest request,DefaultFullHttpResponse response) {
		if(response.getStatus().code()!=200) {
			ByteBuf buf=Unpooled.copiedBuffer(response.getStatus().toString(), CharsetUtil.UTF_8);
			response.content().writeBytes(buf);
			buf.release();
		}
		//服务端向客户端发送数据
		ChannelFuture f=context.channel().writeAndFlush(response);
		if(response.getStatus().code()!=200) {
			f.addListener(ChannelFutureListener.CLOSE);
		}
	}

}
