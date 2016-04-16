package fr.haxweb.xmleditor.core.xsd.services;
import org.apache.log4j.Logger;
import org.junit.Test;

import fr.haxweb.xmleditor.core.xsd.jaxb.Element;
import fr.haxweb.xmleditor.core.xsd.jaxb.SimpleType;
import fr.haxweb.xmleditor.core.xsd.simple.SimpleSchema;
import junit.framework.Assert;

public class XsdUnmarshallerTest {

	public static final Logger LOGGER = Logger.getLogger(XsdUnmarshallerTest.class);

	@Test
	public void testUnmarshal() {
		SimpleSchema simpleSchema = XsdUnmarshaller.unmarshall("xhtml1-strict");
		
		Assert.assertNotNull(simpleSchema);

		LOGGER.info("============");
		LOGGER.info("TOP Level Complex Elements");
		SimpleType element = null;
		for (String name : simpleSchema.simpleTypes.keySet()) {
			LOGGER.info(name);
			element = simpleSchema.simpleTypes.get(name);
		}
		LOGGER.info("============");
	}
	
	@Test
	public void testListXsd() {
		Assert.assertNotNull(XsdUnmarshaller.listXsds());
	}
	
	@Test
	public void testReferenceResolver() {
		SimpleSchema simpleSchema = XsdUnmarshaller.unmarshall("xhtml1-strict");
		Assert.assertNotNull(simpleSchema);
		Assert.assertNotNull(simpleSchema.elements.get("html"));
		
		Element rootElement = simpleSchema.resolveRoot();
		
		LOGGER.info("Found root element : " + rootElement.getName());
		Assert.assertEquals("html", rootElement.getName());
	}

}
