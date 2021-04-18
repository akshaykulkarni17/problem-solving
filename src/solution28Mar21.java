import java.util.*;
public class solution28Mar21 {

    public static void main(String[] args) {
        ArrayList<Integer> l1 = new ArrayList<>(Arrays.asList(-533, -666, -500, 169, 724, 478, 358, -38, -536, 705, -855, 281, -173, 961, -509, -5, 942, -173, 436, -609, -396, 902, -847, -708, -618, 421, -284, 718, 895, 447, 726, -229, 538, 869, 912, 667, -701, 35, 894, -297, 811, 322, -667, 673, -336, 141, 711, -747, -132, 547, 644, -338, -243, -963, -141, -277, 741, 529, -222, -684, 35));
        ArrayList<Integer> l2 = new ArrayList<>(Arrays.asList(756898537, -1973594324, -2038664370, -184803526, 1424268980));
        ArrayList<Integer> l3 = new ArrayList<>(Arrays.asList(377, 448, 173, 307, 108));
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        list.add(l1);
        list.add(l2);
        list.add(l3);
        int[] nums = {-1, 0, 1, 2, -1, -4};
        //System.out.println(solveBeggarsInTemple(5,list));
        //System.out.println(solvePickFromBothSides(l1,48));
        //System.out.println(solveSubsequenceAG("ABCGAG"));
        //System.out.println(maxset(l2));
        //System.out.println(solveClosestMinMax(l3));
        System.out.println(threeSum(nums));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) return new ArrayList<>(); // if nums less than 3 element
        Arrays.sort(nums); // sort array
        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) set.add(Arrays.asList(nums[i], nums[j++], nums[k--]));
                else if (sum > 0) k--;
                else if (sum < 0) j++;
            }

        }

        return new ArrayList<>(set);
    }

    public static int solveClosestMinMax(ArrayList<Integer> A) {
        int length = Integer.MAX_VALUE;
        int minIndex = Integer.MIN_VALUE;
        int maxIndex = Integer.MAX_VALUE;
        int min = Collections.min(A);
        int max = Collections.max(A);
        if (min == max) return 1;
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i) == min) {
                minIndex = i;
            }
            if (A.get(i) == max) {
                maxIndex = i;
            }
            if (minIndex != Integer.MIN_VALUE && maxIndex != Integer.MAX_VALUE) {
                length = Math.min(length, Math.abs(maxIndex - minIndex));
            }
        }
        return length + 1;
    }

    public static ArrayList<Integer> maxset(ArrayList<Integer> A) {
        long maxSum = Integer.MIN_VALUE;
        int startIndex = 0;
        int endIndex = 0;
        long sum = 0;
        int start = 0;
        if (A.size() == 1) {
            if (A.get(0) < 0) {
                A.remove(A.get(0));
            }
            return A;
        }
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i) < 0) {
                if (sum == maxSum) {
                    maxSum = sum;
                    if ((i - start > endIndex - startIndex) || (i - start == endIndex - startIndex && start < startIndex)) {
                        startIndex = start;
                        endIndex = i;
                    }
                }
                if (sum > maxSum) {
                    maxSum = sum;
                    startIndex = start;
                    endIndex = i;
                }
                start = i + 1;
                sum = 0;
            } else {
                if (A.get(i) >= 0) {
                    sum += A.get(i);
                }
            }
        }
        if (sum > maxSum) {
            maxSum = sum;
            startIndex = start;
            endIndex = A.size();
        }
        if (sum == maxSum) {
            maxSum = sum;
            if ((A.size() - start > endIndex - startIndex) || (A.size() - start == endIndex - startIndex && start < startIndex)) {
                startIndex = start;
                endIndex = A.size();
            }
        }
        return new ArrayList<>(A.subList(startIndex, endIndex));
    }

    public static int solveSubsequenceAG(String A) {
        long aCount = 0;
        long agCount = 0;
        for (char c :
                A.toCharArray()) {
            if (c == 'A') {
                aCount++;
            }
            if (c == 'G') {
                agCount += aCount;
            }
        }
        return (int) (agCount % 1000000007);
    }

    public static int solvePickFromBothSides(ArrayList<Integer> A, int B) {
        Stack<Integer> pop = new Stack<>();
        int sum = 0;
        for (int i = 0; i < B; i++) {
            sum += A.get(i);
            pop.push(A.get(i));
        }
        int max = sum;
        for (int i = A.size() - 1; i >= A.size() - B; i--) {
            sum -= pop.pop();
            sum += A.get(i);
            max = Math.max(max, sum);
        }
        return max;
    }

    public static ArrayList<Integer> solveBeggarsInTemple(int A, ArrayList<ArrayList<Integer>> B) {

        ArrayList<Integer> beggar = new ArrayList<>();
        for (int i = 0; i < A; i++) {
            beggar.add(0);
        }
        for (int i = 0; i < B.size(); i++) {
            int temp = beggar.get(B.get(i).get(0) - 1);
            temp += B.get(i).get(2);
            beggar.set(B.get(i).get(0) - 1, temp);
            if (B.get(i).get(1) < A) {
                int temp2 = beggar.get(B.get(i).get(1));
                temp2 += (-1 * B.get(i).get(2));
                beggar.set(B.get(i).get(1), temp2);
            }

        }
        int sum = 0;
        for (int i = 0; i < beggar.size(); i++) {
            sum += beggar.get(i);
            beggar.set(i, sum);
        }

        return beggar;
    }

    public static ArrayList<Integer> solveBeggarsInTempleON2(int A, ArrayList<ArrayList<Integer>> B) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < A; i++) {
            list.add(0);
        }
        for (int i = 0; i < B.size(); i++) {
            for (int j = B.get(i).get(0) - 1; j < B.get(i).get(1); j++) {
                int temp = list.get(j);
                temp += B.get(i).get(2);
                list.set(j, temp);
            }
        }
        return list;
    }
}
