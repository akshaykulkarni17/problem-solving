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


    public TreeNode invertTree(TreeNode A) {
        if (A==null) return A;
        TreeNode temp  = A.left;
        A.left=A.right;
        A.right=temp;
        invertTree(A.left);
        invertTree(A.right);
        return A;
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
