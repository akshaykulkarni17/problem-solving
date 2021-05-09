import java.util.*;

public class solution8May21 {
    public static void main(String[] args) {
        //System.out.println(divideBitManipulation(-2147483648,-1));
        //System.out.println(solveStrangeEquality(8));
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,3,4,6,Integer.MAX_VALUE));
        //System.out.println(singleNumber2(list));
        System.out.println(cntBits(list));
        //System.out.println(reverse(3221225472));
        //81, 13, 2, 7, 96 -> 76
    }
    public static int cntBits(ArrayList<Integer> A) {
        long sum=0;
        long j=1;
        for (int i = 0; i < 32; i++) {
            int oneCount=0;
            for (long x: A){
                if ((x&j)==j){
                    oneCount++;
                }
            }
            sum+=((oneCount%1000000007L)*((A.size()-oneCount)%1000000007L)* 2L)%1000000007L;
            sum%=1000000007;
            j<<=1;
        }
        return (int) sum;
    }
    public static int cntBitsN2(ArrayList<Integer> A) {
        long sum=0;
        for (int i = 0; i < A.size(); i++) {
            for (int j = i+1; j < A.size(); j++) {
                sum+=(Integer.bitCount(A.get(i)^A.get(j))*2);
                sum%=1000000007;
            }
        }
        return (int) sum;
    }
    public static long reverse (long a) {
        StringBuilder sb = new StringBuilder();
        sb.append(Long.toBinaryString(a));
        sb.reverse();
        while (sb.length()<32){
            sb.append("0");
        }
        return Long.parseLong(sb.toString(),2);
    }
    public ArrayList<Integer> solveSingleNumberIIIHashMap(ArrayList<Integer> A) {
        ArrayList<Integer> ans = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        for (int x:A){
            if (map.containsKey(x)){
                map.put(x,map.get(x)+1);
            }
            else map.put(x,1);
        }
        for (int x:map.keySet()) {
            if (map.get(x)==1) ans.add(x);
        }
        return ans;
    }
    public ArrayList<Integer> solveSingleNumberIII(ArrayList<Integer> A) {
        ArrayList<Integer> ans = new ArrayList<>();
        int xor=0;
        for (int x:A){
            xor^=x;
        }
        int i=1;
        while(i<=xor){
            if ((i&xor)==i) break;
            i<<=1;
        }
        int grp1=0;
        int grp0=0;
        for (int x : A){
            if ((x&i)==i){
                grp1^=x;
            }
            else grp0^=x;
        }
        ans.add(grp0);
        ans.add(grp1);
        Collections.sort(ans);
        return ans;
    }

    public static int singleNumber2(final List<Integer> A) {
        int i=1;
        StringBuilder sb = new StringBuilder();
        while(i<= Collections.max(A)){
            int oneCount=0;
            for (int x: A) {
                if ((x & i) == i) {
                    oneCount++;
                }
            }
            sb.append(oneCount%3==0?"0":"1");
            i<<=1;
        }
        return Integer.parseInt(sb.reverse().toString(),2);
    }
    public static int solveStrangeEquality(int A) {
        int a=A;

        int c =Integer.toBinaryString(A).length();
        int count=0;
        while(a!=0){
            a>>=1;
            count++;
        }
        int x=1;
        while(count!=0){
            x<<=1;
            x++;
            count--;
        }
        return c;
    }
    public static int divideBitManipulation(int A, int B) {

        long n = A, m = B;

        // determine sign of the quotient
        int sign = 1;
        if(n < 0) sign *= -1;
        if(m < 0) sign *= -1;

        // remove sign of operands
        n = Math.abs(n);
        m = Math.abs(m);

        // q stores the quotient in computation
        long q = 0, t = 0;

        // test down from the highest bit
        // accumulate the tentative value for valid bits
        for (int i = 31; i >= 0; i--)
        {
            if (t + (m << i) <= n)
            {   t += m << i;
                q |= (long)1 << i;
            }

        }

        // assign back the sign
        if (sign < 0) q = -q;

        // check for overflow and return
        if(q > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        else return (int)q;

    }
    public static int divide(int A, int B) {
        long quo=0;
        boolean isNegative=false;
        if (A<0^B<0) isNegative=true;
        if (A==Integer.MIN_VALUE&&!isNegative) A=Integer.MAX_VALUE;
        long a=Math.abs((long) A);
        long b=Math.abs((long) B);
        while(a>=b){
            a-=b;
            quo++;
        }
        return (int) (isNegative? -1*quo : quo);
    }
}
