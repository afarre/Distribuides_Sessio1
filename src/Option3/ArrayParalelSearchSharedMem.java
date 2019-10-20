package Option3;

import java.util.ArrayList;

public class ArrayParalelSearchSharedMem {
    private static int aBuscar;
    private static int[] Array;
    private static int NumThreads;
    private static ArrayList<int[]> fragments;

    public ArrayParalelSearchSharedMem(int aBuscar, int[] Array, int NumThreads) {
        ArrayParalelSearchSharedMem.aBuscar = aBuscar;
        ArrayParalelSearchSharedMem.Array = Array;
        ArrayParalelSearchSharedMem.NumThreads = NumThreads;
    }

    public static int cercaParallela() {
        if (NumThreads > Array.length) {
            System.out.println("Error. El nombre de threads no pot ser major que la mida del array.");
            System.exit(0);
        } else {
            int fragment = Array.length / NumThreads;
            for (int i = 0; i < Array.length; i+=NumThreads){
                partialSearch();
            }
        }
    }

    private synchronized int partialSearch(int start, int end){

    }
}
