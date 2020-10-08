package com.pricillatan.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class ECTSApp  extends Thread {
	@Autowired
	public RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restTemplate(){
		if(restTemplate==null){
			restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		}
		return restTemplate;
	}
	
	ECTSApp(){}
	private String name;
	public ECTSApp(String name){ this.name=name;}
	
	public void run()
    {
		System.out.println("["+name+"]Thread started running..");
		System.out.println("["+name+"]"+ postToAPI());
    
    }
	public String postToAPI() {
		String url = "http://localhost:9200/v3/trips/new";		
		String accessToken= "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2MDAyMjM0MTAsImlhdCI6MTYwMDEzNzAxMCwic3ViIjoic3lzdGVzdCxzeXN0ZXN0In0.XFWTQgMGfurQcyP_iOq6V9AxgrKC4femSwAcJp0dGUhXbMlTtf7ABWy1WwvHQ6BnInOozd0J3vnA8Lr97YEVEg";
		// create headers
		HttpHeaders headers = new HttpHeaders();
		// set `content-type` header
		headers.setContentType(MediaType.APPLICATION_JSON);
		// set `accept` header
//		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", "Bearer " + accessToken);
		
		// request body parameters
		HashMap<String,Object> map = makeTrip();

		// build the request
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
		// send POST request
		ResponseEntity<String> response = restTemplate().postForEntity(url, entity, String.class);
		// check response
		if (response.getStatusCode() == HttpStatus.OK) {
		    System.out.println("Request Successful");
		    System.out.println(response.getBody());
		    return response.getBody();
		} else {
		    System.out.println("Request Failed");
		    System.out.println(response.getStatusCode());
		    return ""+response.getStatusCode();
		}
	}
	
	
	public HashMap<String, Object> makeTrip(){
		HashMap<String, Object> trip = new HashMap<String, Object>();
		trip.put("bl_number", "BL12345");
		trip.put("driver_contact", "123223344");
		trip.put("driver_name", "Sam Paul");
		trip.put("gen_cargo_desc", "The content of the cargo is stationary");
		trip.put("importer_id", "Canon Printers");
		trip.put("permit_number", "P12345");
		trip.put("swap_origin", "1");
		trip.put("transportation_type", "1");
		trip.put("vessel_number", "V-989898");
		trip.put("vessel_reg_number", "PA 12345 DB");
		trip.put("route_code", "Route-100PP-ARC");

//		HashMap<String, Object> dtl = new HashMap<String, Object>();
//		dtl.put("container_no", "ABC");
//		dtl.put("entry_number", "EntryNumber123");
//		dtl.put("vehicle_no", "PA22234");
//		List<HashMap<String, Object>> dtls = new ArrayList<HashMap<String, Object>>();
//		dtls.add(dtl);

//		trip.put("trip_detail", "dtls");
		 
		return trip;
	}
}
