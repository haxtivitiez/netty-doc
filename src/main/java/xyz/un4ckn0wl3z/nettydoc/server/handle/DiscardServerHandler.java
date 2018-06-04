package xyz.un4ckn0wl3z.nettydoc.server.handle;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Handles a server-side channel.
 */



public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

//	@Override
//	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//		// Discard the received data silently.
//		// ((ByteBuf) msg).release();
//		
//		 ByteBuf in = (ByteBuf) msg;
//		    try {
//		        while (in.isReadable()) { // (1)
//		            System.out.print((char) in.readByte());
//		            System.out.flush();
//		        }
//		    } finally {
//		        ReferenceCountUtil.release(msg); // (2)
//		    }
//	}
//
//	@Override
//	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//		// Close the connection when an exception is raised.
//		cause.printStackTrace();
//        ctx.close();
//	}
	
	  @Override
	    public void channelActive(final ChannelHandlerContext ctx) { // (1)
	        final ByteBuf time = ctx.alloc().buffer(4); // (2)
	        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
	        
	        final ChannelFuture f = ctx.writeAndFlush(time); // (3)
	        f.addListener(new ChannelFutureListener() {
	            public void operationComplete(ChannelFuture future) {
	                assert f == future;
	                ctx.close();
	            }
	        }); // (4)
	    }
	    
	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
	        cause.printStackTrace();
	        ctx.close();
	    }
	
	

}
