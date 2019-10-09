package com.tester.fxmodel;

import java.io.File;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class ServiceList extends ArrayList<ServiceModel>{

	private String servicePath;
	private File serviceRoot;
	
	public ServiceList(){
		servicePath = ".\\services";
		loadServiceList();
	}

	private void loadServiceList() {
		File[] listDir;
		ServiceModel serviceModel;
		try{
			serviceRoot = new File(servicePath);
			if(serviceRoot != null){
				listDir = serviceRoot.listFiles();
				for(int i = 0; i < listDir.length; i++){
					if(listDir[i].isDirectory()){
						serviceModel = new ServiceModel(listDir[i].getName());
						this.add(serviceModel);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
