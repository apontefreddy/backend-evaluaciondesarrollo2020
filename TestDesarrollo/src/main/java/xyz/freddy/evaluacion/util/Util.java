package xyz.freddy.evaluacion.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.stereotype.Component;

import xyz.freddy.evaluacion.domain.Persona;

@Component
public class Util {
  
  public Util() {
    
  }
  
  public Date convertStringToDate(String fechaString) throws ParseException {
    Date date=new SimpleDateFormat("yyyy-MM-dd").parse(fechaString);
    return date;
  }
  
  public String ordenarFecha(String fechaString) {
    String[] fecha=fechaString.split("-");
    return fecha[2]+"-"+fecha[1]+"-"+fecha[0];
  }

  public int calcularEdad(Date fechaNac) {

    Calendar fechaNacim = new GregorianCalendar();
    fechaNacim.setTime(fechaNac);
    Calendar hoy = Calendar.getInstance();

    int diff_year = hoy.get(Calendar.YEAR) - fechaNacim.get(Calendar.YEAR);
    int diff_month = hoy.get(Calendar.MONTH) - fechaNacim.get(Calendar.MONTH);
    int diff_day = hoy.get(Calendar.DAY_OF_MONTH) - fechaNacim.get(Calendar.DAY_OF_MONTH);

    if (diff_month < 0 || (diff_month == 0 && diff_day < 0)) {
      diff_year = diff_year - 1;
    }
    return diff_year;
  }

  public boolean estaCumpliendoAnnos(Date fechaNac) {
    Calendar fechaNacim = new GregorianCalendar();
    fechaNacim.setTime(fechaNac);
    Calendar hoy = Calendar.getInstance();

    if (hoy.get(Calendar.DAY_OF_MONTH) == fechaNacim.get(Calendar.DAY_OF_MONTH)
        && hoy.get(Calendar.MONTH) == fechaNacim.get(Calendar.MONTH)) {
      return true;
    } else if (fechaNacim.get(Calendar.DAY_OF_MONTH) == 29 && fechaNacim.get(Calendar.MONTH) == 1) {
      // Si nacio un 29-Feb
      return this.suCumpleEs29Feb();
    } else {
      return false;
    }

  }

  public boolean suCumpleEs29Feb() {
    GregorianCalendar calendarioActual = new GregorianCalendar();
    Calendar hoy = Calendar.getInstance();

    // Si el anno actual es Bisiesto
    if (calendarioActual.isLeapYear(Calendar.getInstance().get(Calendar.YEAR))) {
      // verificar si hoy es 29-Feb
      if (hoy.get(Calendar.DAY_OF_MONTH) == 29 && hoy.get(Calendar.MONTH) == 1) {
        return true;
      } else {
        return false;
      }
      // No es bisiesto y hoy es 1ero de Marzo
    } else if (hoy.get(Calendar.DAY_OF_MONTH) == 1 && hoy.get(Calendar.MONTH) == 2) {
      return true;
    } else {
      return false;
    }
  }

  public int diasRestantes(Date fechaNac) {
    int dias = 0;
    Calendar fechaCumple=new GregorianCalendar();
    Calendar fechaActual=Calendar.getInstance();
    fechaCumple.setTime(fechaNac);
    fechaCumple.set(Calendar.YEAR, fechaActual.get(Calendar.YEAR));
    if(fechaCumple.before(fechaActual)) {
      fechaCumple.set(Calendar.YEAR, fechaActual.get(Calendar.YEAR)+1);
    }
    
    dias=(int) ((fechaCumple.getTime().getTime()-fechaActual.getTime().getTime())/86400000);
    
    return dias;
  }
  
  public List<Persona> ordenarPersonas(List<Persona> personas){
    
    List<Persona> personList=new ArrayList<Persona>();
    int size=personas.size();
    for(int i=size-1; i>=0;i--) {
      personList.add(personas.get(i));
    }
    return personList;
  }
  
}
