package fr.haxweb.xmleditor.core.xsd.configurator;

import java.util.HashMap;

import org.apache.log4j.Logger;

import fr.haxweb.xmleditor.core.xsd.jaxb.AttributeGroup;
import fr.haxweb.xmleditor.core.xsd.jaxb.Element;
import fr.haxweb.xmleditor.core.xsd.jaxb.Group;
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
		if (schema.simpleTypes == null) {
			schema.simpleTypes = new HashMap<String, TopLevelSimpleType>();
		}
		schema.simpleTypes.put(topLevelSimpleType.getName(), topLevelSimpleType);
	}
	
	@Override
	public void configure(SimpleSchema schema, TopLevelComplexType topLevelComplexType) {
		if (schema.complexTypes == null) {
			schema.complexTypes = new HashMap<String, TopLevelComplexType>();
		}
		schema.complexTypes.put(topLevelComplexType.getName(), topLevelComplexType);
	}
	
	@Override
	public void configure(SimpleSchema schema, TopLevelElement topLevelElement) {
		if (schema.elements == null) {
			schema.elements = new HashMap<String, Element>();
		}
		schema.elements.put(topLevelElement.getName(), topLevelElement);
	}

	@Override
	public void configure(SimpleSchema schema, NamedAttributeGroup namedAttributeGroup) {
		if (schema.attributeGroups == null) {
			schema.attributeGroups = new HashMap<String, AttributeGroup>();
		}
		schema.attributeGroups.put(namedAttributeGroup.getName(), namedAttributeGroup);
	}

	@Override
	public void configure(SimpleSchema schema, NamedGroup namedGroup) {
		if (schema.groups == null) {
			schema.groups = new HashMap<String, Group>();
		}
		schema.groups.put(namedGroup.getName(), namedGroup);
	}

}
