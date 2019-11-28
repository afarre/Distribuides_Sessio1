package Option3;

import java.util.ArrayList;

public class ArrayParalelSearchSharedMem {
    private static int aBuscar;
    private static int[] Array;
    private static int NumThreads;
    private static int fragment;
    private static int start;
    private static int end;

    public ArrayParalelSearchSharedMem(int aBuscar, int[] Array, int NumThreads) {
        ArrayParalelSearchSharedMem.aBuscar = aBuscar;
        ArrayParalelSearchSharedMem.Array = Array;
        ArrayParalelSearchSharedMem.NumThreads = NumThreads;
        fragment = Array.length / NumThreads;
        start = 0;
        end = fragment;
    }

    public int cercaParallela() {
        long startTime = System.nanoTime();
        ArrayList<Thread> threadArrayList = new ArrayList<>();
        if (NumThreads > Array.length) {
            System.out.println("Error. El nombre de threads no pot ser major que la mida del array.");
            System.exit(0);
        } else {
            int residu = Array.length % NumThreads - 1;
            for (int i = 0; i < NumThreads; i++){
                if (residu >= 0){
                    //System.out.println("Inicio cerca del index " + start + " al index " + end);
                    DataRunnable d1 = new DataRunnable(aBuscar, Array, start, ++end);
                    Thread thread = new Thread(d1);
                    threadArrayList.add(thread);
                    thread.start();
                    start = end;
                    end += fragment;
                    residu--;
                }else {
                    //System.out.println("Inicio cerca del index " + start + " al index " + end);
                    DataRunnable d1 = new DataRunnable(aBuscar, Array, start, end);
                    Thread thread = new Thread(d1);
                    threadArrayList.add(thread);
                    thread.start();
                    start = end;
                    end += fragment;
                }
            }
        }

        //bucle d'espera dels threads per a comptar el temps d'execucio
        for (Thread thread: threadArrayList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.nanoTime();
        System.out.println("Time taken (shared mem): " + (endTime - startTime)/1000000 + "ms");
        return -1;
    }
}
