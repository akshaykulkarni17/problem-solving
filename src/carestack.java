import java.util.ArrayList;
import java.util.Arrays;

public class carestack {

    public static void main(String[] args) {
        ArrayList<Integer> l = new ArrayList<>(Arrays.asList(1, 2, 1, 2, 2, 0, 0, 1, 0, 33, 10, 9));
        System.out.println(uniqueNumbers(l));
    }

           static ArrayList<Integer> uniqueNumbers(ArrayList<Integer> list){
                int[] arr =new int[101];
                for (int x : list){
                    arr[x]++;
                }
                ArrayList<Integer> ans = new ArrayList<>();
                for (int i = 0; i < arr.length; i++) {
                    if(arr[i]>0){
                        ans.add(i);
                    }
                }
                return ans;
            }
}
