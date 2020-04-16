package com.sample.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class TestJSON {

	public static void main(String[] args) throws Exception{
		
		
		String json = "{\"test\": \"abc\"}";
		ObjectMapper mapper = new ObjectMapper();
		Ab ab= mapper.readValue(json, Ab.class);
		System.out.println("----");
		System.out.println(mapper.writeValueAsString(ab));
		
	}
	
	
}

@JsonDeserialize(as = Abc.class)
interface Ab {
	
	String getTest();
}

class Abc implements Ab {
	
	private String test;
	public String getTest() {
		return test;
	}
}
