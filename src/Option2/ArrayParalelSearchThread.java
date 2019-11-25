package Option2;

import java.util.Arrays;

public class ArrayParalelSearchThread implements Runnable {
    private int[] fragments;
    private int aBuscar;
    private int casella;

    public ArrayParalelSearchThread(int[] fragments, int aBuscar) {
        this.fragments = fragments;
        this.aBuscar = aBuscar;
        casella = -1;
    }

    @Override
    public void run() {

        System.out.println("Soc el thread " + Thread.currentThread().getId() + " i he de buscar el nombre en el array:" + Arrays.toString(fragments));

        for (int i = 0; i < fragments.length; i++){
            if (fragments[i] == aBuscar){
                setCasella(i);
                System.out.println("Soc el thread " + Thread.currentThread().getId() + " i he trobat el nombre en la casella " + getCasella() + " del meu subarray.");
            }
        }
    }

    public int getCasella() {
        return casella;
    }

    private void setCasella(int casella) {
        this.casella = casella;
    }
}
