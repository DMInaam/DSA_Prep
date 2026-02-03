package LinkedList;

import java.util.Scanner;

public class loopDetection {
    public static LNode loopCreation(LNode head,int a){
        LNode aNode = null;
        LNode temp = head;
        while (temp.next != null) {
            if(temp.data == a) aNode = temp;
            temp = temp.next;
        }
        if(temp.data == a) aNode = temp;
        temp.next = aNode;
        return head;
    }
    static boolean added(LNode head, int val){
        LNode temp = head;
        if(head == null) return false;
        while (temp.next != null) {
            if(temp.data == val) return true;
            temp = temp.next;
        }
        if(temp.data == val) return true;
        return false;
    }
    static boolean loopExists(LNode head){
        //Loop is detected using two pointers method
        if(head == null) return false;
        LNode slow = head;
        LNode fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }
        return false;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        LNode head = null;
        int n = sc.nextInt();
        for(int i = 0;i<n;i++){
            int val = sc.nextInt();
            if(!added(head, val)){
                head = LNode.insertNode(head, val);
            }
            else loopCreation(head, val);
        }
        if(loopExists(head)) System.out.println("Loop exists");
        else {System.out.println("No loop detected in this");
        LNode.display(head);}
        sc.close();
    }
}
