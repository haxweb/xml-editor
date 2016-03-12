package fr.haxweb.xmleditor.core.xsd.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.type.SimpleType;

import fr.haxweb.xmleditor.core.xsd.configurator.IConfigurableSchema;
import fr.haxweb.xmleditor.core.xsd.configurator.ISchemaConfigurator;
import fr.haxweb.xmleditor.core.xsd.configurator.SchemaConfigurator;
import fr.haxweb.xmleditor.core.xsd.jaxb.NamedAttributeGroup;
import fr.haxweb.xmleditor.core.xsd.jaxb.NamedGroup;
import fr.haxweb.xmleditor.core.xsd.jaxb.OpenAttrs;
import fr.haxweb.xmleditor.core.xsd.jaxb.Schema;
import fr.haxweb.xmleditor.core.xsd.jaxb.TopLevelSimpleType;

public class SimpleSchema implements IConfigurableSchema {

	public Map<String, SElement> 		elements;
	
	public Map<String, TopLevelSimpleType> 	simpleTypes;
	
	public Map<String, SComplexType> 	complexTypes;
	
	public Map<String, SAttributeGroup>	attributeGroups; 
	
	public Map<String, SGroup> 			groups;
	
	public List<SAttribute> 			attributes;
	
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
			for (OpenAttrs element : this.jaxbSchema.getSimpleTypeOrComplexTypeOrGroup()) {
				configurator.configure(this.schema, element);
			}
		}

		public void setAttributes() {
			this.schema.attributes = new ArrayList<SAttribute>();
			for (QName attribute : this.jaxbSchema.getOtherAttributes().keySet()) {
//				this.schema.attributes.add(SAttribute.builder.build(attribute));
			}
		}
		
	}

}
