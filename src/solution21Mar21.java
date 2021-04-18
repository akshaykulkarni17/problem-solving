import java.util.*;
public class solution21Mar21 {

    public static void main(String[] args) {

        ArrayList<Character> ll = new ArrayList<>(Arrays.asList('S', 'c', 'A', 'L', 'E', 'r', 'A', 'c', 'a', 'D', 'e', 'm', 'y'));
        //System.out.println(to_upper(ll));
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(9, 1, 2, 5, 9, 4));
        //System.out.println(solveMinimizeDifference(list,7));
        //System.out.println(solveStringRotate("scaler", 2));
        //System.out.println(solvePalindrome("abba"));
        //System.out.println(solveAmazingSubarrays("ABEC"));
        //System.out.println(solvebob("abobcbob"));
        //System.out.println(solveChangeCharacter("abcabbccd" , 3));
        System.out.println(recursivePalindrome("malayalammalayalammalayalammalayalammalayalammalayalam"));
    }

    public static int recursivePalindrome(String A) {
        if (A.length() == 1) return 1;
        else if (A.charAt(0) == A.charAt(1) && A.length() == 2) {
            return 1;
        } else if (A.charAt(0) != A.charAt(A.length() - 1)) {
            return 0;
        }
        return recursivePalindrome(A.substring(1, A.length() - 1));
    }

    public static int solveChangeCharacter(String A, int B) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (char c :
                A.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else map.put(c, 1);
        }
        char max = A.charAt(0);
        for (char x :
                map.keySet()) {
            if (map.get(x) > map.get(max)) {
                max = x;
            }
        }
        char[] ch = A.toCharArray();
        ArrayList<Integer> l = new ArrayList<>(map.values());
        Collections.sort(l);
        for (int x :
                l) {
            for (int i = 0; i < ch.length; i++) {
                if (map.containsKey(ch[i]) && map.get(ch[i]) == x) {
                    while (map.containsKey(ch[i]) && B > 0) {
                        if (map.get(ch[i]) == 1) {
                            map.remove(ch[i]);
                            B--;
                            break;
                        } else {
                            map.put(ch[i], map.get(ch[i]) - 1);
                            B--;
                        }

                    }

                }
                if (B <= 0) break;
            }
            if (B <= 0) break;
        }
        return map.keySet().size();
    }

    public static int solvebob(String A) {
        int i = 0;
        int j = 3;
        int count = 0;
        if (A.length() < 3) return 0;
        while (j <= A.length()) {
            if (A.substring(i, j).equals("bob")) {
                count++;
            }
            i++;
            j++;
        }
        return count;
    }

    public static int solveAmazingSubarrays(String A) {
        int count = 0;
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) == 'a' || A.charAt(i) == 'A' || A.charAt(i) == 'e' || A.charAt(i) == 'E' || A.charAt(i) == 'i' || A.charAt(i) == 'I' || A.charAt(i) == 'o' || A.charAt(i) == 'O' || A.charAt(i) == 'u' || A.charAt(i) == 'U') {
                count += A.length() - i;
            }
        }
        return count % 10003;
    }

    public static String solvePalindrome(String A) {
        int start = 0;
        int end = A.length() - 1;
        int countMismatch = 0;
        while (start < end) {
            if (A.charAt(start) != A.charAt(end)) {
                countMismatch++;
            }
            start++;
            end--;
        }
        if (countMismatch == 1) return "YES";
        if (countMismatch == 0 && A.length() % 2 == 1) return "YES";
        return "NO";

    }

    public static String solveStringRotate(String A, int B) {
        B = B % A.length();
        int index = A.length() - B;
        char[] ch = A.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = index; i < A.length(); i++) {
            sb.append(ch[i]);
        }
        for (int i = 0; i < index; i++) {
            sb.append(ch[i]);
        }
        return sb.toString();
    }

    public static int solveMinimizeDifference(ArrayList<Integer> A, int B) {
        int min = Collections.min(A);
        int max = Collections.max(A);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x :
                A) {
            if (map.containsKey(x)) {
                map.put(x, map.get(x) + 1);
            } else {
                map.put(x, 1);
            }
        }
        while (min < max && B != 0) {
            if (map.get(min) < map.get(max)) {
                if (B > 0) {
                    if (map.containsKey(min + 1)) {
                        map.put(min + 1, map.get(min + 1) + map.get(min));
                    } else {
                        map.put(min + 1, map.get(min));
                    }
                    B -= map.get(min);
                    if (B < 0) break;
                    min = min + 1;
                }
            } else {
                if (B > 0) {
                    if (map.containsKey(max - 1)) {
                        map.put(max - 1, map.get(max) + map.get(max - 1));
                    } else {
                        map.put(max - 1, map.get(max));
                    }
                    B -= map.get(max);
                    if (B < 0) break;
                    max = max - 1;
                }
            }
        }
        return max - min;
    }

    public static ArrayList<Character> to_upper(ArrayList<Character> A) {
        for (int i = 0; i < A.size(); i++) {

            A.set(i, Character.toUpperCase(A.get(i)));

        }
        return A;
    }
}
