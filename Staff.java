
public abstract class Staff {
	private String name,staffID,Role;
	private double salaryRate,salary=0.0;
	
	public Staff(String staffID,String name,double salaryRate,double salary)
	{
        this.staffID=staffID;
		this.name=name;
		this.salaryRate=salaryRate;
		this.salary=salary;

	}//end of the Default constructor block
    public Staff (String StaffID, String StaffName,double salaryRate,double Salary,String Role){

        this.staffID = StaffID;
        this.name = StaffName;
        this.salaryRate = salaryRate;
        this.salary = Salary;
        this.Role = Role;
    }
	public Staff(String name,String staffID,double salary)
	{
		this(name,staffID,1,salary);
	}//end of the constructor

    public String getStaffID() {
		return staffID;
	}

	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}

	public String getName() {
		return name;
	}
	public double getSalaryRate() {
		return salaryRate;
	}

	public void setSalaryRate(double salaryRate) {
		this.salaryRate = salaryRate;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	
	public void computeSalary(){};

    public String getRole(){

        return Role;
    }

    public abstract String StaffListToString();

    public static void saveStaffFile(){
        String data = null;
        for(int i=0;i<RecordSystem.StaffList.size();i++){
            Staff temp = (Staff) RecordSystem.StaffList.get(i);
            if(i==0) {
                data = temp.StaffListToString();
            }
            else {
                data = data+ "\n" +temp.StaffListToString();
            }
        }
        SaveFile save = new SaveFile();
        save.saveto(data);
    }

}
