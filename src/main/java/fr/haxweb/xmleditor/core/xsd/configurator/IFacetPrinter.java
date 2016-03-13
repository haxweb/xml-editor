package fr.haxweb.xmleditor.core.xsd.configurator;

import javax.xml.bind.JAXBElement;

import fr.haxweb.xmleditor.core.xsd.jaxb.Facet;
import fr.haxweb.xmleditor.core.xsd.jaxb.Pattern;
import fr.haxweb.xmleditor.core.xsd.jaxb.TotalDigits;
import fr.haxweb.xmleditor.core.xsd.jaxb.WhiteSpace;

public interface IFacetPrinter {
	
	public void printFacet(Object facetObject);
	
	public void printFacet(JAXBElement<Facet> facet);
	
	public void printFacet(Pattern facet);
	
	public void printFacet(WhiteSpace facet);
	
	public void printFacet(TotalDigits facet);

}
