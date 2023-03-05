import java.util.Scanner;

public class Lab4B {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        queue4b q = new queue4b();
        char input;
        int x;
        for (int i = 0; i < n; i++) {
            input = in.next().charAt(0);
            x = in.nextInt();
           // x = str.charAt(2)-'0';
            // ？？？？？？？
            switch (input) {
                case 'D':
                    System.out.println(q.pop(x)); break;
                case 'E': q.add(x); break;
                // break!!!!!!
            }
        }

    }

}

class queue4b {
    int max=200000;
    int[] a = new int[max];
    // not forget to store the information about the size of the queue
    // but in this exercise it is useless
    int front;
    int rear;

    public queue4b() {
        front = 0;
        rear = 0;
    }

    void add(int n) {
        a[rear] = n;
        if (rear + 1 <max){
            rear++;
        } else {
            rear = 0;
        }

    }

    int pop(int n) {
        if (front +n <max){
            front+=n;
        } else {
            front = n-(max-1-front) -1;
        }
        return a[front];
        // return is not output
    }
}


