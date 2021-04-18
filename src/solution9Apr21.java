import java.util.*;

public class solution9Apr21 {
    public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ArrayList<Integer> l1 = new ArrayList<>(Arrays.asList(2,4));
        ArrayList<Integer> l2 = new ArrayList<>(Arrays.asList(1,5));
        ArrayList<Integer> l3 = new ArrayList<>(Arrays.asList(3,5));
        ArrayList<ArrayList<Integer>> B =new ArrayList<>();
        B.add(l1);B.add(l2);B.add(l3);
    //System.out.println(bulbs(list));
        //System.out.println(solveXorQueries(list,B));
        //System.out.println(solveSumOfBitwiseOr(list));
        System.out.println(solveSquareGranites(6,6,4));
    }
    public static int solveSquareGranites(int A, int B, int C) {
        return (int) (Math.ceil((double)A/C)*Math.ceil((double)B/C));
    }
    public static int solveSumOfBitwiseOr(ArrayList<Integer> A) {
        long sum=0;
        for (int i = 0; i < A.size(); i++) {
            long sumOr=0;
            for (int j = i; j < A.size(); j++) {
                sumOr=(sumOr|A.get(j))%1000000007;
                sum=(sum+sumOr)%1000000007;
            }

        }
        return (int) sum;
    }
    public static ArrayList<ArrayList<Integer>> solveXorQueries(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        ArrayList<Integer> prefixXor = new ArrayList<>();
        int countOnes=0;
        for (int x:
             A) {
            if (x==1) countOnes++;
            prefixXor.add(countOnes);
        }
        ArrayList<ArrayList<Integer>> answer = new ArrayList<>();
        for (int i = 0; i < B.size(); i++) {
            answer.add(xorAnswer(prefixXor,B.get(i).get(0)-1,B.get(i).get(1)-1));
        }
        return answer;
    }
    public static ArrayList<Integer> xorAnswer(ArrayList<Integer> prefixXor,int L, int R){
        ArrayList<Integer> list = new ArrayList<>();
        if (L>0) {
            list.add((prefixXor.get(R) - prefixXor.get(L - 1))%2==0?0:1);
            list.add((R - L + 1) - (prefixXor.get(R) - prefixXor.get(L - 1)));
        }
        else {
            list.add(prefixXor.get(R)%2==0?0:1);
            list.add((R - L + 1) - (prefixXor.get(R)));
        }
        return list;
    }
    public static int bulbs(ArrayList<Integer> A) {
        int switchCount=0;
        boolean toggle=false;
        for (int x:
             A) {
            if (x==0&&toggle==false){
                toggle=true;
                switchCount++;
            }
            if (x==1&&toggle==true){
                toggle=false;
                switchCount++;
            }
        }
        return switchCount;
    }
}
