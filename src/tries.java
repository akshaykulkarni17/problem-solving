import java.sql.Array;
import java.util.*;

class TrieNode {
    Map<Character,TrieNode> child=new LinkedHashMap<>();
    int frequency=0;
    boolean isEnd=false;
}

public class tries {

    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList<>(Arrays.asList("zebra", "dog", "duck", "dot"));
        ArrayList<String> arr2 = new ArrayList<>(Arrays.asList("zebra", "dog", "dug"));
        ArrayList<Integer> aaa = new ArrayList<>(Arrays.asList(28, 31, 13, 22, 17, 22 ));
        //System.out.println(findPrefixes(arr));
        System.out.println(maximumXORSubArrayIndexes(aaa));
        //System.out.println(spellingChecker(arr,arr2));
    }



    public static ArrayList<Integer> maximumXORSubArrayIndexes(ArrayList<Integer> A) {
        ArrayList<Integer> prefixXOR = new ArrayList<>();
        int xor = 0;
        for (int x : A) {
            xor = xor ^ x;
            prefixXOR.add(xor);
        }
        ArrayList<String> binaryStrings = new ArrayList<>();
        for (int x : prefixXOR) {
            StringBuilder B = new StringBuilder(Integer.toBinaryString(x));
            while (B.length() != 32) {
                B.insert(0, "0");
            }
            binaryStrings.add(B.toString());
        }
        TrieNode root = new TrieNode();
        for (String b : binaryStrings) {
            insertXOR(root, b);
        }
        int maxXOR = Collections.max(prefixXOR);
        int start = 1;
        int end = prefixXOR.indexOf(maxXOR)+1;
        for (String b : binaryStrings) {
            StringBuilder xorStr = new StringBuilder();
            TrieNode curr = root;
            for (char c : b.toCharArray()) {
                if (c == '0' && curr.child.containsKey('1')) {
                    xorStr.append('1');
                    curr = curr.child.get('1');
                } else if (c == '1' && curr.child.containsKey('0')) {
                    xorStr.append('0');
                    curr = curr.child.get('0');
                } else if (c == '0' && curr.child.containsKey('0')) {
                    xorStr.append('0');
                    curr = curr.child.get('0');
                } else if (c == '1' && curr.child.containsKey('1')) {
                    xorStr.append('1');
                    curr = curr.child.get('1');
                }
            }
            xor=Integer.parseInt(b,2)^Integer.parseInt(xorStr.toString(),2);
            if(xor>maxXOR){
                maxXOR=xor;
                start= prefixXOR.indexOf(Integer.parseInt(b,2))+2;
                end=prefixXOR.indexOf(Integer.parseInt(xorStr.toString(),2))+1;
            }
            if (xor==maxXOR){
                int s= prefixXOR.indexOf(Integer.parseInt(b,2))+2;
                int e=prefixXOR.indexOf(Integer.parseInt(xorStr.toString(),2))+1;
                if(s<e&&((end-start>e-s)||(end-start==e-s&&s<start))){
                    start=s;
                    end=e;
                }
            }
        }
        return new ArrayList<>(Arrays.asList(start,end));
    }



    public static int maximumXOR(ArrayList<Integer> A) {
        ArrayList<String> binaryStrings = new ArrayList<>();
        for (int x: A){
            StringBuilder B = new StringBuilder(Integer.toBinaryString(x));
            while(B.length()!=32){
                B.insert(0, "0");
            }
            binaryStrings.add(B.toString());
        }
        TrieNode root = new TrieNode();
        for (String b : binaryStrings){
            insertXOR(root,b);
        }
        int maxXOR = 0;
        for (String b : binaryStrings){
            StringBuilder xor = new StringBuilder();
            TrieNode curr = root;
            for (char c : b.toCharArray()){
                if (c=='0'&&curr.child.containsKey('1')){
                    xor.append('1');
                    curr=curr.child.get('1');
                }
                else if(c=='1'&& curr.child.containsKey('0')){
                    xor.append('0');
                    curr=curr.child.get('0');
                }
                else if (c=='0'&&curr.child.containsKey('0')){
                    xor.append('0');
                    curr=curr.child.get('0');
                }
                else if(c=='1'&& curr.child.containsKey('1')){
                    xor.append('1');
                    curr=curr.child.get('1');
                }
            }
            maxXOR=Math.max(maxXOR,Integer.parseInt(b,2)^Integer.parseInt(xor.toString(),2));
        }
        return maxXOR;
    }
    static void insertXOR(TrieNode root,String str){
        TrieNode curr = root;
        for (char c : str.toCharArray()){
            curr.child.putIfAbsent(c,new TrieNode());
            curr=curr.child.get(c);
        }
    }


    static ArrayList<Integer> isCorrect;
    public static ArrayList<Integer> spellingChecker(ArrayList<String> A, ArrayList<String> B) {
        isCorrect=new ArrayList<>();
        TrieNode root = new TrieNode();
        for (String s : A){
            insert(root,s);
        }
        for (String word : B){
            isCorrect(root,word);
        }
        return isCorrect;
    }
    private static void isCorrect(TrieNode root, String word) {
            for (char c : word.toCharArray()){
                if (root.child.get(c)==null){
                    isCorrect.add(0);
                    break;
                }
                root=root.child.get(c);
            }
            if (root.isEnd) isCorrect.add(1);
    }


    public static int MaximumXOR(ArrayList<Integer> A) {
        int max=0, mask=0;
        for (int i = 31; i >=0 ; i--) {
            mask = mask | 1<<i;
            Set<Integer> set = new HashSet<>();
            for (int x : A){
                set.add(x&mask);
            }
            int temp = max | 1<<i;
            for (int prefix : set){
                if (set.contains(temp^prefix)){
                    max=temp;
                    break;
                }
            }
        }
        return max;
    }


    static ArrayList<String> uniquePrefixes;
    static void insert(TrieNode root,String str){
        TrieNode curr = root;
        for (char c : str.toCharArray()){
            curr.child.putIfAbsent(c,new TrieNode());
            curr.child.get(c).frequency++;
            curr=curr.child.get(c);
        }
        curr.isEnd=true;
    }
    static ArrayList<String> findPrefixes(ArrayList<String> list){
        uniquePrefixes=new ArrayList<>();
        TrieNode root=new TrieNode();
        for (int i = 0; i < list.size(); i++) {
            insert(root,list.get(i));
        }
        prefixUtil(root,"");
        return uniquePrefixes;
    }
    static void prefixUtil(TrieNode root, String wordSoFar) {
        if (root==null) return;
        if (root.frequency==1){
            uniquePrefixes.add(wordSoFar);
            return;
        }
        for (char children : root.child.keySet()){
            prefixUtil(root.child.get(children),wordSoFar+children);
        }
    }
}
