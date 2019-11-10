package test.baekjoon;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Problem2920 {

    /*
     * url: https://www.acmicpc.net/problem/2920
     * name: 음계
     * type: 배열
     * */

    private int[] asc = {1, 2, 3, 4, 5, 6, 7, 8};
    private int[] desc = {8, 7, 6, 5, 4, 3, 2, 1};

    @Test
    public void main() {
//        String inputString = "1 2 3 4 5 6 7 8";
//        String inputString = "8 7 6 5 4 3 2 1";
        String inputString = "1 2 3 5 4 6 7 8";
        int[] input = Arrays.stream(inputString.split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(this.solution(input));

    }

    public String solution(int[] input) {
        if(Arrays.equals(input, asc)) return "ascending";
        else if(Arrays.equals(input, desc)) return "descending";
        return "mixed";
    }

}
