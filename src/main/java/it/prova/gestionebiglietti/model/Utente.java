package it.prova.gestionebiglietti.model;

public class Utente {
	private String username;
	private String password;
	private String nome;
	private String cognome;

	public Utente(String username, String password, String nome, String cognome) {
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
	}

}
