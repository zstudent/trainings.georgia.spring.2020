package lesson200423;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Function;

public class MatrixExample {

	public static void main(String[] args) {

		System.out.println(Runtime.getRuntime().availableProcessors() / 2);
		
		System.out.println("generating..");

		long[][] matrix = generate(2000);

		System.out.println("calcualting..");

		System.out.println(elapsed(MatrixExample::matrixSum, matrix));
		System.out.println(elapsed(MatrixExample::parallelMatrixSum, matrix));
		System.out.println(elapsed(MatrixExample::parallelMatrixSumCompletionService, matrix));
	}

	public static BigInteger parallelMatrixSumCompletionService(long[][] matrix) {
		BigInteger result = new BigInteger("0");

		ExecutorService service = Executors.newFixedThreadPool(
				Runtime.getRuntime().availableProcessors() / 2);
		//Pass executor service to executor service completion service object.
		ExecutorCompletionService<BigInteger> completionService = new ExecutorCompletionService<>(service);

		for (int i = 0; i < matrix.length; i++) {
			int tmp = i;
			//just submitting in completionService obj.
			completionService.submit(()->{
				return rowSum(matrix[tmp]);
			});
		}
		BigInteger res = new BigInteger("0");
		//iterate again cause completionService doesn't know how many Futures are in it.
		for (int i = 0; i < matrix.length; i++) {
			try {
				//Use take(not poll) since it waits if no Function is ready.
				Future<BigInteger> tmp = completionService.take();
				BigInteger b = tmp.get();
				res.add(b);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static BigInteger matrixSum(long[][] matrix) {
		BigInteger result = new BigInteger("0");
		for (int i = 0; i < matrix.length; i++) {
			BigInteger rowSum = rowSum(matrix[i]);
			result.add(rowSum);
		}
		return result;
	}

	public static BigInteger parallelMatrixSum(long[][] matrix) {
		BigInteger result = new BigInteger("0");

		ExecutorService service = Executors.newFixedThreadPool(
				Runtime.getRuntime().availableProcessors() / 2);

		List<Future<BigInteger>> futures = new ArrayList<Future<BigInteger>>();
		
		for (int i = 0; i < matrix.length; i++) {
			int tmp = i;
			futures.add(service.submit(()->{
				return rowSum(matrix[tmp]);
			}));
		}
		
		for (Future<BigInteger> future : futures) {
			try {
				BigInteger rowSum = future.get();
				result.add(rowSum);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public static BigInteger rowSum(long[] arr) {
		BigInteger result = new BigInteger("0");
		for (int i = 0; i < arr.length; i++) {
			result.multiply(BigInteger.valueOf(arr[i]));
		}
		return result;
	}

	public static long[][] generate(int size) {

		Random random = new Random();

		long[][] result = new long[size][size];

		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result.length; j++) {
				result[i][j] = Math.abs(random.nextLong());
			}
		}

		return result;
	}

	static public long elapsed(Function<long[][], BigInteger> summer,
			long[][] matrix) {
		long start = System.nanoTime();
		BigInteger result = summer.apply(matrix);
		long stop = System.nanoTime();
		return stop - start;
	}

}
