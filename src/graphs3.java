import java.util.*;

class MatrixEdge{
    String from;
    String to;
    int weight;

    public MatrixEdge(String from, String to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
class Edge {
    int from;
    int to;
    int weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        Edge edge = (Edge) o;
        return from == edge.from && to == edge.to && weight == edge.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, weight);
    }
}
public class graphs3 {


    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(1, 6, 7, 2, 9, 4, 5));
        ArrayList<Integer> a2 = new ArrayList<>(Arrays.asList(1, 2));
        ArrayList<Integer> a3 = new ArrayList<>(Arrays.asList( 3, 4));
        ArrayList<Integer> a4 = new ArrayList<>(Arrays.asList(5,6));
        ArrayList<Integer> a5 = new ArrayList<>(Arrays.asList(5,7));
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        //arr.add(a);
        arr.add(a2);arr.add(a3);arr.add(a4);//arr.add(a5);
        //System.out.println(CommutableIslands(4,arr));
        //System.out.println(EdgeInMST(3,arr));
        //System.out.println(Batches(7,a,arr,12));
        //System.out.println(MatrixAndAbsoluteDifference(3,3,arr));
        //System.out.println(ConstructionCost(3,arr));
    }

    public int GymTrainer(int A, ArrayList<ArrayList<Integer>> B, ArrayList<ArrayList<Integer>> C) {
        Map<Integer,ArrayList<Integer>> walkMap = new HashMap<>();
        Map<Integer,ArrayList<Integer>> talkMap = new HashMap<>();
        for (ArrayList<Integer> b : B){
            if(!walkMap.containsKey(b.get(0))) walkMap.put(b.get(0),new ArrayList<>());
            if(!walkMap.containsKey(b.get(1))) walkMap.put(b.get(1),new ArrayList<>());
            ArrayList<Integer> tmp = walkMap.get(b.get(0));
            tmp.add(b.get(1));
            ArrayList<Integer> tmp2 = walkMap.get(b.get(1));
            tmp2.add(b.get(0));
        }
        for (ArrayList<Integer> c : C){
            if (walkMap.containsKey(c.get(0))|| walkMap.containsKey(c.get(1))) return 0;
            if(!talkMap.containsKey(c.get(0))) talkMap.put(c.get(0),new ArrayList<>());
            if(!talkMap.containsKey(c.get(1))) talkMap.put(c.get(1),new ArrayList<>());
            ArrayList<Integer> tmp = talkMap.get(c.get(0));
            tmp.add(c.get(1));
            ArrayList<Integer> tmp2 = talkMap.get(c.get(1));
            tmp2.add(c.get(0));
        }
        Set<Integer> visited = new HashSet<>();
        int count1=0;
        for (int i = 1; i <=A ; i++) {
            if(visited.contains(i)||!walkMap.containsKey(i)) continue;
            Set<Integer> set = new HashSet<>();
            set.add(i);
            Queue<Integer> q = new LinkedList<>();
            q.add(i);
            while (!q.isEmpty()){
                int v =q.poll();
                for (int x: walkMap.get(v)){
                    if (!set.contains(x)){
                        set.add(x);
                        q.add(x);
                    }
                }
            }
            count1++;
            visited.addAll(set);
        }
        int count2=0;
        for (int i = 1; i <=A ; i++) {
            if(visited.contains(i)||!talkMap.containsKey(i)) continue;
            Set<Integer> set = new HashSet<>();
            set.add(i);
            Queue<Integer> q = new LinkedList<>();
            q.add(i);
            while (!q.isEmpty()){
                int v =q.poll();
                for (int x: talkMap.get(v)){
                    if (!set.contains(x)){
                        set.add(x);
                        q.add(x);
                    }
                }
            }
            count2++;
            visited.addAll(set);
        }
        long diet = 1;
        int ctr = count1+count2;
        while (ctr!=0){
            diet*=2;
            diet%=1000000007L;
            ctr--;
        }
        return (int) diet;
    }




    public static int ConstructionCost(int A, ArrayList<ArrayList<Integer>> B) {
        Map<Integer,ArrayList<Edge>> roadMap = new HashMap<>();
        for (int i = 1; i <=A ; i++) {
            roadMap.put(i,new ArrayList<>());
        }
        for (ArrayList<Integer> r : B){
            ArrayList<Edge> tmp = roadMap.get(r.get(0));
            tmp.add(new Edge(r.get(0), r.get(1), r.get(2) ));
            roadMap.put(r.get(0),tmp);
            ArrayList<Edge> tmp2 = roadMap.get(r.get(1));
            tmp2.add(new Edge(r.get(1), r.get(0), r.get(2) ));
            roadMap.put(r.get(1),tmp2);
        }
        Set<Integer> set = new HashSet<>();
        Set<Edge> mst = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight-o2.weight;
            }
        });
        set.add(1);
        pq.addAll(roadMap.get(1));
        while (!pq.isEmpty()){
            Edge min = pq.poll();
            if(!set.contains(min.to)){
                set.add(min.to);
                mst.add(min);
                pq.addAll(roadMap.get(min.to));
            }
        }
        long sum =0;
        for (Edge e : mst){
            sum+=e.weight;
            sum%=1000000007L;
        }
        return (int) sum;
    }





    public static int MatrixAndAbsoluteDifference(int A, int B, ArrayList<ArrayList<Integer>> C) {
        Map<String,ArrayList<MatrixEdge>> matrixMap = new HashMap<>();
        int[] row = {0,0,-1,1};
        int[] col = {-1,1,0,0};
        for (int i = 0; i < A; i++) {
            for (int j = 0; j < B; j++) {
                int node = C.get(i).get(j);
                ArrayList<MatrixEdge> e = new ArrayList<>();
                for (int k = 0; k < 4; k++) {
                    int r = i+row[k];
                    int c = j+col[k];
                    if(isValid(r,c,A,B)){
                        e.add(new MatrixEdge(""+i+j,""+r+c,Math.abs(node-C.get(r).get(c))));
                    }
                }
                matrixMap.put(""+i+j,e);
            }
        }
        Set<String> set = new HashSet<>();
        Set<MatrixEdge> mst = new HashSet<>();
        PriorityQueue<MatrixEdge> pq = new PriorityQueue<>(new Comparator<MatrixEdge>() {
            @Override
            public int compare(MatrixEdge o1, MatrixEdge o2) {
                return o1.weight-o2.weight;
            }
        });
        set.add("00");
        pq.addAll(matrixMap.get("00"));
        while (!pq.isEmpty()){
            MatrixEdge min = pq.poll();
            if(!set.contains(min.to)){
                set.add(min.to);
                mst.add(min);
                pq.addAll(matrixMap.get(min.to));
            }
        }
        int k= Integer.MIN_VALUE;
        for (MatrixEdge e : mst){
            k=Math.max(k,e.weight);
        }
        return k;
    }

    private static boolean isValid(int r, int c, int a, int b) {
        return r>=0&&c>=0&&r<a&&c<b;
    }


    public static int Batches(int A, ArrayList<Integer> B, ArrayList<ArrayList<Integer>> C, int D) {
        Map<Integer,ArrayList<Integer>> batch = new HashMap<>();
        for (int i=1;i<=A;i++){
            batch.put(i,new ArrayList<>());
        }
        for (ArrayList<Integer> l : C){
            ArrayList<Integer> tmp = batch.get(l.get(0));
            tmp.add(l.get(1));
            batch.put(l.get(0),tmp);
            ArrayList<Integer> tmp2 = batch.get(l.get(1));
            tmp2.add(l.get(0));
            batch.put(l.get(1),tmp2);
        }
        int count=0;
        Set<Integer> visited = new HashSet<>();
        for (int i = 1; i <=A ; i++) {
            if (visited.contains(i)) continue;
            Queue<Integer> q = new LinkedList<>();
            Set<Integer> set = new HashSet<>();
            q.add(i);
            set.add(i);
            while (!q.isEmpty()){
                int x = q.poll();
                for (int z : batch.get(x)){
                    if (!set.contains(z)){
                        set.add(z);
                        q.add(z);
                    }
                }
            }
            int sum = 0;
            for(int x : set){
                sum+=B.get(x-1);
            }
            if(sum>=D) count++;
            visited.addAll(set);
        }
        return count;
    }




    public static ArrayList<Integer> EdgeInMST(int A, ArrayList<ArrayList<Integer>> B) {
        Map<Integer,ArrayList<Edge>> mstEdge = new HashMap<>();
        for (int i = 1; i <=A ; i++) {
            mstEdge.put(i,new ArrayList<>());
        }
        for (ArrayList<Integer> d : B){
            ArrayList<Edge> tmp = mstEdge.get(d.get(0));
            tmp.add(new Edge(d.get(0),d.get(1),d.get(2)));
            mstEdge.put(d.get(0),tmp);
            ArrayList<Edge> tmp2 = mstEdge.get(d.get(1));
            tmp2.add(new Edge(d.get(1),d.get(0),d.get(2)));
            mstEdge.put(d.get(1),tmp2);
        }
        Set<Edge> visited = new HashSet<>();
        for (int i = 1; i <=A ; i++) {
            Set<Integer> set = new HashSet<>();
            PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
                @Override
                public int compare(Edge o1, Edge o2) {
                    return o1.weight-o2.weight;
                }
            });
            set.add(i);
            pq.addAll(mstEdge.get(i));
            while (!pq.isEmpty()){
                Edge min = pq.poll();
                if(!set.contains(min.to)){
                    set.add(min.to);
                    visited.add(min);
                    pq.addAll(mstEdge.get(min.to));
                }
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (ArrayList<Integer> list : B){
            if (visited.contains(new Edge(list.get(0),list.get(1),list.get(2))) || visited.contains(new Edge(list.get(1),list.get(0),list.get(2)))){
                ans.add(1);
            }
            else ans.add(0);
        }
        return ans;
    }



    public static int CommutableIslands(int A, ArrayList<ArrayList<Integer>> B) {
        Map<Integer,ArrayList<Edge>> islandMap = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        Set<Edge> mst = new HashSet<>();
        for (int i = 1; i <=A ; i++) {
            islandMap.put(i,new ArrayList<>());
        }
        for (ArrayList<Integer> d : B){
            ArrayList<Edge> tmp = islandMap.get(d.get(0));
            tmp.add(new Edge(d.get(0),d.get(1),d.get(2)));
            islandMap.put(d.get(0),tmp);
            ArrayList<Edge> tmp2 = islandMap.get(d.get(1));
            tmp2.add(new Edge(d.get(1),d.get(0),d.get(2)));
            islandMap.put(d.get(1),tmp2);
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight-o2.weight;
            }
        });
        set.add(1);
        pq.addAll(islandMap.get(1));
        while (!pq.isEmpty()){
            Edge min = pq.poll();
            if(!set.contains(min.to)){
                set.add(min.to);
                mst.add(min);
                pq.addAll(islandMap.get(min.to));
            }
        }
        int sum =0;
        for (Edge e : mst){
            sum+=e.weight;
        }
        return sum;
    }
}
