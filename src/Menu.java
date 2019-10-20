import Option1.LinkedParalelSearch;
import Option2.ArrayParalelSearch;
import Option3.ArrayParalelSearchSharedMem;

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
                new LinkedParalelSearch(10, 4);
                break;
            case 2:
                int arrayLength = 21;
                int[] ArrayO2 = new int[arrayLength];
                Random random = new Random();
                System.out.println("Es busca el numero \"" + 4 + "\" en el array:");
                for (int i = 0; i < arrayLength; i++){
                    ArrayO2[i] = random.nextInt(10);
                    System.out.print(ArrayO2[i] + ",");
                }
                new ArrayParalelSearch(4, ArrayO2, 3);
                int casella = ArrayParalelSearch.cercaParallela();
                System.out.println("\n\nS'ha trobat el nombre a cerca en la casella " + casella);
                break;
            case 3:
                arrayLength = 21;
                int[] ArrayO1 = new int[arrayLength];
                random = new Random();
                for (int i = 0; i < arrayLength; i++){
                    ArrayO1[i] = random.nextInt(10);
                    System.out.print(ArrayO1[i] + ",");
                }
                new ArrayParalelSearchSharedMem(4, ArrayO1, 3);
                break;
            case 4:
                break;
        }
    }

    /**
     * Comprova que l'usuari introduiex un enter
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
