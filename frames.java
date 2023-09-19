import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class FrameSorting {

    // Sorting the Frames
    public static List<int[]> sortFrame(List<int[]> frame) {
        Collections.sort(frame, (a, b) -> Integer.compare(a[0], b[0]));
        return frame;
    }

    public static void main(String[] args) {
        List<int[]> frame = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of Frames >> ");
        int n = scanner.nextInt();

        Random random = new Random();

        for (int i = 0; i < n; i++) {
            int seqNum = random.nextInt(10000) + 1;
            System.out.printf("Enter the data for %dth frame >> ", i + 1);
            int data = scanner.nextInt();

            frame.add(new int[]{seqNum, data});
        }

        System.out.println("\nBefore Sorting >> ");
        for (int[] i : frame) {
            System.out.printf("Seq. Num -> %d, Data -> %d%n", i[0], i[1]);
        }

        frame = sortFrame(frame);

        System.out.println("\nAfter Sorting >> ");
        for (int[] i : frame) {
            System.out.printf("Seq. Num -> %d, Data -> %d%n", i[0], i[1]);
        }

        scanner.close();
    }
}
