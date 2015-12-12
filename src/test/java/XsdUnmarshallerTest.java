import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import fr.haxweb.xmleditor.core.xsd.jaxb.ObjectFactory;
import fr.haxweb.xmleditor.core.xsd.jaxb.Schema;
import fr.haxweb.xmleditor.core.xsd.services.XsdUnmarshaller;


public class XsdUnmarshallerTest {

	@Test
	public void test() {
		Schema schema = XsdUnmarshaller.unmarshall(new File("src/test/resources/xhtml1-strict.xsd"));
		Assert.assertNotNull(schema);
	}

}
