import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
public class RSA
{
private BigInteger p,q,N,phi,e,d;
private int bitlength=1024;
private Random r;
public RSA()
{
r=new Random();
p=BigInteger.probablePrime(bitlength,r);
q=BigInteger.probablePrime(bitlength,r);
System.out.println("Prime number p is"+p);
System.out.println("prime number q is"+q);
N=p.multiply(q);
phi=p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
e=BigInteger.probablePrime(bitlength/2,r);
while(phi.gcd(e).compareTo(BigInteger.ONE)>0&&e.compareTo(phi)<0)
{
e.add(BigInteger.ONE);
}
System.out.println("Public key is"+e);
d=e.modInverse(phi);
System.out.println("Private key is"+d);
}
public RSA(BigInteger e,BigInteger d,BigInteger N)
{
this.e=e;
this.d=d;
this.N=N;
}
public static void main(String[] args)throws IOException
{
RSA rsa=new RSA();
DataInputStream in=new DataInputStream(System.in);
String testString;
System.out.println("Enter the plain text:");
testString=in.readLine();
System.out.println("Encrypting string:"+testString);
System.out.println("string in bytes:"+bytesToString(testString.getBytes()));
byte[] encrypted=rsa.encrypt(testString.getBytes());
byte[] decrypted=rsa.decrypt(encrypted);
System.out.println("Dcrypting Bytes:"+bytesToString(decrypted));
System.out.println("Dcrypted string:"+new String(decrypted));
}
private static String bytesToString(byte[] encrypted)
{
String test=" ";
for(byte b:encrypted)
{
test+=Byte.toString(b);
}
return test;
}
public byte[]encrypt(byte[]message)
{
return(new BigInteger(message)).modPow(e,N).toByteArray();
}
public byte[]decrypt(byte[]message)
{
return(new BigInteger(message)).modPow(d,N).toByteArray();
}
}



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


// package com.anuj.security.encryption;

// import java.io.BufferedOutputStream;
// import java.io.File;
// import java.io.FileInputStream;
// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.io.ObjectInputStream;
// import java.io.ObjectOutputStream;
// import java.math.BigInteger;
// import java.security.KeyFactory;
// import java.security.KeyPair;
// import java.security.KeyPairGenerator;
// import java.security.NoSuchAlgorithmException;
// import java.security.PrivateKey;
// import java.security.PublicKey;
// import java.security.spec.InvalidKeySpecException;
// import java.security.spec.RSAPrivateKeySpec;
// import java.security.spec.RSAPublicKeySpec;
// import javax.crypto.Cipher;

// /**
//  * 
//  * @author Anuj
//  * Blog www.goldenpackagebyanuj.blogspot.com
//  * RSA - Encrypt Data using Public Key
//  * RSA - Descypt Data using Private Key
//  */
// public class RSAEncryptionDecryption {

// 	private static final String PUBLIC_KEY_FILE = "Public.key";
// 	private static final String PRIVATE_KEY_FILE = "Private.key";
	
// 	public static void main(String[] args) throws IOException {

// 		try {
// 			System.out.println("-------GENRATE PUBLIC and PRIVATE KEY-------------");
// 			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
// 			keyPairGenerator.initialize(2048); //1024 used for normal securities
// 			KeyPair keyPair = keyPairGenerator.generateKeyPair();
// 			PublicKey publicKey = keyPair.getPublic();
// 			PrivateKey privateKey = keyPair.getPrivate();
// 			System.out.println("Public Key - " + publicKey);
// 			System.out.println("Private Key - " + privateKey);

// 			//Pullingout parameters which makes up Key
// 			System.out.println("\n------- PULLING OUT PARAMETERS WHICH MAKES KEYPAIR----------\n");
// 			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
// 			RSAPublicKeySpec rsaPubKeySpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
// 			RSAPrivateKeySpec rsaPrivKeySpec = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);
// 			System.out.println("PubKey Modulus : " + rsaPubKeySpec.getModulus());
// 			System.out.println("PubKey Exponent : " + rsaPubKeySpec.getPublicExponent());
// 			System.out.println("PrivKey Modulus : " + rsaPrivKeySpec.getModulus());
// 			System.out.println("PrivKey Exponent : " + rsaPrivKeySpec.getPrivateExponent());
			
// 			//Share public key with other so they can encrypt data and decrypt thoses using private key(Don't share with Other)
// 			System.out.println("\n--------SAVING PUBLIC KEY AND PRIVATE KEY TO FILES-------\n");
// 			RSAEncryptionDecryption rsaObj = new RSAEncryptionDecryption();
// 			rsaObj.saveKeys(PUBLIC_KEY_FILE, rsaPubKeySpec.getModulus(), rsaPubKeySpec.getPublicExponent());
// 			rsaObj.saveKeys(PRIVATE_KEY_FILE, rsaPrivKeySpec.getModulus(), rsaPrivKeySpec.getPrivateExponent());
			
// 			//Encrypt Data using Public Key
// 			byte[] encryptedData = rsaObj.encryptData("Anuj Patel - Classified Information !");
			
// 			//Descypt Data using Private Key
// 			rsaObj.decryptData(encryptedData);
			
// 		} catch (NoSuchAlgorithmException e) {
// 			e.printStackTrace();
// 		}catch (InvalidKeySpecException e) {
// 			e.printStackTrace();
// 		}

// 	}
	
// 	/**
// 	 * Save Files
// 	 * @param fileName
// 	 * @param mod
// 	 * @param exp
// 	 * @throws IOException
// 	 */
// 	private void saveKeys(String fileName,BigInteger mod,BigInteger exp) throws IOException{
// 		FileOutputStream fos = null;
// 		ObjectOutputStream oos = null;
		
// 		try {
// 			System.out.println("Generating "+fileName + "...");
// 			fos = new FileOutputStream(fileName);
// 			oos = new ObjectOutputStream(new BufferedOutputStream(fos));
			
// 			oos.writeObject(mod);
// 			oos.writeObject(exp);			
			
// 			System.out.println(fileName + " generated successfully");
// 		} catch (Exception e) {
// 			e.printStackTrace();
// 		}
// 		finally{
// 			if(oos != null){
// 				oos.close();
				
// 				if(fos != null){
// 					fos.close();
// 				}
// 			}
// 		}		
// 	}
	
// 	/**
// 	 * Encrypt Data
// 	 * @param data
// 	 * @throws IOException
// 	 */
// 	private byte[] encryptData(String data) throws IOException {
// 		System.out.println("\n----------------ENCRYPTION STARTED------------");
		
// 		System.out.println("Data Before Encryption :" + data);
// 		byte[] dataToEncrypt = data.getBytes();
// 		byte[] encryptedData = null;
// 		try {
// 			PublicKey pubKey = readPublicKeyFromFile(PUBLIC_KEY_FILE);
// 			Cipher cipher = Cipher.getInstance("RSA");
// 			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
// 			encryptedData = cipher.doFinal(dataToEncrypt);
// 			System.out.println("Encryted Data: " + encryptedData);
			
// 		} catch (Exception e) {
// 			e.printStackTrace();
// 		}	
		
// 		System.out.println("----------------ENCRYPTION COMPLETED------------");		
// 		return encryptedData;
// 	}

// 	/**
// 	 * Encrypt Data
// 	 * @param data
// 	 * @throws IOException
// 	 */
// 	private void decryptData(byte[] data) throws IOException {
// 		System.out.println("\n----------------DECRYPTION STARTED------------");
// 		byte[] descryptedData = null;
		
// 		try {
// 			PrivateKey privateKey = readPrivateKeyFromFile(PRIVATE_KEY_FILE);
// 			Cipher cipher = Cipher.getInstance("RSA");
// 			cipher.init(Cipher.DECRYPT_MODE, privateKey);
// 			descryptedData = cipher.doFinal(data);
// 			System.out.println("Decrypted Data: " + new String(descryptedData));
			
// 		} catch (Exception e) {
// 			e.printStackTrace();
// 		}	
		
// 		System.out.println("----------------DECRYPTION COMPLETED------------");		
// 	}
	
// 	/**
// 	 * read Public Key From File
// 	 * @param fileName
// 	 * @return PublicKey
// 	 * @throws IOException
// 	 */
// 	public PublicKey readPublicKeyFromFile(String fileName) throws IOException{
// 		FileInputStream fis = null;
// 		ObjectInputStream ois = null;
// 		try {
// 			fis = new FileInputStream(new File(fileName));
// 			ois = new ObjectInputStream(fis);
			
// 			BigInteger modulus = (BigInteger) ois.readObject();
// 		    BigInteger exponent = (BigInteger) ois.readObject();
			
// 		    //Get Public Key
// 		    RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(modulus, exponent);
// 		    KeyFactory fact = KeyFactory.getInstance("RSA");
// 		    PublicKey publicKey = fact.generatePublic(rsaPublicKeySpec);
		    		    
// 		    return publicKey;
		    
// 		} catch (Exception e) {
// 			e.printStackTrace();
// 		}
// 		finally{
// 			if(ois != null){
// 				ois.close();
// 				if(fis != null){
// 					fis.close();
// 				}
// 			}
// 		}
// 		return null;
// 	}
	
// 	/**
// 	 * read Public Key From File
// 	 * @param fileName
// 	 * @return
// 	 * @throws IOException
// 	 */
// 	public PrivateKey readPrivateKeyFromFile(String fileName) throws IOException{
// 		FileInputStream fis = null;
// 		ObjectInputStream ois = null;
// 		try {
// 			fis = new FileInputStream(new File(fileName));
// 			ois = new ObjectInputStream(fis);
			
// 			BigInteger modulus = (BigInteger) ois.readObject();
// 		    BigInteger exponent = (BigInteger) ois.readObject();
			
// 		    //Get Private Key
// 		    RSAPrivateKeySpec rsaPrivateKeySpec = new RSAPrivateKeySpec(modulus, exponent);
// 		    KeyFactory fact = KeyFactory.getInstance("RSA");
// 		    PrivateKey privateKey = fact.generatePrivate(rsaPrivateKeySpec);
		    		    
// 		    return privateKey;
		    
// 		} catch (Exception e) {
// 			e.printStackTrace();
// 		}
// 		finally{
// 			if(ois != null){
// 				ois.close();
// 				if(fis != null){
// 					fis.close();
// 				}
// 			}
// 		}
// 		return null;
// 	}
// }
