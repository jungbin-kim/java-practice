package test.codeup;

import org.junit.jupiter.api.Test;

import java.util.*;

public class 오목 {
    /*
    * url: https://codeup.kr/problem.php?id=4023
    * type: DFS
    * */

    @Test
    public void main() {
        int [][] input = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 2, 0, 0, 2, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 2, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        Result r = solution(input);
        System.out.println(r);
    }

    private Result solution(int[][] input) {


        int maxRow = input.length;
        int maxCol = input[0].length;
        Map<Point, Boolean> checkedPointMap = new HashMap<>();

        for(int i=1; i <= maxRow; i++) {
            for(int j=1; j <= maxCol; j++) {
                Point startPoint = new Point(i, j);
                if(checkedPointMap.getOrDefault(startPoint, false) == false) {
                    checkedPointMap.put(startPoint, true);

                    List<Point> tempRight = new ArrayList<>(Arrays.asList(startPoint));
                    List<Point> tempDiagonal = new ArrayList<>(Arrays.asList(startPoint));
                    List<Point> tempDown = new ArrayList<>(Arrays.asList(startPoint));

                    loopCheckPoint(startPoint, Direction.RIGHT, checkedPointMap, input, tempRight);
                    loopCheckPoint(startPoint, Direction.DIAGONAL, checkedPointMap, input, tempDiagonal);
                    loopCheckPoint(startPoint, Direction.DOWN, checkedPointMap, input, tempDown);
                    // 확인용 출력
                    if(tempRight.size() > 1) {
                        System.out.println(Arrays.toString(tempRight.toArray()));
                    }
                    if(tempDiagonal.size() > 1) {
                        System.out.println(Arrays.toString(tempDiagonal.toArray()));
                    }
                    if(tempDown.size() > 1) {
                        System.out.println(Arrays.toString(tempDown.toArray()));
                    }

                    // 단, 검은색과 흰색이 동시에 이기거나 검은색 또는 흰색이 두 군데 이상에서 동시에 이기는 경우는 입력으로 들어오지 않는다.
                    if(tempRight.size() == 5) {
                        Point p = tempRight.get(0);
                        return new Result(p.getValueFrom(input), p.row, p.column);
                    }
                    if(tempDiagonal.size() == 5) {
                        Point p = tempDiagonal.get(0);
                        return new Result(p.getValueFrom(input), p.row, p.column);
                    }
                    if(tempDown.size() == 5) {
                        Point p = tempDown.get(0);
                        return new Result(p.getValueFrom(input), p.row, p.column);
                    }
                }
            }
        }
        return null;
    }

    private void loopCheckPoint(Point startPoint,
                                Direction direction,
                                Map<Point, Boolean> checkedPointMap,
                                int[][] input,
                                List<Point> temp) {
        if (startPoint.getValueFrom(input) != 0 &&
            startPoint.column + 1 <= input[0].length &&
            startPoint.row + 1 <= input.length
        ) {
            Point next = startPoint.getNext(direction);
            if(startPoint.getValueFrom(input) == next.getValueFrom(input)) {
                checkedPointMap.put(next, true);
                temp.add(next);
                loopCheckPoint(next, direction, checkedPointMap, input, temp);
            }
        }
        return;
    }

    enum Direction {
        RIGHT,
        DIAGONAL,
        DOWN
    }
    class Point {
        int row;
        int column;

        Point(int row, int column) {
            this.row = row;
            this.column = column;
        }
        @Override
        public boolean equals(Object o) {
            if(o == this) return true;
            if(o instanceof Point) {
                Point that = (Point) o;
                return row == that.row && column == that.column;
            }
            return false;
        }
        @Override
        public String toString() {
            return "[" + this.row + ", " + this.column + "]";
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column); // import java.util.Objects; 가 필요함
        }

        public Point getNext(Direction direction) {
            if(direction == Direction.RIGHT) {
                return new Point(this.row, this.column+1);
            }else if(direction == Direction.DIAGONAL) {
                return new Point(this.row+1, this.column+1);
            } else if(direction == Direction.DOWN) {
                return new Point(this.row+1, this.column);
            }
            return null;
        }
        public int getValueFrom(int[][] input) {
            return input[this.row-1][this.column-1];
        }

    }

    class Result {
        int winner;
        int row;
        int col;
        Result(int winner, int row, int col) {
            this.winner = winner;
            this.row = row;
            this.col = col;
        }
        @Override
        public String toString() {
            return "winner: " + this.winner + ", row: " + this.row + ", col: " + this.col;
        }
    }
}
