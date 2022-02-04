/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people;

import bar.BarElements;

/**
 *
 * @author thibo
 */

/** Humain */
public class Human {
    public final String name;
    public final String nickName;
    public int money;
    public int popularity;
    public final String shout;       
    
    /* Constructeur */
    
    public Human(String name, String nickName,String shout ){
        this.name = name;
        this.nickName = nickName;
        this.money = 30;
        this.popularity = 0;
        this.shout = shout;      
        BarElements.characters.add(this);    
    }
    
    public void talk(String say){
        System.out.println("<"+this.name+"> " + say);
    }
      
    public void drink(String beverage){
        this.popularity+=5;        
        talk("Slurp");          
    }
    
    public void pay(int cost){
        
        this.money-=cost;           
    }
    
    /** Offre la boisson "beverage" à l'humain "humain"
     * @param human 
     * @param beverage */ 
    public void offerDrink(Human human, String beverage){
        talk("Hey "+human.nickName+" my friend ! Drink that "+beverage+" it's on me");
        human.receiveDrink(beverage);
    }
    
    /** Recois puis boit la boisson
     * @param beverage */
    public void receiveDrink(String beverage){
        talk("Thank you for that succulent " + beverage);
        drink(beverage);
    }
    
    public void introduceYourself(){
        talk("Hello, my name is "+ this.name +" but you can call me "+ this.nickName );
    }
    
    /** Vérifie à la fois ;
    * - que la personne a assez d'argent pour s'acheter le nombre de boisson
    * - qu'il y ait assez de boisson en stock
     * @param beverage
     * @param howMany
     * @return 
    */
    public boolean isAffordable(String beverage,int howMany){
        int cost=howMany*BarElements.price.get(beverage);
        if(this.money<cost){
            talk("wait, I don't have the money to buy that");
            return false;
        }
        if(BarElements.stocks.get(beverage)<howMany){
            BarElements.theBartender.talk("I don't have enough "+beverage+" for you in my stock, sorry");
            return false;
        }
        talk("I'll take that "+beverage);
        this.pay(cost);
        BarElements.stocks.put(beverage, BarElements.stocks.get(beverage)-howMany);
        return true;
    }
    
    public void shout(){
        talk(this.shout);
    }
    
    
    /* Getters */
    
    public int getMoney(){
        return this.money;
    }
    public int getPopularity(){
        return this.popularity;
    }
    public String getName(){
        return this.name;
    }
    
    /* Setters */
    
    public void setMoney(int moneyChange){
        this.money+= moneyChange;
    }
    public void setPopularity(int popularityChange){
        this.popularity+= popularityChange;
    }
    
    
    
    
    
    
    
    
    
    
    
}
