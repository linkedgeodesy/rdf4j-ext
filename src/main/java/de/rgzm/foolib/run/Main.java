package de.rgzm.foolib.run;

import de.rgzm.foolib.config.POM_foolib;
import de.rgzm.foolib.log.Logging;
import de.rgzm.foorlib.database.FoolibDatabase;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.SQLException;

/**
 * main class
 * @author thiery
 */
public class Main {

    /**
     * main method
     * @param args
     * @throws IOException
     * @throws SQLException 
     */
    public static void main(String[] args) throws IOException, SQLException {
        String libinfo = "";
        try {
            libinfo = POM_foolib.getInfo().toJSONString();
            System.out.println(libinfo);
        } catch (Exception e) {
            libinfo = Logging.getMessageJSON(e, "de.rgzm.foolib.run.Main");
            System.out.println(Logging.getMessageJSON(e, "de.rgzm.foolib.run.Main"));
        }
        String databaseinfo = "";
        try {
            databaseinfo = FoolibDatabase.getTestById(Integer.parseInt("1")).toJSONString();
            System.out.println(databaseinfo);
        } catch (Exception e) {
            databaseinfo = Logging.getMessageJSON(e, "de.rgzm.foolib.run.Main");
            System.out.println(Logging.getMessageJSON(e, "de.rgzm.foolib.run.Main"));
        } 
        try {
            File file = new File("main.txt");
            String path = file.getCanonicalPath();
            File filePath = new File(path);
            filePath.delete();
            try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF8"))) {
                out.append(libinfo).append("\r\n");
                out.append(databaseinfo).append("\r\n");
                out.flush();
            }
        } catch (IOException e) {
            System.out.println(Logging.getMessageJSON(e, "de.rgzm.foolib.run.Main"));
        }
    }

}