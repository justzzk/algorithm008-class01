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
//         // 1.先对wordList做预处理，将所有编辑距离为1的分为一组
//         // 2.使用队列，从start开始，遍历其所有的匹配模式，将所有编辑距离为1的加入队列；
//         // 3.每次循环从队列中取出一个单词，先查看是否是end，否则继续执行添加编辑距离为1的单词，当然是没有被访问的；
//         // 4.队列为空，也没有找到end，说明wordList中没有end或没有通向end的路径，所以没有将end添加到队列中，返回0；
//         // 5.为了记录长度，使用string，Integer元组保存结果，使用了Pair；或者在合适的位置维护一个计数器，不过单独维护计数器很麻烦，取元组；

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
//                     //别忘了更新map,这一步说明，虽然get返回的是list的引用，但是如果palist更新了，不会将更新写会map，需要手动更新；不过为什么LinkedList get后修改，不需要更新呢？这里因为返回的是new，所以还没有添加到map中，所以下面需要put
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


//单向
//这种方法是BFS，保证路径最短的原因是每一层都遍历所有编辑距离为1的可能性，一旦发现end立即返回
//54，80.7；  48.3，6.67
// class Solution{
//     public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//         //0. wordList中必须有end
//         if(!wordList.contains(endWord)) return 0;
//         //1. 预处理wordList
//         int length=beginWord.length();
//         HashMap<String,List<String>> map=new HashMap<>();
//         wordList.forEach(
//             word->{
//                 for(int i=0;i<length;i++){
//                     String pattern=word.substring(0,i)+"*"+word.substring(i+1,length);
//                     if(map.get(pattern)==null){
//                         map.put(pattern,new ArrayList<String>());
//                     }
//                     List<String> pattlist=map.get(pattern);
//                     pattlist.add(word);
//                 }
//             }
//         );

//         //2. 开始bfs，beginword，同时要记录已访问列表
//         HashSet<String> visited=new HashSet<>();
//         Queue<String> queue=new ArrayDeque<>();
//         queue.add(beginWord);
//         int level=1;
//         while(!queue.isEmpty()){
//             //这里每次遍历一层，而不是用Pair对每个String标记层数
//             int size=queue.size();
//             for(int j=0;j<size;j++){
//                 String curstr=queue.poll();
//                 for(int i=0;i<length;i++){
//                     String curpat=curstr.substring(0,i)+"*"+curstr.substring(i+1,length);
//                     List<String> curlist=map.get(curpat);
//                     if(curlist!=null){
//                         for(String str:curlist){
//                             if(!visited.contains(str)){
//                                 if(str.equals(endWord)){
//                                     //在第一次接触到一个String 的时候就判断，而不是从队列取出后判断
//                                     return level+1;
//                                 }else{
//                                     queue.add(str);
//                                     //已经添加到队列中的直接添加到visited，可以提高效率
//                                     visited.add(str);
//                                 }
//                             }
//                         }
//                     }
//                 }
//             }
//             level++;
//         }
//         return 0;
//     }
// }



//试试基因那个的从bank中取元素，回溯
//这个先试试找到第一个就返回，能不能得到最短路径，私以为，这是DFS，不能直接找到最短路径，但直接backtrace从1开始是直接遍历所有情况的，直接Math.min就可以，基因那个题也是这样，基因那个题也可以试试BFS，这个BFS是根据之差一个字母的模式记录，那个BFS是直接改变每一位，查看是否在bank中
//回溯好像会超时，可能是数据集太大的原因
// class Solution{

//     private int minStep=Integer.MAX_VALUE;
//     public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//         if(!wordList.contains(endWord)){
//             return 0;
//         }
//         //不然每次都从list中删掉被遍历的单词，回溯时再加上
//         HashSet<String> set=new HashSet<>();
//         backtrace(beginWord,endWord,1,set,wordList);
//         return minStep==Integer.MAX_VALUE?0:minStep;
//     }

//     private void backtrace(String beginWord,String endWord,int level,HashSet<String> set,List<String> wordList){
//         if(beginWord.equals(endWord)){
//             minStep=Math.min(minStep,level);
//             return;
//         }

//         //每次都遍历wordList，找出与beginWord相差1的单词
//         //因为遍历过的会直接从wordList中删除，所以不需要set
//         //直接删除不行，比如[a,b,c],i=1,删除后[a,c]，退出后[a,c,b]会直接跳过c
//         //不对，应该在add时add到i位置，一部分测试用例超时，换成set试试，也超时，用之前跑通基因的回溯也是相同的结果
//         for(int i=0;i<wordList.size();i++){
//             String curstr=wordList.get(i);
//             if(!set.contains(curstr) && diffForOne(curstr,beginWord)){
//                 // wordList.remove(curstr);
//                 set.add(curstr);
//                 backtrace(curstr,endWord,level+1,set, wordList);
//                 // wordList.add(i,curstr);
//                 set.remove(curstr);
//             }
//         }
//     }

//     private boolean diffForOne(String s1,String s2){
//         int diff=0;
//         for(int i=0;i<s1.length();i++){
//             if(s1.charAt(i)!=s2.charAt(i)){
//                 diff++;
//                 if(diff>1) return false;
//             }
//         }
//         return diff==1;
//     }
// }


//基本的双向DFS
//每轮whileBFS都用可选startset和endset中单词数少的进行
//28,88.20;  40.8;33.33
//比较快的原因是？每次都查找比较小的集合吗？
//其实不使用visited，在访问了之后直接从wordSet中删除就可以
//18,94,93;  40.8,33.33
// class Solution{
//     public int ladderLength(String beginWord, String endWord, List<String> wordList){
//         //初始化一个set保存所有可选的单词
//         HashSet<String> wordSet=new HashSet<>(wordList);
//         if(!wordSet.contains(endWord)) return 0;

//         //初始化下一轮start和end两个方向的可选单词set
//         HashSet<String> startSet=new HashSet<>();
//         HashSet<String> endSet=new HashSet<>();
//         //将初始的start和end添加到相应的set
//         startSet.add(beginWord);
//         endSet.add(endWord);
//         wordSet.remove(endWord);
//         //初始化已访问set
//         // HashSet<String> visited=new HashSet<>();
//         //下面这一句还是应该加上
//         // visited.add(endWord);
//         //初始化其他变量
//         // int strlen=beginWord.length();
//         //初始步长就是1
//         int len=1;

//         //开始迭代直到遇到或退出while
//         while(!startSet.isEmpty() && !endSet.isEmpty()){
//             //每轮里选择容量小的进行迭代
//             if(startSet.size() > endSet.size()){
//                 HashSet<String> tmp=startSet;
//                 startSet=endSet;
//                 endSet=tmp;
//             }

//             //这里虽然也是BFS，但是不是使用queue，而是每轮更新startSet，
//             //可能因为hash查找比queue更快
//             HashSet<String> tmp=new HashSet<>();
//             for(String startstr : startSet){
//                 //这里的BFS不像上面的，根据对wordlist的预处理，、
//                 //可以将其中编辑距离为1的直接获取，需要每个单词的每个位置替换为26个其中之一，
//                 //查看是否没有访问并且在wordlist中
//                 char[] sstrchar=startstr.toCharArray();
//                 for(int i=0;i<sstrchar.length;i++){
//                     //每个位置都替换为其他字母尝试
//                     for(char c='a';c<='z';c++){
//                         //有必要保存原来的字符吗？是的！
//                         //因为在替换完i位置的26个字母后，还要替换其他位置的，这时需要保证其他位置的不变
//                         //如果不保存就改变了原位置的字符
//                         char old=sstrchar[i];
//                         sstrchar[i]=c;
//                         String targetstr=String.valueOf(sstrchar);

//                         //先判断endset中是否一包含该单词
//                         if(endSet.contains(targetstr)) return len+1;

//                         //判断是否已访问或在wordlist中
//                         // if(wordSet.contains(targetstr) && !visited.contains(targetstr)){
//                         //     tmp.add(targetstr);
//                         //     visited.add(targetstr);
//                         // }
//                         if(wordSet.contains(targetstr)){
//                             tmp.add(targetstr);
//                             wordSet.remove(targetstr);
//                         }
//                         sstrchar[i]=old;
//                     }
//                 }
//             }
//             startSet=tmp;
//             len++;
//         }
//         return 0;
//     }
// }

//试试替换26个字母的单向BFS
//86，63.47；  41.7，20
//的确会快很多
//甚至比预处理wordList那种单向都要慢
// class Solution{
//     public int ladderLength(String beginWord, String endWord, List<String> wordList){
//         HashSet<String> wordSet=new HashSet<>(wordList);
//         if(!wordSet.contains(endWord)) return 0;

//         HashSet<String> startSet=new HashSet<>();
//         startSet.add(beginWord);
//         wordSet.remove(endWord);

//         int len=1;

//         while(!startSet.isEmpty()){

//             HashSet<String> tmp=new HashSet<>();
//             for(String startstr : startSet){
//                 char[] sstrchar=startstr.toCharArray();
//                 for(int i=0;i<sstrchar.length;i++){
//                     for(char c='a';c<='z';c++){
//                         char old=sstrchar[i];
//                         sstrchar[i]=c;
//                         String targetstr=String.valueOf(sstrchar);

//                         if(endWord.equals(targetstr)) return len+1;

//                         if(wordSet.contains(targetstr)){
//                             tmp.add(targetstr);
//                             wordSet.remove(targetstr);
//                         }
//                         sstrchar[i]=old;
//                     }
//                 }
//             }
//             startSet=tmp;
//             len++;
//         }
//         return 0;
//     }
// }



//官方的双向BFS，预处理wordList
//30ms,87.81;  46.2,6.67
class Solution{
    private int strlen=0;
    private HashMap<String,List<String>> allCombDict=new HashMap<>();

    public int ladderLength(String beginWord, String endWord, List<String> wordList){
        if(!wordList.contains(endWord)) return 0;
        this.strlen=beginWord.length();

        //预处理wordList
        wordList.forEach(
                word->{
                    for(int i=0;i<strlen;i++){
                        String newWord=word.substring(0,i)+"*"+word.substring(i+1,strlen);
                        if(allCombDict.get(newWord)==null) allCombDict.put(newWord,new ArrayList<String>());
                        allCombDict.get(newWord).add(word);
                    }
                });

        Queue<Pair<String,Integer>> Qbegin=new LinkedList<>();
        Queue<Pair<String,Integer>> Qend=new LinkedList<>();

        Qbegin.add(new Pair(beginWord,1));
        Qend.add(new Pair(endWord,1));

        //begin和end的已访问map
        Map<String,Integer> visitedBegin=new HashMap<>();
        Map<String,Integer> visitedEnd=new HashMap<>();

        visitedBegin.put(beginWord,1);
        visitedEnd.put(endWord,1);

        while(!Qbegin.isEmpty() && !Qend.isEmpty()){
            int ans=visitWordNode(Qbegin,visitedBegin,visitedEnd);
            if(ans != -1) return ans;

            ans=visitWordNode(Qend,visitedEnd,visitedBegin);
            if(ans != -1) return ans;
        }

        return 0;
    }

    private int visitWordNode(Queue<Pair<String,Integer>> queue,
                              Map<String,Integer> visited,
                              Map<String,Integer> otherVisited){
        Pair<String,Integer> node=queue.remove();
        String word=node.getKey();
        int level=node.getValue();

        for(int i=0;i<strlen;i++){
            String pattern=word.substring(0,i)+"*"+word.substring(i+1,strlen);
            for(String string:allCombDict.getOrDefault(pattern,new ArrayList<String>())){
                if(otherVisited.containsKey(string)){
                    return level+otherVisited.get(string);
                }

                if(!visited.containsKey(string)){
                    visited.put(string,level+1);
                    queue.add(new Pair<String,Integer>(string,level+1));
                }
            }
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
