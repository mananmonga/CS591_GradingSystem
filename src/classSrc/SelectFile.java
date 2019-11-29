package classSrc;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SelectFile {

    public static String select() {
        // TODO Auto-generated method stub
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "Excel File(*.xls)", "xls");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(chooser);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	String newURL = chooser.getSelectedFile().getPath().replaceAll("\\\\","/");
            System.out.println(newURL);
            return newURL;
        }
        return null;
    }
}