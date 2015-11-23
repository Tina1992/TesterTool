package Metrics;

import java.io.File;

import Servicies.AbsService;
import Servicies.ImageProc;

public class ImageSizeMetric extends AbsMetric {

	@Override
	public float getDato(AbsService service, ImageProc image) throws Exception {
		String path=image.getFile_path();
		File f=new File(path);
		return f.getTotalSpace();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Image Size";
	}

	@Override
	public String getMedida() {
		// TODO Auto-generated method stub
		return "Byte";
	}

}
