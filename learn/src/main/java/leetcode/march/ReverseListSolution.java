package leetcode.march;

/**
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 */
public class ReverseListSolution {
    public static ListNode reverseList(ListNode head) {
        return doReverseList(head, head);
    }

    private static ListNode doReverseList(ListNode head, ListNode currNode) {
        if (currNode == null || currNode.next == null) {
            return head;
        }
        ListNode headNext = currNode.next;
        ListNode temp = currNode.next.next;
        currNode.next.next = head;
        currNode.next = temp;
        return doReverseList(headNext, currNode);
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        printList(reverseList(head));
    }

    public static void printList(ListNode head) {
        if (head != null) {
            System.out.println(head.val);
            printList(head.next);
        }
    }

}

