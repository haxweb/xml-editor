package fr.haxweb.xmleditor.core.xsd.configurator;

public interface IConfigurableElement {
	
	public void accept(IConfigurableSchema schema, ISchemaConfigurator configurator);
	
}
