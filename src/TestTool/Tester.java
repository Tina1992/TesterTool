package TestTool;

import java.io.File;

import org.opencv.core.Core;

import com.mashape.unirest.http.exceptions.UnirestException;

import Servicies.FaceRectService;
import Servicies.SkyBiometryService;

public class Tester {

	private static ParserPlan parser;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		parser = new ParserPlan();
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		File f=new File("C:\\Users\\Martina\\Desktop\\FaceRecogPlanExample.xml");
		parser.decodePlan(f);
		System.out.print(ParserPlan.atributos);
		SkyBiometryService frs=new SkyBiometryService();
		File file=new File("Aaron_Eckhart_0001.jpg");
		try {
			frs.parse(frs.post(file, parser.atributos), parser.atributos);
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
