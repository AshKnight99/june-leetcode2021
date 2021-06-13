/*
Palindrome Pairs
Given a list of unique words, return all the pairs of the distinct indices (i, j) in the given list, so that the concatenation of the two words words[i] + words[j] is a palindrome.

 

Example 1:

Input: words = ["abcd","dcba","lls","s","sssll"]
Output: [[0,1],[1,0],[3,2],[2,4]]
Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
Example 2:

Input: words = ["bat","tab","cat"]
Output: [[0,1],[1,0]]
Explanation: The palindromes are ["battab","tabbat"]
Example 3:

Input: words = ["a",""]
Output: [[0,1],[1,0]]
 

Constraints:

1 <= words.length <= 5000
0 <= words[i].length <= 300
words[i] consists of lower-case English letters.
*/
class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> wmap = new HashMap<>();
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < words.length; i++)
            wmap.put(words[i], i);
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("")) {
                for (int j = 0; j < words.length; j++) {
                    String w = words[j];
                    if (isPal(w, 0, w.length()-1) && j != i) {
                        ans.add(List.of(i, j));
                        ans.add(List.of(j, i));
                    }
                }
                continue;
            }
            StringBuilder sb = new StringBuilder(words[i]);
            sb.reverse();
            String bw = sb.toString();
            if (wmap.containsKey(bw)) {
                int res = wmap.get(bw);
                if (res != i) ans.add(List.of(i, res));
            }
            for (int j = 1; j < bw.length(); j++) {
                if (isPal(bw, 0, j-1)) {
                    String s = bw.substring(j);
                    if (wmap.containsKey(s))
                        ans.add(List.of(i, wmap.get(s)));
                }
                if (isPal(bw, j, bw.length()-1)) {
                    String s = bw.substring(0,j);
                    if (wmap.containsKey(s))
                        ans.add(List.of(wmap.get(s), i));
                }
            }
        }
        return ans;
    }
    
    private boolean isPal(String word, int i, int j) {
        while (i < j)
            if (word.charAt(i++) != word.charAt(j--)) return false;
        return true;
    }
}