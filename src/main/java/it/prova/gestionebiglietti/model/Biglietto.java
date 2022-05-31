package it.prova.gestionebiglietti.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "biglietto")
public class Biglietto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "descrizione")
	private String descrizione;
	@Column(name = "destinazione")
	private String destinazione;
	@Column(name = "provenienza")
	private String provenienza;
	@Column(name = "prezzo")
	private int prezzo;
	@Column(name = "data")
	private Date data;

	// campi per le time info del record
	@CreationTimestamp
	private LocalDateTime createDateTime;
	@UpdateTimestamp
	private LocalDateTime updateDateTime;

	public Biglietto() {

	}

	public Biglietto(Long id, String descrizione, String destinazione, String provenienza, int prezzo, Date data,
			LocalDateTime createDateTime, LocalDateTime updateDateTime) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.destinazione = destinazione;
		this.provenienza = provenienza;
		this.prezzo = prezzo;
		this.data = data;
		this.createDateTime = createDateTime;
		this.updateDateTime = updateDateTime;
	}

	public Biglietto(String descrizione, String destinazione, String provenienza, int prezzo, Date data,
			LocalDateTime createDateTime, LocalDateTime updateDateTime) {
		super();
		this.descrizione = descrizione;
		this.destinazione = destinazione;
		this.provenienza = provenienza;
		this.prezzo = prezzo;
		this.data = data;
		this.createDateTime = createDateTime;
		this.updateDateTime = updateDateTime;
	}

	public Biglietto(String descrizione, String destinazione, String provenienza, int prezzo, Date data) {
		super();
		this.descrizione = descrizione;
		this.destinazione = destinazione;
		this.provenienza = provenienza;
		this.prezzo = prezzo;
		this.data = data;
	}

	public Biglietto(String descrizione, String destinazione, String provenienza) {
		super();
		this.descrizione = descrizione;
		this.destinazione = destinazione;
		this.provenienza = provenienza;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDestinazione() {
		return destinazione;
	}

	public void setDestinazione(String destinazione) {
		this.destinazione = destinazione;
	}

	public String getProvenienza() {
		return provenienza;
	}

	public void setProvenienza(String provenienza) {
		this.provenienza = provenienza;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

}
