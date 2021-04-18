import java.util.*;
public class solution10Apr21 {

    public static void main(String[] args) {
        ArrayList<Integer> arrive = new ArrayList<>(Arrays.asList(35, 8, 23, 22, 35, 6, 48, 45, 33, 43, 37, 12, 42, 3, 31, 38, 5, 33, 15));
        ArrayList<Integer> depart = new ArrayList<>(Arrays.asList( 43, 32, 34, 46, 74, 50, 95, 62, 59, 79, 83, 19, 88, 34, 75, 42, 42, 50, 58 ));
        //System.out.println(hotelBookingsPossible(arrive,depart,11));
        ArrayList<Integer> array = new ArrayList<>(Arrays.asList(1,2,3,4,5 ));
        int[] arr= {347148, 221001, 394957, 729925, 276769, 40726, 552988, 29952, 184491, 146773, 418965, 307, 219145, 183037, 178111, 81123, 109199, 683929, 422034, 346291, 11434, 7327, 340473, 316152, 364005, 359269, 170935, 105784, 224044, 22563, 48561, 165781, 9329, 357681, 169473, 175031, 605611, 374501, 6607, 329965, 76068, 836137, 103041, 486817, 195549, 107317, 34399, 56907, 37477, 189690, 36796, 376663, 39721, 177563, 174179, 183646, 217729, 408031, 429122, 631665, 282941, 526797, 262186, 306571, 63613, 57501, 70685, 226381, 1338, 9360, 130360, 20300, 400906, 87823, 180349, 108813, 18181, 119185, 1, 102611, 63591, 12889, 311185, 383896, 8701, 76077, 75481, 386017, 153553, 304913, 383455, 105948, 142885, 1, 12610, 137005, 119185, 16948, 66171, 123683};
        //System.out.println(KthRowOfPascalsTriangle(10));
        System.out.println(solveSubArrayOR(array));
    }
    public static int solveSubArrayOR(ArrayList<Integer> A) {
        int n = A.size();
        int[] index = new int[32];
        long answer=0;
        for (int i = 1; i <=A.size(); i++) {
            long temp = A.get(i-1);
            for (int j = 0; j < 32 ; j++) {
                long power = 1 << j;
                if (((temp&power)!=0)){
                    answer += power*i;
                    index[j]=i;
                }
                else if (index[j]!=0){
                    answer += power * index[j];
                }
            }
        }
        return (int) (answer%1000000007);
    }
    public static int solveSubArrayOR2(ArrayList<Integer> A) {
        double sumOr=0;
        double mod=1000000007;
        int max = Collections.max(A);
        int maxBit=(int)Math.ceil(Math.log(max) + 1);
        double n = A.size();
        double totalSubArray=n*(n+1)/2;
        for (int i = 0; i < maxBit; i++) {
            int zeroCount=0;
            double zeroSubArray=0;
            ArrayList<Integer> list  = new ArrayList<>();
            for (int j = 0; j < A.size(); j++) {
                list.add(A.get(j)&1);
                A.set(j,A.get(j)>>1);
            }
            for (int x:
                 list) {
                if (x==0) zeroCount++;
                if (x==1&&zeroCount!=0){
                    zeroSubArray+=zeroCount*(zeroCount+1)/2;
                    zeroCount=0;
                }
            }
            if (zeroCount!=0)zeroSubArray+=zeroCount*(zeroCount+1)/2;
            sumOr+=((Math.pow(2,i)%mod)*((totalSubArray-zeroSubArray)%mod))%mod;
            sumOr%=mod;
        }
        return (int) sumOr;
    }
    public ArrayList<Integer> solveFindDuplicateRowsInBinaryMatrix(ArrayList<ArrayList<Integer>> A) {
        ArrayList<Integer> list = new ArrayList<>();
        Set<ArrayList<Integer>> set = new HashSet<>();
        for (int i = 0; i < A.size(); i++) {
            if (set.contains(A.get(i))){
                list.add(i);
            }
            set.add(A.get(i));
        }
        return list;
    }
    public static ArrayList<Integer> KthRowOfPascalsTriangle(int A) {
        ArrayList<ArrayList<Integer>> pascal= new ArrayList<>();
        for (int i = 0; i <= A; i++) {
            pascal.add(new ArrayList<>());
            for (int j = 0; j <= i; j++) {
                if (j==0||j==i){
                    pascal.get(i).add(1);
                }
                else {
                    pascal.get(i).add(pascal.get(i-1).get(j)+pascal.get(i-1).get(j-1));
                }
            }
        }
        return pascal.get(pascal.size()-1);
    }
    public static boolean hotelBookingsPossible(ArrayList<Integer> arrive, ArrayList<Integer> depart, int K) {
        ArrayList<ArrayList<Integer>> sortedArriveDepart = new ArrayList<>();
        Collections.sort(arrive);
        Collections.sort(depart);
        for (int i = 0; i < arrive.size(); i++) {
            if (arrive.get(i)!=depart.get(i)){
                sortedArriveDepart.add(new ArrayList<>(Arrays.asList(depart.get(i),1)));
                sortedArriveDepart.add(new ArrayList<>(Arrays.asList(arrive.get(i),0)));
            }
        }
        Collections.sort(sortedArriveDepart, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                return o1.get(0).compareTo(o2.get(0));
            }
        });

        int roomsOccupied=0;
        for (int i = 0; i < sortedArriveDepart.size(); i++) {
            if (sortedArriveDepart.get(i).get(1)==0){
                roomsOccupied++;
            }
            else roomsOccupied--;
            if (roomsOccupied>K) return false;
        }
        return true;
    }
}
