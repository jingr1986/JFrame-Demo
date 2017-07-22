import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Created by ranjing on 18/05/2014.
 */
public class StaffAdminMenu extends JFrame implements ActionListener {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    JPanel panel = new JPanel (new GridBagLayout());
    JLabel StaffLabel = new JLabel ("===Staff Admin===");
    JButton button1 = new JButton("List All Staff");
    JButton button2 = new JButton("Return To Main Menu");
    JButton SaveButton = new JButton("Confirm");

    JComboBox saveSelector = new JComboBox();

    String[] column = {};
    Object[][] rows = {};
    DefaultTableModel model = new DefaultTableModel(rows, column);
    String saveAction=null;

    public StaffAdminMenu(){

        super("Student Record System - Staff Admin");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setBackground(Color.gray);
        c.add(panel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets (10,10,10,10);

        constraints.gridx = 2;
        constraints.gridy = 0;
        panel.add(StaffLabel,constraints);

        constraints.gridx = 2;
        constraints.gridy = 2;
        panel.add(button1,constraints);
        button1.addActionListener(this);

        constraints.gridx = 2;
        constraints.gridy = 4;
        panel.add(button2,constraints);
        button2.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e){

        String cmd = e.getActionCommand();

        if(cmd.equals("List All Staff")){
            listStaff();
        }
        else if (cmd.equals("Return To Main Menu")){
            Mainmenu Menu = new Mainmenu();
            Menu.setVisible(true);
            Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Menu.setResizable(false);
            this.setVisible(false);
        }
        else if (cmd.equals("Return")){
            StaffAdminMenu Menu = new StaffAdminMenu();
            Menu.setVisible(true);
            Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Menu.setResizable(false);
            this.setVisible(false);

        }
        else if (cmd.equals("Confirm")){
            String title = "Save File...";
            saveAction = saveSelector.getSelectedItem().toString();

            if(saveAction.equals("Update")){
                saveStaffFile();
                JOptionPane.showMessageDialog(SaveButton, "Update File Success", title, JOptionPane.PLAIN_MESSAGE);
            }
            else if(saveAction.equals("Save To ...")){
                Staff.saveStaffFile();
            }
            else if(saveAction.equals("-Choose Save Action-")){
                JOptionPane.showMessageDialog(SaveButton, "Please Select A Save Action", title, JOptionPane.ERROR_MESSAGE);
            }
        }

    }
    //list all staff.
    public void listStaff(){

        ArrayList<Staff> StaffList = (ArrayList<Staff>) RecordSystem.StaffList;

        panel.removeAll();
        panel.updateUI();
        panel.setLayout(new GridBagLayout());
        setSize(1200,800);

        JPanel savePanel = new JPanel(new GridBagLayout());
        JLabel label = new JLabel("All Staff Information");
        JButton ReturnButton = new JButton("Return");




        model = new DefaultTableModel(rows, column);
        Object[][] rows = {};

        JTable StuTable = new JTable(model);
        JScrollPane table = new JScrollPane(StuTable);

        GridBagConstraints constraints = new GridBagConstraints();
        //constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(label, constraints);

        saveSelector.addItem("-Choose Save Action-");
        saveSelector.addItem("Update");
        saveSelector.addItem("Save To ...");
        constraints.gridx = 0;
        constraints.gridy = 2;
        savePanel.add(saveSelector, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        savePanel.add(SaveButton, constraints);
        SaveButton.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(savePanel,constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(table, constraints);
        table.setPreferredSize(new Dimension(1100, 650));

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(ReturnButton, constraints);
        ReturnButton.addActionListener(this);


        String[] ColumnNames = {"Staff ID", "Staff Name", "Salary/Salary Rate", "Role"};
        for (int i = 0; i < ColumnNames.length; i++) {
            model.addColumn(ColumnNames[i]);
        }

        for (int i = 0; i < StaffList.size(); i++) {

            Staff temp = StaffList.get(i);
            String tempRole = temp.getRole();
            if(tempRole.equals("Manager")||tempRole.equals("Admin")) {

                String[] rowData = {
                        temp.getStaffID(), temp.getName(), Double.toString(temp.getSalary()), temp.getRole()};

                model.addRow(rowData);

            }
            else if (tempRole.equals("Instructor")){

                String[] rowData = {
                        temp.getStaffID(), temp.getName(), Double.toString(temp.getSalaryRate()), temp.getRole()};

                model.addRow(rowData);

            }


        }

    }
    //update staff file.
    public void saveStaffFile(){
        String title = "Save File";
        try{
            String file = "staffList.txt";
            FileWriter fr = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fr);
            for (int i = 0; i < RecordSystem.StaffList.size(); i++) {
                bw.write(RecordSystem.StaffList.get(i).getStaffID() + ",");
                bw.write(RecordSystem.StaffList.get(i).getName() + ",");
                if(RecordSystem.StaffList.get(i).getRole().equals("Manager")||
                        RecordSystem.StaffList.get(i).getRole().equals("Admin")){
                            bw.write(Double.toString(RecordSystem.StaffList.get(i).getSalary()) + ",");
                }
                else if(RecordSystem.StaffList.get(i).getRole().equals("Instructor")){
                    bw.write(Double.toString(RecordSystem.StaffList.get(i).getSalaryRate()) + ",");
                }
                bw.write(RecordSystem.StaffList.get(i).getRole() + ",");
                bw.newLine();
            }

            bw.close();
            fr.close();
        } catch(Exception ex)
        {System.out.print("error!!!");}
    }

}
