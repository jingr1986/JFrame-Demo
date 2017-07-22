/**
 * Created by ranjing on 25/05/2014.
 */
public class Validation {
    public Validation () {}

    public static boolean stuYOB (String yobString) {
        try {

            int YOB = Integer.parseInt(yobString);
            return true;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }
    public static boolean aYear (String aYearString) {
        try {

            int academyYear = Integer.parseInt(aYearString);
            return true;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean grade (String gradeString) {
        try {

            int grade = Integer.parseInt(gradeString);
            return true;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean runNumber (String runNumberString) {
        try {

            int runNumber = Integer.parseInt(runNumberString);
            return true;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }

}
