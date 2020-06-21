package com.lxg.springboot.test.huawetest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Calendar;
import java.util.Iterator;

public class NIOSocket {
    public static void main(String[] args) {
        try {
            //服务器初始化
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress("localhost",9999));
            //注册AOP_ACCEPT事件（即监听事件，如果有客户端发来连接请求，则该键值在select（）后被选中）
            Selector selector = Selector.open();
            Calendar ca = Calendar.getInstance();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true){
                selector.select();
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()){
                    SelectionKey key = it.next();
                    //处理掉后将键删除，避免重复消费
                    it.remove();
                    if (key.isAcceptable()){
                        SocketChannel socket = serverSocketChannel.accept();
                        socket.configureBlocking(false);
                        socket.register(selector,SelectionKey.OP_READ);
                        String message = "连接成功 你是第" + (selector.keys().size() - 1) + "个用户";
                        //向客户端发送消息
                        socket.write(ByteBuffer.wrap(message.getBytes()));
                        InetSocketAddress address = (InetSocketAddress) socket.getRemoteAddress();
                        //输出客户端地址
                        System.out.println(ca.getTime() + "\t" + address.getHostString() +
                                ":" + address.getPort() + "\t");
                        System.out.println("客戶端已连接");
                        System.out.println("=========================================================");
                    }

                    if (key.isReadable()){
                        SocketChannel socket = (SocketChannel) key.channel();
                        InetSocketAddress address = (InetSocketAddress) socket.getRemoteAddress();
                        System.out.println(ca.getTime() + "\t" + address.getHostString() +
                                ":" + address.getPort() + "\t");
                        ByteBuffer bf = ByteBuffer.allocate(1024 * 4);
                        int len = 0;
                        byte[] res = new byte[1024 * 4];
                        //捕获异常，因为在客户端关闭后会发送FIN报文，会触发read事件，但连接已关闭,此时read()会产生异常
                        try {
                            while ((len = socket.read(bf)) != 0) {
                                bf.flip();
                                bf.get(res, 0, len);
                                System.out.println(new String(res, 0, len));
                                bf.clear();
                            }
                            System.out.println("=========================================================");
                        } catch (IOException e) {
                            //客户端关闭了
                            key.cancel();
                            socket.close();
                            System.out.println("客戶端已断开");
                            System.out.println("=========================================================");
                        }
                    }
                }
            }

        }catch (IOException e){
            e.printStackTrace();
            System.out.println("服务器异常，即将关闭");
        }finally {

        }
    }
}
