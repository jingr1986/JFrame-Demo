import com.sun.java.swing.action.SaveAction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 * Created by ranjing on 18/05/2014.
 */
public class StudentAdminMenu extends JFrame implements ActionListener {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    JPanel panel = new JPanel(new GridBagLayout());
    String[] column = {};
    Object[][] rows = {};
    DefaultTableModel model = new DefaultTableModel(rows, column);
    JLabel StudentLabel = new JLabel("===Student Admin===");
    JButton button1 = new JButton("List All Students");
    JButton button2 = new JButton("Add New Student");
    JButton button3 = new JButton("Enroll Student");
    JButton button4 = new JButton("Process Payment");
    JButton button5 = new JButton("Return To Main Menu");
    JButton listButton = new JButton("List Students");

    JTextField stuIDtext = new JTextField(10);
    JTextField stuNametext = new JTextField(10);
    JTextField YOBtext = new JTextField(10);
    JTextField runtext = new JTextField(10);
    JTextField paytext = new JTextField(10);

    JButton confirm = new JButton("Confirm Add");
    JButton returnButton = new JButton("Return");
    JButton confirmEnroll = new JButton("Confirm Enroll");
    JButton SaveButton = new JButton("Confirm");

    JComboBox courseSelector = new JComboBox();
    JComboBox yearSelector = new JComboBox();
    JComboBox saveSelector = new JComboBox();

    String stuID = null;
    String stuName = null;
    String YOB = null;
    String courseName = null;
    String academyYear = null;
    String runNumber = null;
    String grade = null;
    String payAmount = null;
    String saveAction = null;


    public StudentAdminMenu() {

        super("Student Record System - Student Admin");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setBackground(Color.gray);
        c.add(panel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 2;
        constraints.gridy = 0;
        panel.add(StudentLabel, constraints);

        constraints.gridx = 2;
        constraints.gridy = 2;
        panel.add(button1, constraints);
        button1.addActionListener(this);

        constraints.gridx = 2;
        constraints.gridy = 4;
        panel.add(button2, constraints);
        button2.addActionListener(this);

        constraints.gridx = 2;
        constraints.gridy = 6;
        panel.add(button3, constraints);
        button3.addActionListener(this);

        constraints.gridx = 2;
        constraints.gridy = 8;
        panel.add(button4, constraints);
        button4.addActionListener(this);

        constraints.gridx = 2;
        constraints.gridy = 10;
        panel.add(button5, constraints);
        button5.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("List All Students")) {
            listStudent();
        } else if (cmd.equals("Add New Student")) {
            addNewStudent();
        } else if (cmd.equals("Return To Main Menu")) {
            Mainmenu Menu = new Mainmenu();
            Menu.setVisible(true);
            Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Menu.setResizable(false);
            this.setVisible(false);
        } else if (cmd.equals("Save To...")) {
            Student.saveStuFile();
        } else if (cmd.equals("Return")) {
            StudentAdminMenu StudentMenu = new StudentAdminMenu();
            StudentMenu.setVisible(true);
            StudentMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            StudentMenu.setResizable(false);
            this.setVisible(false);
        } else if (cmd.equals("Confirm Add")) {

            String title = "Add New Student";
            stuID = stuIDtext.getText().toUpperCase();
            stuName = stuNametext.getText();
            YOB = YOBtext.getText();

            if (stuID.trim().isEmpty() || stuName.trim().isEmpty() || YOB.trim().isEmpty()) {
                JOptionPane.showMessageDialog(confirm, "Please Fill All The Blank.", title, JOptionPane.ERROR_MESSAGE);
            } else {
                if (Validation.stuYOB(YOB)) {
                    if (!RecordSystem.findStu(stuID)) {
                        Student temp = new Student(stuID, stuName, Integer.parseInt(YOB));
                        RecordSystem.StudentList.add(temp);
                        temp.addEnrollment("Java", "-");
                        temp.addEnrollment("OO Design", "-");
                        temp.addEnrollment("Software Testing", "-");
                        temp.addEnrollment("J2EE", "-");
                        temp.addEnrollment("Software Architecture", "-");
                        temp.addEnrollment("Design Patterns", "-");
                        JOptionPane.showMessageDialog(confirm, "Add Student Successful.Now You Can Return.", title,
                                JOptionPane.PLAIN_MESSAGE);
                    } else if (RecordSystem.findStu(stuID)) {
                        JOptionPane.showMessageDialog(confirm, "Student ID Has Already Been Used.", title,
                                JOptionPane.PLAIN_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(confirm, "Wrong Input", title, JOptionPane.ERROR_MESSAGE);
                }
            }

        } else if (cmd.equals("Enroll Student")) {
            enrollAStudent();
        } else if (cmd.equals("Confirm Enroll")) {
            String title = "Enroll A Student";
            stuID = stuIDtext.getText();
            courseName = courseSelector.getSelectedItem().toString();
            academyYear = yearSelector.getSelectedItem().toString();
            runNumber = runtext.getText();

            if (stuID.trim().isEmpty() || runNumber.trim().isEmpty()) {
                JOptionPane.showMessageDialog(confirmEnroll, "Please Fill All The Blank.", title, JOptionPane.
                        ERROR_MESSAGE);
            } else {
                if (courseName.equals("-Select Course-") || academyYear.equals("-Select year-")) {
                    JOptionPane.showMessageDialog(confirmEnroll, "Please Select Correct Course Or Academy Year.",
                            title, JOptionPane.ERROR_MESSAGE);
                } else {

                    if (RecordSystem.findStu(stuID)) {

                        for (int i = 0; i < RecordSystem.StudentList.size(); i++) {

                            Student temp = RecordSystem.StudentList.get(i);
                            if (stuID.equals(temp.getStudId())) {
                                grade = temp.getGrade(courseName);

                            }
                        }
                        if (grade.equals("Exp")) {
                            JOptionPane.showMessageDialog(confirmEnroll, "This Student Has Already Got A " +
                                    "Exemption For "
                                    + courseName + ".", title, JOptionPane.ERROR_MESSAGE);
                        }
                        else if (Validation.grade(grade)) {
                            if (Integer.parseInt(grade) > 50) {
                                JOptionPane.showMessageDialog(confirmEnroll, "This Student Has Already Pass "
                                        + courseName + ".", title, JOptionPane.ERROR_MESSAGE);
                            }
                            else if(Integer.parseInt(grade) < 50){
                                if (RecordSystem.checkValid(courseName, academyYear, runNumber)) {

                                    for (int i = 0; i < RecordSystem.StudentList.size(); i++) {
                                        if (stuID.equals(RecordSystem.StudentList.get(i).getStudId())) {
                                            Student temp1 = RecordSystem.StudentList.get(i);
                                            temp1.setGrade(courseName, "Run " + runNumber);
                                            JOptionPane.showMessageDialog(confirmEnroll, "This Student Has Enrolled in "
                                                    + courseName + " Successfully.", title, JOptionPane.PLAIN_MESSAGE);
                                        }
                                    }

                                }
                            }
                        }

                        else {
                                if (RecordSystem.checkValid(courseName, academyYear, runNumber)) {

                                    for (int i = 0; i < RecordSystem.StudentList.size(); i++) {
                                        if (stuID.equals(RecordSystem.StudentList.get(i).getStudId())) {
                                            Student temp1 = RecordSystem.StudentList.get(i);
                                            temp1.setGrade(courseName, "Run " + runNumber);
                                            JOptionPane.showMessageDialog(confirmEnroll, "This Student Has Enrolled in "
                                                    + courseName + " Successfully.", title, JOptionPane.PLAIN_MESSAGE);
                                        }
                                    }

                                }
                            }


                    } else {
                        JOptionPane.showMessageDialog(confirmEnroll, "Student ID Is Incorrect,Please " +
                                "Check Again.", title, JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        }//end of confirm enroll action.

        else if (cmd.equals("Process Payment")) {
            processPayment();
        } else if (cmd.equals("Pay")) {

            String title = "Process A Payment";
            stuID = stuIDtext.getText().toUpperCase();
            payAmount = paytext.getText();

            if (stuID.trim().isEmpty() || payAmount.trim().isEmpty()) {
                JOptionPane.showMessageDialog(confirmEnroll, "Please Fill All The Blank.", title, JOptionPane.
                        ERROR_MESSAGE);
            } else {
                if (RecordSystem.findStu(stuID)) {
                    for (int i = 0; i < RecordSystem.StudentList.size(); i++) {
                        if (stuID.equals(RecordSystem.StudentList.get(i).getStudId())) {
                            Student temp2 = RecordSystem.StudentList.get(i);
                            temp2.setFeePaid(temp2.getFeePaid() + Integer.parseInt(payAmount));
                            JOptionPane.showMessageDialog(confirmEnroll, "Successful!." + "\n"
                                            + "Student ID :" + temp2.getStudId() + "\n" +
                                            "Student Name :" + temp2.getStudName() + "\n" + "Fee Paid:" + temp2.getFeePaid(),
                                    title, JOptionPane.PLAIN_MESSAGE
                            );
                        }


                    }
                } else {
                    JOptionPane.showMessageDialog(confirmEnroll, "Student ID Is Incorrect,Please " +
                            "Check Again.", title, JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (cmd.equals("List Students")) {
            listStudent();
        }
        else if (cmd.equals("Confirm")){

            String title = "Save File...";
            saveAction = saveSelector.getSelectedItem().toString();

            if(saveAction.equals("Update")){
                saveStudentFile();
                JOptionPane.showMessageDialog(SaveButton, "Update File Success", title, JOptionPane.PLAIN_MESSAGE);
            }
            else if(saveAction.equals("Save To ...")){
                Student.saveStuFile();
            }
            else if(saveAction.equals("-Choose Save Action-")){
                JOptionPane.showMessageDialog(SaveButton, "Please Select A Save Action", title, JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //display all student in tabel.
    public void listStudent() {

        ArrayList<Student> StudentList = (ArrayList<Student>) RecordSystem.StudentList;

        panel.removeAll();
        panel.updateUI();
        panel.setLayout(new GridBagLayout());
        setSize(1200, 800);

        JLabel label = new JLabel("Enrolled Students Information");
        JButton ReturnButton = new JButton("Return");


        model = new DefaultTableModel(rows, column);
        Object[][] rows = {};

        JTable StuTable = new JTable(model);
        JScrollPane table = new JScrollPane(StuTable);
        JPanel savePanel = new JPanel(new GridBagLayout());

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


        String[] ColumnNames = {"Student ID", "Student Name", "Birth Year", "Java", "OO Design",
                "Software Testing", "J2EE", "Software Architecture", "Design Patterns", "Paid"};
        for (int i = 0; i < ColumnNames.length; i++) {
            model.addColumn(ColumnNames[i]);
        }

        for (int i = 0; i < StudentList.size(); i++) {

            Student temp = StudentList.get(i);
            String[] rowData = {
                    temp.getStudId(), temp.getStudName(), Integer.toString(temp.getDOB()),
                    temp.getGrade("Java"), temp.getGrade("OO Design"), temp.getGrade("Software Testing"),
                    temp.getGrade("J2EE"), temp.getGrade("Software Architecture"), temp.getGrade("Design Patterns"),
                    Double.toString(temp.getFeePaid())
            };
            model.addRow(rowData);

        }
    }

    //add a new student.
    public void addNewStudent() {

        panel.removeAll();
        panel.updateUI();
        panel.setLayout(new GridBagLayout());

        JPanel stuIDPanel = new JPanel(new GridBagLayout());
        JPanel stuNamePanel = new JPanel(new GridBagLayout());
        JPanel YOBPanel = new JPanel(new GridBagLayout());
        JPanel controlPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 1, 1, 1);
        constraints.gridx = 0;
        constraints.gridy = 0;

        JLabel label = new JLabel("Add A New Student");
        panel.add(label, constraints);

        JLabel stuIDLabel = new JLabel(" Student ID :");
        constraints.gridx = 0;
        constraints.gridy = 1;
        stuIDPanel.add(stuIDLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        stuIDPanel.add(stuIDtext, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(stuIDPanel, constraints);


        JLabel stuNameLabel = new JLabel(" Student Name :");
        constraints.gridx = 0;
        constraints.gridy = 2;
        stuIDPanel.add(stuNameLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        stuIDPanel.add(stuNametext, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(stuNamePanel, constraints);


        JLabel YOBLabel = new JLabel(" Year Of Birth :  ");
        constraints.gridx = 0;
        constraints.gridy = 3;
        YOBPanel.add(YOBLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 3;
        YOBPanel.add(YOBtext, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(YOBPanel, constraints);


        constraints.gridx = 0;
        constraints.gridy = 4;
        controlPanel.add(confirm, constraints);
        confirm.addActionListener(this);
        constraints.gridx = 1;
        constraints.gridy = 4;
        controlPanel.add(listButton, constraints);
        listButton.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(controlPanel, constraints);


        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(returnButton, constraints);
        returnButton.addActionListener(this);


    }

    //enroll.
    public void enrollAStudent() {

        panel.removeAll();
        panel.updateUI();
        panel.setLayout(new GridBagLayout());

        JPanel stuIDPanel = new JPanel(new GridBagLayout());
        JPanel coursePanel = new JPanel(new GridBagLayout());
        JPanel yearPanel = new JPanel(new GridBagLayout());
        JPanel runPanel = new JPanel(new GridBagLayout());
        JPanel controlPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        JLabel label = new JLabel("Enroll A Student.");
        panel.add(label, constraints);

        JLabel stuIDLabel = new JLabel(" Student ID :");
        constraints.gridx = 0;
        constraints.gridy = 1;
        stuIDPanel.add(stuIDLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        stuIDPanel.add(stuIDtext, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(stuIDPanel, constraints);

        courseSelector.addItem("-Select Course-");
        courseSelector.addItem("Java");
        courseSelector.addItem("OO Design");
        courseSelector.addItem("Software Testing");
        courseSelector.addItem("J2EE");
        courseSelector.addItem("Software Architecture");
        courseSelector.addItem("Design Patterns");

        JLabel courseLabel = new JLabel("Select Course :");
        constraints.gridx = 0;
        constraints.gridy = 2;
        coursePanel.add(courseLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        coursePanel.add(courseSelector, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(coursePanel, constraints);

        JLabel yearLabel = new JLabel("Select Academy Year");
        constraints.gridx = 0;
        constraints.gridy = 3;
        yearPanel.add(yearLabel, constraints);

        yearSelector.addItem("-Select year-");
        yearSelector.addItem("2014");
        yearSelector.addItem("2015");
        constraints.gridx = 1;
        constraints.gridy = 3;
        yearPanel.add(yearSelector, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(yearPanel, constraints);

        JLabel runLabel = new JLabel("Run Number :");
        constraints.gridx = 0;
        constraints.gridy = 4;
        runPanel.add(runLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 4;
        runPanel.add(runtext, constraints);
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(runPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        controlPanel.add(confirmEnroll, constraints);
        confirmEnroll.addActionListener(this);
        constraints.gridx = 1;
        constraints.gridy = 5;
        controlPanel.add(listButton, constraints);
        listButton.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(controlPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(returnButton, constraints);
        returnButton.addActionListener(this);

    }

    //process a payment.
    public void processPayment() {

        panel.removeAll();
        panel.updateUI();
        panel.setLayout(new GridBagLayout());

        JPanel stuIDPanel = new JPanel(new GridBagLayout());
        JPanel payPanel = new JPanel(new GridBagLayout());
        JPanel controlPanel = new JPanel(new GridBagLayout());

        JButton payButton = new JButton("Pay");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        JLabel label = new JLabel("Process A Payment");
        panel.add(label, constraints);

        JLabel stuIDLabel = new JLabel(" Student ID :");
        constraints.gridx = 0;
        constraints.gridy = 1;
        stuIDPanel.add(stuIDLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        stuIDPanel.add(stuIDtext, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(stuIDPanel, constraints);

        JLabel payLabel = new JLabel("Payment Amount :");
        constraints.gridx = 0;
        constraints.gridy = 2;
        payPanel.add(payLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        payPanel.add(paytext, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(payPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        controlPanel.add(payButton, constraints);
        payButton.addActionListener(this);
        constraints.gridx = 1;
        constraints.gridy = 3;
        controlPanel.add(listButton, constraints);
        listButton.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(controlPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(returnButton, constraints);
        returnButton.addActionListener(this);

    }

    //update student file.
    public void saveStudentFile() {

        String title = "Save File";
        try{
            String file = "studentlist.txt";
            FileWriter fr = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fr);
            for (int i = 0; i < RecordSystem.StudentList.size(); i++) {
                bw.write(RecordSystem.StudentList.get(i).getStudId() + ",");
                bw.write(RecordSystem.StudentList.get(i).getStudName() + ",");
                bw.write(RecordSystem.StudentList.get(i).getDOB() + ",");
                bw.write(RecordSystem.StudentList.get(i).getGrade("Java") + ",");
                bw.write(RecordSystem.StudentList.get(i).getGrade("OO Design") + ",");
                bw.write(RecordSystem.StudentList.get(i).getGrade("Software Testing") + ",");
                bw.write(RecordSystem.StudentList.get(i).getGrade("J2EE") + ",");
                bw.write(RecordSystem.StudentList.get(i).getGrade("Software Architecture") + ",");
                bw.write(RecordSystem.StudentList.get(i).getGrade("Design Patterns") + ",");
                bw.write(Double.toString(RecordSystem.StudentList.get(i).getFeePaid()));
                bw.newLine();
            }

            bw.close();
            fr.close();
        } catch(Exception ex)
        {System.out.print("error!!!");}
    }
}

