package com.jsfcourse.params;

import java.io.Serializable;

import jakarta.faces.view.ViewScoped;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class ParamSenderBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String surname;
	private String param1;
	private String param2;
	
	@Inject
	FacesContext ctx;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	public String sendThroughRequest(){
		HttpServletRequest req = (HttpServletRequest) ctx.getExternalContext().getRequest();
		req.setAttribute("name", name + "(Req)");
		req.setAttribute("surname", surname + "(Req)");
		
		//metoda forward (redirect zniszczy obecny obiekt ządania)
		return "info";
	}

	public String sendThroughSession(){
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
		session.setAttribute("name", name + "(Ses)");
		session.setAttribute("surname", surname + "(Ses)");
		
		//metoda redirect (parametry zapisane w sesji pozostann do konca trwania sesji lub do ich usuniecia)
		return "info?faces-redirect=true";
	}
	
	public String setCookies(){
		HttpServletResponse resp = (HttpServletResponse) ctx.getExternalContext().getResponse();
		
		Cookie c1 = new Cookie("ciacho1", "krakers");
		c1.setMaxAge(60*60*24*365); //czas zycia w sekundach, domyślnie -1 (sesja przeglądarki)
		//ustawienie domeny jakiej ciastko dotyczy
		//c1.setDomain("domena.pl");
		//ustawienie scieżki jakiej ciastko dotyczy
		//c1.setDomain("/folder/podfolder/");
		Cookie c2 = new Cookie("ciacho2", "oreo");
		c2.setMaxAge(30);
		Cookie c3 = new Cookie("ciacho3", "makowiec");
		c3.setMaxAge(60);
		
		resp.addCookie(c1);
		resp.addCookie(c2);
		resp.addCookie(c3);
		
		return null;
	}
}
