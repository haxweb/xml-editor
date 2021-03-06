//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.12.12 at 12:46:16 PM CET 
//


package fr.haxweb.xmleditor.core.xsd.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import fr.haxweb.xmleditor.core.xsd.configurator.IConfigurableElement;
import fr.haxweb.xmleditor.core.xsd.configurator.IElementPrinter;
import fr.haxweb.xmleditor.core.xsd.configurator.IPrintableElement;
import fr.haxweb.xmleditor.core.xsd.configurator.ISchemaConfigurator;
import fr.haxweb.xmleditor.core.xsd.simple.SimpleSchema;


/**
 * <p>Java class for namedGroup complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="namedGroup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}realGroup">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.w3.org/2001/XMLSchema}annotation" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="all">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}all">
 *                   &lt;group ref="{http://www.w3.org/2001/XMLSchema}allModel"/>
 *                   &lt;anyAttribute processContents='lax' namespace='##other'/>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="choice" type="{http://www.w3.org/2001/XMLSchema}simpleExplicitGroup"/>
 *           &lt;element name="sequence" type="{http://www.w3.org/2001/XMLSchema}simpleExplicitGroup"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "namedGroup")
public class NamedGroup
    extends RealGroup implements IConfigurableElement, IPrintableElement
{

	@Override
	public void accept(SimpleSchema schema, ISchemaConfigurator configurator) {
		configurator.configure(schema, this);
	}

	@Override
	public void accept(SimpleSchema simpleSchema, IElementPrinter printer) {
		printer.print(simpleSchema, this);
	}
}
