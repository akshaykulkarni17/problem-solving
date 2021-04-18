import java.util.*;

public class mock31Mar21 {
    public static void main(String[] args) {
        ArrayList<Integer> l1 = new ArrayList<>(Arrays.asList(8, 9, 3, 2, 1));
        ArrayList<Integer> l2 = new ArrayList<>(Arrays.asList(10, 2, 5));
        System.out.println(throwBallOptimal(l2, l1));
    }

    public static ArrayList<Integer> throwBall(ArrayList<Integer> A, ArrayList<Integer> B) {
        int maxDiff = 0;
        int scoreA = 0;
        int scoreB = 0;
        for (int i = Math.min(Collections.min(A), Collections.min(B)) - 1; i <= Math.max(Collections.max(A), Collections.max(B)); i++) {
            int sumA = 0;
            int sumB = 0;
            for (int j = 0; j < A.size(); j++) {
                if (A.get(j) <= i) {
                    sumA += 2;
                } else {
                    sumA += 3;
                }
            }
            for (int j = 0; j < B.size(); j++) {
                if (B.get(j) <= i) {
                    sumB += 2;
                } else {
                    sumB += 3;
                }
            }
            if (maxDiff < Math.abs(sumA - sumB)) {
                maxDiff = Math.abs(sumA - sumB);
                scoreA = sumA;
                scoreB = sumB;
            }
        }
        return new ArrayList<>(Arrays.asList(scoreA, scoreB));
    }

    public static ArrayList<Integer> throwBallOptimal(ArrayList<Integer> A, ArrayList<Integer> B) {
        Set<Integer> set = new HashSet<>();
        set.addAll(A);
        set.addAll(B);
        int scoreA = 3 * A.size();
        int scoreB = 3 * B.size();
        int maxDiff = Math.abs(scoreA - scoreB);
        for (int x :
                set) {
            int sumA = 0;
            int sumB = 0;
            for (int j = 0; j < A.size(); j++) {
                if (A.get(j) <= x) {
                    sumA += 2;
                } else {
                    sumA += 3;
                }
            }
            for (int j = 0; j < B.size(); j++) {
                if (B.get(j) <= x) {
                    sumB += 2;
                } else {
                    sumB += 3;
                }
            }
            if (maxDiff < Math.abs(sumA - sumB)) {
                maxDiff = Math.abs(sumA - sumB);
                scoreA = sumA;
                scoreB = sumB;
            }
        }
        return new ArrayList<>(Arrays.asList(scoreA, scoreB));
    }
}
