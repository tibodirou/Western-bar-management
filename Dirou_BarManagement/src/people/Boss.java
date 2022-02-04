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

/** Patronne */
public class Boss extends Client implements Worker{
    
    /* Constructeur */
    
    public Boss(String name, String nickName,String shout, String favouriteDrink, String secondaryDrink, String gender,int intelligenceLevel){       
        super(name,nickName,shout,favouriteDrink, secondaryDrink, gender,intelligenceLevel);          
        this.money=200;
        this.popularity=50;
        BarElements.barName="chez "+this.name;
        BarElements.theBoss=this;
    }
    
    @Override
    public void talk(String say){
        System.out.println(ConsoleColors.BLUE+ "<Boss "+this.name+"> " + say);
    }
    
    /** Interdit aux serveur et au barman de servir le Client
     * @param client */
    public final void prohibit(Client client){
        talk(client.name + " got too many drinks don't serve him ");
        BarElements.prohibitedClients.add(client);
        client.prohibed=true;
    }
    
    /** Bannit un Client du bar
     * @param client */
    public final void ban (Client client){
        talk( " It's enougth "+ client.name+"! Get out my establishment ");
        BarElements.bannedClients.add(client);
        BarElements.presentCharacters.remove(client);
    }
    
    /** Offre à boire sans payer
     * @param human
     * @param beverage */
    @Override
    public void offerDrink(Human human, String beverage){
        BarElements.stocks.put(beverage, BarElements.stocks.get(beverage)-1);
        human.receiveDrink(beverage);
        this.popularity+=15;
        
    }
    
    /** message personalisé
     * @param money */
    @Override
    public void receiveMoney(int money){
        this.money+=money;
        talk("business is good tonight, good work !");
    }  

}

