package TestTool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;

import Metrics.AbsMetric;
import Servicies.AbsService;
import Servicies.ImageProc;

public class Tester {

	public static void test(String planName) {
		// TODO Auto-generated method stub
		File f = new File(planName);
		ParserPlan.decodePlan(f);
		String title = new String();
		PrintWriter writer;
		String workingDir = System.getProperty("user.dir");
		for (AbsMetric m : ParserPlan.metricsMet) {
			title += ", "+m.getName() + " (in " + m.getMedida() + "s)";
		}
		try {
			writer = new PrintWriter(workingDir + "\\result.csv", "UTF-8");
			writer.println("Execution number, image name, Provider name"
					+ title);
			System.out.println(ParserPlan.atributos);
			System.out.println(ParserPlan.providers);
			System.out.println(ParserPlan.metrics);
			System.out.println(ParserPlan.imagesDir);
			for (int i = 0; i < ParserPlan.tries; i++) {
				for (AbsService s : ParserPlan.providersServices) {
					for (String sD : ParserPlan.imagesDir) {
						File fD = new File(sD);
						File[] fArray = null;
						if (s.getLimRequest()==-1){
							fArray=fD.listFiles();
						}
						else{
							File[] aux=fD.listFiles();
							fArray=new File[s.getLimRequest()];
							for (int j=0; j<s.getLimRequest(); j++){
								fArray[j]=aux[j];
							}
						}
						for (File fI : fArray) {
							if (fI.getAbsolutePath().contains(".jpg")) {
								ImageProc im = s.getFaceRecognition(fI,
										ParserPlan.atributos);
								String line = new String();
								Path p=Paths.get(im.getFile_path());
								String name= p.getFileName().toString();
								line = i+1 + "º, "
										+ name + ", "
										+ s.getName();
								for (AbsMetric m : ParserPlan.metricsMet) {
									try {
										float dato=m.getDato(s, im);
										if (dato>=0){
										line+=", "+m.getDato(s, im);}
										else
											line+=", error";
										//System.out.println(m.getDato(s, im));
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								System.out.println(fI.getAbsolutePath());
								writer.println(line);
							}
						}
					}
				}
			}
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
