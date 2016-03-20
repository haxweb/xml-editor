package fr.haxweb.xmleditor.ws.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.haxweb.xmleditor.core.xsd.jaxb.Element;
import fr.haxweb.xmleditor.core.xsd.services.XsdUnmarshaller;
import fr.haxweb.xmleditor.core.xsd.simple.SchemaHelper;
import fr.haxweb.xmleditor.core.xsd.simple.SimpleSchema;
import io.swagger.annotations.Api;

@Api("read")
@Path("/read")
public class XsdReader {

	@GET
	@Path("/{file}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response read(@PathParam("file") String fileName) {
		return Response.ok(XsdUnmarshaller.unmarshall(fileName)).build();
	}
	
	@GET
	@Path("/{file}/root")
	@Produces(MediaType.APPLICATION_JSON)
	public Response root(@PathParam("file") String fileName) {
		SimpleSchema schema = XsdUnmarshaller.unmarshall(fileName);
		
		if (schema != null) {
			Element rootElement = schema.resolveRoot();
			if (!SchemaHelper.hasRef(schema, rootElement)) {
				return Response.ok(rootElement).build();
			}
		}
		
		return Response.noContent().build();
	}
	
}
 