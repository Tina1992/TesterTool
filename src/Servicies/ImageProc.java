package Servicies;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.json.JSONException;
import org.json.JSONObject;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImageProc {
	private Mat image;
	private BufferedImage imProc;
	
	private Vector<JSONObject> caras;
	
	private String Gender=null;
	private Boolean Glasses=null;
	private Boolean SunGlasses=null;
	private Boolean Smile=null;
	private Vector<Point> points;

	public ImageProc(String path_image) {
		image = Imgcodecs.imread(path_image);
		caras = new Vector<JSONObject>();
		points = new Vector<Point>();
	}

	public void drawRectangles(JSONObject obj) throws JSONException {
		caras.add(obj);
		double x, y, height, width;
		try {
			x = (double) obj.get("x");
			y = (double) obj.get("y");
			height = (double) obj.get("height");
			width = (double) obj.get("width");
		} catch (ClassCastException e) {
			x = (int) obj.get("x");
			y = (int) obj.get("y");
			height = (int) obj.get("height");
			width = (int) obj.get("width");
		}

		Imgproc.rectangle(image, new Point(x, y), new Point(x + width, y
				+ height), new Scalar(0, 255, 0), 2); // EL 2 FINAL ES EL GROSOR
														// DEL RECTANGULO
	}

	public void drawPoint(double eyLX, double eyLY) {
		points.add(new Point(eyLX, eyLY));
	}

	public void saveImage(Mat image) {
		String filename = "faceDetection.png";
		Imgcodecs.imwrite(filename, image);
	}

	public boolean matToBufferedImage() {
		MatOfByte mb = new MatOfByte();
		Imgcodecs.imencode(".jpg", image, mb);
		try {
			this.imProc = ImageIO.read(new ByteArrayInputStream(mb.toArray()));
			for (Point p: points){
				int r = 0;// red component 0...255
				int g = 255;// green component 0...255
				int b = 0;// blue component 0...255
				int col = (r << 16) | (g << 8) | b;
				imProc.setRGB((int)p.x, (int)p.y, col);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false; // Error
		}
		return true; // Successful
	}

	public void displayImage() {
		// MOSTRAR LA IMAGEN
		if (matToBufferedImage()) {
			JFrame frame = new JFrame("API");
			JLabel CuadroImagen = new JLabel();
			CuadroImagen.setBounds(29, 11, 436, 330);
			ImageIcon img = new ImageIcon(imProc);

			Icon icono = new ImageIcon(img.getImage().getScaledInstance(
					CuadroImagen.getWidth(), CuadroImagen.getHeight(),
					Image.SCALE_DEFAULT));
			CuadroImagen.setIcon(icono);

			frame.getContentPane().add(CuadroImagen, BorderLayout.CENTER);
			frame.setSize(515, 440);
			frame.setVisible(true);

			JFrame frame1 = new JFrame("Open");
			JLabel CuadroImagen1 = new JLabel();
			CuadroImagen1.setBounds(29, 11, 436, 330);

			frame1.getContentPane().add(CuadroImagen1, BorderLayout.CENTER);
			frame1.setSize(515, 440);
			frame1.setVisible(true);
			System.out.print("Gender :"+Gender+'\n');
			System.out.print("Glasses :"+Glasses+'\n');
			System.out.print("SunGlasses :"+SunGlasses+'\n');
			System.out.print("Smile :"+Smile+'\n');
		}
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public boolean getGlasses() {
		return Glasses;
	}

	public void setGlasses(boolean glasses) {
		Glasses = glasses;
	}

	public boolean getSunGlasses() {
		return SunGlasses;
	}

	public void setSunGlasses(boolean sunGlasses) {
		SunGlasses = sunGlasses;
	}

	public boolean getSmile() {
		return Smile;
	}

	public void setSmile(boolean smile) {
		Smile = smile;
	}

}
