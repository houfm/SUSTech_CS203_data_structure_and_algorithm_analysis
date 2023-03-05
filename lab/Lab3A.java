public class Lab3A {
    public static void main(String[] args) {
        // be careful: 0 is not needed to be output
        int n, m;
        QReader in = new QReader();
        QWriter out = new QWriter();
        n = in.nextInt();
        m = in.nextInt();

        // to link a linked list : create an empty head first
        node3a head = new node3a(-1000000001, -1000000001);
        // now head is null, you cannot find head.next as it is null
        // so we use = new....
        node3a cur = head;
        for (int i = 0; i < n; i++) {
            node3a tmp = new node3a(in.nextInt(), in.nextInt());
            cur.next = tmp;
            cur = cur.next;
            // if only use this statement, then only the final node is linked
//            head = head.next;
            // then we cannot find head
            // we create a cur
        }

        node3a tail = new node3a(1000000001, 1000000001);
        cur.next = tail;
        cur = head;
        // bianli: iterate through
//        while (cur.next!= null){
//            cur = cur.next;
//        }

        for (int i = 0; i < m; i++) {
            node3a tmp = new node3a(in.nextInt(), in.nextInt());
            // the first one has 3 circumstance
            // to reduce if, use a fake head
            while (true) {
                // use true if you do not want to think about the end condition
                // use of tail
                if (tmp.exp < cur.next.exp) break;
                cur = cur.next;
            }
            if (cur.exp == tmp.exp) {
                cur.coe += tmp.coe;
            }else {
                // insert
                tmp.next = cur.next;
                cur.next = tmp;
            }
        }

        int cnt = 0;
        cur = head.next;
        // from real head
        while (cur != tail){
            if (cur.coe !=0 ){
                cnt++;
            }
            cur = cur.next;
        }
        out.println(cnt);
        cur = head.next;
        while (cur != tail){
            if (cur.coe !=0 ){
                out.println(cur.coe + " " + cur.exp);
            }
            cur = cur.next;
        }
        out.close();
    }
}

class node3a {
    int coe;
    int exp;
    node3a next;

    public node3a(int coe, int exp) {
        this.coe = coe;
        this.exp = exp;
    }
}



