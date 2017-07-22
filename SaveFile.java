import java.io.FileNotFoundException;
import java.io.PrintStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Created by ranjing on 25/05/2014.
 */
public class SaveFile {
    public SaveFile() {}

    public void saveto (String data) {
        JFileChooser chooser = new JFileChooser();
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Text File (.txt)", "txt"));
        int response = chooser.showSaveDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            PrintStream exportFile = null;
            try {
                exportFile = new PrintStream(chooser.getSelectedFile()+".txt");
            }
            catch (FileNotFoundException e1) {
                System.out.println("Error in PrintStream.");
            }
            exportFile.println(data.toString());
            exportFile.close();
        }
    }

}

