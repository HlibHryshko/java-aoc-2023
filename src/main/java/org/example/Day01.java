package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Day01 {
    private static int solution1(List<String> inputData) {
        return inputData.stream().map(
                line -> {
                    int[] numbers = line.chars()
                            .filter(value -> ((value - 48) > 0 && (value - 48) < 10))
                            .map(value -> value - 48)
                            .toArray();
                    int num1 = numbers[0];
                    int num2 = numbers[numbers.length - 1];
                    return num1 * 10 + num2;
                }
        ).mapToInt(Integer::intValue).sum();
    }

    private static int solution2(List<String> inputData) {
        HashMap<Integer, List<String>> map = new HashMap<>();
        map.put(1, List.of("1", "one"));
        map.put(2, List.of("2", "two"));
        map.put(3, List.of("3", "three"));
        map.put(4, List.of("4", "four"));
        map.put(5, List.of("5", "five"));
        map.put(6, List.of("6", "six"));
        map.put(7, List.of("7", "seven"));
        map.put(8, List.of("8", "eight"));
        map.put(9, List.of("9", "nine"));

        return inputData.stream().map(

                line -> {

                    int num1 = -1;
                    int num2 = -1;
                    int index1 = Integer.MAX_VALUE;
                    int index2 = Integer.MIN_VALUE;

                    for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {

                        Integer key = entry.getKey();
                        List<String> value = entry.getValue();

                        int firstEntryIndex = value.stream().map(line::indexOf).filter(index -> index > -1).min(Integer::compareTo).orElse(Integer.MAX_VALUE);
                        if (firstEntryIndex < index1) {
                            num1 = key;
                            index1 = firstEntryIndex;
                        }

                        int lastEntryIndex = value.stream().map(line::lastIndexOf).filter(index -> index > -1).max(Integer::compareTo).orElse(Integer.MIN_VALUE);
                        if (lastEntryIndex > index2) {
                            num2 = key;
                            index2 = lastEntryIndex;
                        }

                    }

                    return num1 * 10 + num2;

                }

        ).mapToInt(Integer::intValue).sum();
    }

    public static void main(String[] args) {
        List<String> inputDataTest1 = Util.readFile("day01test1.txt");
        List<String> inputDataTask1 = Util.readFile("day01task1.txt");
        List<String> inputDataTest2 = Util.readFile("day01test2.txt");
        List<String> inputDataTask2 = Util.readFile("day01task2.txt");
        System.out.println(solution1(inputDataTest1));
        System.out.println(solution1(inputDataTask1));
        System.out.println(solution2(inputDataTest2));
        System.out.println(solution2(inputDataTask2));

    }
}
