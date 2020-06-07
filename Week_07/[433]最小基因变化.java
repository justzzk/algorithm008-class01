//一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。 
//
// 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。 
//
// 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。 
//
// 与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。 
//
// 现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变
//化次数。如果无法实现目标变化，请返回 -1。 
//
// 注意: 
//
// 
// 起始基因序列默认是合法的，但是它并不一定会出现在基因库中。 
// 所有的目标基因序列必须是合法的。 
// 假定起始基因序列与目标基因序列是不一样的。 
// 
//
// 示例 1: 
//
// 
//start: "AACCGGTT"
//end:   "AACCGGTA"
//bank: ["AACCGGTA"]
//
//返回值: 1
// 
//
// 示例 2: 
//
// 
//start: "AACCGGTT"
//end:   "AAACGGTA"
//bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
//
//返回值: 2
// 
//
// 示例 3: 
//
// 
//start: "AAAAACCC"
//end:   "AACCCCCC"
//bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
//
//返回值: 3
// 
//


//leetcode submit region begin(Prohibit modification and deletion)
//dfs，回溯，遍历bank并取出与start只差一个的string作为下一个start，直到找到end
//0，37.9
// class Solution{
//     private int minstep=Integer.MAX_VALUE;
//     public int minMutation(String start, String end, String[] bank) {
//         if(start.length()!=end.length()) return -1;
//         HashSet<String> set=new HashSet<>();
//         mutation(start,end,bank,set,0);
//         return minstep==Integer.MAX_VALUE?-1:minstep;
//     }

//     private void mutation(String start,
//                           String end,
//                           String[] bank,
//                           HashSet<String> set,
//                           int steps){
//         if(start.equals(end)){
//             minstep=Math.min(minstep,steps);
//             return;
//         }
//         for(String s:bank){
//             int diff=0;
//             //只遍历没遍历过的
//             if(!set.contains(s)){
//                 for(int i=0;i<s.length();i++){
//                     if(s.charAt(i)!=start.charAt(i)){
//                         diff++;
//                     }
//                 }
//                 //如果不同之处超过1处，查看下一个s
//                 if(diff!=1) continue;
//                 //找到不同之处只有一处的s,将其作为下一个start
//                 set.add(s);
//                 mutation(s,end,bank,set,steps+1);
//                 set.remove(s);
//             }
//         }
//     }
// }


//bfs，对当前string，用4种char遍历替换所有位，并查看visited和bankset，
//1，71.80；37.7，20
// public class Solution {
//     public int minMutation(String start, String end, String[] bank) {
//         if(start.equals(end)) return 0;

//         Set<String> bankSet = new HashSet<>();
//         for(String b: bank) bankSet.add(b);

//         char[] charSet = new char[]{'A', 'C', 'G', 'T'};

//         int level = 0;
//         Set<String> visited = new HashSet<>();
//         Queue<String> queue = new LinkedList<>();
//         queue.offer(start);
//         visited.add(start);

//         while(!queue.isEmpty()) {
//             int size = queue.size();
//             while(size-- > 0) {
//                 String curr = queue.poll();
//                 if(curr.equals(end)) return level;

//                 char[] currArray = curr.toCharArray();
//                 for(int i = 0; i < currArray.length; i++) {
//                     char old = currArray[i];
//                     for(char c: charSet) {
//                         currArray[i] = c;
//                         String next = new String(currArray);
//                         if(!visited.contains(next) && bankSet.contains(next)) {
//                             visited.add(next);
//                             queue.offer(next);
//                         }
//                     }
//                     currArray[i] = old;
//                 }
//             }
//             level++;
//         }
//         return -1;
//     }
// }


//1，71.5；  37.2，20
//这个题用回溯还是可解的，单词接龙就不行了，可能是测试集太大
// class Solution {
//     int minStepCount = Integer.MAX_VALUE;
//     public int minMutation(String start, String end, String[] bank) {
//         dfs(new HashSet<String>(), 0, start, end, bank);
//         return (minStepCount == Integer.MAX_VALUE) ? -1 : minStepCount;
//     }
//     private void dfs (HashSet<String> step,
//                     int stepCount,
//                     String current,
//                     String end,
//                     String[] bank) {
//         if (current.equals(end))
//             minStepCount = Math.min(stepCount, minStepCount);
//         for (String str: bank) {
//             int diff = 0;
//             for (int i = 0; i < str.length(); i++)
//                 if (current.charAt(i) != str.charAt(i))
//                     if (++diff > 1) break;
//             if (diff == 1 && !step.contains(str)) {
//                 step.add(str);
//                 dfs(step, stepCount + 1, str, end, bank);
//                 step.remove(str);
//             }
//         }
//     }
// }

//双向BFS，set保存每层状态
//这里使用预处理bank的方法也不合适，因为字符串比较长和bank中的词汇量比较少
//1,70.85;  37.4,20
class Solution{
    public int minMutation(String start, String end, String[] bank){
        HashSet<String> bankSet=new HashSet<>();
        for(String str:bank) bankSet.add(str);
        if(!bankSet.contains(end)) return -1;
        if(start.equals(end)) return 0;

        char[] charSet = new char[]{'A', 'C', 'G', 'T'};

        HashSet<String> startSet=new HashSet<>();
        startSet.add(start);
        bankSet.remove(start);
        HashSet<String> endSet=new HashSet<>();
        endSet.add(end);
        bankSet.remove(end);

        int step=0;
        while(!startSet.isEmpty() && !endSet.isEmpty()){
            if(startSet.size() > endSet.size()){
                HashSet<String> tmpSet=startSet;
                startSet=endSet;
                endSet=tmpSet;
            }
            HashSet<String> tmp=new HashSet<>();
            for(String thisStr:startSet){
                char[] thisSchar=thisStr.toCharArray();
                for(int i=0;i<thisStr.length();i++){
                    for(int j=0;j<4;j++){
                        if(thisSchar[i]==charSet[j]) continue;
                        char oldChar=thisSchar[i];
                        thisSchar[i]=charSet[j];
                        String newStr=new String(thisSchar);
                        if(endSet.contains(newStr)) return step+1;
                        if(bankSet.contains(newStr)){
                            bankSet.remove(newStr);
                            tmp.add(newStr);
                        }
                        thisSchar[i]=oldChar;
                    }
                }
            }
            startSet=tmp;
            step++;
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
