//给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
// 
//
// 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回 0。 
// 所有单词具有相同的长度。 
// 所有单词只由小写字母组成。 
// 字典中不存在重复的单词。 
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。 
// 
//
// 示例 1: 
//
// 输入:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//输出: 5
//
//解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//     返回它的长度 5。
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: 0
//
//解释: endWord "cog" 不在字典中，所以无法进行转换。 
// Related Topics 广度优先搜索


//leetcode submit region begin(Prohibit modification and deletion)
// 单向bfs
// 57,76.67;  47.6,6.67
// class Solution {
//     public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//         //0.因为所有单词等长，start和end不为空，所以不需要边界处理
//         if(!wordList.contains(endWord)){
//             return 0;
//         }
//         //1.先对wordList做预处理，将所有编辑距离为1的分为一组
//         //2.使用队列，从start开始，遍历其所有的匹配模式，将所有编辑距离为1的加入队列；
//         //3.每次循环从队列中取出一个单词，先查看是否是end，否则继续执行添加编辑距离为1的单词，当然是没有被访问的；
//         //4.队列为空，也没有找到end，说明wordList中没有end或没有通向end的路径，所以没有将end添加到队列中，返回0；
//         //5.为了记录长度，使用string，Integer元组保存结果，使用了Pair；或者在合适的位置维护一个计数器，不过单独维护计数器很麻烦，取元组；

//         //1.处理wordList，使用map保存结果，string，List<String>
//         int length=beginWord.length();
//         HashMap<String,List<String>> proceBank=new HashMap<>();
//         //这里使用了容器的iterator的forEach和lambda表达式匿名类
//         //wordList中所有的单词肯定有匹配模式是重复的，但是只能全部遍历一次
//         wordList.forEach(
//             word->{
//                 for(int i=0;i<length;i++){
//                     //得到word的一个匹配模式
//                     String pattern=word.substring(0,i)+"*"+word.substring(i+1,length);
//                     //先取出proceBank中该pattern对应的链表
//                     List<String> palist=proceBank.getOrDefault(pattern,new LinkedList<String>());
//                     //将word添加到该list中，因为wordList中没有重复单词，所以不必担心list中有重复
//                     palist.add(word);
//                     //别忘了更新map
//                     proceBank.put(pattern,palist);
//                 }
//             }
//         );

//         //2.定义队列，将start加入队列，开始while
//         Queue<Pair<String,Integer>> queue=new LinkedList<>();
//         queue.offer(new Pair(beginWord,1));

//         //已访问容器
//         HashSet<String> visited=new HashSet<>();
//         visited.add(beginWord);

//         while(!queue.isEmpty()){
//             Pair<String,Integer> thispair=queue.remove();
//             //遍历这个pair中的string对应的所有模式，从proceBank将每个模式的list遍历，先判断是不是end，否则添加到已访问，并继续遍历
//             String curString=thispair.getKey();
//             int curlevel=thispair.getValue();
//             for(int i=0;i<length;i++){
//                 String thispattern=curString.substring(0,i)+"*"+curString.substring(i+1,length);
//                 List<String> curlist=proceBank.getOrDefault(thispattern,new LinkedList<String>());
//                 for(String thisString:curlist){
//                     if(!visited.contains(thisString)){
//                         if(thisString.equals(endWord)){
//                             return curlevel+1;
//                         }
//                         queue.add(new Pair(thisString,curlevel+1));
//                         visited.add(thisString);
//                     }
//                 }
//             }
//         }
//         return 0;
//     }
// }

//双向bfs
//基本原理与单向bfs相同，不过同时从start和end进行单向操作，一旦wordList中有一个word被访问了两次就返回该单词在两个方向上的level和。不过程序太麻烦了，就不写了
//32，86.06；  45，6.67
class Solution {

    private int L;
    private Map<String, List<String>> allComboDict;

    Solution() {
        this.L = 0;

        // Dictionary to hold combination of words that can be formed,
        // from any given word. By changing one letter at a time.
        this.allComboDict = new HashMap<>();
    }

    private int visitWordNode(
            Queue<Pair<String, Integer>> Q,
            Map<String, Integer> visited,
            Map<String, Integer> othersVisited) {

        Pair<String, Integer> node = Q.remove();
        String word = node.getKey();
        int level = node.getValue();

        for (int i = 0; i < this.L; i++) {

            // Intermediate words for current word
            String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

            // Next states are all the words which share the same intermediate state.
            for (String adjacentWord : this.allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                // If at any point if we find what we are looking for
                // i.e. the end word - we can return with the answer.
                if (othersVisited.containsKey(adjacentWord)) {
                    return level + othersVisited.get(adjacentWord);
                }

                if (!visited.containsKey(adjacentWord)) {

                    // Save the level as the value of the dictionary, to save number of hops.
                    visited.put(adjacentWord, level + 1);
                    Q.add(new Pair(adjacentWord, level + 1));
                }
            }
        }
        return -1;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if (!wordList.contains(endWord)) {
            return 0;
        }

        // Since all words are of same length.
        this.L = beginWord.length();

        wordList.forEach(
                word -> {
                    for (int i = 0; i < L; i++) {
                        // Key is the generic word
                        // Value is a list of words which have the same intermediate generic word.
                        String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                        List<String> transformations =
                                this.allComboDict.getOrDefault(newWord, new ArrayList<>());
                        transformations.add(word);
                        this.allComboDict.put(newWord, transformations);
                    }
                });

        // Queues for birdirectional BFS
        // BFS starting from beginWord
        Queue<Pair<String, Integer>> Q_begin = new LinkedList<>();
        // BFS starting from endWord
        Queue<Pair<String, Integer>> Q_end = new LinkedList<>();
        Q_begin.add(new Pair(beginWord, 1));
        Q_end.add(new Pair(endWord, 1));

        // Visited to make sure we don't repeat processing same word.
        Map<String, Integer> visitedBegin = new HashMap<>();
        Map<String, Integer> visitedEnd = new HashMap<>();
        visitedBegin.put(beginWord, 1);
        visitedEnd.put(endWord, 1);

        while (!Q_begin.isEmpty() && !Q_end.isEmpty()) {

            // One hop from begin word
            int ans = visitWordNode(Q_begin, visitedBegin, visitedEnd);
            if (ans > -1) {
                return ans;
            }

            // One hop from end word
            ans = visitWordNode(Q_end, visitedEnd, visitedBegin);
            if (ans > -1) {
                return ans;
            }
        }

        return 0;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
