package w17progetto.dao.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import w17progetto.entities.Postazione;

@Repository
public interface PostazioneRepository extends JpaRepository<Postazione, UUID> {
//	List<Postazione> findAllByTipoPostazioneAndCittaAndPrenotazioneIsNull(TipoPostazione tipoPostazione, String citta);
}
