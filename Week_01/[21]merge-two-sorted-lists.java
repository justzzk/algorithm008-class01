//将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 示例： 
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4
// 
// Related Topics 链表


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
//递归
//执行用时 :0 ms, 在所有 Java 提交中击败了100.00%的用户
//内存消耗 :38.3 MB, 在所有 Java 提交中击败了59.17%的用户
// class Solution {
//     public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//         if(l1==null) return l2;
//         else if(l2==null) return l1;
//         else if(l1.val>l2.val){
//             l2.next=mergeTwoLists(l1,l2.next);
//             return l2;
//         }else{
//             l1.next=mergeTwoLists(l1.next,l2);
//             return l1;
//         }
//     }
// }

//执行用时 :1 ms, 在所有 Java 提交中击败了82.23%的用户
//内存消耗 :38.3 MB, 在所有 Java 提交中击败了59.77%的用户
class Solution {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //定义哨兵
        ListNode sen=new ListNode(-1);
        ListNode pre=sen;

        while(l1!=null && l2!=null){
            if(l1.val<=l2.val){
                pre.next=l1;
                l1=l1.next;
            }else{
                pre.next=l2;
                l2=l2.next;
            }
            pre=pre.next;
        }
        pre.next=(l1==null)?l2:l1;
        return sen.next;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
