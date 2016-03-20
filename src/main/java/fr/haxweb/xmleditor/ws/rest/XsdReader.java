package fr.haxweb.xmleditor.ws.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.haxweb.xmleditor.core.xsd.services.XsdUnmarshaller;
import io.swagger.annotations.Api;

@Api("read")
@Path("/read")
public class XmlReader {

	@GET
	@Path("/{file}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response read(@PathParam("file") String fileName) {
		return Response.ok(XsdUnmarshaller.unmarshall(fileName)).build();
	}
	
}
 