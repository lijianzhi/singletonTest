package com.lxg.springboot.test.huawetest;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

/**
 * @ClassName NettyDemo
 * @Description: TODO
 * @Author 77984
 * @Date 2021/2/20
 * @Version V1.0
 **/
public class NettyDemo {
    private int port;

    public NettyDemo(int port) {
        this.port = port;
    }

    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bsp = new ServerBootstrap();
            bsp.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future = bsp.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static class NettyServerHandler extends ChannelInboundHandlerAdapter  {

        @Override
        public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
            System.out.println("这是客户端连接上服务，读取数据");
//            ByteBuf in = (ByteBuf) msg;
//            try {
//                while (in.isReadable()) { // (1)
//                    System.out.print((char) in.readByte());
//                    System.out.flush();
//                }
//            } finally {
//                ReferenceCountUtil.release(msg); // (2)
//            }
            int a= 1;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("写入数据");
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    channelHandlerContext.writeAndFlush(msg);
                    System.out.println("写入数据完成");
//                    a++;
                }
            });
            thread.setName("写数据线程");
            thread.start();
            System.out.println("线程名称:"+thread.getName());
        }
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
            // Close the connection when an exception is raised.
            System.out.println("出现异常"+ cause.getStackTrace());
            cause.printStackTrace();
            ctx.close();
        }
        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            System.out.println("数据已经读取完成");
            ctx.flush();
        }

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            System.out.println("连接心跳检查");
            if (evt instanceof IdleStateEvent) {
                ctx.channel().close();      // beat 3N, close if idle
                System.out.println(">>>>>>>>>>> xxl-job provider netty_http server close an idle channel.");
            } else {
                super.userEventTriggered(ctx, evt);
            }
        }
    }

    public static void main(String[] args) {
        int port = 8824;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        new NettyDemo(port).run();
    }
}
