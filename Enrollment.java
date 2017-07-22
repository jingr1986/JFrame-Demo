
public class Enrollment 
{
	private String courseName;
	private String grade;
	
	public Enrollment(String courseName,String grade)
	{
		this.courseName=courseName;
		this.grade=grade;
	}// end constructor block 

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	public void listEnrollments()
	{
		System.out.println("Course Name: "+this.courseName+"\t Grade: "+
				            this.grade);
	}//end of the listEnrollments block

}
