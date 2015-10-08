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

public class FaceRectService extends AbsRemoteService {

	public FaceRectService() {
		super();
		addAvailableAtt("Eyes");
		addAvailableAtt("Nose");
		addAvailableAtt("Mouth");
	}

	@Override
	public HttpResponse<JsonNode> post(File file,
			Hashtable<String, Boolean> options) throws UnirestException {
		// TODO Auto-generated method stub
		imageProc = new ImageProc(file.getAbsolutePath());
		if (options.get("Eyes") || options.get("Nose") || options.get("Mouth")) {
			return Unirest
					.post("https://apicloud-facerect.p.mashape.com/process-file.json")
					.header("X-Mashape-Key",
							"kCKZDyhuAxmsh2l2E7GXVfOLFe9hp1w77PbjsnmUiFR69J94RG")
					.field("features", true).field("image", file).asJson();
		}
		return Unirest
				.post("https://apicloud-facerect.p.mashape.com/process-file.json")
				.header("X-Mashape-Key",
						"kCKZDyhuAxmsh2l2E7GXVfOLFe9hp1w77PbjsnmUiFR69J94RG")
				.field("image", file).asJson();
	}

	@Override
	public void parse(HttpResponse<JsonNode> response,
			Hashtable<String, Boolean> options) {
		// TODO Auto-generated method stub
		try {

			JSONObject obj = response.getBody().getObject();
			JSONArray faces = (JSONArray) obj.get("faces");

			int cantfaces = faces.length();
			for (int i = 0; i < cantfaces; i++) {
				imageProc.drawRectangles(faces.getJSONObject(i));
				if (options.get("Eyes") || options.get("Nose")
						|| options.get("Mouth")) {
					JSONObject face = faces.getJSONObject(i);
					JSONObject features = (JSONObject) face.get("features");
					if (options.get("Eyes")) {
						JSONArray eyes = (JSONArray) features.get("eyes");
						for (int j = 0; j < 2; j++)
							imageProc.drawRectangles(eyes.getJSONObject(j));
					}
					if (options.get("Nose")) {
						imageProc.drawRectangles(features.getJSONObject("nose"));
					}
					if (options.get("Mouth")) {
						imageProc.drawRectangles(features.getJSONObject("mouth"));
					}
				}
			}
			imageProc.displayImage();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
