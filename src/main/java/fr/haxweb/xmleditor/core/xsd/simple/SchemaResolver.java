package fr.haxweb.xmleditor.core.xsd.simple;

import java.util.Map;

import javax.xml.namespace.QName;

import fr.haxweb.xmleditor.core.xsd.jaxb.AttributeGroup;
import fr.haxweb.xmleditor.core.xsd.jaxb.ComplexType;
import fr.haxweb.xmleditor.core.xsd.jaxb.Element;
import fr.haxweb.xmleditor.core.xsd.jaxb.Group;
import fr.haxweb.xmleditor.core.xsd.jaxb.SimpleType;

public class SchemaResolver {
	
	private static Object mapLookup(QName ref, Map<String, ?> map) {
		if (map == null) {
			return null;
		}
		
		String localPartRef = ref.getLocalPart();
		if (map.containsKey(localPartRef)) {
			return map.get(localPartRef);
		}
		return null;
	}
	
	public static Element resolveElementReference(SimpleSchema schema, QName ref) {
		return (Element) mapLookup(ref, schema.elements);
	}
	
	public static ComplexType resolveComplexTypeReference(SimpleSchema schema, QName ref) {
		return (ComplexType) mapLookup(ref, schema.complexTypes);
	}
	
	public static SimpleType resolveSimpleTypeReference(SimpleSchema schema, QName ref) {
		return (SimpleType) mapLookup(ref, schema.simpleTypes);
	}
	
	public static Group resolveGroupReference(SimpleSchema schema, QName ref) {
		return (Group) mapLookup(ref, schema.groups);
	}
	
	public static AttributeGroup resolveAttributeGroupReference(SimpleSchema schema, QName ref) {
		return (AttributeGroup) mapLookup(ref, schema.attributeGroups);
	}
}
