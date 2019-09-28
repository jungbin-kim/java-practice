package test.codeup;

import org.junit.jupiter.api.Test;

import java.util.*;

public class CandyPang {
    /*
        url: https://codeup.kr/problem.php?id=2605
        type: DFS
     */
    @Test
    public void main() {
        int[][] input = {
                {2,1,5,1,1,3,4},
                {2,1,5,1,3,5,3},
                {2,3,4,5,2,2,4},
                {4,4,3,2,3,1,3},
                {4,3,5,3,1,4,3},
                {5,4,4,3,3,5,5},
                {2,1,3,5,1,1,2}
            };

        int r = solution(input);
        System.out.println(r);
    }

    int solution(int[][] input) {
        int numMoreThan3 = 0;
        Map<Cord, Boolean> checkedCordMap = new HashMap<>();
        for(int i=0; i < input.length; i++) {
            for(int j=0; j < input.length; j++) {
                Cord cord = new Cord(i, j, input[i][j]);

                if(checkedCordMap.getOrDefault(cord, false) == false) {
                    checkedCordMap.put(cord, true);
                    List<Cord> result = new ArrayList<>();
                    result.add(cord);
                    loop(cord, input, checkedCordMap, result);
                    System.out.println(Arrays.toString(result.toArray()));
                    if(result.size() >= 3) numMoreThan3++;
                }
            }
        }

        return numMoreThan3;
    }

    void loop(Cord cord, int[][] input, Map<Cord, Boolean> checkedCordMap, List<Cord> result) {
        if(cord.column + 1 < input.length) {
            Cord nextCol = new Cord(cord.row, cord.column + 1, input[cord.row][cord.column + 1]);
            // checkedCordMap 에 true로 되어있는지 검사 필요. 한번 검사된 것을 다시 수행하지 않게 하는 조건 추가
            if(cord.color == nextCol.color && !checkedCordMap.getOrDefault(nextCol, false)) {
                checkedCordMap.putIfAbsent(nextCol, true);
                result.add(nextCol);
                loop(nextCol, input, checkedCordMap, result);
            }
        }
        if(cord.row + 1 < input.length) {
            Cord nextRow = new Cord(cord.row + 1, cord.column, input[cord.row+1][cord.column]);
            if(cord.color == nextRow.color && !checkedCordMap.getOrDefault(nextRow, false)) {
                checkedCordMap.putIfAbsent(nextRow, true);
                result.add(nextRow);
                loop(nextRow, input, checkedCordMap, result);
            }
        }
        return;
    }

    class Cord {
        int row;
        int column;
        int color;

        public Cord(int row, int column, int color) {
            this.row = row;
            this.column = column;
            this.color = color;
        }

        @Override
        public boolean equals(Object o) {
            if(o == this) return true;
            if(o instanceof Cord) {
                Cord that = (Cord) o;
                return row == that.row && column == that.column;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }

        @Override
        public String toString() {
            return "[" + this.row + ", " + this.column + "]";
        }
    }
}
