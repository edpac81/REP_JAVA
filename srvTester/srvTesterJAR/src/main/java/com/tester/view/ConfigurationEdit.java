package com.tester.view;

import com.tester.config.PropertiesLoader;
import com.tester.utils.GetFileUtil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.Properties;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class ConfigurationEdit
  extends JFrame
  implements ActionListener, ListSelectionListener
{
  private JPanel contentPane;
  private JTextField txtConfigFile;
  private File propFile;
  private Properties prop;
  private JList<String> list;
  private DefaultListModel<String> listModel;
  private JTextField txtEditKey;
  private JButton btnOpen;
  private JButton btnAsign;
  private JButton btnSave;
  private JLabel lblKey;
  private JLabel lblEditarValor;
  private JScrollPane scrollPane;
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
	public ConfigurationEdit()
  {
    super("Editar Configuración");
    setDefaultCloseOperation(1);
    setBounds(100, 100, 450, 298);
    this.contentPane = new JPanel();
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.contentPane);
    this.contentPane.setLayout(null);
    
    this.btnSave = new JButton("Guardar");
    this.btnSave.setBounds(353, 225, 71, 23);
    this.btnSave.addActionListener(this);
    this.contentPane.add(this.btnSave);
    
    JLabel lblArchivoDeConfiguracin = new JLabel("Archivo de Configuración");
    lblArchivoDeConfiguracin.setBounds(10, 11, 127, 14);
    this.contentPane.add(lblArchivoDeConfiguracin);
    
    this.txtConfigFile = new JTextField();
    this.txtConfigFile.setBounds(147, 8, 196, 20);
    this.contentPane.add(this.txtConfigFile);
    this.txtConfigFile.setColumns(10);
    
    this.listModel = new DefaultListModel();
    
    this.scrollPane = new JScrollPane();
    this.scrollPane.setBounds(10, 61, 414, 80);
    this.contentPane.add(this.scrollPane);
    this.list = new JList(this.listModel);
    this.scrollPane.setViewportView(this.list);
    this.list.addListSelectionListener(this);
    this.list.setSelectionMode(0);
    
    JLabel lblNewLabel = new JLabel("Valores Configuración");
    lblNewLabel.setBounds(10, 36, 127, 14);
    this.contentPane.add(lblNewLabel);
    
    this.txtEditKey = new JTextField();
    this.txtEditKey.setBounds(10, 192, 333, 20);
    this.contentPane.add(this.txtEditKey);
    this.txtEditKey.setColumns(10);
    
    this.btnAsign = new JButton("Asignar");
    this.btnAsign.addActionListener(this);
    this.btnAsign.setBounds(353, 191, 71, 23);
    this.btnAsign.addActionListener(this);
    this.contentPane.add(this.btnAsign);
    
    this.btnOpen = new JButton("Abrir");
    this.btnOpen.setBounds(353, 7, 71, 23);
    this.btnOpen.addActionListener(this);
    this.contentPane.add(this.btnOpen);
    
    this.lblKey = new JLabel();
    this.lblKey.setBounds(75, 167, 268, 14);
    this.contentPane.add(this.lblKey);
    
    this.lblEditarValor = new JLabel("Editar valor:");
    this.lblEditarValor.setBounds(10, 167, 71, 14);
    this.contentPane.add(this.lblEditarValor);
    
    setVisible(true);
  }
  
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == this.btnOpen) {
      this.propFile = GetFileUtil.open(new FileNameExtensionFilter("Properties Files", new String[] { "properties" }), this);
      if (this.propFile != null) {
        this.txtConfigFile.setText(this.propFile.getAbsolutePath());
        loadPropertiesFile();
        loadPropertiesList();
      }
    }
    else if (e.getSource() == this.btnAsign) {
      this.prop.setProperty(this.lblKey.getText(), this.txtEditKey.getText());
      this.lblKey.setText("");
      this.txtEditKey.setText("");
      loadPropertiesList();
    }
    else if (e.getSource() == this.btnSave) {
      savePropertiesFile();
      dispose();
    }
  }
  
  @SuppressWarnings("unchecked")
	private void loadPropertiesList() {
    this.list.clearSelection();
    this.listModel.clear();
    try
    {
      Enumeration<String> pNames = (Enumeration<String>) this.prop.propertyNames();
      while (pNames.hasMoreElements()) {
        String key = (String)pNames.nextElement();
        String value = this.prop.getProperty(key);
        
        this.listModel.addElement(key + "= " + value);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private void loadPropertiesFile() {
    this.prop = (new PropertiesLoader(this.propFile.getName())).getProperties();
  }
  
  private void savePropertiesFile() {
    try {
      FileOutputStream fileOut = new FileOutputStream(this.propFile);
      this.prop.store(fileOut, "General Configuration");
      fileOut.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public void valueChanged(ListSelectionEvent e) {
    String wrkSel = (String)this.list.getSelectedValue();
    if (wrkSel != null) {
      String wrkKey = wrkSel.substring(0, wrkSel.indexOf('=')).trim();
      String wrkVal = wrkSel.substring(wrkSel.indexOf('=') + 1).trim();
      
      this.lblKey.setText(wrkKey);
      this.txtEditKey.setText(wrkVal);
    }
  }
}