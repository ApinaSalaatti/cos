package com.dogshitempire.cos.utilities;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Merioksan Mikko
 */
public class HTTPConnection {
    /**
     * 
     * @param urlString URL to connect to
     * @return the response from the url or "ERROR" if something crazy goes wrong
     */
    public static String openURL(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            BufferedInputStream buff = new BufferedInputStream(stream);
            int character = -1;
            String response = new String();
            while ((character = buff.read()) != -1) {
                response += (char) character;
            }
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}
