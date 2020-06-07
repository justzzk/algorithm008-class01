//给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换
//需遵循如下规则： 
//
// 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回一个空列表。 
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
//输出:
//[
//  ["hit","hot","dot","dog","cog"],
//  ["hit","hot","lot","log","cog"]
//]
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: []
//
//解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。 
// Related Topics 广度优先搜索 数组 字符串 回溯算法


//leetcode submit region begin(Prohibit modification and deletion)
//双向BFS找邻节点列表+回溯
//21,91.41;   41.9,90
public class Solution {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // 先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
        List<List<String>> res = new ArrayList<>();
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return res;
        }
        // 第 1 步：使用双向广度优先遍历得到后继结点列表 successors
        // key：字符串，value：广度优先遍历过程中 key 的后继结点列表
        Map<String, Set<String>> successors = new HashMap<>();
        boolean found = bidirectionalBfs(beginWord, endWord, wordSet, successors);
        if (!found) {
            return res;
        }
        // 第 2 步：基于后继结点列表 successors ，使用回溯算法得到所有最短路径列表
        Deque<String> path = new ArrayDeque<>();
        path.addLast(beginWord);
        dfs(beginWord, endWord, successors, path, res);
        return res;
    }

    private boolean bidirectionalBfs(String beginWord,
                                     String endWord,
                                     Set<String> wordSet,
                                     Map<String, Set<String>> successors) {
        // 记录访问过的单词
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        visited.add(endWord);

        Set<String> beginVisited = new HashSet<>();
        beginVisited.add(beginWord);
        Set<String> endVisited = new HashSet<>();
        endVisited.add(endWord);

        int wordLen = beginWord.length();
        boolean forward = true;
        boolean found = false;
        // 在保证了 beginVisited 总是较小（可以等于）大小的集合前提下，&& !endVisited.isEmpty() 可以省略
        while (!beginVisited.isEmpty() && !endVisited.isEmpty()) {
            // 一直保证 beginVisited 是相对较小的集合，方便后续编码
            if (beginVisited.size() > endVisited.size()) {
                Set<String> temp = beginVisited;
                beginVisited = endVisited;
                endVisited = temp;

                // 只要交换，就更改方向，以便维护 successors 的定义
                forward = !forward;
            }
            Set<String> nextLevelVisited = new HashSet<>();
            // 默认 beginVisited 是小集合，因此从 beginVisited 出发
            for (String currentWord : beginVisited) {
                char[] charArray = currentWord.toCharArray();
                for (int i = 0; i < wordLen; i++) {
                    char originChar = charArray[i];
                    for (char j = 'a'; j <= 'z'; j++) {
                        if (charArray[i] == j) {
                            continue;
                        }
                        charArray[i] = j;
                        String nextWord = new String(charArray);
                        if (wordSet.contains(nextWord)) {
                            if (endVisited.contains(nextWord)) {
                                found = true;
                                // 在另一侧找到单词以后，还需把这一层关系添加到「后继结点列表」
                                addToSuccessors(successors, forward, currentWord, nextWord);
                            }

                            if (!visited.contains(nextWord)) {
                                nextLevelVisited.add(nextWord);
                                addToSuccessors(successors, forward, currentWord, nextWord);
                            }
                        }
                    }
                    charArray[i] = originChar;
                }
            }
            beginVisited = nextLevelVisited;
            visited.addAll(nextLevelVisited);
            if (found) {
                break;
            }
        }
        return found;
    }

    private void dfs(String beginWord,
                     String endWord,
                     Map<String, Set<String>> successors,
                     Deque<String> path,
                     List<List<String>> res) {

        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(path));
            return;
        }

        if (!successors.containsKey(beginWord)) {
            return;
        }

        Set<String> successorWords = successors.get(beginWord);
        for (String successor : successorWords) {
            path.addLast(successor);
            dfs(successor, endWord, successors, path, res);
            path.removeLast();
        }
    }

    private void addToSuccessors(Map<String, Set<String>> successors, boolean forward,
                                 String currentWord, String nextWord) {
        if (!forward) {
            String temp = currentWord;
            currentWord = nextWord;
            nextWord = temp;
        }

        // Java 1.8 以后支持
        // successors.computeIfAbsent(currentWord, a -> new HashSet<>());
        if(successors.get(currentWord)==null) successors.put(currentWord,new HashSet<String>());
        successors.get(currentWord).add(nextWord);
    }
}



//练习双向BFS+回溯
//要保存每条路径，还是得使用dfs
//因为BFS会同时获得多个方向的路径，目前还没想到比较好的方法在遇到多条路径后分别保存
class Solution{
    public List<List<String>> findLadders(String beginWord,String endWord,List<String> wordList){
        HashSet<String> wordSet=new HashSet<>(wordList);
        List<List<String>> res=new ArrayList<>();
        if(!wordSet.contains(endWord)) return res;

        //双向BFS获取邻节点列表
        HashMap<String,HashSet<String>> successors=new HashMap<>();
        //在BFS时检查是否找到了endword，如果没有，直接返回即可；
        boolean found=bidirectionalBFS(beginWord,endWord,wordSet,successors);
        if(!found) return res;

        //基于邻节点列表的回溯
        Deque<String> path=new ArrayDeque<>();
        path.addLast(beginWord);
        //dfs是单向回溯
        dfs(beginWord,endWord,successors,path,res);
        return res;
    }

    private boolean bidirectionalBFS(String beginWord,
                                     String endWord,
                                     HashSet<String> wordSet,
                                     HashMap<String,HashSet<String>> successors){

        HashSet<String> beginSet=new HashSet<>();
        beginSet.add(beginWord);
        wordSet.remove(beginWord);

        HashSet<String> endSet=new HashSet<>();
        endSet.add(endWord);
        wordSet.remove(endWord);


        int wordLen=beginWord.length();
        boolean forward=true;
        boolean found=false;
        while(!beginSet.isEmpty() && !endSet.isEmpty()){
            if(beginSet.size() > endSet.size()){
                //第一个出错的地方
                // HashSet<String> tmpSet=new HashSet<>();
                HashSet<String> tmpSet=beginSet;
                beginSet=endSet;
                endSet=tmpSet;
                forward=!forward;
            }

            HashSet<String> tmpBeginSet=new HashSet<>();
            for(String curWord:beginSet){
                char[] curWordChar=curWord.toCharArray();
                for(int i=0;i<wordLen;i++){
                    char oldChar=curWordChar[i];
                    //第2个出错的地方
                    // for(char j='a';j<'z';j++){
                    for(char j='a';j<='z';j++){
                        if(j==oldChar) continue;
                        curWordChar[i]=j;
                        String newWord=new String(curWordChar);
                        if(endSet.contains(newWord)) {
                            //标记为true后，结束本层while将退出
                            //这一层添加完后，其中只有几条路径是连通的，其他的是不连通的，
                            //但是不能根据根据found是否是true决定是否addToSuccessors，
                            //从而将本层不成路径的不添加到successors，因为前几层found一直是false
                            found=true;
                            addToSuccessors(successors,curWord,newWord,forward);
                        }
                        if(wordSet.contains(newWord)){
                            tmpBeginSet.add(newWord);
                            addToSuccessors(successors,curWord,newWord,forward);
                        }
                    }
                    curWordChar[i]=oldChar;
                }
            }
            wordSet.removeAll(tmpBeginSet);
            beginSet=tmpBeginSet;
            if(found) break;
        }
        return found;
    }



    private void addToSuccessors(HashMap<String,HashSet<String>> successors,
                                 String curWord,
                                 String newWord,
                                 boolean forward){
        if(!forward){
            String tmpStr=curWord;
            curWord=newWord;
            newWord=tmpStr;
        }
        if(successors.get(curWord)==null) successors.put(curWord,new HashSet<String>());
        successors.get(curWord).add(newWord);
    }

    private void dfs(String beginWord,
                     String endWord,
                     HashMap<String,HashSet<String>> successors,
                     Deque<String> path,
                     List<List<String>> res){
        if(beginWord.equals(endWord)){
            res.add(new ArrayList<>(path));
            return ;
        }
        //因为是双向扩展的，大部分的successor是没有在map中记录自己的successors的，直接跳过即可
        if(!successors.containsKey(beginWord)) return;
        HashSet<String> successorWords=successors.get(beginWord);
        for(String successor:successorWords){
            path.addLast(successor);
            dfs(successor,endWord,successors,path,res);
            path.removeLast();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
