package com.ktiuu.socket.http;

import java.io.IOException;

/**
 * @Create by pankun
 * @DATE 2020/9/3
 */
public abstract class GPServlet {
    public void service(GPRequest request , GPResponse response) throws  Exception{
        if("GET".equalsIgnoreCase(request.getMethod())){
            doGet(request,response);
        }else{
            doPost(request, response);
        }
    }

    public abstract void doGet(GPRequest request,GPResponse response) throws IOException;
    public abstract void doPost(GPRequest request,GPResponse response) throws IOException;
}
