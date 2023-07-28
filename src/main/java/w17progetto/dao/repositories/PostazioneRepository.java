package w17progetto.dao.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import w17progetto.entities.Postazione;
import w17progetto.entities.TipoPostazione;

@Repository
public interface PostazioneRepository extends JpaRepository<Postazione, UUID> {
	List<Postazione> findAllByTipoPostazioneAndEdificio_Citta(TipoPostazione tipoPostazione, String citta);
}
