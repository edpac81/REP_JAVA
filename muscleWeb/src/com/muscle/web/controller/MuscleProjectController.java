package com.muscle.web.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import com.muscle.web.ejb.MuscleProjectFacadeLocal;
import com.muscle.web.model.MuscleProject;

@ManagedBean
public class MuscleProjectController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private MuscleProject muscleProject = new MuscleProject();
	
	@EJB
	private MuscleProjectFacadeLocal muscleProjectEJB;
	
	/*
	@PostConstruct
	public void init(){
		muscleProject = new MuscleProject();
		System.out.println("muscleProject created. "+muscleProject.toString());
	}*/
	
	public void insertProject(MuscleProject mp){
		try{
			System.out.println("insert()... "+mp.toString());
			System.out.println("Interface..."+muscleProjectEJB.toString());
			muscleProjectEJB.create(mp);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public MuscleProject getMuscleProject() {
		return muscleProject;
	}

	public void setMuscleProject(MuscleProject muscleProject) {
		this.muscleProject = muscleProject;
	}
	
	
}
