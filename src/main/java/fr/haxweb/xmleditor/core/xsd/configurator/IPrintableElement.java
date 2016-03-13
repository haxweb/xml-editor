package fr.haxweb.xmleditor.core.xsd.configurator;

import fr.haxweb.xmleditor.core.xsd.simple.SimpleSchema;

public interface IPrintableElement {

	public void accept(SimpleSchema simpleSchema, IElementPrinter printer);
	
}
