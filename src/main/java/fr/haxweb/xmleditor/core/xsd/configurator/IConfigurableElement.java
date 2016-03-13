package fr.haxweb.xmleditor.core.xsd.configurator;

import fr.haxweb.xmleditor.core.xsd.simple.SimpleSchema;

public interface IConfigurableElement {
	
	public void accept(SimpleSchema schema, ISchemaConfigurator configurator);
	
}
