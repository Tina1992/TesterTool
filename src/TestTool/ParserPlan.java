package TestTool;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class ParserPlan {
	public static Hashtable<String, Boolean> atributos=new Hashtable<String, Boolean>();
	public static Vector<String> providers=new Vector<String>();
	public static Vector<String> metrics = new Vector<String>();
	public static Vector<String> images = new Vector<String>();
	public static int tries=1;
	
	public ParserPlan(){
		
	}
	
	public void decodePlan(File f){
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser;
		try {
			saxParser = factory.newSAXParser();
			PlanHandler planHandler = new PlanHandler();
			saxParser.parse(f, planHandler);
			/*System.out.print(atributos);
			System.out.print('\n');
			System.out.print(providers);
			System.out.print('\n');
			System.out.print(metrics);
			System.out.print('\n');
			System.out.print(images);
			System.out.print('\n');
			System.out.print(tries);
			System.out.print('\n');*/
		} catch (ParserConfigurationException | SAXException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
