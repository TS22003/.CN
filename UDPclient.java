import java.net.*;



public class Client
{
	public static void main(String[] args)
	{
		try
		{
			DatagramSocket ds= new DatagramSocket(5010);			//Creates Socket
			String msg;											//Reads all lines sent by server
			
			DatagramPacket rdp;									//Packet that shall be received
			byte buff[]=new byte[100];
			//The msg received is in the form of bytes, so we must change to string later

			
			while(true)											//Keeps running until server stops
			{
				rdp=new DatagramPacket(buff,buff.length);
				ds.receive(rdp);								//Packet received
				msg= new String(rdp.getData(),rdp.getOffset(),rdp.getLength());
				//Changing byte pkt into String
			
				System.out.println(msg);
			}
			
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
