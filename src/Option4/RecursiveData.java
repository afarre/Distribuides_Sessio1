package Option4;

public class RecursiveData implements Runnable {
    private static int[] array;
    private MergeSort parent;

    public RecursiveData(int[] array, MergeSort parent){
        RecursiveData.array = array;
        this.parent = parent;
    }

    @Override
    public void run() {
       /*
       System.out.println("\nThread" + Thread.currentThread().getName());
        for (int i = 0; i < array.length; i++){
            System.out.print(array[i] + ", ");
        }
        System.out.println();
        */
       if (array.length >= 2){
           parent.sortArray(array);
       }else {
           parent.sortChilds();
       }
    }


    public int[] getArray() {
        return array;
    }
}
