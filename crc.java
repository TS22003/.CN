#include<iostream>
using namespace std;

string crc(string rem, string poly, int checkError){
    if ( !checkError )
        for(int i = 0; i<poly.size() -1; ++i)
            rem += "0";

    for(int i = 0; i<rem.size() - poly.size() + 1 ; ++i){
        if ( rem[i] == '1' ){
            for(int j = 0; j<poly.size(); ++j){
                if ( rem[i+j] == poly[j] )
                    rem[i+j] = '0';
                else
                    rem[i+j] = '1';
            }
        }
    }
    return rem.substr(rem.size() - poly.size() + 1); 
}

int main(){
    string data, poly;
    cout << "Enter the data to be transmitted >> ";
    cin >> data;

    cout << "Enter the polynomial >> ";
    cin >> poly;

    cout << "Transmitted data >> " << data + crc(data, poly, 0) << endl;

    cout << "Enter the received data >> ";
    cin >> data;

    if ( stoi(crc(data, poly, 1)) == 0 )
        cout << "No error in data transmission " << endl;
    else
        cout << "Error has occurred in data transmission " << endl;

    return 0;
}
