package fr.haxweb.xmleditor.ws.rest;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.junit.Test;

import fr.haxweb.xmleditor.core.xsd.jaxb.Schema;
import junit.framework.Assert;

public class XmlReaderTest {

	public final static XsdReader reader = new XsdReader();
	
	public final static Logger LOGGER = Logger.getLogger(XmlReaderTest.class);
	
	@Test
	public void readTest() {
		Response response = reader.read("xhtml1-strict");
		Assert.assertEquals(200, response.getStatus());
//		Assert.assertEquals(response.getEntity().getClass(), Schema.class);
	}
	
}
