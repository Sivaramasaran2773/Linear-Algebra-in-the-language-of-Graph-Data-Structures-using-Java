import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BellmanFord {

    public static int[] bellmanFord(int[][] adjMatrix, int source) {
        int n = adjMatrix.length;
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(source);

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v = 0; v < n; v++) {
                if (adjMatrix[u][v] != Integer.MAX_VALUE) {
                    if (dist[v] > dist[u] + adjMatrix[u][v]) {
                        dist[v] = dist[u] + adjMatrix[u][v];
                        if (!q.contains(v)) {
                            q.add(v);
                        }
                    }
                }
            }
        }

        return dist;
    }


        public static void displayMatrix(int[][] adjMatrix) {
            for (int i = 0; i < adjMatrix.length; i++) {
                for (int j = 0; j < adjMatrix[i].length; j++) {
                    int val = adjMatrix[i][j];
                    if (val == Integer.MAX_VALUE) {
                        System.out.print(Character.toString('\u221e') + "  ");
                    } else {
                        System.out.print(adjMatrix[i][j] + "  ");
                    }
                }
                System.out.println();
            }
        
    }

    public static void main(String[] args) {
        adjmatrix obj = new adjmatrix(3);
        obj.addEdge(0, 0, 0);
        obj.addEdge(0, 1, 10);
        obj.addEdge(0, 2, 5);
        obj.addEdge(1, 1, 0);
        obj.addEdge(1, 2, -8);
        obj.addEdge(2, 2, 0);


            System.out.println("Adjacency Matrix:");
            displayMatrix(obj.matrix);
            System.out.println();

                            
        int source = 0;
        int[] dist = bellmanFord(obj.matrix, source);
        System.out.println(" Shortest Distance from " + source +  " to 0: " + dist[0]);
        System.out.println(" Shortest Distance from " + source +  " to 1: " + dist[1]);
        System.out.println(" Shortest Distance from " + source +  " to 2: " + dist[2]);
    }
}

class adjmatrix {
    int vertices;
    int matrix[][];

    adjmatrix(int vertices){
        this.vertices = vertices;
        matrix = new int[vertices][vertices];

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix.length; ++j) {
                matrix[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    void addEdge(int source, int destination, int weight){
        matrix[source][destination] = weight;
    }
}
