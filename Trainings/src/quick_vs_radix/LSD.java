package quick_vs_radix;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

public class LSD {
    private static final int BITS_PER_BYTE = 8;

    public static void main(String[] args) {
		int[] a = generate(100_000_000);
		int[] b = Arrays.copyOf(a, a.length);
		int[] c = Arrays.copyOf(a, a.length);
		
		System.out.println(benchmark(LSD::radixSort, a));
		System.out.println(benchmark(Arrays::parallelSort, b));
		System.out.println(benchmark(Arrays::sort, c));
		System.out.println(Arrays.equals(a, b));
		System.out.println(Arrays.equals(c, b));
	}
    

	private static long benchmark(Consumer<int[]> sorter, int[] arr) {
		long start = System.currentTimeMillis();
		sorter.accept(arr);
		return System.currentTimeMillis() - start;
	}


	private static Random r = new Random();
    
    static private int[] generate(int size) {
    	int[] a = new int[size];
    	for (int i = 0; i < a.length; i++) {
			a[i] = r.nextInt();
		}
    	return a;
	}
    

   /**
     * Rearranges the array of 32-bit integers in ascending order.
     * This is about 2-3x faster than Arrays.sort().
     *
     * @param a the array to be sorted
     */
    public static void radixSort(int[] a) {
        final int BITS = 32;                 // each int is 32 bits 
        final int R = 1 << BITS_PER_BYTE;    // each bytes is between 0 and 255
        final int MASK = R - 1;              // 0xFF
        final int W = BITS / BITS_PER_BYTE;  // each int is 4 bytes

        int n = a.length;
        int[] aux = new int[n];

        for (int d = 0; d < W; d++) {         

            // compute frequency counts
            int[] count = new int[R+1];
            int shift = BITS_PER_BYTE*d;
			for (int i = 0; i < n; i++) {           
                int c = (a[i] >> shift) & MASK;
                count[c + 1]++;
            }

            // compute cumulates
            for (int r = 0; r < R; r++)
                count[r+1] += count[r];

            // for most significant byte, 0x80-0xFF comes before 0x00-0x7F
            if (d == W-1) {
                int shift1 = count[R] - count[R/2];
                int shift2 = count[R/2];
                for (int r = 0; r < R/2; r++)
                    count[r] += shift1;
                for (int r = R/2; r < R; r++)
                    count[r] -= shift2;
            }

            // move data
            for (int i = 0; i < n; i++) {
                int c = (a[i] >> shift) & MASK;
                aux[count[c]++] = a[i];
            }

            // copy back
            for (int i = 0; i < n; i++)
                a[i] = aux[i];
//            int[] t = aux;
//            aux = a;
//            a = t;
        }
    }
}