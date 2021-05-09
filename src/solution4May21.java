import java.util.*;

public class solution4May21 {

    public static void main(String[] args) {
        //ArrayList<Integer> list = new ArrayList<>(Arrays.asList(93, 9, 46, 79, 56, 24, 10, 26, 9, 93, 31, 93, 75, 7, 4, 80, 19, 67, 49, 84, 62, 100, 17, 40, 35, 84, 14, 81, 99, 31, 100, 66, 70, 2, 11, 84, 60, 89, 13, 57, 47, 60, 59, 60, 42, 67, 89, 29, 85, 83, 42, 47, 66, 80, 88, 85, 83, 82, 16, 23, 21, 55, 26, 2, 21, 92, 85, 26, 46, 3, 7, 95, 50, 22, 84, 52, 57, 44, 4, 23, 25, 55, 41, 49 ));
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,1));
        //System.out.println(deleteOne(list));
        //System.out.println(addStrings("1234","98996"));
        //System.out.println(solvePubg(list));
        //System.out.println(CountOfDivisors(list));
        //System.out.println(solve(list,3));
        //System.out.println(solvePrimeModuloInverse(3,7));

    }
    public static int solvePrimeModuloInverse(int A, int B) {
        return power(A,B-2,B);
    }

    static int power(int x, int y, int m)
    {
        if (y == 0)
            return 1;
        int p = power(x, y / 2, m) % m;
        p = (int)((p * (long)p) % m);
        if (y % 2 == 0)
            return p;
        else
            return (int)((x * (long)p) % m);
    }
    public static int solve(ArrayList<Integer> A, int B) {
        Map<Integer,Integer> map = new HashMap<>();
        int count=0;
        for(int x:A){
            int temp=x%B;
            if(map.containsKey((B-temp)%B)){
                count+=map.get((B-temp)%B);
            }
            if (map.containsKey(temp)){
                map.put(temp,map.get(temp)+1);
            }
            else map.put(temp,1);
        }
        return count;
    }
    public static ArrayList<Integer> CountOfDivisors(ArrayList<Integer> A) {
        ArrayList<Integer> answer = new ArrayList<>();
        for (int x:   A) {
            int count=0;
            int i;
            for ( i = 1; i*i<x ; i++) {
                if(x%i==0)count+=2;
            }
            if (i*i==x) count++;
            answer.add(count);
        }
        return answer;
    }
    public static int deleteOne(ArrayList<Integer> A) {
        int gcd =0;
        int[] leftGcd = new int[A.size()];
        for (int i = 0; i < A.size(); i++) {
            gcd=gcd(A.get(i),gcd);
            leftGcd[i]=gcd;
        }
        gcd=0;
        int[] rightGcd = new int[A.size()];
        for (int i = A.size()-1; i >=0 ; i--) {
            gcd=gcd(A.get(i),gcd);
            rightGcd[i]=gcd;
        }
        int[] gcdWithoutNumber = new int[A.size()];
        for (int i = 0; i < A.size(); i++) {
            if (i==0){
                gcdWithoutNumber[i]=rightGcd[i+1];
            }
            else if (i==A.size()-1){
                gcdWithoutNumber[i]=leftGcd[A.size()-2];
            }
            else {
                gcdWithoutNumber[i]=gcd(leftGcd[i-1],rightGcd[i+1]);
            }
        }
        int max=Integer.MIN_VALUE;
        for (int x:gcdWithoutNumber) {
            max=Math.max(max,x);
        }
        return max;
    }
    public static int solvePubg(ArrayList<Integer> A) {
        int gcd=0;
        for(int x : A){
            gcd=gcd(x,gcd);
        }
        return gcd;
    }
    public static String addStrings(String num1, String num2) {
        int i =num1.length()-1;
        int j =num2.length()-1;
        int carry=0;
        StringBuilder s = new StringBuilder();
        while(i>=0||j>=0){
            int sum=carry;
            if(i>=0){
                sum+=Integer.parseInt(String.valueOf(num1.charAt(i)));
            }
            if (j>=0){
                sum+=Integer.parseInt(String.valueOf(num2.charAt(j)));
            }
            s.append(sum%10);
            carry=sum/10;
            i--;j--;
        }
        if(carry>0)s.append(carry);
        return s.reverse().toString();
    }
    public static int gcd (int a , int b){
        if (b==0){
            return a;
        }
        return gcd(b,a%b);
    }
}
