package Option3;

import java.lang.reflect.Array;
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
        if (NumThreads > Array.length) {
            System.out.println("Error. El nombre de threads no pot ser major que la mida del array.");
            System.exit(0);
        } else {
            for (int i = 0; i <= fragment; i+=NumThreads){
                System.out.println("Inicio cerca del index " + start + " al index " + end);

                DataRunnable d1 = new DataRunnable(aBuscar, Array, start, end);
                new Thread(d1).start();
                start = end;
                end += fragment;
            }
        }
        return -1;
    }
}
