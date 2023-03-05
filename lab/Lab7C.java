import java.io.*;
import java.util.StringTokenizer;

public class Lab7C {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        heap7C heap = new heap7C();
        node7C pre = null;
        for (int i = 0; i < n; i++) {
            node7C tmp = new node7C(in.nextInt());
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
        out.println(heap.heap[1].value);
        out.close();
    }

}

class node7C {
    int value;
    // The range of int in Java is [-2^31,2^31].
    // And it is guaranteed that the pile contains less than 2^31 carrots.
    node7C pre;
    node7C post;
    int heapIndex;
    // The variable stores the index of the node in the array of heap,
    // indicating its position in the heap.
    int linkedListIndex;
    // From left to right, the index gets bigger.
    // The parameter will be used to determine its position in the heap.
    boolean isShadow;

    public node7C(int value) {
        // Create a node in a linked list
        this.value = value;
    }

    void changeIndex(int index) {
        heapIndex = index;
    }

    static void connect(node7C node1, node7C node2) {
        // This method do NOt considered the situations both nodes are null.
        if (node1 == null) {
            node2.pre = null;
        } else if (node2 == null) {
            node1.post = null;
        } else {
            node1.post = node2;
            node2.pre = node1;
        }
    }

    node7C merge(heap7C heap) {
        // the linked list: pre.pre - pre - this - post - post.post
        int maxValue;
        int adjacentHeapIndex = -1;
        node7C merge;
        if (pre == null) {
            // merge this and post
            adjacentHeapIndex = post.heapIndex;
            maxValue = post.value;
            merge = new node7C((this.value ^ maxValue) + 1);
            connect(merge, post.post);
            connect(pre, merge);
        } else if (post == null) {
            // merge this and pre
            adjacentHeapIndex = pre.heapIndex;
            maxValue = pre.value;
            merge = new node7C((this.value ^ maxValue) + 1);
            connect(pre.pre, merge);
            connect(merge, post);
        } else {
            int maxValue1 = (pre.value^this.value)+1;
            int maxValue2 = (post.value^this.value)+1;
            if (maxValue1 == maxValue2) {
                // In this case, merge with the previous(left) node.
                adjacentHeapIndex = pre.heapIndex;
                maxValue = pre.value;
                merge = new node7C((this.value ^ maxValue) + 1);
                connect(pre.pre, merge);
                connect(merge, post);
            } else if (maxValue1 > maxValue2) {
                // merge this and pre
                adjacentHeapIndex = pre.heapIndex;
                maxValue = pre.value;
                merge = new node7C((this.value ^ maxValue) + 1);
                connect(pre.pre, merge);
                connect(merge, post);
            } else {
                // merge this and post
                adjacentHeapIndex = post.heapIndex;
                maxValue = post.value;
                merge = new node7C((this.value ^ maxValue) + 1);
                connect(merge, post.post);
                connect(pre, merge);
            }
        }
        merge.linkedListIndex = this.linkedListIndex;
        heap.remove(adjacentHeapIndex);
        return merge;
    }


}

class heap7C {
    // It is a small top heap.
    node7C[] heap = new node7C[5000000];
    int size;
    // The index of the top node is 1.


    public void insert(node7C node) {
        // If multiple piles contain the least number of carrots, then the left-most such pile is chosen.
        // So in the insert method, there are TWO parameters of each node to be compared.
        ++size;
        heap[size] = node;
        node.heapIndex = size;
        int cur = size;
        while (cur > 1 && heap[cur].value < heap[cur / 2].value) {
            exchangeNode(this.heap, cur, cur / 2);
            cur = cur / 2;
        }
        // The below loop maintain the rule to keep node with smaller linked list index on the top
        while (cur > 1 && heap[cur].value == heap[cur / 2].value
                && heap[cur].linkedListIndex < heap[cur / 2].linkedListIndex) {
            exchangeNode(this.heap, cur, cur / 2);
            cur = cur / 2;
        }
    }

    public void remove(int top) {
        // top is the index of the node in heap to be removed.
        if (top == 1) {
            heap[top] = heap[size];
            --size;
            // First assign heap[top], THEN --size.
            // Be careful about the sequence.
            int cur = top;
            int minChildIndex;
            while (cur * 2 <= size) {
                minChildIndex = cur * 2;
                if ((minChildIndex + 1 <= size &&
                        heap[minChildIndex].value > heap[minChildIndex + 1].value)
                || (minChildIndex + 1 <= size &&
                        heap[minChildIndex].value == heap[minChildIndex + 1].value &&
                        heap[minChildIndex].linkedListIndex > heap[minChildIndex + 1].linkedListIndex)) {
                    ++minChildIndex;
                }
                if ((heap[minChildIndex].value < heap[cur].value)||
                        (heap[minChildIndex].value==heap[cur].value && heap[minChildIndex].linkedListIndex<heap[cur].linkedListIndex)) {
                    exchangeNode(heap, cur, minChildIndex);
                    cur = minChildIndex;
                } else {
                    break;
                }
            }
            // Below codes are used to maintain the second constraint
//            int minLinkedListIndex;
//            while (cur * 2 <= size) {
//                if (cur * 2 + 1 <= size) {
//                    // have 2 edges.
//                    if (heap[cur * 2].value == heap[cur].value &&
//                            heap[cur * 2 + 1].value == heap[cur].value) {
//                        // The edges are both have same value with father.
//                        if (heap[cur * 2 + 1].linkedListIndex < heap[cur * 2].linkedListIndex) {
//                            minLinkedListIndex = cur * 2 + 1;
//                        } else {
//                            minLinkedListIndex = cur * 2;
//                        }
//                        if (heap[minLinkedListIndex].linkedListIndex < heap[cur].linkedListIndex) {
//                            exchangeNode(this.heap, cur, minLinkedListIndex);
//                            cur = minLinkedListIndex;
//                        } else {
//                            break;
//                        }
//                    } else if (heap[cur * 2].value == heap[cur].value) {
//                        minLinkedListIndex = cur * 2;
//                        if (heap[minLinkedListIndex].linkedListIndex < heap[cur].linkedListIndex) {
//                            exchangeNode(this.heap, cur, minLinkedListIndex);
//                            cur = minLinkedListIndex;
//                        } else {
//                            break;
//                        }
//                    } else if (heap[cur * 2 + 1].value == heap[cur].value) {
//                        minLinkedListIndex = cur * 2 + 1;
//                        if (heap[minLinkedListIndex].linkedListIndex < heap[cur].linkedListIndex) {
//                            exchangeNode(this.heap, cur, minLinkedListIndex);
//                            cur = minLinkedListIndex;
//                        } else {
//                            break;
//                        }
//                    } else {
//                        break;
//                    }
//                } else {
//                    // The cur has only one child.
//                    if (heap[cur].value == heap[cur * 2].value && heap[cur].linkedListIndex > heap[cur * 2].linkedListIndex) {
//                        minLinkedListIndex = cur * 2;
//                        exchangeNode(this.heap, cur, minLinkedListIndex);
//                        cur = minLinkedListIndex;
//                    } else {
//                        break;
//                    }
//                }
//            }
        }  else {
            heap[top].isShadow = true;
        }

    }

    static void exchangeNode(node7C[] heap, int preNode1Index, int preNode2Index) {
        // When calling the method, the value of reference variables, a copy of reference is passed to the argument.
        node7C tmp = heap[preNode1Index];
        heap[preNode1Index] = heap[preNode2Index];
        heap[preNode2Index] = tmp;
        heap[preNode1Index].heapIndex = preNode1Index;
        // Before executing the method, as the new reference of heap[preNode1Index] comes from heap[preNode2Index],
        // its heapIndex is still preNode2Index.
        heap[preNode2Index].heapIndex = preNode2Index;
    }

}

