package br.com.controller;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.Before;
import org.junit.Test;


public class WebServiceControllerTest {
	
  static String url = "http://localhost:8080/get-ws/ws/";

  Client client = null;
  
  @Before
  public void setAntesTest() {
	  client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
  }
  
  @Test
  public void testList() {
	    String retorno =  ws("GET",url+"list",null);		
		System.out.println(" LIST :" + retorno);
		assertEquals("[{\"id\":1,\"nome\":\"Joao\"},{\"id\":2,\"nome\":\"Ricardo\"},{\"id\":3,\"nome\":\"Passos\"},{\"id\":4,\"nome\":\"Dos\"},{\"id\":5,\"nome\":\"Santos\"}]", retorno);
  } 
  
 @Test
  public void testUpdate() {
	 String retorno = ws("PUT",url+"update","{\"id\":\"1\",\"nome\":\"Joao Ricardo\"}");		
		System.out.println(" UPDATE :" + retorno);
		assertEquals("[{\"id\":2,\"nome\":\"Ricardo\"},{\"id\":3,\"nome\":\"Passos\"},{\"id\":4,\"nome\":\"Dos\"},{\"id\":5,\"nome\":\"Santos\"},{\"id\":1,\"nome\":\"Joao Ricardo\"}]", retorno);		
  }
 
  @Test
  public void testFind() {
	    String retorno = ws("GET",url+"find/4",null);		
		System.out.println(" FIND :" + retorno);
		assertEquals("{\"id\":4,\"nome\":\"Dos\"}",retorno);
  }
   
  @Test
  public void testDelete() {
	  	String  retorno = ws("DELETE",url+"delete/4",null);		
		System.out.println(" DELETE :" + retorno);
		assertEquals("[{\"id\":1,\"nome\":\"Joao\"},{\"id\":2,\"nome\":\"Ricardo\"},{\"id\":3,\"nome\":\"Passos\"},{\"id\":5,\"nome\":\"Santos\"}]",retorno);
  }
  
  @Test
  public void testCreate() {	  
	    String  retorno = ws("POST",url+"create","{\"id\":\"9\",\"nome\":\"Alexandre\"}");		
		System.out.println(" CREATE :" + retorno);
		assertEquals("[{\"id\":1,\"nome\":\"Joao\"},{\"id\":2,\"nome\":\"Ricardo\"},{\"id\":3,\"nome\":\"Passos\"},{\"id\":4,\"nome\":\"Dos\"},{\"id\":5,\"nome\":\"Santos\"},{\"id\":9,\"nome\":\"Alexandre\"}]",retorno);
  }
  
  public String ws(String action, String url, String json ) {
	  WebTarget  webTarget = client.target(url);
	  Invocation.Builder invocationBuilder = null;
	  Response response = null;
	  
	  	switch (action) {
			case "POST": // create				 	 
				   invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
				   response = invocationBuilder.post(Entity.json(json));
				break;
			case "PUT": //update				
				   invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
				   response = invocationBuilder.put(Entity.json(json));
				break;
			case "GET": //read				   
				   invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
				   response = invocationBuilder.get();	
				break;
			case "DELETE": //delete					   
				   invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
				   response = invocationBuilder.delete();
				break;	
		}
	  	
		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		}
				
		return response.readEntity(String.class);
		  	
  }
  
  
}