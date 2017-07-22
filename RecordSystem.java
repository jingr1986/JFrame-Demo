import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ranjing on 18/05/2014.
 */
public class RecordSystem {

    public static ArrayList<Student> StudentList = new ArrayList<Student>();
    public static ArrayList<Staff> StaffList = new ArrayList<Staff>();
    public static ArrayList<Course> CourseInfoList = new ArrayList<Course>();

    //pre-load student data.
    public static void readStudentData(){

        String StudentID = null;
        String StudentName = null;
        int DOB = 0;
        double FeePaid = 0;
        String GradeJava = null;
        String GradeOOD = null;
        String GradeST = null;
        String GradeJ2EE = null;
        String GradeSA = null;
        String GradeDP = null;


        try {
            BufferedReader StudentFile = new BufferedReader(new FileReader("studentlist.txt"));
            String line;
            line = StudentFile.readLine();
            int count;
            while (line!=null) {

                StringTokenizer lineST = new StringTokenizer(line,",$");
                count=1;

                while(lineST.hasMoreTokens() && count<=10) {

                    if (count==1) {StudentID = lineST.nextToken();}
                    if (count==2) {StudentName = lineST.nextToken();}
                    if (count==3) {DOB = Integer.parseInt(lineST.nextToken());}
                    if (count==4) {GradeJava = lineST.nextToken();}
                    if (count==5) {GradeOOD = lineST.nextToken();}
                    if (count==6) {GradeST = lineST.nextToken();}
                    if (count==7) {GradeJ2EE = lineST.nextToken();}
                    if (count==8) {GradeSA = lineST.nextToken();}
                    if (count==9) {GradeDP = lineST.nextToken();}
                    if (count==10) {FeePaid = Double.parseDouble(lineST.nextToken());}

                    count++;
                }

                Student temp = new Student (StudentID,StudentName,DOB);
                temp.addEnrollment("Java",GradeJava);
                temp.addEnrollment("OO Design",GradeOOD);
                temp.addEnrollment("Software Testing",GradeST);
                temp.addEnrollment("J2EE",GradeJ2EE);
                temp.addEnrollment("Software Architecture",GradeSA);
                temp.addEnrollment("Design Patterns",GradeDP);
                temp.setFeePaid(FeePaid);
                StudentList.add(temp);



                if(GradeJava.equals("Run 1")||GradeJava.equals("Run 2")||GradeJava.equals("Run 3")||GradeJava.equals("Run 4")||GradeJava.equals("Run 5")){

                    int run = Integer.parseInt(Character.toString(GradeJava.charAt(4)));

                  for(int i=0;i<CourseInfoList.size();i++){

                      if(CourseInfoList.get(i).getCourseName().equals("Java")){

                          Course tempCourse = CourseInfoList.get(i);
                          tempCourse.addStudentToRun(run,"2014",temp);

                      }
                  }
                }
                if(GradeOOD.equals("Run 1")||GradeOOD.equals("Run 2")||GradeOOD.equals("Run 3")||GradeOOD.equals("Run 4")||GradeOOD.equals("Run 5")){

                    int run = Integer.parseInt(Character.toString(GradeOOD.charAt(4)));

                    for(int i=0;i<CourseInfoList.size();i++){
                        if(CourseInfoList.get(i).getCourseName().equals("OO Design")){
                            Course tempCourse = CourseInfoList.get(i);
                            tempCourse.addStudentToRun(run,"2014",temp);
                        }
                    }
                }
                if(GradeST.equals("Run 1")||GradeST.equals("Run 2")||GradeST.equals("Run 3")||GradeST.equals("Run 4")||GradeST.equals("Run 5")){
                    int run = Integer.parseInt(Character.toString(GradeST.charAt(4)));

                    for(int i=0;i<CourseInfoList.size();i++){
                        if(CourseInfoList.get(i).getCourseName().equals("Software Testing")){
                            Course tempCourse = CourseInfoList.get(i);
                            tempCourse.addStudentToRun(run,"2014",temp);
                        }
                    }
                }
                if(GradeJ2EE.equals("Run 1")||GradeJ2EE.equals("Run 2")||GradeJ2EE.equals("Run 3")||GradeJ2EE.equals("Run 4")||GradeJ2EE.equals("Run 5")){
                    int run = Integer.parseInt(Character.toString(GradeJ2EE.charAt(4)));

                    for(int i=0;i<CourseInfoList.size();i++){
                        if(CourseInfoList.get(i).getCourseName().equals("J2EE")){
                            Course tempCourse = CourseInfoList.get(i);
                            tempCourse.addStudentToRun(run,"2014",temp);
                        }
                    }
                }
                if(GradeSA.equals("Run 1")||GradeSA.equals("Run 2")||GradeSA.equals("Run 3")||GradeSA.equals("Run 4")||GradeSA.equals("Run 5")){
                    int run = Integer.parseInt(Character.toString(GradeSA.charAt(4)));

                    for(int i=0;i<CourseInfoList.size();i++){
                        if(CourseInfoList.get(i).getCourseName().equals("Software Architecture")){
                            Course tempCourse = CourseInfoList.get(i);
                            tempCourse.addStudentToRun(run,"2014",temp);
                        }
                    }
                }
                if(GradeDP.equals("Run 1")||GradeDP.equals("Run 2")||GradeDP.equals("Run 3")||GradeDP.equals("Run 4")||GradeDP.equals("Run 5")){
                    int run = Integer.parseInt(Character.toString(GradeDP.charAt(4)));

                    for(int i=0;i<CourseInfoList.size();i++){
                        if(CourseInfoList.get(i).getCourseName().equals("Design Patterns")){
                            Course tempCourse = CourseInfoList.get(i);
                            tempCourse.addStudentToRun(run,"2014",temp);
                        }
                    }
                }

                line = StudentFile.readLine();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Cannot find the file.");
        }
        catch(IOException e){
            System.out.println("Fail to read file.");
        }

    }

    //pre-load staff data.
    public static void readStaffData(){


        String StaffID = null;
        String StaffName = null;
        double tempSalary = 0;
        String Role = null;

        try {
            BufferedReader StaffFile = new BufferedReader(new FileReader("staffList.txt"));
            String line;
            line = StaffFile.readLine();
            int count;
            while (line!=null) {

                StringTokenizer lineST = new StringTokenizer(line,",$");
                count=1;

                while(lineST.hasMoreTokens() && count<=4) {

                    if (count==1) {StaffID = lineST.nextToken();}
                    if (count==2) {StaffName = lineST.nextToken();}
                    if (count==3) {tempSalary = Double.parseDouble(lineST.nextToken());}
                    if (count==4) {Role = lineST.nextToken();}

                    count++;
                }

                if(Role.equals("Manager")||Role.equals("Admin")){
                    Staff temp = new CasualStaff(StaffID,StaffName,tempSalary,Role);
                    StaffList.add(temp);
                }
                else if(Role.equals("Instructor")){
                    Staff temp = new Instructor(StaffID,StaffName,tempSalary,0.0,Role);
                    temp.computeSalary();
                    StaffList.add(temp);
                }
                line = StaffFile.readLine();

            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Cannot find the file.");
        }
        catch(IOException e){
            System.out.println("Fail to read file.");
        }
    }

    //pre-load course data.
    public static void readCourseData(){

        String CourseName = null;
        String JavaIns = null;
        String OODIns = null;
        String STIns = null;
        String J2eeIns = null;
        String SAIns = null;
        String DPIns = null;
        String year = null;
        int runNumber = 0;



        try {
            BufferedReader CourseFile = new BufferedReader(new FileReader("courseInfo.txt"));
            String line;
            line = CourseFile.readLine();
            int count;

            Course temp1 = new Course("Java", 0.0, 0.0);
            CourseInfoList.add(temp1);
            Course temp2 = new Course("OO Design", 0.0, 0.0);
            CourseInfoList.add(temp2);
            Course temp3 = new Course("Software Testing", 0.0, 0.0);
            CourseInfoList.add(temp3);
            Course temp4 = new Course("J2EE", 0.0, 0.0);
            CourseInfoList.add(temp4);
            Course temp5 = new Course("Software Architecture", 0.0, 0.0);
            CourseInfoList.add(temp5);
            Course temp6 = new Course("Design Patterns", 0.0, 0.0);
            CourseInfoList.add(temp6);

            while (line!=null) {

                StringTokenizer lineST = new StringTokenizer(line,",$");
                count=1;
                while(lineST.hasMoreTokens() && count<=10) {


                        if (count == 1) {
                            year = lineST.nextToken();
                        }
                        if (count == 2) {
                            runNumber = Integer.parseInt(lineST.nextToken());
                        }
                        if (count == 3) {
                            JavaIns = lineST.nextToken();
                            temp1.setRuns(runNumber,year,JavaIns);
                        }
                        if (count == 4) {
                            OODIns = lineST.nextToken();
                            temp2.setRuns(runNumber,year,OODIns);
                        }
                        if (count == 5) {
                            STIns = lineST.nextToken();
                            temp3.setRuns(runNumber,year,STIns);
                        }
                        if (count == 6) {
                            J2eeIns = lineST.nextToken();
                            temp4.setRuns(runNumber,year,J2eeIns);
                        }
                        if (count == 7) {
                            SAIns = lineST.nextToken();
                            temp5.setRuns(runNumber,year,SAIns);
                        }
                        if (count == 8) {
                            DPIns = lineST.nextToken();
                            temp6.setRuns(runNumber,year,DPIns);
                        }

                        count++;

                    }
                    line = CourseFile.readLine();


                }


            }

        catch (FileNotFoundException e) {
            System.out.println("Cannot find the file.");
        }
        catch(IOException e){
            System.out.println("Fail to read file.");
        }


    }

    //find students use student ID.
    public static boolean findStu(String stuID){

        for(int i=0;i<StudentList.size();i++){
            System.out.println(StudentList.get(i).getStudId());
            if(stuID.equals(StudentList.get(i).getStudId())){

                return true;
            }

        }
        return false;
    }

    //find course use course name.
    public static boolean checkCourse(String CourseName){

        for(int i=0;i<CourseInfoList.size();i++) {


            if(CourseName.equals(CourseInfoList.get(i).getCourseName())){

                return true;
            }

        }
        return false;

    }

    //use to check validation of a course.
    public static boolean checkValid(String CourseName,String academyYear,String runNumber){

        for(int i=0;i<CourseInfoList.size();i++) {

            if(checkCourse(CourseName)) {
                if (Integer.parseInt(academyYear) == 2014) {
                    if (CourseInfoList.get(i).getRuns2014()!= null && CourseInfoList.get(i).getRuns2014().length!= 0) {
                        System.out.println(CourseInfoList.get(i).getRuns2014()[Integer.parseInt(runNumber)].getOpenStatus());
                        if(CourseInfoList.get(i).getRuns2014()[Integer.parseInt(runNumber)].getOpenStatus())
                            if(Integer.parseInt(runNumber)<=CourseInfoList.get(i).getActiveRun2014()) {
                                return true;
                        }
                    }
                }
                else if (Integer.parseInt(academyYear) == 2015) {
                    if (CourseInfoList.get(i).getRuns2015()!= null && CourseInfoList.get(i).getRuns2015().length!= 0) {
                        if(Integer.parseInt(runNumber)<=CourseInfoList.get(i).getActiveRun2015()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;

    }


    public static void main (String[] args){

        if(args.length != 3){
            System.out.println("Need To Specify the arguments");
            System.exit(-1);
        }

        readCourseData();
        readStudentData();
        readStaffData();


        Mainmenu Menu = new  Mainmenu();
        Menu.setVisible(true);
        Menu.setResizable(false);



    }



}
