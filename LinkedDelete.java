import java.util.HashSet;
import java.util.Set;

public class LinkedDelete {
    
    public static ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> un = new HashSet<Integer>();
        for(int i: nums) un.add(i);
       ListNode demo = new ListNode(0);
       ListNode prev = demo;
       ListNode cur = head;
       while(cur!=null){
            if(!un.contains(cur.val)){
                prev.next = cur;
                prev = cur;
            }
            cur = cur.next;
       }
        prev.next = null;
        return demo.next;
    }
    public static void printList(ListNode head){
        ListNode current = head;
        while (current!=null) {
            System.out.print(current.val+" ");
            current = current.next;
        }
    }
    public static void main(String[] args) {
        int[] val = {5,4,9,4,2,3,6,5,8,1};
        int[] nums = {1,2,3,4};
        ListNode head = new ListNode(val[0]);
        ListNode current = head;
        for(int i =1;i<val.length;i++){
            ListNode temp = new ListNode(val[i]);
            current.next = temp;
            current = current.next;
        }
        printList(head);
        System.out.println("after");
        ListNode mod = modifiedList(nums, head);
        printList(mod);
        
    }
}

