import java.util.*;

public class solution30Apr21 {
    public static void main(String[] args) {
        ArrayList<Integer> l = new ArrayList<>(Arrays.asList(756898537, -1973594324, -2038664370, -184803526, 1424268980 ));
        //System.out.println(MaxNonNegativeSubArray(l));
        int num=6;
        Integer.toHexString(num);
        //System.out.println(isSubsequence("abc","ahbgdc"));
        System.out.println(longestPalindrome("civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth"));
    }
    public static int longestPalindrome(String s) {
        Map<Character,Integer> m = new HashMap<>();
        for(char c:s.toCharArray()){
            if(m.containsKey(c)){
                m.put(c,m.get(c)+1);
            }
            else{
                m.put(c,1);
            }
        }
        int sum=0;
        boolean odd=false;
        for(int x: m.values()){
            if(x%2==0) sum+=x;
            if(x%2==1) {
                odd=true;
                sum+=x-1;
            }
        }
        return sum+(odd?1:0);
    }
    public static boolean isSubsequence(String s, String t) {
        int i=0;
        int j=0;
        if(t.length()<s.length()) return false;
        while(i<s.length()&&j<t.length()){
            if(s.charAt(i)==t.charAt(j)){
                i++;
            }
            j++;
        }
        if(i==s.length()) return true;
        return false;
    }
    public static ArrayList<Integer> MaxNonNegativeSubArray(ArrayList<Integer> A) {
        ArrayList<Integer> maxArray = new ArrayList<>();
        long maxSum=Integer.MIN_VALUE;
        ArrayList<Integer> array = new ArrayList<>();
        long sum=0;
        for (int x:
                A) {
            if (x>=0){
                sum+=x;
                array.add(x);
            }
            else{
                if (sum>maxSum){
                    maxSum=sum;
                    maxArray.clear();
                    maxArray.addAll(array);
                }
                sum=0;
                array.clear();
            }
        }
        if (sum>maxSum){
            maxArray.clear();
            maxArray.addAll(array);
        }
        return maxArray;
    }

    public static ArrayList<Integer> plusOne(ArrayList<Integer> A) {
        if (A.get(A.size()-1)<9){
            A.set(A.size()-1,A.get(A.size()-1)+1);
            while(A.get(0)==0){
                A.remove(0);
            }
            return A;
        }
        int index = A.size()-1;
        while(index>=0&&A.get(index)==9){
            A.set(index,0);
            index--;
        }
        if (index==-1){
            ArrayList<Integer> ans = new ArrayList<>();
            for (int i = 0; i <= A.size(); i++) {
                ans.add(0);
            }
            ans.set(0,1);
            return ans;
        }
        A.set(index,A.get(index)+1);
        while(A.get(0)==0){
            A.remove(0);
        }
        return A;
    }
//    |A[i] - A[j]| + |i - j|
//    A[i]>A[j]&&i>j    A[i]-A[j]+i-j = A[i]+i - (A[j]+j)   do A[i]+i > max-min
//    A[i]<A[j]&&i<j    -A[i]+A[j]-i+j =   (A[j]+j) - (A[i]+i)      do A[i]+i > max-min
//    A[i]<A[j]&&i>j    A[j]-A[i]+i-j =     A[j]-j - (A[i]-i)    do A[i]-i > max-min
//    A[i]>A[j]&&i<j    -A[j]+A[i]-i+j =      (A[i]-i) -  (A[j]-j )  do A[i]-i > max-min

    public int MaximumAbsoluteDifference(ArrayList<Integer> A) {
        ArrayList<Integer> plusIndex  = new ArrayList<>();
        ArrayList<Integer> minusIndex = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            plusIndex.add(A.get(i)+i);
        }
        for (int i = 0; i < A.size(); i++) {
            minusIndex.add(A.get(i)-i);
        }
        int plus= Collections.max(plusIndex)-Collections.min(plusIndex);
        int minus = Collections.max(minusIndex)-Collections.min(minusIndex);
        return Math.max(plus,minus);
    }
}
