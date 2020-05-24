package xyz.freddy.evaluacion.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import xyz.freddy.evaluacion.domain.Persona;
import xyz.freddy.evaluacion.repository.PersonaRepository;
import xyz.freddy.evaluacion.service.ApiPoemaService;
import xyz.freddy.evaluacion.service.PersonaService;
import xyz.freddy.evaluacion.util.Util;

@Service
public class PersonaServiceImpl implements PersonaService {

  private ApiPoemaService apiPoemaService;
  private PersonaRepository personaRepository;
  private Util util;
  private List<Persona> personas;

  @Autowired
  public PersonaServiceImpl(ApiPoemaService apiPoemaService, PersonaRepository personaRepository, Util util) {
    this.apiPoemaService = apiPoemaService;
    this.personaRepository=personaRepository;
    this.util=util;
    personas=new ArrayList<Persona>();
  }

  @Override
  public Persona savePersona(Persona persona) throws Exception {
    Persona nuevaPersona=new Persona();
    try {
    
    Date FechaNacimiento=util.convertStringToDate(persona.getFechaNacString());
    
    persona.setEdad(util.calcularEdad(FechaNacimiento));
    persona.setFechaNacString(util.ordenarFecha(persona.getFechaNacString()));
    
    String mensaje ="";
    if (util.estaCumpliendoAnnos(FechaNacimiento)) {
      mensaje = "Estas Cumpliendo Años!! Felicidades!!\n te dedico este poema:\n  ";
      mensaje = mensaje + this.apiPoemaService.getPoemRandom().getContent();
      
    } else {
      mensaje="Faltan "+util.diasRestantes(FechaNacimiento)+" Dias para tu cumpleaños";

    }
    persona.setMensaje(mensaje);
    nuevaPersona=this.personaRepository.save(persona);
    personas.add(nuevaPersona);
    }catch(Exception ex) {
      throw new RuntimeException("Error in savePersona method");
    }
    return nuevaPersona;
  }

  @Override
  public List<Persona> allPersonas() {
    //return this.personaRepository.findAll(sortByIdDesc());
    return util.ordenarPersonas(personas);
  }
  
  private Sort sortByIdDesc() {
    return  Sort.by(Direction.DESC, "id");
}

  

}
