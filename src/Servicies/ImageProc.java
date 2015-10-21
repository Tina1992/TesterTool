package Servicies;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;

public class ImageProc {
	private String file_path;
	private Vector<Rectangle> faces;
	
	private Vector<Rectangle> eyesRects;
	private Rectangle noseRect;
	private Rectangle mouthRect;

	private Vector<Point> eyesP = null;
	private Point noseP = null;
	private Point mouthP = null;
	
	private String Gender = null;
	private Boolean Glasses = null;
	private Boolean SunGlasses = null;
	private Boolean Smile = null;

	public ImageProc(String path_image) {
		this.setFile_path(path_image);
		faces = new Vector<Rectangle>();
		eyesP = new Vector<Point>();
		eyesRects = new Vector<Rectangle>();
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public Boolean getGlasses() {
		return Glasses;
	}

	public void setGlasses(boolean glasses) {
		Glasses = glasses;
	}

	public Boolean getSunGlasses() {
		return SunGlasses;
	}

	public void setSunGlasses(boolean sunGlasses) {
		SunGlasses = sunGlasses;
	}

	public Boolean getSmile() {
		return Smile;
	}

	public void setSmile(boolean smile) {
		Smile = smile;
	}

	public Vector<Rectangle> getFaces() {
		return faces;
	}

	public void addFace(Rectangle cara) {
		faces.add(cara);
	}

	public Vector<Point> getEyesPoints() {
		return eyesP;
	}

	public void addEyesPoint(Point eyesP) {
		this.eyesP.add(eyesP);
	}

	public Point getNosePoint() {
		return noseP;
	}

	public void setNosePoint(Point noseP) {
		this.noseP = noseP;
	}

	public Point getMouthPoint() {
		return mouthP;
	}

	public void setMouthPoint(Point point) {
		this.mouthP = point;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public Vector<Rectangle> getEyesRects() {
		return eyesRects;
	}

	public void addEyeRect(Rectangle eyeRect) {
		eyesRects.add(eyeRect);
	}

	public Rectangle getNoseRects() {
		return noseRect;
	}

	public void setNoseRects(Rectangle noseRect) {
		this.noseRect = noseRect;
	}

	public Rectangle getMouthRects() {
		return mouthRect;
	}

	public void setMouthRects(Rectangle mouthRect) {
		this.mouthRect = mouthRect;
	}

}
