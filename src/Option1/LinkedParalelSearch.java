package Option1;

import java.util.LinkedList;
import java.util.Random;

public class LinkedParalelSearch implements Runnable{
    private int searchedNum;
    private int size;
    private LinkedList<Integer> list;
    private boolean flag;
    private int count;

    public LinkedParalelSearch(int size, int searchedNum){
        this.searchedNum = searchedNum;
        this.size = size;

        list = new LinkedList<Integer>();
        Random random = new Random();
        for (int i = 0; i < size; i++){
            list.add(random.nextInt(10));
            System.out.print(list.get(i) + ", ");
        }
        //list.set(size - 1, 4);
        System.out.println(list.get(size - 1));

        Thread t1 = new Thread(this);
        Thread t2 = new Thread(this);
        t1.start();
        t2.start();
    }

    private synchronized void end(int cas, int i){
        switch (cas) {
            case 0:
                System.out.println("Soc el thread FRONT i he trobat el numero 4 a la casella " + i);
                System.exit(0);
                break;

            case 1:
                System.out.println("Soc el thread BACK i he trobat el numero 4 a la casella " + i);
                System.exit(0);
                break;

            case 2:
                System.out.println("No existeix el numero buscat en la LinkedList.");
                System.exit(0);
                break;
        }
    }

    private synchronized void setFlag(boolean flag){
        this.flag = flag;
    }

    private synchronized boolean getFlag(){
        return flag;
    }

    private synchronized void setCount(int count){
        this.count = count;
    }

    private synchronized int getCount(){
        return count;
    }

    @Override
    public void run() {
        while (true){
            setCount(getCount() + 1);
            if (Thread.currentThread().getName().endsWith("0")){
                //System.out.println("Entro thread FRONT");
                setFlag(!getFlag());
                for (int i = 0; i < size/2; i++){
                    if (list.get(i) == searchedNum){
                        end(0, i);
                    }
                }
            }else {
                //System.out.println("Entro thread BACK");
                for (int i = size - 1; i > size/2; i--){
                    if (list.get(i) == searchedNum){
                        end(1, i);
                    }
                }
            }

            if (getCount() > size){
                end(2, -1);
            }
        }
    }
}
