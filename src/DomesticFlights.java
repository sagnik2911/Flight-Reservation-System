/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class DomesticFlights extends Flight{
    static int no;
    public DomesticFlights(String id, String origin, String deptime, String destination, String arrtime, String frequency,int i) {
        super(id, origin, deptime, destination, arrtime, frequency,i);
        no++;
    }
    
}
