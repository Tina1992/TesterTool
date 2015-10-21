package Servicies;

import java.io.File;
import java.util.Hashtable;

import org.opencv.core.MatOfRect;
import org.opencv.objdetect.CascadeClassifier;

public class OpenCVService extends AbsService{

	@Override
	public ImageProc getFaceRecognition(File f, Hashtable<String, Boolean> opts) {
		// TODO Auto-generated method stub
		/*CascadeClassifier faceDetector=new CascadeClassifier("lbpcascade_frontalface.xml");
		MatOfRect faceDetections = new MatOfRect();
		imageProc=new ImageProc(f.getAbsolutePath());
		faceDetector.detectMultiScale(image_captured, faceDetections);
		System.out.println(String.format("Detected %s faces",
				faceDetections.toArray().length));*/
		return null;
	}

}
