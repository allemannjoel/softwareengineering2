package ch.fhnw.richards.lecture10_xml.yoo_exercise.client;

import java.io.PrintWriter;
import java.net.Socket;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ch.fhnw.richards.lecture10_xml.yoo_exercise.commons.Message;

public class ClientModel {

    private PrintWriter sOutput;
    private Socket socket;
    
    public ClientModel(Client client) throws Exception{
        this.socket = new Socket("localhost", 20000);
        this.sOutput = new PrintWriter(socket.getOutputStream(), true);
        client.c = this;
        new ServerListener(client, socket).start();
    }
    
    public void send(Message m) throws Exception{
        Serializer netOut = new Persister();
        netOut.write(m, sOutput);
    }
}
