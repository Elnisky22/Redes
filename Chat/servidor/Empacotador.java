
package servidor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Empacotador {
    
    public static byte[] empacotar(String s){
        ByteArrayOutputStream aux = new ByteArrayOutputStream();
        aux.write((byte)0x80);
        aux.write((byte)(s.length()>>8));
        aux.write((byte)s.length());
        try {
            aux.write(s.getBytes());
            aux.write(new byte[2]);
        } catch (IOException ex) {}
        byte[] packet = aux.toByteArray();
        return packet;
    }
            
    public static void send(byte[] packet,InetAddress ip,int port){        
        try{
            DatagramPacket pkg = new DatagramPacket(packet,packet.length, ip, port);
            DatagramSocket ds = new DatagramSocket();
            ds.send(pkg);
            ds.close();
        }catch(IOException e){}
    }
}
