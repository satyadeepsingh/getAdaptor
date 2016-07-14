package org.infozech.netty;



import javax.servlet.jsp.jstl.core.Config;

import org.apache.logging.log4j.core.config.AbstractConfiguration;
import org.apache.logging.log4j.spi.LoggerContext;
import org.springframework.stereotype.Service;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelHandler.Sharable;

@Sharable
@Service
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
			
		};
		
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	
				ByteBuf in = Unpooled.copiedBuffer((ByteBuf) msg);
				Byte[] b =new Byte[in.capacity()];		
				for (int i = 0; i < in.capacity(); i ++) {
				      b[i] = in.getByte(i);
				      System.out.printf(String.format("%02x", b[i]));
			 }
				
			     
		//		 ByteBuf in = (ByteBuf) msg;
		//	        System.out.println(
		//	            "Server received: " + in.toString(CharsetUtil.UTF_8));
			        ctx.write(in);
			        ctx.close();
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
		cause.printStackTrace();
		ctx.close();
	}



}
