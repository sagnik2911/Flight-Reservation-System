/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sagnik Ghosh
 */
import java.io.*;
import java.io.IOException;
import static java.lang.System.exit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Display_manager {
    public void success(Flight dom[],Flight inter[],int check)
    {
        SwingUtilities.invokeLater(new Runnable()
                {
                        public void run()
                        {
                            new End(dom,inter,check);
                        }
                    });
    }
    public void rerun(Flight dom[],Flight inter[],int check)
    {
        SwingUtilities.invokeLater(new Runnable()
                {
                        public void run()
                        {
                            new Search(dom,inter,check).setVisible(true);
                        }
                    });
    } 
    public void change(String source,String date,String seat,Flight dom[],Flight inter[],int change)
    {
        try {
                FlightManager f2 = new FlightManager(source,date,dom,inter);
                f2.getroutes();    
                    //FlightList f2=new FlightList(f1.arr,f1.numbers,s2);
                SwingUtilities.invokeLater(new Runnable()
                {
                        public void run()
                        {
                            new FlightList(f2.arr,f2.numbers,date,seat,source,dom,inter,change);
                        }
                    });
                } catch (IOException ex) {
                    
                } 
    }
    public static void main(String args[])throws IOException 
    {
        String s1,s2,s3,g;
        Flight[] dom,inter;
        //Routes a[]=new Routes[400];
        int i,ch;
        int check=5;
        i=0;
        PersistenceManager p1 = new PersistenceManager(args[1],args[0]);
        dom=p1.getdomestic();
        inter=p1.getinternational();
        if(args[0].equals("spicejet.Schedule.csv") && args[1].equals("silkair.Schedule.csv"))
            check=0;
        else if(args[0].equals("spicejet.Schedule1.csv") && args[1].equals("silkair.Schedule1.csv"))
            check=1;
        else 
        {
            System.out.println("Not proper files selected");
            exit(0);
        }
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        if (args[2].equalsIgnoreCase("swing") && check==0)
        {     
            SwingUtilities.invokeLater(new Runnable()
                {
                    public void run()
                    {
                        new Search(dom,inter,0).setVisible(true);
                    }
                });
         }
        else if (args[2].equalsIgnoreCase("swing") && check==1)
        {     
            SwingUtilities.invokeLater(new Runnable()
                {
                    public void run()
                    {
                        new Search(dom,inter,1).setVisible(true);
                    }
                });
         }
        else
        {     
            while(true)
            {
                i=0;
                Routes a[]=new Routes[400];
                int status;
            while(true)
                {
                    try {
                        System.out.println();
                        System.out.println("Enter Date Of Travel (dd/mm/yyyy): ");
                        s2=br.readLine();
                        System.out.println();
                        System.out.println("Enter Source City (PUNE,MUMBAI,DELHI): ");
                        s1=br.readLine();
                        System.out.println();
                        System.out.println("Enter Number of Seats (1-10): ");
                        s3=br.readLine();
                        System.out.println();
                        StringTokenizer tok = new StringTokenizer(s2,"/");
                        String day = tok.nextToken();
                        String month = tok.nextToken();
                        String year = tok.nextToken();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Date check_in = sdf.parse("25/10/2014");
                        Date check_out = sdf.parse(s2);
                        if(check_out.after(check_in))
                        {
                            // check_out < check_in
                            status=1;
                            //System.out.println("selected date after 25th");
                        }
                        else
                        {
                            status=0;
                            // check_out == check_in
                            //System.out.println("selected date within 25th");
                        }
                        System.out.println("Data Entered ");
                        System.out.println("DATE: "+s2);
                        //System.out.println();
                        System.out.println("SOURCE CITY: "+s1);
                        //System.out.println();
                        System.out.println("NUMBER OF SEATS: "+s3);
                        System.out.println();
                        System.out.print("Continue to booking [y/n]:");
                        g=br.readLine();
                        System.out.println();
                        if(g.equalsIgnoreCase("y"))
                            break;
                    } catch (ParseException ex) {
                        Logger.getLogger(Display_manager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (status!=check)
                        {
                            System.out.println("Selected date not within booking range");
                            break;
                        }
                try
                {
                    FlightManager f1 = new FlightManager(s1,s2,dom,inter);
                    a=f1.getroutes();
                }
                catch(NullPointerException e)
                {
                    
                }
                
                try
                {
                do
                {
                   System.out.println("OPTION "+(i+1)+":");
                   System.out.println();
                    a[i].display();
                    i++;
                   System.out.println("----------------------");
                }while(a[i]!=null);
                
                System.out.println();
                System.out.println("Enter Your Choice:");
                ch=Integer.parseInt(br.readLine());
                if (Integer.parseInt(a[ch-1].seatsd)>=Integer.parseInt(s3) && Integer.parseInt(a[ch-1].seatsi)>=Integer.parseInt(s3))
                {
                    System.out.println();
                    System.out.println("Booked Flight:");
                    System.out.println();
                    a[ch-1].display();
                    ReadSeats.updateseats(s2,a[ch-1].domesticplane,s3,a[ch-1].interplane);
                    System.out.println();
                    }
                else
                {
                    System.out.println("Booking not possible(Seat not available)");    
                }
                }
                catch(NullPointerException nx)
                {
                    
                }
            
            System.out.print("Want to make another Booking ? [y/n]: ");
            g=br.readLine();
            System.out.println();
            if(g.equalsIgnoreCase("n"))
                break;
            }
        }
    }
}
