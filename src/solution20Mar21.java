import java.util.*;
public class solution20Mar21 {

    public static void main(String[] args) {
        ArrayList<Integer> ll = new ArrayList<>(Arrays.asList(3, 30, 34, 5, 9));
        ArrayList<String> ss = new ArrayList<String>(Arrays.asList("aaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        //System.out.println(largestNumber(ll));
        //System.out.println(getLargest("ittmcsvmoa_jktvvblefw"));
        //System.out.println(longestCommonPrefix(ss));
        System.out.println(solveStringOperations("AbcaZeoB"));
    }

    public static String solveStringOperations(String A) {
        A = A.concat(A);
        char[] ch = A.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] - 'A' >= 0 && ch[i] - 'A' <= 26) {
                ch[i] = ' ';
            }
            if (ch[i] == 'a' || ch[i] == 'e' || ch[i] == 'i' || ch[i] == 'o' || ch[i] == 'u') {
                ch[i] = '#';
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c :
                ch) {
            if (c != ' ') {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String longestCommonPrefix(ArrayList<String> A) {
        if (A.size() == 1) return A.get(0);
        String s = new String();
        Collections.sort(A);
        if (A.get(0).length() == 1) {
            for (int i = 1; i < A.size(); i++) {
                if (!A.get(i).startsWith(A.get(0))) {
                    return "-1";
                }
            }
            return A.get(0);
        }
        boolean b = true;
        for (int i = 1; i <= A.get(0).length(); i++) {

            s = A.get(0).substring(0, i);
            for (int j = 1; j < A.size(); j++) {
                if (!A.get(j).startsWith(s)) {
                    b = false;
                    break;
                }
            }
            if (!b) break;
        }
        return b ? s : s.substring(0, s.length() - 1);
    }

    public static String getLargest(String A) {
        char[] s1 = A.split("_")[0].toCharArray();
        char[] s2 = A.split("_")[1].toCharArray();


        Arrays.sort(s2);
        int s2l = s2.length - 1;
        int s1l = 0;
        while (s2l >= 0 && s1l < s1.length) {
            if (s1[s1l] < s2[s2l]) {
                s1[s1l] = s2[s2l];
                s2l--;
            }

            s1l++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s1.length; i++) {
            sb.append(s1[i]);
        }
        return sb.toString();
    }

    public static String largestNumber(final List<Integer> A) {

        ArrayList<String> s = new ArrayList<>();
        for (int x : A
        ) {
            s.add(String.valueOf(x));
        }
        Collections.sort(s, (a, b) -> -1 * (a + b).compareTo(b + a));
        StringBuilder sb = new StringBuilder();
        for (String str : s) {
            sb.append(str);
        }
        return sb.toString().startsWith("0") ? "0" : sb.toString();
    }
}
