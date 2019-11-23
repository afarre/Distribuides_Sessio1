package Option4;

import java.util.Arrays;

public class RecursiveSortThread extends Thread {
    private int[] array;
    private RecursiveSortThread parent;
    private RecursiveSortThread firstSon;
    private RecursiveSortThread secondSon;


    public RecursiveSortThread(int[] array, RecursiveSortThread parent) {
        this.array = array;
        this.parent = parent;
    }

    @Override
    public void run() {
        System.out.println("Run del " + Thread.currentThread().getName() + " amb el array " + Arrays.toString(array));
        if (array.length > 1){

            int[] subArray1 = null;
            int[] subArray2 = null;

            if (array.length % 2 == 0){
                //System.out.println("even");
                subArray1 = new int[array.length/2];
                subArray2 = new int [array.length/2];

                System.arraycopy(array, 0, subArray1, 0, array.length/2);
                System.arraycopy(array, array.length/2, subArray2, 0, array.length/2);
            }else {
                //System.out.println("odd");
                subArray1 = new int[array.length/2];
                subArray2 = new int [(array.length/2) + 1];

                System.arraycopy(array, 0, subArray1, 0, array.length/2);
                System.arraycopy(array, array.length/2, subArray2, 0, (array.length/2) + 1);
            }

            firstSon = new RecursiveSortThread(subArray1, this);
            firstSon.start();
            secondSon = new RecursiveSortThread(subArray2, this);
            secondSon.start();

            try {
                firstSon.join();
                secondSon.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sortChilds();
        }
    }

    private void sortChilds() {
        // Agafem els arrays dels fills ja ordenats respectivament
        int[] auxArray1 = firstSon.getArray();
        int[] auxArray2 = secondSon.getArray();
        //System.out.println("\tAux arrays thread " + Thread.currentThread().getName() + "; First: " + Arrays.toString(auxArray1) + "; Second: " + Arrays.toString(auxArray2));

        int i = 0;
        int j = 0;
        boolean flag;
        for (int k = 0; k < array.length; k++){
            flag = true;
            while (i < auxArray1.length && j < auxArray2.length){
                flag = false;
                if (auxArray1[i] < auxArray2[j]){
                    array[k] = auxArray1[i];
                    i++;
                    break;
                }else if (auxArray1[i] > auxArray2[j]){
                    array[k] = auxArray2[j];
                    j++;
                    break;
                }else if (auxArray1[i] == auxArray2[j]){
                    array[k] = auxArray2[j];
                    array[++k] = auxArray1[i];
                    i++;
                    j++;
                    break;
                }
            }
            if (flag){
                if (i >= auxArray1.length){
                    array[k] = auxArray2[j];
                    j++;
                }else if (j >= auxArray2.length){
                    array[k] = auxArray1[i];
                    i++;
                }
            }
        }
       // System.out.println("End array status (" + Thread.currentThread().getName() + "): " + Arrays.toString(array));
    }

    public int[] getArray() {
        return array;
    }
}
