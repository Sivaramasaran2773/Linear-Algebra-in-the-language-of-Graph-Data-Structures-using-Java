import java.util.ArrayList;
import java.util.List;

class Graph {
    List<Node> nodes;
    List<Edge> edges;

    Graph() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    void addNode(Node node) {
        nodes.add(node);
    }

    void addEdge(Edge edge) {
        edges.add(edge);
    }

    double[][] Adjacency_Matrix() {
        int n = nodes.size();
        double[][] A = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = 0.0;
            }
        }

        for (Edge edge : edges) {
            A[nodes.indexOf(edge.from)][nodes.indexOf(edge.to)] = edge.coefficient;
        }

        System.out.println("Adjancency matrix obtained after appending nodes: ");
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(A[i][j] + "  ");
            }
            System.out.println();
        }
        return A;
    }

    public double[] solve(double[][] A, double[] b) {

        int N = b.length;
        for (int k = 0; k < N; k++) {
            int mal = k;

            for (int i = k + 1; i < N; i++) {
                if (Math.abs(A[i][k]) > Math.abs(A[mal][k])) {
                    mal = i;
                }
            }

            double[] temp = A[k];
            A[k] = A[mal];
            A[mal] = temp;

            double t = b[k];
            b[k] = b[mal];
            b[mal] = t;

            for (int i = k + 1; i < N; i++) {
                double factor = A[i][k] / A[k][k];
                b[i] -= factor * b[k];
                for (int j = k; j < N; j++) {
                    A[i][j] -= factor * A[k][j];
                }
            }
        }

        double[] l = new double[N];
        for (int i = N - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++) {
                sum += A[i][j] * l[j];
            }
            l[i] = (b[i] - sum) / A[i][i];
        }

     

        return l;
    }

    void print(double[] a) {
        System.out.println("Solution vector for the given System of equations is: ");
        System.out.println();
        for (int r = 0; r < a.length; r++) {
            System.out.println(a[r]);
        }
    }

}

class Node {
    String name;

    Node(String name) {
        this.name = name;
    }
}

class Edge {
    Node to;
    Node from;
    double coefficient;

    Edge(Node from, Node to, double coefficient) {
        this.from = from;
        this.to = to;
        this.coefficient = coefficient;
    }
}

public class Gauss_Elim {

    public static void main(String[] args) {

        // Create a graph to represent the System of equations
        Graph graph = new Graph();

        Node x = new Node("x");
        Node y = new Node("y");
        Node z = new Node("z");

        graph.addNode(x);
        graph.addNode(y);
        graph.addNode(z);

        // Create the edges representing the coefficients
        Edge x_coef_x_eq1 = new Edge(x, x, 6.0);
        Edge x_coef_y_eq1 = new Edge(x, y, -1.0);
        Edge x_coef_z_eq1 = new Edge(x, z, -5.0);

        Edge y_coef_x_eq2 = new Edge(y, x, -4.0);
        Edge y_coef_y_eq2 = new Edge(y, y, 9.0);
        Edge y_coef_z_eq2 = new Edge(y, z, 0.0);

        Edge z_coef_x_eq3 = new Edge(z, x, -1.0);
        Edge z_coef_y_eq3 = new Edge(z, y, 0.0);
        Edge z_coef_z_eq3 = new Edge(z, z, 1.0);

        // Add the edges to the graph
        graph.addEdge(x_coef_x_eq1);
        graph.addEdge(x_coef_y_eq1);
        graph.addEdge(x_coef_z_eq1);
        graph.addEdge(y_coef_x_eq2);
        graph.addEdge(y_coef_y_eq2);
        graph.addEdge(y_coef_z_eq2);
        graph.addEdge(z_coef_x_eq3);
        graph.addEdge(z_coef_y_eq3);
        graph.addEdge(z_coef_z_eq3);

        double[] con = new double[3];
        con[0] = 5.0;
        con[1] = -40.0;
        con[2] = 4.0;

        double[][] M = graph.Adjacency_Matrix();
        System.out.println();
        double[] ans = graph.solve(M, con);
        graph.print(ans);
    }
}