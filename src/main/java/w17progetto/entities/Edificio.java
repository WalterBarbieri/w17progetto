package w17progetto.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "edifici")
@Getter
@Setter
@NoArgsConstructor
public class Edificio {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String nome;
	private String indirizzo;
	private String citta;
	@OneToOne
	@JoinColumn(name = "postazione_id")
	private Postazione postazione;

	public Edificio(String nome, String indirizzo, String citta) {
		this.setNome(nome);
		this.setIndirizzo(indirizzo);
		this.setCitta(citta);
	}

	@Override
	public String toString() {
		return "Edificio [Nome: " + nome + ", Indirizzo: " + indirizzo + ", Città: " + citta + "]";
	}

}
