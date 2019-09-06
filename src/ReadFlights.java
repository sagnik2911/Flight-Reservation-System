
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
public class ReadFlights {
    Flight arr[],arr1[];
    String filename;
    private String cri;
    ReadFlights()
    {
        arr = new Flight[100];
        arr1 = new Flight[100];
    }
    Flight[] getdomflights(String filename) throws IOException
    {
        int i=0,j=0;
        try
        {
            BufferedReader brstream = new BufferedReader( new FileReader(filename));
            String inputline,origin,flightid,deptime,arrtime,destination,freq;
            inputline = brstream.readLine();
            while(true)
            {
                inputline = brstream.readLine();
                //System.out.println(inputline);
                j=0;
                if  (inputline == null)
                    break;
                StringTokenizer tok = new StringTokenizer(inputline,",");
                origin = tok.nextToken();
                destination = tok.nextToken();
                freq = tok.nextToken();
                if(freq.charAt(0) == '"'){
                    j++;
                    freq = freq.substring(1,freq.length());
                   // System.out.println(freq);
                    String tk = tok.nextToken();
                    if(tk.charAt(0) == ' ')
                        tk = tk.substring(1,tk.length());
                    j++;
                    while(tk.charAt((tk.length()-1))!='"'){
                    freq = freq + "," + tk;
                    tk = tok.nextToken();
                    if(tk.charAt(0) == ' ')
                        tk = tk.substring(1,tk.length());
                    j++;
                    }
                    freq = freq + "," + tk.substring(0,(tk.length()-1));
                }
                flightid = tok.nextToken();
                deptime = tok.nextToken();
                arrtime=tok.nextToken();
                tok.nextToken();
                tok.nextToken();
                cri=tok.nextToken();
                //if (cri.equals("25-Oct-2014"))
                {   
                    arr[i]=new DomesticFlights(flightid,origin,deptime,destination,arrtime,freq,j);
                    i++;
                }
            }
            brstream.close();
        }
        catch(FileNotFoundException e)
        {
            //System.out.println("Not found");
        }
    return arr;
    }
    Flight[] getintflights(String filename) throws IOException
    {
        int i=0,j=0;
        try
        {
            BufferedReader brstream = new BufferedReader( new FileReader(filename));
            String inputline,origin,flightid,deptime,time,freq,source,time1,arrtime;
            inputline = brstream.readLine();
            inputline = brstream.readLine();
            inputline = brstream.readLine();
            while(true)
            {
                j=0;
                inputline = brstream.readLine();
                //System.out.println(inputline);
                if  (inputline == null)
                    break;
                StringTokenizer tok = new StringTokenizer(inputline,",");
                source = tok.nextToken();
                StringTokenizer t = new StringTokenizer(source);
                origin = t.nextToken();
                freq = tok.nextToken();
                if(freq.charAt(0) == '"'){
                    j++;
                    freq = freq.substring(1,freq.length());
                    String tk = tok.nextToken();
                    j++;
                    if(tk.charAt(0) == ' ')
                    tk = tk.substring(1,tk.length());
                    while(tk.charAt((tk.length()-1))!='"'){
                    freq = freq + "," + tk;
                    tk = tok.nextToken();
                    if(tk.charAt(0) == ' ')
                        tk = tk.substring(1,tk.length());
                    j++;
                    }
                    freq = freq + "," + tk.substring(0,(tk.length()-1));
                }
                flightid = tok.nextToken();
                time=tok.nextToken();
                StringTokenizer t1 = new StringTokenizer(time,"/");
                deptime = t1.nextToken();
                time1=t1.nextToken();
                StringTokenizer t2 = new StringTokenizer(time1,"+");
                arrtime = t2.nextToken();
                //System.out.println("Reading done");
                arr1[i]=new InternationalFlights(flightid,origin,deptime,"Singapore",arrtime,freq,j);
                //System.out.println("Object created");
                i++;
            }
            brstream.close();
        }
        catch(FileNotFoundException e)
        {
            //System.out.println("Not found");
        }
    return arr1;
    }
}