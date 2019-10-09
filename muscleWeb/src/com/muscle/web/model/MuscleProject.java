package com.muscle.web.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "muscleProject")
public class MuscleProject implements Serializable {

	private static final long serialVersionUID = -8618864977021556253L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int projectId;
	
	@Column(name="projectRelease")
	private String release;
	
	@Column(name="analist")
	private String analist;
	
	@Column(name="systemUser")
	private String systemUser;
	
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getRelease() {
		return release;
	}
	public void setRelease(String release) {
		this.release = release;
	}
	public String getAnalist() {
		return analist;
	}
	public void setAnalist(String analist) {
		this.analist = analist;
	}
	public String getSystemUser() {
		return systemUser;
	}
	public void setSystemUser(String systemUser) {
		this.systemUser = systemUser;
	}
	
	@Override
	public String toString() {
		return "MuscleProject [projectId=" + projectId + ", " + (release != null ? "release=" + release + ", " : "")
				+ (analist != null ? "analist=" + analist + ", " : "") + (systemUser != null ? "systemUser=" + systemUser : "")
				+ "]";
	}

}
