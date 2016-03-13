package fr.haxweb.xmleditor.core.xsd.configurator;

import javax.xml.bind.JAXBElement;

import org.apache.log4j.Logger;

import fr.haxweb.xmleditor.core.xsd.jaxb.Facet;
import fr.haxweb.xmleditor.core.xsd.jaxb.Pattern;
import fr.haxweb.xmleditor.core.xsd.jaxb.TotalDigits;
import fr.haxweb.xmleditor.core.xsd.jaxb.WhiteSpace;

public class FacetPrinter implements IFacetPrinter {

	public static final Logger LOGGER = Logger.getLogger(FacetPrinter.class);
	
	public void printFacet(Object object) {
		if (object != null) {
			try {
				this.getClass().getMethod("printFacet", new Class[] { object.getClass() } ).invoke(this, new Object[] { object });
			} catch (NoSuchMethodException e) {
				LOGGER.error("Cannot print this type of Facet : " + object.getClass().getName(), e);
				return;
			} catch (SecurityException e) {
				LOGGER.error("Error during lookup of the right Method to print the facet of type : "  + object.getClass().getName(), e);
				return;
			} catch (Exception e) {
				LOGGER.error("Error during print of an element of type : "  + object.getClass().getName(), e);
			}
		}
	}
	
	@Override
	public void printFacet(JAXBElement<Facet> facet) {
		LOGGER.info("Facet : " + facet.getName() + " = " + facet.getValue().getValue());
	}
	
	@Override
	public void printFacet(Pattern facet) {
		LOGGER.info("Facet Pattern : " + facet.getValue());
	}

	@Override
	public void printFacet(WhiteSpace facet) {
		LOGGER.info("Facet whitespace " + facet.getValue());
	}

	@Override
	public void printFacet(TotalDigits facet) {
		LOGGER.info("Facet TotalDigits " + facet.getValue());
	}
	
}