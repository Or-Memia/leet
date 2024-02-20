import java.sql.Array;
import java.sql.SQLOutput;
import java.util.*;
import java.util.Stack;

public class Leet {
    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 2, 2, 2, 2, 4, 4, 4, 5, 8, 9};
        Solution sol = new Solution();
        sol.removeDuplicates(nums);
//        sol.isPowerOfThree(162);
//        sol.isPowerOfThreeRecursion(6561);
//        sol.countPrimes(10);
//        sol.fizzBuzz(16);
//        sol.isValid("(");
        //sol.twoSum(nums, target);
        //sol.intToRoman(5);
//        sol.romanToInt("MCMXCIV");
//        String[] s = ["aaab","aaac","aacc","aaan"];
        //sol.longestCommonPrefix(new String[]{"aaab", "aaaac", "aacc", "aaan"});
//        int[] nums = {-1, 0, 1, 2, -1, -4};

//        sol.threeSum(nums);
    }
}

class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i > 0 && map.get(s.charAt(i)) > map.get(s.charAt(i - 1))) {
                result += (map.get(s.charAt(i)) - 2 * map.get(s.charAt(i - 1)));
            } else {
                result += map.get(s.charAt(i));
            }
        }
        return result;
    }

    public String intToRoman(int num) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

//        String[] romanSymbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
//        int[] romanValues = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder sb = new StringBuilder();
        for (Integer key : map.keySet()) {
            while (num >= key) {
                sb.append(map.get(key));
                num -= key;
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }
        }
        return prefix;
    }


    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[left] + nums[right];
                    if (sum == 0) {
                        result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if (sum > 0) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
//        System.out.println(result.toString());
        return result;
    }

    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    for (int k = 0; k < result.length; k++) {
                        System.out.println(result[k]);
                    }
                    return result;
                }
            }
        }
        return result;
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] splitted = s.toCharArray();
        boolean flag = false;
        for (int i = 0; i < splitted.length; i++) {
            if (splitted[i] == '(' || splitted[i] == '[' || splitted[i] == '{') {
                stack.push(splitted[i]);
                if (splitted.length - i == 1 && !stack.isEmpty()) {
                    System.out.println(flag);
                    return flag;
                }
                continue;
            }
            if (stack.isEmpty()) {
                System.out.println(flag);
                return flag;
            }
            if (stack.peek() == '(' && splitted[i] == ')') {
                stack.pop();
                if (splitted.length - i == 1 && !stack.isEmpty()) {
                    System.out.println(flag);
                    return flag;
                }
                continue;
            } else if (stack.peek() == '[' && splitted[i] == ']') {
                stack.pop();
                if (splitted.length - i == 1 && !stack.isEmpty()) {
                    System.out.println(flag);
                    return flag;
                }
                continue;

            } else if (stack.peek() == '{' && splitted[i] == '}') {
                stack.pop();
                if (splitted.length - i == 1 && !stack.isEmpty()) {
                    System.out.println(flag);
                    return flag;
                }
                continue;
            } else if (!stack.empty()) {
                System.out.println(flag);
                return flag;
            }
        }
        flag = true;
        System.out.println(flag);
        return flag;

    }

    public List<String> fizzBuzz(int n) {
        List<String> str = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 5 == 0 && i % 3 == 0) {
                str.add(i - 1, "FizzBuzz");
            } else if (i % 3 == 0) {
                str.add(i - 1, "Fizz");
            } else if (i % 5 == 0) {
                str.add(i - 1, "Buzz");
            } else {
                str.add(i - 1, Integer.toString(i));
            }
        }
        System.out.println(str);
        return str;
    }

    public boolean isPowerOfThree(int n) {
        double len = (Math.log(n) / Math.log(3));
        for (int i = 0; i <= len; i++) {
            if (n < 1) {
                return false;
            }
            if (n == 1 || n == 3) {
                return true;
            } else if (n % 3 == 0) {
                n = n / 3;
            }
        }
        return false;
    }

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0; // Handle empty or null arrays
        }

        ArrayList<Integer> uniqueList = new ArrayList<>();
        uniqueList.add(nums[0]); // Add the first element to the unique list

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                uniqueList.add(nums[i]);
            }
        }

        // Optionally, if you want to replace the original array with the unique elements
        for (int i = 0; i < uniqueList.size(); i++) {
            nums[i] = uniqueList.get(i);
        }

        return uniqueList.size();
    }


//    static boolean temp = false;
//    public boolean isPowerOfThreeRecursion(int n) {
//        boolean flag;
//        if (n < 1){
//            flag = false;
//            System.out.println(flag);
//            return flag;
//        }
//        if(n == 1 || n == 3){
//            flag = true;
//            temp = true;
//            System.out.println(flag);
//            return flag;
//        }
//        else{
//            if(n % 3 == 0){
//                isPowerOfThreeRecursion(n/3);
//                if (temp) {
//                    flag = true;
//                    System.out.println(flag);
//                    return flag;
//                }
//            }
//            flag = false;
//            System.out.println(flag);
//            return flag;
//        }
//    }

//    public int countPrimes(int n) {
//        int[] numbers = new int[n];
//        boolean flag2 = false;
//        boolean flag2 = false;
//        boolean flag2 = false;
//        boolean flag2 = false;
//        boolean flag2 = false;
//        boolean flag2 = false;
//        boolean flag2 = false;
//        boolean flag2 = false;
//        for (int i = 1; i <= n; i++) {
//            numbers[i - 1] = i;
//        }
//        System.out.println(Arrays.toString(numbers));
//        for(int i = 1; i < n; i++){
//            if(i % 2 == 0){
//
//            }
//        }
//        return 0;
//    }
}

