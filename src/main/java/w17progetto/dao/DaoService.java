package w17progetto.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import w17progetto.dao.repositories.EdificioRepository;
import w17progetto.dao.repositories.PostazioneRepository;
import w17progetto.dao.repositories.PrenotazioneRepository;
import w17progetto.dao.repositories.UtenteRepository;
import w17progetto.entities.Edificio;
import w17progetto.entities.Postazione;
import w17progetto.entities.Prenotazione;
import w17progetto.entities.Utente;

@Service
@Slf4j
public class DaoService implements IDao {

	Random rnd = new Random();

	@Autowired
	private EdificioRepository er;

	@Autowired
	private UtenteRepository ur;

	@Autowired
	private PostazioneRepository pr;

	@Autowired
	private PrenotazioneRepository prr;

	@Override
	public void save(Edificio edificio) {
		er.save(edificio);
		log.info("Edificio " + edificio.getNome() + " inserito correttamente!");

	}

	@Override
	public void save(Utente utente) {
		ur.save(utente);
		log.info("Utente " + utente.getNomeCompleto() + " inserito correttamente!");

	}

	@Override
	public void save(Postazione postazione) {
		pr.save(postazione);
		log.info("Postazione " + postazione.getId() + " inserita correttamente!");

	}

	@Override
	public void save(Prenotazione prenotazione) {
		prr.save(prenotazione);
		log.info("Prenotazione " + prenotazione.getId() + " inserita correttamente!");

	}

	// METODI PER PESCAGGIO ISTANZE RANDOM

	public Utente rndUtente() {
		List<Utente> utenti = ur.findAll();
		if (utenti.isEmpty()) {
			return null;
		}
		return utenti.get(rnd.nextInt(utenti.size()));
	}

	public Postazione rndPostazione() {
		List<Postazione> postazioni = pr.findAll();
		if (postazioni.isEmpty()) {
			return null;
		}
		return postazioni.get(rnd.nextInt(postazioni.size()));
	}

	// METODI PER CONTROLLO E CREAZIONE PRENOTAZIONE

	public Prenotazione prenotazione(LocalDate giornoPrenotazione, Postazione postazione, Utente utente) {
		if ((postazione.getPrenotazione() == null || postazione.getPrenotazione().isEmpty())
				&& (utente.getPrenotazione() == null || utente.getPrenotazione().isEmpty())) {
			Prenotazione prenotazione = new Prenotazione(giornoPrenotazione, postazione, utente);
			prr.save(prenotazione);
			return prenotazione;
		}

		if (postazioneDisponibile(giornoPrenotazione, postazione) && utenteDisponibile(giornoPrenotazione, utente)) {
			Prenotazione prenotazione = new Prenotazione(giornoPrenotazione, postazione, utente);
			prr.save(prenotazione);
			return prenotazione;
		} else {
			return null;
		}
	}

	public boolean postazioneDisponibile(LocalDate giornoPrenotazione, Postazione postazione) {
		return prr.findByGiornoPrenotazioneAndPostazione(giornoPrenotazione, postazione) == null;
	}

	public boolean utenteDisponibile(LocalDate giornoPrenotazione, Utente utente) {
		return prr.findByGiornoPrenotazioneAndUtente(giornoPrenotazione, utente) == null;
	}

	public void rndPrenotazione(LocalDate giornoPrenotazione) {
		Utente rndUtente = rndUtente();
		Postazione rndPostazione = rndPostazione();

		try {
			Prenotazione prenotazione = prenotazione(giornoPrenotazione, rndPostazione, rndUtente);
			log.info("Prenotazione " + prenotazione.getId() + " inserita correttamente");
		} catch (Exception e) {
			log.info("Prenotazione non riuscita");
		}

	}

//	// METODO PER RICERCA BY TIPO POSTAZIONE E CITTA'
//
//	public List<Postazione> searchPostazioneAndCitta(TipoPostazione tipoPostazione, String citta) {
//		return pr.findAllByTipoPostazioneAndCittaAndPrenotazioneIsNull(tipoPostazione, citta);
//	}
}
