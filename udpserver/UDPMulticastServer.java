import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;
import java.util.zip.*;
import java.io.*;
public class UDPMulticastServer {

   public static void sendUDPMessage(String message,
   String ipAddress, int port) throws IOException {
      DatagramSocket socket = new DatagramSocket();
      InetAddress group = InetAddress.getByName(ipAddress);
      byte[] msg = message.getBytes();
      DatagramPacket packet = new DatagramPacket(msg, msg.length,
         group, port);
      socket.send(packet);
      socket.close();
   }

   public static void main(String[] args) throws IOException {
	   
	   String msg;
	   Scanner s = new Scanner(System.in);
	   System.out.println("Enter the msg");
	   
	   while(true)
	   {
		msg=s.nextLine();
		byte buffer[] = msg.getBytes();
  ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
  CheckedInputStream cis = new CheckedInputStream(bais, new Adler32());
  byte readBuffer[] = new byte[1024];
  while (cis.read(readBuffer) >= 0){
  long value = cis.getChecksum().getValue();
  System.out.println("The value of checksum is " + value);
  }
      
      sendUDPMessage(msg, "230.0.0.0",4321);
		 
		 
		 
		 if("exit".equals(msg)) {
		sendUDPMessage("OK", "230.0.0.0", 4321);
		break;
		 }
		 
	   }
  
   }
}