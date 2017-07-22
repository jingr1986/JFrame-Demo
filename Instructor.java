/**
 * Created by ranjing on 23/05/2014.
 */
public class Instructor extends Staff {

    String Role ;

    public Instructor (String StaffID, String StaffName,double salaryRate,double Salary,String Role){
        super(StaffID,StaffName,salaryRate,Salary);
        this.Role = Role;
    }

    public void computeSalary()
    {
        double salary = getSalaryRate()*20;
        super.setSalary(salary);
    }

    public String getRole () {

        return Role;
    }

    public String StaffListToString(){
        return getStaffID()+","+getName()+","+getSalaryRate()+","+getRole();
    }

}
