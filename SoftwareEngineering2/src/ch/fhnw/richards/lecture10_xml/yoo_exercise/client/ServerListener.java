package ch.fhnw.richards.lecture10_xml.yoo_exercise.client;

import java.net.Socket;

import ch.fhnw.richards.lecture10_xml.yoo_exercise.commons.Message;
import ch.fhnw.richards.lecture10_xml.yoo_exercise.commons.MessageType;

public class ServerListener extends Thread{
    
    private Client gui;
    private Socket socket;
    
    public  ServerListener(Client gui, Socket socket) throws Exception{
        this.gui = gui;
        this.socket = socket;
    }
    
    public void run(){
        try{
            Message message = null;
            while(true){
                message = Message.receive(socket);
                if(message.getType() == MessageType.YOO){
                    System.out.println(message);
                    gui.setCard(message.getCard());
                    gui.setPosition(message.getPosition());
                }
            }
        }
        catch(Exception e){}
    }
    
}
