package backup;

import java.io.*;

/**
 *
 * @author Taffarel Xavier <taffarel_deus@hotmail.com>
 */
public class Backup {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) throws Exception {
        ProcessBuilder builder = new ProcessBuilder(
            "cmd.exe", "/c", "node E:\\backup_mysql\\index.js");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
        }
    }
    
}