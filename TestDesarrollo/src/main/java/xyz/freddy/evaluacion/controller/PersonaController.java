package xyz.freddy.evaluacion.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.freddy.evaluacion.domain.Persona;
import xyz.freddy.evaluacion.service.PersonaService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PersonaController {

  private PersonaService personaService;

  @Autowired
  public PersonaController(PersonaService personaService) {
    this.personaService = personaService;
  }

  @PostMapping("/persona")
  public ResponseEntity<Persona> guardarPersona(@RequestBody Persona persona) {
    
    Persona nuevaPersona = new Persona();
    try {
      nuevaPersona=this.personaService.savePersona(persona);
    } catch (Exception ex) {
      return new ResponseEntity<Persona>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<Persona>(nuevaPersona, HttpStatus.CREATED);
  }
  
  @GetMapping("/listapersonas")
  public ResponseEntity<List<Persona>> getPersonas(){
    List<Persona> listaPersona=new ArrayList<>();
    try {
      listaPersona=this.personaService.allPersonas();
    }catch(Exception ex) {
      return new ResponseEntity<List<Persona>>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<List<Persona>>(listaPersona, HttpStatus.OK);
  }

}
