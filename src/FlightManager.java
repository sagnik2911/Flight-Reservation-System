/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Saurabh Banerjee
 * @author Sayan Bose
 */
import java.io.*;
import java.util.*;
public class FlightManager {
    String src;
    String date;
    int no;
    int day,month,year;
    int dayofweek;
    private Flight dom[]=new Flight[100];
    private Flight inter[]=new Flight[100];
    public Routes arr[]=new Routes[100];
    int numbers;
    FlightManager(String src, String date, Flight dom[],Flight inter[])
    {
        //System.out.println("In Flightmanager");
        this.src = src;
        this.date   = date;
        this.dom    =   dom;
        this.inter  = inter;
        numbers=0;
        StringTokenizer tok = new StringTokenizer(date,"/");
        day=Integer.parseInt(tok.nextToken());
        //System.out.println(day);
        month=Integer.parseInt(tok.nextToken());
        month--;//calendar class starts from 0
        year=Integer.parseInt(tok.nextToken());
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,day);
        dayofweek = calendar.get(Calendar.DAY_OF_WEEK);
        //System.out.println(dayofweek);
        dayofweek=(dayofweek-1);
        //System.out.println(dayofweek);
    }
    Routes[] getroutes() throws IOException
    {
        int arrhrd,arrmind,dephrd,depmind,arrhri,arrmini,dephri,depmini;
        String  seatsd,seatsi;
        for (int i=0;i<DomesticFlights.no;i++)
        {
            //System.out.println(Arrays.toString(dom[i].week));
            //System.out.println(src);
            //System.out.println(dom[i].origin);
            if((dom[i].week[dayofweek]) && src.equals(dom[i].origin))
            {
                //System.out.println(dom[i].week[dayofweek]);
                //System.out.println(dom[i].id);
                StringTokenizer tok = new StringTokenizer(dom[i].arrtime);
                String time = tok.nextToken();
                StringTokenizer tok1 = new StringTokenizer(time,":");
                arrhrd=Integer.parseInt(tok1.nextToken());
                arrmind=Integer.parseInt(tok1.nextToken());
                String sys = tok.nextToken();
                if (sys.equals("PM"))
                    arrhrd=arrhrd+12;
                //System.out.println(arrhrd+":"+arrmind);
                tok = new StringTokenizer(dom[i].deptime);
                time = tok.nextToken();
                tok1 = new StringTokenizer(time,":");
                dephrd=Integer.parseInt(tok1.nextToken());
                depmind=Integer.parseInt(tok1.nextToken());
                sys = tok.nextToken();
                if (sys.equals("PM"))
                    dephrd=dephrd+12;
                //System.out.println(dephrd+":"+depmind);
                for (int j=0;j<InternationalFlights.no;j++)
                {
                  //  System.out.println(Arrays.toString(inter[j].week));
                    //System.out.println(inter[j].week[dayofweek]);
                    //System.out.println(dayofweek);
                    if((inter[j].week[dayofweek]) && dom[i].destination.equals(inter[j].origin))
                    {
                        //System.out.println(inter[j].id);
                        String time1 = inter[j].arrtime;
                        arrhri=Integer.parseInt(time1.substring(0, 2));
                        arrmini=Integer.parseInt(time1.substring(2, 4));
                        if (arrhri<12)
                            time1=time1.substring(0, 2)+":"+time1.substring(2,4)+" AM";
                        else
                            time1=Integer.toString(arrhri-12)+":"+time1.substring(2,4)+" PM";
                        time=inter[j].deptime;
                        seatsd=ReadSeats.getseats(date, dom[i].id);
                        //System.out.println(seatsd);
                        seatsi=ReadSeats.getseats(date, inter[j].id);
                        //System.out.println(seatsi);
                        dephri=Integer.parseInt(time.substring(0, 2));
                        depmini=Integer.parseInt(time.substring(2,4));
                        //System.out.println(arrhri+":"+arrmini);
                        //System.out.println(dephri+":"+depmini);
                        if (dephri<12)
                            time=time.substring(0, 2)+":"+time.substring(2,4)+" AM";
                        else
                            time=Integer.toString(dephri-12)+":"+time.substring(2,4)+" PM";
                        //time=time.substring(0,2)+":"+time.substring(2,4);
                        int h=dephri-arrhrd;
                        int m=depmini-arrmind;
                        if(m<0)
                        {
                            m=m+60;
                            h--;
                        }
                        m=h*60+m;
                        if(m>120 && m<360)
                        {
                            /*System.out.println("Flights possible on "+date+" from " + dom[i].origin +" via " + dom[i].destination + " to Singapore:");    
                            System.out.println("Plane Id::"+dom[i].id);
                            System.out.println("Departure Time::"+dephrd+":"+depmind);
                            System.out.println("Arrival Time::"+arrhrd+":"+arrmind);    
                            System.out.println("Plane Id::"+inter[j].id);
                            System.out.println("Departure Time::"+dephri+":"+depmini);
                            System.out.println("Arrival Time::"+arrhri+":"+arrmini);*/
                            int duration=(arrhri-dephrd)*60+(arrmini-depmind);
                            if(duration<0)
                                    duration=duration+24*60;
                            duration=duration-150;
                            arr[numbers++]=new Routes(dom[i].id,inter[j].id,dom[i].origin,inter[j].origin,dom[i].deptime,dom[i].arrtime,seatsd,time,time1,duration,seatsi);
                        }
                    }
                }
                
            } 
                    
        }
    sortarr();    
    return arr;    
    }
    void sortarr()
    {
        int i,j;
        for (i=0;i<numbers;i++)
        {
            for (j=i+1;j<numbers;j++)
            {    
                if (arr[i].duration>arr[j].duration)
                {
                   /* Flights temp = new Flights(arr[i].domesticplane,arr[i].interplane,arr[i].source,arr[i].via,arr[i].arrhrd,arr[i].arrmind,arr[i].dephrd,arr[i].depmind,arr[i].arrhri,arr[i].arrmini,arr[i].dephri,arr[i].depmini);
                    temp.display();
                    arr[i] = new Flights(arr[j].domesticplane,arr[j].interplane,arr[j].source,arr[j].via,arr[j].arrhrd,arr[j].arrmind,arr[j].dephrd,arr[j].depmind,arr[j].arrhri,arr[j].arrmini,arr[j].dephri,arr[j].depmini);
                    arr[j] = new Flights(temp.domesticplane,temp.interplane,temp.source,temp.via,temp.arrhrd,temp.arrmind,temp.dephrd,temp.depmind,temp.arrhri,temp.arrmini,temp.dephri,temp.depmini);
                */
                    Routes temp;
                    temp = arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;
                }    
            }            
        }
    }
}
