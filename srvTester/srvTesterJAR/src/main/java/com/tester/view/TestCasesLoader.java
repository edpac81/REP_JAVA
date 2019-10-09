package com.tester.view;

import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.tester.controller.TestCaseListController;
import com.tester.logger.STLogger;
import com.tester.utils.GetFileUtil;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("serial")
public class TestCasesLoader extends JFrame implements ActionListener {
	private JPanel contentPane;
	private File xlsFile;
	private File xmlFile;
	private JButton btnAbrirExcel;
	private JButton btnAbrirRequest;
	private JLabel lblSeleccionarArchivosDe;
	private JButton btnProcesar;
	private JTextField textField;
	private JTextField textField_1;
	private JMenuBar menuBar;
	private JMenu mnConfiguracin;
	private JMenuItem miGeneralConfig;
	Logger log;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestCasesLoader frame = new TestCasesLoader();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TestCasesLoader() {
		STLogger.startLooger();

		setTitle("Ejecutar Casos de Prueba");
		setDefaultCloseOperation(3);
		setBounds(100, 100, 469, 212);

		this.menuBar = new JMenuBar();
		setJMenuBar(this.menuBar);

		this.mnConfiguracin = new JMenu("Configuración");
		this.menuBar.add(this.mnConfiguracin);

		this.miGeneralConfig = new JMenuItem("Editar Configuración");
		this.miGeneralConfig.addActionListener(this);
		this.mnConfiguracin.add(this.miGeneralConfig);

		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC }));

		this.lblSeleccionarArchivosDe = new JLabel("Seleccionar archivos de trabajo");
		this.lblSeleccionarArchivosDe.setFont(new Font("Tahoma", 1, 12));
		this.contentPane.add(this.lblSeleccionarArchivosDe, "2, 2, 3, 1");

		JLabel lblSeleccioneArchivoDe = new JLabel("Casos de Prueba");
		this.contentPane.add(lblSeleccioneArchivoDe, "2, 4, left, default");

		this.btnAbrirExcel = new JButton("Abrir Excel");
		lblSeleccioneArchivoDe.setLabelFor(this.btnAbrirExcel);
		this.btnAbrirExcel.addActionListener(this);

		this.textField = new JTextField();
		this.textField.setEditable(false);
		this.contentPane.add(this.textField, "4, 4, 7, 1, fill, default");
		this.textField.setColumns(10);
		this.contentPane.add(this.btnAbrirExcel, "12, 4");

		JLabel lblSeleccionePlantillaXml = new JLabel("XML de Request ");
		this.contentPane.add(lblSeleccionePlantillaXml, "2, 6, right, default");

		this.btnAbrirRequest = new JButton("Abrir Request");
		lblSeleccionePlantillaXml.setLabelFor(this.btnAbrirRequest);
		this.btnAbrirRequest.addActionListener(this);

		this.textField_1 = new JTextField();
		this.textField_1.setEditable(false);
		this.contentPane.add(this.textField_1, "4, 6, 7, 1, fill, default");
		this.textField_1.setColumns(10);
		this.contentPane.add(this.btnAbrirRequest, "12, 6");

		this.btnProcesar = new JButton("Procesar");
		this.btnProcesar.addActionListener(this);
		this.contentPane.add(this.btnProcesar, "12, 10");
	}

	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnAbrirExcel) {
			this.xlsFile = GetFileUtil.open(new FileNameExtensionFilter("Excel Files", new String[] { "xls", "xlsx" }), this, "\\resources");
			if (this.xlsFile != null) {
				this.textField.setText(this.xlsFile.getAbsolutePath());
			}
		} else if (e.getSource() == this.btnAbrirRequest) {
			this.xmlFile = GetFileUtil.open(new FileNameExtensionFilter("XML Files", new String[] { "xml" }), this, "\\resources");
			if (this.xmlFile != null) {
				this.textField_1.setText(this.xmlFile.getAbsolutePath());
			}
		} else if (e.getSource() == this.miGeneralConfig) {
			ConfigurationEdit localConfigurationEdit = new ConfigurationEdit();
		} else if (e.getSource() == this.btnProcesar) {
			startProcess();
		}
	}

	private void startProcess() {
		if (this.xlsFile == null) {
			JOptionPane.showMessageDialog(this, "Debe cargar el archivo Excel con los casos de prueba", "Error", 0);
			return;
		}
		if (this.xmlFile == null) {
			JOptionPane.showMessageDialog(this, "Debe cargar el archivo con la plantilla de request", "Error", 0);
			return;
		}

		//ServiceStart srvStart = new ServiceStart(this.xlsFile, this.xmlFile);
		TestCaseListController tclController = new TestCaseListController(this.xmlFile, this.xlsFile);
		try {
			tclController.execute();
		} catch (Exception e) {
			STLogger.getLogger().error(e.getMessage(), e);
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", 0);
		}
	}
}