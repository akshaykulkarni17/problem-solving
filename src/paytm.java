import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class paytm {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 1, 3, 5, 7, 9, 1};
        //System.out.println(countFrequencies(arr));
        TreeNode root = new TreeNode(20);
        root.left = new TreeNode(8);
        root.right = new TreeNode(22);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(25);
        root.left.right.left = new TreeNode(10);
        root.left.right.right = new TreeNode(14);
        //System.out.println(bottomView(root));
        //System.out.println(NDigitNumbers(1000,10000));
        System.out.println(verticalOrderTraversal(root));
    }

    public int PalindromePartitioningII(String A) {
        return minimumCut(A,0,A.length()-1);
    }
    private int minimumCut(String s, int i, int j) {
       if (i>=j || isPalindrome(s,i,j)) return 0;
       int ans = Integer.MAX_VALUE;
       for (int k=i;k<j;k++){
           int count = minimumCut(s,k+1,j) + minimumCut(s,i,k) +1;
           ans=Math.min(count,ans);
       }
       return ans;
    }

    private boolean isPalindrome(String s, int i, int j) {
        while (i<j){
            if(s.charAt(i)!=s.charAt(j)) return false;
            i++;j--;
        }
        return true;
    }


    static int[] countFrequencies(int[] arr){
        int i=0;
        while(i<arr.length){
            if (arr[i]<=0) {
                i++;
                continue;
            }
            int elementIndex = arr[i]-1;
            if (arr[elementIndex]>0){
                arr[i]=arr[elementIndex];
                arr[elementIndex]=-1;
            }
            else {
                arr[elementIndex]--;
                arr[i]=0;
                i++;
            }
        }
        return arr;
    }

    static int[] bottomView(TreeNode node){
        TreeMap<Integer,TreeMap<Integer,Integer>> treeMap = new TreeMap<>();
        bottomViewUtil(node,treeMap,0,0);
        int[] bv = new int[treeMap.size()];
        int i=0;
        for (int x : treeMap.keySet()){
            bv[i]= treeMap.get(x).firstEntry().getValue();
            i++;
        }
        return bv;
    }

    private static void bottomViewUtil(TreeNode node, TreeMap<Integer, TreeMap<Integer, Integer>> treeMap, int x,int y) {
        if(node==null) return;
        TreeMap t;
        if(treeMap.containsKey(x)){
            t = treeMap.get(x);
        }
        else{
            t = new TreeMap();
        }
        t.put(y,node.val);
        treeMap.put(x,t);
        bottomViewUtil(node.left,treeMap,x-1,y-1);
        bottomViewUtil(node.right,treeMap,x+1,y-1);
    }

    static TreeMap<Integer,TreeMap<Integer,ArrayList<Integer>>> treeMap;
    public static ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode A) {
        treeMap=new TreeMap<>();
        verticalViewUtil(A,0,0);
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int x: treeMap.keySet()){
            ArrayList<Integer> l = new ArrayList<>();
            for (int y: treeMap.get(x).keySet()){
                l.addAll(treeMap.get(x).get(y));
            }
            list.add(l);
        }
        return list;
    }

    private static void verticalViewUtil(TreeNode a, int x, int y) {
        if (a==null) return;
        if (treeMap.containsKey(x)){
            if (treeMap.get(x).containsKey(y)){
                ArrayList<Integer> temp = treeMap.get(x).get(y);
                temp.add(a.val);
                treeMap.get(x).put(y,temp);
            }
            else treeMap.get(x).put(y,new ArrayList<>(Collections.singleton(a.val)));
        }
        else {
            TreeMap<Integer,ArrayList<Integer>> t = new TreeMap<>();
            t.put(y,new ArrayList<>(Collections.singleton(a.val)));
            treeMap.put(x,t);
        }
        verticalViewUtil(a.left,x-1,y+1);
        verticalViewUtil(a.right,x+1,y+1);
    }


    public static int NDigitNumbers(int A, int B) {
        int start = (int) Math.pow(10,A-1);
        int end = (int) (Math.pow(10,A)-1);
        int count = 0;
        while (start<end){
            int curr=0;
            int temp=start;
            while (temp!=0){
                curr+=temp%10;
                temp/=10;
            }
            if (curr==B) {
                count++;
                count%=1000000007;
                start+=9;
            }
            else start++;
        }
        return count;
    }

}
