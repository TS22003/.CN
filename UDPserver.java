import java.io.*;
import java.net.*;

class UDPServer{
    public static void main(String[] args) throws IOException 
    {
        DatagramSocket ds=new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost");
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        
        int port =2345;
        String msg;
        
        while(true)
        {
            msg=br.readLine();
            DatagramPacket dp=new DatagramPacket(msg.getBytes(),msg.length(),ip,port);
            if(!msg.equals("quit"))
                ds.send(dp);
            else
                break;
        }
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
import java.net.*;
import java.io.*;
import java.util.Scanner;

class UdpServer
{
	public static void main(String args[]) throws Exception
	{
		Scanner in = new Scanner(System.in);
		DatagramSocket datagramSocket = new DatagramSocket();
		InetAddress clientAddress = InetAddress.getByName("127.0.0.1");
		String message;
		byte[] buffer;
		DatagramPacket datagramPacket;
		System.out.println("Enter messages to send: ");
		while (true){
			message = in.nextLine();
			buffer = message.getBytes();
			datagramPacket = new DatagramPacket(buffer, buffer.length, clientAddress, 4000);
			datagramSocket.send(datagramPacket);

			if (message.equalsIgnoreCase("exit")) {
				datagramSocket.close();
				break;
			}

		}
	}

}
