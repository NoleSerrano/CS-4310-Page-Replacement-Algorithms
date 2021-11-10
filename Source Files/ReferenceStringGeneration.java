import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ReferenceStringGeneration {

	public static void main(String[] args) throws IOException {
		// FIFO:
		// https://learn.zybooks.com/zybook/CPPCS4310DongFall2021/chapter/7/section/2
		// LRU:
		// https://learn.zybooks.com/zybook/CPPCS4310DongFall2021/chapter/7/section/2
		// second-chance:
		// https://learn.zybooks.com/zybook/CPPCS4310DongFall2021/chapter/7/section/3

		Scanner sc = new Scanner(System.in);
		print("File path: ");
		File RS = new File(sc.nextLine()); // reference string
		FileWriter fileWriter = new FileWriter(RS);
		print("Virtual Memory Size: P = ");
		int P = sc.nextInt();
		sc.nextLine(); // int P = 1048576; // virtual memory size
		print("Starting Location: s = ");
		int s = sc.nextInt();
		sc.nextLine(); // int s = 0; // starting location
		print("Size of L: e = ");
		int e = sc.nextInt();
		sc.nextLine(); // int e = 5; // size of L
		print("Rate of Motion: m = ");
		int m = sc.nextInt();
		sc.nextLine(); // int m = 20; // rate of motion
		print("Probabiltiy of Transition: t = ");
		double t = sc.nextDouble();
		sc.nextLine(); // double t = .01; // probability of transition
		print("Desired Length of Reference String = ");
		int desiredLength = sc.nextInt();
		sc.nextLine(); // desiredLength of RS
		
//		P = 1048576;
//		s = 0;
//		e = 5;
//		m = 20;
//		t = .01;

		int counter = 0;
		do {
			for (int i = 0; i < m; i++) {
				fileWriter.write(random(s, s + e) + " ");
			}

			double r = Math.random(); // random number in range [0, 1]

			if (r < t) { // process transitions to a new location s
				s = random(0, P);
			} else { // process remains within current locus
				s = (s + 1) % P;
			}
			counter++;
		} while (counter < desiredLength);

		fileWriter.close();
		sc.close();
	}

	public static int random(int a, int b) { // random [a, b]
		return a + (int) (Math.random() * ((b - a) + 1));
	}

	public static void print(Object o) {
		System.out.print(o.toString());
	}
}
