import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class problem {

    public static List<List<Integer>> findTwoSumCombinations(int[] nums, int target) {
        Map<Integer, Integer> complementMap = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (complementMap.containsKey(complement)) {
                List<Integer> pair1 = Arrays.asList(nums[i], complement);
                List<Integer> pair2 = Arrays.asList(nums[complementMap.get(complement)], nums[i]);
                result.add(pair1);
                result.add(pair2);
            }
            complementMap.put(nums[i], i);
        }

        return result;
    }

    public static int[] mergeAndSort(int[] nums) {
        Arrays.sort(nums);
        return nums;
    }

    public static List<List<Integer>> findCombinations(int[] nums, int target) {
        if (target == 0) {
            return new ArrayList<>(Arrays.asList(new ArrayList<>()));
        }

        List<List<Integer>> combinations = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > target) {
                break;
            }
            List<List<Integer>> subCombinations = findCombinations(Arrays.copyOfRange(nums, i + 1, nums.length), target - nums[i]);
            for (List<Integer> subCombination : subCombinations) {
                List<Integer> combination = new ArrayList<>();
                combination.add(nums[i]);
                combination.addAll(subCombination);
                combinations.add(combination);
            }
        }

        return combinations;
    }

    public static List<List<Integer>> findDoubleTargetCombinations(int[] nums, int target) {
        int[] mergedNums = mergeAndSort(nums);
        int doubleTarget = target * 2;
        List<List<Integer>> combinations = findCombinations(mergedNums, doubleTarget);
        return combinations;
    }
    
    public static int[] takeInput(){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int arr[] = new int[n];
        for(int i = 0;i < n;i++){
            arr[i] = s.nextInt();
        }
        return arr;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int[] nums = takeInput();
        int target = s.nextInt();

        List<List<Integer>> twoSumCombinations = findTwoSumCombinations(nums, target);
        System.out.println("First Combination For " + target + " : " + twoSumCombinations);

        int[] mergedArray = mergeAndSort(nums);
        System.out.println("Merge Into a single Array: " + Arrays.toString(mergedArray));

        int doubleTarget = target * 2;
        List<List<Integer>> doubleTargetCombinations = findDoubleTargetCombinations(mergedArray, doubleTarget);
        System.out.println("Second Combination For " + doubleTarget + " : " + doubleTargetCombinations);
    }
}
