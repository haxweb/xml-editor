import java.io.File;

import org.apache.log4j.Logger;
import org.junit.Test;

import fr.haxweb.xmleditor.core.xsd.jaxb.NamedAttributeGroup;
import fr.haxweb.xmleditor.core.xsd.jaxb.NamedGroup;
import fr.haxweb.xmleditor.core.xsd.jaxb.OpenAttrs;
import fr.haxweb.xmleditor.core.xsd.jaxb.Schema;
import fr.haxweb.xmleditor.core.xsd.jaxb.TopLevelComplexType;
import fr.haxweb.xmleditor.core.xsd.jaxb.TopLevelElement;
import fr.haxweb.xmleditor.core.xsd.jaxb.TopLevelSimpleType;
import fr.haxweb.xmleditor.core.xsd.services.XsdUnmarshaller;
import junit.framework.Assert;

public class XsdUnmarshallerTest {

	public static final Logger LOGGER = Logger.getLogger(XsdUnmarshallerTest.class);

	@Test
	public void test() {
		Schema schema = XsdUnmarshaller.unmarshall(new File("src/test/resources/xhtml1-strict.xsd"));
		LOGGER.info(schema);
		LOGGER.info("Other Attributes : " + schema.getOtherAttributes());
		
		Assert.assertNotNull(schema);
		
		for (OpenAttrs open : schema.getSimpleTypeOrComplexTypeOrGroup()) {
			if (open instanceof TopLevelSimpleType) {
				LOGGER.info("TopLevel SimpleType : " + ((TopLevelSimpleType) open).getName());
			}
			if (open instanceof NamedAttributeGroup) {
				LOGGER.info("NamedAttributeGroup : " + ((NamedAttributeGroup) open).getName());
			}
			if (open instanceof NamedGroup) {
				LOGGER.info("NamedGroup : " + ((NamedGroup) open).getName());
			}
			if (open instanceof TopLevelComplexType) {
				LOGGER.info("TopLevelComplexType : " + ((TopLevelComplexType) open).getName());
			}
			if (open instanceof TopLevelElement) {
				LOGGER.info("TopLevelElement : " + ((TopLevelElement) open).getName());
			}
		}

	}

}
