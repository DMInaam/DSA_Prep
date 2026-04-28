package LinkedList;

public class LNode {
    public int data;
    public LNode next;
    public LNode(){
    }
    public LNode(int data){
        this.data = data;
        next = null;
    }
    public static LNode insertNode(LNode head,int a){
        LNode newNode = new LNode(a);
        LNode temp = head;
        if(head == null) return newNode;
        while(temp.next != null) {temp = temp.next;}
        temp.next = newNode;
        return head;
    }
    public static void display(LNode head){
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
