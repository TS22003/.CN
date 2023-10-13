import java.io.*;
import java.net.*;

public class UDPClient{
  public static void main(String[] args) throws IOException
  {
      //Set the port number on which the client must communicate to the server.
      DatagramSocket ds=new DatagramSocket(2345);
      String msg;
      byte[] buf=new byte[100];
      while(true)
      {
          DatagramPacket rdp=new DatagramPacket(buf,buf.length);
          ds.receive(rdp);
          msg=new String(rdp.getData(),rdp.getOffset(),rdp.getLength());

          System.out.println(msg);
      }
  }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
import java.net.*;
import java.io.*;

class UdpClient
{
	public static void main(String args[]) throws Exception
	{
		DatagramSocket datagramSocket = new DatagramSocket(4000);
		byte[] buffer;
		DatagramPacket datagramPacket;
		System.out.println("Received Messages: ");
		while(true)
		{
			buffer = new byte[65555];
			datagramPacket = new DatagramPacket(buffer, buffer.length);
			datagramSocket.receive(datagramPacket);
			String received = new String(buffer).trim();
			System.out.println(received);
			if (received.equalsIgnoreCase("exit")) {
				datagramSocket.close();
				break;
			}
		}
	}
}
