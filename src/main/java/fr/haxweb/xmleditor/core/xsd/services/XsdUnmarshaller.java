package fr.haxweb.xmleditor.core.xsd.services;

import java.io.File;
import java.io.FileFilter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import fr.haxweb.xmleditor.core.xsd.jaxb.Schema;
import fr.haxweb.xmleditor.core.xsd.simple.SimpleSchema;

public class XsdUnmarshaller {

	private static JAXBContext context;
	
	private static Unmarshaller unmarshaller;
	
	private static JAXBIntrospector introspector;
	
	public static final String XSD_EXTENSION = ".xsd";
	
	public static final String XSD_PATH_PROP = "xsd.path";
	
	private static Properties config;
	
	public static final Logger LOGGER = Logger.getLogger(XsdUnmarshaller.class);
	
	static {
		try {
			InputStream stream = XsdUnmarshaller.class.getResourceAsStream("/xml-editor.properties");
			config = new Properties();
			config.load(stream);
		} catch (Exception e) {
			LOGGER.error("Error trying to load properties file", e);
		}
	}
	
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
	
	public static Object getElement(JAXBElement<?> element) {
		if (introspector == null) {
			introspector = getJaxbContext().createJAXBIntrospector();
		}
		return introspector.getValue(element);
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
	
	private static SimpleSchema unmarshall(File xsdFile) {
		try {
			return SimpleSchema.Builder.fromJaxbSchema((Schema)getUnmarshaller().unmarshal(xsdFile));
		} catch (JAXBException e) {
			System.out.println("Error trying to unmarshal " + xsdFile);
			e.printStackTrace();
			return null;
		}
	}
	
	public static SimpleSchema unmarshall(String xsdName) {
		return unmarshall(getXsdFile(xsdName));
	}
	
	public static List<String> listXsds() {
		List<String> xsdList = new ArrayList<String>();
		File xsdPath = new File(config.getProperty(XSD_PATH_PROP));
		
		for (File xsdFile : xsdPath.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.getName().endsWith(XSD_EXTENSION);
			}
		})) {
			xsdList.add(xsdFile.getName().substring(0, xsdFile.getName().indexOf(XSD_EXTENSION)));
		}
		
		return xsdList;
	}
	
	public static File getXsdFile(String xsdName) {
		try {
			return new File(config.getProperty(XSD_PATH_PROP) + xsdName + XSD_EXTENSION);
		} catch (Exception e) {
			LOGGER.error("Error getting file instance : " + e.getStackTrace());
			return null;
		}
	}
}
