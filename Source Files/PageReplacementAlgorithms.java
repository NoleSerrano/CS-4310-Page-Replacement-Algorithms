import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PageReplacementAlgorithms {

	public static int F; // number of frames
	public static int[] M; // phyiscal memory

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		print("File path: ");
		File RS = new File(sc.nextLine());
		print("Number of Frames: ");
		F = sc.nextInt();
		M = new int[F];

		print("FIFO Page Faults: " + FIFO(RS));
		print("\nLRU Page Faults: " + LRU(RS));
		print("\nSecond Chance Page Faults: " + secondChance(RS));
		sc.close();
	}

	public static int FIFO(File RS) throws IOException {
		int pageFaults = 0;
		boolean pageFound = false;
		int oldestFrame;
		Scanner sc = new Scanner(RS);
		// fill initial frames
		for (int i = 0; i < F && sc.hasNextInt(); i++) {
			M[i] = sc.nextInt();
		}
		oldestFrame = 0;
		while (sc.hasNextInt()) {
			pageFound = false;
			int p = sc.nextInt();
			for (int i = 0; i < F; i++) {
				if (M[i] == p) { // page found in phyiscal memory
					pageFound = true;
					continue;
				}
			}
			// page not inside memory - FIFO
			if (!pageFound) {
				pageFaults++;
				M[oldestFrame] = p;
				oldestFrame = (oldestFrame + 1) % F;
			}
		}
		sc.close();
		return pageFaults;
	}

	public static int LRU(File RS) throws IOException {
		int pageFaults = 0;
		boolean pageFound = false;
		Scanner sc = new Scanner(RS);
		int[] Q = new int[F]; // queue for least recently used
		// fill initial frames
		for (int i = 0; i < F && sc.hasNextInt(); i++) {
			M[i] = sc.nextInt();
			Q[i] = M[i];
		}
		while (sc.hasNextInt()) {
			pageFound = false;
			int p = sc.nextInt();
			for (int i = 0; i < F; i++) {
				if (M[i] == p) { // page found in phyiscal memory
					int prev = M[F - 1];
					int temp;
					for (int j = F - 1; j > i; j--) {
						temp = M[j - 1];
						M[j - 1] = prev;
						prev = temp;
					}
					M[F - 1] = p; // lru
					pageFound = true;
					continue;
				}
			}
			// page not inside memory - LRU
			if (!pageFound) {
				pageFaults++;
				int prev = M[F - 1];
				int temp;
				for (int i = F - 1; i > 0; i--) {
					temp = M[i - 1];
					M[i - 1] = prev;
					prev = temp;
				}
				M[F - 1] = p;
			}
		}
		sc.close();
		return pageFaults;
	}

	public static int secondChance(File RS) throws IOException {
		int pageFaults = 0;
		boolean pageFound = false;
		int[] R = new int[F]; // holds the r bits
		int pointer;
		Scanner sc = new Scanner(RS);
		// fill initial frames
		for (int i = 0; i < F && sc.hasNextInt(); i++) {
			M[i] = sc.nextInt();
			R[i] = 1; // all frames recently used since phyiscal memory initialized
		}
		pointer = 0; // pointer initially points to first frame
		while (sc.hasNextInt()) {
			pageFound = false;
			int p = sc.nextInt();
			for (int i = 0; i < F; i++) {
				if (M[i] == p) { // page found in physical memory
					R[i] = 1;
					pageFound = true;
					continue;
				}
			}
			// page not inside memory - second chance
			if (!pageFound) {
				pageFaults++;
				do {
					if (R[pointer] == 1) {
						R[pointer] = 0;
					} else { // 0
						M[pointer] = p;
						R[pointer] = 1;
						break;
					}
					pointer = (pointer + 1) % F;
				} while (true); // do until find a place for p
			}
		}
		sc.close();
		return pageFaults;
	}

	public static void printPhyiscalMemory() {
		for (int i = 0; i < F; i++) {
			print(M[i] + " ");
		}
	}

	public static void print(Object o) {
		System.out.print(o.toString());
	}

}
