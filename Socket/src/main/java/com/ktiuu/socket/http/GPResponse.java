package com.ktiuu.socket.http;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @Create by pankun
 * @DATE 2020/9/3
 */
public class GPResponse {
    private OutputStream outputStream;

    public GPResponse(OutputStream outputStream){
        this.outputStream = outputStream;
    }

    public void write(String s) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\n").append("Content-Type:text/html;\n").append("\r\n").append(s);
        outputStream.write(sb.toString().getBytes());
    }
}
