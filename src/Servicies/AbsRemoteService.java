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
			HttpResponse<JsonNode> res=post(f,opts);
			if (res.getStatus()==200){
				imageProc=parse(res, opts);}
			else{
				imageProc = new ImageProc(f.getAbsolutePath());
				imageProc.setError(res.getStatus());
			}
			endTime=System.nanoTime();
			return imageProc;
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
