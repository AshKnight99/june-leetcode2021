/*
Count of Smaller Numbers After Self
You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

 

Example 1:

Input: nums = [5,2,6,1]
Output: [2,1,1,0]
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Example 2:

Input: nums = [-1]
Output: [0]
Example 3:

Input: nums = [-1,-1]
Output: [0,0]
 

Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
*/
class Solution {
    public  void update(int ind,int val,int[] fen,int n) {		
		while(ind <= n) {
			fen[ind] += val;
			ind = ind + (ind &- ind);
		}
	}
	public  int sum(int ind,int[] fen) {
		int s = 0;
		while(ind > 0) {
			s += fen[ind];
			ind = ind - (ind &- ind);
		}
		return s;
	}
    public List<Integer> countSmaller(int[] nums) {
        int size = nums.length;
        int[] arr = nums.clone();
        int[] fenwick = new int[100005];
        int i = 1;
        Arrays.sort(arr);
        HashMap<Integer,Integer> hm = new HashMap<>();
        //value compression..
        for(int k = 0;k < size;k++) {
            if(!hm.containsKey(arr[k])) {
                hm.put(arr[k], i++);
            }
        }
        List<Integer> ls = new ArrayList<>();
        int freq[] = new int[hm.size()+1];
        for(int v = 0; v < nums.length; v++) {
            int x = hm.get(nums[v]);
            freq[x]++;
            update(x, 1, fenwick,nums.length);
        }
        for(int v = 0; v < nums.length; v++) {
            int x = hm.get(nums[v]);           
            freq[x]--;
            update(x, -1, fenwick, nums.length);            
            int l = sum(x - 1, fenwick);           
            ls.add(l);
        }
        return ls;
    }
}