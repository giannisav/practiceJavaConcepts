package concurrency;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Write a program that uses multiple threads to find the integer in a range that has the largest number of divisors.
 * The range is from 1 to 100000.
 * By using threads, your program will take less time to do the computation when it is run on a multiprocessor computer.
 * At the end of the program, output the elapsed time, the integer that has the largest number of divisors
 * and the number of divisors that it has."
 */
public class ConcurrencyMaxDivisors {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        int numOfThreads;
        do {
            Scanner s = new Scanner(System.in);
            System.out.print("Enter positive number of threads : ");
            numOfThreads = s.nextInt();
        } while (numOfThreads <= 0);


        Instant start = Instant.now();

        ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);
        List<Callable<Integer[]>> tasks = new ArrayList<>();

        int step = 100000 / numOfThreads;

        for (int i = 1; i < 100000; i++) {
            int startPoint = i;
            int endPoint = i + step;
            tasks.add(() -> findMaxDivisors(startPoint, endPoint));
            i = endPoint;
        }

        List<Future<Integer[]>> results = executor.invokeAll(tasks);

        long maxNumber = 0, maxDivisors = 0;
        for (Future<Integer[]> result : results) {
            if (result.get()[1] > maxDivisors) {
                maxDivisors = result.get()[1];
                maxNumber = result.get()[0];
            }
        }

        System.out.println("\nMax number: " + maxNumber + " with " + maxDivisors + " divisors");
        executor.shutdown();
        Instant finish = Instant.now();
        System.out.println("Execution time with " + numOfThreads + " threads: " + Duration.between(start, finish).toMillis() + " milliseconds");
    }

    public static Integer[] findMaxDivisors(int start, int end) {
        Integer[] maxPair = new Integer[2];
        int maxDivisors;
        int numWithMax;

        maxDivisors = 1;
        numWithMax = 1;

        for (int j = start; j <= end; j++) {

            int d;
            int divisorCount;

            divisorCount = 0;

            for (d = 1; d <= end; d++) {  // Count the divisors of N.
                if (j % d == 0)
                    divisorCount++;
            }
            int numbers = 0, divisors = 1;

            if (divisorCount > maxDivisors) {
                maxDivisors = divisorCount;
                numWithMax = j;
            }
            maxPair[numbers] = numWithMax;
            maxPair[divisors] = maxDivisors;
        }
        return maxPair;
    }
}

