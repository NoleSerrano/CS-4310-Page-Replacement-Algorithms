import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ReferenceStringGeneration2 {

	public static void main(String[] args) throws IOException {
		// FIFO:
		// https://learn.zybooks.com/zybook/CPPCS4310DongFall2021/chapter/7/section/2
		// LRU:
		// https://learn.zybooks.com/zybook/CPPCS4310DongFall2021/chapter/7/section/2
		// second-chance:
		// https://learn.zybooks.com/zybook/CPPCS4310DongFall2021/chapter/7/section/3

		Scanner sc = new Scanner(System.in);
		File RS = new File("FILE_PATH"); // reference string
		FileWriter fileWriter = new FileWriter(RS);
		int P = 1048576;
		int s = 0;
		int desiredLength = 1000;
		int m = 200;
		
		int e = 3;
		double t = .01;
		


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
