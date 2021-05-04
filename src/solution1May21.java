import java.util.*;

public class solution1May21 {

    public class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
  }
    public static void main(String[] args) {
        ArrayList<Integer> l = new ArrayList<>(Arrays.asList(1,4,3,2));
        //System.out.println(firstMissingPositive(l));
        System.out.println(nextPermutation(l));
    }
    public static ArrayList<Integer> nextPermutation(ArrayList<Integer> A) {
        int i=A.size()-1;
        //1432
        while(i>0&&A.get(i)<A.get(i-1)){
            i--;
        }
        int swapIndex=i-1;
        if(swapIndex==-1) {
            Collections.sort(A);
            return A;
        }
        int swap=A.get(swapIndex);
        i=A.size()-1;
        while(A.get(i)<swap){
            i--;
        }
        A.set(swapIndex,A.get(i));
        A.set(i,swap);
        Collections.sort(A.subList(swapIndex+1,A.size()));
        return A;
    }
    public static ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        intervals.sort(Comparator.comparing(i->i.start));
        Stack<Interval> stack = new Stack<>();
        stack.add(intervals.get(0));
        for (Interval i: intervals) {
            Interval temp=stack.peek();
            if (i.start<=temp.end&&i.end>=temp.end){
                temp.end=i.end;
                stack.pop();
                stack.push(temp);
            }
            else if(i.start>temp.end){
                stack.push(i);
            }
        }
        Stack<Interval> stack2 = new Stack<>();
        while (!stack.isEmpty()){
            stack2.push(stack.pop());
        }
        ArrayList<Interval> answer = new ArrayList<>();
        while (!stack2.isEmpty()){
            answer.add(stack2.pop());
        }
        return answer;
    }
    public static ArrayList<Interval> mergeSortedIntervals(ArrayList<Interval> intervals, Interval newInterval) {
        if (newInterval.end<newInterval.start) {
            int temp = newInterval.start;
            newInterval.start=newInterval.end;
            newInterval.end=newInterval.start;
        }
        ArrayList<Interval> answer = new ArrayList<>();
        for (int i = 0; i < intervals.size(); i++) {
            if (intervals.get(i).start> newInterval.start){
                answer.add(newInterval);
            }
            answer.add(intervals.get(i));
        }
        if(answer.size()==intervals.size()) answer.add(newInterval);
        Stack<Interval> stack = new Stack<>();
        stack.add(answer.get(0));
        for (int i = 1; i < answer.size(); i++) {
            Interval temp = stack.peek();
            if (temp.end>=answer.get(i).start){
                temp.end=answer.get(i).end;
                stack.pop();
                stack.push(temp);
            }
            else if(temp.end<answer.get(i).end){
                stack.push(answer.get(i));
            }
        }
        Stack<Interval> stk = new Stack<>();
        while (!stack.isEmpty()){
            stk.add(stack.pop());
        }
        answer.clear();
        while (!stk.isEmpty()){
            answer.add(stk.pop());
        }
        return answer;
    }
    public static ArrayList<Interval> mergeIntervals(ArrayList<Interval> intervals, Interval newInterval) {
        if (newInterval.end<newInterval.start) {
            int temp = newInterval.start;
            newInterval.start=newInterval.end;
            newInterval.end=newInterval.start;
        }
//        intervals.add(newInterval);
//        intervals.sort(Comparator.comparing(interval -> interval.start));
        ArrayList<Interval> answer = new ArrayList<>();
        for (int i = 0; i < intervals.size(); i++) {
            if (intervals.get(i).start> newInterval.start){
                answer.add(newInterval);
            }
            answer.add(intervals.get(i));
        }
        if(answer.size()==intervals.size()) answer.add(newInterval);
        intervals.clear();
        intervals.addAll(answer);
        Stack<Interval> stack = new Stack<>();
        stack.add(intervals.get(0));
        for (int i = 1; i < intervals.size(); i++) {
            Interval temp = stack.peek();
            if(intervals.get(i).start<=temp.end&&intervals.get(i).end>=temp.end){
                temp.end=intervals.get(i).end;
                stack.pop();
                stack.push(temp);
            }
            else if(temp.end<intervals.get(i).end){
                stack.push(intervals.get(i));
            }
        }
        ArrayList<Interval> ans = new ArrayList<>();
        Stack<Interval> stack2 = new Stack<>();
        while (!stack.isEmpty()){
            stack2.push(stack.pop());
        }
        while (!stack2.isEmpty()){
            ans.add(stack2.pop());
        }
        return ans;
    }
    public static ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        if (newInterval.end<newInterval.start) {
            int temp = newInterval.start;
            newInterval.start=newInterval.end;
            newInterval.end=newInterval.start;
        }
        if (intervals.get(0).start>=newInterval.end){
            ArrayList<Interval> ans = new ArrayList<>();
            ans.add(newInterval);
            ans.addAll(intervals);
            return ans;
        }
        else if(intervals.get(intervals.size()-1).end<=newInterval.start){
            ArrayList<Interval> ans = new ArrayList<>();
            ans.addAll(intervals);
            ans.add(newInterval);
            return ans;
        }
        else {
            int startPosition=-1;
            for (int i = 0; i < intervals.size()-1; i++) {
                if ((newInterval.start>=intervals.get(i).start&&newInterval.start<=intervals.get(i).end)||(newInterval.start>=intervals.get(i).end)&&newInterval.start<=intervals.get(i+1).start){
                   startPosition=i+1;
                }

            }
            int endPosition=-1;
            for (int i = 0; i < intervals.size()-1; i++) {

            }
            int position = 0;
            for (int i = 1; i < intervals.size(); i++) {
                if(intervals.get(i-1).end>=newInterval.start&&newInterval.end>=intervals.get(i).start){
                    position=i;
                }
            }
            if (position==-1) return intervals;
            ArrayList<Interval> ans = new ArrayList<>();
            for (int i = 0; i < position; i++) {
                ans.add(intervals.get(i));
            }
            ans.add(newInterval);
            for (int i = position; i <intervals.size() ; i++) {
                ans.add(intervals.get(i));
            }
            return ans;
        }
    }
    public static int firstMissingPositive(ArrayList<Integer> A) {
        int j=A.size()+1;
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i)<=0){
                A.set(i,j);
                j++;
            }
        }
        for (int i = 0; i < A.size(); i++) {
            int temp = Math.abs(A.get(i));
            if (temp<=A.size()){
                if (A.get(temp-1)>0){
                    A.set(temp-1,-1*A.get(temp-1));
                }
            }
        }
        int missing=1;
        for (int x: A) {
            if(x<0) missing++;
            else break;
        }
        return missing;
    }
    // Each element occurs (i+1)*(j+1)*(n-i)*(n-j) times
    public int SumOfAllSubMatrices(ArrayList<ArrayList<Integer>> A) {
        int n = A.size();
        int sum=0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum+=A.get(i).get(j)*(i+1)*(j+1)*(n-i)*(n-j);
            }
        }
        return sum;
    }
}
