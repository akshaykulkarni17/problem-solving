import java.util.*;
public class hardProblems {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 6, 7, 9, 5, 4, 3, 1, 1, 3, 3, 3));
        System.out.println(Nby3RepeatNumber(list));
    }

    public static int Nby3RepeatNumber(ArrayList<Integer> A) {
        int countFirst = 0;
        int countSecond = 0;
        int first = -1;
        int second = -1;
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i).equals(first)) {
                countFirst++;
            } else if (A.get(i).equals(second)) {
                countSecond++;
            } else if (countFirst == 0) {
                first = A.get(i);
            } else if (countSecond == 0) {
                second = A.get(i);
            } else {
                countFirst--;
                countSecond--;
            }
        }
        countFirst = 0;
        countSecond = 0;
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i).equals(first)) countFirst++;
            if (countFirst > A.size() / 3) return first;
        }
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i).equals(second)) countSecond++;
            if (countSecond > A.size() / 3) return second;
        }
        return -1;
    }
}











