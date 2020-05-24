package xyz.freddy.evaluacion.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UtilTest {
  
  Util util;
  
  String fechaString;
  Date dateCumple;
  
  @Before
  public void init() {
    fechaString="1990-11-14";
    Calendar calend = new GregorianCalendar();
    calend.set(1990, 10, 14);
    dateCumple=calend.getTime();
    util=new Util();
  }
  
  @Test
  public void convertStringToDate() throws ParseException {
    //Action
    Date result=this.util.convertStringToDate(fechaString);
    //Assert
    assertThat(result.toString()).isNotBlank();
  }
  
  @Test
  public void ordenarFecha() throws ParseException {
    //Action
    String result=this.util.ordenarFecha(fechaString);
    //Assert
    assertThat(result).isNotBlank();
  }
  
  @Test
  public void calcularEdad() {
    //Action
    int result=this.util.calcularEdad(dateCumple);
    //Assert
    assertThat(result).isNotZero();
    
  }
  
  @Test
  public void NoEsSuCumple() {
    //Action
    boolean result=this.util.estaCumpliendoAnnos(dateCumple);
    //Assert
    assertThat(result).isFalse();
  }
  
  @Test
  public void esSuCumple() {
    //prepare
    Calendar calend = Calendar.getInstance();
    Calendar birthday=new GregorianCalendar();
    birthday.set(1990, calend.get(Calendar.MONTH), calend.get(Calendar.DATE));
    
    //Action
    boolean result=this.util.estaCumpliendoAnnos(birthday.getTime());
    //Assert
    assertThat(result).isTrue();
  }
  
  @Test
  public void diasRestantes() {
    //Action
    int result=this.util.diasRestantes(dateCumple);
    //Assert
    assertThat(result).isNotZero();
  }
  
  @Test
  public void diasRestantes_CumpleYafueEsteAnno() {
    //Prepare
    Calendar birthday=new GregorianCalendar();
    birthday.set(1990, 0, 10);
    //Action
    int result=this.util.diasRestantes(birthday.getTime());
    //Assert
    assertThat(result).isNotZero();
  }
  
  @Test
  public void es29Feb() {
    //prepare
    Calendar birthday=new GregorianCalendar();
    birthday.set(2016, 01, 29);
    
    //Action
    boolean result=this.util.estaCumpliendoAnnos(birthday.getTime());
    //Assert
    assertThat(result).isFalse();
  }
  
  
  
  

}
