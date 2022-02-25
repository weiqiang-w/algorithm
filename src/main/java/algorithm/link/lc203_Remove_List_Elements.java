package algorithm.link;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 *
 */
public class lc203_Remove_List_Elements {

    static ListNode removeElements0(ListNode head, int val) {
        if(head == null){
            return head;
        }
        ListNode r = new ListNode(-1);
        ListNode pre = r;
        while(head!=null){
            ListNode next = head.next;
            if(head.val!=val){
                head.next = null;
                pre.next = head;
                pre = pre.next;
            }
            head = next;
        }
        return r.next;
    }
    static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        ListNode result = new ListNode(-1);
        ListNode pre = result;
        while (head != null) {
            if (head.val != val) {
                pre.next = head;
                pre = pre.next;
            }
            head = head.next;
            if (head == null){
                pre.next = null;
            }
        }
        return result.next;
    }

    static ListNode removeElements2(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        ListNode result = new ListNode(-1);
        result.next = head;
        ListNode pre = result;
        while (pre.next != null) {
            if (pre.next.val == val) {
                pre.next = pre.next.next;
            } else {
                pre = pre.next;
            }
        }
        return result.next;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(6);
        ListNode listNode1 = new ListNode(5, listNode);
        ListNode listNode2 = new ListNode(4, listNode1);
        ListNode listNode3 = new ListNode(3, listNode2);
        ListNode listNode4 = new ListNode(6, listNode3);
        ListNode listNode5 = new ListNode(2, listNode4);
        ListNode listNode6 = new ListNode(1, listNode5);

        ListNode listNode7 = removeElements0(listNode6, 6);
        lc21_Merge_Two_Sorted_Lists.print(listNode7);
    }
}


