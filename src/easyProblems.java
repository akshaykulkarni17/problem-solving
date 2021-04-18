import java.util.*;

public class easyProblems {

    public static void main(String[] args) {

    }
    public ArrayList<ArrayList<Integer>> anagrams(final List<String> A) {
        HashMap<String,ArrayList<Integer>> anagram = new HashMap<>();
        for (int i = 0; i < A.size(); i++) {
            char[] ch= (A.get(i).toCharArray());
            Arrays.sort(ch);
            String s = String.valueOf(ch);
            if (anagram.containsKey(s)){
                ArrayList<Integer> list = anagram.get(s);
                list.add(i+1);
            }
            else{
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i+1);
                anagram.put(s,list);
            }
        }
        ArrayList<ArrayList<Integer>> answer = new ArrayList<>();
        for (ArrayList<Integer> i:
             anagram.values()) {
            answer.add(i);
        }
        return answer;
    }
}
