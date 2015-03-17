import java.util.*;
import java.lang.*;
import java.util.concurrent.atomic.AtomicInteger;

public class JavaAtomic {
    public static void main(String[] args) throws InterruptedException {
        ProcessingThread pt = new ProcessingThread();
        Thread t1 = new Thread(pt, "t1");
        t1.start();
        Thread t2 = new Thread(pt, "t2");
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Processing count=" + pt.getCount());
    }
}

class ProcessingThread implements Runnable {
    /*
        If you will run above program, you will notice that count value varies between 5,6,7,8
        . The reason is because count++ is not an atomic operation. So by the time one threads 
        read it’s value and increment it by one, other thread has read the older value leading 
        to wrong result.
    */
    //private int count;

    /*
        To solve this issue, we will have to make sure the increment operation on count is atomic, 
        we can do that using synchronization but Java 5 java.util.concurrent.atomic provides wrapper
        classes for int and long that can be used to achieve this atomically without usage of 
        synchronization.

        However, this class does extend Number to allow uniform access by tools and utilities that deal
        with numerically-based classes.
    */
    private AtomicInteger count = new AtomicInteger(); 
    
    @Override
    public void run() {
        for (int i = 1; i < 5; i++) {
            processingSomething(i);
            //count++;
            count.incrementAndGet();
            System.out.println("the thread is " + Thread.currentThread().getName() + ", count = " + getCount());
        }
    }

    public int getCount() {
        //return this.count;
        return this.count.get();
    }

    private void processingSomething(int i) {
        // processing some job
        try {
            System.out.println("thread " + Thread.currentThread().getName() + " starts sleeping...i = " + i);
            Thread.sleep(i * 1000);
            System.out.println("thread " + Thread.currentThread().getName() + " wakes up!"); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
