package ch.fhnw.richards.lecture08_threads.webServer_v2;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import javafx.concurrent.Task;

public class WebServerModel {
    private Integer port;
    private final Logger logger = Logger.getLogger("");
    
    final Task<Void> serverTask = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            try {
                ServerSocket listener = new ServerSocket(port, 10, null);
                logger.info("Listening on port " + port);

                while (true) {
                    // The "accept" method waits for a request, then creates a socket
                    // connected to the requesting client
                    Socket clientSocket = listener.accept();
                    
                    ClientThread client = new ClientThread(clientSocket);
                    client.start();
                }
            } catch (Exception e) {
                System.err.println(e);
            }
            return null;
        }
    };
    
    /**
     * Called by the controller, to start the task, to serve web content
     */
    public void serveContent(Integer port) {
        this.port = port;
        new Thread(serverTask).start();
    }
}
