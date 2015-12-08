package Metrics;

import Servicies.AbsService;
import Servicies.ImageProc;

public abstract class AbsMetric {
	
	public abstract Object getDato(AbsService service, ImageProc image);
	
	public abstract String getName(); 
	
	public abstract String getMedida();

}
