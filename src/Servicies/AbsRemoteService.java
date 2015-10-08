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

	public abstract void parse(HttpResponse<JsonNode> response, Hashtable<String, Boolean> options);

}
