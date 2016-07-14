package org.infozech.netty;



import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Service;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@Sharable
@Service
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
	
	private static final Logger logger = Logger.getLogger(NettyServerHandler.class);
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
			
		};
		
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	
				ByteBuf in = Unpooled.copiedBuffer((ByteBuf) msg);
				Byte[] b =new Byte[in.capacity()];
				Date date = new Date();
			//	logger.info("[ "+ " Receiving from the server: " +date + NettyDispatcher.address +" ]");
				for (int i = 0; i < in.capacity(); i ++) {
				      b[i] = in.getByte(i);
				      System.out.printf(String.format("%02x", b[i]));
				      logger.info(b[i]);
			 }
			 					 		     
		//		 ByteBuf in = (ByteBuf) msg;
		//	        System.out.println(
		//	            "Server received: " + in.toString(CharsetUtil.UTF_8));
			        ctx.write(in);
			        //ctx.close();
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
		cause.printStackTrace();
		ctx.close();
	}



}
