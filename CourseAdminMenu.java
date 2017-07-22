import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ranjing on 18/05/2014.
 */
public class CourseAdminMenu extends JFrame implements ActionListener{

    public ArrayList<Student> enrolledStudents = new ArrayList<Student>();

    CourseRun [] runs2014 = new CourseRun[5];
    CourseRun [] runs2015 = new CourseRun[5];

    public static ArrayList<String> stuId = new ArrayList<String>();
    public static ArrayList<String> stuGrade = new ArrayList<String>();

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    JPanel panel = new JPanel(new GridBagLayout());
    JPanel controlPanel = new JPanel(new GridBagLayout());

    JLabel CourseLabel = new JLabel("===Course Admin===");

    JButton button1 = new JButton("Generate Results");
    JButton button2 = new JButton("Disable A Course");
    JButton button3 = new JButton("Return To Main Menu");
    JButton generate = new JButton("Generate");
    JButton returnButton = new JButton("Return");
    JButton disableButton = new JButton("Disable");
    JButton saveGradeButton = new JButton("Save Grade To ...");
    JButton listButton = new JButton("List Students");

    JComboBox courseSelector = new JComboBox();
    JComboBox yearSelector = new JComboBox();
    JComboBox saveSelector = new JComboBox();

    JTextField runtext = new JTextField(10);

    String courseName = null;
    String academyYear = null;
    String runNumber = null;
    String grade = null;

    boolean check2014 = false;
    boolean check2015 = false;
    boolean check = false;

    public CourseAdminMenu(){

        super("Student Record System - Course Admin");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setBackground(Color.gray);
        c.add(panel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets (10,10,10,10);

        constraints.gridx = 2;
        constraints.gridy = 0;
        panel.add(CourseLabel,constraints);

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


    }

    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();

        if(cmd.equals("Return To Main Menu")){
            Mainmenu Menu = new Mainmenu();
            Menu.setVisible(true);
            Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Menu.setResizable(false);
            this.setVisible(false);
        }

        else if(cmd.equals("Return")){
            CourseAdminMenu CourseMenu = new CourseAdminMenu();
            CourseMenu.setVisible(true);
            CourseMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            CourseMenu.setResizable(false);
            this.setVisible(false);
        }

        else if(cmd.equals("Generate Results")){
           generateResult();
        }

        else if(cmd.equals("Generate")){

            String title = "Generate Result.";

            courseName = courseSelector.getSelectedItem().toString();
            academyYear = yearSelector.getSelectedItem().toString();
            runNumber = runtext.getText();

            CourseRun[] runTemp;

            if (runNumber.trim().isEmpty()) {
                JOptionPane.showMessageDialog(generate, "Please Fill The Blank, And Try Again.", title, JOptionPane.
                        ERROR_MESSAGE);
            }
            else{
                if (courseName.equals("-Select Course-") || academyYear.equals("-Select year-")) {
                    JOptionPane.showMessageDialog(generate, "Please Select Correct Course Or Academy Year.",
                            title, JOptionPane.ERROR_MESSAGE);
                }
                else{
                     if(RecordSystem.checkValid(courseName,academyYear,runNumber)){
                         for(int i=0;i<RecordSystem.CourseInfoList.size();i++){
                             Course course = RecordSystem.CourseInfoList.get(i);
                             runTemp = course.getRuns2014();

                             for(int j=0;j< runTemp.length;j++){

                                enrolledStudents = runTemp[j].getStudentsList();

                                for(int k=0;k<enrolledStudents.size();k++){

                                    Student enrolltemp = enrolledStudents.get(k);
                                    grade = enrolltemp.getGrade(courseName);
                                    if(!Validation.grade(grade)) {
                                        if (!grade.equals("Exp")) {

                                            if (!grade.equals("-")) {


                                                Random x = new Random();
                                                enrolltemp.setGrade(courseName, Integer.toString(x.nextInt(100)));
                                                stuId.add(enrolltemp.getStudId());
                                                stuGrade.add(enrolltemp.getGrade(courseName));

                                                check = true;

                                            }
                                        }
                                    }

                                }
                           }
                         }

                     }
                }

            }
            if(check) {
                JOptionPane.showMessageDialog(generate, "Sucessful!",
                        title, JOptionPane.PLAIN_MESSAGE
                );
            }
            else{
                JOptionPane.showMessageDialog(generate, "Does Not Need Generate Results.",
                        title, JOptionPane.PLAIN_MESSAGE
                );
            }
        }//end of generate action.

        else if(cmd.equals("Disable A Course")){
            disCourse();
        }

        else if(cmd.equals("Disable")){

            String title = "Disable Course";

            courseName = courseSelector.getSelectedItem().toString();
            academyYear = yearSelector.getSelectedItem().toString();
            runNumber = runtext.getText();

            if (runNumber.trim().isEmpty()) {
                JOptionPane.showMessageDialog(generate, "Please Fill The Blank, And Try Again.", title, JOptionPane.
                        ERROR_MESSAGE);
            }else {
                if (courseName.equals("-Select Course-") || academyYear.equals("-Select year-")) {
                    JOptionPane.showMessageDialog(generate, "Please Select Correct Course Or Academy Year.",
                            title, JOptionPane.ERROR_MESSAGE);
                }else{

                      for(int i=0;i<RecordSystem.CourseInfoList.size();i++)
                          if (courseName.equals(RecordSystem.CourseInfoList.get(i).getCourseName())) {
                              Course course = RecordSystem.CourseInfoList.get(i);
                              if (Integer.parseInt(academyYear) == 2014) {
                                  runs2014 = course.getRuns2014();
                                  try {
                                      for (int a = 0; a < runs2014.length; a++) {

                                          if ((Integer.parseInt(runNumber)) == runs2014[a].getRunNumber()) {
                                              runs2014[a].disable();
                                              JOptionPane.showMessageDialog(disableButton, "Disable A Course Success.", title,
                                                      JOptionPane.PLAIN_MESSAGE);
                                              check2014 = true;
                                          }
                                      }
                                  }
                                  catch(Exception exception)
                                  {}



                                  if (!check2014) {
                                      JOptionPane.showMessageDialog(disableButton, "This Run Of Course Dose Not Exist.",
                                              title, JOptionPane.ERROR_MESSAGE);
                                  }
                              } else if (Integer.parseInt(academyYear) == 2015) {
                                  runs2015 = course.getRuns2015();
                                  try {
                                      for (int b = 0; b < runs2015.length; b++) {
                                          if ((Integer.parseInt(runNumber)) == runs2015[b].getRunNumber()) {
                                              runs2015[b].disable();
                                              JOptionPane.showMessageDialog(disableButton, "Disable A Course Success.",
                                                      title, JOptionPane.PLAIN_MESSAGE);
                                              check2015 = true;
                                          }
                                      }
                                  }catch(Exception exception)
                                  {}
                                  if (!check2015) {
                                      JOptionPane.showMessageDialog(disableButton, "This Run Of Course Dose Not Exist.",
                                              title, JOptionPane.ERROR_MESSAGE);
                                  }
                              }
                          }

                }

            }

        }//end of disable action.

        else if(cmd.equals("Save Grade To ...")){
            saveGradeFile();
        }

        else if(cmd.equals("List Students")){
            StudentAdminMenu StudentMenu = new StudentAdminMenu();
            StudentMenu.setVisible(true);
            StudentMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            StudentMenu.setResizable(false);
            this.setVisible(false);
            StudentMenu.listStudent();
        }


    }
    //generate a result
    public void generateResult() {

        panel.removeAll();
        panel.updateUI();
        panel.setLayout(new GridBagLayout());

        JPanel coursePanel = new JPanel(new GridBagLayout());
        JPanel yearPanel = new JPanel(new GridBagLayout());
        JPanel runPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        JLabel label = new JLabel("Generate Result");
        panel.add(label,constraints);

        courseSelector.addItem("-Select Course-");
        courseSelector.addItem("Java");
        courseSelector.addItem("OO Design");
        courseSelector.addItem("Software Testing");
        courseSelector.addItem("J2EE");
        courseSelector.addItem("Software Architecture");
        courseSelector.addItem("Design Patterns");
        JLabel courseLabel = new JLabel("Select Course :");
        constraints.gridx = 0;
        constraints.gridy = 1;
        coursePanel.add(courseLabel,constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        coursePanel.add(courseSelector,constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(coursePanel,constraints);

        JLabel yearLabel = new JLabel("Select Academy Year:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        yearPanel.add(yearLabel,constraints);
        yearSelector.addItem("-Select year-");
        yearSelector.addItem("2014");
        yearSelector.addItem("2015");
        constraints.gridx = 1;
        constraints.gridy = 2;
        yearPanel.add(yearSelector,constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(yearPanel,constraints);

        JLabel runLabel = new JLabel("Run Number :");
        constraints.gridx = 0;
        constraints.gridy = 3;
        runPanel.add(runLabel,constraints);
        constraints.gridx = 1;
        constraints.gridy = 3;
        runPanel.add(runtext,constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(runPanel,constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        controlPanel.add(generate,constraints);
        generate.addActionListener(this);
        constraints.gridx = 1;
        constraints.gridy = 4;
        controlPanel.add(saveGradeButton,constraints);
        saveGradeButton.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(controlPanel,constraints);
        generate.addActionListener(this);

        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(listButton,constraints);
        listButton.addActionListener(this);

        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(returnButton,constraints);
        returnButton.addActionListener(this);





    }

    //disable a course
    public void disCourse(){

        panel.removeAll();
        panel.updateUI();
        panel.setLayout(new GridBagLayout());

        JPanel coursePanel = new JPanel(new GridBagLayout());
        JPanel yearPanel = new JPanel(new GridBagLayout());
        JPanel runPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        JLabel label = new JLabel("Disable Course");
        panel.add(label,constraints);

        courseSelector.addItem("-Select Course-");
        courseSelector.addItem("Java");
        courseSelector.addItem("OO Design");
        courseSelector.addItem("Software Testing");
        courseSelector.addItem("J2EE");
        courseSelector.addItem("Software Architecture");
        courseSelector.addItem("Design Patterns");
        JLabel courseLabel = new JLabel("Select Course :");
        constraints.gridx = 0;
        constraints.gridy = 1;
        coursePanel.add(courseLabel,constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        coursePanel.add(courseSelector,constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(coursePanel,constraints);

        JLabel yearLabel = new JLabel("Select Academy Year");
        constraints.gridx = 0;
        constraints.gridy = 2;
        yearPanel.add(yearLabel,constraints);
        yearSelector.addItem("-Select year-");
        yearSelector.addItem("2014");
        yearSelector.addItem("2015");
        constraints.gridx = 1;
        constraints.gridy = 2;
        yearPanel.add(yearSelector,constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(yearPanel,constraints);

        JLabel runLabel = new JLabel("Run Number :");
        constraints.gridx = 0;
        constraints.gridy = 3;
        runPanel.add(runLabel,constraints);
        constraints.gridx = 1;
        constraints.gridy = 3;
        runPanel.add(runtext,constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(runPanel,constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(disableButton,constraints);
        disableButton.addActionListener(this);

        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(returnButton,constraints);
        returnButton.addActionListener(this);

    }

    //save a grade file
    public void saveGradeFile(){
        courseName = courseSelector.getSelectedItem().toString();
        String data = null;
        String stuID = null;
        String grade = null;
        for(int i=0;i<CourseAdminMenu.stuId.size();i++){

            stuID = stuId.get(i);
            grade = stuGrade.get(i);

            if(i==0) {
                data = courseName+"\n"+stuID +":"+ grade;
            }
            else {
                data = data+ "\n" +stuID+":"+grade;
            }
        }
        SaveFile save = new SaveFile();
        save.saveto(data);
    }


}
