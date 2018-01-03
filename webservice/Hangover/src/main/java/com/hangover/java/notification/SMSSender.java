package com.hangover.java.notification;

import com.hangover.java.exception.HangoverException;
import com.hangover.java.util.CommonUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/2/16
 * Time: 3:59 PM
 * To change this template use File | Settings | File Templates.
 */
@Component("smsSender")
@Scope("prototype")
public class SMSSender implements MessageSender{

    private static Logger logger = LoggerFactory.getLogger(SMSSender.class);

    @Override
    public void send(Message message) {
        if(message.isPromotional())
            sendPromotionalMessage(StringUtils.join(message.getTo(), ","), message.getContent());
        else
            sendTransactionalMessage(StringUtils.join(message.getTo(), ","), message.getContent());
        /*final CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try{
            StringBuilder urlBuilder = new StringBuilder(CommonUtil.getProperty("SMS_END_POINT_URL"));
            urlBuilder.append("?");
            urlBuilder.append("authkey=");
            urlBuilder.append(CommonUtil.getProperty("SMS_API_KEY"));
            urlBuilder.append("&sender=");
            urlBuilder.append(CommonUtil.getProperty("SMS_SENDER_ID"));
            urlBuilder.append("&route=");
            urlBuilder.append(CommonUtil.getProperty("SMS_ROUTE"));
            urlBuilder.append("&mobiles=");
            urlBuilder.append(message.getTo()[0]+"");
            urlBuilder.append("&message=");
            urlBuilder.append(URLEncoder.encode(message.getContent()));
            HttpGet request = new HttpGet(urlBuilder.toString());
            HttpResponse response = httpClient.execute(request);
            String mresult = EntityUtils.toString(response.getEntity());
            System.out.println("result........" +mresult);
        } catch (ClientProtocolException e) {
            logger.error("SMS Client error:- "+e.getMessage());
            throw new HangoverException(e);
        } catch (UnsupportedEncodingException e) {
            logger.error("SMS Client error:- "+e.getMessage());
            throw new HangoverException(e);
        } catch (IOException e) {
            logger.error("SMS Client error:- "+e.getMessage());
            throw new HangoverException(e);
        }finally {
            try {
                if(null != httpClient)
                    httpClient.close();
            } catch (IOException e) {
                logger.error("Unale to close SMS client connection:- " + e.getMessage());
            }
        } */
    }
    
    
    private void sendPromotionalMessage(String to, String message){
        final CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try{
            StringBuilder urlBuilder = new StringBuilder(CommonUtil.getProperty("PRO_SMS_END_POINT_URL"));
            urlBuilder.append("?");
            urlBuilder.append("authkey=");
            urlBuilder.append(CommonUtil.getProperty("PRO_SMS_API_KEY"));
            urlBuilder.append("&sender=");
            urlBuilder.append(CommonUtil.getProperty("PRO_SMS_SENDER_ID"));
            urlBuilder.append("&route=");
            urlBuilder.append(CommonUtil.getProperty("PRO_SMS_ROUTE"));
            urlBuilder.append("&mobiles=");
            urlBuilder.append(to);
            urlBuilder.append("&message=");
            urlBuilder.append(URLEncoder.encode(message));
            HttpGet request = new HttpGet(urlBuilder.toString());
            HttpResponse response = httpClient.execute(request);
            String mresult = EntityUtils.toString(response.getEntity());
            System.out.println("result........" +mresult);
        } catch (ClientProtocolException e) {
            logger.error("SMS Client error:- "+e.getMessage());
            throw new HangoverException(e);
        } catch (UnsupportedEncodingException e) {
            logger.error("SMS Client error:- "+e.getMessage());
            throw new HangoverException(e);
        } catch (IOException e) {
            logger.error("SMS Client error:- "+e.getMessage());
            throw new HangoverException(e);
        }finally {
            try {
                if(null != httpClient)
                    httpClient.close();
            } catch (IOException e) {
                logger.error("Unable to close SMS client connection:- " + e.getMessage());
            }
        }
    }

    private void sendTransactionalMessage(String to, String message){
        final CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try{
            StringBuilder urlBuilder = new StringBuilder(CommonUtil.getProperty("TRNS_SMS_END_POINT_URL"));
            urlBuilder.append("?");
            urlBuilder.append("authkey=");
            urlBuilder.append(CommonUtil.getProperty("TRNS_SMS_API_KEY"));
            urlBuilder.append("&sender=");
            urlBuilder.append(CommonUtil.getProperty("TRNS_SMS_SENDER_ID"));
            urlBuilder.append("&route=");
            urlBuilder.append(CommonUtil.getProperty("TRNS_SMS_ROUTE"));
            urlBuilder.append("&mobiles=");
            urlBuilder.append(to);
            urlBuilder.append("&message=");
            urlBuilder.append(URLEncoder.encode(message));
            HttpGet request = new HttpGet(urlBuilder.toString());
            HttpResponse response = httpClient.execute(request);
            String mresult = EntityUtils.toString(response.getEntity());
            System.out.println("result........" +mresult);
        } catch (ClientProtocolException e) {
            logger.error("SMS Client error:- "+e.getMessage());
            throw new HangoverException(e);
        } catch (UnsupportedEncodingException e) {
            logger.error("SMS Client error:- "+e.getMessage());
            throw new HangoverException(e);
        } catch (IOException e) {
            logger.error("SMS Client error:- "+e.getMessage());
            throw new HangoverException(e);
        }finally {
            try {
                if(null != httpClient)
                    httpClient.close();
            } catch (IOException e) {
                logger.error("Unable to close SMS client connection:- " + e.getMessage());
            }
        }
    }
}
