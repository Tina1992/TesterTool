package TestTool;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import Metrics.AbsMetric;
import Servicies.AbsService;

public class ParserPlan {
	public static Hashtable<String, Boolean> atributos = new Hashtable<String, Boolean>();
	public static Vector<String> providers = new Vector<String>();
	public static Vector<String> metrics = new Vector<String>();
	public static Vector<String> imagesDir = new Vector<String>();
	public static int tries = 1;

	public static Vector<AbsService> providersServices = new Vector<AbsService>();
	public static Vector<AbsMetric> metricsMet = new Vector<AbsMetric>();

	public ParserPlan() {

	}

	public static void decodePlan(File f) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser;
		try {
			emptyVectors();
			saxParser = factory.newSAXParser();
			PlanHandler planHandler = new PlanHandler();
			saxParser.parse(f, planHandler);
			decodeServices();
			decodeMetrics();
		} catch (ParserConfigurationException | SAXException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private static void emptyVectors() {
		if (!atributos.isEmpty()){
			atributos=new Hashtable<String, Boolean>();
		}
		if (!providers.isEmpty()){
			providers=new Vector<String>();
		}
		if (!metrics.isEmpty()){
			metrics=new Vector<String>();
		}
		if (!imagesDir.isEmpty()){
			imagesDir=new Vector<String>();
		}
		
	}

	private static void decodeServices() {
		providersServices = new Vector<AbsService>();
		for (String s : providers) {
			try {
				if (!(s.contains("GooglePlay"))) {
					Object instanceOfMyClass = Class.forName("Servicies." + s + "Service")
							.newInstance();

					providersServices.add((AbsService) instanceOfMyClass);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.err.println("Clase no implementada. No será considerada en el testing.");
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	private static void decodeMetrics() {
		metricsMet = new Vector<AbsMetric>();
		for (String s : metrics) {
			try {
					Object instanceOfMyClass = Class.forName("Metrics." + s + "Metric")
							.newInstance();

					metricsMet.add((AbsMetric) instanceOfMyClass);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
