import java.util.*;
public class solution3Apr21 {


    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(-8, -7, -6, 1, 2, 3));
        System.out.println(firstMissingPositive(list));
        //System.out.println(solveSumTheDifference(list));
    }

    public static int firstMissingPositive(ArrayList<Integer> A) {

        for (int i = 0; i < A.size(); i++) {
            if (A.get(i) > 0 && A.get(i) <= A.size()) {

                if (!A.get(A.get(i) - 1).equals(A.get(i))) {
                    Collections.swap(A, A.get(i) - 1, i);
                    i--;
                }
            }
        }
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i) != i + 1) {
                return i + 1;
            }
        }
        return A.size() + 1;


    }

    public static int firstMissingPositiveN(ArrayList<Integer> A) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < A.size(); i++) {
            set.add(A.get(i));
        }
        for (int i = 1; i <= A.size(); i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return 1;
    }

    public static int firstMissingPositiveNlogN(ArrayList<Integer> A) {
        int i = 1;
        Collections.sort(A);
        for (int j = 0; j < A.size(); j++) {
            if (A.get(j) == i) {
                i++;
            }
        }
        return i;
    }

    public static int firstMissingPositiveN2(ArrayList<Integer> A) {
        for (int i = 0; i <= A.size(); i++) {
            if (!A.contains(i + 1)) {
                return i + 1;
            }
        }
        return 1;
    }

    public static int solveSumTheDifference(ArrayList<Integer> A) {
        long minSum = 0;
        long maxSum = 0;
        Collections.sort(A);
        for (int i = 0; i < A.size(); i++) {
            minSum = (minSum * 2) % 1000000007;
            minSum = minSum + A.get(i);
            maxSum = (maxSum * 2) % 1000000007;
            maxSum = maxSum + A.get(A.size() - 1 - i);
        }
        return (int) (maxSum - minSum + 1000000007) % 1000000007;
    }

    public static int solveSumTheDifferenceExponential(ArrayList<Integer> A) {
        double minSum = 0;
        double maxSum = 0;
        Collections.sort(A);
        for (int i = 0; i < A.size() - 1; i++) {
            minSum += ((((Math.pow(2, A.size() - i - 1) - 1) % 1000000007.0) * (double) A.get(i)) % 1000000007.0);
            minSum %= 1000000007.0;
        }
        for (int i = A.size() - 1; i > 0; i--) {
            maxSum += ((((Math.pow(2, i) - 1) % 1000000007.0) * (double) A.get(i)) % 1000000007.0);
            maxSum %= 1000000007.0;
        }
        return (int) (maxSum - minSum);
    }
}
