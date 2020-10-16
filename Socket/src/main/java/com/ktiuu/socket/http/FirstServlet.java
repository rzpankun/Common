package com.ktiuu.socket.http;

import java.io.IOException;

/**
 * @Create by pankun
 * @DATE 2020/9/3
 */
public class FirstServlet extends GPServlet {
    @Override
    public void doGet(GPRequest request, GPResponse response) throws IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(GPRequest request, GPResponse response) throws IOException {
        response.write("This is First Servlet");
    }
}
