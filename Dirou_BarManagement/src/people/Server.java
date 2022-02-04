/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people;
import java.util.Random;
import bar.BarElements;

/**
 *
 * @author thibo
 */

/** Serveur */
public class Server extends Human implements Worker{
    
    public int bicepsWideness;
    public int charmCoefficient;
    public String gender;
    Random random = new Random();
    
    /* Constructeur */  
    public Server(String name, String nickName, String shout, String gender ){
        super(name,nickName,shout);
        setGender(gender); 
        BarElements.servers.add(this);
    }
    

    @Override
    public void talk(String say){
        System.out.println(ConsoleColors.PURPLE+ "<Server "+this.name+"> " + say);
    }
    
    /** Refuse la boisson s'il ne s'agit pas d'eau */
    @Override
    public void receiveDrink(String beverage){
        if (beverage.equals("water")){
            talk("I can drink that, thank you");
            return;
        }
        talk("Sorry, I can only drink water during my service");
    }   
    
    @Override
    public void receiveMoney(int money){
        this.money+=money;
        talk("Your Drink has been paid thank you !");
    }
    
    /** Change les caracteristiques en fonction du genre
     * @param gender */
    public final void setGender(String gender){
        if (gender.equals("male")){
            this.charmCoefficient = 0;
            int rdm = random.nextInt(100);
            this.bicepsWideness=rdm;
        }
        else{
            this.bicepsWideness = 0;
            int rdm = random.nextInt(100);
            this.charmCoefficient=rdm;
        }  
        this.gender=gender;
    }
   
   
}
