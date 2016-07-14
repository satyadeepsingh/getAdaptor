package org.infozech.netty;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Category;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

@Controller
public class NettyDispatcher {
	
	@Value("${msg}")
	private String hello;
	
	@Value("${port1}")
	private int port1;
	
	@Value("${port2}")
	private int port2;
	
	@Value("${port3}")
	private int port3;
	
	@Value("${port4}")
	private int port4;
	
	@Value("${port5}")
	private int port5;
	
	@Value("${port6}")
	private int port6;
	
	@Value("${port7}")
	private int port7;
	
	@Value("${port8}")
	private int port8;
	
	@Value("${port9}")
	private int port9;
	
	@Value("${port10}")
	private int port10;
	
	@Autowired
	NettyServerHandler nettyserverhandler;
	
	private static final Logger logger = Logger.getLogger(NettyDispatcher.class);
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String netty(Model model){
		System.out.println("Home Controller..... Passing through");
		
		model.addAttribute("hello",this.hello);
		
		return "hello";
	}
	
	InetSocketAddress address = null;
	@RequestMapping("/nettystart")
	public String nettystart(Model model){
		
		System.out.println("Value from the properties file:" + hello);
		
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();	
			
			//Server 1
			bootstrap.group(bossGroup,workerGroup)
			 .channel(NioServerSocketChannel.class)
			 .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
			 .option(ChannelOption.TCP_NODELAY, true)
			// .handler(new LoggingHandler(LogLevel.INFO))
			 
			 .childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					// TODO Auto-generated method stub
					ChannelPipeline p = ch.pipeline();
					System.out.println("New Client connected:" + ch.localAddress());
				    logger.info("Receiving data from" + ch.localAddress());
				    
					p.addLast(nettyserverhandler);
				}
			})
			 .option(ChannelOption.SO_BACKLOG, 1024)
			 .childOption(ChannelOption.SO_KEEPALIVE,true); 
			
			 
			 bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
			 bootstrap.childOption(ChannelOption.SO_RCVBUF,1048576);
			 bootstrap.childOption(ChannelOption.SO_SNDBUF, 1048576);
			 
			List<ChannelFuture> futures = new ArrayList<ChannelFuture>();
			futures.add(bootstrap.bind(port1));
			futures.add(bootstrap.bind(port2));
			futures.add(bootstrap.bind(port3));
			futures.add(bootstrap.bind(port4));
			futures.add(bootstrap.bind(port5));
			futures.add(bootstrap.bind(port6));
			futures.add(bootstrap.bind(port7));
			futures.add(bootstrap.bind(port8));
			futures.add(bootstrap.bind(port9));
			futures.add(bootstrap.bind(port10));
			
			String helloagain = "Netty Servers are already loaded";
			System.out.println("Netty Servers loaded successfully");
			logger.info("Netty Servers loaded successfully") ;
			model.addAttribute("helloagain", helloagain);
			model.addAttribute("logger", logger);	
			
			for (ChannelFuture f: futures) {
			    f.sync();
			    f.channel().closeFuture().sync();
			}
			
			//f.channel().closeFuture().sync();
			
		} catch(Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			String message = e.getMessage() + " exception";
			model.addAttribute("message" , message);
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
		return "nettysuccess";
	}

}
