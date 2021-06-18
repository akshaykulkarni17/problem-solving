import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class contest2 {
    public static void main(String[] args) {
        System.out.println(solvePositionSequence(3,5));
        char c = '0';
        Integer.parseInt(String.valueOf(c));
    }

    public static int solvePositionSequence(int A, int B) {
        int count=A;
        int i=1;
        while(B>0&&A>0){
            int temp=Math.min(i,B/A);
            if(i>temp) break;
            B-=temp;
            A--;
            i++;
        }
        return count-i+1;
    }


    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Long> prefix = new ArrayList<>();
        long sum = 0;
        for (int x : A){
            sum+=x;
            prefix.add(sum);
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int x : B){
            int start=0;
            int end = A.size()-1;
            int ans =A.size();
            while(start<=end){
                int mid = start + (end-start)/2;
                if (x>=prefix.get(mid)){
                    start=mid+1;
                }
                else {
                    ans=mid;
                    end=mid-1;
                }
            }
            list.add(ans);
        }
        return list;
    }
}
