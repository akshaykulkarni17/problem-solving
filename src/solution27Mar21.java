import java.util.*;
public class solution27Mar21 {

    public static void main(String[] args) {
        ArrayList<Integer> ll = new ArrayList<>(Arrays.asList(2, 4, 6, 8, 10));
        int[] nums1 = {1, 3};
        int[] nums2 = {2, 4};
        //System.out.println(findMedianSortedArrays(nums1,nums2));
        //System.out.println(solvePairSumDivisibleByM(ll,2));
        //System.out.println(longestPalindrome("cnnd"));
        //System.out.println(convertZigzag("PAYPALISHIRING",3));
        System.out.println(myAtoi("abc 756"));
    }

    public static int myAtoi(String s) {
        int ascii = s.trim().toUpperCase().charAt(0);
        if (ascii >= 65 && ascii <= 90) return 0;
        String str = s.replaceAll("[^0-9]", "");
        long value = (s.contains("-") ? -1 : 1) * Long.parseLong(str);
        if (value > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (value < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return (int) value;

    }

    public static String convertZigzag(String s, int numRows) {
        List<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            list.add(new StringBuilder());
        }
        if (numRows == 1 || numRows > s.length()) return s;
        int currentrow = 0;
        boolean goingdown = false;
        for (char c :
                s.toCharArray()) {
            list.get(currentrow).append(c);
            if (currentrow == 0 || currentrow == numRows - 1) goingdown = !goingdown;
            currentrow += goingdown ? 1 : -1;
        }
        StringBuilder ans = new StringBuilder();
        for (StringBuilder ss :
                list) {
            ans.append(ss);
        }
        return ans.toString();
    }

    public static String longestPalindrome(String s) {
        int start = 0;
        int end = 0;
        if (isPalindrome(s)) return s;
        for (int i = 0; i < s.length(); i++) {
            for (int left = i, right = i; left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right); --left, ++right) {
                if (right - left > end - start) {
                    start = left;
                    end = right;
                }
            }
            for (int left = i, right = i + 1; left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right); --left, ++right) {
                if (right - left > end - start) {
                    start = left;
                    end = right;
                }
            }
        }

        return s.substring(start, end + 1);
    }

    public static boolean isPalindrome(String s) {

        if (s.length() == 1) return true;
        if (s.length() == 2 && s.charAt(0) == s.charAt(1)) return true;
        if (s.charAt(0) != s.charAt(s.length() - 1)) return false;
        return isPalindrome(s.substring(1, s.length() - 1));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] list = new int[nums1.length + nums2.length];
        for (int i = 0; i < list.length; ) {
            for (int x :
                    nums1) {
                list[i] = x;
                i++;
            }
            for (int x :
                    nums2) {
                list[i] = x;
                i++;
            }
        }
        Arrays.sort(list);
        return list.length % 2 == 1 ? (double) (list[list.length / 2]) : (double) (list[list.length / 2] + list[(list.length / 2) - 1]) / 2;
    }

    public static int solvePairSumDivisibleByM(ArrayList<Integer> A, int B) {
        HashMap<Long, Long> map = new HashMap<>();
        long pairs = 0;
        long divisor = B;
        for (int i = 0; i < A.size(); i++) {
            long mod = A.get(i) % divisor;
            if (map.containsKey((divisor - mod) % divisor)) {
                pairs += map.get((divisor - mod) % divisor);
                pairs %= 1000000007L;
            }
            if (map.containsKey(mod)) {
                map.put(mod, map.get(mod) + 1L);
            } else {
                map.put(mod, 1L);
            }
        }
        return (int) pairs;
    }

}
