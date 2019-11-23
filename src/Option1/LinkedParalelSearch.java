package Option1;

import java.util.LinkedList;
import java.util.Random;

public class LinkedParalelSearch implements Runnable{
    private int searchedNum;
    private int size;
    private LinkedList<Integer> list;
    private int count;
    //private boolean frontSearch;

    public LinkedParalelSearch(int size, int searchedNum){
        this.searchedNum = searchedNum;
        this.size = size;

        list = new LinkedList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++){
            list.add(random.nextInt(10));
            System.out.print(list.get(i) + ", ");
        }

        //frontSearch = true;
        Thread t1 = new Thread(this);
        t1.start();

        //frontSearch = false;
        Thread t2 = new Thread(this);
        t2.start();
    }

    @Override
    public void run() {
        if (Thread.currentThread().getName().endsWith("0")){
            for (int i = 0; i < size/2; i++){
                System.out.println("[FRONT] miro index " + i + " valor " + list.get(i));
                if (list.get(i) == searchedNum){
                    end(true, i);
                }
            }
        }else {
            for (int i = size - 1; i >= size/2; i--){
                System.out.println("[BACK] miro index " + i + " valor " + list.get(i));
                if (list.get(i) == searchedNum){
                    end(false, i);
                }
            }
        }
    }

    private synchronized void end(boolean cas, int i){
        if (cas){
            System.out.println("\nSoc el thread FRONT i he trobat el numero 4 a la casella " + i);
            System.exit(0);
        }else {
            System.out.println("Soc el thread BACK i he trobat el numero 4 a la casella " + i);
            System.exit(0);
        }
    }
}
