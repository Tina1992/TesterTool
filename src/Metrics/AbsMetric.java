package Metrics;

import Servicies.AbsService;
import Servicies.ImageProc;

public abstract class AbsMetric {
	
	public abstract float getDato(AbsService service, ImageProc image) throws Exception;
	
	public abstract String getName(); 
	
	public abstract String getMedida();

}
