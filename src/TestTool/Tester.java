package TestTool;

import java.io.File;

public class Tester {

	private static ParserPlan parser;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		parser = new ParserPlan();
		File f=new File("C:\\Users\\Martina\\Desktop\\FaceRecogPlanExample.xml");
		parser.decodePlan(f);
	}

}
