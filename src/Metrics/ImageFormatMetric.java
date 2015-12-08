package Metrics;

import java.nio.file.Path;
import java.nio.file.Paths;

import Servicies.AbsService;
import Servicies.ImageProc;

public class ImageFormatMetric extends AbsMetric {

	@Override
	public Object getDato(AbsService service, ImageProc image){
		// TODO Auto-generated method stub
		Path path=Paths.get(image.getFile_path());
		String name=path.getFileName().toString();
		return name.substring(name.length()-3);
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
