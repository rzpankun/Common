package com.ktiuu.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Create by pankun
 * @DATE 2020/9/2
 */
public class server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8000);
        server.bind(address);
        while (true){
            Socket accept = server.accept();
            InputStream inputStream = accept.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String a ;
            while((a = bufferedReader.readLine())!= null){
                System.out.println(a);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        }
    }
}
