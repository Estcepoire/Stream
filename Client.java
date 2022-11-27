package socket;
import java.io.*;
import java.util.*;
import java.net.*;

public class Client {
    public void send() throws Exception {
        Socket socket = new Socket("localhost",6666);
        DataOutputStream data = new DataOutputStream(socket.getOutputStream());
        Scanner scann = new Scanner(System.in);
        System.out.print("file Name: ");
        String message = (String) scann.nextLine();
        data.writeUTF(message);
        data.flush();
        socket.close();
    }
    public void receive() throws Exception{
        ServerSocket server = new ServerSocket(6667);
        Socket socket = server.accept();
        DataInputStream data= new DataInputStream(socket.getInputStream());
        String string = (String)data.readUTF();
        System.out.println("file: "+string);
        server.close();
    }
    public static void main(String[] args) {
        Client c = new Client();
        try {
            c.send();
            c.receive();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
