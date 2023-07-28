package w17progetto.runner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import w17progetto.dao.DaoService;
import w17progetto.entities.Edificio;
import w17progetto.entities.Postazione;
import w17progetto.entities.TipoPostazione;
import w17progetto.entities.Utente;

@Component
@Slf4j
public class GestionePrenotazioneRunner implements CommandLineRunner {

	@Autowired
	private ApplicationContext ctx;

	@Autowired
	private DaoService ds;

	@Override
	public void run(String... args) throws Exception {

		Random rnd = new Random();

		// ISTANZIO E SALVO RANDOM EDIFICI
		List<Edificio> edifici = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			Edificio edificio = (Edificio) ctx.getBean("rndEdificio");
			log.info(edificio.toString());
			edifici.add(edificio);
			ds.save(edificio);
		}

		// ISTANZIO E SALVO RANDOM POSTAZIONI
		for (Edificio edificio : edifici) {
			Postazione postazione = (Postazione) ctx.getBean("rndPostazione", edificio);
			log.info(postazione.toString());
			ds.save(postazione);
			ds.save(edificio);
		}

		// ISTANZIO E SALVO RANDOM UTENTI
		for (int i = 0; i < 50; i++) {
			Utente utente = (Utente) ctx.getBean("rndUtente");
			log.info(utente.toString());
			ds.save(utente);
		}

		// ISTANZIO E SALVO RANDOM PRENOTAZIONI
		for (int i = 0; i < 200; i++) {
			int rndInt = rnd.nextInt(7) + 1;
			ds.rndPrenotazione(LocalDate.now().plusDays(rndInt));
		}

		// RICERCA RANDOM POSTAZIONE TRAMITE TIPO POSTAZIONE E CITTA'

		for (int i = 0; i < 100; i++) {
			ds.searchRandomized();
		}
		// RICERCA MANUALE POSTAZIONE TRAMITE TIPO POSTAZIONE E CITTA'
		ds.searchPostazioneAndCitta(TipoPostazione.OPENSPACE, "Lubowitzstad").forEach(el -> log.info(el.toString()));

	}

}
