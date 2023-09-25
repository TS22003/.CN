import java.util.Scanner;
public class crc{

    public static void main(String args[]) {

    Scanner sc = new Scanner(System.in);
    //Input Data Stream
    System.out.print("\r\n Enter message bits: ");
    String message = sc.nextLine();
    System.out.print("Enter divisor: ");
    String generator = sc.nextLine();
int data[] = new int[message.length() + generator.length() - 1];
int divisor[] = new int[generator.length()];
for(int i=0;i<message.length();i++)
	data[i] = Integer.parseInt(message.charAt(i)+"");
for(int i=0;i<generator.length();i++)
	divisor[i] = Integer.parseInt(generator.charAt(i)+"");

//Calculation of CRC
for(int i=0;i<message.length();i++)
{
	if(data[i]==1)
		for(int j=0;j<divisor.length;j++)
			data[i+j] ^= divisor[j];
}

//Display CRC
System.out.print("\r\n The checksum code is: ");
for(int i=0;i<message.length();i++)
	data[i] = Integer.parseInt(message.charAt(i)+"");
for(int i=0;i<data.length;i++) 
    System.out.print(data[i]);
System.out.println();
//Check for input CRC code
System.out.print("\r\n Enter checksum code: ");
message = sc.nextLine();
System.out.print("divisor: ");
generator = sc.nextLine();
data = new int[message.length() + generator.length() - 1];
divisor = new int[generator.length()];
for(int i=0;i<message.length();i++)
	data[i] = Integer.parseInt(message.charAt(i)+"");
for(int i=0;i<generator.length();i++)
	divisor[i] = Integer.parseInt(generator.charAt(i)+"");

//Calculation of remainder
for(int i=0;i<message.length();i++) {
	if(data[i]==1)
		for(int j=0;j<divisor.length;j++)
			data[i+j] ^= divisor[j];
}
//Display validity of data
boolean valid = true;
for(int i=0;i<data.length;i++)
	if(data[i]==1){
		valid = false;
		break;
}
if(valid==true) 
	System.out.println("\r\n Data stream is VALID");
else 
	System.out.println("Data stream is invalid. CRC error occurred.");
}
}

//Another method
import java.util.Scanner;

public class CRC {

    public static String crc(String rem, String poly, int checkError) {
        if (checkError == 0) {
            for (int i = 0; i < poly.length() - 1; i++) {
                rem += "0";
            }
        }

        for (int i = 0; i < rem.length() - poly.length() + 1; i++) {
            if (rem.charAt(i) == '1') {
                for (int j = 0; j < poly.length(); j++) {
                    if (rem.charAt(i + j) == poly.charAt(j)) {
                        rem = rem.substring(0, i + j) + '0' + rem.substring(i + j + 1);
                    } else {
                        rem = rem.substring(0, i + j) + '1' + rem.substring(i + j + 1);
                    }
                }
            }
        }
        return rem.substring(rem.length() - poly.length() + 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String data, poly;
        System.out.print("Enter the data to be transmitted >> ");
        data = scanner.nextLine();

        System.out.print("Enter the polynomial >> ");
        poly = scanner.nextLine();

        System.out.println("Transmitted data >> " + data + crc(data, poly, 0));

        System.out.print("Enter the received data >> ");
        data = scanner.nextLine();

        if (Integer.parseInt(crc(data, poly, 1)) == 0) {
            System.out.println("No error in data transmission");
        } else {
            System.out.println("Error has occurred in data transmission");
        }
    }
}
