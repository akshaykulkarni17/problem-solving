public class ola {

    //Find the maximum sum which can be achieved by summing elements with condition that no two array elements should be consecutive taken.
    //10 2 3 20
    //100 300

    int maxSum(int[] A){
        int include=A[0];
        int exclude=0;
        for (int i = 1; i < A.length; i++) {
            int temp=Math.max(include,exclude);
            include = Math.max(include,exclude )+ A[i];
            exclude=temp;
        }
        return Math.max(include,exclude);
    }
}
//include =10
//e=0
//
//i=1
//i=10
//e=10
//
//i=10
//i=13
//e=10
//
//i=3
//i=22
//e=13


