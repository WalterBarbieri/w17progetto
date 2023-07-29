package w17progetto.dao.repositories;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import w17progetto.entities.Postazione;
import w17progetto.entities.Prenotazione;
import w17progetto.entities.Utente;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {
	Optional<Prenotazione> findByGiornoPrenotazioneAndPostazione(LocalDate giornoPrenotazione, Postazione postazione);

	Optional<Prenotazione> findByGiornoPrenotazioneAndUtente(LocalDate giornoPrenotazione, Utente utente);

}
