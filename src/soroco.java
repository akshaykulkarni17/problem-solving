import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class soroco {

    //Backtracking problem
    static final String mappings[]
            = { "0","1","abc", "def", "ghi", "jkl", "mno","pqrs", "tuv", "wxyz" };
    public static void main(String[] args) {
        System.out.println(letterPhone("012"));
    }
    public static ArrayList<String> letterPhone(String input){
        if (input.length()==0){
            return new ArrayList<>(Collections.singleton(""));
        }
        char ch = input.charAt(0);
        String rest = input.substring(1);
        ArrayList<String> restStrings = letterPhone(rest);
        ArrayList<String> answer = new ArrayList<>();
        String maps = mappings[ch-'0'];
        for (String str : restStrings){
            for (int i = 0; i < maps.length(); i++) {
                answer.add(maps.charAt(i)+str);
            }
        }
        Collections.sort(answer);
        return answer;
    }
}
