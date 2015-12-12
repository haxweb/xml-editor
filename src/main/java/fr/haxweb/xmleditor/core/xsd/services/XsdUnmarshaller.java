package fr.haxweb.xmleditor.core.xsd.services;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import fr.haxweb.xmleditor.core.xsd.jaxb.Schema;

public class XsdUnmarshaller {

	private static JAXBContext context;
	
	private static Unmarshaller unmarshaller;
	
	private static Unmarshaller getUnmarshaller() {
		try {
			if (unmarshaller == null) {
				unmarshaller = getJaxbContext().createUnmarshaller();
			}
		} catch (Exception e) {
			System.out.println("Error during creation of Unmarshaller : ");
			e.printStackTrace();
		}
		return unmarshaller;
	}
	
	private static JAXBContext getJaxbContext() {
		try {
			if (context == null) {
				context = JAXBContext.newInstance("fr.haxweb.xmleditor.core.xsd.jaxb");				
			}
		} catch (Exception e) {
			System.out.println("Error getting JAXBContext creation : ");
			e.printStackTrace();
		}
		return context;
	}
	
	public static Schema unmarshall(File xsdFile) {
		try {
			return (Schema) getUnmarshaller().unmarshal(xsdFile);
		} catch (JAXBException e) {
			System.out.println("Error trying to unmarshal " + xsdFile);
			e.printStackTrace();
			return null;
		}
	}
	
}
