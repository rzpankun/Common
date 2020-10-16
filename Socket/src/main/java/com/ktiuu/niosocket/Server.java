package com.ktiuu.niosocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @Create by pankun
 * @DATE 2020/9/2
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel open = ServerSocketChannel.open();
        open.configureBlocking(false);
        Selector selector = Selector.open();
        open.bind(new InetSocketAddress(8000));
        open.register(selector, SelectionKey.OP_ACCEPT);
        while(true){
            int select = selector.select();
            if(select > 0){
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while(iterator.hasNext()){
                    SelectionKey next = iterator.next();
                    if(next.isAcceptable()){
                        ServerSocketChannel channel = (ServerSocketChannel)next.channel();
                        SocketChannel accept = channel.accept();
                        accept.configureBlocking(false);
                        accept.register(selector, SelectionKey.OP_READ);
                    }
                    if(next.isReadable()){
                        SocketChannel channel = (SocketChannel) next.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int read = channel.read(buffer);
                        if(read > 0){
                            buffer.flip();
                            byte[] array = buffer.array();
                            System.out.println(new String(array , 0 , read));
                        }

                    }
                    iterator.remove();
                }

            }
        }
    }
}
