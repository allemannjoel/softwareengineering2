package ch.fhnw.richards.lecture10_xml.yoo_exercise.server;

import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import ch.fhnw.richards.lecture10_xml.yoo_exercise.commons.Message;
import ch.fhnw.richards.lecture10_xml.yoo_exercise.commons.MessageType;
import ch.fhnw.richards.lecture10_xml.yoo_exercise.server.ClientThread;

public class ServerThread extends Thread {
    Server gui;
    int port;
    HashMap<ClientThread, Message> state;
    File f;

    public ServerThread(int port, Server gui) {
        this.port = port;
        this.gui = gui;
        this.state = new HashMap<ClientThread, Message>();
    }

    public void run() {
        // Try-with-resources, see: https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
        try (ServerSocket serverSocket = new ServerSocket(this.port);) {
            f = new File("log.xml");
            while (true) {
                Socket socket = serverSocket.accept();
                ClientThread clientThread = new ClientThread(socket, this);
                clientThread.start();
                this.state.put(clientThread, new Message(MessageType.YOO));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void broadcast() throws Exception {
        Serializer netOut = new Persister();
        for (ClientThread c : state.keySet()) {
            netOut.write(state.get(c), c.out);
        }
    }
}
