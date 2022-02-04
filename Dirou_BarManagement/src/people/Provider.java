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

/** Livreur */
public class Provider extends Human implements Worker {
    
    /* Constructeur */
    
    public Provider(String name, String nickName,String shout){
        super(name,nickName,shout);
        BarElements.theProvider=this;
    }
    
    @Override
    public void talk(String say){
        System.out.println(ConsoleColors.YELLOW+ "<Provider "+this.name+"> " + say );
    }
    
    /** Remplit les stocks du bar en fonction de la liste donnée par le barman
    *
    * voir command() dans Bartender
     * @param command
    */
    public void receiveCommand(ArrayList<String> command){
        talk("Alright, I'll bring you what you want ");
        for(int i=0;i<command.size();i++){
            BarElements.stocks.put(command.get(i), 20);
        }
        talk("Your stock have been filled !");
    }
    
    @Override
    public void receiveMoney(int money){
        this.money+=money;
        talk("It's a pleasure to do business with you");
    }
    
}
