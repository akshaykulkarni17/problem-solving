import java.util.*;

public class backTrackingProblems {
    public static void main(String[] args) {
        ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(0, 1));
        ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(2, 0  ));
        //ArrayList<Integer> c3 = new ArrayList<>(Arrays.asList(0, 0, 2, -1));
//        ArrayList<Integer> c4 = new ArrayList<>(Arrays.asList(0, 0, 3, 0, 1, 0, 0, 8, 0));
//        ArrayList<Integer> c5 = new ArrayList<>(Arrays.asList( 9, 0, 0, 8, 6, 3, 0, 0, 5));
//        ArrayList<Integer> c6 = new ArrayList<>(Arrays.asList(0, 5, 0, 0, 9, 0, 6, 0, 0));
//        ArrayList<Integer> c7 = new ArrayList<>(Arrays.asList(1, 3, 0, 0, 0, 0, 2, 5, 0));
//        ArrayList<Integer> c8 = new ArrayList<>(Arrays.asList( 0, 0, 0, 0, 0, 0, 0, 7, 4 ));
//        ArrayList<Integer> c9 = new ArrayList<>(Arrays.asList(0, 0, 5, 2, 0, 6, 3, 0, 0 ));
        ArrayList<ArrayList<Integer>> board = new ArrayList<>();
        board.add(c1);board.add(c2);//board.add(c3);//board.add(c4);board.add(c5);board.add(c6);board.add(c7);board.add(c8);board.add(c9);
        //solveSudoku(board);
        //System.out.println(generateParenthesis(4));
        //System.out.println(isPalindrome("aa"));
        //System.out.println(partition("abcbad"));
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2, 3, 6, 7));
        int[] arr= {1,2,3};
        int min = Arrays.stream(arr).min().getAsInt();
        //System.out.println(combinationSum(list,7));
        //System.out.println(solveNQueens(4).size());
        System.out.println(solveUniquePathsIII(board));
    }
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
