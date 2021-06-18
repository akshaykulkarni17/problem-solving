import java.util.*;

public class backTrackingProblems {
    public static void main(String[] args) {
        ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(1,1,1));
        ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(1,1,1  ));
        ArrayList<Integer> c3 = new ArrayList<>(Arrays.asList(1,1 ,1 ));
        ArrayList<Integer> c4 = new ArrayList<>(Arrays.asList(1,4  ));
        ArrayList<Integer> c5 = new ArrayList<>(Arrays.asList(8,10  ));
        //ArrayList<Integer> c3 = new ArrayList<>(Arrays.asList(0, 0, 2, -1));
//        ArrayList<Integer> c4 = new ArrayList<>(Arrays.asList(0, 0, 3, 0, 1, 0, 0, 8, 0));
//        ArrayList<Integer> c5 = new ArrayList<>(Arrays.asList( 9, 0, 0, 8, 6, 3, 0, 0, 5));
//        ArrayList<Integer> c6 = new ArrayList<>(Arrays.asList(0, 5, 0, 0, 9, 0, 6, 0, 0));
//        ArrayList<Integer> c7 = new ArrayList<>(Arrays.asList(1, 3, 0, 0, 0, 0, 2, 5, 0));
//        ArrayList<Integer> c8 = new ArrayList<>(Arrays.asList( 0, 0, 0, 0, 0, 0, 0, 7, 4 ));
//        ArrayList<Integer> c9 = new ArrayList<>(Arrays.asList(0, 0, 5, 2, 0, 6, 3, 0, 0 ));
        ArrayList<ArrayList<Integer>> board = new ArrayList<>();
        board.add(c1);board.add(c2);board.add(c3);//board.add(c4);board.add(c5);//board.add(c6);board.add(c7);board.add(c8);board.add(c9);
        //solveSudoku(board);
        //System.out.println(generateParenthesis(4));
        //System.out.println(isPalindrome("aa"));
        //System.out.println(partition("abcbad"));
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(10, 1, 2, 7, 6, 1, 5));
        int[] arr= {1,2};
        int min = Arrays.stream(arr).min().getAsInt();
        //System.out.println(combinationSum(list,7));
        //System.out.println(solveNQueens(4).size());
        //System.out.println(solveUniquePathsIII(board));
        //System.out.println(solveNumberOfSquarefulArrays(list));
        //System.out.println(solveMinimumSwapsToFormPairs(5,list,board));
        //System.out.println(combinationSumII(list,8));
        ArrayList<String> dictionaries = new ArrayList<>(Arrays.asList("cat", "cats", "and", "sand", "dog"));
        //System.out.println(wordBreakII("catsanddog",dictionaries));
        //System.out.println(solveRemoveInvalidParentheses("))m))w"));
        System.out.println(solveVerticalAndHorizontalSums(3,board,2));
    }

    ////
    static boolean possible;
    static ArrayList<ArrayList<Integer>> matrix;
    public static int solveVerticalAndHorizontalSums(int A, ArrayList<ArrayList<Integer>> B, int C) {
        possible=false;
        matrix=new ArrayList<>(B);
        verticalAndHorizontalSums(A,C);
        if (possible) return 1;
        return 0;
    }
    public static void verticalAndHorizontalSums(int A, int C) {
        int N=matrix.size();
        int M=matrix.get(0).size();
        for (int row = 0; row <= N; row++) {
            for (int col = 0; col < M; col++) {
                possible=isValidMatrix(C);
                if(possible) return;
                if(row==N) return;
                int colSum=0;
                for (ArrayList<Integer> list : matrix) {
                    colSum += list.get(col);
                    if (colSum > C || colSum<-100000) break;
                }
                int rowSum=0;
                for (int i = 0; i < M; i++) {
                    rowSum+=matrix.get(row).get(i);
                    if (rowSum > C || rowSum<-100000) break;
                }
                if (rowSum<=C&&colSum<=C) continue;
                if (A==0) return;
                matrix.get(row).set(col,-1*matrix.get(row).get(col));
                A--;
                colSum=0;
                for (ArrayList<Integer> list : matrix) {
                    colSum += list.get(col);
                    if (colSum > C || colSum<-100000) break;
                }
                rowSum=0;
                for (int i = 0; i < M; i++) {
                    rowSum+=matrix.get(row).get(i);
                    if (rowSum > C || rowSum<-100000) break;
                }
                if (rowSum<=C&&colSum<=C){
                    verticalAndHorizontalSums(A,C);
                    if(possible) return;
                    matrix.get(row).set(col,-1*matrix.get(row).get(col));
                    A++;
                }
            }
        }
    }

    private static boolean isValidMatrix(int sum) {

        for (ArrayList<Integer> list : matrix) {
            int rowSum = 0;
            for (int j = 0; j < matrix.get(0).size(); j++) {
                rowSum += list.get(j);
                if (rowSum > sum || rowSum<-100000) return false;
            }

        }
        for (int i = 0; i < matrix.get(0).size(); i++) {
            int colSum=0;
            for (ArrayList<Integer> list : matrix) {
                colSum += list.get(i);
                if (colSum > sum || colSum<-100000) return false;
            }

        }
        return true;
    }


    //////
    public static ArrayList<String> solveRemoveInvalidParentheses(String A) {
        ArrayList<String> answer = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        String temp = null;
        boolean level =false;
        q.add(A);
        visited.add(A);
        while (!q.isEmpty()){
            A=q.peek();q.remove();
            if (isValidParentheses(A)){
                answer.add(A);
                level=true;
            }
            if (level) continue;
            for (int i = 0; i < A.length(); i++) {
                if ((A.charAt(i) !='(') && (A.charAt(i) !=')')) continue;
                temp=A.substring(0,i)+A.substring(i+1);
                if (!visited.contains(temp)) q.add(temp);
                visited.add(temp);
            }

        }
        return answer;
    }

    private static boolean isValidParentheses(String s) {
        Stack<Character> stk = new Stack<>();
        for (char c : s.toCharArray()){
            if(!stk.isEmpty()&&stk.peek()=='('&&c==')'){
                stk.pop();
            }
            else if(c=='('||c==')')stk.push(c);
        }
        return stk.isEmpty();
    }

    //////////
    static ArrayList<String> wordBreakII ;
    public static ArrayList<String> wordBreakII(String A, ArrayList<String> B) {
        Set<String> set = new HashSet<>(B);
        wordBreakII = new ArrayList<>();
        recursiveWordBreak(A,set,"",A.length());
        return wordBreakII;
    }

    private static void recursiveWordBreak(String A,Set<String> set, String sentence,int n) {
        for (int i = 0; i <= n; i++) {
            String str = A.substring(0,i);
            if(doesExist(str,set)){
                if (i==n){
                    sentence+=str;
                    wordBreakII.add(sentence);
                    return;
                }
                recursiveWordBreak(A.substring(i),set,sentence+str+" ",n-i);
            }
        }
    }

    private static boolean doesExist(String str, Set<String> set) {
        String[] list = str.split(" ");
        for (String s : list){
            if(!set.contains(s)){
                return false;
            }
        }
        return true;
    }


    /////////
    static Set<ArrayList<Integer>> combinationSumSet;
    public static ArrayList<ArrayList<Integer>> combinationSumII(ArrayList<Integer> A, int B) {
        ArrayList<Integer> temp = new ArrayList<>();
        combinationSumSet=new HashSet<>();
        boolean[] used = new boolean[A.size()];
        recursiveCombinationSum(A,temp,used,B);
        return new ArrayList<>(combinationSumSet);
    }

    private static void recursiveCombinationSum(ArrayList<Integer> A, ArrayList<Integer> temp, boolean[] used, int sum) {
        if (sum==0){
            ArrayList<Integer> sumArray = new ArrayList<>(temp);
            Collections.sort(sumArray);
            combinationSumSet.add(sumArray);
        }
        else {
            for (int i = 0; i < A.size(); i++) {
                if (!used[i]&&A.get(i)<=sum){
                    used[i]=true;
                    temp.add(A.get(i));
                    recursiveCombinationSum(A,temp,used,sum-A.get(i));
                    temp.remove(temp.size()-1);
                    used[i]=false;
                }
            }
        }
    }

    //return minimum swaps
    public static int solveMinimumSwapsToFormPairs(int A, ArrayList<Integer> B, ArrayList<ArrayList<Integer>> C) {
        //to store indices of array elements
        int[] indexes= new int[A*2+1];
        //store index of each element in array index
        for (int i = 0; i <B.size() ; i++) {
            indexes[B.get(i)]=i;
        }
        //Arraylist of pairs
        int[] pairs = new int[2*A+1];
        for (ArrayList<Integer> list : C) {
            int x = list.get(0), y = list.get(1);
            pairs[x] = y;
            pairs[y] = x;
        }
        //create array
        int[] array = new int[2*A+1];
        int i=1;
        for (int x : B){
            array[i]=x;
            i++;
        }
        //call recursive function
        return minSwapsUtil(indexes,array,pairs,1,2*A);
    }
    private static int minSwapsUtil(int[] indexes, int[] array, int[] pairs, int i, int n) {
        //all pairs processed
        if (i>n) return 0;
        //if current pair is valid, skip and process others
        if (pairs[array[i]]==array[i+1]){
            return minSwapsUtil(indexes,array,pairs,i+2,n);
        }
        //current pair is not valid
        //swap pair of array(i) and array(i+1)
        //recursively compute minimum
        int one = array[i + 1];
        int indexTwo = i + 1;
        int indexOne = indexes[pairs[array[i]]];
        int two = array[indexes[pairs[array[i]]]];
        //array[i+1] ^= two ^ 1 ;
        array[i+1] =array[i + 1] ^ array[indexOne] ^  (array[indexOne] = array[i + 1]);
        swapIndex(indexes,one,indexOne,two,indexTwo);
        //count swaps if this move is made
        int a = minSwapsUtil(indexes,array,pairs,i+2,n);
        //backtrack to previous config, revert earlier swap
        array[i+1] =array[i + 1] ^ array[indexOne] ^  (array[indexOne] = array[i + 1]);
        swapIndex(indexes,one,indexTwo,two,indexOne);
        //swap pair of array(i) and array(i+1) (reverse of earlier)
        //recursively compute minimum
        one = array[i];
        indexOne = indexes[pairs[array[i + 1]]];
        two = array[indexes[pairs[array[i + 1]]]];
        indexTwo = i;
        array[i] = array[i] ^ array[indexOne] ^ (array[indexOne]=array[i]);
        swapIndex(indexes,one,indexOne,two,indexTwo);
        //count swaps for the earlier swap is reversed
        int b= minSwapsUtil(indexes,array,pairs,i+2,n);
        //backtrack to previous config, revert earlier swap
        array[i] = array[i] ^ array[indexOne] ^ (array[indexOne]=array[i]);
        swapIndex(indexes,one,indexTwo,two,indexOne);
        // return min of two cases
        return 1+Math.min(a,b);
    }
    private static void swapIndex(int[] indexes, int a, int ai, int b, int bi) {
        indexes[a] =ai;
        indexes[b]= bi;
    }




    /////////
    static int squareCount;
    public static int solveNumberOfSquarefulArrays(ArrayList<Integer> A) {
        if (A.size()==1){
            return isSquare(A.get(0),0) ? 1 : 0;
        }
        squareCount=0;
        Collections.sort(A);
        helperSquarefulArray(new ArrayList<Integer>(),A,new boolean[A.size()],-1);
        return squareCount;
    }
    private static boolean isSquare(Integer integer, int lastNumber) {
        double sqrt = Math.sqrt(integer+lastNumber);
        return (sqrt-Math.floor(sqrt))==0;
    }
    private static void helperSquarefulArray(ArrayList<Integer> temp, ArrayList<Integer> A, boolean[] used, int lastNumber) {
        if (temp.size()==A.size()){
            squareCount++;
        }
        else{
            for (int i = 0; i < A.size(); i++) {
                if (used[i] || (i>0 && A.get(i).equals(A.get(i-1)) && !used[i-1]))continue;
                //if (used[i]) continue; at the end divide answer by count of all duplicate value
                if (lastNumber!=-1){
                    if (!isSquare(A.get(i),lastNumber)) continue;
                }
                used[i]=true;
                temp.add(A.get(i));
                helperSquarefulArray(temp,A,used,A.get(i));
                temp.remove(temp.size()-1);
                used[i]=false;
            }
        }
    }



    ///////////////
    static final String mappings[]
            = { "0","1","abc", "def", "ghi", "jkl", "mno","pqrs", "tuv", "wxyz" };
    public static ArrayList<String> letterPhone(String input){
        if (input.length()==0){
            return new ArrayList<>(Collections.singleton(""));
        }
        char ch = input.charAt(0);
        String rest = input.substring(1);
        ArrayList<String> restStrings = letterPhone(rest);
        ArrayList<String> answer = new ArrayList<>();
        String maps = mappings[ch-'0'];
        for (String str : restStrings){
            for (int i = 0; i < maps.length(); i++) {
                answer.add(maps.charAt(i)+str);
            }
        }
        Collections.sort(answer);
        return answer;
    }








    /////////////
    static int count=0;
    public static int solveUniquePathsIII(ArrayList<ArrayList<Integer>> A) {
        int startRow = 0;
        int startCol = 0;
        int zeroes=0;
        for (int i = 0; i < A.size(); i++) {
            for (int j = 0; j < A.get(0).size(); j++) {
                if (A.get(i).get(j).equals(1)){
                    startRow=i;
                    startCol=j;
                }
                if (A.get(i).get(j).equals(0)){
                    zeroes++;
                }
            }
        }
        boolean[][] visited = new boolean[A.size()][A.get(0).size()];
        int zeroCount=0;
        uniquePaths(A,startRow,startCol,zeroes,zeroCount,visited);
        return count;
    }
    static void uniquePaths(ArrayList<ArrayList<Integer>> A, int row, int col, int zeroes,int zeroCount,boolean[][] visited){
        int n=A.size(), m=A.get(0).size();
        visited[row][col]=true;
        if (A.get(row).get(col).equals(2)&&zeroes==zeroCount){
            count++;
            visited[row][col]=false;
            return;
        }
        if (A.get(row).get(col).equals(0)) zeroCount++;
        //going up
        if (row>0 && !visited[row-1][col] && !A.get(row-1).get(col).equals(-1)){
            uniquePaths(A,row-1,col,zeroes,zeroCount,visited);
        }
        //going down
        if (row<n-1 && !visited[row+1][col] && !A.get(row+1).get(col).equals(-1)){
            uniquePaths(A,row+1,col,zeroes,zeroCount,visited);
        }
        //going left
        if (col>0 && !visited[row][col-1] && !A.get(row).get(col-1).equals(-1)){
           uniquePaths(A,row,col-1,zeroes,zeroCount,visited);
        }
        //going right
        if (col<m-1 && !visited[row][col+1] && !A.get(row).get(col+1).equals(-1)){
            uniquePaths(A,row,col+1,zeroes,zeroCount,visited);
        }
        visited[row][col]=false;
    }
    static ArrayList<ArrayList<String>> answer;
    public static ArrayList<ArrayList<String>> solveNQueens(int a) {
         char[][] board = new char[a][a];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < a; j++) {
                board[i][j]='.';
            }
        }
        answer=new ArrayList<>();
        queensUtil(board,0);
        return answer;
    }
    static void queensUtil(char[][] board, int col){
        if (col==board.length){
            answer.add(construct(board));
            return ;
        }
        for (int row = 0; row < board.length; row++) {
            if (isQueenSafe(board,row,col)){
                board[row][col] = 'Q';
                queensUtil(board,col+1);
                board[row][col] = '.';
            }
        }
    }
    private static ArrayList<String> construct(char[][] board) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            String s = new String(board[i]);
            list.add(s);
        }
        return list;
    }
    private static boolean isQueenSafe(char[][] board, int row, int col) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j]=='Q'){
                    if (row-col==i-j || row+col==i+j || row==i || col==j) return false;
                }
            }
        }
        return true;
    }








    ///////////////
    static ArrayList<ArrayList<Integer>> combinationSum;
    public static ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> A, int B) {
        Set<Integer> set = new HashSet<>(A);
        A.clear();
        A.addAll(set);
        Collections.sort(A);
        ArrayList<Integer> temp = new ArrayList<>();
        combinationSum=new ArrayList<>();
        findCombinations(A,B,0,temp);
        return combinationSum;
    }

    private static void findCombinations(ArrayList<Integer> A, int sum, int index, ArrayList<Integer> temp) {
        if (sum==0){
            combinationSum.add(new ArrayList<>(temp));
            return;
        }
        for (int i = index; i <A.size() ; i++) {
            if (A.get(i)<=sum) {
                temp.add(A.get(i));
                findCombinations(A, sum - A.get(i), i, temp);
                temp.remove(A.get(i));
            }
        }
    }






    ///////////////
    static ArrayList<ArrayList<String>> palindromeList;
    public static ArrayList<ArrayList<String>> partition(String a) {
        palindromeList= new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        return palindromePartitioning(a,temp,0);

    }
    static ArrayList<ArrayList<String>> palindromePartitioning(String a,ArrayList<String> temp, int partition){
        String str = "";
        ArrayList<String> list = new ArrayList<>(temp);
        if (partition==0) temp.clear();

        for (int i = partition; i < a.length(); i++) {
            str=str+a.charAt(i);
            if (isPalindrome(str)){
                temp.add(str);
                if (i+1<a.length()){
                    palindromeList=palindromePartitioning(a,temp,i+1);
                }
                else palindromeList.add(temp);
                temp=new ArrayList<>(list);
            }
        }
        return palindromeList;
    }
    static boolean isPalindrome(String s){
        if (s.length()==1) return true;
        if(s.charAt(0)!=s.charAt(s.length()-1)) return false;
        if (s.length()==2) return true;
        return isPalindrome(s.substring(1,s.length()-1));
    }



    //////////////////
    static ArrayList<String> parenthesis;
    public static ArrayList<String> generateParenthesis(int A) {
        parenthesis=new ArrayList<>();
        //StringBuilder sb = new StringBuilder();
        parenthesis(A,A,"",A);
        return parenthesis;
    }

    private static void parenthesis(int open, int close, String s,int len) {
        if (s.length()==len*2){
            parenthesis.add(s.toString());
            return;
        }
        if (open>0){
            parenthesis(open-1,close,s+"(",len);
        }
        if (close>open){
            parenthesis(open,close-1,s+")",len);
        }
    }







    /////////////
    public static void solveSudoku(ArrayList<ArrayList<Integer>>  board) {
        if(board == null || board.size() == 0)
            return;
        sudoku(board);
    }
    private static boolean sudoku(ArrayList<ArrayList<Integer>> board) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i).get(j).equals(0)){
                    for (int num = 1; num <= 9; num++) {
                        if (isSudokuSafe(board,i,j,num)) {
                            board.get(i).set(j, num);
                            if (sudoku(board)) {
                                return true;
                            } else {
                                board.get(i).set(j, 0);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        System.out.println(board);
        return true;
    }
    private static boolean isSudokuSafe(ArrayList<ArrayList<Integer>> board, int row, int col, int num) {
        for (int i = 0; i < 9 ; i++) {
            if (board.get(row).get(i).equals(num)){
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (board.get(i).get(col).equals(num)){
                return false;
            }
        }
        int boxRowStart = row - row%3;
        int boxColStart = col - col%3;
        for (int i = boxRowStart; i < boxRowStart+3; i++) {
            for (int j = boxColStart; j < boxColStart+3; j++) {
                if (board.get(i).get(j).equals(num)){
                    return false;
                }
            }
        }
        return true;
    }
}
