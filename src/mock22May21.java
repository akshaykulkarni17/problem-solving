import java.util.*;

public class mock22May21 {
    public static void main(String[] args) {
    List<Integer> l = new ArrayList<>(Arrays.asList(11,2,3,1,5,13,7,6,5));
    System.out.println(getMinimumSecondsIJ(l,15,2,3));
    }
    public static int getMinimumSecondsIJ(List<Integer> height, int h, int u, int v) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        Collections.sort(height);
        int i=0;
        Set set = new TreeSet();
        set.add(1);
        set.add("2");
        int j=height.size()-1;
        while(i<j){
            if (height.get(i)!=-1&&height.get(j)!=-1&&height.get(i)+height.get(j)<h){
                list.add(new ArrayList<>(Arrays.asList(height.get(i),height.get(j))));
                height.set(i,-1);
                height.set(j,-1);
                i++;
            }
            j--;
        }
        int time=0;
        for (int x : height){
            if(x!=-1) time+=u;
        }
        return time+list.size()*v;
    }
    public static int getMinimumSeconds(List<Integer> height, int h, int u, int v) {
        int[] arr = new int[height.size()];
        Collections.sort(height);
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==0){
                int temp = binarySearch(height,height.get(i),h,i+1,height.size()-1,-1,arr);
                if(temp==-1) continue;
                else{
                    height.set(i, height.get(i)+height.get(temp));
                    arr[i]++;
                    arr[temp]--;
                }
            }
        }
        int time=0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==0) time+=u;
            if(arr[i]==1) time+=v;
        }
        return time;
    }
    static int binarySearch(List<Integer> height,int num,int h, int low, int high,int answer,int[] arr) {
        if(low<=high){
            int mid =low +(high-low)/2;
            if(height.get(mid)+num>=h){
                return binarySearch(height, num, h, low, mid-1,answer,arr);
            }
            else{
                if(arr[mid]!=-1) answer=mid;
                 return binarySearch(height, num, h, mid+1, high,answer,arr);
            }
        }
        return answer;
    }
    public static int subArrayWithMinMaxDiffLessThanK(ArrayList<Integer> A, int k){
        if (Collections.max(A)-Collections.min(A)<k) return A.size();
        int i=0;
        int j=i+1;
        int max=Integer.MIN_VALUE;
        while (j<A.size()){
            if (Collections.max(A.subList(i,j+1))-Collections.min(A.subList(i,j+1))<k){
                max=Math.max(max,j-i+1);
            }
            else {
                i++;
            }
            j++;
        }
        return max;
    }
}
