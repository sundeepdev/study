/*  Implement an algorithm to determine if a string has all unique
    characters. What if you can not use additional data structures?
*/

class problem14 {
    //return true it has all unique characters;
    //otherwise return false
    public static boolean testString1(String thisString) {
        //It takes 256 bytes to storage
        boolean[] char_set = new boolean[256];

        for (int i = 0; i < thisString.length(); i++) {
            if (char_set[thisString.charAt(i)] == true) {
                return false;
            } else {
                char_set[thisString.charAt(i)] = true;
            }
        }

        return true;
    }

    //Use bit vector to save more storagen space. It uses
    //a 32-bit integer as a bit-wise checker. 
    public static boolean testString2(String thisString) {
        int checker = 0;
        for (int i = 0; i < thisString.length(); i++) {
            int value = thisString.charAt(i) - 'a';
            if ((checker & (1<<value)) > 0) {
                return false;
            }
        
            checker |= (1<<value);
        }

        return true;
    }

    public static void main(String[] args) {
        boolean result = testString2("abcd");
        System.out.println(result);
        return;
    }
}
