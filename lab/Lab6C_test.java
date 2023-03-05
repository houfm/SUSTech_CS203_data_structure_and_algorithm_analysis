import java.util.Random;

public class Lab6C_test {
    public static void main(String[] args) {

            test();

    }

    static void test(){
        Random rand = new Random();
        int bound  = 200;
        int n = rand.nextInt(bound)+2;
        System.out.println(n);
        int to1 = rand.nextInt(n)+2;
        // the number connect to 1
        int[][] connect ;
        connect=generateRandomTree(n);
        int m = rand.nextInt(bound*2)+1;
        System.out.println(m);
        for (int i = 0; i < m; i++) {
            int sequence = rand.nextInt(2);
            int choose_connect = rand.nextInt(n-1);
            int na = rand.nextInt(2*n)+1;
            if (sequence == 0) {
                System.out.println(connect[0][choose_connect]+" "+connect[1][choose_connect]+" "+na);
            } else {
                System.out.println(connect[1][choose_connect]+" "+connect[0][choose_connect]+" "+na);
            }
        }
    }

    // Prints edges of tree
    // represented by give Prufer code
    static int[][] printTreeEdges(int prufer[], int m,int n)
    {
        int vertices = m + 2;
        int vertex_set[] = new int[vertices];
        int[][] connect = new int[2][n-1];
        int cnt=0;
        // Initialize the array of vertices
        for (int i = 0; i < vertices; i++)
            vertex_set[i] = 0;

        // Number of occurrences of vertex in code
        for (int i = 0; i < vertices - 2; i++)
            vertex_set[prufer[i] - 1] += 1;

//            System.out.print("\nThe edge set E(G) is:\n");

        int j = 0;

        // Find the smallest label not present in
        // prufer[].
        for (int i = 0; i < vertices - 2; i++) {
            for (j = 0; j < vertices; j++) {

                // If j+1 is not present in prufer set
                if (vertex_set[j] == 0) {

                    // Remove from Prufer set and print
                    // pair.
                    vertex_set[j] = -1;
                    System.out.println((j + 1) + " "
                            + prufer[i] );
                    connect[0][cnt] = j+1;
                    connect[1][cnt] = i;
                    cnt++;
                    vertex_set[prufer[i] - 1]--;

                    break;
                }
            }
        }

        j = 0;
        // For the last element
        for (int i = 0; i < vertices; i++) {
            if (vertex_set[i] == 0 && j == 0) {

                System.out.print((i + 1) + " ");
                connect[0][cnt] = i+1;
                j++;
            }
            else if (vertex_set[i] == 0 && j == 1){
                System.out.print((i + 1) + "\n");
                connect[1][cnt] = i+1;


            }

        }
        return connect;
    }

    // Function to Generate Random Tree
    static int[][] generateRandomTree(int n)
    {

        Random rand = new Random();
        int length = n - 2;
        int[] arr = new int[length];

        // Loop to Generate Random Array
        for (int i = 0; i < length; i++) {
            arr[i] = rand.nextInt(length + 1) + 1;
        }

        return printTreeEdges(arr, length,n);
    }

    // Driver Code

}
    // Java Implementation for random
// tree generator using Prufer Sequence




