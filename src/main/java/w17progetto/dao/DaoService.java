package w17progetto.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import w17progetto.dao.repositories.EdificioRepository;
import w17progetto.dao.repositories.PostazioneRepository;
import w17progetto.dao.repositories.UtenteRepository;
import w17progetto.entities.Edificio;
import w17progetto.entities.Postazione;
import w17progetto.entities.Utente;

@Service
@Slf4j
public class DaoService implements IDao {

	@Autowired
	private EdificioRepository er;

	@Autowired
	private UtenteRepository ur;

	@Autowired
	private PostazioneRepository pr;

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
		log.info("Postazione " + postazione.getId() + " inserito correttamente!");

	}

}
