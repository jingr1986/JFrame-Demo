
public class Permanent extends Staff 
{
	private String role;
	
	public Permanent(String role,String name,String staffID,
			         double salaryRate,double salary)
	{
		super(name,staffID,salaryRate,salary);
		this.role=role;
	}//end of the default constructor
	public void computeSalary() 
	{
		double salary=0.0;
		super.setSalary(salary);
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

    public String StaffListToString(){
        return getStaffID()+","+getName()+","+getSalary()+","+getRole();
    }

}
