package w17progetto.runner;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import w17progetto.dao.DaoService;

@Component
@Slf4j
public class GestionePrenotazioneRunner implements CommandLineRunner {

	@Autowired
	private ApplicationContext ctx;

	@Autowired
	private DaoService ds;

	@Override
	public void run(String... args) throws Exception {

//		// ISTANZIO E SALVO RANDOM EDIFICI
//		List<Edificio> edifici = new ArrayList<>();
//		for (int i = 0; i < 20; i++) {
//			Edificio edificio = (Edificio) ctx.getBean("rndEdificio");
//			log.info(edificio.toString());
//			edifici.add(edificio);
//			ds.save(edificio);
//		}
//
//		// ISTANZIO E SALVO RANDOM EDIFICI
//		for (Edificio edificio : edifici) {
//			Postazione postazione = (Postazione) ctx.getBean("rndPostazione", edificio);
//			log.info(postazione.toString());
//			ds.save(postazione);
//			ds.save(edificio);
//		}
//
//		// ISTANZIO E SALVO RANDOM UTENTI
//		for (int i = 0; i < 50; i++) {
//			Utente utente = (Utente) ctx.getBean("rndUtente");
//			log.info(utente.toString());
//			ds.save(utente);
//		}

		// ISTANZIO E SALVO RANDOM PRENOTAZIONI
		for (int i = 0; i < 200; i++) {
			ds.rndPrenotazione(LocalDate.now());
		}
	}

}
