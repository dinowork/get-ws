package br.com.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class WebServiceControllerTest {
	
  static String url = "http://localhost:8080/get-ws/ws/";

  @Test
  public void testList() {
	    String retorno = ws("GET",url+"list",null);		
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
  
  public static String ws(String action, String url, String json ) {
	  	Client client = Client.create();
	  	WebResource webResource = client.resource(url);
	  	ClientResponse response = null; 
	  	
	  	switch (action) {
			case "POST": // create
				response = webResource.type("application/json").post(ClientResponse.class, json);
				break;
			case "PUT": //update
				response = webResource.type("application/json").put(ClientResponse.class, json);
				break;
			case "GET": //read
				response = webResource.accept("application/json").get(ClientResponse.class);
				break;
			case "DELETE": //delete
				response = webResource.accept("application/json").delete(ClientResponse.class);
				break;	
		}
		
		if (response.getStatus() != 200 && response.getStatus() != 201 ) {
		   throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		}
		return response.getEntity(String.class);
  }
  

}