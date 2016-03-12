package fr.haxweb.xmleditor.core.xsd.configurator;

import org.apache.log4j.Logger;

import fr.haxweb.xmleditor.core.xsd.jaxb.NamedAttributeGroup;
import fr.haxweb.xmleditor.core.xsd.jaxb.NamedGroup;
import fr.haxweb.xmleditor.core.xsd.jaxb.OpenAttrs;
import fr.haxweb.xmleditor.core.xsd.jaxb.TopLevelComplexType;
import fr.haxweb.xmleditor.core.xsd.jaxb.TopLevelElement;
import fr.haxweb.xmleditor.core.xsd.jaxb.TopLevelSimpleType;
import fr.haxweb.xmleditor.core.xsd.simple.SimpleSchema;

public class SchemaConfigurator implements ISchemaConfigurator {

	public static Logger LOGGER = Logger.getLogger(SchemaConfigurator.class);
	
	/**
	 * Main Method for all OpenAttrs attribute that can be configured
	 * on the SimpleSchema</br>
	 * This Method will lookup the sub-class of the given attribute
	 * and invoke the right Method to setup the element on SimpleSchema
	 */
	@Override
	public void configure(SimpleSchema schema, OpenAttrs attribute) {
		try {
			LOGGER.info("Configuration of element type : "+ attribute.getClass().getName());
			this.getClass().getMethod("configure", new Class[] { SimpleSchema.class, attribute.getClass() } ).invoke(this, new Object[] {schema, attribute});
		} catch (NoSuchMethodException e) {
			LOGGER.error("Cannot configure this type of element : " + attribute.getClass().getName(), e);
			return;
		} catch (SecurityException e) {
			LOGGER.error("Error during lookup of the right Method to configure the element of type : "  + attribute.getClass().getName(), e);
			return;
		} catch (Exception e) {
			LOGGER.error("Error during configuration of an element of type : "  + attribute.getClass().getName(), e);
		} 
	}
	
	@Override
	public void configure(SimpleSchema schema, TopLevelSimpleType topLevelSimpleType) {
		schema.simpleTypes.put(topLevelSimpleType.getName(), topLevelSimpleType);
	}
	
	@Override
	public void configure(SimpleSchema schema, TopLevelComplexType topLevelComplexType) {
		
	}
	
	@Override
	public void configure(SimpleSchema schema, TopLevelElement topLevelElement) {
		
	}

	@Override
	public void configure(SimpleSchema schema, NamedAttributeGroup namedAttributeGroup) {

	}

	@Override
	public void configure(SimpleSchema schema, NamedGroup namedGroup) {

	}

}
