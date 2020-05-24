package xyz.freddy.evaluacion.service;

import java.util.List;

import xyz.freddy.evaluacion.domain.Persona;

public interface PersonaService {
  
  Persona savePersona(Persona persona) throws Exception;
  List<Persona> allPersonas();

}
