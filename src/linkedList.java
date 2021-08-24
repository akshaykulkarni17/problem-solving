import java.util.*;

/**
 * Definition for singly-linked list.
 **/
  class ListNode {
      public int val;
      public ListNode next;
        ListNode(int x) { val = x; next = null; }
 }
 //ListNode with random pointer
//class ListNode {
//    int val;
//    ListNode next, random;
//    ListNode(int x) {
//        val = x;
//        next = random = null;
//    }
//}

public class linkedList {
    public static void main(String[] args) {
        ListNode A = new ListNode(2);
        ListNode temp=A;
        A.next=new ListNode(4);
        A=A.next;
        A.next=new ListNode(3);
        A=A.next;
        A.next=new ListNode(4);
        A=A.next;
        ListNode B = new ListNode(5);
        ListNode temp2 =B;
        B.next=new ListNode(6);
        B=B.next;
        B.next=new ListNode(4);
        B=B.next;
        A.next=new ListNode(7);
        A=A.next;
        A.next=new ListNode(8);
        A=A.next;
        A.next=new ListNode(9);
        //System.out.println(removeNthFromEnd(temp,5));
        //System.out.println(reorderList(temp));
        //System.out.println(KReverseLinkedList(temp,4));
        //System.out.println(SwapListNodesInPairs(temp));
        //System.out.println(deleteDuplicates(temp));
        //System.out.println(addTwoNumbers(temp,temp2));
        //System.out.println(sortLinkedList(temp));
        System.out.println(reverseLinkedListII(temp,1,4));
    }

    public static ListNode reverseLinkedListII(ListNode A, int B, int C) {
        ListNode curr=A;
        ListNode leftToReverse=null;
        ListNode rightToReverse=null;
        while(curr!=null){
            if (--B==1) leftToReverse=curr;
            if (--C==0) rightToReverse=curr;
            curr=curr.next;
        }
        ListNode tail = rightToReverse!=null ? rightToReverse.next : null;
        if (rightToReverse!=null) rightToReverse.next=null;
        ListNode mid = leftToReverse!=null ? reverseList(leftToReverse.next) : reverseList(A);
        if (leftToReverse!=null) leftToReverse.next=mid;
        else A=mid;
        curr=mid;
        while(curr.next!=null){
            curr=curr.next;
        }
        curr.next=tail;
        return A;
    }







    public int LongestPalindromicLinkedList(ListNode A) {
        int result=0;
        ListNode prev=null;
        ListNode curr=A;
        while (curr!=null){
            ListNode next = curr.next;
            curr.next=prev;
            //odd palindrome
            result=Math.max(result,2*countCommon(prev,next)+1);
            //even palindrome
            result=Math.max(result,2*countCommon(curr,next));
            prev=curr;
            curr=next;
        }
        return result;
    }
    private int countCommon(ListNode a, ListNode b) {
        int count=0;
        while (a!=null&&b!=null){
            if (a.val==b.val) count++;
            else break;
            a=a.next;b=b.next;
        }
        return count;
    }




    public static ListNode sortLinkedList(ListNode A) {
        return mergeSort(A);
    }
    private static ListNode mergeSort(ListNode head) {
        if (head==null||head.next==null) return head;
        ListNode mid = findMiddle(head);
        ListNode next = mid.next;
        mid.next=null;
        return sortedMerge(mergeSort(head),mergeSort(next));
    }
    private static ListNode findMiddle(ListNode head) {
        if (head==null) return null;
        ListNode slow=head;
        ListNode fast=head;
        while (fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
    static ListNode sortedMerge(ListNode a, ListNode b){
        if (a==null) return b;
        if (b==null) return a;
        ListNode result = null;
        if (a.val<=b.val){
            result=a;
            result.next=sortedMerge(a.next,b);
        }
        else {
            result=b;
            result.next=sortedMerge(a,b.next);
        }
        return result;
    }





    public ListNode partitionList(ListNode A, int B) {

        ListNode curr = A;
        ListNode first=null;
        ListNode second=null;
        ListNode f = null;
        ListNode s = null;
        while (curr!=null){
            if (curr.val<B){
                if (first==null){
                    first=curr;
                    f=first;
                    curr=curr.next;
                    continue;
                }
                f.next=curr;
                f=f.next;
            }
            else {
                if (second==null){
                    second=curr;
                    s=second;
                    curr=curr.next;
                    continue;
                }
                s.next=curr;
                s=s.next;
            }
            curr=curr.next;
        }
        if (s!=null)s.next=null;
        if (f!=null&&second!=null)f.next=second;
        if (first==null) return second;
        return first;
    }



    public static ListNode addTwoNumbers(ListNode A, ListNode B) {
        ListNode ans = new ListNode(0);
        int carry=0;
        ListNode node = ans;
        while (A!=null||B!=null){
            int sum=carry;
            if (A!=null){
                sum+=A.val;
                A=A.next;
            }
            if (B!=null){
                sum+=B.val;
                B=B.next;
            }
            node.next=new ListNode(sum%10);
            node=node.next;
            carry=sum/10;
        }
        if (carry!=0) node.next=new ListNode(carry);
        return (ans.next);
    }


    public static ListNode mergeTwoLists(ListNode A, ListNode B) {
        ListNode node = new ListNode(0);
        ListNode curr = node;
        while(A!=null&&B!=null){
            if (A.val< B.val){
                curr.next=A;
                A=A.next;
            }
            else {
                curr.next=B;
                B=B.next;
            }
            curr=curr.next;
        }
        while (A!=null){
            curr.next=A;
            A=A.next;
            curr=curr.next;
        }
        while (B!=null){
            curr.next=B;
            B=B.next;
            curr=curr.next;
        }
        return node.next;
    }



    public int isPalindrome(ListNode A) {
        ListNode node = A;
        ArrayList<Integer> arr = new ArrayList<>();
        while (node!=null){
            arr.add(node.val);
            node=node.next;
        }
        int i=0,j=arr.size()-1;
        while (i<j){
            if (!arr.get(i).equals(arr.get(j))) return 0;
            i++;j--;
        }
        return 1;
    }

    public int isPalindromeNoSpace(ListNode A) {
        ListNode slow = A;
        ListNode fast = A;
        while (fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        ListNode right=slow.next;
        slow.next=null;
        right=reverseList(right);
        ListNode left =A;
        while(right!=null){
            if(left.val!=right.val) return 0;
            left=left.next;
            right=right.next;
        }
        return 1;
    }



    public ListNode RemoveLoopFromLinkedList(ListNode A) {
        ListNode node = A;
        ListNode slow = A;
        ListNode fast = A;
        while (slow!=null&&fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast) break;
        }
        if (slow==null||fast==null) return A;
        ListNode start = null;
        while(true){
            slow=slow.next;
            node=node.next;
            if (node==slow)start = node;
            if (start!=null&&node.next==start) {
                node.next=null;
                break;
            }
        }
        return A;
    }

    ListNode cloneList(ListNode A) {
        ListNode node =A;
        while(node.next!=null&&node.next.next!=null){
            ListNode temp = new ListNode(node.val);
            temp.next=node.next;
            node.next=temp;
            node=temp.next;
        }
        node=A;
        while(node.next!=null&&node.next.next!=null){
            ///node.next.random = node.random.next;
            node=node.next.next;
        }
        ListNode ans = new ListNode(0);
        node=A.next;
        while(node.next!=null&&node.next.next!=null){
            ans.next=node;
            node=node.next.next;
        }
        return ans;
    }



    public static ListNode deleteDuplicates(ListNode A) {
        if (A==null||A.next==null) return A;
        ListNode node = new ListNode(0);
        node.next = A;
        ListNode curr = node.next;
        while(curr!=null&&curr.next!=null){
            ListNode l = curr;
            while (l!=null&&l.val==curr.val){
                l=l.next;
            }
            curr.next=l;
            curr=curr.next;
        }
        return node.next;
    }

    public static ListNode SwapListNodesInPairs(ListNode A) {
        ListNode node = new ListNode(0);
        node.next = A;
        ListNode curr = node;
        while (curr.next!=null&&curr.next.next!=null){
            curr.next = swapNodes (curr.next,curr.next.next);
            curr=curr.next.next;
        }
        return node.next;
    }

    private static ListNode swapNodes(ListNode n1, ListNode n2) {
        ListNode next = n2.next;
        n2.next=n1;
        n1.next=next;
        return n2;
    }

    public int MiddleElementOfLinkedList(ListNode A) {
        ListNode slow = A;
        ListNode fast = A;
        while (fast!=null&&fast.next!=null){
            slow= slow.next;
            fast=fast.next.next;
        }
        return slow.val;
    }



    public static ListNode KReverseLinkedList(ListNode A, int B) {
        ListNode temp = A;
        ArrayList<Stack<Integer>> list = new ArrayList<>();
        while(temp!=null){
            Stack<Integer> stk = new Stack<>();
            int seg =B;
            while (seg!=0){
                stk.push(temp.val);
                temp=temp.next;
                seg--;
            }
            list.add(stk);
        }
        temp=A;
        int i=0;
        while (temp!=null){
            Stack<Integer> stk = list.get(i);
            while (!stk.isEmpty()){
                temp.val=stk.pop();
                temp=temp.next;
            }
            i++;
        }
        return A;
    }

    public  static  ListNode reverseList(ListNode A){
        ListNode prev = null;
        ListNode curr = A;
        while(curr!=null){
            ListNode next = curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        return prev;
    }




    public static ListNode reorderList(ListNode A) {
        ListNode slow = A;
        ListNode fast = slow.next;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        ListNode n1 = A;
        ListNode n2 = reverseList(slow.next);
        slow.next=null;
        ListNode node = new ListNode(0);
        ListNode curr = node;
        while(n1!=null || n2!=null){
            if (n1!=null){
                curr.next=n1;
                n1=n1.next;
                curr=curr.next;
            }
            if(n2!=null){
                curr.next=n2;
                n2=n2.next;
                curr=curr.next;
            }
        }
        return node.next;
    }

    public static ListNode reorderListNSpace(ListNode A) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        ListNode temp = A;
        while(temp!=null){
            deque.add(temp.val);
            temp=temp.next;
        }
        temp = A;
        int i=1;
        while (!deque.isEmpty()){
            if (i%2==1) temp.val=deque.pollFirst();
            else temp.val=deque.pollLast();
            i++;
            temp=temp.next;
        }
        return A;
    }

    public static ListNode reorderListN2(ListNode A) {
        ListNode tail = A;
        int size=1;
        while(tail.next!=null){
            tail=tail.next;
            size++;
        }
        if (size%2==1)size/=2;
        else size=size/2-1;
        ListNode curr = A;
        while(size>0){
            tail=A;
            while(tail.next!=null&&tail.next.next!=null){
                tail=tail.next;
            }
            ListNode next = curr.next;
            curr.next=tail.next;
            curr.next.next=next;
            tail.next=null;
            curr=curr.next.next;
            size--;
        }
        return A;
    }




    public static ListNode removeNthFromEnd(ListNode A, int B) {
        int size = 0;
        ListNode temp = A;
        while(temp!=null){
            size++;
            temp=temp.next;
        }
        if (size<=B) return A.next;
        size=size-B-1;
        temp = A;
        while(size>0){
           temp=temp.next;
           size--;
        }
        temp.next=temp.next.next;
        return A;
    }

}
