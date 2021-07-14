import java.util.ArrayList;
import java.util.Arrays;

public class segmentTrees {

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(5,4,5,7));
        ArrayList<Integer> q1 = new ArrayList<>(Arrays.asList(1, 2, 4));
        ArrayList<Integer> q2 = new ArrayList<>(Arrays.asList(0, 1, 2));
        ArrayList<Integer> q3 = new ArrayList<>(Arrays.asList(1, 1, 4));
        ArrayList<ArrayList<Integer>> B = new ArrayList<>();
        B.add(q1);B.add(q2);B.add(q3);
        System.out.println(RangeMinimumQuery(A,B));
    }


    static int segmentTree[];
    public static ArrayList<Integer> RangeMinimumQuery(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        int x = (int) Math.ceil(Math.log(A.size())/Math.log(2));
        int size = (int) (2 * Math.pow(2,x)-1);
        segmentTree=new int[size];
        constructSegmentTree(A,0,A.size()-1,0);
        ArrayList<Integer> ans = new ArrayList<>();
        for (ArrayList<Integer> query : B){
            if (query.get(0).equals(0)){
                update(0,A.size()-1,0,query.get(1)-1,query.get(2));
            }
            else if (query.get(0).equals(1)){
                ans.add(RMQ(0,A.size()-1,query.get(1)-1,query.get(2)-1,0));
            }
        }
        return ans;
    }

    private static void update(int start, int end, int index, int queryIndex, int value) {
        if (start==end){
            segmentTree[index]=value;
        }
        else {
            int mid = start+(end-start)/2;
            if (queryIndex<=mid) update( start,mid,2*index+1,queryIndex,value);
            else update(mid+1,end,2*index+2,queryIndex,value);
            segmentTree[index]=Math.min(segmentTree[2*index+1],segmentTree[2*index+2]);
        }
    }

    private static int RMQ(int start, int end, int left, int right, int index) {
        if (start>right || end<left) return Integer.MAX_VALUE;
        if (left<=start && right>=end) return segmentTree[index];
        int mid=start+(end-start)/2;
        return Math.min(RMQ(start,mid,left,right,2*index+1),RMQ(mid+1,end,left,right,2*index+2));
    }

    private static void constructSegmentTree(ArrayList<Integer> a, int start, int end, int index) {
        if (start==end){
            segmentTree[index]=a.get(start);
        }
        else {
            int mid = start + (end-start)/2;
            constructSegmentTree(a,start,mid,2*index+1);
            constructSegmentTree(a,mid+1,end,2*index+2);
            segmentTree[index]=Math.min(segmentTree[2*index+1],segmentTree[2*index+2]);
        }
    }


}
