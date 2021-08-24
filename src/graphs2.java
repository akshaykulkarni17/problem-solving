import java.util.*;



class Dist {
    int start;
    int value;
    public Dist(Integer start, Integer value) {
        this.start=start;
        this.value=value;
    }
}

public class graphs2 {

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(1, 4, 3, 1));
        ArrayList<Integer> a2 = new ArrayList<>(Arrays.asList(5, 2, 4, 4));
        ArrayList<Integer> a3 = new ArrayList<>(Arrays.asList(7, 38, 27, 37, 1));
        ArrayList<Integer> a4 = new ArrayList<>(Arrays.asList(1, 1, 2));
        ArrayList<Integer> a5 = new ArrayList<>(Arrays.asList(32, 18, 26));
        ArrayList<Integer> a6 = new ArrayList<>(Arrays.asList(1 ,2 ,2));
        ArrayList<Integer> a7 = new ArrayList<>(Arrays.asList(2 ,3 ,3));
        ArrayList<Integer> a8 = new ArrayList<>(Arrays.asList(3 ,4 ,1));
        ArrayList<Integer> a9 = new ArrayList<>(Arrays.asList(-1, 0, 0));
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        //arr.add(a);arr.add(a2);arr.add(a3);arr.add(a4);
        arr.add(a5);arr.add(a6);arr.add(a7);arr.add(a8);arr.add(a9);
        //System.out.println(dijkstraAlgo(5,arr,4));
        int[][] az = { {0 , 50 , 39}, {-1 , 0 , 1},{-1 , 10 , 0} };
        //System.out.println(Arrays.deepToString(FloydWarshallAlgorithm(az)));
        //System.out.println(ShortestDistanceInAMaze(arr,a4,a5));
        //System.out.println(AnotherBFS(6,arr,3,2));
        //System.out.println(SheldonAndPairOfCities(4,6,2,a,a2,a3,a4,a5));
        //System.out.println(SmallestSequenceWithGivenPrimes(2,3,5,15));
        //System.out.println(MinimumWeightedCycle(4,arr));
        //System.out.println(LargestDistanceBetweenNodesOfTree(a9));
        //System.out.println(MaximumDepth(5,a,a2,a3,a4,a5));
        ArrayList<ArrayList<Character>> arrayLists = new ArrayList<>();
        ArrayList<Character> q = new ArrayList<>(Arrays.asList('X','O','X','X','X','X','O','O','X','X'));
        ArrayList<Character> w = new ArrayList<>(Arrays.asList('X','O','O','O','O','X','O','O','X','X'));
        ArrayList<Character> e = new ArrayList<>(Arrays.asList('O','X','X','O','O','X','X','X','O','O'));
        ArrayList<Character> r = new ArrayList<>(Arrays.asList('O','X','O','X','O','O','O','X','X','O'));
        ArrayList<Character> t = new ArrayList<>(Arrays.asList('O','X','O','O','X','X','O','O','X','X'));
        ArrayList<Character> y = new ArrayList<>(Arrays.asList('O','X','X','X','O','X','X','O','X','O'));
        ArrayList<Character> u = new ArrayList<>(Arrays.asList('O','O','X','X','X','X','O','X','O','O'));
        arrayLists.add(q);arrayLists.add(w);arrayLists.add(e);arrayLists.add(r);arrayLists.add(t);arrayLists.add(y);arrayLists.add(u);
        CaptureRegionsOnBoard(arrayLists);
    }


    public static void CaptureRegionsOnBoard(ArrayList<ArrayList<Character>> a) {
        int row=a.size()-1;
        int col=a.get(0).size()-1;
        for (int i = 0; i <=row; i++) {
            for (int j = 0; j <=col; j++) {
                if (a.get(i).get(j).equals('O')){
                    boolean left=false;
                    boolean right=false;
                    boolean top=false;
                    boolean bottom=false;
                    for (int l = j-1; l>=0; l--) {
                        if (a.get(i).get(l).equals('X')) {
                            left = true;
                            break;
                        }
                    }
                    if(!left)break;
                    for (int r = j+1; r <=col ; r++) {
                        if (a.get(i).get(r).equals('X')) {
                            right = true;
                            break;
                        }
                    }
                    if (!right) break;
                    for (int t = i-1; t >=0; t--) {
                        if (a.get(t).get(j).equals('X')) {
                            top = true;
                            break;
                        }
                    }
                    if (!top) break;
                    for (int b = i+1; b <=row ; b++) {
                        if (a.get(b).get(j).equals('X')) {
                            bottom = true;
                            break;
                        }
                    }
                    if (!bottom) break;
                    a.get(i).set(j,'X');
                }
            }
        }
    }



    public static ArrayList<Integer> MaximumDepth(int A, ArrayList<Integer> B, ArrayList<Integer> C, ArrayList<Integer> D, ArrayList<Integer> E, ArrayList<Integer> F) {
        Map<Integer,ArrayList<Integer>> mapMaxDepth = new HashMap<>();
        for (int i = 1; i <= A; i++) {
            mapMaxDepth.put(i,new ArrayList<>());
        }
        ArrayList<Character> ch = new ArrayList<>();
        Arrays.toString(ch.toArray());
        for (int i = 0; i < B.size(); i++) {
            mapMaxDepth.putIfAbsent(B.get(i),new ArrayList<>());
            mapMaxDepth.putIfAbsent(C.get(i),new ArrayList<>());
            mapMaxDepth.get(B.get(i)).add(C.get(i));
            mapMaxDepth.get(C.get(i)).add(B.get(i));
        }
        int level=0;
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        boolean[] visited = new boolean[A+1];
        Map<Integer,ArrayList<Integer>> levelMap = new HashMap<>();
        while (!q.isEmpty()){
            int size=q.size();
            while (size!=0){
                int v = q.poll();
                visited[v] = true;
                if (levelMap.containsKey(level)){
                    ArrayList<Integer> tmp = levelMap.get(level);
                    tmp.add(v);
                    levelMap.put(level,tmp);
                }
                else levelMap.put(level,new ArrayList<>(Collections.singleton(v)));
                for (int x : mapMaxDepth.get(v)){
                    if (!visited[x]) q.add(x);
                }
                size--;
            }
            level++;
        }
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < E.size(); i++) {
            ArrayList<Integer> indexes = levelMap.get(E.get(i)%level);
            int min=Integer.MAX_VALUE;
            for (int x : indexes){
                if (D.get(x-1)>=F.get(i)){
                    min=Math.min(min,D.get(x-1));
                }
            }
            if (min == Integer.MAX_VALUE) {
                answer.add(-1);
            } else {
                answer.add(min);
            }
        }
        return answer;
    }





    static Map<Integer,ArrayList<Integer>> mapNodes;
    static boolean[] visitedNodes;
    static int maxNodes;
    public static int LargestDistanceBetweenNodesOfTree(ArrayList<Integer> A) {
        mapNodes = new HashMap<>();
        for (int i = 0; i < A.size(); i++) {
            mapNodes.put(i,new ArrayList<>());
        }
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i).equals(-1)) continue;
            ArrayList<Integer> tmp = mapNodes.get(A.get(i));
            tmp.add(i);
            mapNodes.put(A.get(i),tmp);
            ArrayList<Integer> tmp2 = mapNodes.get(i);
            tmp2.add(A.get(i));
            mapNodes.put(i,tmp2);
        }
        visitedNodes= new boolean[A.size()];
        maxNodes=Integer.MIN_VALUE;
        for (int i = 0; i < A.size(); i++) {
            dfsNodes(i,0);
        }
        return maxNodes;
    }

    private static void dfsNodes(int start, int distance) {
        maxNodes=Math.max(maxNodes,distance);
        visitedNodes[start]=true;
        for (int x : mapNodes.get(start)){
            if (!visitedNodes[x]) dfsNodes(x,distance+1);
        }
        visitedNodes[start]=false;
    }


    static Map<Integer,ArrayList<Dist>> mapMinWeight;
    static boolean[] visitedMinWeight;
    static int minWeight;
    public static int MinimumWeightedCycle(int A, ArrayList<ArrayList<Integer>> B) {
         mapMinWeight = new HashMap<>();
        for (int i = 1; i <=A ; i++) {
            mapMinWeight.put(i,new ArrayList<>());
        }
        for (ArrayList<Integer> list : B){
            Collections.sort(list);
            ArrayList<Dist> tmp = mapMinWeight.get(list.get(0));
            Dist d = new Dist(list.get(1),list.get(2));
            tmp.add(d);
            mapMinWeight.put(list.get(0),tmp);
            ArrayList<Dist> tmp2 = mapMinWeight.get(list.get(1));
            Dist d2 = new Dist(list.get(0),list.get(2));
            tmp2.add(d2);
            mapMinWeight.put(list.get(1),tmp2);
        }
        visitedMinWeight=new boolean[A+1];
        minWeight=Integer.MAX_VALUE;
        for (int i = 1; i <=A ; i++) {
            dfsMinWeight(i,i,0,0);
        }
        return minWeight;
    }

    private static void dfsMinWeight(int source,int start, int path,int cycle) {
        visitedMinWeight[start]=true;
        for (Dist x:mapMinWeight.get(start)){
            if (x.start==source&&cycle>=2){
                minWeight=Math.min(minWeight,path+x.value);
                return;
            }
            else if (!visitedMinWeight[x.start]) {
                dfsMinWeight(source,x.start,path+x.value,cycle+1);
            }
        }
        visitedMinWeight[start]=false;
    }

    public static ArrayList<Integer> SmallestSequenceWithGivenPrimes(int A, int B, int C, int D) {
        ArrayList<Integer> res = new ArrayList<>();
        res.add(1);
        int i = 0, x = 0, y = 0, z = 0;
        while(i < D)  {
            int tmp = Math.min(A*res.get(x), Math.min(B*res.get(y), C*res.get(z)));
            res.add(tmp);
            ++i;
            if(tmp == A*res.get(x)) ++x;
            if(tmp == B*res.get(y)) ++y;
            if(tmp == C*res.get(z)) ++z;
        }
        res.remove(0);
        return res;
    }




    public static ArrayList<Integer> SheldonAndPairOfCities(int A, int B, int C, ArrayList<Integer> D, ArrayList<Integer> E, ArrayList<Integer> F, ArrayList<Integer> G, ArrayList<Integer> H) {
        long[][] cities = new long[A+1][A+1];
        for (int i = 0; i <= A; i++) {
            for (int j = 0; j <=A ; j++) {
                if(i==0||j==0||i==j) cities[i][j]=0;
                else cities[i][j]=Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < B; i++) {
            cities[D.get(i)][E.get(i)]=cities[E.get(i)][D.get(i)]=F.get(i);
        }
        for (int k = 1; k <=A ; k++) {
            for (int i = 1; i <=A ; i++) {
                for (int j = 1; j <=A ; j++) {
                    cities[i][j]=Math.min(cities[i][j],cities[i][k]+cities[k][j]);
                }
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i <C ; i++) {
            int x = (int) cities[G.get(i)][H.get(i)];
            x = x>=Integer.MAX_VALUE ? -1 : x;
            ans.add(x);
        }
        return ans;
    }






    static Map<Integer,ArrayList<Dist>> mapAnotherBFS;
    static boolean[] visitedAnotherBFS;
    static int minAnotherBFS;
    public static int AnotherBFS(int A, ArrayList<ArrayList<Integer>> B, int C, int D) {
         mapAnotherBFS = new HashMap<>();
        for (int i = 0; i < A; i++) {
            mapAnotherBFS.put(i,new ArrayList<>());
        }
        for (ArrayList<Integer> list : B){
            ArrayList<Dist> tmp = mapAnotherBFS.get(list.get(0));
            Dist d = new Dist(list.get(1),list.get(2));
            tmp.add(d);
            mapAnotherBFS.put(list.get(0),tmp);
            ArrayList<Dist> tmp2 = mapAnotherBFS.get(list.get(1));
            Dist d2 = new Dist(list.get(0),list.get(2));
            tmp2.add(d2);
            mapAnotherBFS.put(list.get(1),tmp2);
        }
        minAnotherBFS=Integer.MAX_VALUE;
        visitedAnotherBFS=new boolean[A];
        dfsAnotherBFS(C,D,0);
        return minAnotherBFS==Integer.MAX_VALUE? -1 : minAnotherBFS;
    }

    private static void dfsAnotherBFS(int start, int end, int path) {
        if(start==end){
            minAnotherBFS=Math.min(minAnotherBFS,path);
            return;
        }
        visitedAnotherBFS[start]=true;
        for (Dist d : mapAnotherBFS.get(start)){
            if (!visitedAnotherBFS[d.start]) dfsAnotherBFS(d.start,end,path+d.value);
        }
        visitedAnotherBFS[start]=false;
    }


    static int matrixRow;
    static int matrixCol;
    static Map<String,ArrayList<String>> map;
    static int min;
    public static int ShortestDistanceInAMaze(ArrayList<ArrayList<Integer>> A, ArrayList<Integer> B, ArrayList<Integer> C) {
        int[]row={0,0,1,-1};
        int[]col={-1,1,0,0};
        matrixRow=A.size()-1;
        matrixCol=A.get(0).size()-1;
        map = new HashMap<>();
        if(A.get(B.get(0)).get(B.get(1)).equals(1)||A.get(C.get(0)).get(C.get(1)).equals(1)) return -1;
        for (int i = 0; i <=matrixRow ; i++) {
            for (int j = 0; j <=matrixCol ; j++) {
                if (A.get(i).get(j).equals(1)) continue;
                String point = new String(String.valueOf(i)+String.valueOf(j));
                ArrayList<String> points = new ArrayList<>();
                for (int k = 0; k < 4; k++) {
                    int r = i+row[k];
                    int c = j+col[k];
                    if (isValid(r,c)&&!A.get(r).get(c).equals(1)){
                        String p = new String(String.valueOf(r)+String.valueOf(c));
                        points.add(p);
                    }
                }
                map.put(point,points);
            }
        }
        String start=new String(String.valueOf(B.get(0))+String.valueOf(B.get(1)));
        String end=new String(String.valueOf(C.get(0))+String.valueOf(C.get(1)));
        min=Integer.MAX_VALUE;
        dfsMaze(start,end,0,new HashSet<>());
        return min;
    }
    static void dfsMaze (String start, String end,int count,Set<String> visited){
        if (start.equals(end)){
            min=Math.min(min,count);
            return;
        }
        visited.add(start);
        for (String s : map.get(start)){
            if (!visited.contains(s))dfsMaze(s,end,count+1,visited);
        }
        visited.remove(start);
    }
    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r <= matrixRow && c <= matrixCol;
    }






    public static int[][]  FloydWarshallAlgorithm(int[][] A) {
        int N = A.length;
        for (int i = 0; i <N ; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    A[j][k]=Math.min(A[j][k],A[j][i]+A[i][k]);
                }
            }
        }
        return A;
    }






    public static ArrayList<Integer> dijkstraAlgo(int A, ArrayList<ArrayList<Integer>> B, int C) {
        Map<Integer,ArrayList<Dist>> map = new HashMap<>();
        for (int i = 0; i < A; i++) {
            map.put(i,new ArrayList<>());
        }
        for (ArrayList<Integer> d : B) {
            Dist dist = new Dist(d.get(1),d.get(2));
            ArrayList<Dist> tmp = map.get(d.get(0));
            tmp.add(dist);
            map.put(d.get(0),tmp);
            ArrayList<Dist> tmp2=map.get(d.get(1));
            Dist dist2 = new Dist(d.get(0),d.get(2));
            tmp2.add(dist2);
            map.put(d.get(1),tmp2);
        }
        int[] distances = new int[A];
        Arrays.fill(distances,Integer.MAX_VALUE);
        distances[C]=0;
        PriorityQueue<Dist> pq = new PriorityQueue<>(new Comparator<Dist>() {
            @Override
            public int compare(Dist o1, Dist o2) {
                return o1.value-o2.value;
            }
        });
        pq.add(new Dist(C,distances[C]));
        while (!pq.isEmpty()){
            int v1 = pq.poll().start;
            for (Dist x : map.get(v1)){
                int v2=x.start;
                if(distances[v2]>distances[v1]+x.value){
                    distances[v2]=distances[v1]+x.value;
                    pq.add(new Dist(v2,distances[v2]));
                }
            }
        }
        ArrayList<Integer> arr = new ArrayList<>();
        for (int x:distances){
            if (x==Integer.MAX_VALUE) x=-1;
            arr.add(x);
        }
        return arr;
    }
}
