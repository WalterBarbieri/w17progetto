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
import w17progetto.entities.TipoPostazione;
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

	public String rndCitta() {
		List<Edificio> edifici = er.findAll();
		if (edifici.isEmpty()) {
			return null;
		}
		return edifici.get(rnd.nextInt(edifici.size())).getCitta();
	}

	// METODI PER CONTROLLO E CREAZIONE PRENOTAZIONE

	// METODI DI CONTROLLO

	// ******PRIMA ITERAZIONE: QUESTI DUE METODI DI CONTROLLO USATI ASSIEME PER
	// DETERMINARE
	// SE L'UTENTE E LA POSTAZIONE FOSSERO LIBERI ****
//	public boolean postazioneDisponibile(LocalDate giornoPrenotazione, Postazione postazione) {
//		return prr.findByGiornoPrenotazioneAndPostazione(giornoPrenotazione, postazione) == null;
//	}
//
//	public boolean utenteDisponibile(LocalDate giornoPrenotazione, Utente utente) {
//		return prr.findByGiornoPrenotazioneAndUtente(giornoPrenotazione, utente) == null;
//	}
	// *****SOSTITUITO DA QUESTO MA PERMANE PROBLEMA, PERO' FUNZIONA UGUALE ED E'
	// PIU' SINTETICO

	public boolean utenteEPostazioneDisponibile(LocalDate giornoPrenotazione, Postazione postazione, Utente utente) {
		return prr.findByGiornoPrenotazioneAndPostazioneAndUtente(giornoPrenotazione, postazione, utente) == null;
	}

	// METODO MANUALE **AGGIUNTO UPDATE DI POSTAZIONE E UTENTE ALLA CREAZIONE DI
	// PRENOTAZIONE, MA PERMANE IL PROBLEMA
	public Prenotazione prenotazione(LocalDate giornoPrenotazione, Postazione postazione, Utente utente) {
		if ((postazione.getPrenotazione() == null || postazione.getPrenotazione().isEmpty())
				&& (utente.getPrenotazione() == null || utente.getPrenotazione().isEmpty())) {
			Prenotazione prenotazione = new Prenotazione(giornoPrenotazione, postazione, utente);
			postazione.getPrenotazione().add(prenotazione);
			utente.getPrenotazione().add(prenotazione);
			prr.save(prenotazione);
			pr.save(postazione);
			ur.save(utente);
			return prenotazione;
		}
		// ****** QUA ERANO RICHIAMATI I DUE METODI IN QUESTO MODO:
		// if(postazioneDisponibile(giornoPrenotazione, postazione) &&
		// utenteDisponibile(giornoPrenotazione, utente)) {} ****
		if (utenteEPostazioneDisponibile(giornoPrenotazione, postazione, utente)) {
			Prenotazione prenotazione = new Prenotazione(giornoPrenotazione, postazione, utente);
			postazione.getPrenotazione().add(prenotazione);
			utente.getPrenotazione().add(prenotazione);
			prr.save(prenotazione);
			pr.save(postazione);
			ur.save(utente);
			return prenotazione;
		} else {
			return null;
		}
	}

	// METODO RANDOM
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

	// METODO PER RICERCA BY TIPO POSTAZIONE E CITTA'
	// METODO MANUALE
	public List<Postazione> searchPostazioneAndCitta(TipoPostazione tipoPostazione, String citta) {
		return pr.findAllByTipoPostazioneAndEdificio_Citta(tipoPostazione, citta);
	}

	// METODO RANDOM
	public void searchRandomized() {
		TipoPostazione rndTipoPostazione = TipoPostazione.values()[rnd.nextInt(TipoPostazione.values().length)];
		String rndCitta = rndCitta();
		try {
			List<Postazione> trovati = searchPostazioneAndCitta(rndTipoPostazione, rndCitta);
			if (trovati.isEmpty()) {
				log.info("Nessun elemento corrisponde alla ricerca effettuata per " + rndTipoPostazione + " e "
						+ rndCitta);
			} else {
				log.info("Risultati trovati per " + rndTipoPostazione + " e " + rndCitta);
				log.info(trovati.toString());
			}

		} catch (Exception e) {
			log.info("Errore nella ricerca");
		}
	}
}
