import java.util.*;

public class queues {
    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 2, 7, 1, 3, 6));
        ArrayList<Integer> b = new ArrayList<>(Arrays.asList(2, 5, -1, 7, -3, -1, -2));
        //System.out.println(TaskScheduling(a,b));
        //System.out.println(slidingWindowMaximum(a,4));
        //System.out.println(SumOfMinAndMax(b,4));
        //System.out.println(PerfectNumbers(100000));
        System.out.println(NIntegersContainingOnly123(29500));
    }

    public static ArrayList<Integer> NIntegersContainingOnly123(int A) {
        String[] list = new String[A+1];
        list[0] = "";
        int size=1, n=1;
        while (size<=A){
            for (int i = 0; i<n && (size+i)<=A ; i++) {
                list[size+i] ="1" + list[size-n+i];
            }
            for (int i = 0; i<n && (size+n+i)<=A ; i++) {
                list[size+n+i] ="2" + list[size-n+i];
            }
            for (int i = 0; i<n && (size+2*n+i)<=A ; i++) {
                list[size+2*n+i] ="3" + list[size-n+i];
            }
            n*=3;
            size+=n;
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i=1;i<list.length;i++){
            ans.add(Integer.valueOf(list[i]));
        }
        return ans;
    }

    public static String PerfectNumbers(int A) {
        String[] perfecto = new String[A+1];
        perfecto[0]="";
        int size=1,p=1;
        while (size<=A){
            for (int i = 0; i<p && (size+i)<=A ; i++) {
                perfecto[size+i] = "1" + perfecto[size-p+i] + "1";
            }
            for (int i = 0; i<p && (size+p+i)<=A ; i++) {
                perfecto[size+p+i] = "2" + perfecto[size-p+i] + "2";
            }
            p<<=1;
            size+=p;
        }
        return perfecto[A];
    }

    public static int SumOfMinAndMax(ArrayList<Integer> A, int B) {
        ArrayDeque<Integer> minDeque = new ArrayDeque<>();
        ArrayDeque<Integer> maxDeque = new ArrayDeque<>();
        long mod= 1000000007L;
        for (int i = 0; i < B; i++) {
            if (!maxDeque.isEmpty()) {
                while (!maxDeque.isEmpty() && maxDeque.peekLast() < A.get(i)) {
                    maxDeque.pollLast();
                }
            }
            maxDeque.add(A.get(i));
            if (!minDeque.isEmpty()) {
                while (!minDeque.isEmpty() && minDeque.peekLast() > A.get(i)) {
                    minDeque.pollLast();
                }
            }
            minDeque.add(A.get(i));
        }
        long sum=0;
        for (int i = B; i < A.size(); i++) {
            if (!maxDeque.isEmpty()&&!minDeque.isEmpty()){
                sum+= (maxDeque.peek()%mod + minDeque.peek()%mod + mod)%mod;
                sum%= mod;
            }
            if (!maxDeque.isEmpty()&&maxDeque.peek().equals(A.get(i-B))) maxDeque.pollFirst();
            if (!minDeque.isEmpty()&&minDeque.peek().equals(A.get(i-B))) minDeque.pollFirst();
            if (maxDeque.isEmpty()) {
                maxDeque.add(A.get(i));
            }
            while (!maxDeque.isEmpty()&&maxDeque.peekLast()<A.get(i)){
                maxDeque.pollLast();
            }
            maxDeque.add(A.get(i));
            if (minDeque.isEmpty()){
                minDeque.add(A.get(i));
                continue;
            }
            while (!minDeque.isEmpty()&&minDeque.peekLast()>A.get(i)){
                minDeque.pollLast();
            }
            minDeque.add(A.get(i));
        }
        if (!maxDeque.isEmpty()&&!minDeque.isEmpty()){
            sum+= (maxDeque.peek()%mod + minDeque.peek()%mod + mod)%mod;
            sum%= mod;
        }
        return (int) sum;
    }





    public ArrayList<Integer> ReversingElementsOfQueue(ArrayList<Integer> A, int B) {
            Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < B; i++) {
            q.add(A.get(i));
        }
        q=reverse(q);
        ArrayList<Integer> list = new ArrayList<>();
        while (!q.isEmpty()){
            list.add(q.poll());
        }
        for (int i = B; i < A.size(); i++) {
            list.add(A.get(i));
        }
        return list;
    }

    Queue<Integer> reverse (Queue<Integer> q){
        if (q.isEmpty()) {
        }
        else {
            int temp =q.poll();
            q=reverse(q);
            q.add(temp);
        }
        return q;
    }

    public static ArrayList<Integer> slidingWindowMaximum(final List<Integer> A, int B) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < B; i++) {
            if (deque.isEmpty()) {
                deque.add(A.get(i));
                continue;
            }
            while (!deque.isEmpty()&&deque.peekLast()<A.get(i)){
                deque.pollLast();
            }
            deque.add(A.get(i));
        }
        ArrayList<Integer> max = new ArrayList<>();
        for (int i = B; i <A.size() ; i++) {
            max.add(deque.peek());
            if (!deque.isEmpty()&&deque.peek().equals(A.get(i - B))) deque.pollFirst();
            if (deque.isEmpty()) {
                deque.add(A.get(i));
                continue;
            }
            while (!deque.isEmpty()&&deque.peekLast()<A.get(i)){
                deque.pollLast();
            }
            deque.add(A.get(i));
        }
        max.add(deque.peek());
        return max;
    }

    public String FirstNonRepeatingCharacter(String A) {
        Queue<Character> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : A.toCharArray()){
            if (q.isEmpty()){
                q.add(c);
            }
            else if(q.peek()==c){
                q.poll();
            }
            sb.append(q.peek());
        }
        return sb.toString();
    }

    public static int TaskScheduling(ArrayList<Integer> A, ArrayList<Integer> B) {
        Queue<Integer> q = new LinkedList<>(A);
        int count = 0;
        for (int x : B){
            while(!q.isEmpty()&&x!=q.peek()){
                q.add(q.poll());
                count++;
            }
            q.poll();
            count++;
        }
        return count;
    }
}
