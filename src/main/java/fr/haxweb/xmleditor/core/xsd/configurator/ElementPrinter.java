package fr.haxweb.xmleditor.core.xsd.configurator;

import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;

import fr.haxweb.xmleditor.core.xsd.jaxb.Annotated;
import fr.haxweb.xmleditor.core.xsd.jaxb.Attribute;
import fr.haxweb.xmleditor.core.xsd.jaxb.AttributeGroup;
import fr.haxweb.xmleditor.core.xsd.jaxb.AttributeGroupRef;
import fr.haxweb.xmleditor.core.xsd.jaxb.ComplexType;
import fr.haxweb.xmleditor.core.xsd.jaxb.ExplicitGroup;
import fr.haxweb.xmleditor.core.xsd.jaxb.GroupRef;
import fr.haxweb.xmleditor.core.xsd.jaxb.List;
import fr.haxweb.xmleditor.core.xsd.jaxb.LocalElement;
import fr.haxweb.xmleditor.core.xsd.jaxb.LocalSimpleType;
import fr.haxweb.xmleditor.core.xsd.jaxb.NamedAttributeGroup;
import fr.haxweb.xmleditor.core.xsd.jaxb.NamedGroup;
import fr.haxweb.xmleditor.core.xsd.jaxb.Restriction;
import fr.haxweb.xmleditor.core.xsd.jaxb.SimpleType;
import fr.haxweb.xmleditor.core.xsd.jaxb.TopLevelComplexType;
import fr.haxweb.xmleditor.core.xsd.jaxb.TopLevelElement;
import fr.haxweb.xmleditor.core.xsd.jaxb.TopLevelSimpleType;
import fr.haxweb.xmleditor.core.xsd.simple.SimpleSchema;

public class ElementPrinter implements IElementPrinter {

	public static final Logger LOGGER = Logger.getLogger(ElementPrinter.class);
	
	@Override
	public void print(SimpleSchema schema, Object attribute) {
		try {
			this.getClass().getMethod("print", new Class[] { SimpleSchema.class, attribute.getClass() } ).invoke(this, new Object[] {schema, attribute});
		} catch (NoSuchMethodException e) {
			LOGGER.error("Cannot print this type of element : " + attribute.getClass().getName(), e);
			return;
		} catch (SecurityException e) {
			LOGGER.error("Error during lookup of the right Method to print the element of type : "  + attribute.getClass().getName(), e);
			return;
		} catch (Exception e) {
			LOGGER.error("Error during print of an element of type : "  + attribute.getClass().getName(), e);
		}
	}

	public void print(SimpleType simpleType) {
		_print(null, simpleType);
	}
	
	public void print(SimpleSchema schema, TopLevelSimpleType topLevelSimpleType) {
		_print(schema, topLevelSimpleType);
	}
	
	@Override
	public void print(SimpleSchema schema, LocalSimpleType localSimpleType) {
		_print(schema, localSimpleType);
	}
	
	protected void _print(SimpleSchema schema, SimpleType simpleType) {
		if (simpleType != null) {
			LOGGER.info("===========================");
			LOGGER.info("Type : " + simpleType.getClass().getSimpleName());
			LOGGER.info("Id : " + simpleType.getId() + ", Name : " + simpleType.getName());
			
			printList(simpleType.getList());
			
			printOtherAttributes(simpleType.getOtherAttributes());
			
			printRestriction(simpleType.getRestriction());
			
			LOGGER.info("===========================");
		}
	}

	@Override
	public void print(SimpleSchema schema, NamedAttributeGroup namedAttributeGroup) {
		_print(schema, namedAttributeGroup);
	}
	
	@Override
	public void print(SimpleSchema schema, AttributeGroup attributeGroup) {
		_print(schema, attributeGroup);
	}
	
	@Override
	public void print(SimpleSchema schema, AttributeGroupRef attributeGroupRef) {
		_print(schema, attributeGroupRef);
	}
	
	protected void _print(SimpleSchema schema, AttributeGroup attributeGroup) {
		LOGGER.info("Attribute group : " + (attributeGroup.getName() != null ? attributeGroup.getName() : attributeGroup.getRef()));
		for (Annotated annotated : attributeGroup.getAttributeOrAttributeGroup()) {
			if (annotated instanceof Attribute) {
				LOGGER.info("|== Attribute : " + ((Attribute) annotated).getName() != null ? ((Attribute) annotated).getName() : ((Attribute) annotated).getRef());
			} else if (annotated instanceof AttributeGroup) {
				print(schema, annotated);
			}
		}
	}
	
	@Override
	public void print(SimpleSchema schema, NamedGroup namedGroup) {
		LOGGER.info("Named Group : " + namedGroup.getName() != null ? namedGroup.getName() : namedGroup.getRef());
		
		if (namedGroup.getMinOccurs() != null) {
			LOGGER.info("Min occurs : " + namedGroup.getMinOccurs());
		}
		if (namedGroup.getMaxOccurs() != null) {
			LOGGER.info("Max occurs : " + namedGroup.getMaxOccurs());
		}
		
		_printParticles(schema, namedGroup.getParticle());
		
		printOtherAttributes(namedGroup.getOtherAttributes());
	}

	private void _printParticles(SimpleSchema schema, java.util.List<Object> particles) {
		if (particles != null) {
			for (Object particle : particles) {
				JAXBElement<?> particleElement = (JAXBElement<?>) particle;
				LOGGER.info("Particle : " + particleElement.getName() != null ? particleElement.getName() : "");
				print(schema, particleElement.getValue());
			}
		}
	}

	public void print(SimpleSchema schema, ExplicitGroup explicitGroup) {
		if (explicitGroup != null) {
			LOGGER.info("Explicit Group : " + explicitGroup.getName() != null ? explicitGroup.getName() : explicitGroup.getRef());
			_printParticles(schema, explicitGroup.getParticle());
		}
	}
	
	public void print(SimpleSchema schema, GroupRef groupRef) {
		if (groupRef != null) {
			LOGGER.info("Group Ref : " + groupRef.getName() != null ? groupRef.getName() : groupRef.getRef());
			_printParticles(schema, groupRef.getParticle());
		}
	}
	
	public void print(SimpleSchema schema, LocalElement localElement) {
		LOGGER.info("Local Element : " + (localElement.getName() != null ? localElement.getName() : localElement.getRef()) + " " 
				+ (localElement.getType() != null ? " Of type : " + localElement.getType() : ""));
		
		if (localElement.getMinOccurs() != null) {
			LOGGER.info("Min occurs : " + localElement.getMinOccurs());
		}
		if (localElement.getMaxOccurs() != null) {
			LOGGER.info("Max occurs : " + localElement.getMaxOccurs());
		}
		
		print(schema, localElement.getSimpleType());
		
		print(schema, localElement.getComplexType());
	}
	
	public void print(SimpleSchema schema, ComplexType complexType) {
		if (complexType != null) {
			LOGGER.info("Complex type " + complexType.getName() != null ? complexType.getName() : "");
			if (complexType.getSequence() != null) {
				_printParticles(schema, complexType.getSequence().getParticle());
			}
			
			print(schema, complexType.getChoice());
		}
		
	}
	
	@Override
	public void print(SimpleSchema schema, TopLevelComplexType topLevelComplexType) {
		LOGGER.info("Top Level ComplexType : " + topLevelComplexType.getName());
		print(schema, topLevelComplexType.getChoice());
		print(schema, topLevelComplexType.getSequence());
	}

	@Override
	public void print(SimpleSchema schema, TopLevelElement topLevelElement) {
		// TODO Auto-generated method stub

	}

	public void printRestriction(Restriction restriction) {
		if (restriction != null) {
			LOGGER.info("Restriction : " + (restriction.getId() != null ? restriction.getId() : ""));
			LOGGER.info("Base : " + restriction.getBase());
			
			printFacets(restriction.getFacets());
			
			print(restriction.getSimpleType());
		}
	}
	
	public void printFacets(java.util.List<Object> facets) {
		if (facets != null && facets.size() > 0) {
			LOGGER.info("Facets : ");
			IFacetPrinter facetPrinter = new FacetPrinter();
			for (Object facet : facets) {
				facetPrinter.printFacet(facet);
			}
		}
	}
	
	public static void printOtherAttributes(Map<QName, String> attributes) {
		if (attributes != null && attributes.size() > 0) {
			LOGGER.info("Other Attributes : ");
			for (QName attributeName : attributes.keySet()) {
				LOGGER.info(attributeName.getLocalPart() + " = " + attributes.get(attributeName));
			}
		}
	}
	
	public static void printList(List list) {
		if (list != null) {
			LOGGER.info("List of type : " + list.getItemType().getLocalPart());
			LOGGER.info("ID : " + list.getId());
		}
	}
	
}
