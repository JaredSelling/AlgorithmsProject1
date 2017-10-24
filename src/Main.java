import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int ns = 5000;
        int m = 10;
        int nf = 100000;
        int delta = 5000;

        int[][] A = new int[m][nf];

        int choice = 0;

        //generate numbers in A[1...m, 1...nf]
        Random random = new Random();
        System.out.println("Creating array A...");
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < nf; j++) {
               A[i][j] = random.nextInt(32767);
            }
        }

        do {
            System.out.println("-----------------------------------------------------");
            System.out.println("Enter the number of the algorithm to test:");
            System.out.println("Insertion Sort (1)");
            System.out.println("Merge Sort     (2)");
            System.out.println("Quick Sort     (3)");
            System.out.println("Exit program   (0)");

            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();

            switch(choice) {
                case 1:
                    for(int n = ns; n<=nf; n+=delta) {
                        testInsertionSort(ns, nf, m, n, A, delta);
                    }
                    break;
                case 2:
                    for(int n = ns; n<=nf; n+= delta) {
                        testMergeSort(ns, nf, m, n, A, delta);
                    }
                    break;
                case 3:
                    for(int n = ns; n<=nf; n+=delta) {
                        testQuickSort(ns, nf, m, n, A, delta);
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Please enter a valid choice");
            }

            /*for(int n = ns; n<=nf; n+=delta) {
                testInsertionSort(ns, nf, m, n, A, delta);
                //testMergeSort(ns, nf, m, n, A, delta);
                //testQuickSort(ns, nf, m, n, A, delta);
            }*/
        } while(choice != 0);




    }



    public static void testInsertionSort(int ns, int nf, int m, int n, int[][] A, int delta) {

        long[][] insertionSortRTs = new long[m][nf];

        long[] insertionSortAvgRTs = new long[nf];

        System.out.println("*******TESTING INSERTION SORT*******");
        System.out.println("n = " + n + ", m = " + m);
        for(int i = 0; i<m; i++) {
            //make copy of part of part of A to run algorithms on
            int[] B = new int[n];
          //  System.out.println("Before sorting:");
            for(int x = 0; x<n; x++) {
                B[x] = A[i][x];
            }

            long startTime = System.nanoTime();
            InsertionSort(B);
            long endTime = System.nanoTime();
            long duration = endTime-startTime;
            System.out.println("n = " + n + ", i = " + i + ", Duration: " + duration + " nanoseconds");
            insertionSortRTs[i][n-1] = duration;
          /*  System.out.println("After sorting:");
            for(int y=0;y<B.length; y++) {
                System.out.println("B["+y+"]: " + B[y]);
            } */
        }

        //variable to hold sum of all elements in insertionSortRTs array
        long sum = 0;
        //sum up all values in insertionSortRTs for row i
        for(int x = 0; x<m; x++) {
            sum += insertionSortRTs[x][n-1];
        }

        //compute the average of all the RTs for a certain i and store the average in an array
        insertionSortAvgRTs[n-1] = sum/m;
        System.out.println("Average runtime for n = " + n + ": " + insertionSortAvgRTs[n-1]);

      /*  System.out.println("Average runtimes for n = " + n + ": ");
        for(int i = 0; i<insertionSortAvgRTs.length; i++) {
            System.out.println("insertionSortAvgRTs["+i+"]: " + insertionSortAvgRTs[i]);
        } */
    }

    public static void testMergeSort(int ns, int nf, int m, int n, int[][] A, int delta) {

        long[][] mergeSortRTs = new long[m][nf];

        long[] mergeSortAvgRTs = new long[nf];

        System.out.println("*******TESTING MERGE SORT*******");
        System.out.println("n = " + n + ", m = " + m);
        for(int i = 0; i<m; i++) {
            //make copy of part of part of A to run algorithms on
            int[] B = new int[n];
          //  System.out.println("Before sorting:");
            for(int x = 0; x<n; x++) {
                B[x] = A[i][x];
             //   System.out.println("B["+x+"]: " + B[x]);
            }

            long startTime = System.nanoTime();
            MergeSort(B, 0, n-1);
            long endTime = System.nanoTime();
            long duration = endTime-startTime;
            System.out.println("Duration: " + duration + " nanoseconds");
            mergeSortRTs[i][n-1] = duration;
         /*   System.out.println("After sorting:");
            for(int y=0;y<B.length; y++) {
                System.out.println("B["+y+"]: " + B[y]);
            } */
        }

        //variable to hold sum of all elements in insertionSortRTs array
        long sum = 0;
        //sum up all values in insertionSortRTs for row i
        for(int x = 0; x<m; x++) {
            sum += mergeSortRTs[x][n-1];
        }

        //compute the average of all the RTs for a certain i and store the average in an array
        mergeSortAvgRTs[n-1] = sum/m;
        System.out.println("Average runtime for n = " + n + ": " + mergeSortAvgRTs[n-1]);

     /*   System.out.println("Average runtimes for n = " + n + ": ");
        for(int i = 0; i<mergeSortAvgRTs.length; i++) {
            System.out.println("insertionSortAvgRTs["+i+"]: " + mergeSortAvgRTs[i]);
        } */
    }


    public static void testQuickSort(int ns, int nf, int m, int n, int[][] A, int delta) {

        long[][] quickSortRTs = new long[m][nf];

        long[] quickSortAvgRTs = new long[nf];

        System.out.println("*******TESTING QUICK SORT*******");
        System.out.println("n = " + n + ", m = " + m);
        for(int i = 0; i<m; i++) {
            //make copy of part of part of A to run algorithms on
            int[] B = new int[n];
         //   System.out.println("Before sorting:");
            for(int x = 0; x<n; x++) {
                B[x] = A[i][x];
             //   System.out.println("B["+x+"]: " + B[x]);
            }

            long startTime = System.nanoTime();
            QuickSort(B, 0, n-1);
            long endTime = System.nanoTime();
            long duration = endTime-startTime;
            System.out.println("n = " + n + ", i = " + i + ", Duration: " + duration + " nanoseconds");
            quickSortRTs[i][n-1] = duration;
           /* System.out.println("After sorting:");
            for(int y=0;y<B.length; y++) {
                System.out.println("B["+y+"]: " + B[y]);
            } */
        }

        //variable to hold sum of all elements in insertionSortRTs array
        long sum = 0;
        //sum up all values in insertionSortRTs for row i
        for(int x = 0; x<m; x++) {
            sum += quickSortRTs[x][n-1];
        }

        //compute the average of all the RTs for a certain i and store the average in an array
        quickSortAvgRTs[n-1] = sum/m;

        System.out.println("Average runtime for n = " + n + ": " + quickSortAvgRTs[n-1]);

       /* System.out.println("Average runtimes for n = " + n + ": ");
        for(int i = 0; i<quickSortAvgRTs.length; i++) {
            System.out.println("insertionSortAvgRTs["+i+"]: " + quickSortAvgRTs[i]);
        } */
    }






    public static int[] InsertionSort(int[] B) {
        for(int j = 1; j<B.length; j++) {
            int key = B[j];
            int i = j-1;
            while(i>=0 && B[i] > key) {
                B[i+1] = B[i];
                i--;
            }
            B[i+1] = key;
        }
        return B;
    }

    public static void QuickSort(int[] B, int p, int r) {
        if(p<r) {
            int q = Partition(B, p, r);
            QuickSort(B, p, q-1);
            QuickSort(B, q+1, r);
        }
    }

    public static int Partition(int[] B, int p, int r) {
        int x = B[r];
        int i = p-1;
        for(int j = p; j<=r-1; j++) {
            if(B[j] <= x) {
                i++;
                int temp = B[i];
                B[i] = B[j];
                B[j] = temp;
            }
        }
        int temp = B[i+1];
        B[i+1] = B[r];
        B[r] = temp;
        return i+1;
    }



    public static void MergeSort(int[] B, int p, int r) {
        if(p<r) {
            int q = (p+r) / 2;
            MergeSort(B, p, q);
            MergeSort(B,q+1, r);
            Merge(B, p, q, r);
        }
    }

    //Auxiliary function Merge to be called in MergeSort
    public static void Merge(int[] B, int p, int q, int r) {
        int n1 = q-p+1;
        int n2 = r-q;
        int i = 0;
        int j = 0;
        int[] L = new int[n1+1];
        int[] R = new int[n2+1];

        //??????
        for(i = 0; i<n1; i++) {
            L[i] = B[p+i];
        }

        for(j = 0; j<n2; j++) {
            R[j] = B[q+j+1];
        }

        L[n1] = R[n2] = Integer.MAX_VALUE;
        i = j = 0;

        for(int k = p; k<=r; k++) {
            if(L[i] <= R[j]) {
                B[k] = L[i];
                i++;
            }
            else {
                B[k] = R[j];
                j++;
            }
        }

    }
}
