package org.infozech.netty;



import java.util.ArrayList;

import java.util.List;

import org.apache.log4j.Logger;
import org.infozech.dao.BytesDao;
import org.infozech.dao.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author satyadeep
 *
 */
@Sharable
@Service
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
	
	
	@Autowired
	BytesDao bytesdao;
	
	@Autowired
	Test test;
	
	private static final Logger logger = Logger.getLogger(NettyServerHandler.class);
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
			
		};
		
	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelRead(io.netty.channel.ChannelHandlerContext, java.lang.Object)
	 */
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	
				ByteBuf in = Unpooled.copiedBuffer((ByteBuf) msg);
								
				byte[] b = new byte[in.capacity()];
				
				List<Byte> bytelist = new ArrayList<Byte>();
				
				for (int i = 0; i < in.capacity(); i ++) {
				      b[i] = in.getByte(i);
				      System.out.printf(String.format("%02x", b[i]));
				      logger.info(b[i]);
				      bytelist.add(b[i]);
				 }
				
				 bytesdao.setBytelist(bytelist);
				 /*
				 List<Byte> newbytelist = bytesdao.getBytelist();
				 for(byte bytes:newbytelist){
					 System.out.println(String.format("%02x", bytes));
				 }*/
				 test.get();
		         ctx.write(in);
		         ctx.close();
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
		cause.printStackTrace();
		ctx.close();
	}



}
