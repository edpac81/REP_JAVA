package com.muscle.web.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import com.muscle.web.dao.LoginDAO;

@ManagedBean
@SessionScoped
public class Login implements Serializable{
	
	private static final long serialVersionUID = 1094801825228386363L;
	private String password;
	private String message;
	private String user;

	public String validateUsernamePassword() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message;
		
		if (LoginDAO.validate(user, password)) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user);
			return "projectList";
		} else {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Incorrect Username and Passowrd", "Please enter correct username and Password");
			context.addMessage(null, message);
			return "login";
		}
	}

	// logout event, invalidate session
	public String logout(ActionEvent actionEvent) {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
