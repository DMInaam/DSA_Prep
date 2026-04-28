package Trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import LinkedList.LNode;
public class sortedBST {
    
    public static TreeNode sortedListToBST(LNode head) {
        if(head == null) return null;
        if(head.next == null) return new TreeNode(head.data);
        LNode last = null;
        LNode slow = head;
        LNode fast = head;
        while(fast!=null&& fast.next!=null){
            last = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        last.next = null;
        TreeNode root = new TreeNode(slow.data);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slow.next);
        return root;
    }
    public static void displayLR(TreeNode root){
        if(root == null) {System.out.print("null ");return;}
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i=1;
        while(!q.isEmpty()){
            TreeNode cur = q.poll();
            System.out.print(cur.data+" ");
            if(cur.left != null) {q.add(cur.left);}
            if(cur.right!=null){q.add(cur.right);}
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] s = line.split(" ");
        LNode head = new LNode();
        for(String part:s){
            if(s.equals("null")) continue;
            head = LNode.insertNode(head, Integer.parseInt(part));
        }
        TreeNode root = sortedListToBST(head);
        displayLR(root);
        sc.close();
    }
}
