package TestTool;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class CreateXML {
	
	private  Hashtable<String,Boolean> atributos=new Hashtable<String,Boolean>();
	private  Vector<String> providers=new Vector<String>();
	private  Vector<String> metrics=new Vector<String>();
	private String execPerImage="1";
	private String name;
	private Vector<String> directorios=new Vector<String>(); 
//	private String path;
	
	
	public CreateXML(){
		atributos = new Hashtable<String,Boolean>();
		providers = new Vector<String>();
		metrics = new Vector<String>();
	}
	
	public void addAtributo(String k, Boolean v){
		atributos.put(k, v);
	}
	
	public Boolean getValueAtributo(String k){
		return atributos.get(k);
	}
	
	public void addProviders(String p){
		providers.add(p);
	}
	
	public void addMetrics(String m){
		metrics.add(m);
	}
	
	public void setExecPerImage(String e){
		execPerImage=e;
	}
	
	public void setName(String n){
		name=n;
	}
	
	public void addDirectorio(String d){
		System.out.println(d);
		directorios.add(d);
	}
	
	//SETTINGS: ATRIBUTOS 
	public Element Setting(){
		
		Element settings = new Element("settings");
		
		//RECORRIENDO LA HASHTABLE
		for(String k:atributos.keySet()){
			String value=atributos.get(k).toString();
			settings.addContent(new Element(k).setText(value));
		}
		
        return settings;
	}
	
	public Element Providers(){
		Element Providers = new Element("Providers");
		for(int i=0; i<providers.size();i++){
			Providers.addContent((new Element("ProviderName").setText(providers.elementAt(i))));
		}
		return Providers;
	}
	
	public Element ExecPerImage(){
		Element execs = new Element("NumberOfExecutionsPerImageAndProvider");
		execs.setText(execPerImage);
		return execs;
	}
	
	public Element Metrics(){
		Element Metrics = new Element("Metrics");
		for(int i=0;i<metrics.size();i++)
			Metrics.addContent((new Element("Metric")).setText(metrics.elementAt(i)));
		return Metrics;
	}
	
	public Element Images(){
		Element Images=new Element("Images");
		for(int i=0;i<directorios.size();i++)
			Images.addContent((new Element("DirectoryName")).setText(directorios.elementAt(i)));
		return Images;
	}
	
	
	
	public void create() throws IOException{
		Element TestPlan = new Element("TestPlan");
        Document documento = new Document();
        documento.setRootElement(TestPlan);
        
        documento.getRootElement().addContent(new Element("Name").setText(name));
        documento.getRootElement().addContent(this.ExecPerImage());
        documento.getRootElement().addContent(this.Providers());
        documento.getRootElement().addContent(this.Setting());
        documento.getRootElement().addContent(this.Images());
        documento.getRootElement().addContent(this.Metrics());
        
        XMLOutputter xmloutput = new XMLOutputter();
        xmloutput.setFormat(Format.getPrettyFormat());
        String workingDir = System.getProperty("user.dir");
        if (name==null)
        	xmloutput.output(documento, new FileWriter(workingDir+"\\testPlan.xml"));
        else
        	xmloutput.output(documento, new FileWriter(workingDir+"\\"+name+".xml"));
	}
	
}
