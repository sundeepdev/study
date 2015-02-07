/*

Problem:

You are given a series like this: 
(1,2) 
(2,3) 
(5,6) 
(2,9) 

Every element in this series is a pair(u,v) which means that there is a connection between (u,v). 

Output group of elements: 
For instance, if you look at the above series, the output will be: 
[1,2,3,9], [5,6]

My thought:

1.  Create an array:

|----|----|----|---|---|
| 2  |  6 |    |...|   |
|----|----|----|---|---|

list: set1<integer>, set2<integer>...

insert(list<set> l, <i1, i2>)

foreach set in l
  if i1 belongs to set
    add i2 to set
    return
puts i1 i2 into a new set and add that set into l

Time complexity:  size of the list, the worst case is n (that measn no grounping occurs all the time)
Space compiexity: O(n), n is the total number of elements


*/

import java.lang.*;
import java.util.*;

class Turple {
    private Integer item1;
    private Integer item2;

    public void setItem1(int item1) {
        this.item1 = item1;
    }

    public void setItem2(int item2) {
        this.item2 = item2;
    }

    public Integer getItem1() {
        return item1;
    }

    public Integer getItem2() {
        return item2;
    }

    public Turple(int item1, int item2) {
        this.item1 = item1;
        this.item2 = item2;
    }
}

public class Series {
    private ArrayList<Set<Integer>> list;
    public Series() {
        list = null;
    }

    public void insert(Turple t) {
        if (list == null) {
            list = new ArrayList<Set<Integer>>();
            Set<Integer> set = new HashSet<Integer>();
            set.add(t.getItem1());
            set.add(t.getItem2());
            list.add(set);
        } else {
            //iterate through the list
            for (Set<Integer> set : list) {
                if (set.contains(t.getItem1())) {
                    set.add(t.getItem2());
                    return;
                }
            }

            Set<Integer> set = new HashSet<Integer>();
            set.add(t.getItem1());
            set.add(t.getItem2());
            list.add(set);
        }
    }

    public void print() {
        
        for (Set<Integer> set : list) {
            System.out.print("[");
            for (Integer item : set) {
                System.out.print(item + " ");
            }    
            System.out.println("]");
        }
    }

    public static void main(String[] args) {
        //Create some tables
        Turple t1 = new Turple(1, 2);
        Turple t2 = new Turple(2, 3);
        Turple t3 = new Turple(5, 6);
        Turple t4 = new Turple(2, 9);

        //Create a series
        Series s = new Series();
        s.insert(t1);
        s.insert(t2);
        s.insert(t3);
        s.insert(t4);

        s.print();
    }
}
