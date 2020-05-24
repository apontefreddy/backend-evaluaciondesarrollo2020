package xyz.freddy.evaluacion.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import xyz.freddy.evaluacion.domain.Poema;
import xyz.freddy.evaluacion.service.ApiPoemaService;

@Service
public class ApiPoemaServiceImpl implements ApiPoemaService {
  
  
  private String pathApiPoem="https://www.poemist.com/api/v1/randompoems";
  private Poema[] poemas;
  
  public ApiPoemaServiceImpl() {
    this.poemas=getPoemas();
  }

  @Override
  public Poema getPoemRandom() {
    int min=0;
    int max=this.poemas.length-1;
    int valorDado = (int) Math.floor(Math.random()*(max-min+1)+min);
    return this.poemas[valorDado];
  }
  
  private Poema[] getPoemas(){
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate.getForObject(pathApiPoem, Poema[].class);
   
    
  }

}
