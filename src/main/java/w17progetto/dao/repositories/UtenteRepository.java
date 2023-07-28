package w17progetto.dao.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import w17progetto.entities.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, UUID> {
	List<Utente> findAll();
}
