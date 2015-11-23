package Metrics;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import Servicies.AbsService;
import Servicies.ImageProc;

public class PrecisionMetric extends AbsMetric {

	// ------Metodos utilizados para la creación del dataset--------

	private static void createDirectory(String workingDir, File path) {
		if (path.isFile() && path.getAbsolutePath().contains(".jpg")) {
			path.renameTo(new File(workingDir + "\\" + path.getName()));
		} else {
			if (path.isDirectory()) {
				File[] dirs = path.listFiles();
				for (File d : dirs) {
					createDirectory(workingDir, d);
				}
			}
		}

	}

	@SuppressWarnings("unused")
	private static void createDataset() {
		String workingDir = System.getProperty("user.dir");
		Path path = FileSystems.getDefault().getPath(workingDir, "Faces");
		File oldPath = new File(workingDir, "lfw");
		try {
			Files.createDirectory(path);
			createDirectory(path.toString(), oldPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static void createPrecisionDir() {
		String workingDir = System.getProperty("user.dir");
		Path path = FileSystems.getDefault().getPath(workingDir, "Faces");
		Path newPath = FileSystems.getDefault()
				.getPath(workingDir, "Precision");
		try {
			Files.createDirectories(newPath);
			File dir = new File(path.toString());
			File[] directoryListing = dir.listFiles();
			if (directoryListing != null) {
				for (File child : directoryListing) {
					newPath = FileSystems.getDefault().getPath(
							workingDir + "\\Precision",
							child.getName().replace(".jpg", ".txt"));
					Files.createFile(newPath);
					PrintWriter writer = new PrintWriter(newPath.toString(),
							"UTF-8");
					writer.println("1");
					writer.close();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// -------Fin de metodos de creación del dataset-----------

	@Override
	public float getDato(AbsService service, ImageProc image) {
		// TODO Auto-generated method stub
		Path file_path = FileSystems.getDefault().getPath(image.getFile_path());
		Path file_name = file_path.getFileName();

		String workingDir = System.getProperty("user.dir");
		Path prec_path = FileSystems.getDefault().getPath(
				workingDir + "\\Precision",
				file_name.toString().replace(".jpg", ".txt"));

		Charset ENCODING = StandardCharsets.UTF_8;
		try {
			if (image.getFaces() != null) {
				BufferedReader reader = Files.newBufferedReader(prec_path,
						ENCODING);
				String line = reader.readLine();
				if (line != null) {
					Integer p=image.getFaces().size();
					Integer tp = new Integer(line);
					Integer fp = new Integer(Math.abs(p-tp));
					return (float)tp  / (float) (tp+fp);
				}
			} else
				return -3;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return -4;
		}
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Precision";
	}

	@Override
	public String getMedida() {
		// TODO Auto-generated method stub
		return "per cent";
	}
}
