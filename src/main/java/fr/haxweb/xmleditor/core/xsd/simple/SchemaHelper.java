package fr.haxweb.xmleditor.core.xsd.simple;

import javax.xml.namespace.QName;

import fr.haxweb.xmleditor.core.xsd.jaxb.Element;

public class SchemaHelper {

	public static boolean isElementComplex(SimpleSchema schema, Element element) {
		if (element == null) {
			return false;
		}
		
		if (element.getComplexType() != null) {
			return true;
		}
		
		if (element.getSimpleType() != null) {
			return false;
		}
		
		QName ref = element.getRef();
		if (ref != null && schema != null) {
			return isElementComplex(schema, SchemaResolver.resolveElementReference(schema, ref));
		}
		
		return false;
	}
	
	public static boolean hasRef(SimpleSchema schema, Element refElementToFind) {
		for (Element topElement : schema.elements.values()) {
			if (topElement.getName().equals(refElementToFind.getName())) {
				continue;
			}
			if (SchemaReferenceFinder.hasRef(schema, refElementToFind, topElement)) {
				return true;
			}
		}
		return false;
	}
	

}
