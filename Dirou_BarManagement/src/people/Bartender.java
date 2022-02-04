/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people;

import bar.BarElements;
import java.util.ArrayList;
/**
 *
 * @author thibo
 */

/** Barman */
public class Bartender extends Human implements Worker{
    
    /* Constructeur */
    
    public Bartender(String name, String nickName,String shout){
        super(name,nickName,shout);  
        this.popularity=100;
        this.money=50;
        BarElements.theBartender=this;
    }
    
    /** Rajout de "coco" à la fin de ses phrases
     * @param say */
    @Override
    public void talk(String say){
        System.out.println(ConsoleColors.GREEN+ "<Bartender "+this.name+"> " + say + " coco");
    }
    
    /** refuse les boissons alcoolisées
     * @param beverage */
    @Override
    public void receiveDrink(String beverage){
        if(BarElements.bloodAlcohol.get(beverage)==0){
            talk("Thank you dear");
            drink("boisson");
        }
        else{
            talk("There is alcohol in that, can't drink that during my service sorry");
        }
    }
    
    /** Met l'argent dans la caisse
     * @param money */
    @Override
    public void receiveMoney(int money){
        BarElements.barCash+=this.money;
        talk("Thank you for the  !");
    }
    
    /** Rend l'argent à "humain"
     * @param human
     * @param money */
    public void returnMoney(Human human, int money){
        BarElements.barCash-=money;
        talk("Here you go "+ human.name +" ");
        human.money+=money;
    }
    
    /** vide la caisse du bar et donne l'argent au boss */
    public void giveMoneyToBoss(){
        talk("Take that money boss, the bar cash is full ");
        BarElements.theBoss.receiveMoney(BarElements.barCash);
        BarElements.barCash=0;
    }
    
    /** Fait une liste de commande au fourniseur en fonction des ses stocks vides */
    public void makeCommand(){
        ArrayList<String> command = new ArrayList<>();
        for(int i=0;i<BarElements.drinksList.size();i++){
            if(BarElements.stocks.get(BarElements.drinksList.get(i))==0){
                command.add(BarElements.drinksList.get(i));
            }
        }      
        talk("Alright "+BarElements.theProvider.name+", I need you to fill my stock for "+command.size()+" beverage" );
        BarElements.theProvider.receiveCommand(command);
    }
    
}
