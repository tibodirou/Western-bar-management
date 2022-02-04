/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lauchers;

import bar.BarElements;
import cardGames.BeloteGame;
import cardGames.Team;
import cardGames.Tournoi;
import people.Bartender;
import people.Boss;
import people.Client;
import people.ConsoleColors;
import people.Provider;
import people.Server;

/**
 *
 * @author thibo
 */
public class Laucher {
    public static void main(String[] args) {
        
        
                                                    /* Creation NPC */
        Client Marie = new Client("Marie","rima","iiiiihhhhhh","beer","red wine","female",0);
        Client Thibaud = new Client("Thibaud","Titi","ahaahh","red wine","champagne","male",2);
        Client Elven = new Client("Elven","nevle", "Genial !", "whiskey", "beer", "male",2);
        Client Miguel = new Client("Miguel","Migu","fikjnskfkshf","rum","whiskey", "male",1);
        Client Manon = new Client("Manon", "Mama","Carré dans l'axe !", "beer", "vodka","female",0);     
        Client Gonzales = new Client("Gonzales", "Speedy","Jajajaja", "rum", "vodka","male",2);
        Client Malo = new Client("Malo", "Malococcyx","Wah !", "beer", "whisky","male",0);
        Client Eglandine = new Client("Eglandine", "glande","trop bien !", "red wine", "vodka","female",1);
        
        Bartender Toto = new Bartender("Toto","totaupe","yeah boi");
        
        Server Pape = new Server("Pape","pablito","Super !","male");
        Server Bahar = new Server("Bahar","Baba","yes !","female");   
        Server Logan = new Server("Logan","Lolo","aight","female"); 
        Server Christa = new Server("Christa","Cricri","fikjnskfkshf","female"); 
        
        Provider Etienne = new Provider("Etienne","ginger","Ouai super !");
        
        Boss Emma = new Boss("Emma","Em","whaou","champagne","rum","female",2);
        
                                    /* Initialisation de la valeur des cartes */
                                    
        BeloteGame.trumpValues.put("7",0);
        BeloteGame.trumpValues.put("8",0);
        BeloteGame.trumpValues.put("9",14);
        BeloteGame.trumpValues.put("10",10);
        BeloteGame.trumpValues.put("jack",20);
        BeloteGame.trumpValues.put("queen",3);
        BeloteGame.trumpValues.put("king",4);
        BeloteGame.trumpValues.put("ace",11);
        
        BeloteGame.notTrumpValues.put("7",0);
        BeloteGame.notTrumpValues.put("8",0);
        BeloteGame.notTrumpValues.put("9",0);
        BeloteGame.notTrumpValues.put("10",10);
        BeloteGame.notTrumpValues.put("jack",2);
        BeloteGame.notTrumpValues.put("queen",3);
        BeloteGame.notTrumpValues.put("king",4);
        BeloteGame.notTrumpValues.put("ace",11);
        
        
        
                                    /* Remplissage initial du stock d'alcool */
                                    
        BarElements.drinksList.add("beer");
        BarElements.bloodAlcohol.put("beer",5);
        BarElements.price.put("beer",2);
        BarElements.stocks.put("beer",9);
        
        BarElements.drinksList.add("white wine");
        BarElements.bloodAlcohol.put("white wine",10);
        BarElements.price.put("white wine",3);
        BarElements.stocks.put("white wine",10);
        
        BarElements.drinksList.add("red wine");
        BarElements.bloodAlcohol.put("red wine",10);
        BarElements.price.put("red wine",3);
        BarElements.stocks.put("red wine",10);
        
        BarElements.drinksList.add("champagne");
        BarElements.bloodAlcohol.put("champagne",10);
        BarElements.price.put("champagne",10);
        BarElements.stocks.put("champagne",5);
                
        BarElements.drinksList.add("rum");
        BarElements.bloodAlcohol.put("rum",40);
        BarElements.price.put("rum",6);
        BarElements.stocks.put("rum",10);
        
        BarElements.drinksList.add("vodka");
        BarElements.bloodAlcohol.put("vodka",40);
        BarElements.price.put("vodka",6);
        BarElements.stocks.put("vodka",10);
        
        BarElements.drinksList.add("whiskey");
        BarElements.bloodAlcohol.put("whiskey",45);
        BarElements.price.put("whiskey",10);
        BarElements.stocks.put("whiskey",5);
        
        BarElements.drinksList.add("water");
        BarElements.bloodAlcohol.put("water",0);
        BarElements.price.put("water",1);
        BarElements.stocks.put("water",20);
        
        BarElements.drinksList.add("milk");
        BarElements.bloodAlcohol.put("milk",0);
        BarElements.price.put("milk",2);
        BarElements.stocks.put("milk",10);
        
        BarElements.drinksList.add("soda");
        BarElements.bloodAlcohol.put("soda",0);
        BarElements.price.put("soda",5);
        BarElements.stocks.put("soda",15);
        
        
        System.out.println("\n"+ ConsoleColors.RED_BOLD + "                           Welcome to "+BarElements.theBoss.name+" bar  ! \n");
        
                                                           /* actions NPC démo */
        
        Thibaud.offerDrink(Marie, "red wine");
        
        Emma.sitTable(BarElements.table2);
        Gonzales.sitTable(BarElements.table2);
        
        Gonzales.commandDrink(Bahar);
        Manon.introduceYourself();
        Manon.sitTable(BarElements.table2);
        Manon.offerDrink(Emma, "vodka");
        Gonzales.shout();
        
        Team team1=new Team(Thibaud,Elven);
        Team team2=new Team(Marie,Manon);
        Team team3=new Team(Gonzales,Emma);        
        BarElements.teamsList.add(team1);
        BarElements.teamsList.add(team2);
        BarElements.teamsList.add(team3);
        Tournoi tournoiDemo= new Tournoi(BarElements.teamsList);
        tournoiDemo.generateGames();
        
           
        Thibaud.tournee("beer");
        
        Elven.introduceYourself();
        Elven.shout();
        Emma.prohibit(Elven);
        Elven.commandDrink(Pape);
        
        Marie.commandDrink(Christa);
        Toto.giveMoneyToBoss();
        Eglandine.commandDrink(Pape);
        Emma.ban(Elven);
        
        Team teama=new Team(Thibaud,Gonzales);
        Team teamb=new Team(Marie,Manon);
        Team teamc=new Team(Gonzales,Emma);        
        BarElements.teamsList.add(teama);
        BarElements.teamsList.add(teamb);
        BarElements.teamsList.add(teamc);
        Tournoi tournoiDemo2= new Tournoi(BarElements.teamsList);
        tournoiDemo2.generateGames();
        
        Thibaud.offerDrink(Gonzales, "red wine");
        Toto.makeCommand();
        Manon.commandDrink(Pape);
        
    }
}
