import java.util.Random;
import java.util.Date;
import java.util.Calendar;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

class FileUtils {
    public static BufferedWriter getWrittenFile(String filePath) {

        BufferedWriter bw = null;
        
        try {
        
            bw = new BufferedWriter(new FileWriter(filePath));
            return bw;
        } catch (IOException e) {
            e.printStackTrace();
            return bw;
        }
    }

    public static boolean writeWord(String buffer, BufferedWriter bufferedWriter) {
        if (bufferedWriter == null || buffer == null) {
            return false;
        }

        try {
           bufferedWriter.write(buffer);
           bufferedWriter.write("\n");

        } catch (IOException e) {
            
            e.printStackTrace();
        }

        return true;
    }

    public static BufferedReader getReadFile(String filePath) {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filePath));
            return br;
        } catch (IOException e) {
            e.printStackTrace();
            return br;
        }
    }
}

class TimeUtils {
    public static long getCurrentTime() {
        Date date = new Date();
        return date.getTime();
    }

    public static void takeTime(long t1, long t2) {
        if (t1 > t2) {
            System.out.println("t2 must >= t1");
        } else {
            long diff = t2 - t1; //diff in milliseconds

            long seconds = (diff / 1000) % 60 ;
            long minutes = ((diff / (1000*60)) % 60);
            long hours   = ((diff / (1000*60*60)) % 24);
            long milliseconds = (diff % 1000);

            System.out.println("It takes: " + hours + " hours " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds");
        }
    }
}

public class RemoveRepetition {
    public static void produceWord(int sum) {
        char[] c = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                    'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                    'u', 'v', 'w', 'x', 'y', 'z'};

        Random r = new Random();
        int length = 0;
        int count = 0;
        
        StringBuffer sb = new StringBuffer("");
        boolean result;

        BufferedWriter bWriter = FileUtils.getWrittenFile("word.txt");
        long t1 = TimeUtils.getCurrentTime();
        
        try {
            while(count<sum) {
                length = r.nextInt(10) + 1;
                for (int i = 0; i < length; i++) {
                    sb.append(c[r.nextInt(26)]);
                }

                result = FileUtils.writeWord(new String(sb), bWriter);
                
                if (result == false) {
                    System.out.println("System error: cannot write buffer!");
                    break;
                }
                
                sb.delete(0, sb.length());
                count++;
            }

            long t2 = TimeUtils.getCurrentTime();
            TimeUtils.takeTime(t1, t2);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bWriter != null) {
                    bWriter.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();       
            }
        }
    }

    public static void main(String[] args) {
        
        
        int num = 10000000;
        System.out.println("It takes " +  "to generate " + num " character strings with size from 1 to 10" );
        produceWord(10000000);
        
    }
}
