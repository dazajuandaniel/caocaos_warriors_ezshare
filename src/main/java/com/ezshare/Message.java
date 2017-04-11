package com.ezshare;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Message {
	@JsonView(Views.comm.class)
	public String command;
	
	@JsonView(Views.Query.class)
	public boolean relay;
	
	@JsonView(Views.Share.class)
	public 	String secret;
	
	@JsonView(Views.norm.class)
	public Resource resource;
	
	@JsonView(Views.Fetch.class)
	public Resource resourceTemplate;

	
	public Message() {
		resource = new Resource();
		// Define the default value for resources
		resource.uri = "";
		resource.name="";
		resource.description="";
		resource.owner="";
		resource.channel="";
		resourceTemplate = resource;
		relay = true;
		Relay isrelay = new Relay(resourceTemplate, relay);
	}
	
	public String toJson() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (command.equals("FETCH")){
				return mapper.writerWithView(Views.Fetch.class).writeValueAsString(this);
			}else if(command.equals("QUERY")){
				return mapper.writerWithView(Views.Query.class).writeValueAsString(this);
			}else if(command.equals("SHARE")){
				return mapper.writerWithView(Views.Share.class).writeValueAsString(this);
			}else{
				return mapper.writerWithView(Views.norm.class).writeValueAsString(this);
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
}
