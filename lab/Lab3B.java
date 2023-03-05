import java.io.*;
import java.util.StringTokenizer;

public class Lab3B {
    public static void main(String[] args) {
        int n;
        long m;
        QWriter out = new QWriter();
        QReader in = new QReader();

        n = in.nextInt();
        m = in.nextLong();
        long sum =0;
        // ？？？？？？？？？？
        // m is money
        node3b head = new node3b(in.nextInt());
        sum += head.price;
        // only the "new" statement create an object that is reserved in the heap memory
        // every variable in Java is reference types, which is used to point object/value
        node3b cur = head;
        for (int i = 0; i < n - 1; i++) {
            // head is a shop as well
            cur.next = new node3b(in.nextInt());
            cur = cur.next;
            sum += cur.price;
        }
        cur.next = head;
        node3b last = cur;
        // make the linked list a circle

        long cnt =0;
        cnt += n*(m/sum);
        // different from n*m/sum
        // eg. 6*12/9
        m = m%sum;
        // above statements are used to prevent time limit exceed


        node3b now = head;
        while (now != null) {
            // not m>0
            if (m >= now.price) {
                m -= now.price;
                ++cnt;
                now = now.next;
                last = last.next;
            } else {
                last.next = now.next;
                node3b tmp = now;
                now = now.next;
                tmp.next = null;
                // do not need to let last = last.next
            }
        }
        out.println(cnt);
        out.close();
    }
    // 9 15
    //19 2 1 3 1 1 45 1 2

}

//public class Lab3B {
//    public static void main(String[] args) {
//        int n;
//        long m;
//        QWriter out = new QWriter();
//        QReader in = new QReader();
//        n = in.nextInt();
//        m = (long) in.nextInt();
//        // m is money
//        node3b head = new node3b(in.nextInt());
//        // only the "new" statement create an object that is reserved in the heap memory
//        // every variable except the primitive data types in Java is reference types, which is used to point object/value
//        node3b cur = head;
//        for (int i = 0; i < n - 1; i++) {
//            // head is a shop as well
//            cur.next = new node3b(in.nextInt());
//            cur = cur.next;
//        }
//        cur.next = head;
//        // make the linked list a circle
//        cur = head;
//        long cnt = 0;
//        while (cur != null) {
////            // >= not >
////            // ???? cur == null
////            // should be cur != null
////            // if cur==null, the loop is impossible to run
////            if (m < cur.price){
////                while (m < cur.next.price){
////                    // ALL the store which price is not affordable should be removed.
////                    // not just the next one
////                    // so this place should be 'while' instead of 'if'
//////                    cur.next = cur.next.next;
//////                    cur.next = null;
////                    // these 2 statement is useless
////                    // it is equal to cur.next = null
////                    cur.next = cur.next.next;
////
////                    cur = cur.next;
////                }
////            } else {
////                m-=cur.price;
////                ++cnt;
////                // if the product is not affordable, just delete the node from the linked list
////                cur = cur.next;
////            }
//            if (m >= cur.price) {
//                m -= cur.price;
//                cur = cur.next;
//            } else {
//                while (m < cur.price) {
//
//                }
//            }
//        }
//        out.println(cnt);
//        out.close();
//    }
//}

class node3b {
    int price;
    node3b next;

    public node3b(int price) {
        this.price = price;
    }
}





