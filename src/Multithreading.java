import java.util.Scanner;

public class Multithreading {

    static int counter=0;
    static int N;

    synchronized  void printOdd() throws InterruptedException {
        while (counter<N){
            if (counter%2==0){
                wait();
            }
            System.out.println(counter);
            counter++;
            notifyAll();
        }

    }
    synchronized  void printEven() throws InterruptedException {
        while(counter<N){
            if (counter%2==1){
                wait();
            }
            System.out.println(counter);
            counter++;
            notify();
        }

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N= sc.nextInt();
        Multithreading mt = new Multithreading();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mt.printEven();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mt.printOdd();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }


}
