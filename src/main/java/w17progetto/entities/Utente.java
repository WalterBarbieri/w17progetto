package w17progetto.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Utenti")
@Getter
@Setter
@NoArgsConstructor
public class Utente {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private String userName;
	private String nomeCompleto;
	private String email;
	@OneToMany(mappedBy = "utente")
	private List<Prenotazione> prenotazione;

	public Utente(String userName, String nomeCompleto, String email) {
		this.setUserName(userName);
		this.setNomeCompleto(nomeCompleto);
		this.setEmail(email);
	}

	@Override
	public String toString() {
		return "Utente [Id: " + id + ", UserName: " + userName + ", Nome Completo: " + nomeCompleto + ", Email: "
				+ email + "]";
	}

}
