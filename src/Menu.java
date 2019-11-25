import Option1.LinkedParalelSearch;
import Option2.ArrayParalelSearch;
import Option3.ArrayParalelSearchSharedMem;
import Option4.RecursiveSortThread;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Menu {

    public void showMenu(){
        int opcio;
        do {
            System.out.println("Sel·leccion a la opcio corresponent:");
            System.out.println("\t1. Cerca LinkedList");
            System.out.println("\t2. Cerca Array");
            System.out.println("\t3. Cerca Array memoria compartida");
            System.out.println("\t4. Merge sort");
            opcio = readInt();
        }while (opcio < 0 || opcio > 4);

        switch (opcio){
            case 1:
                System.out.println("Es busca el numero \"" + 4 + "\" en el array:");
                new LinkedParalelSearch(11, 4);
                break;
            case 2:
                int arrayLength = 23;
                int numThreads = 4;
                int aBuscar = 4;
                int[] ArrayO2 = new int[arrayLength];
                Random random = new Random();
                System.out.println("Es busca el numero \"" + 4 + "\" en el array:");
                for (int i = 0; i < arrayLength; i++){
                    ArrayO2[i] = random.nextInt(10);
                }
                System.out.print(Arrays.toString(ArrayO2) + "\n");

                new ArrayParalelSearch(aBuscar, ArrayO2, numThreads);
                int casella = ArrayParalelSearch.cercaParallela();
                for (int i = numThreads*(arrayLength/numThreads); i < arrayLength; i++){
                    if (ArrayO2[i] == aBuscar){
                        System.out.println("\nSoc el thread " + Thread.currentThread().getName() + " i he trobat el nombre en la casella " + i + " del meu subarray.");
                    }
                }
                break;

            case 3:
                arrayLength = 23;
                numThreads = 4;
                aBuscar = 4;
                int[] ArrayO3 = new int[arrayLength];
                random = new Random();
                System.out.println("Es busca el numero \"" + 4 + "\" en el array:");
                for (int i = 0; i < arrayLength; i++){
                    ArrayO3[i] = random.nextInt(10);
                }
                System.out.println(Arrays.toString(ArrayO3));

                new ArrayParalelSearchSharedMem(aBuscar, ArrayO3, numThreads).cercaParallela();

                for (int i = numThreads*(arrayLength/numThreads); i < arrayLength; i++){
                    if (ArrayO3[i] == aBuscar){
                        System.out.println("\nSoc el thread " + Thread.currentThread().getName() + " i he trobat el nombre en la casella " + i + " del meu subarray.");
                    }
                }

                break;
            case 4:
                arrayLength = 23;
                int[] ArrayO4 = new int[arrayLength];
                int[] Array04v2 = new int[arrayLength];
                random = new Random();
                for (int i = 0; i < arrayLength; i++){
                    ArrayO4[i] = random.nextInt(10);
                }
                System.out.println("Array a ordenar: " + Arrays.toString(ArrayO4));

                System.arraycopy(ArrayO4, 0, Array04v2, 0, arrayLength);


                RecursiveSortThread recursiveSortThread = new RecursiveSortThread(ArrayO4);
                long startTime = System.nanoTime();
                recursiveSortThread.start();
                try {
                    recursiveSortThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long endTime = System.nanoTime();
                System.out.println("Final sorted array: " + Arrays.toString(recursiveSortThread.getArray()));
                System.out.println("Time taken: " + (endTime - startTime)/1000000 + "ms");


                startTime = System.nanoTime();

                int k;
                int minValue;
                for (int i = 0; i < Array04v2.length-1; i++) {
                    k = i;
                    for (int j = k + 1; j < Array04v2.length; j++) {
                        if (Array04v2[k] > Array04v2[j]) { //change to a[k]<a[j] to sort in the descending order
                            k = j; // record index of the smallest value
                        }
                    }
                    if (k != i) { // to avoid swapping with self
                        minValue = Array04v2[k];
                        Array04v2[k] = Array04v2[i];
                        Array04v2[i] = minValue;
                    }
                }

                endTime = System.nanoTime();
                System.out.println("Final sorted array (sequential): " + Arrays.toString(ArrayO4));
                System.out.println("Time taken (sequential): " + (endTime - startTime)/1000000 + "ms");
                break;
        }

    }

    /**
     * Comprova que l'usuari introduiexi un enter
     * @return El numero introduit per l'usuari o -1 en cas de que no hagi introduit un numero
     */
    private int readInt(){
        try {
            Scanner read = new Scanner(System.in);
            return read.nextInt();
        }catch (InputMismatchException ignored){
        }
        return -1;
    }
}
