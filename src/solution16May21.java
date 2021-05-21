import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class solution16May21 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(5, 17, 1000, 11));
        //minimumBribes(list);
        System.out.println(solveSixlets(list,4));
    }
    static int count;
    public static int solveSixlets(ArrayList<Integer> A, int B) {
        count=0;
        subset(A,0,0,0,B);
        return count;
    }
    public static void subset(ArrayList<Integer> A, int index,int sum,int length,int B){
        if (sum>1000) return;
        if(length==B){
            count++;
            return;
        }
        if(index==A.size()) return;
        subset(A,index+1,sum+A.get(index),length+1,B);
        subset(A,index+1,sum,length,B);
    }
    static boolean chaos = false;
    public static void minimumBribes(List<Integer> q) {
        // Write your code here
        int swaps = mergeSortCount(q,0,q.size()-1);
        if(chaos) System.out.println("Too chaotic");
        else System.out.println(swaps);
    }
    static int mergeSortCount(List<Integer> a, int l , int h){
        int count=0;
        if(l<h){
            int mid = l+(h-l)/2;
            count+= mergeSortCount(a,l,mid-1);
            count+=mergeSortCount(a,mid+1,h);
            count+=mergeCount(a,l,mid,h);
        }
        return count;
    }
    static int mergeCount(List<Integer> a, int l ,int mid, int h){
        List<Integer> left = a.subList(l,mid+1);
        List<Integer> right = a.subList(mid+1,h+1);
        int i=0,j=0,swap=0,k=l;
        while(i<left.size()&&j<right.size()){
            if(left.get(i)<right.get(j)){
                a.set(k,left.get(i));
                k++;i++;
            }
            else{
                a.set(k,right.get(j));
                k++;j++;
                int temp = (mid + 1) - (l + i);
                if(temp>2) chaos=true;
                swap+=temp;
            }
        }
        while(i<left.size()){
            a.set(k,left.get(i));
            k++;i++;
        }
        while(j<right.size()){
            a.set(k,right.get(j));
            k++;j++;
        }
        return swap;
    }
}
