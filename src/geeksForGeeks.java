import java.util.Arrays;
import java.util.*;

public class geeksForGeeks {

    public static void main(String[] args) {
    long[] arr1 = {468 ,335 ,1, 170, 225, 479, 359, 463 ,465 ,206, 146 ,282 ,328 ,462 ,492, 496 ,443, 328, 437, 392, 105 ,403, 154 ,293, 383 ,422 ,217 ,219 ,396 ,448 ,227 ,272 ,39, 370, 413 ,168 ,300 ,36 ,395, 204, 312, 323};
    int[] arr = {2,3,4,4,4,4,4,4,4,4,4,5,6,7,8};
    //System.out.println(countInversions2(arr));
    //System.out.println(removeDuplicates("aamfdsssg"));
       // System.out.println(generateParenthesis(8));
      System.out.println(searchInsertKeyInSortedArray(arr,6));
    }
    public static int searchInsertKeyInSortedArray(int[] nums, int target) {
        if (target <= nums[0]) return 0;
        if (target>nums[nums.length-1]) return nums.length;
        return binarySearchIndex(nums,0, nums.length,target);
    }
    public static int binarySearchIndex(int[] arr, int start, int end, int key){
        if (end>=start){
            int mid = (start+end)/2;
            if (key>arr[mid-1]&&key<=arr[mid]){
                return mid;
            }
            else if (key>arr[mid]){
                return binarySearchIndex(arr,mid+1,end,key);
            }
            else {
                return binarySearchIndex(arr,start,mid-1,key);
            }
        }
        return -1;
    }
    public static int[] searchRange(int[] nums, int target) {
        return new int[]{first(nums, 0, nums.length - 1, target), last(nums, 0, nums.length-1, target)};
    }
    private static int last(int[] nums, int start, int end, int target) {
        if (end>=start){
            int mid=(start+end)/2;
            if ((mid==nums.length-1||target<nums[mid+1])&&target==nums[mid]){
                return mid;
            }
            else if (target<nums[mid]){
                return last(nums,start,mid-1,target);
            }
            else {
                return last(nums,mid+1,end,target);
            }
        }
        return -1;
    }
    private static int first(int[] nums, int start, int end, int target) {
        if (end>=start){
            int mid=(start+end)/2;
            if ((mid==0 || target>nums[mid-1]) && target==nums[mid]){
                return mid ;
            }
            else if (target>nums[mid]){
                return first(nums,mid+1,end,target);
            }
            else {
                return first(nums,start,mid-1,target);
            }
        }
        return -1;
    }

    public static int searchInSortedRotatedArray(int[] nums, int target) {
        int pivot = pivotElement(nums,0,nums.length-1);

        if(pivot==-1) {
            return binarySearch(nums,0,nums.length-1,target);
        }
        else if(target<=nums[pivot]&&target>=nums[0]){
            return binarySearch(nums,0,pivot,target);
        }
        return binarySearch(nums,pivot+1,nums.length-1,target);
    }
    public static int pivotElement(int[] a, int i, int j){
        if(j<i) return -1;
        int mid = (i+j)/2;
        if (mid>i && a[mid-1]>a[mid]){
            return mid-1;
        }
        if (mid<j && a[mid]>a[mid+1]){
            return mid;
        }
        if(a[mid]<a[i]){
            return   pivotElement(a,i,mid-1);
        }
        else {
            return pivotElement(a,mid+1,j);
        }
    }
    public static int binarySearch(int[] a, int start, int end, int key){
        int mid=(start+end)/2;
        if(start==end&&key!=a[mid]) return -1;
        if(key==a[mid]) return mid;
        if(key<a[mid]){
            return binarySearch(a,start,mid,key);
        }
            return binarySearch(a,(mid+1),end, key);

    }
    public static List<String> generateParenthesis(int n) {
        ArrayList<String> list = new ArrayList();
        backtrack(list,"",0,0,n);
        return list;
    }
    public static void backtrack(ArrayList<String> list, String s, int open, int close, int n){
        if(s.length()==n*2){
            list.add(s);
            return;
        }
        if(open<n){
            backtrack(list,s+"(",open+1,close,n);
        }
        if(close<open){
            backtrack(list,s+")",open,close+1,n);
        }
    }
    static String removeDuplicates(String str) {
        Set<Character> set = new HashSet<>();
        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if(set.contains(ch[i])){
                ch[i]='\0';
            }
            else{
                set.add(ch[i]);
            }
        }
        return String.valueOf(ch);
    }
    public static int countInversions2(long[] arr){
        return mergeSort(arr,0, arr.length-1);

    }
    public static int mergeSort(long[] arr,int l,int r){
        int countInversion=0;
        if (r>l){

            int mid = (l+r)/2;
            countInversion+=mergeSort(arr,l,mid);
            countInversion+=mergeSort(arr,mid+1,r);
            countInversion+=mergeArray(arr,l,mid,r);
        }
        return countInversion;
    }
    public static int mergeArray(long[] arr, int l, int m, int r){
        long[] left = Arrays.copyOfRange(arr,l,m+1);
        long[] right = Arrays.copyOfRange(arr,m+1,r+1);
        int i=0,j=0,k=l,swap=0;
        while (i<left.length&&j<right.length){
            if (left[i]<right[j]){
                arr[k++]=left[i++];
            }
            else {
                arr[k++]=right[j++];
                swap+=((m+1)-(l+i));
            }
        }
        while (i<left.length){
            arr[k++]=left[i++];
        }
        while (j<right.length){
            arr[k++]=right[j++];
        }
        return swap;
    }
    public static int countInversions(long[] arr){
        int swap=0;
            for(int i=0; i<arr.length; i++){
                for(int j=i+1; j<arr.length; j++){
                    if(arr[i]>arr[j]){
                        swap++;
                    }
                }
            }
            return swap;
    }
}
