import java.util.*;

public class twoPointers {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList( 2, 4, 6, 8 ));
        //System.out.println(pairsWithGivenDifference(list,3));
        //System.out.println(pairsWithGivenSumII(list,1000000000));
        //System.out.println(SubArrayWithGivenSum(list,5));
        //System.out.println(AnotherCountRectangles(list,977));
        //System.out.println(countSubArrays(list));
        System.out.println(ClosestPairFromSortedArrays(list,list2,9));
        //System.out.println(maxContinuousSeriesOfOnes(list,1));
        //System.out.println(threeSumClosest(list,-3);

    }

    public ArrayList<Integer> scaredRishika(int A, int B, ArrayList<Integer> C) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> powers = new ArrayList<>();
        int a=0,b=0;
        while(Math.pow(A,a)<=Integer.MAX_VALUE&&Math.pow(B,b)<=Integer.MAX_VALUE){
            if (Math.pow(A,a)<Math.pow(B,b)){
                powers.add((int) Math.pow(A,a));
                a++;
            }
            else powers.add((int) Math.pow(B,b));
        }

        return list;
    }

    public static ArrayList<Integer> maxContinuousSeriesOfOnes(ArrayList<Integer> A, int B) {
        int i=0,j=0;
        int max=Integer.MIN_VALUE;
        int start=0;
        int end=0;
        while (i<A.size()){
            if (j<A.size()&&A.get(j).equals(1)){
                j++;
            }
            else if (j<A.size()&&B>0&&A.get(j).equals(0)){
                B--;
                j++;
            }
            else if (j<A.size()&&B==0&&A.get(j).equals(0)){
                while (B==0){
                    if (A.get(i).equals(0)) B++;
                    i++;
                }
            }
            else i++;
            if (max<j-i){
                max=j-i;
                start=i;
                end=j;
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (int k = start; k <end ; k++) {
            ans.add(k);
        }
        return ans;
    }




    public static ArrayList<Integer> ClosestPairFromSortedArrays(ArrayList<Integer> A, ArrayList<Integer> B, int C) {
        int min=Integer.MAX_VALUE;
        int i=0,j=B.size()-1;
        ArrayList<Integer> list = null;
        int start=-1;
        int end=-1;
        while (i<A.size()&&j>=0){
            int temp = Math.abs(A.get(i)+B.get(j)-C);
            if(min>=temp){
                if (list!=null&&temp==min) {
                    if (i < start || (start == i && j < end)) {
                        list = new ArrayList<>(Arrays.asList(A.get(i), B.get(j)));
                        min = Math.abs(A.get(i) + B.get(j) - C);
                        start = i;
                        end = j;
                    }
                }
                else {
                        list=new ArrayList<>(Arrays.asList(A.get(i),B.get(j)));
                        min=Math.abs(A.get(i)+B.get(j)-C);
                        start=i;
                        end=j;
                }
            }
            if (A.get(i)+B.get(j)<C) i++;
            else if (A.get(i)+B.get(j)==C){
                i++;j--;
            }
            else j--;
        }
        return list;
    }



    public int minimizeArray3Pointers(final List<Integer> A, final List<Integer> B, final List<Integer> C) {
        int a=0,b=0,c=0;
        int minimized=Integer.MAX_VALUE;
        while (a<A.size()&&b<B.size()&&c<C.size()){
            int max=Math.max(Math.abs(A.get(a)-B.get(b)),Math.max(Math.abs(A.get(a)-C.get(c)),Math.abs(B.get(b)-C.get(c))));
            minimized=Math.min(minimized,max);
            if (A.get(a)<B.get(b)){
                if (A.get(a)<C.get(c)){
                    a++;
                }
                else c++;
            }
            else if (B.get(b)<C.get(c)){
                b++;
            }
            else c++;
        }
        return minimized;
    }


    public static int countSubArrays(ArrayList<Integer> A) {
        long count =0;
        long mod = 1000000007L;
        int i=0,j=0;
        Set<Integer> set = new HashSet<>();
        set.add(A.get(0));
        while (j<A.size()-1){
            if (set.size()==j-i+1){
                count+=set.size();
                count%=mod;
                j++;
                set.add(A.get(j));
            }
            else {
                while(!A.get(i).equals(A.get(j))){
                    set.remove(A.get(i));
                    i++;
                }
                i++;
            }
        }
        if (set.size()!=j-i+1){
            while(!A.get(i).equals(A.get(j))){
                set.remove(A.get(i));
                i++;
            }
        }
        count+=set.size();
        count%=mod;
        return (int) count;
    }

    public static int threeSumClosestAllPositive(ArrayList<Integer> A, int B) {
        if(A.size()<3) {
            int result=0;
            for (int x : A){
                result+=x;
            }
            return result;
        }
        Collections.sort(A);
        int result= A.get(0)+A.get(1)+A.get(2);
        for (int i = 0; i < A.size()-1; i++) {
            int start=i+1;
            int end=A.size()-1;
            while (start<end){
                int mid=start+(end-start)/2;
                int sum = A.get(i) + A.get(start) + A.get(end);
                if (sum>B) end=mid-1;
                else start=mid+1;
                if(Math.abs(sum-B)<Math.abs(result-B)){
                    result=sum;
                }
            }
        }
        return result;
    }


    public static int threeSumClosest(ArrayList<Integer> A, int B) {
        if(A.size()<3) {
            int result=0;
            for (int x : A){
                result+=x;
            }
            return result;
        }
        Collections.sort(A);
        int result= A.get(0)+A.get(1)+A.get(2);
        for (int i = 0; i < A.size()-1; i++) {
            int start=i+1;
            int end=A.size()-1;
            while (start<end){
                int sum = A.get(i) + A.get(start) + A.get(end);
                if (sum>B) end-=1;
                else start+=1;
                if(Math.abs(sum-B)<Math.abs(result-B)){
                    result=sum;
                }
            }
        }
        return result;
    }

    public int maxAreaContainer(ArrayList<Integer> A) {
        int i=0;
        int j=A.size()-1;
        long maxArea = Integer.MIN_VALUE;
        while(i<j){
            long area= (long) (j - i) *Math.min(A.get(i),A.get(j));
            maxArea=Math.max(maxArea,area);
            if(A.get(i)<A.get(j)) i++;
            else j--;
        }
        return (int) maxArea;
    }

    public static int AnotherCountRectangles(ArrayList<Integer> A, int B) {
        int i=0;
        long count=0;
        long mod=1000000007L;
        while((long)A.get(i)*A.get(i)<B){
            int start=i;
            int end=A.size()-1;
            int j=0;
            while(start<=end){
                int mid=start+(end-start)/2;
                if((long)A.get(i)*A.get(mid)>=B){
                    end=mid-1;
                }
                else {
                    j=mid;
                    start=mid+1;
                }
            }
            if(i!=j) count+=(j-i)* 2L ;
            count++;
            count%=mod;
            i++;
        }
        return (int) count;
    }



    public static ArrayList<Integer> SubArrayWithGivenSum(ArrayList<Integer> A, int B) {
        int i=0;
        int j=0;
        long sum=A.get(0);
        while(i<A.size()){
            if (sum==B){
                return new ArrayList<>(A.subList(i,j+1));
            }
            else if(sum<B&&j<A.size()-1){
                j++;
                sum+=A.get(j);
            }
            else {
                sum-=A.get(i);
                i++;
            }
        }
        return new ArrayList<>(Collections.singleton(-1));
    }

    public static int pairsWithGivenDifference(ArrayList<Integer> A, int B) {
        Collections.sort(A);
        Set<ArrayList<Integer>> set= new HashSet<>();
        int i=0;
        int j=0;
        while(i<A.size()&&j<A.size()){
            if(B==Math.abs(A.get(i)-A.get(j))){
                ArrayList<Integer> temp = new ArrayList<>(Arrays.asList(A.get(i),A.get(j)));
                Collections.sort(temp);
                set.add(temp);
                i++;j++;
            }
            else if(B>Math.abs(A.get(i)-A.get(j))){
                j++;
            }
            else i++;
        }
        return set.size();
    }

    public static int pairsWithGivenSumII(ArrayList<Integer> A, int B) {
        Map<Integer,Long> map = new HashMap<>();
        for (int x : A){
            if (map.containsKey(x)){
                map.put(x,map.get(x)+1L);
            }
            else map.put(x,1L);
        }
        Set<Integer> set = new HashSet<>(A);
        A.clear();
        A.addAll(set);
        int i=0,j=A.size()-1;
        long count=0;
        long mod=1000000007L;
        while(i<j){
            long temp=(A.get(i)+A.get(j));
            if (temp==(long)B){
                count+=(map.get(A.get(i))*map.get(A.get(j)))%mod;
                count%=mod;
                i++;j--;
            }
            else if(temp<(long) B) i++;
            else j--;
        }
        for (int x: map.keySet()){
            long temp=x* 2L;
            if(temp==B) {
                count+=(map. get(x)*(map. get(x)-1)/2)%mod;
                count%=mod;
            }
        }
        return (int) count;
    }
}
