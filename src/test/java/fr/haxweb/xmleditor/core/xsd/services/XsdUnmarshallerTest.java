package fr.haxweb.xmleditor.core.xsd.services;
import org.apache.log4j.Logger;
import org.junit.Test;

import fr.haxweb.xmleditor.core.xsd.jaxb.Schema;
import fr.haxweb.xmleditor.core.xsd.simple.SimpleSchema;
import junit.framework.Assert;

public class XsdUnmarshallerTest {

	public static final Logger LOGGER = Logger.getLogger(XsdUnmarshallerTest.class);

	@Test
	public void test() {
		Schema schema = XsdUnmarshaller.unmarshall("xhtml1-strict");
		LOGGER.info(schema);
		LOGGER.info("Other Attributes : " + schema.getOtherAttributes());
		
		SimpleSchema simpleSchema = SimpleSchema.Builder.fromJaxbSchema(schema);
		
		Assert.assertNotNull(schema);
		
		LOGGER.info(simpleSchema);
	}

}
