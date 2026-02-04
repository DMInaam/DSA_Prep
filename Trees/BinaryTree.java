package Trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BinaryTree {
    public static TreeNode createTree(String[] s){
        if(s.length == 0 || s[0].equals("null")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(s[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while(i<s.length && !q.isEmpty()){
            TreeNode cur = q.poll();
            if(i<s.length && !s[i].equals("null")){
                cur.left = new TreeNode(Integer.parseInt(s[i]));
                q.add(cur.left);
            }i++;
            if(i<s.length && !s[i].equals("null")){
                cur.right = new TreeNode(Integer.parseInt(s[i]));
                q.add(cur.right);
            }i++;
        }
        return root;
    }
    public static void display(TreeNode root){
        //pre order
        if(root == null) return;
        System.out.print(root.data+"   ");
        display(root.left);
        display(root.right);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] s = line.split(" ");
        TreeNode root = createTree(s);
        display(root);
        sc.close();
    }
}
