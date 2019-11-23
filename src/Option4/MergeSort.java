package Option4;

public class MergeSort {
    private RecursiveData data1;
    private RecursiveData data2;

    public void sortArray(int[] array) {
        int[] subArray1 = null;
        int[] subArray2 = null;

        if (array.length % 2 == 0){
            System.out.println("even");
            subArray1 = new int[array.length/2];
            subArray2 = new int [array.length/2];

            System.arraycopy(array, 0, subArray1, 0, array.length/2);
            System.arraycopy(array, array.length/2, subArray2, 0, array.length/2);
        }else {
            System.out.println("odd");
            subArray1 = new int[array.length/2];
            subArray2 = new int [(array.length/2) + 1];

            System.arraycopy(array, 0, subArray1, 0, array.length/2);
            System.arraycopy(array, array.length/2, subArray2, 0, (array.length/2) + 1);
        }

        System.out.println();
        for (int i = 0; i < subArray1.length; i++){
            System.out.print(subArray1[i] + ", ");
        }
        System.out.println();

        for (int i = 0; i < subArray2.length; i++){
            System.out.print(subArray2[i] + ", ");
        }

        RecursiveData data1 = new RecursiveData(subArray1, this);
        Thread thread1 = new Thread(data1);
        thread1.start();

        RecursiveData data2 = new RecursiveData(subArray2, this);
        Thread thread2 = new Thread(data2);
        thread2.start();

    }

    public void sortChilds() {
        int[] auxArray1 = data1.getArray();
        int[] auxArray2 = data1.getArray();
        if (auxArray1[0] < auxArray2 [0]){

        }
    }
}
