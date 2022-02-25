package algorithm.link;

/**
 *
 */
public class lc141_Linked_List_Cycle {
    static boolean hasCycle(ListNode head) {
        if (head==null || head.next==null){
            return false;
        }
        ListNode t1 = head;
        ListNode t2 = head.next;
        while (t1!=t2){
            t1 = t1.next;
            t2 = t2.next;
            if (t1==null|t2==null){
                return  false;
            }
        }
        return true;
    }
}
