/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backup;

import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Taffarel Xavier <taffarel_deus@hotmail.com>
 */
public class AppendFile {

    private static String dirName,
            fileName, appName, fileNameWithFolder;
    private static int porta;

    public static String getFileNameWithFolder() {
        return fileNameWithFolder;
    }

    public static void setFileNameWithFolder(String fileNameWithFolder) {
        AppendFile.fileNameWithFolder = fileNameWithFolder;
    }

    public static String getAppName() {
        return appName;
    }

    public static void setAppName(String appName) {
        AppendFile.appName = appName;
    }

    public static int getPorta() {
        return porta;
    }

    public static void setPorta(int porta) {
        AppendFile.porta = porta;
    }

    public static String getDirName() {
        return dirName;
    }

    public static void setDirName(String dirName) {
        AppendFile.dirName = dirName;
    }

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String fileName) {
        AppendFile.fileName = fileName;
    }

    /**
     * 
     * @param key
     * @param value 
     */
    public static void writeFile(String key, String value) {
        try {
            /*Primeiramente, vamos ler o arquivo
            para verificar se a key jÃ¡ existe.*/
             PrintWriter out = new PrintWriter(new BufferedWriter(
                        new FileWriter(getFileNameWithFolder(), true)));

                out.println(key + '=' + value);

                out.close();
           /* if (!keyExists(key)) {
                
                PrintWriter out = new PrintWriter(new BufferedWriter(
                        new FileWriter(getFileNameWithFolder(), true)));

                out.println(key + '=' + value);

                out.close();
            }
            else{
                
            }*/

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n72");
        }

    }

    /**
     *
     * @param fileName
     * @return
     */
    public static boolean deleteFile(String fileName) {
        try {
            File file = new File(fileName);
            System.out.println("Falsooo" + file.exists());
            if (file.exists()) {
                return file.delete();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n87");
        }
        return false;
    }

    public static void readFile() {

        String sfilename = getFileNameWithFolder();

        Scanner Sc1;

        try {

            FileInputStream FI = new FileInputStream(sfilename);

            Sc1 = new Scanner(FI);

            while (Sc1.hasNext()) {

                String data = Sc1.nextLine();

                if (data.length() > 0) {

                    //PEGA O NÃšMERO DA PORTA
                    if (data.startsWith("porta")) {

                        String portaNode[] = data.split("=");

                        setPorta(Integer.parseInt(portaNode[1].trim()));

                    }

                    //PEGA O NOME DA APLICAÃ‡ÃƒO
                    if (data.startsWith("app_name")) {

                        String portaNode[] = data.split("=");

                        setAppName(portaNode[1].trim());
                    }

                }

            }

        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, e.getMessage() + "\n118");
        }
    }

    public static String getValueByKeyName(String key) {

        String sfilename = getFileNameWithFolder();

        Scanner Sc1;

        try {

            FileInputStream FI = new FileInputStream(sfilename);

            Sc1 = new Scanner(FI);

            while (Sc1.hasNext()) {

                String data = Sc1.nextLine();

                if (data.length() > 0) {

                    //PEGA O NÃšMERO DA PORTA
                    if (data.startsWith(key)) {

                        String portaNode[] = data.split("=");

                        return portaNode[1].trim();
                    }

                }

            }

        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return "";
    }

    /**
     *
     * @param key A chave sem os dois pontos.
     * @return TRUE se existir, caso contrÃ¡rio, falso.
     */
    public static boolean keyExists(String key) {

        String sfilename = getFileNameWithFolder();

        Scanner Sc1;

        try {

            FileInputStream FI = new FileInputStream(sfilename);

            Sc1 = new Scanner(FI);

            while (Sc1.hasNext()) {

                String data = Sc1.nextLine();

                if (data.length() > 0) {

                    //PEGA O NÃšMERO DA PORTA
                    if (data.startsWith(key + "=")) {

                        return true;

                    }

                }

            }

        } catch (IOException e) {
            System.out.println("195\n" + e.getStackTrace());
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return false;
    }
    
    public static String _getPathDefult64(String folerName) {
        return System.getenv("LOCALAPPDATA") + "\\" + folerName;
    }

    /**
     *
     * @param conteudo
     */
    public static void escreverArquivoConfiguracao(String conteudo) {
        try (PrintWriter out = new PrintWriter(
                new BufferedWriter(
                        new FileWriter(_getPathDefult64("backup_mysql") + "\\configuracoes.txt", true)))) {
            out.println(conteudo);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
