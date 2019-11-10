package test.baekjoon;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Problem2798 {
    /*
     * url: https://www.acmicpc.net/problem/2798
     * name: 블랙잭
     * type: 배열, 완전 탐색(모든 경우의 수를 구해야함), combination(수학)
     * */

    @Test
    public void main() {
        String inputFirstLine = "5 21";
        String inputSecondLine = "5 6 7 8 9";
        int[] inputFirstToInt = Arrays.stream(inputFirstLine.split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = inputFirstToInt[0];
        int M = inputFirstToInt[1];
        int[] inputSecondToIntArray = Arrays.stream(inputSecondLine.split(" ")).mapToInt(Integer::parseInt).filter(n -> n < M - 1).toArray();
        Set<Integer> resultSet = new HashSet<>();
        // Make combination C(카드 숫자 리스트,3) and sum of three element
        // Set<Integer> 을 반환하는 함수로 만들어도 좋음.
        for(int i = 0; i < inputSecondToIntArray.length - 2; i++) {
            int firstNum = inputSecondToIntArray[i];
            for(int j = i + 1; j < inputSecondToIntArray.length -1; j++) {
                int secondNum = inputSecondToIntArray[j];
                for(int k = j + 1; k < inputSecondToIntArray.length; k++) {
                    int thirdNum = inputSecondToIntArray[k];
                    resultSet.add(firstNum+secondNum+thirdNum);
                }
            }
        }

        if(resultSet.contains(M)) System.out.println(M);
        else {
            int r = Collections.max(resultSet.stream().filter(n -> n < M).collect(Collectors.toList()));
            System.out.println(r);
        }
    }

}
