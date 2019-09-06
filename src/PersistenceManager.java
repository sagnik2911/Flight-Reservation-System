
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class PersistenceManager {
        String date,i,d;
        ReadFlights r;
        PersistenceManager(String inter,String domes)
        {
            this.i=inter;
            this.d=domes;
            r=new ReadFlights();
        }
        Flight[] getdomestic() throws IOException
        { 
            Flight arr[];
            arr = r.getdomflights(d);
            /*for (int i=0;i<DomesticFlights.no;i++)
            {
                arr[i].display();
            }*/
            return arr;
        }
        Flight[] getinternational() throws IOException
        { 
            Flight arr[];
            arr = r.getintflights(i);
            /*for (int i=0;i<InternationalFlights.no;i++)
            {
                arr[i].display();
            }*/
            return arr;
        }
}
