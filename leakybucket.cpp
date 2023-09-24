#include <iostream>
using namespace std;

int main(){
    int qSize, bucketSize, output, stored = 0, sizeLeft ;

    cout << "Enter the size of Queue >> ";
    cin >> qSize;

    int arr[qSize];

    cout << "Enter the data >> ";
    for(int i = 0; i<qSize; ++i)
        cin >> arr[i];

    cout << "Enter the bucket size >> ";
    cin >> bucketSize;

    cout << "Enter the output size >> ";
    cin >> output;

    for(int i = 0; i<qSize; ++i){
        sizeLeft = bucketSize - stored;

        if ( arr[i] <= sizeLeft ){
            stored += arr[i];
            cout << "Input >> " << arr[i] << " Stored size >> " << stored << endl;
        }
        else{
            cout << "Input >> " << arr[i] << " Discarded ( Packet Loss ) " << " Stored size >> " << stored << endl;
        }

        if ( stored != 0 ){
            stored -= output;
            cout << "After output stored >> " << stored << endl;
        }
    }
}
