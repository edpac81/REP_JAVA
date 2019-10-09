package com.util;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class GetFileUtility
{
  public GetFileUtility() {}
  
  public static File open(FileFilter filter, Component caller, String folder)
  {
  	String path = folder != null ? ".\\"+folder : ".";
    JFileChooser fc = new JFileChooser();
    File wf = null;
    fc.setCurrentDirectory(new File(path));
    fc.setFileSelectionMode(0);
    fc.addChoosableFileFilter(filter);
    fc.setAcceptAllFileFilterUsed(false);
    fc.setFileFilter(filter);
    int rv = fc.showOpenDialog(caller);
    if (rv == 0) {
      wf = fc.getSelectedFile();
    }
    return wf;
  }
}