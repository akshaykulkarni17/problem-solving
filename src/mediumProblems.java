import java.util.*;

public class mediumProblems {

    public static void main(String[] args) {
        //System.out.println(reverseBits(3221225472L));
        // System.out.println(NumberOfSetBits(3221225));
        //System.out.println(addBinaryStrings("111","111"));
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(3, 6, 8, 10, 15, 50));
        //System.out.println(SingleNumberII(xorlist));
        //System.out.println(solveLengthOfLongestConsecutiveOnes("111011101"));
        //System.out.println(twoSum(list,2));
        //System.out.println(solvePairsWithGivenXor(list,5));
        System.out.println(solvePermutationsOfAinB("aca", "acaa"));
    }

    public static int solvePermutationsOfAinB(String A, String B) {
        A.toLowerCase();
        B.toLowerCase();
        int[] frequencyA = new int[26];
        int[] frequencyB = new int[26];
        for (int i = 0; i < A.length(); i++) {
            frequencyA[A.charAt(i) - 'a'] += 1;
            frequencyB[B.charAt(i) - 'a'] += 1;
        }
        boolean isPermute = true;
        for (int i = 0; i < 26; i++) {
            if (frequencyA[i] != frequencyB[i]) isPermute = false;
        }
        int count = isPermute ? 1 : 0;
        for (int i = A.length(); i < B.length(); i++) {
            frequencyB[B.charAt(i) - 'a'] += 1;
            frequencyB[B.charAt(i - A.length()) - 'a'] -= 1;
            isPermute = true;
            for (int j = 0; j < 26; j++) {
                if (frequencyA[j] != frequencyB[j]) isPermute = false;
            }
            if (isPermute) count++;
        }
        return count;
    }

    public static int solvePairsWithGivenXor(ArrayList<Integer> A, int B) {
        Set<Integer> set = new HashSet<>();
        int count = 0;
        for (int i = 0; i < A.size(); i++) {
            if (set.contains(A.get(i) ^ B)) {
                count++;
            }
            set.add(A.get(i));
        }
        return count;
    }

    public static ArrayList<Integer> twoSumDuplicates(final List<Integer> A, int B) {

        int sum = B;
        int diff;
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        int size = A.size();
        int num;
        int index;
        ArrayList<Integer> res = new ArrayList<Integer>();
        ArrayList<Node> node = new ArrayList<>();

        for (int i = size - 1; i >= 0; i--) {
            num = A.get(i);
            diff = sum - num;

            if (hashMap.containsKey(diff)) {
                index = hashMap.get(diff);
                node.add(new Node(i + 1, index + 1));
            }

            hashMap.put(num, i);

        }

        if (node.size() > 0) {
            Collections.sort(node);
            res.add(node.get(0).left);
            res.add(node.get(0).right);
        }

        return res;

    }

    public static ArrayList<Integer> twoSum(final List<Integer> A, int B) {
        ArrayList<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        ArrayList<ArrayList<Integer>> indexes = new ArrayList<>();
        for (int i = A.size() - 1; i >= 0; i--) {
            int diff = B - A.get(i);
            int num = A.get(i);
            if (map.containsKey(diff)) {
                indexes.add(new ArrayList<>(Arrays.asList(i + 1, map.get(diff) + 1)));
            }
            map.put(num, i);
        }
        int index2 = Integer.MAX_VALUE;
        int index1 = Integer.MAX_VALUE;
        for (int i = 0; i < indexes.size(); i++) {
            if (indexes.get(i).get(1) == index2 && indexes.get(i).get(0) < index1) {
                index2 = indexes.get(i).get(1);
                index1 = indexes.get(i).get(0);
            }
            if (indexes.get(i).get(1) < index2) {
                index2 = indexes.get(i).get(1);
                index1 = indexes.get(i).get(0);
            }
        }
        if (index1 != index2) {
            list.add(index1);
            list.add(index2);
        }
        return list;
    }

    public static int solveLengthOfLongestConsecutiveOnes(String A) {
        int countLeft = 0;
        int longestOnes = Integer.MIN_VALUE;
        int countOnes = 0;
        if (!A.contains("0")) return A.length();
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) == '1') {
                countLeft++;
                countOnes++;
            }
            if (A.charAt(i) == '0') {
                int rightIndex = i + 1;
                int countRight = 0;
                while (rightIndex < A.length() && A.charAt(rightIndex) == '1') {
                    countRight++;
                    rightIndex++;
                }
                longestOnes = Math.max(longestOnes, countLeft + countRight);
                countLeft = 0;
            }
        }
        return countOnes > longestOnes ? longestOnes + 1 : longestOnes;
    }

    public static long reverseBits(long a) {
        StringBuilder sb = new StringBuilder(Long.toBinaryString(a));
        sb.reverse();
        while (sb.length() != 32) {
            sb.append("0");
        }
        return Long.parseLong(sb.toString(), 2);
    }

    public static int NumberOfSetBits(long a) {
        int count = 0;
        while (a != 0) {
            count += (a & 1) == 1 ? 1 : 0;
            a >>= 1;
        }
        return count;
    }

    public static String addBinaryStrings(String A, String B) {
//        long a = Long.parseLong(A,2);
//        long b = Long.parseLong(B,2);
//        return Long.toBinaryString(a+b);
        StringBuilder sb = new StringBuilder();
        int i = A.length() - 1;
        int j = B.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0) {
                sum += A.charAt(i) - '0';
            }
            if (j >= 0) {
                sum += B.charAt(j) - '0';
            }
            sb.append(sum % 2);
            carry = sum / 2;
            i--;
            j--;
        }
        if (carry != 0) sb.append('1');
        return sb.reverse().toString();
    }

    public static int SingleNumberII(final ArrayList<Integer> A) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int j = 0; j < A.size(); j++) {
                sum ^= A.get(j) & 1;
                A.set(j, A.get(j) >> 1);
            }
            sb.append(sum);
        }
        return Integer.parseInt(sb.reverse().toString(), 2);
    }

    static class Node implements Comparable<Node> {

        int left;
        int right;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }

        public int compareTo(Node o) {
            if (this.right < o.right)
                return -1;
            if (this.right > o.right)
                return 1;

            return Integer.compare(this.left, o.left);
        }

    }
}
