package com.hangover.java.notification;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 10/24/16
 * Time: 12:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class FCMResponse {

    HttpsURLConnection connection;
    public FCMResponse(HttpsURLConnection con){
        connection = con;
    }

    public String toString(){
        return String.format("Response: %d\nMessage: '%s'", getResponseCode(), getResponseMessage());
    }

    public int getResponseCode(){
        try {
            return connection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String getResponseMessage(){
        InputStream in = connection.getErrorStream();
        if(in == null)
            return "";
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        StringBuilder total = new StringBuilder();
        String line = null;
        try {
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            r.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total.toString();
    }

}
