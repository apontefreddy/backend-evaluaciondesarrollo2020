package xyz.freddy.evaluacion.serviceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

import xyz.freddy.evaluacion.domain.Persona;
import xyz.freddy.evaluacion.domain.Poema;
import xyz.freddy.evaluacion.repository.PersonaRepository;
import xyz.freddy.evaluacion.service.ApiPoemaService;
import xyz.freddy.evaluacion.service.impl.PersonaServiceImpl;
import xyz.freddy.evaluacion.util.Util;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PersonaServiceImplTest {
  
  @Mock
  ApiPoemaService apiPoemaService;
  @Mock
  PersonaRepository personaRepository;
  @Mock
  Util util;
  
  Persona persona;
  Poema poema;
  
  @Rule
  public ExpectedException thrown = ExpectedException.none();
  
  PersonaServiceImpl personaService;
  
  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    personaService=new PersonaServiceImpl(apiPoemaService, personaRepository, util);
    Calendar calend = new GregorianCalendar();
    calend.set(1990, 10, 14);
    persona = new Persona();
    persona.setApellido("Aponte");
    persona.setNombre("Freddy");
    persona.setFechaNac(calend.getTime());
    persona.setFechaNacString("1990-11-14");
    poema=new Poema();
    poema.setContent("contenido");
  }
  
  @Test
  public void savePersona_itsBirthday_success() throws Exception {
    //prepare
    Calendar fechaExcepted = new GregorianCalendar();
    fechaExcepted.set(1990, 10, 14);
    int edadExpected=30;
    String fechaStringExpected="1990-11-14";
    
    Mockito.when(util.convertStringToDate(any(String.class))).thenReturn(fechaExcepted.getTime());
    Mockito.when(util.calcularEdad(any(Date.class))).thenReturn(edadExpected);
    Mockito.when(util.ordenarFecha(any(String.class))).thenReturn(fechaStringExpected);
    Mockito.when(util.estaCumpliendoAnnos(any(Date.class))).thenReturn(true);
    Mockito.when(apiPoemaService.getPoemRandom()).thenReturn(poema);
    Mockito.when(personaRepository.save(any(Persona.class))).thenReturn(persona);
    //Action
    Persona result=this.personaService.savePersona(persona);
    //Assert
    assertThat(result.getApellido()).isNotNull();
  }
  
  @Test
  public void savePersona_itsNotBirthday_success() throws Exception {
    //prepare
    Calendar fechaExcepted = new GregorianCalendar();
    fechaExcepted.set(1990, 10, 14);
    int edadExpected=30;
    String fechaStringExpected="1990-11-14";
    
    Mockito.when(util.convertStringToDate(any(String.class))).thenReturn(fechaExcepted.getTime());
    Mockito.when(util.calcularEdad(any(Date.class))).thenReturn(edadExpected);
    Mockito.when(util.ordenarFecha(any(String.class))).thenReturn(fechaStringExpected);
    Mockito.when(util.estaCumpliendoAnnos(any(Date.class))).thenReturn(false);
    Mockito.when(util.diasRestantes(any(Date.class))).thenReturn(15);
    Mockito.when(personaRepository.save(any(Persona.class))).thenReturn(persona);
    
    //Action
    Persona result=this.personaService.savePersona(persona);
    //Assert
    assertThat(result.getApellido()).isNotNull();
  }
  
  @Test
  public void savePersona_Exception() throws Exception {
    //Prepare
    ParseException excepExpected=new ParseException("error", 0);
    Mockito.when(util.convertStringToDate(any(String.class))).thenThrow(excepExpected);
    //Assert
    thrown.expect(RuntimeException.class);
    this.personaService.savePersona(persona);
  }
  
  @Test
  public void allPersona_success() {
    //prepare
    List<Persona> listap = new ArrayList<>();
    listap.add(persona);
    //Action
    List<Persona> result=this.personaService.allPersonas();
    //Assert
    assertThat(result.size()).isNotNull();
    
  }
  

}
