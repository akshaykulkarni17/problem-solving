import java.math.BigInteger;
import java.util.*;

public class solution3May21 {

    public static void main(String[] args) {
    ArrayList<Integer> l1 = new ArrayList<>(Arrays.asList(1, 2, 3));
    ArrayList<Integer> l2 = new ArrayList<>(Arrays.asList(4,5,6));
    ArrayList<Integer> l3 = new ArrayList<>(Arrays.asList(7, 8, 9));
        ArrayList<Integer> B = new ArrayList<>(Arrays.asList(1, 1));
        ArrayList<Integer> C = new ArrayList<>(Arrays.asList(1, 4));
        ArrayList<Integer> D = new ArrayList<>(Arrays.asList(2, 2));
        ArrayList<Integer> E = new ArrayList<>(Arrays.asList(2, 4));
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        list.add(l1);list.add(l2);list.add(l3);
        //System.out.println(subMatrixSumQueries(list,B,C,D,E));
        //System.out.println(SearchInRowwiseColumnwiseSortedMatrix(list,2));
        System.out.println(DivisorGame(1000000000,10000,10000));
    }
    public static int DivisorGame(int A, int B, int C) {
        int lcm = (B*C)/gcd(B,C);
        return A/lcm;

    }
    public static int gcd(int a,int b){
        int i=Math.max(a,b);
        int j=Math.min(a,b);
        if(j==0){
            return i;
        }
        else return gcd(j,i%j);
    }
    public static int ONSearchInRowwiseColumnwiseSortedMatrix(ArrayList<ArrayList<Integer>> A, int B) {
        int i = A.size();
        int j= A.get(0).size();
        int l=0;
        int r=j-1;
        while (l<i&&r>=0){
            if (A.get(l).get(r)==B) return (l+1)*1009+r+1;
            else if (A.get(l).get(r)<B) r--;
            else l++;
        }
        return -1;
    }
    public static int SearchInRowwiseColumnwiseSortedMatrix(ArrayList<ArrayList<Integer>> A, int B) {
        int n=A.get(0).size()-1;
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i).get(0)<=B&&B<=A.get(i).get(n)){
                for (int j = 0; j < A.get(i).size(); j++) {
                    if (A.get(i).get(j)==B){
                        return (i+1)*1009+j+1;
                    }
                }
            }
        }
        return -1;
    }
    public ArrayList<Integer> workingSubMatrixSumQueries(ArrayList<ArrayList<Integer>> A,
                                    ArrayList<Integer> B, ArrayList<Integer> C, ArrayList<Integer> D, ArrayList<Integer> E)
    {
        ArrayList<ArrayList<Long>> prefixSumMatrix = new ArrayList<>();
        long mod = 1000000007;
        for (int i = 0; i < A.size(); i++) {
            prefixSumMatrix.add(new ArrayList<>());
            long sum=0;
            for (int j = 0; j < A.get(i).size(); j++) {
                sum=(sum%mod + A.get(i).get(j)%mod)%mod;
                prefixSumMatrix.get(i).add(sum);
            }
        }
        for (int i = 0; i < A.get(0).size(); i++) {
            long sum =0;
            for (int j = 0; j < A.size(); j++) {
                sum=(sum%mod + prefixSumMatrix.get(j).get(i)%mod)%mod;
                prefixSumMatrix.get(j).set(i,sum);
            }
        }
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < B.size(); i++) {
            int topLeft = B.get(i)-1;
            int topRight = C.get(i)-1;
            int bottomLeft = D.get(i)-1;
            int bottomRight = E.get(i)-1;
            long sumSubMatrix =0;
            if (topLeft==0&&topRight==0) {
                sumSubMatrix = prefixSumMatrix.get(bottomLeft).get(bottomRight);
            }
            else if (topLeft==0){
                sumSubMatrix = prefixSumMatrix.get(bottomLeft).get(bottomRight) - prefixSumMatrix.get(bottomLeft).get(topRight-1);
            }
            else if (topRight==0){
                sumSubMatrix = prefixSumMatrix.get(bottomLeft).get(bottomRight) - prefixSumMatrix.get(topLeft-1).get(bottomRight);
            }
            else {
                sumSubMatrix = prefixSumMatrix.get(bottomLeft).get(bottomRight) - prefixSumMatrix.get(topLeft-1).get(bottomRight) - prefixSumMatrix.get(bottomLeft).get(topRight-1) + prefixSumMatrix.get(topLeft-1).get(topRight-1);
            }
            while(sumSubMatrix<0)
                sumSubMatrix = sumSubMatrix+mod;
            sumSubMatrix%=mod;
            answer.add((int) sumSubMatrix);
        }
        return answer;
    }
    public static ArrayList<Integer> subMatrixSumQueries(ArrayList<ArrayList<Integer>> A,
                                                         ArrayList<Integer> B, ArrayList<Integer> C,
                                                         ArrayList<Integer> D, ArrayList<Integer> E) {
        int mod = 1000000007;
        //prefix row sum
        for (int i = 0; i < A.size(); i++) {
            int sum=0;
            for (int j = 0; j < A.get(0).size(); j++) {
                sum=(sum%mod+A.get(i).get(j)%mod)%mod;
                A.get(i).set(j, sum);
            }
        }
        //prefix column sum
        for (int i = 0; i < A.get(0).size(); i++) {
            int sum=0;
            for (int j = 0; j <A.size() ; j++) {
                sum=(sum%mod+A.get(j).get(i)%mod)%mod;
                A.get(j).set(i,  sum);
            }
        }
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < B.size(); i++) {
            answer.add(subMatrixSum(A,B.get(i)-1,C.get(i)-1,D.get(i)-1,E.get(i)-1));
        }
        return answer;
    }
    public static int subMatrixSum(ArrayList<ArrayList<Integer>> A, int left, int top, int bottom, int right){
        int mod = 1000000007;
        long sumSubMatrix=0;
        if (left==0&&top==0){
            sumSubMatrix= A.get(bottom).get(right);
        }
        else if (left==0){
            sumSubMatrix= A.get(bottom).get(right) - A.get(bottom).get(top-1);
        }
        else if (top==0){
            sumSubMatrix= A.get(bottom).get(right) - A.get(left-1).get(right);
        }
        else {
            sumSubMatrix= A.get(bottom).get(right) - A.get(bottom).get(top-1) - A.get(left-1).get(right) + A.get(left-1).get(top-1);
        }
        while (sumSubMatrix<0){
            sumSubMatrix+=mod;
        }
        sumSubMatrix%=mod;
        return (int) sumSubMatrix;
    }
}
