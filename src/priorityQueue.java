import java.util.*;


class Fraction {
    double numerator;
    double denominator;

    public Fraction(double numerator, double denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }
}
public class priorityQueue {

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(59, 64, 10, 39));
        //System.out.println(ConnectRopes(arr));
        //System.out.println(BthSmallestPrimeFraction(arr,42));
        System.out.println(RunningMedian(arr));
    }
    public ArrayList<Integer> nMaxPairCombinations(ArrayList<Integer> A, ArrayList<Integer> B) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int n = A.size();
        for (int i = 0; i < 9; i++) {
            
        }
        return null;
    }



    public int MinimumLargestElement(ArrayList<Integer> A, int B) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(A);
        while (B!=1){
            pq.add(pq.poll()+1);
            B--;
        }
        return Collections.max(pq);
    }



    public static ArrayList<Integer> RunningMedian(ArrayList<Integer> A) {
        PriorityQueue<Integer> smaller = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> larger = new PriorityQueue<>();
        ArrayList<Integer> medians = new ArrayList<>();
        int median = A.get(0);
        medians.add(median);
        smaller.add(median);
        for (int i = 1; i < A.size(); i++) {
            int x = A.get(i);
            if (smaller.size() > larger.size()){
                if (x<median){
                    larger.add(smaller.poll());
                    smaller.add(x);
                }
                else larger.add(x);
                median=Math.min(smaller.peek(),larger.peek());
                medians.add(median);
            }
            else if (smaller.size()<larger.size()){
                if (x>median){
                    smaller.add(larger.poll());
                    larger.add(x);
                }
                else smaller.add(x);
                median=Math.min(smaller.peek(),larger.peek());
                medians.add(median);
            }
            else {
                if (x<median){
                    smaller.add(x);
                    median=(smaller.peek());
                    medians.add(median);
                }
                else {
                    larger.add(x);
                    median=(larger.peek());
                    medians.add(median);
                }
            }
        }
        return medians;
    }





    public ArrayList<Integer> AthLargestElement(int A, ArrayList<Integer> B) {
        ArrayList<Integer> ans = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int x: B){
            pq.add(x);
            if (pq.size()<A) ans.add(-1);
            else {
                while(pq.size()!=A){
                    pq.poll();
                }
                ans.add(pq.peek());
            }
        }
        return ans;
    }


    public ListNode mergeKLists(ArrayList<ListNode> a) {
        ListNode node = new ListNode(0);
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparing(x-> x.val));
        for (ListNode l : a){
            while(l!=null){
                pq.add(l);
                l=l.next;
            }
        }
        ListNode curr = node;
        while(!pq.isEmpty()){
            curr.next=pq.poll();
            curr=curr.next;
        }
        return node.next;
    }


    public ArrayList<Integer> ProductOf3(ArrayList<Integer> A) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        ArrayList<Integer> ans = new ArrayList<>();
        for (int x : A){
            pq.add(x);
            if (pq.size()<3) ans.add(-1);
            else{
                int a=pq.poll();
                int b=pq.poll();
                int c=pq.poll();
                ans.add(a*b*c);
                pq.add(a);
                pq.add(b);
                pq.add(c);
            }
        }
        return ans;
    }

    public static int ConnectRopes(ArrayList<Integer> A) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(A);
        int tot = 0;
        while (pq.size()>1){
            int sum=pq.poll()+ pq.poll();
            tot+=sum;
            if(!pq.isEmpty()) pq.add(sum);
        }
         return tot;

    }

    public int MaximumArraySumAfterBNegations(ArrayList<Integer> A, int B) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(A);
        while(B!=0){
            int x = pq.poll();
            pq.add(-1*x);
            B--;
        }
        int sum=0;
        for (int x : pq){
            sum+=x;
        }
        return sum;
    }


    public int MagicianAndChocolates(int A, ArrayList<Integer> B) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.addAll(B);
        int sum=0;
        while (A!=0){
            int x = pq.poll();
            sum+=x;
            pq.add(x/2);
            A--;
        }
        return sum;
    }


    public int MishaAndCandies(ArrayList<Integer> A, int B) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(A);
        int tot=0;
        while (!pq.isEmpty()){
            int x = pq.poll();
            if (x<=B){
                int halfX = x/2;
                tot+=halfX;
                pq.add(pq.poll()+x-halfX);
            }
        }
        return tot;
    }


    public int KthSmallestElementInSortedMatrix(ArrayList<ArrayList<Integer>> A, int B) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < A.size(); i++) {
            for (int j = 0; j < A.get(0).size(); j++) {
                pq.add(A.get(i).get(j));
            }
        }
        while(B!=1){
            pq.poll();
            B--;
        }
        return pq.poll();
    }

    public static ArrayList<Integer> BthSmallestPrimeFraction(ArrayList<Integer> A, int B) {
        PriorityQueue<Fraction> pq = new PriorityQueue<>(Comparator.comparing(fraction -> fraction.numerator/fraction.denominator));
        for (int i = 0; i < A.size()-1; i++) {
            for (int j = i+1; j < A.size() ; j++) {
                Fraction f = new Fraction(A.get(i),A.get(j));
                pq.add(f);
            }
        }
        while(B!=1){
            pq.poll();
            B--;
        }
        Fraction f = pq.poll();
        return new ArrayList<>(Arrays.asList((int)f.numerator,(int)f.denominator));
    }
}
