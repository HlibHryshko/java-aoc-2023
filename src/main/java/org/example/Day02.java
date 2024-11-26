package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day02 {
    static class GameData {
        public int gameId;

        public Map<String, List<Integer>> cubesCount;

        GameData(int gameId, Map<String, List<Integer>> cubesCount) {
            this.gameId = gameId;
            this.cubesCount = cubesCount;
        }
    }

    private static List<GameData> parseInputData(List<String> inputData) {
        return inputData.stream().map(
                line -> {
                    String[] split = line.split(":");
                    int gameId = Integer.parseInt(split[0].split(" ")[1]);
                    Map<String, List<Integer>> cubesCount = new HashMap<>();
                    String[] gamesInfo = split[1].split("; ");
                    for (String game : gamesInfo) {
                        String[] draws = game.split(", ");
                        for (String draw : draws) {
                            draw = draw.strip();

                            String count = draw.split(" ")[0];
                            String color = draw.split(" ")[1];

                            cubesCount.putIfAbsent(color, new ArrayList<>());
                            cubesCount.get(color).add(Integer.parseInt(count));
                        }
                    }
                    return new GameData(gameId, cubesCount);
                }
        ).toList();
    }

    private static int solution1(List<String> inputData) {
        List<GameData> data = parseInputData(inputData);

        Map<String, Integer> cubesCount = new HashMap<>();
        cubesCount.put("red", 12);
        cubesCount.put("green", 13);
        cubesCount.put("blue", 14);

        return data.stream().filter(
                gameData -> {
                    boolean isPossible = true;
                    for (String color : cubesCount.keySet()) {
                        if (gameData.cubesCount.getOrDefault(color, new ArrayList<>()).stream().max(Integer::compareTo).orElse(Integer.MIN_VALUE) > cubesCount.get(color)) {
                            isPossible = false;
                        }
                    }
                    return isPossible;
                }
        ).map(
                gameData -> gameData.gameId
        ).mapToInt(Integer::intValue).sum();
    }

    private static int solution2(List<String> inputData) {
        List<GameData> data = parseInputData(inputData);

        return data.stream().map(
                gameData ->
                        gameData.cubesCount.keySet().stream().map(
                                color -> gameData.cubesCount.get(color).stream().max(Integer::compareTo).get()
                        ).reduce((number1, number2) -> number1 * number2).get()
        ).mapToInt(Integer::intValue).sum();
    }

    public static void main(String[] args) {
        List<String> inputDataTest1 = Util.readFile("day02test1.txt");
        List<String> inputDataTest2 = Util.readFile("day02test2.txt");
        List<String> inputDataTask = Util.readFile("day02task.txt");
        System.out.println(solution1(inputDataTest1));
        System.out.println(solution1(inputDataTask));
        System.out.println(solution2(inputDataTest2));
        System.out.println(solution2(inputDataTask));
    }
}
