package Option3;

import java.util.ArrayList;

public class ArrayParalelSearchSharedMem implements Runnable{
    private static int aBuscar;
    private static int[] Array;
    private static int NumThreads;
    private static ArrayList<int[]> fragments;
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
                //TODO: o trobar una forma dinamica de iniciar els threads amb el seu index corresponent de inici i fi abans de que es canviin, o be guardar els index en un array i despres recorrer el array iniciant threads amb aquests indexs
                int value = partialSearch();
                setStart(getEnd());
                setEnd(fragment + getEnd());
                if (value > 0){
                    return value;
                }
            }
        }
        return -1;
    }

    private synchronized int partialSearch(){
        System.out.println("Inicio cerca del index " + getStart() + " al index " + getEnd());
        Thread thread = new Thread(this);
        thread.start();

        return -1;
    }

    @Override
    public void run() {
        System.out.println("\nSoc el thread " + Thread.currentThread().getId() + " i els meus index van de " + getStart() + " a " + getEnd());
    }

    public synchronized int getStart() {
        return start;
    }

    public synchronized void setStart(int start) {
        this.start = start;
    }

    public synchronized int getEnd() {
        return end;
    }

    public synchronized void setEnd(int end) {
        this.end = end;
    }
}
