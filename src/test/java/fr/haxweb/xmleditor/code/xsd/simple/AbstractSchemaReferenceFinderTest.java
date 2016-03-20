package fr.haxweb.xmleditor.code.xsd.simple;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import fr.haxweb.xmleditor.core.xsd.services.XsdUnmarshaller;
import fr.haxweb.xmleditor.core.xsd.simple.SimpleSchema;
import junit.framework.TestCase;

public abstract class AbstractSchemaReferenceFinderTest extends TestCase {
	
	protected SimpleSchema schema;
	
	public static Logger LOGGER = Logger.getLogger(AbstractSchemaReferenceFinderTest.class);
	
	@Override
	public void setUp() {
		LOGGER.info("Running test : " + getName());
		loadTestFileFromTestName(getName());
	}
	
	public void loadTestFileFromTestName(String methodName) {
		String testFileName = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(methodName), "-").toLowerCase();
		
		if (!(new File(this.getClass().getClassLoader().getResource(testFileName + ".xsd").getFile()).exists())) {
			fail("Test file for test " + getName() + " not found");
		}
		
		LOGGER.info("With test file : " + testFileName + ".xsd");
		schema = XsdUnmarshaller.unmarshall(testFileName);
	}
	
}
