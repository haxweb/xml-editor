package fr.haxweb.xmleditor.core.xsd.configurator;

import fr.haxweb.xmleditor.core.xsd.jaxb.NamedAttributeGroup;
import fr.haxweb.xmleditor.core.xsd.jaxb.NamedGroup;
import fr.haxweb.xmleditor.core.xsd.jaxb.OpenAttrs;
import fr.haxweb.xmleditor.core.xsd.jaxb.TopLevelComplexType;
import fr.haxweb.xmleditor.core.xsd.jaxb.TopLevelElement;
import fr.haxweb.xmleditor.core.xsd.jaxb.TopLevelSimpleType;
import fr.haxweb.xmleditor.core.xsd.simple.SimpleSchema;

public interface ISchemaConfigurator {
	
	public void configure(SimpleSchema schema, OpenAttrs attribute);
	
	public void configure(SimpleSchema schema, TopLevelSimpleType topLevelSimpleType);
	
	public void configure(SimpleSchema schema, NamedAttributeGroup namedAttributeGroup);
	
	public void configure(SimpleSchema schema, NamedGroup namedGroup);
	
	public void configure(SimpleSchema schema, TopLevelComplexType topLevelComplexType);
	
	public void configure(SimpleSchema schema, TopLevelElement topLevelElement);
	
}
