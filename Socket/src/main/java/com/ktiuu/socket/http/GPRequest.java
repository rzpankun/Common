package com.ktiuu.socket.http;

import java.io.InputStream;

/**
 * @Create by pankun
 * @DATE 2020/9/3
 */
public class GPRequest {
    private String uri;
    private String method;

    public GPRequest(InputStream inputStream){
        try{
            String contnet = "";
            byte[] buffer = new byte[1024];
            int length = 0 ;
            if((length =inputStream.read(buffer)) > 0){
                contnet = new String(buffer,0,length);
            }
            String line = contnet.split("\\n")[0];
            String[] split = line.split("\\s");
            this.method = split[0];
            this.uri = split[1].split("\\?")[0];
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public String getUri() {
        return uri;
    }

    public String getMethod() {
        return method;
    }
}
