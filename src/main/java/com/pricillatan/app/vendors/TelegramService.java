package com.pricillatan.app.vendors;


import org.springframework.stereotype.Service;

import com.pricillatan.app.entry.Stock;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public interface TelegramService {

	String sendMsg(String msg) throws IOException;
	
}
