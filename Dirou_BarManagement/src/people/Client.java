/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package people;
import bar.*;
import java.util.Random;
/**
 *
 * @author thibo
 */

/** Client */
public class Client extends Human {
    
    private final String favouriteDrink;
    private final String secondaryDrink;
    public int bloodAlcoholLevel;
    public int numberOfJewelry;
    public String shirtColor;
    public String gender;
    public boolean prohibed;
    public int intelligenceLevel;
    Random random = new Random();
    
    /* Constructeur */
    public Client(String name, String nickName, String shout, String favouriteDrink, String secondaryDrink, String gender,int intelligenceLevel){
        super(name,nickName,shout);  
        this.intelligenceLevel=intelligenceLevel;
        this.favouriteDrink = favouriteDrink;
        this.secondaryDrink = secondaryDrink;
        setGender(gender);
        BarElements.clients.add(this);
    }
    
    /** Augmente son taux d'alcolemi
     * @param beverage*/
    @Override
    public void drink(String beverage){
        this.popularity+=5;
        this.bloodAlcoholLevel+=BarElements.bloodAlcohol.get(beverage);
        talk("Slurp");        
    }
    
    @Override
    public void introduceYourself(){
        talk("Hello fellas, my name is "+ this.name + " aka " + this.nickName +" and I'm pleased to be here tonight");
    }
    
    /** Change les caracteristiques du Client en fonction de son genre
     * @param gender */
    public final void setGender(String gender){
        if (gender.equals("male")){
            this.numberOfJewelry = 0;
            int rdm = random.nextInt(BarElements.colors.length);
            this.shirtColor=BarElements.colors[rdm];
        }
        else{
            this.shirtColor = "None";
            int rdm = random.nextInt(5);
            this.numberOfJewelry=rdm;
        }
        this.gender=gender;
    }
    
    /** Commande à boire à un serveur
    *  -Différentes intéractions en fonction du genre du serveur et de son genre
    *  -Vérification qu'il n'ait pas été interdit de consommer par la patronne
    *  -Voir isAffordable() dans Humain 
    *  -renonce à boire s'il ne peut ni boire sa boisson favorite ni sa boisson segondaire
     * @param server
    */
    public void commandDrink(Server server){
        if(server.bicepsWideness>=35 && this.gender.equals("female") && this.bloodAlcoholLevel>50){
            talk("I'll have an other drink handsome !");
        }
        if(server.charmCoefficient>=10 && this.gender.equals("male") && this.bloodAlcoholLevel>50){
            talk("I'll have an other drink baby !");
        }
        else{
            talk("I want a drink");
        }
        
        if (this.prohibed){
            server.talk("Sorry I can not serve you, the boss want you to stop drinking");
            return;
        }
        if(isAffordable(this.favouriteDrink,1)){
            server.talk("Here you go, it will cost you "+BarElements.price.get(this.favouriteDrink)+"€" );
            drink(this.favouriteDrink); 
            return;
        }
        if(isAffordable(this.secondaryDrink,1)){
            server.talk("Here you go, it will cost you "+BarElements.price.get(this.secondaryDrink)+"€" );
            drink(this.secondaryDrink);
            return;
        }
        talk("There is nothing good to drink in this bar");
    }
    
    /** S'assoit à une table
     * @param table */
    public void sitTable(Table table){
        table.addClient(this);
    }
    /** Quitte de la table
     * @param table */
    public void leaveTable(Table table){
        table.removeClient(this);
    }
    
    /** Offre une tournée générale
     * @param beverage */
    public void tournee(String beverage){
        int clients = BarElements.clients.size();
        if (isAffordable(beverage,clients)==false){
            return;
        }
        BarElements.theBartender.talk("TOURNEE GENERALE !");
        BarElements.theBoss.talk(" tout va bien, les affaires reprennent");
        for (int i=0;i<clients;i++){
           BarElements.clients.get(i).shout(); 
        }
        for (int i=0;i<clients;i++){
           BarElements.clients.get(i).drink(beverage); 
        }
    }
       
   
}
