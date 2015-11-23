package Metrics;

import java.nio.file.Path;
import java.nio.file.Paths;

import Servicies.AbsService;
import Servicies.ImageProc;

public class ImageFormatMetric extends AbsMetric {

	@Override
	public float getDato(AbsService service, ImageProc image) throws Exception {
		// TODO Auto-generated method stub
		Path path=Paths.get(image.getFile_path());
		String name=path.getFileName().toString();
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Image Format";
	}

	@Override
	public String getMedida() {
		// TODO Auto-generated method stub
		return null;
	}

}
