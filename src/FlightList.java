/*0
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sagnik Ghosh
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JOptionPane.showMessageDialog;
public class FlightList {
    int day;
    int check;
    String month,year;
    Flight dom[],inter[];
    FlightList( Routes flight[],int count,String date,String seat,String source,Flight dom[],Flight inter[],int check)
    {
        this.dom=dom;
        this.inter=inter;
        this.check=check;
        JFrame fltlst = new JFrame("ROUTES AVAILABLE");
        JLabel g = new JLabel( "Flight List");
        //fltlst.setSize(1400,600);
        fltlst.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fltlst.setLayout(new GridLayout(4,2));
        JPanel jp1 = new JPanel();
        JLabel jl1 = new JLabel("Change Date");  
        jp1.add(jl1);
        JButton jprev = new JButton("Previous Day");
        jp1.add(jprev);
        JButton jnext = new JButton("Next Day");
        jp1.add(jnext);
        JLabel jtf = new JLabel(date);
        jp1.add(jtf);
        fltlst.add(jp1);
        StringTokenizer tok = new StringTokenizer(date,"/");
        day=Integer.parseInt(tok.nextToken());
        month=tok.nextToken();
        year=tok.nextToken();
        if (day>=31)
            jnext.setEnabled(false);
        if (day<=1)
            jprev.setEnabled(false);
        JPanel jp3 = new JPanel();
        JButton book = new JButton("Book");
        book.setEnabled(false);
        jp3.add(book);
        JButton back = new JButton("Back");
        JPanel jp2=new JPanel();
        jp3.add(back);
        JLabel jl3= new JLabel("Domestic");
        jp2.add(jl3);
        JLabel jl4= new JLabel("International");
        jp2.add(jl4);
        
        String colhead[]={"Domestic PLANE ID","FROM","TO","DEPARTURE TIME(IST)","ARRIVAL TIME(IST)","Domestic SEATS LEFT","International PLANE ID","FROM","TO","DEPARTURE TIME(IST)[24 hrs]","ARRIVAL TIME(MST)[24 hrs]","International Seats left","JOURNEY DURATION(in mins)"};               
        String data[][]=new String[count][13];
        for(int i=0;i<count;i++)
        {
            data[i][0]= flight[i].domesticplane;
            data[i][1]= flight[i].source;
            data[i][2]= flight[i].via;
            data[i][3]= flight[i].depd;
            data[i][4]= flight[i].aard;
            data[i][5]= flight[i].seatsd;
            data[i][6]= flight[i].interplane;
            data[i][7]= flight[i].via;
            data[i][8]= "Singapore";
            data[i][9]= flight[i].depi;
            data[i][10]= flight[i].aari;
            data[i][11]= flight[i].seatsi;
            data[i][12]=flight[i].duration+"mins";
        }
        JTable tab = new JTable(data,colhead) {
        @Override
        public boolean isCellEditable(int row,int column) {                
            return false;               
        };
       // public boolean isRowSelected(int column)
        //{
          //  book.setEnabled(true);
          //  return true;
        //};
    };
        int width = tab.getPreferredSize().width; 
        int height = count * tab.getRowHeight(); 
        tab.setPreferredScrollableViewportSize(new Dimension(width, height));
        tab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tab.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            //if (e.getClickCount() == 2) {
              //  JTable target = (JTable)e.getSource();
                //int row = target.getSelectedRow();
             //   int column = target.getSelectedColumn();
                // do some action if appropriate column
           // }
                book.setEnabled(true);
        }
    });
        JScrollPane jsp= new JScrollPane(tab);
        jsp.add(jp2);
        fltlst.add(jsp);
        //JLabel jl2 = new JLabel("Change Date");  
        //jp1.add(jl1);
        jnext.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                day++;
                String date1;
                date1 = Integer.toString(day)+"/"+month+"/"+year;
                Display_manager dm= new Display_manager();
                fltlst.dispose();
                if  (date1.equals("26/10/2014"))
                {
                    showMessageDialog(null, "Datespan over with selected database");
                    dm.rerun(dom,inter,check);
                }
                else
                    dm.change(source, date1, seat,dom,inter,check);
            }
        });
        jprev.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                day--;
                String date1;
                date1 = Integer.toString(day)+"/"+month+"/"+year;
                Display_manager dm= new Display_manager();
                fltlst.dispose();
                if  (date1.equals("25/10/2014"))
                {
                    showMessageDialog(null, "Datespan over with selected database");
                    dm.rerun(dom,inter,check);
                }
                else
                    dm.change(source, date1, seat,dom,inter,check);
            }
        });
        book.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                int selectedRow = tab.getSelectedRow();
                selectedRow = tab.convertRowIndexToModel(selectedRow);
                String domid = (String)tab.getModel().getValueAt(selectedRow, 0);
                String val2 = (String)tab.getModel().getValueAt(selectedRow, 5);
                String intid = (String)tab.getModel().getValueAt(selectedRow, 6);
                String val4 = (String)tab.getModel().getValueAt(selectedRow, 11);
                String start = (String)tab.getModel().getValueAt(selectedRow, 1);
                String via = (String)tab.getModel().getValueAt(selectedRow, 2);
                String domdep = (String)tab.getModel().getValueAt(selectedRow, 3);
                String domarr = (String)tab.getModel().getValueAt(selectedRow, 4);
                String end = (String)tab.getModel().getValueAt(selectedRow, 8);
                String intdep = (String)tab.getModel().getValueAt(selectedRow, 9);
                String intarr = (String)tab.getModel().getValueAt(selectedRow, 10);
                System.out.println("ID: " +domid +"Seats left:"+ val2 +"ID: "+ intid +"Seats left:"+ val4);
                try {
                    ReadSeats.updateseats(date,domid,seat,intid);
                } catch (IOException ex) {
                    Logger.getLogger(FlightList.class.getName()).log(Level.SEVERE, null, ex);
                }
                SwingUtilities.invokeLater(new Runnable()
                    {
                        public void run()
                        {
                            new BookPage(date,seat,domid,domdep,domarr,intid,intdep,intarr,start,via,end,dom,inter,check);
                        }
                    });
                fltlst.setVisible(false);
            }    
    });
    back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                Display_manager dm=new Display_manager();
                dm.rerun(dom, inter, check);
                fltlst.dispose();
            }
        });
        fltlst.add(jp3);
        fltlst.pack();
        fltlst.setVisible(true);
    }
}
