package com.muscle.web.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.muscle.web.model.MuscleProject;

@Stateless
public class MuscleProjectFacade 
	//extends AbstractFacade<MuscleProject> 
	implements MuscleProjectFacadeLocal {

	@PersistenceContext(unitName = "testDS")
	//@PersistenceContext(name = "muscleWeb-unit")
	private EntityManager em;
/*
	@Override
	protected EntityManager getEntityManager() { 
		return em ;
	}

	public MuscleProjectFacade(){
		super(MuscleProject.class);
	}
*/

	@Override
	public void create(MuscleProject muscleProject) {
		em.persist(muscleProject);
		
	}

}
