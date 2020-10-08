package com.pricillatan.app.vendors;


import org.springframework.stereotype.Service;

import com.pricillatan.app.entry.Stock;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public interface YahooFinService {

	String getInfo() throws IOException;
	
	public  List<Stock> getStocks(String region,String[] symbols) throws IOException;
}
