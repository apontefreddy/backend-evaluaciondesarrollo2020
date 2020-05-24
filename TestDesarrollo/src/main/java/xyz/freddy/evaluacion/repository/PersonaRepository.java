package xyz.freddy.evaluacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xyz.freddy.evaluacion.domain.Persona;
@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

  Persona findByNombre(String nombre);
  List<Persona> findAll();
}
