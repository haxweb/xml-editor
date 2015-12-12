package fr.haxweb.xmleditor.ws.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;

@Path("/read/{file}")
@Api("read")
public class XmlReader {

	@GET
	@Produces("application/json")
	public Response read(@PathParam("file") String fileName) {
		return Response.ok("{ 'filename' : '" + fileName + "' }").build();
	}
	
}
 