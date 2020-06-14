//运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。 
//
// 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。 
//写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在
//写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。 
//
// 
//
// 进阶: 
//
// 你是否可以在 O(1) 时间复杂度内完成这两种操作？ 
//
// 
//
// 示例: 
//
// LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );
//
//cache.put(1, 1);
//cache.put(2, 2);
//cache.get(1);       // 返回  1
//cache.put(3, 3);    // 该操作会使得关键字 2 作废
//cache.get(2);       // 返回 -1 (未找到)
//cache.put(4, 4);    // 该操作会使得关键字 1 作废
//cache.get(1);       // 返回 -1 (未找到)
//cache.get(3);       // 返回  3
//cache.get(4);       // 返回  4
// 
// Related Topics 设计


//leetcode submit region begin(Prohibit modification and deletion)
class LRUCache {

    class Node{
        int key;
        int value;
        Node pre;
        Node next;

        Node(int key,int value,Node pre,Node next){
            this.key=key;
            this.value=value;
            this.pre=pre;
            this.next=next;
        }
    }

    private HashMap<Integer,Node> map;
    private int capacity;
    private Node dummy;
    private Node tail;

    public LRUCache(int capacity) {
        map=new HashMap<>();
        this.capacity=capacity;
        this.dummy=new Node(0,0,null,null);
        this.tail=new Node(0,0,dummy,null);
        dummy.next=tail;
    }

    public int get(int key) {
        if(map.containsKey(key)){
            //移到首位,在列表中的node一定是有pre和next的
            Node target=map.get(key);
            target.pre.next=target.next;
            target.next.pre=target.pre;
            toFirst(target);
            return target.value;
        }else return -1;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node target=map.get(key);
            target.value=value;
            //访问之后要移到首位
            target.pre.next=target.next;
            target.next.pre=target.pre;
            toFirst(target);
        }else{
            //新的key
            if(map.size()==capacity){
                //移除末尾
                Node target=tail.pre;
                tail.pre=target.pre;
                target.pre.next=tail;
                map.remove(target.key);
            }
            Node nNode=new Node(key,value,null,null);
            toFirst(nNode);
            map.put(key,nNode);
        }
    }

    private void toFirst(Node node){
        Node dummyNext=dummy.next;
        dummy.next=node;
        node.pre=dummy;
        node.next=dummyNext;
        dummyNext.pre=node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
