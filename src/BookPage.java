
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class BookPage {
    BookPage(String date,String seats,String domid,String domdep,String domarr,String intid,String intdep,String intarr,String start,String via,String end,Flight[] dom,Flight inter[],int check)
    {
        JFrame book = new JFrame("Enter Details");
        //book.setSize(700,300);
        book.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BoxLayout boxLayout = new BoxLayout(book.getContentPane(), BoxLayout.Y_AXIS);
        book.setLayout(boxLayout);
        JPanel jp2=new JPanel();
        JLabel jl1 = new JLabel("Selected Route: ");
        jp2.add(jl1);
        book.add(jp2);
        String colhead[]={"PLANE ID","FROM","TO","DEPARTURE TIME(IST)","ARRIVAL TIME(IST)"};               
        String data[][]=new String[2][5];
        data[0][0]=domid;
        data[0][1]=start;
        data[0][2]=via;
        data[0][3]=domdep;
        data[0][4]=domarr;
        data[1][0]=intid;
        data[1][1]=via;
        data[1][2]=end;
        data[1][3]=intdep;
        data[1][4]=intarr;
        JTable tab = new JTable(data,colhead) {
        @Override
        public boolean isCellEditable(int row,int column) {                
            return false;               
            };
        };
        
        JScrollPane jsp= new JScrollPane(tab);
       // int A = tab.getWidth();
       // int B = tab.getHeight();
        
        int width = tab.getPreferredSize().width; 
        int height = 2 * tab.getRowHeight(); 
        tab.setPreferredScrollableViewportSize(new Dimension(width, height));    
        JPanel jp3=new JPanel(new GridLayout());
        jp3.add(jsp);
        book.add(jp3);
        JPanel jp1=new JPanel();
        JLabel jl2 = new JLabel("Give details of Passengers ");
        jp1.add(jl2);
        book.add(jp1);
        for (int i=0;i<Integer.parseInt(seats);i++)
        {
            JPanel jp=new JPanel();
            jp.setLayout(new BoxLayout(jp,BoxLayout.X_AXIS));
            JLabel lab=new JLabel("Name:");
            JTextField jtf=new JTextField(15);
            jp.add(lab);
            jp.add(jtf);
            JComboBox<String> jcb;
            String sex[]={"sex","male","female"};
            jcb = new JComboBox<> (sex);
            jp.add(jcb);
            JLabel lab1= new JLabel("Age:");
            JTextField jtf1=new JTextField(5);
            jp.add(lab1);
            jp.add(jtf1);
            book.add(jp);
        }
        JPanel jp=new JPanel();
        JButton ok = new JButton("ok");
        jp.add(ok);
        book.add(jp);
        ok.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                Display_manager dm=new Display_manager();
                dm.success(dom,inter,check);
                book.dispose();
            }
        });
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        int x=(d.width-book.getWidth())/2;
        int y=(d.height-book.getHeight())/2;
        book.setLocation(x, y);
        book.pack();
        book.setVisible(true);
    }
}
