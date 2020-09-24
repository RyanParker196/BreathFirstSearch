import java.util.*;

class HW2 {

    // Adds the value y to the list at position x for a given AdjList
    static void addEdge(ArrayList<ArrayList<Integer>> adjList, int x, int y) {
        // Using x-1 to represent nodes as (1 to 8) rather than (0 to 7) in main
        adjList.get(x - 1).add(y);
    }

    // Helper function to print the adjList
    static void printGraph(ArrayList<ArrayList<Integer>> adjList) {
        for (int i = 0; i < adjList.size(); i++) {
            System.out.print(i + 1);
            System.out.print("-> ");
            int length = adjList.get(i).size();
            for (int j = 0; j < length; j++) {
                System.out.print(adjList.get(i).get(j));
                System.out.print(" -> ");
            }
            System.out.println();
        }
    }

    // Helper function to print the BFS Tree
    static void printTree(Set<int[]> bfs_tree) {
        for (int[] pair : bfs_tree) {
            System.out.print("(" + pair[0] + ", " + pair[1] + ")");

        }
    }

    // Implementation of BFS on an adjacency list
    static Set<int[]> breathFirstSearch(int s, ArrayList<ArrayList<Integer>> adjList) {

        boolean discovered[] = new boolean[8];
        Set<int[]> bfs_tree = new HashSet<int[]>();

        // Using linkedList structure for the Queue
        // O(1) to remove or add the first node
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // Mark the current node as visited and enqueue it
        discovered[s - 1] = true;
        queue.add(s);

        while (queue.size() != 0) {

            // Dequeue an element and visit it
            int curr = queue.pop();
            System.out.println(curr + " Was visited");

            // Get adjacentList for curr
            ArrayList<Integer> currList = adjList.get(curr - 1);

            // For each node adjacent to curr
            for (int i = 0; i < currList.size(); i++) {

                // Peek at each element and check if they have been discovered
                // If not, Discover and queue them
                Integer peeked = currList.get(i);

                // Construct pair for BFS Tree and add it to the Tree
                int[] pair = new int[] { curr, peeked };
                bfs_tree.add(pair);

                // Using peeked - 1 to get the correct index form 0 - 7
                if (!discovered[peeked - 1]) {
                    discovered[peeked - 1] = true;
                    queue.add(peeked);
                }
            }
        }
        return bfs_tree;
    }

    public static void main(String[] args) {

        int Vertices = 8;
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>(Vertices);

        // Get user input for starting vertex
        Scanner sc = new Scanner(System.in);
        System.out.println("Input a Starting Vertex between 1 and 8");
        int start = sc.nextInt();
        sc.close();

        // Init the adjList with empty arrays
        for (int i = 0; i < Vertices; i++)
            adjList.add(new ArrayList<Integer>());

        addEdge(adjList, 1, 2);
        addEdge(adjList, 1, 3);
        addEdge(adjList, 2, 1);
        addEdge(adjList, 2, 3);
        addEdge(adjList, 2, 4);
        addEdge(adjList, 2, 5);
        addEdge(adjList, 3, 1);
        addEdge(adjList, 3, 2);
        addEdge(adjList, 3, 5);
        addEdge(adjList, 3, 7);
        addEdge(adjList, 3, 8);
        addEdge(adjList, 4, 2);
        addEdge(adjList, 4, 5);
        addEdge(adjList, 5, 2);
        addEdge(adjList, 5, 3);
        addEdge(adjList, 5, 4);
        addEdge(adjList, 5, 6);
        addEdge(adjList, 6, 5);
        addEdge(adjList, 7, 3);
        addEdge(adjList, 7, 8);
        addEdge(adjList, 8, 3);
        addEdge(adjList, 8, 7);

        printGraph(adjList);

        Set<int[]> bfs_tree = breathFirstSearch(start, adjList);

        printTree(bfs_tree);
    }
}