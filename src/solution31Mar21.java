import java.util.*;
public class solution31Mar21 {

    public static void main(String[] args) {

        ArrayList<Integer> ll = new ArrayList<>(Arrays.asList(93, 51, 84, 81, 89, 82, 28, 78, 86, 35, 64, 38, 49, 99, 83));
        //System.out.println(slidingMaximum(ll,6));
        System.out.println(solveMaximumDifference(ll, 4));
    }

    public static int solveMaximumDifference(ArrayList<Integer> A, int B) {
        ArrayList<Integer> prefix = new ArrayList<>();
        int sum = 0;
        for (int x :
                A) {
            sum += x;
        }
        int maxDiff = Integer.MIN_VALUE;
        for (int i = 0; i < A.size() - B; i++) {
            int s1 = 0;
            for (int j = i; j < j + B; j++) {
                s1 += A.get(j);
            }
            maxDiff = Math.max(maxDiff, Math.abs(sum - 2 * s1));
        }
//        for (int x:
//             A) {
//            sum+=x;
//            prefix.add(sum);
//        }
//        int i =0;
//        int j = i+B-1;
//        int maxDiff=Integer.MIN_VALUE;
//        while (j<A.size()){
//            int s1 = i==0? prefix.get(j): prefix.get(j)-prefix.get(i-1);
//            int s2 = sum-s1;
//            maxDiff=Math.max(maxDiff,Math.abs(s1-s2));
//            i++;j++;
//        }
        return maxDiff;
    }

    public static int majorityElement(final List<Integer> A) {
//        Collections.sort(A);
//        return A.get(A.size()/2);
        int i = 1;
        int majority = A.get(0);
        int count = 1;
        while (i < A.size()) {
            if (majority == A.get(i)) {
                count++;
            } else if (count == 1) {
                majority = A.get(i);
            } else count--;
            i++;
        }
        count = 0;
        for (int x :
                A) {
            if (majority == x) count++;
        }
        return count;
    }

    public static int solveMinimumAbsoluteDifference(ArrayList<Integer> A) {
        int i = 0;
        int j = 1;
        int diff = Integer.MAX_VALUE;
        Collections.sort(A);
        while (j < A.size()) {
            diff = Math.min(diff, Math.abs(A.get(i) - A.get(j)));
            i++;
            j++;
        }
        return diff;
    }

    public static ArrayList<Integer> slidingMaximum(final List<Integer> A, int B) {
        ArrayDeque<Integer> deck = new ArrayDeque<>();
        for (int i = 0; i < B; i++) {
            int temp = A.get(i);
            while (!deck.isEmpty() && deck.peekLast() < temp) {
                deck.pollLast();
            }
            if (deck.isEmpty() || deck.peekLast() > temp) {
                deck.add(temp);
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = B; i < A.size(); i++) {
            int temp = A.get(i);
            list.add(deck.peekFirst());
            if (deck.peekFirst().intValue() == A.get(i - B)) {
                deck.pollFirst();
            }
            while (!deck.isEmpty() && deck.peekLast() < temp) {
                deck.pollLast();
            }
            if (deck.isEmpty() || deck.peekLast() >= temp) {
                deck.add(temp);
            }
        }
        list.add(deck.pollFirst());
        return list;
    }

    public static ArrayList<Integer> slidingMaximumPriorityQueue(final List<Integer> A, int B) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < B; i++) {
            pq.add(A.get(i));
        }
        for (int i = B, j = 0; i < A.size(); i++, j++) {
            list.add(pq.peek());
            pq.remove(A.get(j));
            pq.add(A.get(i));
        }
        list.add(pq.peek());
        return list;
    }

    public static ArrayList<Integer> slidingMaximumBruteForce(final List<Integer> A, int B) {
        int i = 0;
        int j = B;
        ArrayList<Integer> list = new ArrayList<>();
        if (B > A.size()) return list;
        while (j <= A.size()) {
            list.add(Collections.max(A.subList(i, j)));
            i++;
            j++;
        }
        return list;
    }
}
