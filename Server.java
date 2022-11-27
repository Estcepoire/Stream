package socket;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
public class Server {
    public Vector<String> showAllFile(String fileName) throws Exception{
        File dir  = new File(fileName);
        File[] liste = dir.listFiles();
        Vector<String> showAllFile = new Vector<>();
        for(File item : liste) {
            if(item.isFile()){
                showAllFile.add(item.getName());
            }
        }
        return showAllFile;
    }
    public
    ;6 void receive() throws Exception{
        ServerSocket server = new ServerSocket(6666);
        Socket socket = server.accept();
        DataInputStream data= new DataInputStream(socket.getInputStream());
        String string = (String)data.readUTF();
        System.out.println(string);
        String fileName = "E:/ITU/RESEAUX(Naina)/StreamMultimedia/Multimedia/"+string;
        Vector<String> showAllFile = this.showAllFile(fileName);
        for (int i = 0; i < showAllFile.size(); i++) {
            System.out.println(showAllFile.elementAt(i));
            this.send(fileName);
        }
        server.close();
    }
    public void send(String fileName) throws Exception{
        Socket socket = new Socket("localhost",6667);
        DataOutputStream data = new DataOutputStream(socket.getOutputStream());
        Vector<String> showAllFile = this.showAllFile(fileName);
        for (int i = 0; i < showAllFile.size(); i++) {
            data.writeUTF(showAllFile.elementAt(i));
        }
        data.flush();
        socket.close();
    }
    public static void main(String[] args) {
        Server s = new Server();
        try {
            s.receive();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
