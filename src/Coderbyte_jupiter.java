import java.util.HashMap;
import java.util.Map;

public class Coderbyte_jupiter {

    int bipartiteMatching(String[] arr){
        Map<Character,Character> pair = new HashMap<>();
        int total1 =0;
        int total2 = 0;
        for (int i = 0; i < arr.length; i++) {
            String current = arr[i];
            char leftNode = current.charAt(0);
            char rightNode = current.charAt(3);
            if (!pair.containsKey(rightNode)){
                boolean repeat = false;
                for (char c : pair.keySet()){
                    if (pair.get(c)==leftNode){
                        repeat=true;
                        break;
                    }
                }
                if (!repeat){
                    pair.put(rightNode,leftNode);
                    total1++;
                }
            }
        }
        pair.clear();
        for (int i = arr.length-1; i >=0 ; i--) {
            String current = arr[i];
            char leftNode = current.charAt(0);
            char rightNode = current.charAt(3);
            if (!pair.containsKey(rightNode)){
                boolean repeat = false;
                for (char c : pair.keySet()){
                    if (pair.get(c)==leftNode){
                        repeat=true;
                        break;
                    }
                }
                if (!repeat){
                    pair.put(rightNode,leftNode);
                    total2++;
                }
            }
        }
        return total1>total2 ? total1 : total2;
    }
}
