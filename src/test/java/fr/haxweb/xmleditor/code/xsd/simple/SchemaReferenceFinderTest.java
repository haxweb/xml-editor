package fr.haxweb.xmleditor.code.xsd.simple;

import org.apache.log4j.Logger;
import org.junit.Test;

import fr.haxweb.xmleditor.core.xsd.simple.SchemaReferenceFinder;
import junit.framework.Assert;

public class SchemaReferenceFinderTest extends AbstractSchemaReferenceFinderTest {

	public static Logger LOGGER = Logger.getLogger(SchemaReferenceFinderTest.class);
	
	@Test
	public void testHasRefElementInChoice() {
		Assert.assertNotNull(schema);
		boolean hasRef = SchemaReferenceFinder.hasRef(
			schema, 
			schema.elements.get("sub-element"), 
			schema.elements.get("root-element")
		);
		Assert.assertTrue(hasRef);
	}
	
	@Test
	public void testHasRefElementInSequence() {
		Assert.assertNotNull(schema);
		boolean hasRef = SchemaReferenceFinder.hasRef(
				schema, 
				schema.elements.get("sub-element"), 
				schema.elements.get("root-element")
				);
		Assert.assertTrue(hasRef);
	}
	
	@Test
	public void testHasRefElementInGroupRefGroup() {
		Assert.assertNotNull(schema);
		boolean hasRef = SchemaReferenceFinder.hasRef(
				schema, 
				schema.elements.get("sub-element"), 
				schema.elements.get("root-element")
				);
		Assert.assertTrue(hasRef);
	}
	

}
