package Metrics;

import Servicies.AbsService;
import Servicies.ImageProc;

public class ResponseTimeMetric extends AbsMetric {

	@Override
	public Object getDato(AbsService service, ImageProc image) {
		return service.getResponseTime();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Response Time";
	}

	@Override
	public String getMedida() {
		// TODO Auto-generated method stub
		return "second";
	}

}
