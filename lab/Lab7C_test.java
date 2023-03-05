import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Lab7C_test {
    public static void main(String[] args) {
        test();
    }
    static void test() {
        while(true){
            Random ram = new Random();
            int n = ram.nextInt(50000)+1;
            int[] a = new int[n];
            for (int j = 0; j < n; j++) {
                a[j] = ram.nextInt(10)+1;
            }
            int ans_test = Lab7C_test.original(n,a);
            int ans_right = Lab7C_test.right(n,a);
            if (ans_test != ans_right) {
                System.out.println("The n is:" + n);
                System.out.println(Arrays.toString(a));
                System.out.println("The wrong ans is: "+ans_test);
                System.out.println(ans_right);
                break;
            } else {
                System.out.println("right");
            }
        }
    }
    static int right(int n,int[] input){

        ArrayList<Integer> a = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            a.add(input[i]);
        }
        while(a.size()>1) {
            int min = a.get(0);
            int minIndex=0;
            for (int i = 0; i < a.size(); i++) {
                if (a.get(i) < min) {
                    minIndex = i;
                    min = a.get(i);
                }
            }
            int merge;
            if (minIndex==0){
                merge =(a.get(minIndex)^a.get(minIndex+1))+1;
                a.remove(minIndex);
                a.remove(minIndex);
                a.add(0,merge);
            } else if (minIndex == a.size() - 1) {
                merge = (a.get(minIndex)^a.get(minIndex-1))+1;
                a.remove(minIndex-1);
                a.remove(minIndex-1);
                a.add(minIndex-1,merge);
            } else{
                if (Objects.equals(a.get(minIndex + 1), a.get(minIndex - 1))) {
                    merge = (a.get(minIndex)^a.get(minIndex-1))+1;
                    a.remove(minIndex-1);
                    a.remove(minIndex-1);
                    a.add(minIndex-1,merge);
                } else {
                    int maxIndex=minIndex-1;
                    if (a.get(maxIndex) < a.get(maxIndex+2)) {
                        maxIndex = maxIndex+2;
                    }
                    merge = (a.get(minIndex)^a.get(maxIndex))+1;
                    a.remove(Math.min(minIndex,maxIndex));
                    a.remove(Math.min(minIndex,maxIndex));
                    a.add(Math.min(minIndex,maxIndex),merge);
                }
            }
        }
        return a.get(0);
    }
    static int original(int n,int a[]){
        heap7C heap = new heap7C();
        node7C pre = null;
        for (int i = 0; i < n; i++) {
            node7C tmp = new node7C(a[i]);
            tmp.linkedListIndex = i;
            node7C.connect(pre, tmp);
            heap.insert(tmp);
            // Except static methods,
            // other methods in a class only can be accessed after an object of this class is created.
            pre = tmp;
        }

        node7C min = heap.heap[1];
        while (!(min.post == null && min.pre == null)) {
            heap.remove(1);
            node7C mergeResult = min.merge(heap);
            heap.insert(mergeResult);
            min = heap.heap[1];
            while (min.isShadow && heap.size > 1) {
                heap.remove(1);
                min = heap.heap[1];
            }
            if (min.isShadow) {
                break;
            }
        }

//        while (heap.size > 1) {
//            node7C min = heap.heap[1];
//            while(min.isShadow && heap.size > 1) {
//                heap.remove(1);
//                min = heap.heap[1];
//            }
//            if (heap.size == 1) {
//                break;
//            }
//            heap.remove(1);
//            node7C tmp = min.merge(heap);
//            heap.insert(tmp);
//
//        }
        return heap.heap[1].value;
    }
}
