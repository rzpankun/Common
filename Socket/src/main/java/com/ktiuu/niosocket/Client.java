package com.ktiuu.niosocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Create by pankun
 * @DATE 2020/9/2
 */
public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress("127.0.0.1", 8000));
        ByteBuffer buffer = ByteBuffer.wrap("Hello world".getBytes());
        if(channel.finishConnect()){
            while (buffer.hasRemaining()){
                channel.write(buffer);
            }
        }
        channel.close();
    }
}
