import java.util.*;


public class Course 
{
	private String courseName;
	private double tax,taxRate;
	private  int  activeRun2014,activeRun2015 ;//activeRun Variables to keep track of the number of runs
	private final static int MAX_RUN=5;
	private  CourseRun [] runs2014 ;
	private  CourseRun [] runs2015 ;
    private  CourseRun [] temp = new CourseRun[5];
	private Scanner scan;
	public Course(String courseName ,double tax,double taxRate)
	{
		this.courseName=courseName;
		this.tax=tax;
		this.taxRate=taxRate;
		this.runs2014=new CourseRun [MAX_RUN];
		this.runs2015=new CourseRun[MAX_RUN];
		this.scan=new Scanner(System.in);
		
	}//end of the constructor block
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public double getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}
	public int getActiveRun2014() {
		return activeRun2014;
	}
	public void setActiveRun2014(int activeRun2014) {
		this.activeRun2014 = activeRun2014;
	}
	public int getActiveRun2015() {
		return activeRun2015;
	}
	public void setActiveRun2015(int activeRun2015) {
		this.activeRun2015 = activeRun2015;
	}

    public int getActiveRun(int year){
        if(year==2014){return activeRun2014;}
        else if(year==2015){return activeRun2015;}
        return 0;
    }

    public CourseRun[] getRuns(int year){
        if(year==2014){ return getRuns2014();}
        if(year==2015){ return getRuns2015();}
        return null;
    }

	public CourseRun[] getRuns2014() {
		return runs2014;
	}
	public void setRuns(int runNumber,String year,String instructorID){
		if (year.equals("2014"))
		{
			if (activeRun2014 == MAX_RUN)
			{
				System.out.println("Sorry You Can't Create More Runs " +
						           "For This Year '2014'");
			}
			else
			{
				boolean enrollmentStatus=true;
				double tuitionFee=500.0;
				runs2014[activeRun2014]=new CourseRun(runNumber,instructorID, tuitionFee, enrollmentStatus,Integer.parseInt(year));
				activeRun2014++;
			}
		}
		else
		{
			if (runs2015.length == MAX_RUN)
			{
				System.out.println("Sorry You Can't Create More Runs " +
						           "For This Year '2012'");
			}
			else
			{
				boolean enrollmentStatus=true;
				double tuitionFee=500.0;
				runs2015[activeRun2015]=new CourseRun(runNumber,instructorID, tuitionFee, enrollmentStatus,Integer.parseInt(year));
				activeRun2015++;
			}
		}
	}
	public CourseRun[] getRuns2015() {
		return runs2015;
	}

	public void setRuns2015(CourseRun[] runs2015) {
		this.runs2015 = runs2015;
	}
	public void addStudentToRun(int runNumber,String year,Student obj)
	{
		if (year.equals("2014"))
		{
			this.runs2014[runNumber].setStudentsList(obj);
		}
		else
		{
			this.runs2015[runNumber].setStudentsList(obj);
		}
	}
	







	

	
	

}
