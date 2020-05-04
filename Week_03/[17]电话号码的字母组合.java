//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 示例: 
//
// 输入："23"
//输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
// 
//
// 说明: 
//尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。 
// Related Topics 字符串 回溯算法


//leetcode submit region begin(Prohibit modification and deletion)
//迭代，嵌套层数取决于digit，有层序遍历的意思
//过程是这样的
//""->a,b,c->ad,ae,af,bd,be,bf,cd,ce,cf
//8，15.97；  39.9，5.17
// class Solution{
//     public List<String> letterCombinations(String digits){
//         String[] store={"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
//         List<String> res=new LinkedList<>();
//         if(digits==null || digits.length()==0){
//             return res;
//         }
//         res.add("");
//         //每次循环增加一个字符
//         for(int i=0;i<digits.length();i++){
//             int size=res.size();
//             for(int j=0;j<size;j++){
//                 String last=res.remove(0);
//                 for(char c:store[digits.charAt(i)-'0'].toCharArray()){
//                     res.add(last+c);
//                 }
//             }
//         }
//         return res;
//     }
// }


//9,10.71;  40.4,5.17
// class Solution{
//     public List<String> letterCombinations(String digits) {
// 		LinkedList<String> ans = new LinkedList<String>();
// 		if(digits.isEmpty()) return ans;
// 		String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
// 		ans.add("");
// 		for(int i =0; i<digits.length();i++){
// 			int x = Character.getNumericValue(digits.charAt(i));
// 			while(ans.peek().length()==i){
// 				String t = ans.remove();
// 				for(char s : mapping[x].toCharArray())
// 					ans.add(t+s);
// 			}
// 		}
// 		return ans;
// 	}
// }


//递归
//0,100;  38.8,5.17
class Solution{
    private String[] store={"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    private List<String> res=new ArrayList<>();
    public List<String> letterCombinations(String digits){
        if(digits==null || digits.length()==0){
            return res;
        }
        combination(digits,0,new StringBuilder());
        return res;
    }

    private void combination(String digits,int level,StringBuilder sb){
        if(level==digits.length()){
            res.add(sb.toString());
            return;
        }

        String thisstring=store[digits.charAt(level)-'0'];
        for(int i=0;i<thisstring.length();i++){
            sb.append(thisstring.charAt(i));
            combination(digits,level+1,sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }
// }


    //递归，非回溯
//6,39.14;  40.3,5.17
    class Solution{
        private String[] store={"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        private List<String> res=new ArrayList<>();
        public List<String> letterCombinations(String digits){
            if(digits==null || digits.length()==0){
                return res;
            }
            combination(digits,0,new String());
            return res;
        }

        private void combination(String digits,int level,String s){
            if(s.length()==digits.length()){
                res.add(s);
                return;
            }

            String thisstring=store[digits.charAt(level)-'0'];
            for(int i=0;i<thisstring.length();i++){
                combination(digits,level+1,s+thisstring.charAt(i));
            }
        }
    }

//leetcode submit region end(Prohibit modification and deletion)
