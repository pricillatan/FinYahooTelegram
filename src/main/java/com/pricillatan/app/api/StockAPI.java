package com.pricillatan.app.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pricillatan.app.entry.Stock;
import com.pricillatan.app.vendors.TelegramService;
import com.pricillatan.app.vendors.YahooFinService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Configuration
@Path("/stock")
public class StockAPI {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockAPI.class);

    private static final ObjectMapper objMapper = new ObjectMapper();

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private YahooFinService service;
    @Autowired
    private TelegramService telegramSrv;
    

    @GET
    @Path("/alive")
    @Produces(MediaType.APPLICATION_JSON)
    public Response keepalive() {
        // save and process
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("alive: ");
        }
        return Response.ok("awake").build();
    }
    @GET
    @Path("/summary")
    @Produces(MediaType.APPLICATION_JSON)
    public Response summary(String payload) {
        // save and process
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("payload: {}", payload);
        }
        String resp;
		try {
			resp = service.getInfo();
			LOGGER.debug("resp: {}", resp);
	        return Response.ok().entity(resp).build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return Response.ok().build();
    }


	@Value("${FIN_REGION}")
	private String FIN_REGION;
	@Value("${FIN_SYMBOLS}")
	private String[] FIN_SYMBOLS;
    @GET
    @Path("/mylist")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMyList(String payload) {
        // save and process
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("payload: {}", payload);
        }
        String resp;
		try {
			List<Stock> results = service.getStocks(FIN_REGION,FIN_SYMBOLS);
//			LOGGER.debug("resp: {}", resp);
			
			StringBuffer sb = new StringBuffer();
			for(Stock stock:results) {
				sb.append("<strong>"+stock.getSymbol()+":"+stock.getLongName()+"</strong>");
				sb.append("<pre> ");
				sb.append("Bid: "+stock.getBid() +" ---- ");
				sb.append("Ask: "+stock.getAsk());
				sb.append("</pre>");
			}
			
			
			telegramSrv.sendMsg(sb.toString());
	        return Response.ok().entity(results).build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return Response.ok().build();
    }

}
