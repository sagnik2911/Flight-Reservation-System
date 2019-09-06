/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sagnik Ghosh
 */
public class Routes {
    static int j=0;
    String domesticplane,interplane;
    String source,via;
  //  static int no=0;
    String depd,aard,depi,aari;
    String seatsd,seatsi;
//    String dep_timed,arr_timed,dep_timei,arr_timei;
    int duration; //calculate the time takes to fly throught this route 
    Routes(String domesticplane,String interplane,String source,String via,String depd,String aard,String seatsd,String depi,String aari,int duration,String seatsi)
    {
       j++;
       this.domesticplane=domesticplane;
       this.interplane=interplane;
       this.source=source;
       this.via=via;
       this.depd=depd;
       this.aard=aard;
       this.depi=depi;
       this.aari=aari;
       this.duration=duration;
//       no++;
       this.seatsd=seatsd;
       this.seatsi=seatsi;
    }
    void display()
    {
        System.out.println("Flights from " + source +" via " + via + " to Singapore:");
        System.out.println("Flight from " + source +" to " + via );    
        System.out.println("Plane Id::"+domesticplane);
        System.out.println("Departure Time::"+depd);
        System.out.println("Arrival Time::"+aard);  
        System.out.println("Seats Left::"+seatsd);
        System.out.println("Flight from " + via +" to Singapore");  
        System.out.println("Plane Id::"+interplane);
        System.out.println("Departure Time::"+depi);
        System.out.println("Arrival Time::"+aari);
        System.out.println("Seats Left::"+seatsi);
        System.out.println("Duration(in mins)::"+duration);
    }
}
