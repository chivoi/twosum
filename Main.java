
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) {
        int[] inputArray = { 2, 1, 3, 7, 11, 15};
        int inputTarget = 9;
        System.out.println(Arrays.toString(twoSum(inputArray, inputTarget)));

    }

    public static int[] twoSum(int[] nums, int target) {
        // convert nums to List to be able to grab indexes later
        List<Integer> allNumbersList = Arrays.stream(nums).boxed().toList();
        ArrayList<Integer> result = new ArrayList<Integer>();

        // find valid numbers
        IntStream allNumStream = Arrays.stream(nums);
        List<Integer> validNumList = allNumStream.filter(num -> num <= target).boxed().toList();

        // main logic, building result
        if (validNumList.contains(target)) {
            result.add(allNumbersList.indexOf(target));
        } else if (validNumList.stream().reduce(0, Integer::sum) == target) {
            validNumList.forEach(num -> result.add(allNumbersList.indexOf(num)));
        } else {
            validNumList.forEach(num -> {
                if (validNumList.contains(target-num)){
                    result.add(allNumbersList.indexOf(num));
                    result.add(allNumbersList.indexOf(target-num));
                }
            });

            //@TODO make it work if there are more numbers than 2 that add up to the target
            //@TODO adds same indices to result twice: debug
        }

        // if there are 0s, add them in too
        if (validNumList.contains(0)) {
            result.add(allNumbersList.indexOf(0));
        }


        return result.stream().mapToInt(i->i).toArray();
    }
}