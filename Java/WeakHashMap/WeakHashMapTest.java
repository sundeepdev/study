import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapTest {
    private static Map map;
    public static void main(String[] args) {
        map = new WeakHashMap();
        map.put(new String("Maine"), "Augusta");

        Runnable runner = new Runnable() {
            public void run() {
                while(map.containsKey("Maine")) {
                    try {
                        Thread.sleep(500);
                    } catch( InterruptedException ignored ) {
                    }
                    System.out.println("Thread waiting");
                    System.gc();
                }
            }
        };
        Thread t = new Thread(runner);
        System.out.println("Main waiting");
        t.start();
        try {
            t.join();
        } catch (InterruptedException ignored) {
        }
    }
}
