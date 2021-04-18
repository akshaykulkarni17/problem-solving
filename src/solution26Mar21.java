import java.util.*;
public class solution26Mar21 {

    public static void main(String[] args) {
        //System.out.println(reverse(-123));
        //System.out.println(findRank("VIEW"));
        //System.out.println(uniquePaths(15,14));
        //System.out.println(solveMagicNumber(3));
        ArrayList<Integer> ll = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println(solveSumDivisibleByB(ll, 2));
    }

    public static int solveSumDivisibleByB(ArrayList<Integer> A, int B) {
        int count = 0;
        for (int i = 0; i < A.size(); i++) {
            for (int j = i + 1; j < A.size(); j++) {
                if ((A.get(i) + A.get(j)) % B == 0) count++;
            }
        }
        return count;
    }

    public static int solveMagicNumber(int A) {
        char[] ch = Integer.toBinaryString(A).toCharArray();
        int sum = 0;
        int pow = 1;
        for (int i = ch.length - 1; i >= 0; i--) {
            if (ch[i] == '1') {
                sum += Math.pow(5, pow);
            }
            pow++;
        }
        return sum;
    }

    public static int uniquePaths(int A, int B) {
        double tot = A + B - 2;
        double diff = Math.min(A - 1, B - 1);
        double ans = 1;
        for (int i = 0; i < diff; i++) {
            ans *= (tot - i) / (diff - i);
        }
        return (int) (Math.ceil(ans) - ans < ans - Math.floor(ans) ? Math.ceil(ans) : Math.floor(ans));
    }

    public static int findRank(String A) {
        A.toLowerCase();
        long len = A.length(), rank = 1;
        if (A.length() == 1) return (int) rank;
        for (int i = 0; i < A.length(); i++) {
            long smallerChar = 0;
            for (int j = i + 1; j < A.length(); j++) {
                if (A.charAt(i) - 'a' > A.charAt(j) - 'a') {
                    smallerChar++;
                }
            }
            rank += (smallerChar == 0 ? 0 : smallerChar * factorial(len - i - 1));
        }
        return (int) rank;
    }

    public static long factorial(long n) {
        return (n <= 1) ? 1 : (n * (factorial(n - 1)) % 1000003);
    }

    public static int findRankRecursive(String A) {
        if (A.length() == 1) return 1;
        ArrayList<String> list = new ArrayList<>();
        permuteRecursive(A, "", list);
        Collections.sort(list);
        return (list.indexOf(A) + 1) % 1000003;
    }

    public static void permuteRecursive(String s, String ans, ArrayList<String> list) {
        if (s.length() == 0) {
            list.add(ans);

        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            String rs = s.substring(0, i) + s.substring(i + 1);
            permuteRecursive(rs, ans + c, list);
        }
    }

    public static int reverse(int A) {
        StringBuilder s = new StringBuilder();
        s.append(A < 0 ? String.valueOf(-1 * A) : String.valueOf(A));
        s.reverse();
        long l = Long.parseLong(String.valueOf(s));
        l = A < 0 ? -1 * l : l;
        return l <= Integer.MAX_VALUE && l >= Integer.MIN_VALUE ? (int) l : 0;
    }

}
