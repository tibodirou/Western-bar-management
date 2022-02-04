/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bar;
        
import cardGames.Team;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import people.Bartender;
import people.Boss;
import people.Client;
import people.Human;
import people.Provider;
import people.Server;

/**
 *
 * @author thibo
 */


/**
 * All the elements of the bar that are used in other classes
 */
public class BarElements {
    
    
    /* People */
    public static ArrayList <Human> characters = new ArrayList <Human> ();
    public static ArrayList <Server> servers = new ArrayList <Server> ();
    public static ArrayList <Client> clients = new ArrayList <Client> ();
    public static ArrayList <Client> prohibitedClients = new ArrayList <Client> ();
    public static ArrayList <Client> bannedClients = new ArrayList <Client> ();
    public static ArrayList <Human> presentCharacters = new ArrayList <Human> ();
    public static Boss theBoss; 
    public static Bartender theBartender;
    public static Provider theProvider;
    
    /* Drinks*/
    public static ArrayList <String> drinksList = new ArrayList<>();
    public static Map<String,Integer> bloodAlcohol = new HashMap<>();
    public static Map<String,Integer> price = new HashMap<>();
    public static Map<String,Integer> stocks = new HashMap<>();
    
    /* Tables */
    public static ArrayList <Table> tableList = new ArrayList<>();
    public static Table table1 = new Table();
    public static Table table2 = new Table();
    public static Table table3 = new Table();

    /* Other Variables */
    public static int barCash;
    public static String barName;
    public static String[] colors ={"red","blue","white","black","yellow"};
    /* Tournoi */
    public  static ArrayList <Team> teamsList= new ArrayList<>();
    
}