package com.pricillatan.app.vendors;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.pricillatan.app.entry.Stock;
import com.pricillatan.common.utils.GetSend;
import com.pricillatan.common.utils.JsonUtil;

import java.io.IOException;
import java.util.*;

@Configuration
@Service
public class YahooFinServiceImpl implements YahooFinService {

    private static final Logger LOGGER = LoggerFactory.getLogger(YahooFinServiceImpl.class);
    

	
	@Value("${YAHOO_FIN_KEY}")
	private String YAHOO_FIN_KEY="";
	@Value("${YAHOO_FIN_HOST}")
	private String YAHOO_FIN_HOST="";
	@Value("${YAHOO_FIN_URL}")
	private String YAHOO_FIN_URL="";
	
	private Map<String, Object> sendGet(String path){
		String fullUrl = YAHOO_FIN_URL;
		
		fullUrl+=path;
		Map<String,String> headers = new HashMap<String,String>();
    	headers.put("x-rapidapi-host",YAHOO_FIN_HOST);
    	headers.put("x-rapidapi-key",YAHOO_FIN_KEY);
    	headers.put("useQueryString","true");
		
		return GetSend.sendForResult(fullUrl, headers);
	}
    
	@Override
	public  String getInfo() throws IOException {
		String path ="/market/get-summary";
		Map<String, Object> res = sendGet(path);
		if(res==null) {
    		return null;
    	}
		return (String)res.get("body");
	} 

	@Override
	public  List<Stock>  getStocks(String region, String[] symbols) throws IOException {
		String sym=Arrays.toString(symbols).replace("[", "").replace("]", "").replace(" ", "").trim();
		LOGGER.info(sym);  
		String path ="/market/get-quotes?region="+region+"&lang=en&symbols="+sym;
		Map<String, Object> res = sendGet(path);
		if(res==null) {
    		return null;
    	}
		LOGGER.info((String)res.get("body"));
		List<Stock> stocks=new ArrayList();
		Map<String,Object> resp= JsonUtil.convJson2Map((String)res.get("body"), String.class, Object.class);
		if(resp.containsKey("quoteResponse")) {
			Map<String,Object> quoteResp=(Map<String,Object>)resp.get("quoteResponse");
			if(quoteResp.containsKey("result")) {
				stocks=JsonUtil.convList(quoteResp.get("result"),Stock.class);
			}
		}
		return stocks;
	} 
}
