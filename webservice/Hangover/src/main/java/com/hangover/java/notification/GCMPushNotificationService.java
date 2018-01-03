package com.hangover.java.notification;

import com.google.android.gcm.server.*;
import com.hangover.java.exception.HangoverException;
import com.hangover.java.util.CommonUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/11/16
 * Time: 10:15 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class GCMPushNotificationService /*implements PushNotificationService*/{

    private static Logger logger = LoggerFactory.getLogger(GCMPushNotificationService.class);

    protected static final String UTF8 = "UTF-8";

    private String key;


    /*@Override*/
    public void promotional(Message message) {
        JSONObject jsonObject = new JSONObject();
        makeGCMRequest(jsonObject);
    }

    /*@Override*/
    public void transactional(Message message) {
        /*JSONObject jsonObject = new JSONObject();
        makeGCMRequest(jsonObject);*/
        sendPush(message);
    }


    private Boolean sendPush(Message message){
        Sender sender = new Sender(CommonUtil.getProperty("GCM_API_KEY"), Endpoint.GCM); // Here you will write APP key given by Android end
        com.google.android.gcm.server.Message msg = new com.google.android.gcm.server.Message.Builder().setData(message.getContext()).build();
        Boolean response=null;
        try{
            MulticastResult results = sender.send(msg, Arrays.asList(message.getTo()), 5); // Where appId is given by Android end
            for (Result r : results.getResults()) {
                if (r.getMessageId() != null) {
                    response = true;
                } else{
                    response= false;
                    String error = r.getErrorCodeName();
                    if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
                    }
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }

    private void makeGCMRequest(JSONObject body){
        final CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try{
            HttpPost request = new HttpPost(CommonUtil.getProperty("GCM_END_POINTS"));
            request.setHeader("Content-type", MediaType.APPLICATION_JSON);
            request.setHeader("Authorization", "Key="+CommonUtil.getProperty("GCM_API_KEY"));
            StringEntity params = new StringEntity(body.toString());
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            String result = EntityUtils.toString(response.getEntity());
            System.out.println("result........" +result);
        } catch (ClientProtocolException e) {
            logger.error("GCM Client error:- "+e.getMessage());
            throw new HangoverException(e);
        } catch (UnsupportedEncodingException e) {
            logger.error("GCM Client error:- "+e.getMessage());
            throw new HangoverException(e);
        } catch (IOException e) {
            logger.error("GCM Client error:- "+e.getMessage());
            throw new HangoverException(e);
        }finally {
                try {
                    if(null != httpClient)
                        httpClient.close();
                } catch (IOException e) {
                    logger.error("Unable to close GCM client connection:- " + e.getMessage());
                }
        }
    }


    private String makeGcmHttpRequest(Map<Object, Object> jsonRequest) throws HangoverException {
        String requestBody = "";//JSONValue.toJSONString(jsonRequest);
        logger.info("JSON request: " + requestBody);
        HttpURLConnection conn;
        int status;
        try {
            conn = post(CommonUtil.getProperty("GCM_END_POINTS"), "application/json", requestBody);
            status = conn.getResponseCode();
        } catch (IOException e) {
            logger.error("IOException posting to GCM", e);
            return null;
        }
        String responseBody;
        if (status != 200) {
            try {
                responseBody = getAndClose(conn.getErrorStream());
                logger.info("JSON error response: " + responseBody);
            } catch (IOException e) {
                responseBody = "N/A";
                logger.error("Exception reading response: ", e);
            }
            throw new HangoverException(status, responseBody);
        }
        try {
            responseBody = getAndClose(conn.getInputStream());
        } catch(IOException e) {
            logger.error("IOException reading response", e);
            return null;
        }
        logger.info("JSON response: " + responseBody);
        return responseBody;
    }


    protected HttpURLConnection post(String url, String contentType, String body)
            throws IOException {
        if (url == null || contentType == null || body == null) {
            throw new IllegalArgumentException("arguments cannot be null");
        }
        if (!url.startsWith("https://")) {
            logger.warn("URL does not use https: " + url);
        }
        logger.info("Sending POST to " + url);
        logger.info("POST body: " + body);
        byte[] bytes = body.getBytes(UTF8);
        HttpURLConnection conn = getConnection(url);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setFixedLengthStreamingMode(bytes.length);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", contentType);
        conn.setRequestProperty("Authorization", "key=" + key);
        OutputStream out = conn.getOutputStream();
        try {
            out.write(bytes);
        } finally {
            close(out);
        }
        return conn;
    }

    private static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                // ignore error
                logger.error("IOException closing stream", e);
            }
        }
    }


    protected static void addParameter(StringBuilder body, String name,
                                       String value) {
        nonNull(body).append('&')
                .append(nonNull(name)).append('=').append(nonNull(value));
    }


    private static String getAndClose(InputStream stream) throws IOException {
        try {
            return getString(stream);
        } finally {
            if (stream != null) {
                close(stream);
            }
        }
    }


    protected static String getString(InputStream stream) throws IOException {
        if (stream == null) {
            return "";
        }
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(stream));
        StringBuilder content = new StringBuilder();
        String newLine;
        do {
            newLine = reader.readLine();
            if (newLine != null) {
                content.append(newLine).append('\n');
            }
        } while (newLine != null);
        if (content.length() > 0) {
            // strip last newline
            content.setLength(content.length() - 1);
        }
        return content.toString();
    }

    protected HttpURLConnection getConnection(String url) throws IOException {
        return (HttpURLConnection) new URL(url).openConnection();
    }

    static <T> T nonNull(T argument) {
        if (argument == null) {
            throw new IllegalArgumentException("argument cannot be null");
        }
        return argument;
    }



}
