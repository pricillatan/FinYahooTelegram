package com.pricillatan;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pricillatan.app.api.StockAPI;

import java.util.function.Function;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/api")
public class JerseyApiConfig extends ResourceConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(JerseyApiConfig.class);

    public JerseyApiConfig() {
        LOGGER.info("init...");
        register(CORSResponseFilter.class);
        register(GenericExceptionMapper.class);

        register(StockAPI.class);
    }
    
    
    
    public static void main(String [] args) {
    	
    	// Function which takes in a number 
        // and returns half of it 
        Function<Integer, Double> half = a -> a / 2.0; 
    	Function<String, String> append = string -> string + ".";
    	
    	
    	Function<Integer, Function<Integer,Integer>> makeAdder = Utils::adder;

    	Function<Integer,Integer> add1 = makeAdder.apply(1);

        System.out.println(add1.apply(2)); 
        // apply the function to get the result 
        System.out.println(half.apply(10)); 
    	
        System.out.println(append.apply("hey")); 
        
//    	Function<String, String> append = (String s) -> s + " ";

    	// compile error in Java 10

//    	Function<String, String> append = (var string) -> string + " ";
    	
    	
    	var multiline = "This\r\nis a\r\nmultiline\r\nstring";

	    // we now have a `Stream<String>`
    	multiline.lines().map(line -> "// " + line)
    	    .forEach(System.out::println);
    }
}

 class Utils {

    public static Function<Integer, Integer> adder(Integer x) {
       return y -> x + y;
    }

}
