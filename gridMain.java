import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class gridMain {
    class DSU {
        int[] parent, rank;
        DSU(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 1; i <= n; i++) parent[i] = i;
        }
        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }
        void union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx == ry) return;
            if (rank[rx] < rank[ry]) parent[rx] = ry;
            else if (rank[rx] > rank[ry]) parent[ry] = rx;
            else {
                parent[ry] = rx;
                rank[rx]++;
            }
        }
    }

    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        DSU dsu = new DSU(c);
        
        // Step 1: Build DSU for all connections
        for (int[] conn : connections) {
            dsu.union(conn[0], conn[1]);
        }

        // Step 2: Create TreeSet for each component to track online stations
        Map<Integer, TreeSet<Integer>> gridOnlineStations = new HashMap<>();
        boolean[] isOnline = new boolean[c + 1];
        Arrays.fill(isOnline, true);

        for (int i = 1; i <= c; i++) {
            int root = dsu.find(i);
            gridOnlineStations.putIfAbsent(root, new TreeSet<>());
            gridOnlineStations.get(root).add(i);
        }

        // Step 3: Process queries
        List<Integer> outputList = new ArrayList<>();

        for (int[] q : queries) {
            int type = q[0];
            int x = q[1];
            int root = dsu.find(x);

            if (type == 1) { // Maintenance check
                if (isOnline[x]) {
                    outputList.add(x);
                } else {
                    TreeSet<Integer> set = gridOnlineStations.get(dsu.find(x));
                    if (set == null || set.isEmpty())
                        outputList.add(-1);
                    else
                        outputList.add(set.first());
                }
            } else if (type == 2) { // Station goes offline
                if (isOnline[x]) {
                    isOnline[x] = false;
                    TreeSet<Integer> set = gridOnlineStations.get(root);
                    if (set != null)
                        set.remove(x);
                }
            }
        }

        // Step 4: Convert list to array
        int[] result = new int[outputList.size()];
        for (int i = 0; i < outputList.size(); i++) {
            result[i] = outputList.get(i);
        }
        return result;
    }
    class Main {
        
    public static void main(String[] args) {
        gridMain sol = new gridMain();
        int c = 5;
        int[][] connections = {{1,2},{2,3},{3,4},{4,5}};
        int[][] queries = {{1,3},{2,1},{1,1},{2,2},{1,2}};
        System.out.println(Arrays.toString(sol.processQueries(c, connections, queries)));
        // Output: [3, 2, 3]
    }
}

}
