import java.util.*;

public class Stacks {

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(5,10,7,3,17));
        ArrayList<Integer> a1 = new ArrayList<>(Arrays.asList(0, 1, 1));
        ArrayList<Integer> a2 = new ArrayList<>(Arrays.asList(1, 0, 1));
        ArrayList<Integer> a3 = new ArrayList<>(Arrays.asList(1, 1, 1));
        ArrayList<Integer> a4 = new ArrayList<>(Arrays.asList(1,4));
        ArrayList<Integer> a6 = new ArrayList<>(Arrays.asList(2,0));
        ArrayList<Integer> a7 = new ArrayList<>(Arrays.asList(2,0));
        ArrayList<Integer> a8 = new ArrayList<>(Arrays.asList(994390, 986616, 976849, 979707, 950477, 968402, 992171, 937674, 933065, 960863, 980981, 937319, 951236, 959547, 991052, 991799, 992213, 941294, 978103, 997198, 960759, 988476, 963517, 980366, 921767, 979757, 977912, 983761, 981869, 947454, 930202, 999086, 973538, 999798, 996446, 944001, 974217, 951595, 942688, 975075, 970973, 970130, 897109, 927660, 862233, 997130, 986068, 954098, 978175, 889682, 988973, 996036, 969675, 985751, 977724, 881538, 988613, 924230, 906475, 915565, 986952, 975702, 994316, 964011, 986848, 983699, 949076, 989673, 981788, 929094, 988310, 926471, 994763, 999736, 980762, 973560, 996622, 934475, 998365, 966255, 998697, 998700, 911868, 983245, 996382, 996992, 953142, 994104, 987303, 853837, 960626, 904203, 998063, 977596, 977868, 996012, 946345, 949255, 988138, 996298, 954933, 965036, 886976, 998628, 990878, 953725, 916744, 985233, 919661, 970903, 986066 ));
        ArrayList<Integer> a9 = new ArrayList<>(Arrays.asList(2,1,5,6,2,3));
        ArrayList<Integer> a99 = new ArrayList<>(Arrays.asList(34, 35, 27, 42, 5, 28, 39, 20, 28));
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        //list.add(a);list.add(a1);list.add(a2);list.add(a3);list.add(a4);list.add(a6);list.add(a7);list.add(a8);list.add(a9);list.add(a99);
        //System.out.println(MaximumFrequencyStack(list));
        //System.out.println(redundantBraces("(a+(a+b))"));
        //System.out.println(AllSubArrays(a));
        System.out.println(Check2BracketExpressions("-(a+((b-c)-(d+e)))","-(a+b-c-d-e)"));
        //-a-f+b-c-d+e
        //System.out.println(NearestSmallerElementOnLeft(a99));
        //System.out.println(largestRectangleAreaHistogram(a9));
        //System.out.println(maxAndMinSubArray(a8));
        list.add(a1);list.add(a2);list.add(a3);
        //System.out.println(MaximumRectangleBinaryMatrix(list));
        //System.out.println(infixToPostfix("q+(c*t)*o+(g*g)+q*(i-a)*p-(i*l)"));

    }

    static int precedence (Character c){
         switch (c) {
            case '+':
            case '-':
                return  1;
            case '/':
            case '*' :
                return 2;
            case '^' :
                 return 3;
            default :
                 return -1;
        }
    }
    static String infixToPostfix(String A){
        Stack<Character> stk= new Stack<>();
        StringBuilder result = new StringBuilder();
        for (char c : A.toCharArray()){
            if (Character.isLetterOrDigit(c)) result.append(c);
            else if (c=='(') stk.push(c);
            else if (c==')') {
                while (!stk.isEmpty()&&stk.peek()!='('){
                    result.append(stk.pop());
                }
                stk.pop();
            }
            else {
                while (!stk.isEmpty()&&precedence(c)<precedence(stk.peek())){
                    result.append(stk.pop());
                }
                stk.push(c);
            }
        }
        while(!stk.isEmpty()&&stk.peek()!='('){
            result.append(stk.pop());
        }
        return result.toString();
    }






    public static int MaximumRectangleBinaryMatrix(ArrayList<ArrayList<Integer>> A) {
        int maxArea = largestRectangleAreaHistogram(A.get(0));
        for (int i = 1; i <A.size() ; i++) {
            for (int j = 0; j < A.get(0).size(); j++) {
                if (!A.get(i).get(j).equals(0)) A.get(i).set(j,A.get(i-1).get(j)+A.get(i).get(j));
            }
            maxArea=Math.max(maxArea,largestRectangleAreaHistogram(A.get(i)));
        }
        return maxArea;
    }


    public static int largestRectangleAreaHistogram(ArrayList<Integer> A) {
        ArrayList<Integer> prevSmallerIndex = new ArrayList<>();
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < A.size(); i++) {
            if (stk.isEmpty()){
                prevSmallerIndex.add(-1);
            }
            else if (A.get(stk.peek())>=A.get(i)){
                while(!stk.isEmpty()&&A.get(stk.peek())>=A.get(i)){
                    stk.pop();
                }
                if (stk.isEmpty()){
                    prevSmallerIndex.add(-1);
                }
                else prevSmallerIndex.add(stk.peek());
            }
            else if (A.get(stk.peek())<A.get(i)){
                prevSmallerIndex.add(stk.peek());
            }
            stk.push(i);
        }
        ArrayList<Integer> nextSmallerIndex = new ArrayList<>();
        stk.clear();
        int n = A.size()-1;
        for (int i = n; i >=0 ; i--) {
            if (stk.isEmpty()){
                nextSmallerIndex.add(A.size());
            }
            else if (A.get(stk.peek())>=A.get(i)){
                while(!stk.isEmpty()&&A.get(stk.peek())>=A.get(i)){
                    stk.pop();
                }
                if (stk.isEmpty()){
                    nextSmallerIndex.add(A.size());
                }
                else nextSmallerIndex.add(stk.peek());
            }
            else if (A.get(stk.peek())<A.get(i)){
                nextSmallerIndex.add(stk.peek());
            }
            stk.push(i);
        }
        Collections.reverse(nextSmallerIndex);
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < A.size(); i++) {
            int height = A.get(i);
            int breadth = (i-prevSmallerIndex.get(i)) + (nextSmallerIndex.get(i)-i) - 1;
            maxArea = Math.max(maxArea, height*breadth);
        }
        return maxArea;
    }

    public static int maxAndMinSubArray(ArrayList<Integer> A) {
        ArrayList<Integer> prevSmallerIndex = new ArrayList<>();
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < A.size(); i++) {
            if (stk.isEmpty()){
                prevSmallerIndex.add(-1);
            }
            else if (A.get(stk.peek())>A.get(i)){
                while(!stk.isEmpty()&&A.get(stk.peek())>A.get(i)){
                    stk.pop();
                }
                if (stk.isEmpty()){
                    prevSmallerIndex.add(-1);
                }
                else prevSmallerIndex.add(stk.peek());
            }
            else if (A.get(stk.peek())<A.get(i)){
                prevSmallerIndex.add(stk.peek());
            }
            stk.push(i);
        }
        ArrayList<Integer> nextSmallerIndex = new ArrayList<>();
        stk.clear();
        for (int i = A.size()-1; i >=0 ; i--) {
            if (stk.isEmpty()){
                nextSmallerIndex.add(A.size());
            }
            else if (A.get(stk.peek())>A.get(i)){
                while(!stk.isEmpty()&&A.get(stk.peek())>A.get(i)){
                    stk.pop();
                }
                if (stk.isEmpty()){
                    nextSmallerIndex.add(A.size());
                }
                else nextSmallerIndex.add(stk.peek());
            }
            else if (A.get(stk.peek())<A.get(i)){
                nextSmallerIndex.add(stk.peek());
            }
            stk.push(i);
        }
        Collections.reverse(nextSmallerIndex);
        ArrayList<Integer> prevGreaterIndex = new ArrayList<>();
        stk.clear();
        for (int i = 0; i < A.size(); i++) {
            if (stk.isEmpty()){
                prevGreaterIndex.add(-1);
            }
            else if (A.get(stk.peek())>A.get(i)){
                prevGreaterIndex.add(stk.peek());
            }
            else if (A.get(stk.peek())<A.get(i)){
                while (!stk.isEmpty()&&A.get(stk.peek())<A.get(i)){
                    stk.pop();
                }
                if (stk.isEmpty()) prevGreaterIndex.add(-1);
                else prevGreaterIndex.add(stk.peek());
            }
            stk.push(i);
        }
        ArrayList<Integer> nextGreaterIndex = new ArrayList<>();
        stk.clear();
        for (int i = A.size()-1; i >=0 ; i--) {
            if (stk.isEmpty()){
                nextGreaterIndex.add(A.size());
            }
            else if (A.get(stk.peek())>A.get(i)){
                nextGreaterIndex.add(stk.peek());
            }
            else if (A.get(stk.peek())<A.get(i)){
                while (!stk.isEmpty()&&A.get(stk.peek())<A.get(i)){
                    stk.pop();
                }
                if (stk.isEmpty()) nextGreaterIndex.add(A.size());
                else nextGreaterIndex.add(stk.peek());
            }
            stk.push(i);
        }
        Collections.reverse(nextGreaterIndex);
        long sum=0;
        long mod=1000000007L;
        for (int i = 0; i <A.size() ; i++) {
            long max = (long) ((i - prevGreaterIndex.get(i))%mod * (nextGreaterIndex.get(i)-i)%mod)%mod;
            long min = (long) ((i - prevSmallerIndex.get(i))%mod * (nextSmallerIndex.get(i)-i)%mod)%mod;
            sum+=(max*A.get(i))%mod;
            sum%=mod;
            sum-=(min*A.get(i))%mod;
            if(sum<0)sum=(sum+mod)%mod;
        }
        return (int) sum;
    }








    public String DoubleCharacterTrouble(String A) {
        Stack<Character> stk = new Stack<>();
        for (char c : A.toCharArray()){
            if (stk.isEmpty()) stk.push(c);
            else if (stk.peek().equals(c)) stk.pop();
            else stk.push(c);
        }
        StringBuilder sb = new StringBuilder();
        while (!stk.isEmpty()) {
            sb.append(stk.pop());
        }
        return sb.reverse().toString();
    }

    public static ArrayList<Integer> NearestSmallerElementOnLeft(ArrayList<Integer> A) {
        ArrayList<Integer> prevSmaller = new ArrayList<>();
        Stack<Integer> stk = new Stack<>();
        for (int x : A){
            if (stk.isEmpty()){
                prevSmaller.add(-1);
            }
            else if (stk.peek()<x){
                prevSmaller.add(stk.peek());
            }
            else if(stk.peek()>x){
                while(!stk.isEmpty()&&stk.peek()>x){
                    stk.pop();
                }
                if (stk.isEmpty()) prevSmaller.add(-1);
                else prevSmaller.add(stk.peek());
            }
            stk.push(x);
        }
        return prevSmaller;
    }

    public int EvaluateExpressionRPN(ArrayList<String> A) {
        Stack<Integer> stk = new Stack<>();
        for (String s : A){
            if (stk.isEmpty()){
                stk.push(Integer.valueOf(s));
            }
            else if(s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/")){
                int op2 = stk.pop();
                int op1 = stk.pop();
                if(s.equals("+")){
                    stk.push(op1+op2);
                }
                else if(s.equals("-")){
                    stk.push(op1-op2);
                }
                else if(s.equals("*")){
                    stk.push(op1*op2);
                }
                else {
                    stk.push(op1/op2);
                }
            }
            else stk.push(Integer.valueOf(s));
        }
        return stk.pop();
    }



    public static int Check2BracketExpressions(String A, String B) {
        Stack<Boolean> negative = new Stack<>();
        int[] a = new int[26];
        char[] strA = A.toCharArray();
        negative.push(false);
        for (int i = 0; i < strA.length; i++) {
            if (strA[i]=='('&&i!=0) {
                if (strA[i-1]=='-') negative.push(!negative.peek());
                if (strA[i-1]=='(') negative.push(negative.peek());
            }
            else if (strA[i]-'a'>=0&&strA[i]-'a'<26){
                char sign;
                if (i==0) sign='+';
                else if (strA[i-1]=='+'&&negative.peek()) sign='-';
                else if (strA[i-1]=='-'&&negative.peek()) sign='+';
                else if (strA[i-1]=='('&&negative.peek()) sign='-';
                else if (strA[i-1]=='('&&!negative.peek()) sign='+';
                else sign=strA[i-1];
                if (sign=='+') a[strA[i]-'a']++;
            }
            else if (strA[i]==')') negative.pop();
        }

        negative = new Stack<>();
        int[] b = new int[26];
        char[] strB = B.toCharArray();
        negative.push(false);
        for (int i = 0; i < strB.length; i++) {
            if (strB[i]=='('&&i!=0) {
                if (strB[i-1]=='-') negative.push(!negative.peek());
                if (strA[i-1]=='(') negative.push(negative.peek());
            }
            else if (strB[i]-'a'>=0&&strB[i]-'a'<26){
                char sign;
                if (i==0) sign='+';
                else if (strB[i-1]=='+'&&negative.peek()) sign='-';
                else if (strB[i-1]=='-'&&negative.peek()) sign='+';
                else if (strB[i-1]=='('&&negative.peek()) sign='-';
                else if (strB[i-1]=='('&&!negative.peek()) sign='+';
                else sign=strB[i-1];
                if (sign=='+') b[strB[i]-'a']++;
            }
            else if (strB[i]==')') negative.pop();
        }
        for (int i = 0; i < 26; i++) {
            if (a[i]!=b[i]) return 0;
        }
        return 1;
     }



    public static int AllSubArrays(ArrayList<Integer> A) {
        Stack<Integer> stk = new Stack<>();
        int xor =0;
        for (int x :  A){
            if (stk.isEmpty()) stk.push(x);
            else if (x>stk.peek()){
                while (!stk.isEmpty()&&x>stk.peek()){
                    xor=Math.max(xor,x^stk.pop());
                }
                if (!stk.isEmpty()) xor=Math.max(xor,x^stk.peek());
                stk.push(x);
            }
            else if (x<stk.peek()){
                xor=Math.max(xor,x^stk.peek());
                stk.push(x);
            }
        }
        return xor;
    }


    public int balancedParentheses(String A) {
        Stack<Character> stk = new Stack<>();
        for (char c : A.toCharArray()){
            if (c=='{'||c=='('||c=='[') stk.push(c);
            else if (c==')'&&stk.peek()=='(') stk.pop();
            else if (c=='}'&&stk.peek()=='{') stk.pop();
            else if (c==']'&&stk.peek()=='[') stk.pop();
            else return 0;
        }
        if (stk.isEmpty()) return 1;
        return 0;
    }




    public static int redundantBraces(String A) {
        Stack<Character> stk = new Stack<>();
        Stack<Character> st = new Stack<>();
        char[] str = A.toCharArray();
        // Iterate through the given expression
        for (char ch : str) {

            // if current character is close parenthesis ')'
            if (ch == ')') {
                char top = st.peek();
                st.pop();

                // If immediate pop have open parenthesis '('
                // duplicate brackets found
                int flag = 1;

                while (top != '(') {

                    // Check for operators in expression
                    if (top == '+' || top == '-'
                            || top == '*' || top == '/') {
                        flag = 0;
                    }

                    // Fetch top element of stack
                    top = st.peek();
                    st.pop();
                }

                // If operators not found
                if (flag == 1) {
                    return 1;
                }
            } else {
                st.push(ch); // push open parenthesis '(',
            }                // operators and operands to stack
        }
        return 0;
    }




    public static ArrayList<Integer> MaximumFrequencyStack(ArrayList<ArrayList<Integer>> A) {
        Map<Integer,Stack<Integer>> stacks = new HashMap<>();
        ArrayList<Integer> operations = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        for (ArrayList<Integer> command : A){
            if (command.get(0).equals(1)){
                int num=command.get(1);
                if (map.containsKey(num)){
                    map.put(num,map.get(num)+1);
                }
                else map.put(num,1);
                if (stacks.containsKey(map.get(num))){
                    Stack<Integer> tempstk = stacks.get(map.get(num));
                    tempstk.push(num);
                    stacks.put(map.get(num),tempstk);
                }
                else{
                    Stack<Integer> tempstk = new Stack<>();
                    tempstk.push(num);
                    stacks.put(map.get(num),tempstk);
                }
                operations.add(-1);
            }
            else if (command.get(0).equals(2)){
                int maxFreq=0;
                for (int x : stacks.keySet()){
                    maxFreq=Math.max(maxFreq,x);
                }
                Stack<Integer> tempstk = stacks.get(maxFreq);
                int num=tempstk.pop();
                operations.add(num);
                if (tempstk.isEmpty()) stacks.remove(maxFreq);
                if (maxFreq==1) map.remove(num);
            }
        }
        return operations;
    }



    public ArrayList<Integer> SortStackUsingAnotherStack(ArrayList<Integer> A) {
        Stack<Integer> stk1 = new Stack<>();
        Stack<Integer> stk2 = new Stack<>();
        for (int x: A){
            if (stk1.isEmpty()){
                stk1.push(x);
            }
            else if (x>stk1.peek()){
                while (!stk1.isEmpty()&&stk1.peek()<x){
                    stk2.push(stk1.pop());
                }
                stk1.push(x);
                while (!stk2.isEmpty()){
                    stk1.push(stk2.pop());
                }
            }
            else stk1.push(x);
        }
        ArrayList<Integer> list = new ArrayList<>();
        while (!stk1.isEmpty()){
            list.add(stk1.pop());
        }
        return list;
    }





    Integer min;
    Stack<Integer> stk;
    public void push(int x) {
        if(stk.isEmpty()){
            stk.push(x);
            min=x;
        }
        else if (x<min){
            stk.push(2*x-min);
            min=x;
        }
        else stk.push(x);
    }

    public void pop() {
        if(!stk.isEmpty()) {
            Integer temp = stk.pop();
            if (temp<min){
                min=2*min-temp;
            }
        }
    }

    public int top() {
        if (stk.isEmpty()) return -1;
        else if (stk.peek()<min){
            return min;
        }
        return stk.peek() ;
    }

    public int getMin() {
        if (stk.isEmpty()) return -1;
        return min;
    }







    public int passingGame(int A, int B, ArrayList<Integer> C) {
        Stack<Integer> stk = new Stack<>();
        stk.push(B);
        for (int x : C){
            if(x==0) stk.pop();
            else stk.push(x);
        }
        return stk.peek();
    }
}
