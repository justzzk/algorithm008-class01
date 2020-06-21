//给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。 
//
// 
// 如果剩余字符少于 k 个，则将剩余字符全部反转。 
// 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。 
// 
//
// 
//
// 示例: 
//
// 输入: s = "abcdefg", k = 2
//输出: "bacdfeg"
// 
//
// 
//
// 提示： 
//
// 
// 该字符串只包含小写英文字母。 
// 给定字符串的长度和 k 在 [1, 10000] 范围内。 
// 
// Related Topics 字符串


//leetcode submit region begin(Prohibit modification and deletion)
//直接思路:使用sb构造新的字符串
//4,17.22;  40.1,9.09
 class Solution {
     public String reverseStr(String s, int k) {
         if(s==null || s.length()==0) return null;
         char[] schar=s.toCharArray();
         int length=schar.length;
         StringBuilder sb=new StringBuilder();
         for(int i=0;i<length;i+=2*k){
             int rend=Math.min(i+k,length);
             for(int j=rend-1;j>=i;j--) sb.append(schar[j]);
             int nend=Math.min(i+2*k,length);
             for(int j=rend;j<nend;j++) sb.append(schar[j]);
         }
         return sb.toString();
     }
 }


//转为char后原位反转
//2,45.41;  39.9,9.09
//不调用swap，直接在while 中交换：1，99.93；  39.7，9.09
class Solution{
    public String reverseStr(String s,int k){
        if(s==null || s.length()==0) return null;
        char[] schar=s.toCharArray();
        int length=schar.length;
        for(int i=0;i<length;i+=2*k){
            int start=i;
            // int end=Math.min(i+k,length);
            //while中end和start是变化的，所以end-start也是变化的，可以在while之前计算长度代替end-start
            // while(start<i+(end-start)/2) swap(schar,start++,--end);
            int end=Math.min(i+k,length)-1;
            // while(start<end) swap(schar,start++,end--);
            while(start<end){
                char tmp=schar[start];
                schar[start++]=schar[end];
                schar[end--]=tmp;
            }
        }

        // return new String(schar);
        return String.valueOf(schar);
    }

    // private void swap(char[] schar,int i,int j){
    //     char tmp=schar[i];
    //     schar[i]=schar[j];
    //     schar[j]=tmp;
    // }
}


//leetcode submit region end(Prohibit modification and deletion)
