
public class CasualStaff extends Staff 
{
    String Role;
	public CasualStaff(String name, String staffID, double salary, String Role )
	{
		super(name,staffID,salary);
		this.Role = Role;
	}//end of the default constructor
	public void computeSalary()
	{
		double salary=0.0;
		super.setSalary(salary);
	}

    public String getRole(){
        return Role;
    }

    public String StaffListToString(){
        return getStaffID()+","+getName()+","+getSalary()+","+getRole();
    }

}
