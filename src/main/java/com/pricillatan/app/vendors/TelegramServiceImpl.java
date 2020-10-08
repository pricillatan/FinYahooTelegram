package com.pricillatan.app.vendors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.pricillatan.common.utils.GetSend;

import java.io.IOException;
import java.util.*;


@Configuration
@Service
public class TelegramServiceImpl implements TelegramService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramServiceImpl.class);
    

	@Value("${TELEGRAM_TOKEN}")
	private String TOKEN;
	@Value("${TELEGRAM_CHANNEL_ID}")
	private String CHANNEL_ID;
	
	
	private Map<String, Object> sendGet(String path){
		String fullUrl = "https://api.telegram.org/bot"+TOKEN+"/";
		
		fullUrl+=path;
		LOGGER.info("fullpath:"+fullUrl);
		Map<String,String> headers = new HashMap<String,String>();
		
		return GetSend.sendForResult(fullUrl, headers);
	}

	@Override
	public String sendMsg(String msg) throws IOException {

	    String path="sendMessage?chat_id="+CHANNEL_ID+"&text="+msg+"&parse_mode=html";
	    Map<String, Object> res = sendGet(path);
		return "";
	}
    
}
