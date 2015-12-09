package Servicies;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Hashtable;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;
import org.xml.sax.InputSource;
import static java.nio.file.StandardCopyOption.*;

public class OpenCVService extends AbsService {

	public OpenCVService() {
		addAvailableAtt("Eyes");
		addAvailableAtt("Mouth");
		addAvailableAtt("Nose");
		addAvailableAtt("Glasses");
		addAvailableAtt("FaceOrientation");
	}

	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	private CascadeClassifier getClassifier(String resource)
			throws IOException {
		
		String tempDir="tmp"; //TODO: use a sensible default
		Path tmp;
		tmp = Files.createTempFile(tempDir, "cv");
		Files.copy(OpenCVService.class.getResourceAsStream("/classificators/"+resource), tmp, REPLACE_EXISTING);
		return new CascadeClassifier(tmp.toString());
		
	}

	@Override
	public ImageProc getFaceRecognition(File f, Hashtable<String, Boolean> opts) {
		// TODO Auto-generated method stub
		startTime = System.nanoTime();
		CascadeClassifier faceDetector;
		try {
			faceDetector = getClassifier("lbpcascade_frontalface.xml");
			MatOfRect faceDetections = new MatOfRect();
			imageProc = new ImageProc(f.getAbsolutePath());
			Mat image = Imgcodecs.imread(f.getAbsolutePath());
			faceDetector.detectMultiScale(image, faceDetections);
			for (Rect face : faceDetections.toArray()) {
				imageProc.addFace(RectToRectangle(face));
				if (opts.get("FaceOrientation")) {
					imageProc.addOrientation("frontal");
				}
				processOptions(image, face, opts);
			}
			CascadeClassifier faceDetector2 = getClassifier("lbpcascade_profileface.xml");
			MatOfRect faceDetections2 = new MatOfRect();
			faceDetector2.detectMultiScale(image, faceDetections2);
			for (Rect face : faceDetections2.toArray()) {
				imageProc.addFace(RectToRectangle(face));
				if (opts.get("FaceOrientation")) {
					imageProc.addOrientation("profile");
				}
				processOptions(image, face, opts);
			}
			endTime = System.nanoTime();
			return imageProc;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageProc;

	}

	private void processOptions(Mat image, Rect submat,
			Hashtable<String, Boolean> opts) {
		for (String o : opts.keySet()) {
			if (opts.get(o) == true) {
				switch (o) {
				case "Mouth": {
					CascadeClassifier faceDetector2;
					try {
						faceDetector2 = getClassifier("Mouth.xml");

						MatOfRect mouthDetection = new MatOfRect();
						Rect r = new Rect();
						r.x = submat.x;
						r.height = submat.height / 2;
						r.y = submat.y + submat.height / 2;
						r.width = submat.width;
						faceDetector2.detectMultiScale(image.submat(r),
								mouthDetection);
						for (Rect mouth : mouthDetection.toArray()) {
							mouth.x = r.x + mouth.x;
							mouth.y = r.y + mouth.y;
							imageProc.addMouthRect(RectToRectangle(mouth));
						}
						break;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						break;
					}
				}
				case "Eyes": {
					CascadeClassifier faceDetector2;
					try {
						faceDetector2 = getClassifier("haarcascade_eye.xml");
						MatOfRect eyesDetection = new MatOfRect();
						faceDetector2.detectMultiScale(image.submat(submat),
								eyesDetection);
						for (Rect eye : eyesDetection.toArray()) {
							eye.x = submat.x + eye.x;
							eye.y = submat.y + eye.y;
							imageProc.addEyeRect(RectToRectangle(eye));
						}
						break;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						break;
					}

				}
				case "Nose": {
					CascadeClassifier faceDetector2;
					try {
						faceDetector2 = getClassifier("Nariz.xml");
						MatOfRect noseDetection = new MatOfRect();
						faceDetector2.detectMultiScale(image.submat(submat),
								noseDetection);
						for (Rect nose : noseDetection.toArray()) {
							nose.x = submat.x + nose.x;
							nose.y = submat.y + nose.y;
							imageProc.addNoseRect(RectToRectangle(nose));
						}
						break;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						break;
					}
				}
				case "Glasses": {
					CascadeClassifier faceDetector2;
					try {
						faceDetector2 = getClassifier("haarcascade_eye_tree_eyeglasses.xml");

						MatOfRect noseDetection = new MatOfRect();
						faceDetector2.detectMultiScale(image.submat(submat),
								noseDetection);
						if (noseDetection.toArray().length != 0) {
							imageProc.addGlasses(true);
						} else
							imageProc.addGlasses(false);
						break;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						break;
					}
				}
				case "Smile": {
					Rect r = new Rect();
					r.x = submat.x;
					r.height = submat.height / 2;
					r.y = submat.y + submat.height / 2;
					r.width = submat.width;
					boolean smile = false;
					int i = 1;
					try {
						while ((!smile) && (i <= 5)) {
							CascadeClassifier faceDetector2;
							faceDetector2 = getClassifier("smiled_0" + i
									+ ".xml");
							MatOfRect mouthDetection = new MatOfRect();
							faceDetector2.detectMultiScale(image.submat(r),
									mouthDetection);
							if (mouthDetection.toArray().length != 0) {
								imageProc.addSmile(true);
								System.out.println("SMiiile :D");
								smile = true;
							}
							if (i < 5)
								i++;
							else {
								imageProc.addSmile(false);
								smile = true;
							}
						}
						break;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						break;
					}

				}
				}
			}
		}
	}

	private Rectangle RectToRectangle(Rect r) {
		Rectangle ret = new Rectangle();
		ret.x = r.x;
		ret.y = r.y;
		ret.height = r.height;
		ret.width = r.width;
		return ret;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "OpenCV";
	}

}
