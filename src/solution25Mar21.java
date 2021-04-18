import java.util.*;
public class solution25Mar21 {

    public static void main(String[] args) {
        ArrayList<Integer> ll = new ArrayList<>(Arrays.asList(3, 0, 2, 1));
        //arrange(ll);
        //System.out.println(trailingZeroes(10));
        //System.out.println(convertToTitle(1350));
        System.out.println(uniquePaths(15, 9));
    }

    public static int uniquePaths(int A, int B) {
        int i = 0;
        int j = 0;
        return rec(i, j, A, B);
    }

    public static int rec(int i, int j, int k, int l) {
        if (i == k - 1 && j == l - 1) return 1;
        if (i >= k || j >= l) return 0;
        return rec(i, j + 1, k, l) + rec(i + 1, j, k, l);
    }

    public static String convertToTitle(int A) {
        HashMap<Integer, Character> map = new HashMap<>();
        for (char i = 'A'; i < 'Z'; i++) {
            map.put(i - 'A' + 1, i);
        }
        map.put(0, 'Z');
        StringBuilder sb = new StringBuilder();

        while (A > 0) {
            sb.append(map.get(A % 26));
            if (A % 26 == 0) A = A / 26 - 1;
            else A /= 26;
        }
        return sb.reverse().toString();
    }

    public static int trailingZeroes(int A) {
        int i = 1;
        int count = 0;
        while (A >= Math.pow(5, i)) {
            count += (int) (A / Math.pow(5, i));
            i++;
        }
        return count;
    }

    public static void arrange(ArrayList<Integer> a) {
        for (int i = 0; i < a.size(); i++) {
            a.set(i, a.size() * a.get(i));
        }
        for (int i = 0; i < a.size(); i++) {
            a.set(i, a.get(a.get(i) / a.size()) / a.size() + a.get(i));
        }
        for (int i = 0; i < a.size(); i++) {
            a.set(i, a.get(i) % a.size());
        }

    }
}
