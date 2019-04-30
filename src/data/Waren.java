package data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * @author Maxi
 */
public class Waren implements Comparable<Waren>, Serializable
{
    private final String sorte;
    private final int menge;
    private final double gewicht;
    private  LocalDate ablaufdatum = LocalDate.now();
    
    private transient static DateTimeFormatter datum = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Waren(String sorte, int menge, double gewicht)
    {
        this.sorte = sorte;
        this.menge = menge;
        this.gewicht = gewicht;
        this.ablaufdatum  = ablaufdatum.plusDays(5);
       
    }

    public String getSorte()
    {
        return sorte;
    }

    public int getMenge()
    {
        return menge;
    }

    public double getGewicht()
    {
        return gewicht;
    }

    public LocalDate getAblaufdatum()
    {
        return ablaufdatum;
    }

    public String getAblaufdatumAsString()
    {   
        return ablaufdatum.format(datum);
    }

    @Override
    public int compareTo(Waren t)
    {
       
        return Double.compare(this.gewicht, t.getGewicht());
    }

    @Override
    public String toString()
    {
        return String.format(sorte + " " + menge + " " +gewicht + " " + getAblaufdatumAsString());
    }
    
    
}
