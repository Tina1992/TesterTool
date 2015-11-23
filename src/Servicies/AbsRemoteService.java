package Servicies;

import java.io.File;
import java.util.Hashtable;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

public abstract class AbsRemoteService extends AbsService {
	
	public AbsRemoteService(){
		super();
	}

	public abstract HttpResponse<JsonNode> post(File file, Hashtable<String, Boolean> options) throws UnirestException;

	public abstract ImageProc parse(HttpResponse<JsonNode> response, Hashtable<String, Boolean> options);

	public ImageProc getFaceRecognition(File f, Hashtable<String, Boolean> opts){
		try {
			startTime=System.nanoTime();
			ImageProc im=parse(post(f, opts), opts);
			endTime=System.nanoTime();
			return im;
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
