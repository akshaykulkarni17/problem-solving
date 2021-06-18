import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class solution29May21 {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        list.sort(Comparator.comparing(a->a.get(0)));
        System.out.println(periodOfString("abcaabcaab"));
        //System.out.println(boringSubstring("ajafafgja"));
        //System.out.println(cyclicPermutations("0111111111","1111111101"));
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(8986143 ,-5026827, 5591744 ,4058312 ,2210051 ,5680315 ,-5251946 ,-607433, 1633303, 2186575 ));
        ArrayList<Integer> b = new ArrayList<>(Arrays.asList(40,30,20,10,40));
        //System.out.println(solver(a));
        //System.out.println(solvess("aaba"));
        //System.out.println(makeStringPalindromeLPS("AACECAAAA"));
    }
    public static int makeStringPalindromeLPS(String A) {
        StringBuilder sb = new StringBuilder();
        sb.append(A);
        String reverse = sb.reverse().toString();
        sb.reverse().append("$").append(reverse);
        int[] lps = lpsArray(sb.toString());
        return A.length()-lps[lps.length-1];
    }
    private static int[] lpsArray(String str) {
        int i=1,len=0;
        int n = str.length();
        int[] lps = new int[n];
        while (i<n){
            if (str.charAt(i)==str.charAt(len)){
                len++;
                lps[i]=len;
                i++;
            }
            else {
                if (len!=0){
                    len=lps[len-1];
                }
                else {
                    lps[i]=0;
                    i++;
                }
            }
        }
        return lps;
    }

    public static int makeStringPalindrome(String A) {
        if(isPalindrome(A)) return 0;
        int count=1;
        for (int i = A.length()-1; i >=0 ; i--) {
            if (isPalindrome(A.substring(0,i))) break;
            count++;
        }
        return count;
    }
    public static boolean isPalindrome(String s) {

        if (s.length() == 1) return true;
        if (s.length() == 2 && s.charAt(0) == s.charAt(1)) return true;
        if (s.charAt(0) != s.charAt(s.length() - 1)) return false;
        return isPalindrome(s.substring(1, s.length() - 1));
    }
    public static int solvess(String A) {
        int count=0;
        int sum=0;
        for(char c : A.toCharArray()){
            if(c=='a'){
                count++;
                sum+=count;
            }
        }
        return sum;
    }
    public static ArrayList<Integer> solver(ArrayList<Integer> A) {
        int len=0;
        int maxlen=Integer.MIN_VALUE;
        ArrayList<Integer> list = new ArrayList<>();
        int start=0;
        for(int i=0;i<A.size();i++){
            if(A.get(i)>=0){
                len++;
                if(len>maxlen){
                    maxlen=len;
                    list.clear();
                    if(start==i)list.addAll(A.subList(start,i+1));
                    else list.addAll(A.subList(start,i+1));
                }
            }
            else {
                len=0;start=i+1;
            }

        }
        return list;
    }
    public static int solve(ArrayList<Integer> A, ArrayList<Integer> B) {
        int min=Integer.MAX_VALUE;
        for(int i=0;i<A.size();i++){
            for(int j=i+1;j<A.size();j++){
                for(int k=j+1;k<A.size();k++){
                    if(A.get(i)<A.get(j)&&A.get(j)<A.get(k)){
                        min=Math.min(min,B.get(i)+B.get(j)+B.get(k));
                    }
                }
            }
        }
        return min;
    }

    public static int periodOfString(String A) {
        int n = A.length();
        int[] z = lpsArray(A);
        return n-z[n-1];
    }
    public static int cyclicPermutations2(String A, String B) {
        int i=1;
        int count=0;
        if(A.equals(B)) count++;
        while(i<A.length()){
            StringBuilder sb = new StringBuilder();
            sb.append(B.substring(i));
            sb.append(B, 0, i);
            if (A.equals(sb.toString())) count++;
            i++;
        }
        return count;
    }
    public static int cyclicPermutations(String A, String B) {
        B=B+B;
        B=B.substring(0,B.length()-1);
        String str = A + "$" + B;
        int[] z = lpsArray(str);
        int count =0;
        for (int x : z){
            if(x==A.length())  count++ ;
        }
        return count;
    }



    public static int boringSubstring(String A) {
        char[] temp = A.toCharArray();
        Arrays.sort(temp);
        String odd= "",even="";
        for (int i = 0; i <A.length() ; i++) {
            if (temp[i]%2==0) even=even+temp[i];
            else odd=odd+temp[i];
        }
        String str1 = odd+even;
        String str2 = even+odd;
        boolean boring1=false;
        boolean boring2=false;
        for (int i = 1; i < A.length(); i++) {
            if (Math.abs(str1.charAt(i)-str1.charAt(i-1))==1) boring1=true;
        }
        for (int i = 1; i < A.length(); i++) {
            if (Math.abs(str2.charAt(i)-str2.charAt(i-1))==1) boring2=true;
        }
        if (boring1&&boring2) return 0;
        return 1;
    }
    public String longestCommonPrefix(ArrayList<String> A) {
        Collections.sort(A);
        String s=A.get(0);
        int index=1;
        boolean not = false;
        while (index<=s.length()){
            for (String str : A){
                if(!str.startsWith(s.substring(0,index))) {
                    not=true;
                    break;
                }
            }
            if(!not){
                index++;
            }
            else break;
        }
        if (index==s.length()) return s;
        return s.substring(0,index-1);
    }
}
