package com.pricillatan.app.jobs;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.pricillatan.app.entry.Stock;
import com.pricillatan.app.vendors.TelegramService;
import com.pricillatan.app.vendors.YahooFinService;

import java.io.IOException;
import java.util.*;

@Configuration
@Service
public class MySummaryJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySummaryJob.class);
    

    @Autowired
    private YahooFinService finSrv;
    @Autowired
    private TelegramService telegramSrv;
    


	@Value("${FIN_REGION}")
	private String FIN_REGION;
	@Value("${FIN_SYMBOLS}")
	private String[] FIN_SYMBOLS;
	
	public String getMyList() {
		try {
			List<Stock> results = finSrv.getStocks(FIN_REGION,FIN_SYMBOLS);
			
			StringBuffer sb = new StringBuffer();
			for(Stock stock:results) {
				sb.append("<strong>"+stock.getSymbol()+":"+stock.getLongName()+"</strong>");
				sb.append("<pre> ");
				sb.append("Bid: "+stock.getBid() +" ---- ");
				sb.append("Ask: "+stock.getAsk());
				sb.append("</pre>");
			}
			telegramSrv.sendMsg(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
    }
    
}
