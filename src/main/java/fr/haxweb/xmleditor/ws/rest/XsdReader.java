package fr.haxweb.xmleditor.ws.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;

import fr.haxweb.xmleditor.core.xsd.jaxb.ComplexType;
import fr.haxweb.xmleditor.core.xsd.jaxb.Element;
import fr.haxweb.xmleditor.core.xsd.jaxb.Group;
import fr.haxweb.xmleditor.core.xsd.services.XsdUnmarshaller;
import fr.haxweb.xmleditor.core.xsd.simple.SchemaResolver;
import fr.haxweb.xmleditor.core.xsd.simple.SimpleSchema;
import io.swagger.annotations.Api;

@Api("read")
@Path("/read")
public class XsdReader {

	public static Logger LOGGER = Logger.getLogger(XsdReader.class);
	
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
			Element root = schema.resolveRoot();
			if (root != null) {
				LOGGER.info("Found root element of " + schema);
				return Response.ok(root).build();
			}
		}
		
		return Response.noContent().build();
	}
	
	@GET
	@Path("/{file}/element/{element}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response element(@PathParam("file") String fileName, @PathParam("element") String element) {
		SimpleSchema schema = XsdUnmarshaller.unmarshall(fileName);
		
		if (schema != null) {
			Element elementObject = SchemaResolver.resolveElementReference(schema, QName.valueOf(element));
			if (elementObject != null) {
				LOGGER.info("Found element " + element + " on schema " + schema);
				return Response.ok(elementObject).build();
			}
		}
		
		return Response.noContent().build();
	}
	
	@GET
	@Path("/{file}/group/{group}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response group(@PathParam("file") String fileName, @PathParam("group") String group) {
		SimpleSchema schema = XsdUnmarshaller.unmarshall(fileName);
		
		if (schema != null) {
			Group groupObject = SchemaResolver.resolveGroupReference(schema, QName.valueOf(group));
			if (groupObject != null) {
				LOGGER.info("Found group " + group + " on schema " + schema);
				return Response.ok(groupObject).build();
			}
		}
		
		return Response.noContent().build();
	}
	
	@GET
	@Path("/{file}/type/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response type(@PathParam("file") String fileName, @PathParam("type") String type) {
		SimpleSchema schema = XsdUnmarshaller.unmarshall(fileName);
		
		if (schema != null) {
			ComplexType complexTypeObject = SchemaResolver.resolveComplexTypeReference(schema, QName.valueOf(type));
			if (complexTypeObject != null) {
				LOGGER.info("Found complex type " + type + " on schema " + schema);
				return Response.ok(complexTypeObject).build();
			}
		}
		
		return Response.noContent().build();
	}
	
}
 