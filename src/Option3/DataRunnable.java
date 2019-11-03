package Option3;

public class DataRunnable implements Runnable {
    private int aBuscar;
    private int[] Array;
    private int startIndex;
    private int endIndex;

    public DataRunnable(int aBuscar, int[] Array, int startIndex, int endIndex){
        this.aBuscar = aBuscar;
        this.Array = Array;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public void run() {
        //System.out.println("Inicio thread amb el index d'inici " + startIndex + " i amb el index de fi " + endIndex + ". Soc el thread " + Thread.currentThread().getId() + "/" + Thread.currentThread().getName());
        for (int i = startIndex; i < endIndex; i++){
            if (Array[i] == aBuscar){
                System.out.println("He trobat el numero buscat en el thread " + Thread.currentThread().getName() + " a la posicio " + i);
            }
        }
    }
}
