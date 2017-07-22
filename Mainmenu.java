import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * Created by ranjing on 18/05/2014.
 */
public class Mainmenu extends JFrame implements ActionListener {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    JLabel MainLabel = new JLabel("===Welcome to Student Record System===");
    JButton button1 = new JButton("Student Admin");
    JButton button2 = new JButton("Course  Admin");
    JButton button3 = new JButton(" Staff  Admin  ");
    JButton button4 = new JButton("Exit");

    public Mainmenu()

    {
        super("Student Record System - Main Menu");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setBackground(Color.gray);

        JPanel panel = new JPanel(new GridBagLayout());
        c.add(panel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets (20,10,10,10);
        constraints.gridx = 2;
        constraints.gridy = 0;
        panel.add(MainLabel,constraints);

        constraints.gridx = 2;
        constraints.gridy = 2;
        panel.add(button1,constraints);
        button1.addActionListener(this);

        constraints.gridx = 2;
        constraints.gridy = 4;
        panel.add(button2,constraints);
        button2.addActionListener(this);

        constraints.gridx = 2;
        constraints.gridy = 6;
        panel.add(button3,constraints);
        button3.addActionListener(this);

        constraints.gridx = 2;
        constraints.gridy = 8;
        panel.add(button4,constraints);
        button4.addActionListener(this);


    }

    public void actionPerformed(ActionEvent e){

        String cmd = e.getActionCommand();
        if(cmd.equals("Student Admin")) {
            StudentAdminMenu StudentMenu = new StudentAdminMenu();
            StudentMenu.setVisible(true);
            StudentMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //StudentMenu.setResizable(false);
            this.setVisible(false);

        }
        else if(cmd.equals("Course  Admin")){
            CourseAdminMenu CourseMenu = new CourseAdminMenu();
            CourseMenu.setVisible(true);
            CourseMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            CourseMenu.setResizable(false);
            this.setVisible(false);
        }
        else if(cmd.equals(" Staff  Admin  ")) {
            StaffAdminMenu StaffMenu = new StaffAdminMenu();
            StaffMenu.setVisible(true);
            StaffMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            StaffMenu.setResizable(false);
            this.setVisible(false);
        }
        else if(cmd.equals("Exit")){
            System.exit(0);
        }

        }


    }








