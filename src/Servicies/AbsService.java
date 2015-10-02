package Servicies;

import java.util.Hashtable;

import TestTool.ParserPlan;

public abstract class AbsService {
	protected float timeout;
	protected int repeticiones;
	protected int cantMax;
	
	private Hashtable<String, Boolean> disp = new Hashtable<String, Boolean>();
	
	public AbsService(){
		for (String s:ParserPlan.atributos.keySet()){
			disp.put(s, false);
		}
	}
	
	public boolean IsAvailableAtt(String at){
		return disp.get(at);
	}
	
	public void addAvailableAtt(String at){
		disp.put(at, true);
	}
	
	public abstract void post();
	
	public abstract void parse();
	
}
