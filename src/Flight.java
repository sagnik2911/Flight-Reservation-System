
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Flight {
    String id,origin,deptime,destination,arrtime,frequency;
    boolean week[];
    private int days;
    Flight(String id,String origin,String deptime,String destination,String arrtime,String frequency,int days)
    {
        this.id=id;
        this.arrtime=arrtime;
        this.deptime=deptime;
        this.destination=destination;
        origin = origin.toUpperCase();
        this.origin=origin;
        this.frequency=frequency;
        week = new boolean[7];
        for (int i=0;i<7;i++)
            week[i]=false;
        this.days=days;
        //System.out.println(days);
        if (days!=0 && days!=7) 
        {
            StringTokenizer tok = new StringTokenizer(frequency,",");
            for (int i=0;i<days;i++)
            {
                String s=tok.nextToken();
                //System.out.println(s);
                int j = availability(s);
                //System.out.println(j);
                week[j]=true;
            }
        }
        else 
          for (int i=0;i<7;i++)
            week[i]=true;  
    }
    int availability(String s)
    {
        if (s.equals("Sunday") || s.equals("Sun"))
                return 0;
        else if (s.equals("Monday") || s.equals("Mon"))
                return 1;
        else if (s.equals("Tuesday") || s.equals("Tue"))
                return 2;
        else if (s.equals("Wednesday") || s.equals("Wed"))
                return 3;
        else if (s.equals("Thursday") || s.equals("Thu"))
                return 4;
        else if (s.equals("Friday") || s.equals("Fri"))
                return 5;
        else //if (s.equals("Saturday") || s.equals("Sat"))
                return 6;
    }
    void display()
    {
        System.out.println("Flights from " + origin +" via " + destination);
        //System.out.println("Flight from " + source +" to " + via );    
        System.out.println("Plane Id::"+id);
        System.out.println("Departure Time::"+deptime);
        System.out.println("Arrival Time::"+arrtime);  
        //System.out.println("Flight from " + via +" to Singapore");  
        //System.out.println("Plane Id::"+interplane);
        //System.out.println("Departure Time::"+dephri+":"+depmini);
        System.out.println("Frequency::"+frequency);
        System.out.println("Days::"+days);
        System.out.println("Week::"+Arrays.toString(week));
    }
}
