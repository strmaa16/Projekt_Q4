package sort;

import data.Waren;
import java.util.Comparator;

/*
 * @author Maxi
 */
public class CompDate implements Comparator<Waren>
{
    @Override
    public int compare(Waren t, Waren t1)
    {
        return t.getAblaufdatum().compareTo(t1.getAblaufdatum());
    }

}
