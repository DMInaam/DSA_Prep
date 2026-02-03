package LinkedList;

public class LNode {
    int data;
    LNode next;
    LNode(int data){
        this.data = data;
        next = null;
    }
    static LNode insertNode(LNode head,int a){
        LNode newNode = new LNode(a);
        LNode temp = head;
        if(head == null) return newNode;
        while(temp.next != null) {temp = temp.next;}
        temp.next = newNode;
        return head;
    }
    static void display(LNode head){
        LNode temp = head;
        if(head == null) {
            System.out.println("No elements present");
        }
        while(temp.next != null){
            System.out.print(temp.data+"->");
            temp = temp.next;
        }
        System.out.print(temp.data);
    }
    
}
