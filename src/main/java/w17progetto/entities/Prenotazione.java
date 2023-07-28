package w17progetto.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "prenotazioni")
@Getter
@Setter
@NoArgsConstructor
public class Prenotazione {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private LocalDate giornoPrenotazione;
	@ManyToOne
	@JoinColumn(name = "postazione_id")
	private Postazione postazione;
	@ManyToOne
	@JoinColumn(name = "username_id")
	private Utente utente;

	public Prenotazione(LocalDate giornoPrenotazione, Postazione postazione, Utente utente) {
		this.setGiornoPrenotazione(giornoPrenotazione);
		this.setPostazione(postazione);
		this.setUtente(utente);
	}

	@Override
	public String toString() {
		return "Prenotazione [Id: " + id + ", Data Prenotazione: " + giornoPrenotazione + ", Postazione"
				+ postazione.getTipoPostazione() + " in " + postazione.getEdificio().getCitta() + ", Utente: "
				+ utente.getNomeCompleto() + "]";
	}

}
