package com.ktiuu.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Create by pankun
 * @DATE 2020/9/9
 */
public class DingTalkRobotUtil {
    private static final Logger log = LoggerFactory.getLogger(DingTalkRobotUtil.class);

    public static void sendTextMessage(String WEBHOOK_TOKEN,String msgText,boolean isAll) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(WEBHOOK_TOKEN);
        httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        ObjectNode at = objectMapper.createObjectNode();
        at.put("isAtAll", isAll);
        objectNode.set("at", at);
        objectNode.put("msgtype", "text");
        ObjectNode text = objectMapper.createObjectNode();
        text.put("content", msgText + "\t\n");
        objectNode.set("text", text);
        String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectNode);
        System.out.println(s);


        StringEntity stringEntity = new StringEntity(s, "utf-8");
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse execute = httpClient.execute(httpPost);
        if(execute.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String s1 = EntityUtils.toString(execute.getEntity(), "utf-8");
            log.info(s1);
        }
    }


    public static void main(String[] args) throws IOException {
        sendTextMessage("","1111111",false);
    }
}
