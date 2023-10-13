import math

def generateKey(p,q):
    n = p*q
    phi = (p-1)*(q-1)

    e = 2

    while e<phi:
        if math.gcd(e,phi)==1 and math.gcd(e,n)==1:
            break

        else:
            e = e+1

    d = 1

    while math.fmod(d*e,phi) != 1:
        d = d+1

    return ((e,n),(d,n))

def encrypted(pk,plain):
    e,n = pk

    cipher = [(ord(char) ** e)%n for char in plain]

    return cipher

def decrypt(pk,cipher):
    d,n = pk
    plaintext = [chr((c**d)%n) for c in cipher]

    return "".join(plaintext)

if __name__ == "__main__":
    p = 101
    q = 103
    
    public, private = generateKey(p,q)
    print("Enter the message to be decrypted: ")
    msg = input()
    cipher = encrypted(public,msg)
    print(f"The ciphertext for orginal message is: {cipher}")
    print(f"The decypted message is: {decrypt(private,cipher)}")
