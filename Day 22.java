/*
Number of Matching Subsequences
Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
 

Example 1:

Input: s = "abcde", words = ["a","bb","acd","ace"]
Output: 3
Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".
Example 2:

Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
Output: 2
 

Constraints:

1 <= s.length <= 5 * 104
1 <= words.length <= 5000
1 <= words[i].length <= 50
s and words[i] consist of only lowercase English letters.
*/
class Solution {
   HashMap<Character,List<Integer>> map = new HashMap<>();

    boolean isSubSequence(String str){
        int req = -1;
        for(char x : str.toCharArray()){
            boolean should = false;
            if(!map.containsKey(x)){return false;}
            for(int i : map.get(x)){
                if(i > req){req = i;should = true;break;}
            }
            if(!should){return false;}
        }
        return true;
    }


    public int numMatchingSubseq(String s, String[] words) {
        
        for(int i = 0;i < s.length();i++){
            if(!map.containsKey(s.charAt(i))){
                map.put(s.charAt(i),new ArrayList<Integer>());
            }
            map.get(s.charAt(i)).add(i);
        }
        int res = 0;
        for(String str : words){
            if(isSubSequence(str)){res++;}
        }
     
        return res;
    }
}