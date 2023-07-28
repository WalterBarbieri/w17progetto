package w17progetto.beans;

import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.github.javafaker.Faker;

import w17progetto.entities.Edificio;
import w17progetto.entities.Postazione;
import w17progetto.entities.TipoPostazione;
import w17progetto.entities.Utente;

@Configuration
public class BeansConfig {
	Faker faker = new Faker();
	Random rnd = new Random();

	@Bean
	@Scope("prototype")
	Edificio rndEdificio() {
		String rndName = faker.lordOfTheRings().location();
		String rndIndirizzo = faker.address().streetAddress();
		String rndCitta = faker.address().cityName();
		Edificio rndEdificio = new Edificio(rndName, rndIndirizzo, rndCitta);
		return rndEdificio;
	}

	@Bean
	@Scope("prototype")
	Utente rndUtente() {
		String rndUsername = faker.animal().name();
		String rndNomeCompleto = faker.lordOfTheRings().character();
		String rndEmail = faker.internet().emailAddress();
		Utente rndUtente = new Utente(rndUsername, rndNomeCompleto, rndEmail);
		return rndUtente;
	}

	@Bean
	@Scope("prototype")
	Postazione rndPostazione(Edificio edificio) {
		String rndDescrizione = faker.lorem().sentence();
		TipoPostazione rndTipoPostazione = TipoPostazione.values()[rnd.nextInt(TipoPostazione.values().length)];
		int rndNumeroMassimo = rnd.nextInt(20) + 1;
		Postazione rndPostazione = new Postazione(rndDescrizione, rndTipoPostazione, rndNumeroMassimo, edificio);
		return rndPostazione;

	}

}
