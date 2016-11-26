package lesson07;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * denis
 * 27.11.2016.
 */
public class MailSum {
    static int syncSum;
    static volatile int sum; //volatile Тип переменной для многопоточных приложений
    static final AtomicInteger ATOMIC_SUM = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10000; i++) {

            new Thread() {

                @Override
                public void run() {
                    sum++;
                    ATOMIC_SUM.incrementAndGet();
                    synchronized(this) {
                        syncSum++;
                    }
                }
            }.start();


        }



        Thread.sleep(500);

        System.out.println(sum);
        System.out.println(ATOMIC_SUM.get());
        System.out.println(syncSum);


    }


}
