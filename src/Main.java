
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static class Edge{
        int weight;
        int beg,end;

    }
    public static class krus{

        int ojciec[] = new int[20];

        int find(int x) {
            if (ojciec[x] == x) {
                return x;
            }else
                return find(ojciec[x]);
        }

        void scal(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            ojciec[fx] = fy;
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Edge> E = new ArrayList<>();
        File plik = new File("In0303.txt");
        Scanner fileScanner = new Scanner(plik);
        int n = fileScanner.nextInt();
        int m = fileScanner.nextInt();
        fileScanner.nextLine();
        int i = 1;
        boolean f;
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();

            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNext()) {
                f = true;
                Edge e = new Edge();
                e.beg = i;
                e.end = lineScanner.nextInt();
                e.weight = lineScanner.nextInt();
                for (Edge e1 : E) {
                    if ((e1.beg == e.end) && (e1.end == e.beg))
                        f = false;
                }
                if (f)
                    E.add(e);
            }
            i++;
        }
        fileScanner.close();

        Collections.sort(E, new Comparator<Edge>() {
            @Override
            public int compare(Edge e1, Edge e2) {
                return e1.weight - e2.weight; // Ascending
            }
        });
        int p, q, w;
        int mst_edges = 0;
        int j = 0;
        PrintWriter zapis = new PrintWriter("Out0303.txt");

        krus X = new krus();
        for (i = 1; i <= n; i++) {
            X.ojciec[i] = i;
        }

        while ((mst_edges < n - 1) || (j < m)) {
            p = E.get(j).beg;
            q = E.get(j).end;
            w = E.get(j).weight;

            if (X.find(p) != X.find(q)) {
                X.scal(p, q);
                System.out.println(p + " " + q + " [" + w +"]");
                zapis.println(p + " " + q + " [" + w +"]");
                mst_edges++;
            }else
                System.out.println(p + " " + q + " [" + w +"] - Nie dodano");
            j++;
        }
        zapis.close();
    }
}