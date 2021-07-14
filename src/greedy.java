import java.sql.Array;
import java.util.*;

class jobs{
    int startTime;
    int endTime;
}
class Car{
    int time;
    int profit;

    public Car(int time, int profit) {
        this.time = time;
        this.profit = profit;
    }
}
class children{
    int rating;
    int candy;

    public children(int rating, int candy) {
        this.rating = rating;
        this.candy = candy;
    }
}
public class greedy {

    public static void main(String[] args) {
        ArrayList<Integer> s = new ArrayList<>(Arrays.asList(1, 3, 1, -7, 2, 7, -1, 7, -6, 7, -8, 3, 3, -5, 4, 4, -5 ));
        ArrayList<Integer> e = new ArrayList<>(Arrays.asList(2, 4, 6, 8, 4, 4, 4, 11, 8, 11, 8, 7, 7, 7, 6, 10, 4  ));
        //System.out.println(FreeCars(s,e));
        //System.out.println(DistributeCandy(s));
        //System.out.println(seats("....x..xx...x.."));
        //System.out.println(anotherCoinProblem(147));
        //System.out.println(BinaryStrings("00010110",3));
        System.out.println(TheShipCompany(4,3,new ArrayList<>(Arrays.asList(2,1,1))));
    }

    public static ArrayList<Integer> TheShipCompany(int A, int B, ArrayList<Integer> C) {
        int passenger =A;
        int max=0;
        int min=0;
        PriorityQueue<Integer> minQ = new PriorityQueue<>(C);
        while(passenger!=0){
            int price=minQ.poll();
            if (price>1) minQ.add(price-1);
            min+=price;
            passenger--;
        }
        passenger=A;
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());
        maxQ.addAll(C);
        while(passenger!=0){
            int price=maxQ.poll();
            if (price>1) maxQ.add(price-1);
            max+=price;
            passenger--;
        }
        return new ArrayList<>(Arrays.asList(max,min));
    }



    public static int BinaryStrings(String A, int B) {
        char[] cc = A.toCharArray();
        int swaps=0;
        for (int i = 0; i <= cc.length-B; i++) {
            if (cc[i]=='0'){
                swaps++;
                for (int j = i; j <i+B  ; j++) {
                    if (cc[j]=='0') cc[j]='1';
                    else if (cc[j]=='1') cc[j]='0';
                }
            }
        }
        for (char c : cc){
            if (c=='0') return -1;
        }
        return swaps;
    }

    public static int anotherCoinProblem(int A) {
        int power=0;
        while (Math.pow(5,power)<=A){
            power++;
        }
        power--;
        int notes=0;
        while (A>0){
            int div= (int) (A/Math.pow(5,power));
            notes+=div;
            A-=div*Math.pow(5,power);
            power--;
        }
        return notes;
    }



    public static int seats(String A) {
        int cost=0;
        boolean occupied = false;
        int guests=0;
        for (char c : A.toCharArray()){
            if(c=='x')guests++;
        }
        for (char c : A.toCharArray()){
            if (occupied&&c=='.')cost++;
            if (c=='x'){
                occupied=true;
                guests--;
            }
            if (guests==0)occupied=false;
        }
        return cost;
    }


    public static int DistributeCandy(ArrayList<Integer> A) {
        ArrayList<children> child = new ArrayList<>();
        for (int x: A){
            child.add(new children(x,1));
        }
        for (int i = 1; i < child.size(); i++) {
            if (child.get(i-1).rating<child.get(i).rating){
                child.get(i).candy=child.get(i-1).candy+1;
            }
        }
        Collections.reverse(child);
        for (int i = 1; i < child.size(); i++) {
            if (child.get(i-1).rating<child.get(i).rating){
                if(child.get(i).candy<=child.get(i-1).candy) child.get(i).candy=child.get(i-1).candy+1;
            }
        }
        int tot=0;
        for (children c : child ){
            tot+=c.candy;
        }
        return tot;
    }


    public static int FreeCars(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Car> cars = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            cars.add(new Car(A.get(i),B.get(i)));
        }
        cars.sort(Comparator.comparing(a->-1*a.profit));
        cars.sort(Comparator.comparing(a -> a.time));
        PriorityQueue<Integer> profits = new PriorityQueue<>();
        int profit=0;
        int time=0;
        for (int i = 0; i < cars.size(); i++) {
            if (time<cars.get(i).time){
                profits.add(cars.get(i).profit);
                time++;
            }
            else if (profits.peek()<cars.get(i).profit){
                profits.poll();
                profits.add(cars.get(i).profit);
            }
        }
        while (!profits.isEmpty()){
            profit+=profits.poll();
        }
        return profit;
    }



    public static int FinishMaximumJobs(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<jobs> jobs = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            jobs j = new jobs();
            j.startTime=A.get(i);
            j.endTime=B.get(i);
            jobs.add(j);
        }
        jobs.sort(Comparator.comparing(a -> a.endTime));
        int time=0;
        int finish=0;
        for (jobs job : jobs){
            if (job.startTime>=time){
                finish++;
                time=job.endTime;
            }
        }
        return finish;
    }
}
