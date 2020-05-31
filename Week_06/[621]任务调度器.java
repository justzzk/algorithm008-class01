//给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，并且每个任务
//都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。 
//
// 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。 
//
// 你需要计算完成所有任务所需要的最短时间。 
//
// 
//
// 示例 ： 
//
// 输入：tasks = ["A","A","A","B","B","B"], n = 2
//输出：8
//解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
//     在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。 
//
// 
//
// 提示： 
//
// 
// 任务的总个数为 [1, 10000]。 
// n 的取值范围为 [0, 100]。 
// 
// Related Topics 贪心算法 队列 数组


//leetcode submit region begin(Prohibit modification and deletion)
//因为无法想办法实现BAACBA到ABCABA的方法而搁置
// class Solution {
//     public int leastInterval(char[] tasks, int n) {
//         Map<Character,Integer> map=new HashMap<>();
//         for(char c:tasks){
//             if(map.containsKey(c)) map.put(c,map.get(c)+1);
//             else map.put(c,1);
//         }
//         int i=0;
//         while(!map.isEmpty()){
//             tasks[i]=
//         }
//     }
// }

//最简方法
// class Solution{
//     public int leastInterval(char[] tasks,int n){
//         int[] map=new int[26];
//         for(char c:tasks) map[c-'A']++;
//         Arrays.sort(map);
//         int level=map[25]-1;
//         int idleslot=level*n;
//         for(int i=24;i>=0 && map[i]>0;i--){
//             //之所以要取二者较小值，代表此种任务可以消耗的slot数量，
//             //通过图可以看出，如果数量大于level，也只去level，因为超出level的部分不占用slot
//             idleslot-=Math.min(map[i],level);
//         }
//         //idleslot的含义指的是要在指定允许间隔下，做完数量最多的那种任务需要的slot，
//         //得到的空闲slot可以填充其他任务，如果slot没有填充完，剩余的也要计算在内，
//         //如果填充完毕剩余0或者负表明tasks可以通过不空闲来全部完成，所需时长就是tasks长度，
//         //idleslot可能出现的值，正-有剩余；0-刚好分配完毕；负-分配的slot不足，
//         //所以当slot剩余为负的时候不应该计算在内
//         return idleslot<0?tasks.length:tasks.length+idleslot;
//     }
// }

//使用数组，每轮安排n+1个slot，直到task为空
public class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        Arrays.sort(map);
        int time = 0;
        while (map[25] > 0) {
            int i = 0;
            while (i <= n) {
                if (map[25] == 0)
                    break;
                if (i < 26 && map[25 - i] > 0)
                    map[25 - i]--;
                time++;
                i++;
            }
            Arrays.sort(map);
        }
        return time;
    }
}



//使用堆，代替数组
public class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c: tasks) map[c - 'A']++;
        //第2个参数是比较器，是将自然排序逆序
        PriorityQueue < Integer > queue = new PriorityQueue < > (26, Collections.reverseOrder());
        for (int f: map) {
            if (f > 0)
                queue.add(f);
        }
        int time = 0;
        while (!queue.isEmpty()) {
            int i = 0;
            List < Integer > temp = new ArrayList < > ();
            while (i <= n) {
                if (!queue.isEmpty()) {
                    if (queue.peek() > 1)
                        temp.add(queue.poll() - 1);
                    else
                        queue.poll();
                }
                time++;
                if (queue.isEmpty() && temp.size() == 0)
                    break;
                i++;
            }
            for (int l: temp)
                queue.add(l);
        }
        return time;
    }
}


class Solution{
    public int leastInterval(char[] tasks,int n){
        int[] map=new int[26];
        for(char c:tasks) map[c-'A']++;
        Arrays.sort(map);

        //统计出现次数最多的任务有几种
        int count=1;
        for(int i=24;i >= 0 ;i--) {
            if (map[i]==map[25]) count++;
            else break;
        }

        int slots=(n+1)*(map[25]-1)+count;
        return Math.max(slots,tasks.length);
    }
}



//耗时在sort和count，直接在统计的过程中完成即可
//??比sort和count还慢？
//应该是数组总共就26，sort和遍历并不消耗时间
// class Solution{
//     public int leastInterval(char[] tasks,int n){
//         int[] map = new int[26];
//         int count = 0;
//         int maxtimes = 0;
//         for(char c:tasks){
//             int tmp = ++map[c-'A'];
//             if( tmp > maxtimes){
//                 maxtimes = tmp;
//                 count = 1;
//             }else if(tmp == maxtimes){
//                 count ++ ;
//             }
//         }

//         int slots = (n+1)*(maxtimes-1)+count;
//         return slots < tasks.length ? tasks.length:slots;
//     }
// }

//leetcode submit region end(Prohibit modification and deletion)
