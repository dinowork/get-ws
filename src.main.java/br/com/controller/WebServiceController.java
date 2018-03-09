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
	

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<WebServiceModel> list(){	
		return web;
	}
	
	@GET
	@Path("/find/{id}")
	@Produces("application/json")
	public WebServiceModel find(@PathParam("id") int id){
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
	@Produces("application/json")
	public List<WebServiceModel> delete(@PathParam("id") int id){
		WebServiceModel obj = null;		
		for (WebServiceModel webServiceModel : web) {
			if(webServiceModel.getId() == id) {
				obj = webServiceModel;	
				break;
			} 
		}		
		web.remove(obj);
		return list();	
	}
	
	@POST
	@Path("/create")
	@Consumes("application/json")
	public List<WebServiceModel> create(WebServiceModel ws){				
		web.add(ws);
		return web;
	}	

	@PUT
	@Path("/update")
	@Consumes("application/json")
	public List<WebServiceModel> update(WebServiceModel ws){		
		delete(ws.getId());
		web.add(ws);
		return web;
	}
	
	// Criar List de Objetos	
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
