package br.com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.model.WebServiceModel;

@Path("/ws")
public class WebServiceController {
	List <WebServiceModel> web = null;

	/* CHAMADAS JSON  */

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<WebServiceModel> listJson(){	
		return web;
	}
	
	@GET
	@Path("/find/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public WebServiceModel findJson(@PathParam("id") int id){
		WebServiceModel obj = null;		
		for (WebServiceModel webServiceModel : web) {
			if(webServiceModel.getId() == id) {
				obj = webServiceModel;
			} 
		}				
		return obj;		
	}
	
	@DELETE
	@Path("/delete/{id}")	
	@Produces(MediaType.APPLICATION_JSON)
	public List<WebServiceModel> deleteJson(@PathParam("id") int id){
		WebServiceModel obj = null;		
		for (WebServiceModel webServiceModel : web) {
			if(webServiceModel.getId() == id) {
				obj = webServiceModel;	
				break;
			} 
		}		
		web.remove(obj);
		return listJson();	
	}
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public List<WebServiceModel> createJson(WebServiceModel ws){				
		web.add(ws);
		return web;
	}	

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public List<WebServiceModel> updateJson(WebServiceModel ws){		
		deleteJson(ws.getId());
		web.add(ws);
		return web;
	}
	
	// LIST PARA JSON E XML
	public WebServiceController() {
		
		web = new ArrayList<WebServiceModel>();
		
		WebServiceModel obj = new WebServiceModel();
		obj.setId(1);
		obj.setNome("Joao");
		web.add(obj);
		
		obj = new WebServiceModel();
		obj.setId(2);
		obj.setNome("Ricardo");
		web.add(obj);
		
		obj = new WebServiceModel();
		obj.setId(3);
		obj.setNome("Passos");
		web.add(obj);
		
		obj = new WebServiceModel();
		obj.setId(4);
		obj.setNome("Dos");
		web.add(obj);
		
		obj = new WebServiceModel();
		obj.setId(5);
		obj.setNome("Santos");
		web.add(obj);		
		
	}
}
