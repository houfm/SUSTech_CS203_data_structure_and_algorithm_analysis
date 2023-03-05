public class Lab3D {
    public static void main(String[] args) {
        // if disjoint, it will make it difficult
        // is a 2-d swap
        // 1-d swap: exchange the first and end of the 2 segments
        // 2-d swap: use n linked list (better than use 1 linked list, it is easier to understand
        // to exchange two cubic, we need to exchange every sub line
        // qnm -> q(n+m)
        // not okay to use an array to implement o(1) search
        // close to 1oo billion times: but we have 2 s
        // _|
        // only need to change the edges, not need to consider the inner links
        // write a function swap
        // head out, left out, right in, feet in
        // not need to use two-direction needle

        // use others' exe to prevet fan bian yi

        QWriter out  = new QWriter();
        QReader in = new QReader();
        long n = in.nextLong();
        int m=in.nextInt();
        int q = in.nextInt();
        // n rows, m columns, swap q times

        int[] upA = new int[m];
        node3d[] upNode = new node3d[m];
        for (int i = 0; i < m; i++) {
            upA[m] = in.nextInt();
        }
        for (int i = m-1; i >= 0; i--) {
            upNode[i].a = upA[i];
            if (i != m-1){
                upNode[i].right = upNode[i+1];
            }
        }
    }
}
class node3d {
    int a;
    node3d right;
    node3d down;

    public node3d(int a) {
        this.a = a;
        right = null;
        down = null;
    }
}
