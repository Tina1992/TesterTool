package Servicies;

import java.io.File;
import java.util.Hashtable;

import org.apache.http.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class SkyBiometryService extends AbsRemoteService {

	public SkyBiometryService() {
		super();
		addAvailableAtt("Eyes");
		addAvailableAtt("Nose");
		addAvailableAtt("Mouth");
		addAvailableAtt("Gender");
		addAvailableAtt("Glasses");
		addAvailableAtt("SunGlasses");
		addAvailableAtt("Smile");
	}

	@Override
	public HttpResponse<JsonNode> post(File file,
			Hashtable<String, Boolean> options) throws UnirestException {
		// TODO Auto-generated method stub
		String s = "";
		imageProc = new ImageProc(file.getAbsolutePath());
		if (options.get("Gender")) {
			s=s.concat("Gender");
		}
		if (options.get("Glasses")) {
			if (s.equals(""))
				s=s.concat("Glasses");
			else
				s=s.concat(", Glasses");
		}
		if (options.get("Smile")) {
			if (s.equals(""))
				s=s.concat("Smiling");
			else
				s=s.concat(", Smiling");
		}
		if (s.equals("")) {
			s = "none";
		}
		return Unirest
				.post("https://face.p.mashape.com/faces/detect?api_key=771b5991a5ff4ce1a7cfcef2e1d91ae5&api_secret=cfc895f2c40e475eb1032be25b63f969")
				.header("X-Mashape-Key",
						"kCKZDyhuAxmsh2l2E7GXVfOLFe9hp1w77PbjsnmUiFR69J94RG")
				.field("attributes", s).field("detector", "Aggressive")
				.field("files", file).asJson();
	}

	@Override
	public void parse(HttpResponse<JsonNode> response,
			Hashtable<String, Boolean> options) {
		JSONObject auxiliar = new JSONObject();
		try {
			JSONObject obj = response.getBody().getObject();
			int cantPhotos = ((JSONArray) obj.get("photos")).length();
			for (int j = 0; j < cantPhotos; j++) {
				int imgHeight = (int) ((JSONArray) obj.get("photos"))
						.getJSONObject(j).get("height");
				int imgWidth = (int) ((JSONArray) obj.get("photos"))
						.getJSONObject(j).get("width");

				JSONObject faces = ((JSONArray) obj.get("photos"))
						.getJSONObject(j); // DATOS DE TODOS LOS ROSTROS: TAGS Y
											// RESTO.

				int cantfaces = faces.getJSONArray("tags").length();

				for (int i = 0; i < cantfaces; i++) {
					JSONObject face = faces.getJSONArray("tags").getJSONObject(
							i);
					JSONObject coordenadas = face.getJSONObject("center");
					double faceH = (double) face.get("height") / 100
							* imgHeight;
					double faceW = (double) face.get("width") / 100 * imgWidth;
					double valueX = (((double) coordenadas.get("x")) / 100 * imgWidth)
							- faceW / 2;
					double valueY = ((double) coordenadas.get("y")) / 100
							* imgHeight - faceH / 2;
					double valueHeight = faceH;
					double valueWidth = faceW;
					auxiliar.put("x", valueX);
					auxiliar.put("y", valueY);
					auxiliar.put("height", valueHeight);
					auxiliar.put("width", valueWidth);
					imageProc.drawRectangles(auxiliar);
					for (String s: options.keySet()){
						addPoint(s, options, face, imgHeight, imgWidth);
					}
					imageProc.displayImage();
				}

			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void addPoint(String op, Hashtable<String, Boolean> options, JSONObject face, double h, double w) {
		JSONObject coor = null;
		if (options.get(op)) {
			switch (op) {
			case "Mouth": {
				coor=face.getJSONObject("mouth_center");
				double x=(double) coor.get("x")/100*w;
				double y=(double) coor.get("y")/100*h;
				imageProc.drawPoint(x, y);
				break;
			}
			case "Eyes":{
				coor=face.getJSONObject("eye_right");
				double x=(double) coor.get("x")/100*w;
				double y=(double) coor.get("y")/100*h;
				imageProc.drawPoint(x, y);
				coor=face.getJSONObject("eye_left");
				x=(double) coor.get("x")/100*w;
				y=(double) coor.get("y")/100*h;
				imageProc.drawPoint(x, y);
				break;
			}
			case "Nose":{
				coor=face.getJSONObject("nose");
				double x=(double) coor.get("x")/100*w;
				double y=(double) coor.get("y")/100*h;
				imageProc.drawPoint(x, y);
				break;
			}
			case "Gender":{
				JSONObject att=face.getJSONObject("attributes");
				JSONObject gen=att.getJSONObject("gender");
				String genVal=(String)gen.get("value");
				imageProc.setGender(genVal);
				break;
			}
			case "Glasses":{
				JSONObject att=face.getJSONObject("attributes");
				JSONObject gen=att.getJSONObject("glasses");
				String genVal=(String)gen.get("value");
				imageProc.setGlasses(Boolean.getBoolean(genVal));
				break;
			}
			case "Smile":{
				JSONObject att=face.getJSONObject("attributes");
				JSONObject gen=att.getJSONObject("smiling");
				String genVal=(String)gen.get("value");
				imageProc.setSmile(Boolean.getBoolean(genVal));
				break;
			}
			case "SunGlasses":{
				JSONObject att=face.getJSONObject("attributes");
				JSONObject gen=att.getJSONObject("dark_glasses");
				String genVal=(String)gen.get("value");
				imageProc.setSunGlasses(Boolean.getBoolean(genVal));
				break;
			}
			}
		}
	}

}
