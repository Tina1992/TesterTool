package TestTool;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import TestTool.CreateXML;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//AÑADIR: CAMPOS OBLIGATORIOS, POSIBILIDAD DE MAS DE UN DIRECTORIO. 

public class UI extends JFrame {

	private JPanel contentPane;
	private CreateXML createXML = new CreateXML();
	private JTextField ExecPerImage;
	private JTextField NombreArchivo;
	private JTextField DirectorioImagenes;
	private boolean planCreado=false;
	private boolean planErrors=false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI frame = new UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UI() {
		setTitle("Face Recognition - settings-");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 625, 677);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JCheckBox checkBox_Smile = new JCheckBox("Smile");
		checkBox_Smile.setFont(new Font("Calibri", Font.PLAIN, 18));
		checkBox_Smile.setBounds(10, 51, 71, 31);
		contentPane.add(checkBox_Smile);

		final JCheckBox checkBox_Eyes = new JCheckBox("Eyes");
		checkBox_Eyes.setFont(new Font("Calibri", Font.PLAIN, 18));
		checkBox_Eyes.setBounds(10, 90, 71, 31);
		contentPane.add(checkBox_Eyes);

		final JCheckBox checkBox_Nose = new JCheckBox("Nose");
		checkBox_Nose.setFont(new Font("Calibri", Font.PLAIN, 18));
		checkBox_Nose.setBounds(10, 127, 71, 31);
		contentPane.add(checkBox_Nose);

		final JCheckBox checkBox_Mouth = new JCheckBox("Mouth");
		checkBox_Mouth.setFont(new Font("Calibri", Font.PLAIN, 18));
		checkBox_Mouth.setBounds(10, 166, 91, 31);
		contentPane.add(checkBox_Mouth);

		final JCheckBox checkBox_Ears = new JCheckBox("Ears");
		checkBox_Ears.setFont(new Font("Calibri", Font.PLAIN, 18));
		checkBox_Ears.setBounds(10, 203, 71, 31);
		contentPane.add(checkBox_Ears);

		final JCheckBox checkBox_Cheeks = new JCheckBox("Cheeks");
		checkBox_Cheeks.setFont(new Font("Calibri", Font.PLAIN, 18));
		checkBox_Cheeks.setBounds(103, 51, 91, 31);
		contentPane.add(checkBox_Cheeks);

		final JCheckBox checkBox_Gender = new JCheckBox("Gender");
		checkBox_Gender.setFont(new Font("Calibri", Font.PLAIN, 18));
		checkBox_Gender.setBounds(103, 90, 91, 31);
		contentPane.add(checkBox_Gender);

		final JCheckBox checkBox_Glasses = new JCheckBox("Glasses");
		checkBox_Glasses.setFont(new Font("Calibri", Font.PLAIN, 18));
		checkBox_Glasses.setBounds(103, 127, 91, 31);
		contentPane.add(checkBox_Glasses);

		final JCheckBox checkBox_Sunglasses = new JCheckBox("SunGlasses");
		checkBox_Sunglasses.setFont(new Font("Calibri", Font.PLAIN, 18));
		checkBox_Sunglasses.setBounds(103, 166, 105, 31);
		contentPane.add(checkBox_Sunglasses);

		final JCheckBox checkBox_Faceorientation = new JCheckBox(
				"FaceOrientation");
		checkBox_Faceorientation.setFont(new Font("Calibri", Font.PLAIN, 18));
		checkBox_Faceorientation.setBounds(103, 203, 150, 31);
		contentPane.add(checkBox_Faceorientation);

		final JCheckBox checkBox_FaceRect = new JCheckBox("FaceRect");
		checkBox_FaceRect.setFont(new Font("Calibri", Font.PLAIN, 18));
		checkBox_FaceRect.setBounds(11, 296, 113, 23);
		contentPane.add(checkBox_FaceRect);

		final JCheckBox checkBox_SkyBiometry = new JCheckBox("SkyBiometry");
		checkBox_SkyBiometry.setFont(new Font("Calibri", Font.PLAIN, 18));
		checkBox_SkyBiometry.setBounds(128, 296, 135, 23);
		contentPane.add(checkBox_SkyBiometry);

		final JCheckBox checkBox_Opencv = new JCheckBox("OpenCV");
		checkBox_Opencv.setFont(new Font("Calibri", Font.PLAIN, 18));
		checkBox_Opencv.setBounds(268, 296, 91, 23);
		contentPane.add(checkBox_Opencv);

		final JCheckBox checkBox_Googleplay = new JCheckBox("GooglePlay");
		checkBox_Googleplay.setFont(new Font("Calibri", Font.PLAIN, 18));
		checkBox_Googleplay.setBounds(373, 296, 133, 23);
		contentPane.add(checkBox_Googleplay);

		final JCheckBox checkbox_ResponseTime = new JCheckBox("ResponseTime");
		checkbox_ResponseTime.setFont(new Font("Calibri", Font.PLAIN, 18));
		checkbox_ResponseTime.setBounds(227, 51, 135, 31);
		contentPane.add(checkbox_ResponseTime);

		final JCheckBox checkbox_Hausdorffdistance = new JCheckBox(
				"HausdorffDistance");
		checkbox_Hausdorffdistance.setFont(new Font("Calibri", Font.PLAIN, 18));
		checkbox_Hausdorffdistance.setBounds(227, 85, 161, 31);
		contentPane.add(checkbox_Hausdorffdistance);

		final JCheckBox checkbox_Imagesize = new JCheckBox("ImageSize");
		checkbox_Imagesize.setFont(new Font("Calibri", Font.PLAIN, 18));
		checkbox_Imagesize.setBounds(429, 51, 161, 31);
		contentPane.add(checkbox_Imagesize);

		final JCheckBox checkbox_Imageformat = new JCheckBox("ImageFormat");
		checkbox_Imageformat.setFont(new Font("Calibri", Font.PLAIN, 18));
		checkbox_Imageformat.setBounds(429, 88, 161, 31);
		contentPane.add(checkbox_Imageformat);
		
		final JCheckBox checkBox_Precision = new JCheckBox("Precision");
		checkBox_Precision.setFont(new Font("Calibri", Font.PLAIN, 18));
		checkBox_Precision.setBounds(227, 119, 161, 31);
		contentPane.add(checkBox_Precision);

		JFormattedTextField restriccion = new JFormattedTextField(
				new Integer(3));
		ExecPerImage = new JTextField();
		ExecPerImage.setBounds(347, 360, 91, 26);
		contentPane.add(ExecPerImage);
		ExecPerImage.setColumns(10);

		NombreArchivo = new JTextField();
		NombreArchivo.setBounds(165, 458, 133, 27);
		contentPane.add(NombreArchivo);
		NombreArchivo.setColumns(10);

		DirectorioImagenes = new JTextField();
		DirectorioImagenes.setColumns(10);
		DirectorioImagenes.setBounds(165, 400, 273, 27);

		DirectorioImagenes.setText("C:/Users/marmota/Directorio");
		contentPane.add(DirectorioImagenes);

		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ARMADO DEL PLANTEST

				// ----------------------------OPCIONES-----------------------------
				if (checkBox_Smile.isSelected()) {
					createXML.addAtributo("Smile", true);
				} else
					createXML.addAtributo("Smile", false);

				if (checkBox_Eyes.isSelected())
					createXML.addAtributo("Eyes", true);
				else
					createXML.addAtributo("Eyes", false);

				if (checkBox_Nose.isSelected())
					createXML.addAtributo("Nose", true);
				else
					createXML.addAtributo("Nose", false);

				if (checkBox_Mouth.isSelected()) {
					createXML.addAtributo("Mouth", true);
				} else
					createXML.addAtributo("Mouth", false);

				if (checkBox_Ears.isSelected()) {
					createXML.addAtributo("Ears", true);
				} else
					createXML.addAtributo("Ears", false);

				if (checkBox_Cheeks.isSelected()) {
					createXML.addAtributo("Cheeks", true);
				} else
					createXML.addAtributo("Cheeks", false);

				if (checkBox_Gender.isSelected()) {
					createXML.addAtributo("Gender", true);
				} else
					createXML.addAtributo("Gender", false);

				if (checkBox_Glasses.isSelected()) {
					createXML.addAtributo("Glasses", true);
				} else
					createXML.addAtributo("Glasses", false);

				if (checkBox_Sunglasses.isSelected()) {
					createXML.addAtributo("Sunglasses", true);
				} else
					createXML.addAtributo("Sunglasses", false);

				if (checkBox_Faceorientation.isSelected()) {
					createXML.addAtributo("FaceOrientation", true);
				} else
					createXML.addAtributo("FaceOrientation", false);

				// ----------------------------PROVIDERS-----------------------------
				if (checkBox_FaceRect.isSelected()) {
					createXML.addProviders("FaceRect");
				}

				if (checkBox_SkyBiometry.isSelected()) {
					createXML.addProviders("SkyBiometry");
				}

				if (checkBox_Opencv.isSelected()) {
					createXML.addProviders("OpenCV");
				}

				if (checkBox_Googleplay.isSelected()) {
					createXML.addProviders("Googleplay");
				}
				
				if (!(checkBox_FaceRect.isSelected())&&!(checkBox_SkyBiometry.isSelected())&&!(checkBox_Opencv.isSelected())&&!(checkBox_Googleplay.isSelected()))
					planErrors=true;

				// ----------------------------METRICS-----------------------------
				if (checkbox_ResponseTime.isSelected()) {
					createXML.addMetrics("ResponseTime");
				}

				if (checkbox_Hausdorffdistance.isSelected()) {
					createXML.addMetrics("HausdorffDistance");
				}
				if (checkBox_Precision.isSelected()){
					createXML.addMetrics("Precision");
				}
				
				//----------------------------ATRIBUTOS-----------------------------

				if (checkbox_Imagesize.isSelected()) {
					createXML.addMetrics("Imagesize");
				}

				if (checkbox_Imageformat.isSelected()) {
					createXML.addMetrics("Imageformat");
				}

				// ---------------NUMBER EXEC PER IMAGE & PROVIDER
				// -----------------
				String execs = ExecPerImage.getText();
				createXML.setExecPerImage(execs);

				// ------------------------------NAME-------------------------------
				String name = NombreArchivo.getText();
				createXML.setName(name);

				// ------------------------DIRECTORIO-------------------------------
				if (!DirectorioImagenes.getText().equals("")) {
					String directorio = DirectorioImagenes.getText();
					createXML.addDirectorio(directorio);
				}
				else{
					planErrors=true;
				}
				
				if (planErrors){
					JPanel panel = new JPanel();
					JOptionPane.showMessageDialog(panel, "Plan creado con errores. Por favor, reintente", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					try {
						createXML.create();
						planCreado=true;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		btnDone.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnDone.setBounds(238, 537, 121, 23);
		contentPane.add(btnDone);

		JButton btnExecute = new JButton("Execute");
		btnExecute.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (planCreado){
				String workingDir = System.getProperty("user.dir");
				Tester.test(workingDir + "\\"+NombreArchivo.getText()+".xml");}
				else
				{
					JPanel panel = new JPanel();
					JOptionPane.showMessageDialog(panel, "El testPlan no fue creado. Por favor, reintente", "Error", JOptionPane.ERROR_MESSAGE);
				 }
			}
		});
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// AGREGAR CODIGO PARA QUE SE EXECUTE EL PLAN
				if (planCreado){
				UI.this.setVisible(false);}
			}
		});
		btnExecute.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnExecute.setBounds(373, 537, 114, 23);

		contentPane.add(btnExecute);

		JLabel Etiqueta_atributos = new JLabel("Opciones");
		Etiqueta_atributos.setFont(new Font("Calibri", Font.PLAIN, 19));
		Etiqueta_atributos.setBounds(10, 23, 91, 31);
		contentPane.add(Etiqueta_atributos);

		JLabel lblProveedoresDeServicio = new JLabel("Proveedores de servicio");
		lblProveedoresDeServicio.setFont(new Font("Calibri", Font.PLAIN, 19));
		lblProveedoresDeServicio.setBounds(10, 258, 200, 31);
		contentPane.add(lblProveedoresDeServicio);

		JLabel Etiqueta_NumberExecPerImage = new JLabel(
				"Numero de ejecuciones por imagen y proveedor");
		Etiqueta_NumberExecPerImage
				.setFont(new Font("Calibri", Font.PLAIN, 17));
		Etiqueta_NumberExecPerImage.setBounds(10, 358, 347, 31);
		contentPane.add(Etiqueta_NumberExecPerImage);

		JLabel Etiqueta_Metricas = new JLabel("M\u00E9tricas");
		Etiqueta_Metricas.setFont(new Font("Calibri", Font.PLAIN, 19));
		Etiqueta_Metricas.setBounds(227, 23, 91, 31);
		contentPane.add(Etiqueta_Metricas);

		JLabel Etiqueta_NombreArchivo = new JLabel("Nombre de plan");
		Etiqueta_NombreArchivo.setFont(new Font("Calibri", Font.PLAIN, 18));
		Etiqueta_NombreArchivo.setBounds(34, 459, 121, 23);
		contentPane.add(Etiqueta_NombreArchivo);

		JLabel Etiqueta_Directorio = new JLabel("Directorio Imagenes");
		Etiqueta_Directorio.setFont(new Font("Calibri", Font.PLAIN, 18));
		Etiqueta_Directorio.setBounds(10, 400, 161, 23);
		contentPane.add(Etiqueta_Directorio);

		JLabel Label_agregarDir = new JLabel();
		Label_agregarDir.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				if (!DirectorioImagenes.getText().equals("")) {
					String directorio = DirectorioImagenes.getText();
					createXML.addDirectorio(directorio);
				}
				DirectorioImagenes.setText("");
			}
		});
		Label_agregarDir.setBounds(441, 400, 26, 27);
		contentPane.add(Label_agregarDir);
		ImageIcon image = new ImageIcon(getClass().getResource("add.jpg"));
		Icon icon = new ImageIcon(image.getImage().getScaledInstance(
				Label_agregarDir.getWidth(), Label_agregarDir.getHeight(),
				Image.SCALE_DEFAULT));
		Label_agregarDir.setIcon(icon);
		
		JLabel label = new JLabel("Atributos");
		label.setFont(new Font("Calibri", Font.PLAIN, 19));
		label.setBounds(429, 23, 161, 31);
		contentPane.add(label);

	}
}
