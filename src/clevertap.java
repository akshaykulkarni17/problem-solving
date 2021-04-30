import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class clevertap {

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(2,7,11,15));
        System.out.println(targetSum(arr,9));
    }
    public  static ArrayList<Integer> targetSum(ArrayList<Integer> list, int target){
        ArrayList<Integer> answer = new ArrayList<>();
        Map<Integer,Integer> indexes = new HashMap<>();
        for(int i=0;i<list.size();i++){
            if(indexes.containsKey(target-list.get(i))){
                answer.add(indexes.get(target-list.get(i)));
                answer.add(i);
                return answer;
            }
            else {
                indexes.put(list.get(i),i);
            }
        }
        return answer;
    }
}
