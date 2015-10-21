package Servicies;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.json.JSONException;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class GraphImage {
	private ImageProc im;
	private Mat image;
	private BufferedImage imProc;
	
	public GraphImage(ImageProc im){
		this.im=im;
		image = Imgcodecs.imread(im.getFile_path());
	}
	
	private void drawRectangles(Rectangle obj) throws JSONException {
		org.opencv.core.Point p1=new org.opencv.core.Point(obj.x, obj.y);
		org.opencv.core.Point p2=new org.opencv.core.Point(obj.x + obj.width, obj.y
				+ obj.height);
		Imgproc.rectangle(image, p1, p2, new Scalar(0, 255, 0), 2); // EL 2 FINAL ES EL GROSOR
														// DEL RECTANGULO
	}
	
	public boolean matToBufferedImage() {
		MatOfByte mb = new MatOfByte();
		if (im.getFaces()!=null){
			for (Rectangle r: im.getFaces())
				drawRectangles(r);
		}
		if (im.getMouthRects()!=null)
			drawRectangles(im.getMouthRects());
		if (im.getNoseRects()!=null)
			drawRectangles(im.getNoseRects());
		if (im.getEyesRects()!=null){
			for (Rectangle r: im.getEyesRects()){
				drawRectangles(r);
			}
		}
		Imgcodecs.imencode(".jpg", image, mb);
		try {
			this.imProc = ImageIO.read(new ByteArrayInputStream(mb.toArray()));
			int r = 0;// red component 0...255
			int g = 255;// green component 0...255
			int b = 0;// blue component 0...255
			int col = (r << 16) | (g << 8) | b;
			if (im.getEyesPoints() != null) {
				for (Point p : im.getEyesPoints()) {
					imProc.setRGB((int) p.x, (int) p.y, col);
				}
			}
			if (im.getNosePoint() != null)
				imProc.setRGB((int) im.getNosePoint().x, (int) im.getNosePoint().y, col);
			if (im.getMouthPoint() != null)
				imProc.setRGB((int) im.getMouthPoint().x, (int) im.getMouthPoint().y, col);
		} catch (IOException e) {
			e.printStackTrace();
			return false; // Error
		}
		return true; // Successful
	}
	

	public void displayImage() {
		// MOSTRAR LA IMAGEN
		if (matToBufferedImage()) {
			JFrame frame = new JFrame("Resultado");
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
			if (im.getGender()!=null){
			System.out.print("Gender :" + im.getGender()+ '\n');}
			else
			System.out.print("Gender was not evaluated\n");
			if (im.getGlasses()!=null){
			System.out.print("Glasses :" + im.getGlasses()+ '\n');}
			else
				System.out.print("Glasses was not evaluated\n");
			if (im.getSunGlasses()!=null){
			System.out.print("SunGlasses :" + im.getSunGlasses()+ '\n');}
			else
				System.out.print("SunGlasses was not evaluated\n");
			if (im.getSmile()!=null){
			System.out.print("Smile :" + im.getSmile()+ '\n');}
			else
				System.out.print("Smile was not evaluated\n");
		}
	}

}
