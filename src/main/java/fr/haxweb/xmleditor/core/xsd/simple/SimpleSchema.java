package fr.haxweb.xmleditor.core.xsd.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.namespace.QName;

import org.apache.log4j.Logger;

import fr.haxweb.xmleditor.core.xsd.configurator.ElementPrinter;
import fr.haxweb.xmleditor.core.xsd.configurator.IConfigurableSchema;
import fr.haxweb.xmleditor.core.xsd.configurator.IElementPrinter;
import fr.haxweb.xmleditor.core.xsd.configurator.ISchemaConfigurator;
import fr.haxweb.xmleditor.core.xsd.configurator.SchemaConfigurator;
import fr.haxweb.xmleditor.core.xsd.jaxb.AttributeGroup;
import fr.haxweb.xmleditor.core.xsd.jaxb.Element;
import fr.haxweb.xmleditor.core.xsd.jaxb.Group;
import fr.haxweb.xmleditor.core.xsd.jaxb.OpenAttrs;
import fr.haxweb.xmleditor.core.xsd.jaxb.Schema;
import fr.haxweb.xmleditor.core.xsd.jaxb.TopLevelComplexType;
import fr.haxweb.xmleditor.core.xsd.jaxb.TopLevelSimpleType;

public class SimpleSchema implements IConfigurableSchema {

	public Map<String, Element> 			elements;
	
	public Map<String, TopLevelSimpleType> 	simpleTypes;
	
	public Map<String, TopLevelComplexType> complexTypes;
	
	public Map<String, AttributeGroup>		attributeGroups; 
	
	public Map<String, Group> 				groups;
	
	public List<QName> 						attributes;
	
	public boolean referencesResolved = false;
	
	public static class Builder {
		
		SimpleSchema schema;
		
		Schema jaxbSchema;
		
		Logger LOGGER = Logger.getLogger(SimpleSchema.Builder.class);
		
		public static SimpleSchema fromJaxbSchema(Schema jaxbSchema) {
			Builder builder = new Builder();
			return builder.buildFromJaxbSchema(jaxbSchema);
		}
		
		private SimpleSchema buildFromJaxbSchema(Schema jaxbSchema) {
			this.schema = new SimpleSchema();
			this.jaxbSchema = jaxbSchema;
			setAttributes();
			setElements();
			
			return this.schema;
		}
		
		private void setElements() {
			ISchemaConfigurator configurator = new SchemaConfigurator();
			IElementPrinter printer = new ElementPrinter();
			for (OpenAttrs element : this.jaxbSchema.getSimpleTypeOrComplexTypeOrGroup()) {
				configurator.configure(this.schema, element);
//				printer.print(this.schema, element);
			}
		}

		public void setAttributes() {
			this.schema.attributes = new ArrayList<QName>();
			for (QName attribute : this.jaxbSchema.getOtherAttributes().keySet()) {
				this.schema.attributes.add(attribute);
			}
		}
		
	}

	public Element resolveRoot() {
		for (Element topElement : this.elements.values()) {
			if (!SchemaHelper.hasRef(this, topElement)) {
				return topElement;
			}
		}
		return null;
	}
	
}
