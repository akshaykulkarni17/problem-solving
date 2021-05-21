import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class soroco {
    static final String mappings[]
            = { "abc", "def", "ghi", "jkl", "mno","pqr", "stu", "vwx", "yz" };
    public static void main(String[] args) {
        System.out.println(printStrings("23"));
    }
    public static ArrayList<String> printStrings(String input){
        if (input.length()==0){
            return new ArrayList<>(Collections.singleton(""));
        }
        char ch = input.charAt(0);
        String rest = input.substring(1);
        ArrayList<String> restStrings = printStrings(rest);
        ArrayList<String> answer = new ArrayList<>();
        String maps = mappings[ch-'0'-1];
        for (String str : restStrings){
            for (int i = 0; i < maps.length(); i++) {
                answer.add(maps.charAt(i)+str);
            }
        }
        return answer;
    }
}
