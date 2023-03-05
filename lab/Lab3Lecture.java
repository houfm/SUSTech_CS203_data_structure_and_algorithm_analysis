public class Lab3Lecture {
    public static void main(String[] args) {
        int[] aa = new int[5];
        System.out.println(aa);
        // output: [I@372f7a8d
        //int[] a :
        // new: fenpei kongjian
        node_test a = new node_test();
        node_test b = new node_test();
        a.next = b;
        b = null;
        node_test c = new node_test();

        //if we want to insert c between a and b
        c.next = a.next;
        a.next = c;
        c = null;

        // now a and c both linked to
        // c become not in the linked list
        a.next = a.next.next;
    }
}

class node_test {
    int val;
    node_test next;
}
