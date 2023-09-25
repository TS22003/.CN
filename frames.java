
   import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Frame {
    int seqNo;
    int data;

    public Frame(int seqNo) {
        this.seqNo = seqNo;
    }
}

public class Main {

    public static void bubbleSort(List<Frame> frames) {
        int n = frames.size();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (frames.get(j).seqNo > frames.get(j+1).seqNo) {
                    Frame temp = frames.get(j);
                    frames.set(j, frames.get(j+1));
                    frames.set(j+1, temp);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of frames: ");
        int n = scanner.nextInt();

        List<Integer> seqList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = (int) (Math.random() * (n * 100)) + 1;
            while (seqList.contains(x)) {
                x = (int) (Math.random() * (n * 100)) + 1;
            }
            seqList.add(x);
        }

        List<Frame> frames = new ArrayList<>();
        for (int seqNo : seqList) {
            frames.add(new Frame(seqNo));
        }

        for (Frame frame : frames) {
            System.out.print("Enter data for frame " + frame.seqNo + ": ");
            frame.data = scanner.nextInt();
        }

        bubbleSort(frames);

        for (Frame frame : frames) {
            System.out.println("Frame " + frame.seqNo + " : " + frame.data);
        }
    }
}

Frame sorting
