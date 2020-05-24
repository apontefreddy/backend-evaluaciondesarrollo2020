package xyz.freddy.evaluacion.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import xyz.freddy.evaluacion.domain.Persona;
import xyz.freddy.evaluacion.service.PersonaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonaControllerTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  protected MockMvc mvc;
  @Mock
  PersonaService personaService;
  @InjectMocks
  PersonaController controller;

  Persona persona;
  private static final long OK = 200;
  private static final long CREATED = 201;
  private static final long ERROR = 400;

  protected String mapToJsonPerson(Persona obj) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(obj);
  }

  protected <T> T mapFromJson(String json, Class<T> clazz) throws IOException {

    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(json, clazz);
  }

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    controller = new PersonaController(personaService);

    mvc = MockMvcBuilders.standaloneSetup(this.controller).build();
    Calendar calend = new GregorianCalendar();
    calend.set(1990, 10, 14);
    persona = new Persona();
    persona.setApellido("Aponte");
    persona.setNombre("Freddy");
    persona.setFechaNac(calend.getTime());
    persona.setFechaNacString("1990-11-14");

  }

  @Test
  public void savePersona() throws Exception {

    // prepare
    String inputJson = this.mapToJsonPerson(persona);

    Mockito.when(personaService.savePersona(any(Persona.class))).thenReturn(persona);
    String uri = "/api/persona";
    // Action
    MvcResult mvcResult = mvc
        .perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
        .andReturn();
    // Assert
    int status = mvcResult.getResponse().getStatus();
    assertEquals("CREATED", CREATED, status);
  }

  @Test
  public void obtenerlistaPersonas() throws Exception {

    // prepare
    List<Persona> listap = new ArrayList<>();
    listap.add(persona);

    Mockito.when(personaService.allPersonas()).thenReturn(listap);
    String uri = "/api/listapersonas";
    // Action
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
        .andReturn();

    // Assert
    int status = mvcResult.getResponse().getStatus();
    assertEquals("OK", OK, status);
  }

  @Test
  public void savePersona_exception() throws Exception {
    //prepare
    String inputJson = this.mapToJsonPerson(persona);
    Exception exceptionExpected=new Exception("error");

    Mockito.when(personaService.savePersona(any(Persona.class))).thenThrow(exceptionExpected);
    String uri = "/api/persona";
    // Action
    MvcResult mvcResult = mvc
        .perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
        .andReturn();
    // Assert
    int status = mvcResult.getResponse().getStatus();
    assertEquals("ERROR", ERROR, status);
  }
  
  @Test
  public void obtenerlistaPersonas_exception() throws Exception {
    //prepare
    RuntimeException exceptionExpected=new RuntimeException("error");

    Mockito.when(personaService.allPersonas()).thenThrow(exceptionExpected);
    String uri = "/api/listapersonas";
    // Action
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
        .andReturn();
    // Assert
    int status = mvcResult.getResponse().getStatus();
    assertEquals("ERROR", ERROR, status);
  }

}
