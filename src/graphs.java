import java.util.*;

/**
 * Definition for undirected graph.*/
  class UndirectedGraphNode {
      int label;
      List<UndirectedGraphNode> neighbors;
      UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
  }
  enum Color{
  RED , GREEN;
}
class Orange{
    int i;
    int j;

    public Orange(int i, int j) {
        this.i=i;
        this.j=j;
    }
}

class Topology{
      int node;
    int in;
    public Topology(int node, int in) {
        this.node = node;
        this.in = in;
    }
}

public class graphs {
    public static void main(String[] args) {
        ArrayList<Integer> a1 = new ArrayList<>(Arrays.asList(7, 5));
        ArrayList<Integer> a2 = new ArrayList<>(Arrays.asList(15, 14));
        ArrayList<Integer> a3 = new ArrayList<>(Arrays.asList(11, 2));
        ArrayList<Integer> a4 = new ArrayList<>(Arrays.asList(8, 7));
        ArrayList<Integer> a5 = new ArrayList<>(Arrays.asList(10, 3));
        ArrayList<Integer> a6 = new ArrayList<>(Arrays.asList(5, 3));
        ArrayList<Integer> a7 = new ArrayList<>(Arrays.asList(4, 2));
        ArrayList<Integer> a8 = new ArrayList<>(Arrays.asList(6, 4));
        ArrayList<Integer> a9 = new ArrayList<>(Arrays.asList(13, 2));
        ArrayList<Integer> a10 = new ArrayList<>(Arrays.asList(3, 2));
        ArrayList<Integer> a11= new ArrayList<>(Arrays.asList(14,11));
        ArrayList<Integer> a12= new ArrayList<>(Arrays.asList(12,9));
        ArrayList<Integer> a13= new ArrayList<>(Arrays.asList(2,1));
        ArrayList<Integer> a14= new ArrayList<>(Arrays.asList(9, 2));
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        a.add(a1);
        a.add(a2);
        a.add(a3);
        a.add(a4);
        a.add(a5);
        a.add(a6);
        a.add(a7);
        a.add(a8);a.add(a9);a.add(a10);a.add(a11); a.add(a12); a.add(a13); a.add(a14);
        //System.out.println(PathInDirectedGraph(5,a));
        //System.out.println(RottenOranges(a));
        //System.out.println(FirstDepthFirstSearch(a4,9,1));
        UndirectedGraphNode z = new UndirectedGraphNode(1);
        UndirectedGraphNode x = new UndirectedGraphNode(2);
        UndirectedGraphNode c = new UndirectedGraphNode(3);
        UndirectedGraphNode v = new UndirectedGraphNode(4);
        UndirectedGraphNode b = new UndirectedGraphNode(5);
        UndirectedGraphNode n = new UndirectedGraphNode(6);
        z.neighbors.add(x);
        z.neighbors.add(c);
        z.neighbors.add(v);
        v.neighbors.add(b);
        v.neighbors.add(n);
        //System.out.println(cloneGraph(z));
        int[][] A = {{0, 0, 0, 1}, {0, 0, 1, 1}, {0, 1, 1, 0}};
        //DistanceOfNearestCell(A);
        //System.out.println(isBipartite(9, a));
        //System.out.println(CycleInDirectedGraph(5,a));
        //System.out.println(TopologicalSort(8,a));
        //System.out.println(ConstructRoads(15,a));
        ArrayList<String> s = new ArrayList<>(Arrays.asList("aab", "bac", "aaa", "cda"));
        System.out.println(MakeCircle(s));
    }


    public int MinimumWeightedCycle(int A, ArrayList<ArrayList<Integer>> B) {
        int[] inDegree =new int[A+1];
        int[] outDegree = new int[A+1];
        return 0;
    }





    public int PossibilityOfFinishing(int A, ArrayList<Integer> B, ArrayList<Integer> C) {
        int[] inDegree = new int[A+1];
        boolean[] visited = new boolean[A+1];
        Map<Integer,ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < B.size(); i++) {
            if (map.containsKey(B.get(i))){
                ArrayList<Integer> tmp = map.get(B.get(i));
                tmp.add(C.get(i));
                map.put(B.get(i),tmp);
            }
            else map.put(B.get(i),new ArrayList<>(Collections.singleton(C.get(i))));
            inDegree[C.get(i)]++;
        }
        for (int i = 1; i <=A ; i++) {
            if (inDegree[i]==0&&map.containsKey(i)&&!visited[i]){
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                visited[i]=true;
                while (!q.isEmpty()){
                    int v = q.poll();
                    if(map.containsKey(v)){
                        for (int x : map.get(v)){
                            inDegree[x]--;
                            if(inDegree[x]==0&&!visited[x]){
                                q.add(x);
                                visited[x]=true;
                            }
                        }
                    }
                    if(inDegree[v]==0&&!visited[v]){
                        q.add(v);
                        visited[v]=true;
                    }
                }
            }
        }
        for (int i = 1; i <=A ; i++) {
            if(!visited[i]) return 0;
        }
        return 1;
    }







    static Map<Character,ArrayList<String>> stringMap;
    public static int MakeCircle(ArrayList<String> A) {
         stringMap = new HashMap<>();
        boolean[] visited = new boolean[26];
        for (char i = 'a'; i <='z'; i++) {
            stringMap.put(i,new ArrayList<>());
        }
        int[] inbound = new int[26];
        int[] outbound = new int[26];
        for(String s :A){
            ArrayList<String> tmp = stringMap.get(s.charAt(0));
            tmp.add(s);
            stringMap.put(s.charAt(0),tmp);
            inbound[s.charAt(0)-'a']++;
            outbound[s.charAt(s.length()-1)-'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if(inbound[i]!=outbound[i]) return 0;
        }
        dfsCircle(visited,A.get(0).charAt(0));
        for (char i = 'a'; i <='z'; i++) {
            if (!visited[i-'a']&&stringMap.get(i).size()>0) return 0;
        }
        return 1;
    }

    private static void dfsCircle(boolean[] visited, char c) {
        visited[c-'a']=true;
        for (String s : stringMap.get(c)){
            if(!visited[s.charAt(s.length()-1)-'a']){
                dfsCircle(visited,s.charAt(s.length()-1));
            }
        }
    }


    public static int ConstructRoads(int A, ArrayList<ArrayList<Integer>> B) {
        int[] color = new int[A+1];
        Arrays.fill(color,-1);
        Map<Integer,ArrayList<Integer>> mapper= new HashMap<>();
        for (int i = 1; i <=A ; i++) {
            mapper.put(i,new ArrayList<>());
        }
        for (ArrayList<Integer> integers : B){
            ArrayList<Integer> temp = mapper.get(integers.get(0));
            temp.add(integers.get(1));
            mapper.put(integers.get(0),temp);
            temp = mapper.get(integers.get(1));
            temp.add(integers.get(0));
            mapper.put(integers.get(1),temp);
        }
        for (int i = 1; i <=A ; i++) {
            if (color[i]!=-1) continue;
            color[i]=1;
            Queue<Integer> q = new LinkedList<>();
            q.add(i);
            while (!q.isEmpty()){
                int x = q.poll();
                for (int z : mapper.get(x)){
                    if (color[z]==-1){
                        color[z]=color[x]^1;
                        q.add(x);
                    }
                    if (color[z]==color[x]){
                       return 0;
                    }
                }
            }
        }
        int count1 = 0;
        int count0 = 0;
        for (int x : color){
            if (x==0) count0++;
            if(x==1) count1++;
        }
        return count0*count1-B.size();
    }









    public static ArrayList<Integer> TopologicalSort(int A, ArrayList<ArrayList<Integer>> B) {
        int[] inDegree = new  int[A+1];
        boolean[] visited = new boolean[A+1];
        Map<Integer,ArrayList<Integer>> mapper= new HashMap<>();
        for (ArrayList<Integer> integers : B){
            if(mapper.containsKey(integers.get(0))){
                ArrayList<Integer> temp = mapper.get(integers.get(0));
                temp.add(integers.get(1));
                Collections.sort(temp);
                mapper.put(integers.get(0),temp);
            }
            else mapper.put(integers.get(0),new ArrayList<>(Collections.singleton(integers.get(1))));
            inDegree[integers.get(1)]++;
        }
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < inDegree.length; i++) {
            if (inDegree[i]==0&&!visited[i]){
                q.add(i);
            }
            while (!q.isEmpty()){
                int v = q.poll();
                if (mapper.containsKey(v)){
                    for (int x: mapper.get(v)){
                        inDegree[x]--;
                        if(inDegree[x]==0&&!visited[v]) q.add(x);
                    }
                }
                if (inDegree[v]==0&&!visited[v]){
                    visited[v]=true;
                    ans.add(v);
                }
            }

        }


        return ans;
    }







    static Set<Integer> dfsComplete ;
    static Set<Integer> dfsRunning ;
    static Map<Integer,ArrayList<Integer>> mapper ;
    static boolean isCycle;
    public static int CycleInDirectedGraph(int A, ArrayList<ArrayList<Integer>> B) {
         dfsComplete = new HashSet<>();
         dfsRunning = new HashSet<>();
         mapper = new HashMap<>();
        for(ArrayList<Integer> integers : B){
            if(mapper.containsKey(integers.get(0))){
                ArrayList<Integer> temp = mapper.get(integers.get(0));
                temp.add(integers.get(1));
                mapper.put(integers.get(0),temp);
            }
            else mapper.put(integers.get(0),new ArrayList<>(Collections.singleton(integers.get(1))));
        }
        isCycle=false;
        for (int i = 1; i <= A ; i++) {
            if(!dfsComplete.contains(i) && mapper.containsKey(i)){
                runDFS(i);
                if(isCycle)return 1;
            }
        }
        return 0;
    }

    private static void runDFS(int x) {
        if(dfsRunning.contains(x)) {
            isCycle=true;
            return;
        }
        dfsRunning.add(x);
        if(mapper.containsKey(x)){
            for (int i: mapper.get(x)){
                  runDFS(i);
            }
        }
        dfsRunning.remove(x);
        dfsComplete.add(x);
    }


    static ArrayList<ArrayList<Integer>> graph;
    public static void graphC() {
        graph = new ArrayList < ArrayList < Integer > > ();
        for (int i = 0; i < 100009; i++) {
            graph.add(new ArrayList < Integer > ());
        }
    }
    public static int isBipartite(int A, int[][] B){
        graphC();
        for (int[] edge: B) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        if (isBipar(A) == true)
            return 1;
        return 0;
    }
    public static boolean isBipar(int n) {
        if (n == 0)
            return true;
        int[] color = new int[n];
        Arrays.fill(color, -1);
        Queue < Integer > q = new ArrayDeque < > ();
        for (int i = 0; i < n; ++i) {
            if (color[i] != -1)
                continue;
            color[i] = 1;
            q.offer(i);
            while (q.size() > 0) {
                int x = q.poll();
                for (int it: graph.get(x)) {
                    if (color[it] == -1) {
                        color[it] = color[x] ^ 1;
                        q.offer(it);
                    } else if (color[it] == color[x])
                        return false;
                }
            }
        }
        return true;
    }








    public static int[][] DistanceOfNearestCell(int[][] A) {
        int[] row = {0,0,-1,1};
        int[] col = {1,-1,0,0};
        matrixRow=A.length-1;
        matrixCol=A[0].length-1;
        int[][] matrix = new int[A.length][A[0].length];
        boolean[][] visited = new boolean[A.length][A[0].length];
        Queue<Orange> q = new LinkedList<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j]==1){
                    q.add(new Orange(i,j));
                    visited[i][j]=true;
                }
            }
        }
        while (!q.isEmpty()){
            Orange temp = q.poll();
            int i= temp.i;
            int j= temp.j;
            int val = matrix[i][j];
            for (int k = 0; k < 4; k++) {
                int r=i+row[k];
                int c=j+col[k];
                if(isValid(r,c)&&!visited[r][c]){
                    matrix[r][c]=val+1;
                    visited[r][c]=true;
                    q.add(new Orange(r,c));
                }
            }
        }
        return matrix;
    }





    static Map<UndirectedGraphNode,UndirectedGraphNode> VisitedMap;
    public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        VisitedMap = new HashMap<>();
        doDFs(node);
        return VisitedMap.get(node);
    }

    private static void doDFs(UndirectedGraphNode node) {
        if (VisitedMap.containsKey(node)){
            return;
        }
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        VisitedMap.put(node,clone);
        if (!node.neighbors.isEmpty()){
            for (UndirectedGraphNode u : node.neighbors){
                doDFs(u);
                clone.neighbors.add(VisitedMap.get(u));
            }
        }
    }


    public int NumberOfIslands(ArrayList<ArrayList<Integer>> A) {
        int[] row = {0,0,-1,1,-1,-1,1,1};
        int[] col = {1,-1,0,0,-1,1,-1,1};
        matrixRow=A.size()-1;
        matrixCol=A.get(0).size()-1;
        boolean[][] visited = new boolean[matrixRow+1][matrixCol+1];
        int islands=0;
        for (int i = 0; i <=matrixRow ; i++) {
            for (int j = 0; j <=matrixCol ; j++) {
                if (A.get(i).get(j).equals(1)&&!visited[i][j]){
                    Queue<Orange> q = new LinkedList<>();
                    q.add(new Orange(i,j));
                    while (!q.isEmpty()){
                        Orange temp=q.poll();
                        int r= temp.i;
                        int c= temp.j;
                        visited[r][c]=true;
                        for (int k = 0; k < 8; k++) {
                            int h = row[k]+r;
                            int v = col[k]+c;
                            if (isValid(h,v)){
                                visited[h][v] = true;
                                if(A.get(h).get(v).equals(1)) q.add(new Orange(h,v));
                            }
                        }
                    }
                    islands++;
                }
            }
        }
        return islands;
    }





    static Map<Integer,ArrayList<Integer>> map;
    static boolean possible;
    public static int FirstDepthFirstSearch(ArrayList<Integer> A, final int B, final int C) {
        map = new HashMap<>();
        for(int i=1; i<A.size(); i++){
            if(map.containsKey(A.get(i))){
                ArrayList<Integer> temp = map.get(A.get(i));
                temp.add(i+1);
                map.put(A.get(i),temp);
            }
            else {
                map.put(A.get(i),new ArrayList<>(Collections.singleton(i+1)));
            }
        }
        possible=false;
        dfs(C,B);
        if(possible)return 1;
        return 0;
    }
    static void dfs (int from, int to){
        if (map.containsKey(from)){
            for (int x : map.get(from)){
                if (x==to) possible= true;
                dfs(x,to);
            }
        }
    }






    static int matrixRow;
    static int matrixCol;
    public static int RottenOranges(ArrayList<ArrayList<Integer>> A) {
        int[] row = {0,0,-1,1};
        int[] col = {1,-1,0,0};
        matrixRow=A.size()-1;
        matrixCol=A.get(0).size()-1;
        Queue<Orange> q = new LinkedList<>();
        boolean[][] visited = new boolean[matrixRow+1][matrixCol+1];
        for (int i = 0; i < A.size(); i++) {
            for (int j = 0; j < A.get(0).size(); j++) {
                if(A.get(i).get(j).equals(2)){
                    Orange o = new Orange(i,j);
                    q.add(o);
                    visited[i][j]=true;
                }
                else if(A.get(i).get(j).equals(0)){
                    visited[i][j]=true;
                }
            }
        }
        if(q.isEmpty()) return -1;
        int time=-1;
        while (!q.isEmpty()){
            int size = q.size();
            while(size!=0){
                Orange temp = q.poll();
                for(int k=0;k<4;k++){
                    int r = temp.i+row[k];
                    int c = temp.j+col[k];
                    if(isValid(r,c)){
                        int curr = A.get(r).get(c);
                        if(curr==1&&!visited[r][c]){
                            q.add(new Orange(r,c));
                        }
                        visited[r][c]=true;
                    }
                }
                size--;
            }
            time++;
        }
        for (int i = 0; i <=matrixRow ; i++) {
            for (int j = 0; j <=matrixCol ; j++) {
                if(!visited[i][j]) return -1;
            }
        }
        return time;
    }
    static boolean isValid(int i, int j){
        return i >= 0 && j >= 0 && i <= matrixRow && j <= matrixCol;
    }


    public static int PathInDirectedGraph(int A, ArrayList<ArrayList<Integer>> B) {
        Map<Integer,ArrayList<Integer>> map = new HashMap<>();
        for(ArrayList<Integer> a: B){
            if(map.containsKey(a.get(0))){
                ArrayList<Integer> temp = map.get(a.get(0));
                temp.add(a.get(1));
                map.put(a.get(0),temp);
            }
            else {
                map.put(a.get(0),new ArrayList<>(Collections.singleton(a.get(1))));
            }
        }
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.add(1);
        visited.add(1);
        while(!q.isEmpty()){
            int temp=q.poll();
            if (map.containsKey(temp)){
                for (int x : map.get(temp)){
                    if (x==A) return 1;
                    if (!visited.contains(x))q.add(x);
                    visited.add(x);
                }
            }
        }
        return 0;
    }


        public int IsBiPartiteBFSMySolution(int A, ArrayList<ArrayList<Integer>> B) {
            Map<Integer,ArrayList<Integer>> map = new HashMap<>();
            for (ArrayList<Integer> integers : B) {
                if (map.containsKey(integers.get(0))) {
                    ArrayList<Integer> temp = map.get(integers.get(0));
                    temp.add(integers.get(1));
                    map.put(integers.get(0), temp);
                } else map.put(integers.get(0), new ArrayList<>(Collections.singleton(integers.get(1))));
            }
            ArrayList<ArrayList<Integer>> length = new ArrayList<>();
            for (int x : map.keySet()){
                length.add(new ArrayList<>(Arrays.asList(x,map.get(x).size())));
            }
            length.sort(Comparator.comparing(a->-1*a.get(1)));
            Set<Integer> s1 = new HashSet<>();
            Set<Integer> s2 = new HashSet<>();
            while (!length.isEmpty()&& !map.isEmpty()){
                Queue<Integer> q = new LinkedList<>();
                int v = length.get(0).get(0);
                length.remove(0);
                q.add(v);
                while (!q.isEmpty()){
                    v = q.poll();
                    if (map.containsKey(v)){
                        if (s1.contains(v)){
                            for (int x : map.get(v)){
                                if(s1.contains(x)) return 0;
                                s2.add(x);
                                q.add(x);
                            }
                        }
                        else if(s2.contains(v)){
                            for (int x : map.get(v)){
                                if (s2.contains(x)) return 0;
                                s1.add(x);
                                q.add(x);
                            }
                        }
                        else{
                            for(int y : map.get(v)){
                                if (s1.contains(y)) {
                                    s2.add(v);
                                    q.add(y);
                                }
                                else if(s2.contains(y)){
                                    s1.add(v);
                                    q.add(y);
                                }
                                else{
                                    s1.add(v);
                                    s2.add(y);
                                    q.add(y);
                                }
                            }
                        }
                        map.remove(v);
                    }
                }
            }
            return 1;
        }

}
