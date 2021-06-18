import java.util.*;

public class solution27May21 {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList( 100000, 100000, 100000, 100000, 100000));
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(1, 3, 0, 0));
        //System.out.println(solveAlternatePositiveNegativeElements(list));
        //System.out.println(solveReversePairs(list));
        //System.out.println(subArrayWithZeroSum(list));
        //System.out.println(sortArrayInGivenOrder(list,list2));
        //System.out.println(pointsOnSameLine(list, list2));
        //System.out.println(maxChunksToMakeSorted(list));
        //System.out.println(largestNumber(list));
        //System.out.println(minimumSwaps2(list2));
        //System.out.println(uniqueElements(list));
        //System.out.println(lengthOfLongestSubstring("abcabcbb"));
        //System.out.println(minWindow("Aa91b","ab"));
        ArrayList<ArrayList<Integer>> B = new ArrayList<>();
        B.add(list2);B.add(list3);
        System.out.println(compareSortedSubArrays(list,B));
    }

        public static ArrayList<Integer> compareSortedSubArrays(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
            Map<Integer,Long> map = new HashMap<>();
            for (int x : A){
                map.put(x, (long) (Math.random()*(100000000000L-10000000000L+1L))+10000000000L);
            }
            ArrayList<Long> prefix = new ArrayList<>();
            for (int x: A){
                prefix.add(map.get(x));
            }
            long sum =0;
            ArrayList<Long> preSum = new ArrayList<>();
            for (long x: prefix){
                sum+=x;
                preSum.add(sum);
            }
            ArrayList<Integer> ans = new ArrayList<>();
            for (ArrayList<Integer> query : B){
                long sum1=query.get(0)==0 ? preSum.get(query.get(1)) : preSum.get(query.get(1))-preSum.get(query.get(0)-1);
                long sum2=query.get(2)==0 ? preSum.get(query.get(3)) : preSum.get(query.get(3))-preSum.get(query.get(2)-1);
                if (sum1==sum2) ans.add(1);
                else ans.add(0);
            }
            return ans;
        }

        public static ArrayList<Integer> compareSortedArrays(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        ArrayList<Integer> answer = new ArrayList<>();
        for (ArrayList<Integer> query : B){
            if (query.get(1)-query.get(0)==query.get(3)-query.get(2)){
                set1.clear();
                set1.addAll(A.subList(query.get(0),query.get(1)+1));
                set2.clear();
                set2.addAll(A.subList(query.get(2),query.get(3)+1));
                if (set1.equals(set2)) answer.add(1);
            }
            else answer.add(0);
        }
        return answer;
    }
    public static ArrayList<ArrayList<Integer>> anagrams(final List<String> A) {
        ArrayList<String> list = new ArrayList<>();
        for (String s : A){
            char[] ch = s.toCharArray();
            Arrays.sort(ch);
            list.add(String.valueOf(ch));
        }
        Map<String,ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            if(map.containsKey(list.get(i))){
                ArrayList<Integer> temp = map.get(list.get(i));
                temp.add(i+1);
                map.put(list.get(i), temp);
            }
            else map.put(list.get(i),new ArrayList<>(Collections.singleton(i+1)));
        }
        ArrayList<ArrayList<Integer>> answer = new ArrayList<>();
        for (ArrayList<Integer> l : map.values()){
            answer.add(l);
        }
        return answer;
    }


    public static String minWindow(String B, String A) {
        if (B.length()<A.length()) return "";
        int i=0,j=0;
        Map<Character,Integer> aMap = new HashMap<>();
        for (char c : A.toCharArray()){
            if (aMap.containsKey(c)){
                aMap.put(c,aMap.get(c)+1);
            }
            else aMap.put(c,1);
        }
        int start=0;
        int end=0;
        int min = Integer.MAX_VALUE;
        Map<Character,Integer> bMap = new HashMap<>();
        while(j<B.length()||i<B.length()){
            if(isValid(aMap,bMap)){
                if(j-i+1<min){
                    min=j-i+1;
                    start=i;
                    end=j;
                }
                char temp= B.charAt(i);
                if (bMap.get(temp)>1) bMap.put(temp,bMap.get(temp)-1);
                else bMap.remove(temp);
                i++;
            }
            else if(j<B.length()){
                char temp = B.charAt(j);
                if(bMap.containsKey(temp)){
                    bMap.put(temp, bMap.get(temp)+1);
                }
                else{
                    bMap.put(temp,1);
                }
                j++;
            }
            else i++;
        }
        return B.substring(start,end);
    }

    private static boolean isValid(Map<Character, Integer> aMap, Map<Character, Integer> bMap) {
        for (char c : aMap.keySet()) {
            if (bMap.containsKey(c)) {
                if (aMap.get(c) > bMap.get(c)) return false;
            } else return false;
        }
        return true;
    }

    public static int lengthOfLongestSubstring(String A) {
        Set<Character> set = new HashSet<>();
        int maxLen=0;
        char[] str = A.toCharArray();
        int i=0,j=0;
        while(j<str.length){
            if (set.contains(str[j])){
                maxLen=Math.max(maxLen,j-i);
                while (str[i]!=str[j]){
                    set.remove(str[i]);
                    i++;
                }
                i++;
            }
            set.add(str[j]);
            j++;
        }
        return Math.max(maxLen,j-i);
    }


    public int colorful(int A) {
        String s = new String(String.valueOf(A));
        Set<Integer> set = new HashSet<>();
        set.add(Integer.parseInt(String.valueOf(s.charAt(0))));
        for (int i = 1; i <s.length() ; i++) {
            int a1 = Integer.parseInt(String.valueOf(s.charAt(i-1)));
            int a2 = Integer.parseInt(String.valueOf(s.charAt(i)));
            if (a1==1||a2==1) return 0;
            if(set.contains(a2)||set.contains(a2*a1)) return 0;
            set.add(a2);
            set.add(a1*a2);
        }
        return 1;
    }
    public int PermutationsOfAInB(String A, String B) {
        int[] freqA = new int[26];
        for (char c : A.toCharArray()){
            freqA[c-'a']++;
        }
        int[] freqB = new int[26];
        for (int i = 0; i < A.length(); i++) {
            freqB[B.charAt(i)-'a']++;
        }
        int count=isSame(freqA,freqB)?1:0;
        for (int i = A.length(); i <B.length() ; i++) {
            freqB[B.charAt(i-A.length())-'a']--;
            freqB[B.charAt(i)-'a']++;
            if(isSame(freqA,freqB)) count++;
        }
        return count;
    }
    static boolean isSame(int[] a, int[] b){
        for (int i = 0; i < 26; i++) {
            if(a[i]!=b[i]) return false;
        }
        return true;
    }
    public static int uniqueElements(ArrayList<Integer> A) {
        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        for(int x: A){
            if(treeMap.containsKey(x)){
                treeMap.put(x,treeMap.get(x)+1);
            }
            else treeMap.put(x,1);
        }
        int[] arr = new int[Math.max(A.size(),Collections.max(A))];
        int count=0;
        int index=0;
        for (int x : treeMap.keySet()){
            index=Math.max(index,x);
            int freq = treeMap.get(x);
            while(freq!=0){
                count+=index-x;
                index++;
                freq--;
            }
        }
        return count;
    }
    public static int minimumSwaps2(ArrayList<Integer> A) {
        int swaps=0;
        for (int i = 0; i < A.size(); i++) {
            while(!A.get(i).equals(i)){
                Collections.swap(A,i,A.get(i));
                swaps++;
            }
        }
        return swaps;
    }
    public static String largestNumber(final List<Integer> A) {
        ArrayList<String> list = new ArrayList<>();
        for (int x : A){
            list.add(String.valueOf(x));
        }
        list.sort((a, b) -> -1*(a + b).compareTo(b + a));
        StringBuilder sb = new StringBuilder();
        for (String s : list){
            sb.append(s);
        }
        return sb.toString();
    }
    public static int maxChunksToMakeSorted(ArrayList<Integer> A) {
        int chunks=0,max=0,i=0;
        for (int x : A) {
            max=Math.max(max,x);
            if(max==i) chunks++;
            i++;
        }
        return chunks;
    }
    public int countRectangles(ArrayList<Integer> A, ArrayList<Integer> B) {
        int count=0;
        Set<Pair<Integer,Integer>> points = new HashSet<>();
        for (int i = 0; i < A.size(); i++) {
            for (int j = 0; j < B.size(); j++) {
                Pair<Integer,Integer> p = new Pair<>(A.get(i),B.get(i));
                points.add(p);
            }
        }
        for (int i = 0; i < A.size(); i++) {
            for (int j = i+1; j < A.size(); j++) {
                Pair<Integer,Integer> p1 = new Pair<>(A.get(i),B.get(i));
                Pair<Integer,Integer> p2 = new Pair<>(A.get(j),B.get(j));
                if (p1.x==p2.x || p1.y==p2.y) continue;
                Pair<Integer,Integer> p3 = new Pair<>(p1.x,p2.y);
                Pair<Integer,Integer> p4 = new Pair<>(p2.x,p1.y);
                if (points.contains(p3)&& points.contains(p4)){
                    count++;
                }
            }
        }
        return count;
    }
    public int countRightAngledTriangles(ArrayList<Integer> A, ArrayList<Integer> B) {
        long count =0;
        Map<Integer,Integer> xMap = new HashMap<>();
        for (int x : A){
            if (xMap.containsKey(x)){
                xMap.put(x,xMap.get(x)+1);
            }
            else xMap.put(x,1);
        }
        Map<Integer,Integer> yMap = new HashMap<>();
        for (int x : B){
            if (yMap.containsKey(x)){
                yMap.put(x,yMap.get(x)+1);
            }
            else yMap.put(x,1);
        }
        for (int i = 0; i < A.size(); i++) {
            count+=((long) (xMap.get(A.get(i)) - 1) *(yMap.get(B.get(i))-1))%1000000007L;
            count%=1000000007L;
        }
        return (int) count;
    }

    public static int pointsOnSameLine(ArrayList<Integer> A, ArrayList<Integer> B) {

        if(A.size() <= 0) return 0;
        if(A.size() <= 2) return A.size();
        int result = 0;
        for(int i = 0; i < A.size(); i++){
            HashMap<Double, Integer> hm = new HashMap<Double, Integer>();
            int samex = 1;
            int samep = 0;
            for(int j = 0; j < A.size(); j++){
                if(j != i){
                    if(A.get(j).equals(A.get(i)) && B.get(j).equals(B.get(i)) ){
                        samep++;
                    }
                    if(A.get(j).equals(A.get(i))){
                        samex++;
                        continue;
                    }
                    double k = (double)(B.get(j) - B.get(i)) / (double)(A.get(j) - A.get(i));
                    if(hm.containsKey(k)){
                        hm.put(k,hm.get(k) + 1);
                    }else{
                        hm.put(k, 2);
                    }
                    result = Math.max(result, hm.get(k) + samep);
                }
            }
            result = Math.max(result, samex);
        }
        return result;
    }
    public static ArrayList<Integer> sortArrayInGivenOrder(ArrayList<Integer> A, ArrayList<Integer> B) {
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for (int x : A){
            if (map.containsKey(x)){
                map.put(x,map.get(x)+1);
            }
            else map.put(x,1);
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int x : B){
            if (map.containsKey(x)){
                int temp=map.get(x);
                while(temp!=0){
                    list.add(x);
                    temp--;
                }
                map.remove(x);
            }
        }
        for (int x : map.keySet()){
            int temp=map.get(x);
            while (temp!=0){
                list.add(x);
                temp--;
            }
        }
        return list;
    }
    public static int subArrayWithZeroSum(ArrayList<Integer> A) {
        ArrayList<Integer> prefix = new ArrayList<>();
        int sum=0;
        for(int x :  A){
            sum+=x;
            prefix.add(sum);
        }
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,0);
        int i=1,len=0;
        for(int x : prefix){
            if(!map.containsKey(x)){
                map.put(x,i);
            }
            else{
                len=Math.max(len,i-map.get(x));
            }
            i++;
        }
        return len;
    }

    public static int solveReversePairs(ArrayList<Integer> A) {
        if (A.size()<=1) return 0;
        return mergeSortCount(A,0,A.size()-1);
    }

    private static int mergeSortCount(ArrayList<Integer> a, int low, int high) {
        if (low<high){
            int mid = low + (high-low)/2;
            int count = mergeSortCount(a,low,mid) + mergeSortCount(a,mid+1,high);
            for (int i = 0,j=mid+1; i <=mid && j<=high ;) {
                if (a.get(i)> (long)a.get(j)*2){
                    count+=mid-i+1;
                    j++;
                }
                else i++;
            }
            ArrayList<Integer> arr = new ArrayList<>();
            int right = mid + 1, pos = 0;
            while (pos<a.size()){
                if(a.get(low)<a.get(right)){
                    arr.add(a.get(low));
                }
                else {
                    arr.add(a.get(right));
                }
                pos++;
            }
            a.clear();
            a.addAll(arr);
            return count;
        }
        return 0;
    }


    public static ArrayList<Integer> solveAlternatePositiveNegativeElements(ArrayList<Integer> A) {
        int index=0;
        boolean negative = true;
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i)<0&&negative){
                int loc = i;
                while(loc!=index){
                    Collections.swap(A,loc,loc-1);
                    loc--;
                }
                index++;
                negative=false;
                i=index-1;
            }
            else if (A.get(i)>=0&&!negative){
                int loc = i;
                while(loc!=index){
                    Collections.swap(A,loc,loc-1);
                    loc--;
                }
                index++;
                negative=true;
                i=index-1;
            }
        }
        return A;
    }
}
