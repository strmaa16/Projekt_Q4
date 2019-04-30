package bl;

import data.Waren;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import sort.CompDate;


/*
 * @author Maxi
 */
public class WarenModel extends AbstractTableModel
{

    DateTimeFormatter datum = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    List<Waren> list = new ArrayList<>();

    public void laden(){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            fis = new FileInputStream("Waren.bin");
            ois = new ObjectInputStream(fis);
            Waren w = (Waren) ois.readObject();
            while(w!=null){
                list.add(w);
                w = (Waren) ois.readObject();
                
            }
        }
        catch (FileNotFoundException | EOFException  ex)
        {
            System.err.print(ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(WarenModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(WarenModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try
            {
                
                ois.close();
               
                fis.close();
                
            }
            catch (IOException ex)
            {
                Logger.getLogger(WarenModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
               
    }
    
    
    public void add(Waren w)
    {
        list.add(w);
        Collections.sort(list, new CompDate());
        this.fireTableDataChanged();
    }

    public void del(int w)
    {
        list.remove(w);
        Collections.sort(list, new CompDate());
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount()
    {
        return list.size();
    }

    @Override
    public int getColumnCount()
    {

        return WarenEnum.values().length;
    }

    @Override
    public void setValueAt(Object o, int row, int col)
    {
        if (o instanceof Waren)
        {
            list.set(row, (Waren) o);
        }
        else
        {
            Waren wAlt = list.get(row);
            String sorte = wAlt.getSorte();
            int menge = wAlt.getMenge();
            double gewicht = wAlt.getGewicht();
         

            WarenEnum index = WarenEnum.values()[col];

            switch (index)
            {
                case SORTE:
                    sorte = (String) o;
                    break;
                case MENGE:
                    menge = Integer.parseInt((String) o);
                    break;
                case GEWICHT:
                    gewicht = Double.parseDouble((String) o);
                    break;
                

            }
            Waren wNeu = new Waren(sorte, menge, gewicht);
            list.set(row, wNeu);
            this.fireTableDataChanged();
           
        }
    }

    @Override
    public boolean isCellEditable(int i, int i1)
    {
        return true;
    }

    @Override
    public String getColumnName(int i)
    {
        return WarenEnum.values()[i].getName();
    }

    @Override
    public Object getValueAt(int row, int col)
    {
        Waren w = list.get(row);
        WarenEnum index = WarenEnum.values()[col];

        switch (index)
        {
            case SORTE:
                return w.getSorte();
            case MENGE:
                return w.getMenge();
            case GEWICHT:
                return w.getGewicht();
            case ABLAUFDATUM:
                return w.getAblaufdatumAsString();
            default:
                return "?";
        }
    }

    public Waren getWarenAt(int zeile)
    {
        return list.get(zeile);
    }

    @Override
    public Class<?> getColumnClass(int i)
    {
        return String.class;
    }

}
