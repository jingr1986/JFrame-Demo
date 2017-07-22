import java.util.ArrayList;


public class CourseRun 
{
	private String instructor;
    private int runNumber;
	private double tuitionFee;
	private boolean enrollmentStatus;
    private boolean openStatus;
    private int academyYear;
	private static ArrayList <Student> studentsList = new ArrayList<Student>();
	public CourseRun( int runNumber,String instructor, double tuitionFee,
			boolean enrollmentStatus,int academyYear)
	{
		
        this.runNumber = runNumber;
		this.instructor = instructor;
		this.tuitionFee = tuitionFee;
		this.enrollmentStatus = enrollmentStatus;
        this.academyYear = academyYear;
        this.openStatus = true;
		studentsList=new ArrayList <Student>();

	}

    public int getyear() {
        return academyYear;
    }
    public void setyear(int year) {
        this.academyYear = year;
    }

	public static void setStudentsList(Student obj)
	{
		studentsList.add(obj);
	}

    public void disable(){this.openStatus= false;}

    public boolean getOpenStatus(){return this.openStatus;}

    public static ArrayList getStudentsList(){

        return studentsList;

    }

    public static void saveGrade(String courseName){


    }

	/*public String getTextBook() {
		return textBook;
	}
	public void setTextBook(String textBook) {
		this.textBook = textBook;
	}*/

    public int getRunNumber(){ return runNumber;}
    public void setRunNumber(int runNumber){this.runNumber = runNumber;}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public double getTuitionFee() {
		return tuitionFee;
	}
	public void setTuitionFee(double tuitionFee) {
		this.tuitionFee = tuitionFee;
	}
	public boolean isEnrollmentStatus() {
		return enrollmentStatus;
	}
	public void setEnrollmentStatus(boolean enrollmentStatus) {
		this.enrollmentStatus = enrollmentStatus;
	}
	
	
	

}
