package fr.haxweb.xmleditor.core.xsd.simple;

import javax.xml.bind.JAXBElement;

import org.apache.log4j.Logger;

import fr.haxweb.xmleditor.core.xsd.jaxb.ComplexContent;
import fr.haxweb.xmleditor.core.xsd.jaxb.ComplexType;
import fr.haxweb.xmleditor.core.xsd.jaxb.Element;
import fr.haxweb.xmleditor.core.xsd.jaxb.ExplicitGroup;
import fr.haxweb.xmleditor.core.xsd.jaxb.ExtensionType;
import fr.haxweb.xmleditor.core.xsd.jaxb.Group;
import fr.haxweb.xmleditor.core.xsd.jaxb.GroupRef;
import fr.haxweb.xmleditor.core.xsd.jaxb.LocalElement;
import fr.haxweb.xmleditor.core.xsd.jaxb.Restriction;
import fr.haxweb.xmleditor.core.xsd.jaxb.RestrictionType;

public class SchemaReferenceFinder {

	public static Logger LOGGER = Logger.getLogger(SchemaReferenceFinder.class);
	
	/**
	 * Generic method to find the appropriate method to lookup in given object
	 * @param schema
	 * @param elementToFind
	 * @param objectLookuped
	 * @return boolean whether or not there is a reference found in the given object
	 */
	public static boolean hasRef(SimpleSchema schema, Element elementToFind, Object objectLookuped) {
		try {
			return (boolean) SchemaReferenceFinder.class.getMethod("hasRef", new Class[] { SimpleSchema.class, Element.class, objectLookuped.getClass() } )
					.invoke(SchemaReferenceFinder.class, new Object[] {schema, elementToFind, objectLookuped});
		} catch (NoSuchMethodException e) {
			LOGGER.error("Cannot find reference on this type of element : " + objectLookuped.getClass().getName(), e);
			return false;
		} catch (SecurityException e) {
			LOGGER.error("Error during lookup of the right Method to lookup reference on the element of type : "  + objectLookuped.getClass().getName(), e);
			return false;
		} catch (Exception e) {
			LOGGER.error("Error during lookup reference on an element of type : "  + objectLookuped.getClass().getName(), e);
			return false;
		}
	}
	
	public static boolean hasRef(SimpleSchema schema, Element elementToFind, LocalElement localElementLookuped) {
		return hasRef(schema, elementToFind, (Element) localElementLookuped);
	}
	
	public static boolean hasRef(SimpleSchema schema, Element elementToFind, Element elementLookuped) {
		if (elementToFind == null || elementLookuped == null) {
			return false;
		}
		
		if ((elementLookuped.getName() != null && elementToFind.getName().equals(elementLookuped.getName()))
		|| (elementLookuped.getRef() != null && elementToFind.getName().equals(elementLookuped.getRef().getLocalPart()))) {
			return true;
		}
		
		if (SchemaHelper.isElementComplex(schema, elementLookuped)) {
			ComplexType complexType = null;
			if (elementLookuped.getComplexType() != null) {
				complexType = elementLookuped.getComplexType();
			} else {
				complexType = SchemaResolver.resolveComplexTypeReference(schema, elementLookuped.getRef());
			}
			
			if (complexType != null) {
				if (complexType.getChoice() != null) {
					return hasRef(schema, elementToFind, complexType.getChoice());
				}
				if (complexType.getSequence() != null) {
					return hasRef(schema, elementToFind, complexType.getSequence());
				}
				if (complexType.getComplexContent() != null) {
					return hasRef(schema, elementToFind, complexType.getComplexContent());
				}
			}
		}
		
		return false;
	}

	public static boolean hasRef(SimpleSchema schema, Element elementToFind, ExtensionType extensionLookuped) {
		if (extensionLookuped == null) {
			return false;
		}
		
		if (extensionLookuped.getBase().equals(extensionLookuped)) {
			return true;
		}
		
		if (extensionLookuped.getChoice() != null) {
			return hasRef(schema, elementToFind, extensionLookuped.getChoice());
		}
		if (extensionLookuped.getGroup() != null) {
			return hasRef(schema, elementToFind, extensionLookuped.getGroup());
		}
		if (extensionLookuped.getSequence() != null) {
			return hasRef(schema, elementToFind, extensionLookuped.getSequence());
		}
		
		return false;
	}
	
	public static boolean hasRef(SimpleSchema schema, Element elementToFind, RestrictionType restrictionLookuped) {
		if (restrictionLookuped == null) {
			return false;
		}
		
		if (restrictionLookuped.getBase().equals(elementToFind.getName())) {
			return true;
		}

		if (restrictionLookuped.getChoice() != null) {
			return hasRef(schema, elementToFind, restrictionLookuped.getChoice());
		}
		if (restrictionLookuped.getGroup() != null) {
			return hasRef(schema, elementToFind, restrictionLookuped.getGroup());
		}
		if (restrictionLookuped.getSequence() != null) {
			return hasRef(schema, elementToFind, restrictionLookuped.getSequence());
		}
		
		return false;
	}
	
	public static boolean hasRef(SimpleSchema schema, Element elementToFind, Restriction restrictionLookuped) {
		if (restrictionLookuped == null) {
			return false;
		}
		return false;
	}
	
	public static boolean hasRef(SimpleSchema schema, Element elementToFind, ComplexContent complexLookupded) {
		if (complexLookupded == null) {
			return false;
		}
		
		if (complexLookupded.getRestriction() != null) {
			if (complexLookupded.getRestriction().getBase().equals(elementToFind.getName())) {
				return true;
			}
			
		} else if (complexLookupded.getExtension() != null) { 
			if (complexLookupded.getExtension().getBase().equals(elementToFind.getName())) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean hasRef(SimpleSchema schema, Element elementToFind, GroupRef groupLookuped) {
		return hasRef(schema, elementToFind, (Group)groupLookuped);
	}
	
	public static boolean hasRef(SimpleSchema schema, Element elementToFind, ExplicitGroup groupLookupded) {
		return hasRef(schema, elementToFind, (Group)groupLookupded);
	}
	
	public static boolean hasRef(SimpleSchema schema, Element elementToFind, Group groupLookupded) {
		if (groupLookupded == null) {
			return false;
		}
		
		if (groupLookupded.getRef() != null) {
			groupLookupded = SchemaResolver.resolveGroupReference(schema, groupLookupded.getRef());
		}
		
		if (groupLookupded != null && groupLookupded.getParticle() != null) {
			for (Object particle : groupLookupded.getParticle()) {
				if (hasRef(schema, elementToFind, ((JAXBElement<?>)particle).getValue())) {
					return true;
				}
			}
		}
		
		return false;
	}
	
}
