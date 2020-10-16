package com.ktiuu.socket.http;

import com.ktiuu.niosocket.Client;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Create by pankun
 * @DATE 2020/9/3
 */
public class GPTomcat {
    private int port = 8080;
    private ServerSocket server;
    private Map<String ,GPServlet> servletMapping = new HashMap<>();
    private Properties webxml = new Properties();

    private void init(){
        try {
            String path = this.getClass().getResource("/").getPath();
            FileInputStream fis = new FileInputStream(path + "web.properties");
            webxml.load(fis);
            for(Object key :webxml.keySet()){
                String s = key.toString();
                if(s.endsWith(".url")){
                    String servletName = s.replaceAll("\\.url$", "");
                    String url = webxml.getProperty(s);
                    String className = webxml.getProperty(servletName + ".ClassName");
                    GPServlet servlet = (GPServlet) Class.forName(className).newInstance();
                    servletMapping.put(url, servlet);
                }
            }
        }catch (Exception ex){

        }finally {

        }
    }


    public void start(){
        init();

        try{
            server = new ServerSocket(port);
            while(true){
                Socket accept = server.accept();
                process(accept);
            }
        }catch (Exception e){

        }
    }

    public void process(Socket accept) throws Exception{
        InputStream inputStream = accept.getInputStream();
        OutputStream outputStream = accept.getOutputStream();
        GPRequest request = new GPRequest(inputStream);
        GPResponse response = new GPResponse(outputStream);
        String uri = request.getUri();
        if(servletMapping.containsKey(uri)){
            servletMapping.get(uri).service(request, response);
        }else{
            response.write("404 - Not Found");
        }

        outputStream.flush();
        outputStream.close();

        inputStream.close();
        accept.close();
    }

    public static void main(String[] args) {
        new GPTomcat().start();
    }
}
