package com.example.collections;

import com.sun.source.tree.Tree;

import java.sql.Array;
import java.util.*;

public class SetExample {
    public static void main(String[] args) {

        Person p1 = new Person(19, "Z");
        Person p2 = new Person(21, "A");
//
//        if(p1.equals(p2)) {
//            System.out.println("YES");
//        } else {
//            System.out.println("NO");
//        }
//
//        HashSet<Person> set = new HashSet<>();
//
//        set.add(p1);
//        set.add(p2);
//
//        System.out.println(set);

//        LinkedHashSet<Integer> set = new LinkedHashSet<>();
//
//        set.add(1);
//        set.add(5);
//        set.add(10);
//        set.add(1);
//        set.add(2);
//        set.add(1000);
//
//        Iterator it = set.iterator();
//        while(it.hasNext()) {
//            System.out.println(it.next());
//        }

//        TreeSet<Person> set = new TreeSet<>(new NameComparator());
//
////        p1.compareTo(p2);
//
//        set.add(p1);
//        set.add(p2);


//        TreeSet<Integer> set = new TreeSet<>();
//
//        set.add(5);
//        set.add(1);
//        set.add(4);
//        set.add(3);
//
//        System.out.println(set.first());
//        System.out.println(set.last());
//        set.pollFirst();
//        set.pollLast();
//        System.out.println(set);

//
//        System.out.println(set);

//        ArrayList<Person> arr = new ArrayList<>();
//        arr.add(p1);
//        arr.add(p2);
//
//        Collections.sort(arr, new NameComparator());
//
//        System.out.println(arr);

//        HashMap<Integer, String> map = new HashMap<>();
//
//        map.put(1, "ABC");
//        map.put(2, "XYZ");
//        map.put(3, "PQR");
//
//        System.out.println(map.get(2));
//
//        System.out.println(map.containsKey(1));
//        System.out.println(map.containsKey(5));
//
//        map.remove(2);
//
//        System.out.println(map.keySet());
//        System.out.println(map.values());

        PriorityQueue<Integer> pq = new PriorityQueue<>((s1, s2) -> {
            return s1-s2;
        });

        pq.add(1);
        pq.add(3);
        pq.add(2);

        Iterator it = pq.iterator();

//        while(it.hasNext()) {
//            System.out.println(it.next());
//        }

        while(!pq.isEmpty()) {
            System.out.println(pq.poll());
        }

    }
}

class PQComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return 0;
    }
}

class NameComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return o1.name.compareTo(o2.name);
    }
}

class AmountComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        if(o1.amount < o2.amount) {return -1;}
        return 1;
    }
}