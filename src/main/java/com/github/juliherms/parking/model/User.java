package com.github.juliherms.parking.model;

public class User {

	private String id;
	private int codigo;
	private String login;
	private String name;

	public User(String id, int codigo, String login, String name) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.login = login;
		this.name = name;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
