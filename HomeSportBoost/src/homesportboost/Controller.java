/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homesportboost;

import static homesportboost.Accueil2Frame.tableauClass;
import static homesportboost.Accueil2Frame.tableauDefis;
import static homesportboost.Accueil2Frame.utilisateur;
import static homesportboost.HomeSportBoost.map;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.JOptionPane;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 
 */
public class Controller {

    // réécriture de la fonction de comparaison
    // pour une utilisation pour des tableaux en 2 dimensions
    public static void sort(String[][] myArray) {
        Arrays.sort(myArray, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                // tri par odre décroissant
                return o2[1].compareTo(o1[1]);
            }
        });
    }

    /*public static void readCsv(String csvfilename) throws IOException {
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
    }*/
    
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
    }
    

    public static String[][] readDefis(String csvfilename) throws IOException{
        String[][] tableauRetour = new String[23][4];
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvfilename));
            String line =  null;
            // on ne lit pas la 1ere ligne qui contient la description d'une ligne
            line=br.readLine();
            int i=0;
            while ((line = br.readLine()) != null) {
                // pour ne pas lire les lignes blanches (ou s'il y a uniquement des espaces)
                if (line.trim().length() > 0) {
                    String str[] = line.split(",");
                    tableauRetour[i][0] = str[0];
                    tableauRetour[i][1] = str[1];
                    tableauRetour[i][2] = str[2];
                    tableauRetour[i][3] = str[3];
                    i++;
                }
            }
             // System.out.println(map);
            br.close();
        } catch (FileNotFoundException ex) {
            // Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return tableauRetour;
    }
    
    public static String readConseil(String csvfilename,String nb) throws IOException{
        String conseil = new String();
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvfilename));
            String line =  null;
            // on ne lit pas la 1ere ligne qui contient la description d'une ligne
            line=br.readLine();
            while ((line = br.readLine()) != null) {
                // pour ne pas lire les lignes blanches (ou s'il y a uniquement des espaces)
                if (line.trim().length() > 0) {
                    String str[] = line.split("/");
                    if(str[0].equals(nb)){
                        conseil=conseil + str[1] + " \n";
                    }
                }
            }
             // System.out.println(map);
            br.close();
        } catch (FileNotFoundException ex) {
            // Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }   
        return conseil;
    }

    public static boolean isUserValid(String user) {
        //System.out.println("USER:\n"+"\""+user+"\"");
        return map.containsKey(user);
    }

    public static boolean isPasswordValid(String user, String pass) {
        // System.out.println("user="+user+"\n password="+pass);
        // System.out.println("MAP:"+map.get(user));
        return map.get(user).equals(pass);
    }

    public static void saveLogin(String user, String pass, String filepath) {
        try {
            FileWriter fw = new FileWriter(filepath, true);  // true to write, not overwrite
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.append("\n" + user + "," + pass);
            pw.flush(); // make sure all data is written in the file
            pw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Data not saved");
        }
    }

    public static String hashPassword(String pass) {
        String generatedSecuredPasswordHash = BCrypt.hashpw(pass, BCrypt.gensalt(12));
        // System.out.println("password:"+generatedSecuredPasswordHash);
        return generatedSecuredPasswordHash;
    }

    public static boolean matchPassword(String pass, String pass1) {
        return BCrypt.checkpw(pass, pass1);
    }

    public static String[][] readClassement(String csvfilename) throws IOException {
        String[][] tableau = new String[12][2];
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvfilename));
            String line = null;
            // on ne lit pas la 1ere ligne qui contient la description d'une ligne
            line = br.readLine();
            int i = 0;
            while ((line = br.readLine()) != null) {
                // pour ne pas lire les lignes blanches (ou s'il y a uniquement des espaces)
                if (line.trim().length() > 0) {
                    String str[] = line.split(",");
                    tableau[i][0] = str[0];
                    tableau[i][1] = str[1];
                    i++;
                }
            }
            // System.out.println(map);
            br.close();
        } catch (IOException e) {
            System.out.println("Error readClassement : " + e);
        }
        return tableau;
    }

    public static String[][] sortAndDisplay2DArrays(String[][] tableauClass) {
        // méthode qui prend en paramère un tableau en 2D
        // qui le trie par ordre décroissant et retourne le nouveau tableau trié
        int nullCount = 0;
        // boucle for "améliorée", permettant de parcourir le tableau
        // on initialise une variable (pair) du type du tableau,
        // qui se verra attribuée chaque valeur de chaque élément du tableau jusqu'à la fin du tableau.
        for (String[] pair : tableauClass) {
            // si on rencontre un élément null, on l'ajoute au compteur
            if (pair[0] == null && pair[1] == null) {
                nullCount++;
            }
        }

        // on initialise un nouveau tableau 2D
        // celui-ci prendra comme dimension la taille initiale moins le nombre de valeur 'null'
        String[][] myArrWithNullsRemoved = new String[tableauClass.length - nullCount][];

        // 2eme étape, on parcourt toujours le même tableau (avec les valeurs 'null'
        // et pour chaque élément non null qu'on va rencoontrer
        // on remplit notre nouveau tableau créé juste avant par les valeurs du tableau original
        int i = 0;
        for (String[] pair : tableauClass) {
            if (pair[0] != null && pair[1] != null) {
                myArrWithNullsRemoved[i] = pair;
                i++;
            }
        }

        /* For debug
        System.out.println("Avant de trier :");
        for (String[] pair : myArrWithNullsRemoved) {
            System.out.println(pair[0] + "," + pair[1]);
        }*/
        // On a maintenant notre nouveau tableau sans les valeurs null
        // on peut le trier avec la méthode qu'on a redéfinie au début de cette classe
        Controller.sort(myArrWithNullsRemoved);

        /* For debug
        // affichage du tableau
        System.out.println("\n Affichage du tableau trié :");
        for (String[] pair : myArrWithNullsRemoved) {
            System.out.println(pair[0] + "," + pair[1]);
        }*/
        return myArrWithNullsRemoved;
    }

    public static void ajoutePoints(String idDefis) throws IOException {
        // méthode qui permert, une fois un défis validé,
        // d'ajouter le nombre de points correspondant dans le fichier classement.csv
        // on récupère la ligne dans le tableau de l'utilisateur
        int u = 0;
        for (String[] match : tableauClass) {
            if (match[0].equals(utilisateur)) {
                break;
            }
            u++;
        }
        // on récupère le nombre de points du défis qui vient d'être validé
        //String pointsDefis = tableauDefis[Integer.parseInt(idDefis)][2];
        String pointsDefis = "";
        for (int i = 0; i < tableauDefis.length; i++) {
            // System.out.println("idDefis ="+idDefis+";"+tableauDefis[i][0]+";");
            if (idDefis.equals(tableauDefis[i][0])) {
                pointsDefis = tableauDefis[i][2];
                break;
            }
        }
        // on récupère le nombre de points que l'utilisateur a déjà
        String pointsInit = tableauClass[u][1];
        // on ajoute le nombre de points du défis qui vient d'être validé avec les points existant
        int total = Integer.parseInt(pointsDefis) + Integer.parseInt(pointsInit);
        // on peut maintenant changer dans le fichier par les nouveaux points calculés
        tableauClass[u][1] = Integer.toString(total);
        File file = new File("src/homesportboost/classement.csv");
        String csvfilename = file.getAbsolutePath();
        BufferedWriter bw = null;
        String line = "";
        // on enlève du tableau les cases null
        int nullCount=0;
        for (String[] pair : tableauClass) {
            // si on rencontre un élément null, on l'ajoute au compteur
            if (pair[0] == null && pair[1] == null) {
                nullCount++;
            }
        }
        // on remplit le tableau avec la bonne taille maintenant
        bw = new BufferedWriter(new FileWriter(csvfilename));
        bw.write("#username,points(max99)\n");
        for (int i = 0; i < tableauClass.length - nullCount; i++) {
            line = tableauClass[i][0] + "," + tableauClass[i][1] + "\n";
            bw.write(line);
        }
        bw.close();
    }

   public static void valideDefis(String idDefis) throws IOException {
        try {
            // ça marche // System.out.println("idDEFIS:"+idDefis);
            for (int i=0; i < tableauDefis.length; i++) {
                // System.out.println("idDefis ="+idDefis+";"+tableauDefis[i][0]+";");
                if (idDefis.equals(tableauDefis[i][0])) {
                    // System.out.println("LA");
                    tableauDefis[i][3] = "oui";
                    // System.out.println("Modif :"+tableauDefis[i][3]);   // rien ne s'affiche
                    break;
                }
            }
            File file = new File("src/homesportboost/defis.csv");
            String csvfilename = file.getAbsolutePath();
            BufferedWriter bw = null;
            String line =  "";
            // on écrit dans le fichier csv
            bw = new BufferedWriter(new FileWriter(csvfilename));
            bw.write("#numero,defis,points,realise\n");
            for (int i=0; i < tableauDefis.length; i++) {
                line = "";
                for (int j=0;j<3;j++)
                    line += tableauDefis[i][j] + ",";
                line += tableauDefis[i][3] + "\n";
                bw.write(line);
            }
            bw.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private static boolean isContain(String source, String subItem){
         String pattern = "\\b"+subItem+"\\b";
         Pattern p=Pattern.compile(pattern);
         Matcher m=p.matcher(source);
         return m.find();
    }


}
