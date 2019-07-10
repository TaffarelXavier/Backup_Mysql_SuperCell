package backup;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Backup {

    private static final DateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HH_mm");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Date date = new Date();

        System.out.println(AppendFile._getPathDefult64("backup_mysql"));

        File pasta = new File(AppendFile._getPathDefult64("backup_mysql"));

        if (!pasta.exists()) {

            pasta.mkdir();

            String nomeDoArquivo = AppendFile._getPathDefult64("backup_mysql") + "\\configuracoes.txt";

            File file = new File(nomeDoArquivo);
            //Se nÃ£o existir
            if (!file.exists()) {

                PrintWriter writer;
                try {
                    writer = new PrintWriter(nomeDoArquivo, "UTF-8");
                    writer.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TelaInicial.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(TelaInicial.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            //Mostra a tela:
            new TelaInicial().setVisible(true);
        } else {

            //Define o arquivo de configurações:
            AppendFile.setFileNameWithFolder(AppendFile._getPathDefult64("backup_mysql") + "\\configuracoes.txt");

            String host = AppendFile.getValueByKeyName("hostname");
            String username = AppendFile.getValueByKeyName("username");
            String password = AppendFile.getValueByKeyName("password");
            String dbname = AppendFile.getValueByKeyName("dbname");

            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "mysqldump -h" + host + " -u" + username
                    + " -p" + password + " " + dbname + " > " + AppendFile.getValueByKeyName("salvar") + "\\backup_" + dbname + "_" + sdf.format(date) + ".sql");
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                System.out.println(line);
            }
        }

    }

}
