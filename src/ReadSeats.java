
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
public class ReadSeats {
    static String getseats(String date,String id) throws IOException
    {
        String temp="15";
        String tempdate,tempid;
        try
        {
            BufferedReader brstream = new BufferedReader( new FileReader("seatinfo.txt"));
            String inputline;
            while(true)
            {
                inputline=brstream.readLine();
                if  (inputline == null)
                 break;
                //System.out.println(inputline);
                StringTokenizer tok = new StringTokenizer(inputline,",");
                tempid=tok.nextToken();
                if(tempid.equals(id))
                {
                    tempdate=tok.nextToken();
                    if(tempdate.equals(date))
                    {
                        temp=tok.nextToken();
                    }
                }
                
            }
            brstream.close();
        }
        catch(FileNotFoundException e)
        {
           // System.out.println("Not found");
        }
        //System.out.println(temp);
        return temp;
    }
    static void updateseats(String date, String domid, String seatbooked, String intid) throws IOException
    {
        try
        {
            File inputFile = new File("seatinfo.txt");
            File tempFile = new File("myTempFile.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String tempdate, tempid, seat,seat1;
            int seatb,seata;
            int status=0;
            String currentLine,editline;

            while(true) {
            // trim newline when comparing with lineToRemove
                currentLine=reader.readLine();
                if (currentLine==null)
                    break;
                StringTokenizer tok = new StringTokenizer(currentLine,",");
                tempid=tok.nextToken();
                tempdate=tok.nextToken();
                if (tempid.equals(domid) && tempdate.equals(date))
                {
                    seat=tok.nextToken();
                    seata=Integer.parseInt(seat);
                    seatb=Integer.parseInt(seatbooked);
                    seat1=Integer.toString(seata-seatb);
                    editline=domid+","+tempdate+","+seat1;
                  //  System.out.println("dom match");
                    writer.write(editline);
                    if (status==2)
                        status=3;
                    else if(status==0)
                        status=1;
                    writer.newLine();
                }
                else if (tempid.equals(intid) && tempdate.equals(date))
                {
                    seat=tok.nextToken();
                    seata=Integer.parseInt(seat);
                    seatb=Integer.parseInt(seatbooked);
                    seat1=Integer.toString(seata-seatb);
                    editline=intid+","+tempdate+","+seat1;
                   // System.out.println("int match");
                    writer.write(editline);
                    if (status==1)
                        status=3;
                    else if(status==0)
                        status=2;
                    writer.newLine();
                }
                else
                {
                    writer.write(currentLine);
                    writer.newLine();
                }
            }
            if (status == 2 || status == 0)
            {
                seatb=Integer.parseInt(seatbooked);
                seat1=Integer.toString(15-seatb);
                editline=domid+","+date+","+seat1;
                //System.out.println("int match and dom did not match");
                writer.write(editline);
                writer.newLine();
            }
            if  (status == 1 || status == 0)
            {  
                seatb=Integer.parseInt(seatbooked);
                seat1=Integer.toString(15-seatb);
                editline=intid+","+date+","+seat1;
                //System.out.println("dom match and int did not match");
                writer.write(editline);
                writer.newLine();
            }
            reader.close();
            writer.close();
            inputFile.delete();
            //    System.out.println("renamed");
            boolean bool = tempFile.renameTo(inputFile); 
                    //    System.out.println("renamed");
              //  System.out.println(bool);
        }
        catch(FileNotFoundException e)
        {
         //   System.out.println("Not found");
        }
    }
}
