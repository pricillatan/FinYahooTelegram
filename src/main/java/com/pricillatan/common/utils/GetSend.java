package com.pricillatan.common.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GetSend {

    private static final Logger logger = LoggerFactory.getLogger(GetSend.class);

    public static final int CONNECT_TIMEOUT = 20000; // default 20 seconds
    public static final int READ_TIMEOUT = 10000;  // default 10 seconds

    public static final String RESULT_KEY_RESP_FLAG_BOOLEAN = "flag";
    public static final String RESULT_KEY_RESP_CODE_INT = "code";
    public static final String RESULT_KEY_RESP_MSG_STR = "message";
    public static final String RESULT_KEY_RESP_BODY_STR = "body";


    public static Map<String, Object> sendForResult(String fullUrl, Map<String,String> headers) {
    	Map<String, Object> result = new HashMap<>(4);
        try {
            URL url = new URL(fullUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(10000);
            con.setReadTimeout(10000);
            con.setRequestProperty("Content-Type", "application/json");
            if(headers!=null && headers.size()>0) {
            	Iterator<Map.Entry<String, String>> itr = headers.entrySet().iterator(); 
                
                while(itr.hasNext()) 
                { 
                     Map.Entry<String, String> entry = itr.next(); 
                     con.setRequestProperty(entry.getKey(),entry.getValue());
                } 
            }
            
            int responseCode = con.getResponseCode();
            logger.info("responseCode:{}",responseCode);
            
            StringBuilder response = new StringBuilder();
            try (
                    InputStreamReader ins = new InputStreamReader(con.getInputStream());
                    BufferedReader in = new BufferedReader(ins);
            ) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
            int resultCode = con.getResponseCode();
            result.put(RESULT_KEY_RESP_CODE_INT, resultCode);
            String resMsg = con.getResponseMessage();
            result.put(RESULT_KEY_RESP_MSG_STR, resMsg);
            
            con.disconnect();
            String resp = response.toString();
            logger.debug(resp);
            result.put(RESULT_KEY_RESP_BODY_STR, resp);
            
        } catch (IOException e) {
        	logger.error(e.getMessage(), e);
        	
        }
        return result;
    }
}
