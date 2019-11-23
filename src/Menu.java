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
                        System.out.println("\nSoc el thread " + Thread.currentThread().getId() + " (Menu) i he trobat el nombre en la casella " + i + " del meu subarray.");
                    }
                }

                break;
            case 3:
                arrayLength = 20;
                int[] ArrayO1 = new int[arrayLength];
                random = new Random();
                System.out.println("Es busca el numero \"" + 4 + "\" en el array:");
                for (int i = 0; i < arrayLength; i++){
                    ArrayO1[i] = random.nextInt(10);
                    System.out.print(ArrayO1[i] + ", ");
                }
                new ArrayParalelSearchSharedMem(4, ArrayO1, 3).cercaParallela();
                //ArrayParalelSearchSharedMem.cercaParallela();
                break;
            case 4:
                arrayLength = 20;
                int[] ArrayO3 = new int[arrayLength];
                random = new Random();
                for (int i = 0; i < arrayLength; i++){
                    ArrayO3[i] = random.nextInt(10);
                }

                RecursiveSortThread recursiveSortThread = new RecursiveSortThread(ArrayO3);
                recursiveSortThread.start();
                try {
                    recursiveSortThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Final sorted array: " + Arrays.toString(recursiveSortThread.getArray()));
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
