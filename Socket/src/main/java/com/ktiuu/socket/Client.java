package com.ktiuu.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @Create by pankun
 * @DATE 2020/9/2
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1", 8000));
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(outputStream));
        pw.print("hello world");
        pw.close();
        outputStream.close();
    }
}
