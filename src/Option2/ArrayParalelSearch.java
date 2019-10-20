package Option2;

import java.util.ArrayList;

public class ArrayParalelSearch {
    private static int aBuscar;
    private static int[] Array;
    private static int NumThreads;
    private static ArrayList<int[]> fragments;

    public ArrayParalelSearch(int aBuscar, int[] Array, int NumThreads) {
        ArrayParalelSearch.aBuscar = aBuscar;
        ArrayParalelSearch.Array = Array;
        ArrayParalelSearch.NumThreads = NumThreads;
    }

    public static int cercaParallela() {
        if (NumThreads > Array.length){
            System.out.println("Error. El nombre de threads no pot ser major que la mida del array.");
            System.exit(0);
        }else {
            fragments = new ArrayList<>(NumThreads);
            int aux = 0;
            for (int j = 0; j < NumThreads; j++){
                int[] auxArray =  new int[Array.length / NumThreads];
                for (int i = 0; i < (Array.length / NumThreads); i++){
                    auxArray[i] = Array[aux];
                    aux++;
                }
                fragments.add(auxArray);
            }
        }

        ArrayList<ArrayParalelSearchThread> threads = new ArrayList<>();
        ArrayList<Integer> caselles = new ArrayList<>();
        for (int i = 0; i < fragments.size(); i++){
            threads.add(new ArrayParalelSearchThread(fragments.get(i), aBuscar));
            Thread thread = new Thread(threads.get(i));
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            caselles.add(threads.get(i).getCasella());
        }
        return -1;
    }
}
