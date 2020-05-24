package xyz.freddy.evaluacion.serviceImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import xyz.freddy.evaluacion.domain.Poema;
import xyz.freddy.evaluacion.service.impl.ApiPoemaServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ApiPoemaServiceImplTest {

  ApiPoemaServiceImpl apiPoema;
  
  @Mock
  private RestTemplate restTemplate;
  
  Poema[] poemas=new Poema[2];
  
  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    Poema poema1=new Poema();
    poema1.setContent("content 1");
    Poema poema2=new Poema();
    poema2.setContent("content 2");
    poemas[0]=poema1;
    poemas[1]=poema2;
    //Mockito.when(restTemplate.getForObject(any(String.class), Mockito.eq(Poema[].class))).thenReturn(poemas);
    apiPoema=new ApiPoemaServiceImpl();
    
  }
  
  @Test
  public void poemaRandom() {
    //Action
    Poema result=this.apiPoema.getPoemRandom();
    //Assert
    assertThat(result.getContent()).isNotBlank();
    
  }
  
}
