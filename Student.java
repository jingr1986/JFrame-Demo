import java.util.*;
public class Student
{
	/**
	 * 
	*/
	private String studId , studName ;
	private int DOB;
    private ArrayList <Enrollment> enrollmentList = new ArrayList<Enrollment>();
	private double totalFee , feePaid;
	private boolean instructor;
	
	public Student(String studId,String studName,int DOB)
	{
		this.studId=studId;
		this.studName=studName;
		this.DOB=DOB;
		this.enrollmentList=new ArrayList<Enrollment>();
		this.totalFee=0.0;
		this.feePaid=0.0;
		this.instructor=false;
	}//end of the constructor block

    public Student(String studID,String studName,int DOB,ArrayList enrollmentList,double feePaid){
        this.studId=studID;
        this.studName=studName;
        this.DOB=DOB;
        this.enrollmentList = enrollmentList;
        this.feePaid = feePaid;
    }

	public String getStudId() {
		return studId;
	}

	public void setStudId(String studId) {
		this.studId = studId;
	}

	public String getStudName() {
		return studName;
	}

	public void setStudName(String studName) {
		this.studName = studName;
	}

	public int getDOB() {
		return DOB;
	}

	public void setDOB(int dOB) {
		DOB = dOB;
	}

	public double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}

	public double getFeePaid() {
		return feePaid;
	}

	public void setFeePaid(double FeePaid) {
		this.feePaid = FeePaid;
	}

	public boolean isInstructor() {
		return instructor;
	}

	public void setInstructor(boolean instructor) {
		this.instructor = instructor;
	}
	public void exemptStudent() throws Handler
	{
		
		
	}//end of the exemptStudent Method
	public void enroll(String courseName, String grade,int run) throws Handler
	{
		Enrollment objRef;
		objRef=searchEnrollment(courseName);
		if (objRef == null)
		{
		  objRef=new Enrollment(courseName, grade);
		  enrollmentList.add(objRef);
		}//valid enrollment
		else
		{
			String tempGrade=objRef.getGrade();
			
			if (tempGrade=="Exp")
            {
				throw new Handler("The Student Has Been Exempted From this Course");
			}//end of the if block
			else if (Integer.parseInt(tempGrade)>50)
			throw new Handler("The Student is already Enrolled is This Course"); 
			else
			{
				objRef=new Enrollment(courseName, tempGrade);
				enrollmentList.add(objRef);
			}
		}//end of else block
	}//end of enroll block

    public boolean checkExp(String courseName){
        Enrollment objRef;
        objRef=searchEnrollment(courseName);

            String tempGrade=objRef.getGrade();

            if (tempGrade=="Exp")
            {
                return true;
            }
            return false;
    }//valid enrollment



	public void calculateTotalFee()
	{
		
	}//end of calculateTotalFee block
	
	public Enrollment searchEnrollment(String courseName)
	{
		Enrollment tempObj;
		Iterator<Enrollment> i= this.enrollmentList.iterator();
		while(i.hasNext())
		{
			tempObj=i.next();
			if (tempObj.getCourseName().equals(courseName))
				return tempObj;
		}//end of the while block
		return null;
	}//end of the helper method

    public void addEnrollment(String CourseName, String Grade){


        Enrollment temp = new Enrollment(CourseName,Grade);
        enrollmentList.add(temp);

    }

    public String getGrade(String CourseName){
        String grade=null;
        for(int i=0;i<enrollmentList.size();i++){
            Enrollment temp = enrollmentList.get(i);
            if(CourseName.equals(temp.getCourseName())){
                grade = new String(temp.getGrade());

            }
        }
        return grade;
    }

    public void setGrade(String CourseName,String Grade){

        Enrollment tempObj;
        Iterator<Enrollment> i= this.enrollmentList.iterator();
        while(i.hasNext())
        {
            tempObj=i.next();
            if (tempObj.getCourseName().equals(CourseName))
                tempObj.setGrade(Grade);
        }

    }

	public String StudentListToString(){
        return studId+","+studName+","+DOB+","+getGrade("Java")+","+getGrade("OO Design")+","+getGrade("Software Testing")
                +","+getGrade("J2EE")+","+getGrade("Software Architecture")+","+getGrade("Design Patterns")+","+feePaid;
    }



    public static void saveStuFile(){

        String data = null;
        for(int i=0;i<RecordSystem.StudentList.size();i++){
            Student temp =  RecordSystem.StudentList.get(i);
            if(i==0) {
                data = temp.StudentListToString();
            }
            else {
                data = data+ "\n" +temp.StudentListToString();
            }
        }
        SaveFile save = new SaveFile();
        save.saveto(data);
    }


    public static Student getObj(String stuID){


        for(int i=0;i<RecordSystem.StudentList.size();i++){
            Student student = (Student) RecordSystem.StudentList.get(i);
            if(stuID==student.getStudId()){
                return (Student) RecordSystem.StudentList.get(i);
            }

        }
        return null;
    }
}

