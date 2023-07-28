package w17progetto.dao.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import w17progetto.entities.Edificio;

@Repository
public interface EdificioRepository extends JpaRepository<Edificio, UUID> {

}
