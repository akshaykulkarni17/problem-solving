import java.util.*;
public class solution2Apr21 {

    public static void main(String[] args) {
        //System.out.println(solveConstructArray(7,39,41));
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1000, 999, 998, 997, 996, 995, 994, 993, 992, 991, 990, 989, 988, 987, 986, 985, 982, 981, 980, 1000, 999, 998, 997, 996, 995, 994, 993, 992, 991, 990, 989, 988, 987, 986, 985, 982, 981, 980, 1000, 999, 998, 997, 996, 995, 994, 993, 992, 991, 990, 989, 988, 987, 986, 985, 982, 981, 980, 1000, 999, 998, 997, 996, 995, 994, 993, 992, 991, 990, 989, 988, 987, 986, 985, 982, 981, 980, 1000, 999, 998, 997, 996, 995, 994, 993, 992, 991, 990, 989, 988, 987, 986, 985, 982, 981, 980, 1000, 999, 998, 997, 996, 995, 994, 993, 992, 991, 990, 989, 988, 987, 986, 985, 982, 981, 980, 1000, 999, 998, 997, 996, 995, 994, 993, 992, 991, 990, 989, 988, 987, 986, 985, 982, 981, 980, 1000, 999, 998, 997, 996, 995, 994, 993, 992, 991, 990, 989, 988, 987, 986, 985, 982, 981, 980));
        //System.out.println(solveContiguousArrayZeroOne(list));
        System.out.println(solveSumTheDifference(list));
    }

    public static int solveSumTheDifference(ArrayList<Integer> A) {
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

    public static int solveContiguousArrayZeroOne(ArrayList<Integer> A) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int x :
                A) {
            list.add(x == 0 ? -1 : 1);
        }
        int sum = 0;
        int length = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
            if (sum == 0) length = i + 1;
            if (map.containsKey(sum)) {
                length = Math.max(length, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return length;
    }

    public static ArrayList<Integer> solveConstructArray(final int A, final int B, final int C) {
        ArrayList<Integer> divisors = new ArrayList<>();
        for (int i = 1; i <= Math.sqrt(C - B); i++) {
            if ((C - B) % i == 0) {
                divisors.add(i);
                divisors.add((C - B) / i);
            }
        }
        int minMax = Integer.MAX_VALUE;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < divisors.size(); i++) {
            int array = A;
            ArrayList<Integer> ll = new ArrayList<>();
            int aleft = C;
            int aright = C + divisors.get(i);
            while (array != 0) {
                if (aleft > 0) {
                    ll.add(aleft);
                    aleft -= divisors.get(i);
                } else {
                    ll.add(aright);
                    aright += divisors.get(i);
                }
                array--;
            }
            if ((ll.contains(B) && minMax > Collections.max(ll)) || (ll.contains(B) && minMax == Collections.max(ll) && Collections.min(ll) < Collections.min(ans))) {
                minMax = Collections.max(ll);
                ans.clear();
                ans.addAll(ll);
            }
        }
        Collections.sort(ans);
        return ans;
    }

    public static int solveMinimizeDifferencePuzzle(ArrayList<Integer> A, int B) {
        Collections.sort(A);
        int i = 0;
        int j = B - 1;
        int diff = Integer.MAX_VALUE;
        while (j < A.size()) {
            if (A.get(j) - A.get(i) < diff) {
                diff = A.get(j) - A.get(i);
            }
            i++;
            j++;
        }
        return diff;
    }

}
