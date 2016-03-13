package fr.haxweb.xmleditor.core.xsd.configurator;

import fr.haxweb.xmleditor.core.xsd.jaxb.AttributeGroup;
import fr.haxweb.xmleditor.core.xsd.jaxb.AttributeGroupRef;
import fr.haxweb.xmleditor.core.xsd.jaxb.LocalSimpleType;
import fr.haxweb.xmleditor.core.xsd.jaxb.NamedAttributeGroup;
import fr.haxweb.xmleditor.core.xsd.jaxb.NamedGroup;
import fr.haxweb.xmleditor.core.xsd.jaxb.TopLevelComplexType;
import fr.haxweb.xmleditor.core.xsd.jaxb.TopLevelElement;
import fr.haxweb.xmleditor.core.xsd.jaxb.TopLevelSimpleType;
import fr.haxweb.xmleditor.core.xsd.simple.SimpleSchema;

public interface IElementPrinter {

	
	public void print(SimpleSchema schema, Object attribute);
	
	public void print(SimpleSchema schema, TopLevelSimpleType topLevelSimpleType);
	
	public void print(SimpleSchema schema, LocalSimpleType topLevelSimpleType);
	
	public void print(SimpleSchema schema, NamedGroup namedGroup);
	
	public void print(SimpleSchema schema, NamedAttributeGroup namedAttributeGroup);
	
	public void print(SimpleSchema schema, TopLevelComplexType topLevelComplexType);
	
	public void print(SimpleSchema schema, TopLevelElement topLevelElement);

	public void print(SimpleSchema schema, AttributeGroup attributeGroup);

	public void print(SimpleSchema schema, AttributeGroupRef attributeGroupRef);

	
}
