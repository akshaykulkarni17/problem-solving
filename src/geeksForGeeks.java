import java.util.*;

public class geeksForGeeks {

     //Definition for singly-linked list.
      public static class ListNode {
          int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

    public static void main(String[] args) {
    long[] arr1 = {468 ,335 ,1, 170, 225, 479, 359, 463 ,465 ,206, 146 ,282 ,328 ,462 ,492, 496 ,443, 328, 437, 392, 105 ,403, 154 ,293, 383 ,422 ,217 ,219 ,396 ,448 ,227 ,272 ,39, 370, 413 ,168 ,300 ,36 ,395, 204, 312, 323};
    int[] arr = {3,9,9};
    ListNode head = new ListNode(1);
    head=head.next;
    //deleteDuplicates(head);
     String []   strs = {"flower","flow","flight"};
    //System.out.println(countInversions2(arr));
    //System.out.println(removeDuplicates("aamfdsssg"));
       // System.out.println(generateParenthesis(8));
      //System.out.println(combinationSum(arr,2));
        //System.out.println(plusOne(arr));
        //System.out.println(longestCommonPrefix(strs));
        int[] prices={9,6,4,2,3,5,7,0,1};
        //System.out.println(maxProfit(prices));
        //System.out.println(isHappy(2));
        ArrayDeque<Integer> deck = new ArrayDeque<>();
        deck.addFirst(3);
        deck.pop();deck.peekFirst();
        //System.out.println(countPrimes(100));
        //System.out.println(missingNumber(prices));
        //System.out.println(reverseVowels("hello"));
        //System.out.println(isUgly(121));
        //System.out.println(simplifyPath("/./.././ykt/xhp/nka/eyo/blr/emm/xxm/fuv/bjg/./qbd/./../pir/dhu/./../../wrm/grm/ach/jsy/dic/ggz/smq/mhl/./../yte/hou/ucd/vnn/fpf/cnb/ouf/hqq/upz/akr/./pzo/../llb/./tud/olc/zns/fiv/./eeu/fex/rhi/pnm/../../kke/./eng/bow/uvz/jmz/hwb/./././ids/dwj/aqu/erf/./koz/.."));
        System.out.println(minAmplitude2(new int[]{-1,3,-1,5,4,8}));
      }

    private static int minAmplitude2(int[] nums) {
        if(nums.length <= 4) return 0;
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>();
        PriorityQueue<Integer> minQueue = new PriorityQueue<>(Collections.reverseOrder());
        for(int n : nums) {
            maxQueue.add(n);
            if(maxQueue.size() > 4) maxQueue.poll();
            minQueue.add(n);
            if(minQueue.size() > 4) minQueue.poll();
        }
        List<Integer> maxList = new ArrayList<>();
        while(maxQueue.size() > 0) maxList.add(maxQueue.poll());
        List<Integer> minList = new ArrayList<>();
        while(minQueue.size() > 0) minList.add(minQueue.poll());
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i <= 3; i++) {
            ans = Math.min(ans, maxList.get(i) - minList.get(3-i));
        }
        return ans;
    }

    public static String reverseVowels(String s) {

        int i=0;
        int j=s.length()-1;
        char[] ch = s.toCharArray();
        while(i<j){
            while(ch[i]!='a'&&ch[i]!='A'&&ch[i]!='e'&&ch[i]!='E'&&ch[i]!='I'&&ch[i]!='i'&&ch[i]!='O'&&ch[i]!='o'&&ch[i]!='U'&&ch[i]!='u'){
                i++;
            }
            while(ch[j]!='a'&&ch[j]!='A'&&ch[j]!='e'&&ch[j]!='E'&&ch[j]!='I'&&ch[j]!='i'&&ch[j]!='O'&&ch[j]!='o'&&ch[j]!='U'&&ch[j]!='u'){
                j--;
            }
            if (i<j){
                char temp=ch[i];
                ch[i]=ch[j];
                ch[j]=temp;
                i++;
                j--;
            }
        }
        return String.valueOf(ch);
    }
    public static int missingNumber(int[] nums){
        for(int i=0;i<nums.length;i++){
             if(nums[i]!=i&&nums[i]!=nums.length){
                int temp2= nums[nums[i]];
                nums[nums[i]]=nums[i];
                nums[i]=temp2;
                i--;
            }
        }
        for(int i=0;i<nums.length;i++){
            if(nums[i]==nums.length) return i;
        }
        return nums.length;
    }
    public static boolean isUgly(int n) {

        for(int p5=0;Math.pow(5,p5)<=n;p5++){
            for(int p3=0;Math.pow(3,p3)<=n;p3++){
                for(int p2=0;Math.pow(2,p2)<=n;p2++){
                    if(Math.pow(5,p5)*Math.pow(3,p3)*Math.pow(2,p2)==n) return true;
                }
            }
        }
        return false;
    }
    public static List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        int begin=0;
        for(int i=0;i<nums.length-1;i++){
            if(nums[i+1]!=nums[i]+1){
                if(i>begin){
                    list.add(new String(nums[begin]+"->"+nums[i]));
                }
                else{
                    list.add(new String(String.valueOf(nums[begin])));
                }
                begin=i+1;
            }
        }
        if(nums.length-1>begin){
            list.add(new String(nums[begin]+"->"+nums[nums.length-1]));
        }
        if(nums.length-1==begin){
            list.add(new String(String.valueOf(nums[begin])));
        }
        return list;
    }
    public static String simplifyPath(String A) {
        String[] ch =A.split("/");
        StringBuilder sb = new StringBuilder();
        Deque<String> stk = new ArrayDeque<>();
        for(int i=0;i<ch.length;i++){
            if(!stk.isEmpty()&&ch[i].equals("..")){
                stk.pollFirst();
            }
            else if(ch[i].matches("[a-zA-Z]+")){
                stk.push(ch[i]);
            }
        }
        if(stk.isEmpty()) return "/";
        while(!stk.isEmpty()){
            sb.append("/");
            sb.append(stk.pollLast());
        }
        return sb.toString();

    }
    public static int countPrimes(int n) {
        boolean[] primes = new boolean[n];
        int count=0;
        for(int i=2;i<Math.sqrt(n);i++){
            if(!primes[i]){
                count++;
                for(int j=i;i*j<n;j++){
                    primes[i*j]=true;
                }
            }
        }
        for (int i = (int) Math.sqrt(n); i < n; i++) {
            if(!primes[i])
                count++;
        }
        return count;
    }
    public static int countPrimesN2(int n) {
        int count=0;
        if(n<=2) return count;
        for(int i=2; i<n; i++){
            if(i==2||i==3) count++;
            else count+= isPrime(i) ? 1 : 0;
        }
        return count;
    }
    public static boolean isPrime(int n){
        for(int i=2;i<=Math.sqrt(n);i++){
            if(n%i==0) return false;
        }
        return true;
    }
    static Set<Integer> set = new HashSet<>();
    public static boolean isHappy(int n) {
        int sum = squareSum(n);
        if(sum==1) return true;
        if(set.contains(sum)) return false;
        set.add(sum);
        return isHappy(sum);
    }
    public static int squareSum(int n){
        int sum =0;
        while(n>0){
            sum+=((n%10) * (n%10));
            n/=10;
        }
        return sum;
    }
    public static int maxProfit(int[] prices) {
        int buy = 0;
        int sell = 0;
        return profit(prices,buy,sell);
    }
    public static int profit(int[] prices, int buy, int sell){
        buy=sell;
        while(buy<prices.length-1&&prices[buy]>prices[buy+1]){
            buy++;
        }
        sell=buy;
        while(sell<prices.length-1&&prices[sell]<prices[sell+1]){
            sell++;
        }
        return (prices[sell]-prices[buy]) + (buy==sell ? 0 : profit(prices,buy,sell));
    }
    public static String longestCommonPrefix(String[] strs) {
        int minLength=Integer.MAX_VALUE;
        String minString = new String();
        for (String s:
             strs) {
            if (s.length()<minLength){
                minLength=s.length();
               minString=s;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < minString.length(); i++) {
            boolean commonPrefix =true;
            for (String s:
                 strs) {
                if(minString.charAt(i)!=s.charAt(i)) commonPrefix=false;
            }
            if(commonPrefix) sb.append(minString.charAt(i));
            else break;
        }
        return sb.toString();
    }
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
        ListNode ans = new ListNode();
        while(current.next!=null){
            if(current.val!=current.next.val){
                ans.next=current;
                ans=ans.next;
            }
            current=current.next;
        }
        return ans;
    }
    public static int[] plusOne(int[] digits) {
        if(digits[digits.length-1]<9){
            digits[digits.length-1]=digits[digits.length-1]+1;
        }
        else{
            int i = digits.length-1;
            while (i>=0&&digits[i]==9){
                digits[i]=0;
                i--;
            }
            if (i==-1){
                int[] ans = new int[digits.length+1];
                ans[0]=1;
                return ans;
            }
            else {
                digits[i]=digits[i]+1;
            }
        }
        return digits;
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode l = new ListNode();
        ListNode current = l;
        while (l1!=null&&l2!=null){
            if(l1.val< l2.val){
                current.next=l1;
                current=current.next;
                l1=l1.next;
            }
            else{
                current.next=l2;
                current=current.next;
                l2=l2.next;
            }
        }
        while(l1!=null){
            current.next=l1;
            current=current.next;
            l1=l1.next;
        }
        while(l2!=null){
            current.next=l1;
            current=current.next;
            l1=l1.next;
        }
        return l;
    }
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Set<List<Integer>> list = new HashSet<>();
        Set<Integer> set = new HashSet<>();
        for (int x: candidates) {
            set.add(x);
        }
        for (int x: set) {
            if (x==target) list.add(new ArrayList<>(Arrays.asList(x)));
            int multiplier=1;
            while(target>x*multiplier){
                if (set.contains(target-(x*multiplier))){
                    ArrayList<Integer> sum = new ArrayList<>();
                    for (int i = 0; i < multiplier; i++) {
                        sum.add(x);
                    }
                    sum.add(target-(x*multiplier));
                    Collections.sort(sum);
                    list.add(sum);
                }
                multiplier++;
            }
        }
        List<List<Integer>> answer = new ArrayList<>();
        for (List<Integer> l: list) {
            answer.add(l);
        }
        answer.sort(List::indexOf);
        return answer;
    }
    public static int searchInsertKeyInSortedArray(int[] nums, int target) {
        if (target <= nums[0]) return 0;
        if (target>nums[nums.length-1]) return nums.length;
        return binarySearchIndex(nums,0, nums.length-1,target);
    }
    public static int binarySearchIndex(int[] arr, int start, int end, int key){
        if (end>=start){
            int mid = (start+end)/2;
            if ((mid==0||key>arr[mid-1])&&key<=arr[mid]){
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
