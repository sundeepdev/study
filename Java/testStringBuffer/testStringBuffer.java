import java.util.*;
import java.lang.*;

class testStringBuffer {

    public static String makeSentence(String[] words) {
        StringBuffer sentence = new StringBuffer();
        for (String w: words) sentence.append(w);
        return sentence.toString();
    }

    public static void main(String[] args) {
        String[] words = {"ABC", "aaa"};
        String result = makeSentence(words);
        
        System.out.println(result);
    }

}

