import java.util.*;

/**
 * Definition for binary tree
 *  */
  class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) {
       val = x;
       left=null;
       right=null;
      }
  }

public class trees {

    public static void main(String[] args) {
        TreeNode A = new TreeNode(1);
        A.left = new TreeNode(3);
        A.right = new TreeNode(7);
        A.left.left = new TreeNode(2);
        A.left.right = new TreeNode(5);
        A.right.left = new TreeNode(10);
        A.right.right = new TreeNode(9);
        A.left.right.left = new TreeNode(12);
        A.right.left.right = new TreeNode(18);
        //System.out.println(verticalOrderTraversal(A));
        System.out.println(topViewOfTree(A));
        //System.out.println(deserializeArray(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, -1, -1, -1, -1, -1, -1))));
        //System.out.println(boundaryTraversalOfBinaryTree(A));
    }

    Stack<Integer> stk;
    public int EqualTreePartition(TreeNode A) {
        stk=new Stack<>();
        count(A);
        int total = stk.pop();
        while (!stk.isEmpty()){
            int x = stk.pop();
            if (x==total-x){
                return 1;
            }
        }
        return 0;
    }
    private int count(TreeNode a) {
        if (a==null) return 0;
        int leftSum = count(a.left);
        int rightSum = count(a.right);
        int curr = a.val + leftSum +rightSum;
        stk.push(curr);
        return curr;
    }


    public TreeNode flattenBinaryTreeToLinkedList(TreeNode a) {
        flattenTree(a);
        return a;
    }
    private void flattenTree(TreeNode a) {
        if (a==null||(a.left==null&&a.right==null)) return;
        if (a.left!=null){
            flattenTree(a.left);
            TreeNode temp = a.right;
            a.right = a.left;
            a.left = null;
            TreeNode curr = a.right;
            while (curr.right!=null){
                curr=curr.right;
            }
            curr.right=temp;
        }
        flattenTree(a.right);
    }


    public ArrayList<Integer> mergeTwoBSTs(TreeNode A, TreeNode B) {
        ArrayList<Integer> a =inorderTraversal(A);
        ArrayList<Integer> b = inorderTraversal(B);
        ArrayList<Integer> ans = new ArrayList<>();
        int i=0,j=0;
        while (i<a.size()&&j<b.size()){
            if (a.get(i)<b.get(j)){
                ans.add(a.get(i));
                i++;
            }
            else{
                ans.add(a.get(j));
                j++;
            }
        }
        while (i<a.size()){
            ans.add(a.get(i));
            i++;
        }
        while (j<b.size()){
            ans.add(b.get(j));
            j++;
        }
        return ans;
    }





    public int hasPathSum(TreeNode A, int B) {
        return pathSum(A,B) ? 1:0;
    }

    private boolean pathSum(TreeNode a, int sum) {
        if (a==null) return false;
        if(a.left == null && a.right == null && sum - a.val == 0) return true;
        return pathSum(a.left,sum-a.val) || pathSum(a.right,sum-a.val);
    }


    public TreeNode invertTree(TreeNode A) {
        if (A==null) return null;
        TreeNode temp = A.left;
        A.left=A.right;
        A.right=temp;
        invertTree(A.left);
        invertTree(A.right);
        return A;
    }




    public boolean isLeaf(TreeNode A) {
        if (A==null) return false;
        if (A.left==null&&A.right==null) return true;
        return false;
    }
    int isSumBinaryTree (TreeNode A){
        int leftSum=0;
        int rightSum=0;
        if (A==null||isLeaf(A))  return 1;
        if (isSumBinaryTree(A.left)!=0 && isSumBinaryTree(A.right)!=0){
            if (A.left==null) leftSum=0;
            else if (isLeaf(A.left)) leftSum=A.left.val;
            else leftSum=2*A.left.val;

            if (A.right==null) rightSum=0;
            else if (isLeaf(A.right)) rightSum=A.right.val;
            else rightSum=2*A.right.val;

            if (A.val==leftSum+rightSum) return 1;
            else return 0;
        }
        return 0;
    }


    public int lca(TreeNode A, int B, int C) {
        TreeNode node= leastCommonAncestor(A,B,C);
        return node.val;
    }
    public TreeNode leastCommonAncestor(TreeNode root, int v1, int v2) {
        if (root==null||root.val==v1 || root.val==v2) return root;
        TreeNode left = leastCommonAncestor(root.left,v1,v2);
        TreeNode right = leastCommonAncestor(root.right,v1,v2);
        if (left!=null && right!=null){
            return root;
        }
        return left!=null ? left : right;
    }





    static ArrayList<Integer> boundary;
    public static ArrayList<Integer> boundaryTraversalOfBinaryTree(TreeNode A) {
        boundary=new ArrayList<>();
        if (A==null) return boundary;
        boundary.add(A.val);
        printBoundaryLeft(A.left);
        printLeaves(A.left);
        printLeaves(A.right);
        printBoundaryRight(A.right);
        return boundary;
    }

    private static void printBoundaryRight(TreeNode root) {
        if (root==null) return;
        if (root.right!=null){
            printBoundaryRight(root.right);
            boundary.add(root.val);
        }
        else if (root.left!=null){
            printBoundaryRight(root.left);
            boundary.add(root.val);
        }

    }

    private static void printLeaves(TreeNode root) {
        if (root==null) return;
        printLeaves(root.left);
        if (root.left==null&&root.right==null){
            boundary.add(root.val);
        }
        printLeaves(root.right);
    }

    private static void printBoundaryLeft(TreeNode root) {
        if (root==null) return;
        if (root.left!=null){
            boundary.add(root.val);
            printBoundaryLeft(root.left);
        }
        else if (root.right!=null){
            boundary.add(root.val);
            printBoundaryLeft(root.right);
        }
    }


    public TreeNode sortedArrayToBST(final int[] A) {
        return saToBst(A,0,A.length-1);
    }
    TreeNode saToBst(int[] a,int start, int end){
        if (start>end) return null;
        int mid = (start+end)/2;
        TreeNode node = new TreeNode(a[mid]);
        node.left = saToBst(a,start,mid-1);
        node.right= saToBst(a,mid+1,end);
        return node;

    }





    public int isValidBST(TreeNode A) {
        return isBST(A,Integer.MIN_VALUE,Integer.MAX_VALUE) ? 1 : 0;
    }

    private boolean isBST(TreeNode a, int minValue, int maxValue) {
        if (a==null) return true;
        if (a.val<minValue||a.val>maxValue) return false;
        return isBST(a.left,minValue,a.val) && isBST(a.right,a.val,maxValue);
    }



    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode A) {
        ArrayList<ArrayList<Integer>> zigzag = new ArrayList<>();
        travel(A,0,zigzag);
        return zigzag;
    }
    private void travel(TreeNode curr, int level, ArrayList<ArrayList<Integer>> zigzag) {
        if (curr==null) return;
        if (zigzag.size()<=level){
            ArrayList<Integer> newLevel = new ArrayList<>();
            zigzag.add(newLevel);
        }
        ArrayList<Integer> collection = zigzag.get(level);
        if (level%2==0) collection.add(curr.val);
        else collection.add(0,curr.val);
        travel(curr.left,level+1,zigzag);
        travel(curr.right,level+1,zigzag);
    }


    public int minDepth(TreeNode A) {
        if (A==null) return 0;
        if (A.left==null&&A.right==null) return 1;
        if (A.left==null) return minDepth(A.right)+1;
        if (A.right==null) return minDepth(A.left)+1;
        return Math.min(minDepth(A.left),minDepth(A.right))+1;
    }


    static int t;
    public static TreeNode deserializeArray(ArrayList<Integer> A) {
        t=0;
        return deserializer(A);
    }
    static TreeNode deserializer(ArrayList<Integer> A){
        if (A.get(t).equals(-1)) return null;
        TreeNode root = new TreeNode(A.get(t));
        t++;
        root.left= deserializer(A);
        t++;
        root.right=deserializer(A);
        return root;
    }


      static Map<Integer,TreeMap<Integer,PriorityQueue<Integer>>> hm ;
      static int minX;
      static int maxX;

    public static ArrayList<Integer> topViewOfTree(TreeNode A) {
        hm=new HashMap<>();
        minX=0;
        maxX=0;
        helper(A,0,0);
        ArrayList<Integer> top = new ArrayList<>();
        for (int i = minX; i <= maxX; i++) {
            int key = hm.get(i).lastKey();
            top.add(hm.get(i).get(key).poll());
        }
        return top;
    }


    public static ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode A) {
        hm=new HashMap<>();
        minX=0;
        maxX=0;
        helper(A,0,0);
        ArrayList<ArrayList<Integer>> verticalTraversal = new ArrayList<>();
        for (int i = minX; i <=maxX ; i++) {
            ArrayList<Integer> level = new ArrayList<>();
            for (int key : hm.get(i).keySet()){
                while (!hm.get(i).get(key).isEmpty()){
                    level.add(hm.get(i).get(key).poll());
                }
            }
            verticalTraversal.add(level);
        }
        return verticalTraversal;
    }
    private static void helper(TreeNode root, int x, int y) {
        if (root==null) return;
        minX=Math.min(minX,x);
        maxX=Math.max(maxX,x);
        if (hm.get(x)==null){
            hm.put(x,new TreeMap<>());
        }
        if (hm.get(x).get(y)==null){
            hm.get(x).put(y,new PriorityQueue<>(((a,b)->-1*a.compareTo(b))));
        }
        hm.get(x).get(y).add(root.val);
        helper(root.left,x-1,y+1);
        helper(root.right,x+1,y+1);
    }


    static  int ans;
      public int diameterOfTree(TreeNode A){
          if (A==null) return 0;
          ans=Integer.MIN_VALUE;
          heightOfNode(A);
          return ans;
      }

    private int heightOfNode(TreeNode root) {
          if (root==null) return 0;
          int leftHeight = heightOfNode(root.left);
          int rightHeight = heightOfNode(root.right);
          ans=Math.max(ans,leftHeight+rightHeight);
          return Math.max(rightHeight,leftHeight)+1;
    }

    public ArrayList<Integer> rightViewOfTree(TreeNode A) {
        Queue<TreeNode> q = new LinkedList<>();
        ArrayList<Integer> rightView = new ArrayList<>();
        if (A==null) return rightView;
        q.add(A);
        while (!q.isEmpty()){
            int size= q.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp=q.poll();
                if (i==0) rightView.add(temp.val);
                if (temp.right!=null) q.add(temp.right);
                if (temp.left!=null) q.add(temp.left);
            }
        }
        return rightView;
    }


    public ArrayList<Integer> leftViewOfTree(TreeNode A) {
        Queue<TreeNode> q = new LinkedList<>();
        ArrayList<Integer> leftView = new ArrayList<>();
        if (A==null) return leftView;
        q.add(A);
        while (!q.isEmpty()){
            int size= q.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp=q.poll();
                if (i==0) leftView.add(temp.val);
                if (temp.left!=null) q.add(temp.left);
                if (temp.right!=null) q.add(temp.right);
            }
        }
        return leftView;
    }

    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode A) {
        Queue<TreeNode> q = new LinkedList<>();
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        if (A==null) return list;
        q.add(A);
        while (!q.isEmpty()){
            int size = q.size();
            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode t = q.poll();
                temp.add(t.val);
                if (t.left!=null) q.add(t.left);
                if (t.right!=null) q.add(t.right);
            }
            list.add(temp);
        }
        return list;
    }



    public ArrayList<Integer> SerializeBinaryTree(TreeNode A) {
        Queue<TreeNode> q = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        q.add(A);
        while (!q.isEmpty()){
            TreeNode temp = q.poll();
            list.add(temp.val);
            if (temp.val!=-1){
                if (temp.left != null) {
                    q.add(temp.left);
                } else {
                    q.add(new TreeNode(-1));
                }
                if (temp.right != null) {
                    q.add(temp.right);
                } else {
                    q.add(new TreeNode(-1));
                }
            }
        }
        return list;
    }



    int heightOfTree(TreeNode root){
        if (root==null) return 0;
        int left = heightOfTree(root.left);
        int right = heightOfTree(root.right);
        return Math.max(left,right)+1;
    }



    public int CheckIfGivenPreorderInorderAndPostorderTraversalsAreOfSameTree(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<Integer> C) {
        return checkTree(A,0,B,0,C,0,A.size()) ? 1 : 0;
    }
    private boolean checkTree(ArrayList<Integer> preOrder, int preStart, ArrayList<Integer> inOrder, int inStart, ArrayList<Integer> postOrder, int postStart, int size) {
        if (size==0)return true;
        if (size==1) return preOrder.get(preStart).equals(inOrder.get(inStart)) && inOrder.get(inStart).equals(postOrder.get(postStart));
        int index =inOrder.indexOf(preOrder.get(preStart));
        if (index==-1) return false;
        index=index-inStart;
        boolean r1 = checkTree(preOrder,preStart+1,inOrder,inStart, postOrder, postStart, index );
        boolean r2 = checkTree(preOrder, preStart+index+1, inOrder, inStart+index+1, postOrder, postStart+index,size-index-1);
        return r1 && r2;
    }


    public TreeNode buildTreePreIn(ArrayList<Integer> A, ArrayList<Integer> B) {
        return buildTreeInorderPreorder(A,B,0,0,B.size()-1);
    }
    private TreeNode buildTreeInorderPreorder(ArrayList<Integer> preOrder, ArrayList<Integer> inOrder, int preIndex, int inStart, int inEnd) {
        if (preIndex==preOrder.size()||inStart>inEnd) return null;
        TreeNode node = new TreeNode(preOrder.get(preIndex));
        int inIndex= inOrder.indexOf(node.val);
        node.left=buildTreeInorderPreorder(preOrder,inOrder,preIndex+1,inStart,inIndex-1);
        node.right=buildTreeInorderPreorder(preOrder,inOrder,preIndex+inIndex-inStart+1,inIndex+1,inEnd);
        return node;
    }


    public TreeNode buildTreeInPost(ArrayList<Integer> A, ArrayList<Integer> B) {
        if(A.size()==0||B.size()==0||A.size()!=B.size()) return null;
        return buildTreeFromInorderPostorder(A,B,0,A.size()-1,0,B.size()-1);
    }
    private TreeNode buildTreeFromInorderPostorder(ArrayList<Integer> inOrder, ArrayList<Integer> postOrder,int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart>inEnd){
            return null;
        }
        TreeNode node = new TreeNode(postOrder.get(postEnd));
        if (inStart==inEnd) return node;
        int rootIndex = inOrder.indexOf(node.val);
        node.left = buildTreeFromInorderPostorder(inOrder,postOrder,inStart,rootIndex-1,postStart,postStart-inStart+rootIndex-1);
        node.right = buildTreeFromInorderPostorder(inOrder,postOrder,rootIndex+1,inEnd,postEnd-inEnd+rootIndex-1,postEnd-1);
        return node;
    }




    public int isSameTree(TreeNode A, TreeNode B) {
        return isChildSame(A,B) ? 1 : 0;
    }
    boolean isChildSame(TreeNode left, TreeNode right){
        if (left==null&&right==null) return true;
        if (left==null||right==null) return false;
        if (left.val!=right.val) return false;
        return isChildSame(left.left,right.left) && isChildSame(left.right,right.right);
    }

    public ArrayList<Integer> inorderTraversal(TreeNode A) {
        ArrayList<Integer> tree = new ArrayList<>();
        if (A==null) return tree;
        Stack<TreeNode> stk = new Stack<>();
        TreeNode current = A;
        while (current!=null || !stk.isEmpty()){
            while (current!=null){
                stk.push(current);
                current=current.left;
            }
            current=stk.pop();
            tree.add(current.val);
            current=current.right;
        }
        return tree;
    }

    public ArrayList<Integer> postorderTraversal(TreeNode A) {
        ArrayList<Integer> tree = new ArrayList<>();
        if (A==null) return tree;
        Stack<TreeNode> stk = new Stack<>();
        stk.push(A);
        TreeNode prev=null;
        while (!stk.isEmpty()){
            TreeNode current = stk.peek();
            if (prev==null || prev.left== current || prev.right== current){
                if (current.left!=null){
                    stk.push(current.left);
                }
                else if (current.right!=null){
                    stk.push(current.right);
                }
                else {
                    stk.pop();
                    tree.add(current.val);
                }
            }
            else if (current.left==prev){
                if (current.right!=null){
                    stk.push(current.right);
                }
                else {
                    stk.pop();
                    tree.add(current.val);
                }
            }
            else if (current.right==prev){
                stk.pop();
                tree.add(current.val);
            }
            prev= current;
        }
        return tree;
    }

    public ArrayList<Integer> preorderTraversalIterative(TreeNode A) {
        ArrayList<Integer> tree = new ArrayList<>();
        if (A==null) return tree;
        Stack<TreeNode> stk = new Stack<>();
        stk.push(A);
        while (!stk.isEmpty()){
            TreeNode node = stk.pop();
            tree.add(node.val);
            if (node.right!=null){
                stk.push(node.right);
            }
            if (node.left!=null){
                stk.push(node.left);
            }
        }
        return tree;
    }

    static ArrayList<Integer> tree;
    public ArrayList<Integer> preorderTraversal(TreeNode A) {
        tree = new ArrayList<>();
        recursivePreorderTraversal(A);
        return tree;
    }
    private void recursivePreorderTraversal(TreeNode a) {
        if (a==null) return;
        tree.add(a.val);
        recursivePreorderTraversal(a.left);
        recursivePreorderTraversal(a.right);
    }


    public int isSymmetric(TreeNode A) {
        if (A==null) return 0;
        return isChildSymmetric(A.left,A.right) ? 1 : 0;
    }
    boolean isChildSymmetric (TreeNode left,TreeNode right){
        if (left==null&&right==null) return true;
        if (left==null||right==null) return false;
        if (left.val==right.val){
            return isChildSymmetric(left.left,right.right) && isChildSymmetric(left.right,right.left);
        }
        return false;
    }


}
