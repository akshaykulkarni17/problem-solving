import java.util.*;
public class solution4Apr21 {
    public static void main(String[] args) {
        ArrayList<Integer> l1 = new ArrayList<>(Arrays.asList(5, 17, 100, 11));
        ArrayList<Integer> l2 = new ArrayList<>(Arrays.asList(0, 0, 2, 8));
        //ArrayList<Integer> l3 = new ArrayList<>(Arrays.asList( 7,8,9));
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        matrix.add(l1);
        matrix.add(l2);
        //matrix.add(l3);
        ArrayList<Integer> b = new ArrayList<>(Arrays.asList(1, 1));
        ArrayList<Integer> c = new ArrayList<>(Arrays.asList(1, 4));
        ArrayList<Integer> d = new ArrayList<>(Arrays.asList(2, 2));
        ArrayList<Integer> e = new ArrayList<>(Arrays.asList(2, 4));
        //System.out.println(solveMinimizeTheAbsoluteDifference(l1,l2,l3));
        System.out.println(solveSubMatrixSumQueries(matrix, b, c, d, e));
    }

    public static ArrayList<Integer> IntersectionOfSortedArrays(final List<Integer> A, final List<Integer> B) {
        HashMap<Integer, Integer> mapA = new HashMap<>();
        for (int x :
                A) {
            if (mapA.containsKey(x)) {
                mapA.put(x, mapA.get(x) + 1);
            } else {
                mapA.put(x, 1);
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int x :
                B) {
            if (mapA.containsKey(x)) {
                list.add(x);
                if (mapA.get(x) == 1) {
                    mapA.remove(x);
                } else {
                    mapA.put(x, mapA.get(x) - 1);
                }
            }
        }
        return list;
    }

    public static int solveSumOfAllSubMatrices(ArrayList<ArrayList<Integer>> A) {
        int sum = 0;
        for (int i = 0; i < A.size(); i++) {
            for (int j = 0; j < A.size(); j++) {
                sum += (i + 1) * (j + 1) * (A.size() - i) * (A.size() - j) * A.get(i).get(j);
            }
        }
        return sum;
    }

    public static ArrayList<Integer> solveSubMatrixSumQueries(ArrayList<ArrayList<Integer>> A,
                                                              ArrayList<Integer> B,
                                                              ArrayList<Integer> C,
                                                              ArrayList<Integer> D,
                                                              ArrayList<Integer> E) {
        ArrayList<ArrayList<Long>> prefixSumMatrix = new ArrayList<>();
        long mod = 1000000007;
        for (int i = 0; i < A.size(); i++) {
            prefixSumMatrix.add(new ArrayList<>());
            long sum = 0;
            for (int j = 0; j < A.get(i).size(); j++) {
                sum = (sum % mod + A.get(i).get(j) % mod) % mod;
                prefixSumMatrix.get(i).add(sum);
            }
        }
        for (int i = 0; i < A.get(0).size(); i++) {
            long sum = 0;
            for (int j = 0; j < A.size(); j++) {
                sum = (sum % mod + prefixSumMatrix.get(j).get(i) % mod) % mod;
                prefixSumMatrix.get(j).set(i, sum);
            }
        }
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < B.size(); i++) {
            int topLeft = B.get(i) - 1;
            int topRight = C.get(i) - 1;
            int bottomLeft = D.get(i) - 1;
            int bottomRight = E.get(i) - 1;
            long sumSubMatrix = 0;
            if (topLeft == 0 && topRight == 0) {
                sumSubMatrix = prefixSumMatrix.get(bottomLeft).get(bottomRight) % mod;
            } else if (topLeft == 0) {
                sumSubMatrix = (prefixSumMatrix.get(bottomLeft).get(bottomRight) % mod - prefixSumMatrix.get(bottomLeft).get(topRight - 1) % mod) % mod;
            } else if (topRight == 0) {
                sumSubMatrix = (prefixSumMatrix.get(bottomLeft).get(bottomRight) % mod - prefixSumMatrix.get(topLeft - 1).get(bottomRight) % mod) % mod;
            } else {
                sumSubMatrix = (prefixSumMatrix.get(bottomLeft).get(bottomRight) % mod - prefixSumMatrix.get(topLeft - 1).get(bottomRight) % mod - prefixSumMatrix.get(bottomLeft).get(topRight - 1) % mod + prefixSumMatrix.get(topLeft - 1).get(topRight - 1) % mod) % mod;
            }
            answer.add((int) sumSubMatrix);
        }
        return answer;
    }

    public static int solveMinimizeTheAbsoluteDifference(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<Integer> C) {
        int aIndex = 0;
        int bIndex = 0;
        int cIndex = 0;
        int diff = (Math.abs(Math.max(A.get(aIndex), Math.max(B.get(bIndex), C.get(cIndex))) - Math.min(A.get(aIndex), Math.min(B.get(bIndex), C.get(cIndex)))));
        while (aIndex < A.size() && bIndex < B.size() && cIndex < C.size()) {
            diff = Math.min(diff, (Math.abs(Math.max(A.get(aIndex), Math.max(B.get(bIndex), C.get(cIndex))) - Math.min(A.get(aIndex), Math.min(B.get(bIndex), C.get(cIndex))))));
            if (A.get(aIndex) < B.get(bIndex)) {
                if (A.get(aIndex) < C.get(cIndex)) {
                    aIndex++;
                } else {
                    cIndex++;
                }
            } else if (B.get(bIndex) < C.get(cIndex)) {
                bIndex++;
            } else {
                cIndex++;
            }
        }
        return diff;
    }

    public static int solveOptimalPartitioning(ArrayList<Integer> A) {
        Collections.sort(A);
        int diff = Integer.MAX_VALUE;
        int i = 0;
        int j = 1;
        while (j < A.size()) {
            if (diff > A.get(j) - A.get(i)) {
                diff = A.get(j) - A.get(i);
            }
            i++;
            j++;
        }
        return diff;
    }

    public ArrayList<Integer> solveMergeSortedArrays(final List<Integer> A, final List<Integer> B) {
        ArrayList<Integer> list = new ArrayList<>();
        long aIndex = 0;
        long bIndex = 0;
        while (aIndex < A.size() && bIndex < B.size()) {
            if (A.get((int) aIndex) < B.get((int) bIndex)) {
                list.add(A.get((int) aIndex));
                aIndex++;
            } else {
                list.add(B.get((int) bIndex));
                bIndex++;
            }
        }
        if (aIndex < A.size()) {
            list.add(A.get((int) aIndex));
            aIndex++;
        }
        if (bIndex < B.size()) {
            list.add(B.get((int) bIndex));
            bIndex++;
        }
        return list;
    }

    public ArrayList<Integer> solveMergeSortedArraysNlogN(final List<Integer> A, final List<Integer> B) {
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(A);
        list.addAll(B);
        Collections.sort(list);
        return list;
    }

    public ArrayList<Integer> solveCommonElements(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Integer> list = new ArrayList<>();
        Map<Integer, Integer> mapA = new HashMap<>();
        for (int x :
                A) {
            if (mapA.containsKey(x)) {
                mapA.put(x, mapA.get(x) + 1);
            } else mapA.put(x, 1);
        }
        for (int x :
                B) {
            if (mapA.containsKey(x)) {
                list.add(x);
                if (mapA.get(x) == 1) {
                    mapA.remove(x);
                } else {
                    mapA.put(x, mapA.get(x) - 1);
                }
            }
        }
        return list;
    }
}
