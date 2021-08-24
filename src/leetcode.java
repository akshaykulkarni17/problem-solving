import java.util.Collections;
import java.util.HashMap;
import java.util.*;
import java.util.Map;


class fuelstop{
    int dist;
    int capacity;
    fuelstop(int x ,int y){
        this.dist=x;
        this.capacity=y;
    }
}
public class leetcode {


    public static void main(String[] args) {
        //System.out.println(totalFruit(new int[]{3,3,3,1,2,1,1,2,3,3,4}));
        //System.out.println(minRefuelStops(100,25,new int[][]{{25,25},{50,25},{75,25}}));
//        String s = "I am cloud engineer working at cloud. The cloud engineer stores a cloud of data";
//        List<String> offensiveWords = new ArrayList<>();
//        offensiveWords.add("cloud");
//        offensiveWords.add("cloud engineer");
//        Collections.sort(offensiveWords, Collections.reverseOrder());
//        String[] stringArray = s.split(" ");
//
//        for(String word : offensiveWords){
//            while(true) {
//                String newString = "";
//                int index = s.indexOf(word);
//                if(index == -1) break;
//                newString = s.substring(0, index) + "" + s.substring(index + word.length());
//                s = newString;
//            }
//        }
//
//        System.out.println(s);
       // System.out.println(canCompleteCircuit(new int[]{1,2,3,4,5},new int[]{3,4,5,1,2}));
    }



    public static int totalFruit(int[] fruits) {
        Map<Integer,Integer> map = new HashMap<>();
        int i=0;
        int j=0;
        int max=Integer.MIN_VALUE;
        while(j<fruits.length){
            map.putIfAbsent(fruits[j],0);
            map.put(fruits[j],map.get(fruits[j])+1);
            if(map.size()<=2){
                max=Math.max(max,j-i+1);
                j++;
            }
            else {
                while(i<fruits.length&&map.size()!=2){
                    map.put(fruits[i],map.get(fruits[i])-1);
                    if(map.get(fruits[i])==0) map.remove(fruits[i]);
                    i++;
                }
                map.put(fruits[j],map.get(fruits[j])-1);
            }
        }
        return max;
    }

    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
        int fuel=startFuel;
        int stop=0;
        int currdist=0;
        while(currdist+fuel<target){
            PriorityQueue<fuelstop> pq = new PriorityQueue(new Comparator<fuelstop>(){
                @Override
                public int compare(fuelstop f1,fuelstop f2){
                    return -1*(f1.capacity-f2.capacity);
                }
            });
            for(int[] s : stations){
                if(s[0]>currdist&&s[0]<=currdist+fuel) {
                    pq.add(new fuelstop(s[0], s[1]));
                }
            }
            if(pq.isEmpty()) return -1;
            fuelstop next = pq.poll();
            fuel=fuel-next.dist+currdist+next.capacity;
            currdist=next.dist;
            stop++;
        }
        return stop;
    }
}
