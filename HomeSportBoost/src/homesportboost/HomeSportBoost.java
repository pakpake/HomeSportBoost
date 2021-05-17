/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homesportboost;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
public class HomeSportBoost {

    public static HashMap<String,String> map = new HashMap<String, String>(); // variable global
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            File file = new File("src/homesportboost/accounts.csv");
            String filename = file.getAbsolutePath();
            //System.out.println("filepath:"+filename);
            // on lit le fichier des comptes
            Controller.readCsv(filename);

            new LoginFrame().setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(HomeSportBoost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}



/*//public static void readCsv(String csvfilename) throws IOException {
    public static Map<String,String> readCsv(String csvfilename) throws IOException {

        try {
            BufferedReader br = new BufferedReader(new FileReader(csvfilename));
            String line = null;
            // on ne lit pas la 1ere ligne qui contient la description d'une ligne
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                // pour ne pas lire les lignes blanches (ou s'il y a uniquement des espaces)
                if (line.trim().length() > 0) {
                    String str[] = line.split(",");
                    map.put(str[0], str[1]);
                }
            }
             // System.out.println(map);
            br.close();
        } catch (FileNotFoundException ex) {
            // Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return map;
    }*/
