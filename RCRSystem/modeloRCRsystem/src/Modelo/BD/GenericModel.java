package Modelo.BD;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class GenericModel {
    private SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    private Date fechaString;
    public String getFechaFormateada(Date fecha)
    {
        return formato.format((Date)fecha);
    }
    
    public Date stringToDate(String text)
    {        
        try {
            fechaString=(Date)formato.parseObject(text);
        } catch (ParseException ex) {}
        return fechaString;
    }
    
    
}
