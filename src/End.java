
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class End {
    End(Flight dom[],Flight inter[],int check)
    {
        JFrame jf = new JFrame();
        JLabel jl= new JLabel("Booking Successful");
        JPanel jp1 = new JPanel();
        jp1.add(jl);
        JButton ok = new JButton("Done");
        jp1.add(ok);
        JButton bkn = new JButton("Book Another");
        jp1.add(bkn);
        jf.add(jp1);
        bkn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                Display_manager dm=new Display_manager();
                dm.rerun(dom, inter, check);
                jf.dispose();
            }
        });
        ok.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                showMessageDialog(null, "GOOD BYE \n Copyright : CodeWiz");
                jf.dispose();
            }
        });
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        int x=(d.width-jf.getWidth())/2;
        int y=(d.height-jf.getHeight())/2;
        jf.setLocation(x, y);
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
