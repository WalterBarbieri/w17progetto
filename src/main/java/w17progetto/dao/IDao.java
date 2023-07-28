package w17progetto.dao;

import w17progetto.entities.Edificio;
import w17progetto.entities.Postazione;
import w17progetto.entities.Utente;

public interface IDao {
	public void save(Edificio edificio);

	public void save(Utente utente);

	public void save(Postazione postazione);
}
