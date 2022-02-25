package algorithm.link;

/**
 *
 */

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}


public class lc21_Merge_Two_Sorted_Lists {
    static void print(ListNode l1) {
        StringBuilder stringBuilder = new StringBuilder();
        while (l1 != null) {
            stringBuilder.append(l1.val).append(",");
            l1 = l1.next;
        }
        System.out.println(stringBuilder.toString());
    }

    static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            print(prehead);
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        prev.next = l1 == null ? l2 : l1;

        return prev.next;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(4);
        ListNode listNode1 = new ListNode(2, listNode);
        ListNode listNode2 = new ListNode(1, listNode1);

        ListNode listNode3 = new ListNode(4);
        ListNode listNode4 = new ListNode(3, listNode3);
        ListNode listNode5 = new ListNode(1, listNode4);

        ListNode listNode6 = mergeTwoLists(listNode2, listNode5);
        print(listNode6);
    }


}