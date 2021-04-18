import java.util.ArrayList;

public class veryEasyProblems {

    public static void main(String[] args) {

    }
    public ArrayList<Integer> MultiplicationOfPreviousAndNext(ArrayList<Integer> A){
        int previous=A.get(0);
        A.set(0,A.get(0)*A.get(1));
        for (int i = 1; i < A.size()-1; i++) {
            int current=A.get(i);
            A.set(i,previous*A.get(i+1));
            previous=current;
        }
        A.set(A.size()-1,previous*A.get(A.size()-1));
        return A;
    }
    public int solveMinimumPicks(ArrayList<Integer> A) {
        int maxEven=Integer.MIN_VALUE;
        int minOdd=Integer.MAX_VALUE;
        for (int x:
             A) {
            if (x%2==0){
                maxEven=Math.max(maxEven,x);
            }
            else {
                minOdd=Math.min(minOdd,x);
            }
        }
        return maxEven-minOdd;
    }
    public static ArrayList<Integer> solveElementsWithAtleast2GreaterElements(ArrayList<Integer> A) {
        int max1=Integer.MIN_VALUE;
        for (int x:
             A) {
            max1=Math.max(max1,x);
        }
        int max2=Integer.MIN_VALUE;
        for (int x:
             A) {
            if (max1!=x){
                max2=Math.max(max2,x);
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i)!=max1&&A.get(i)!=max2){
                list.add(A.get(i));
            }
        }
        return list;
    }
}
