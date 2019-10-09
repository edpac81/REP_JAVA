package com.tester.utils;

import java.io.File;
import javax.swing.JFileChooser;

public class GetFileUtil
{
  public GetFileUtil() {}
  
  public static File open(javax.swing.filechooser.FileFilter filter, java.awt.Component caller){
    return open(filter, caller, "");
  }
  
  public static File open(javax.swing.filechooser.FileFilter filter, java.awt.Component caller, String folder){
    JFileChooser fc = new JFileChooser();
    File wf = null;
    fc.setCurrentDirectory(new File("."+folder));
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