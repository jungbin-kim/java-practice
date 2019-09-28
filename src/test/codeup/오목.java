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
        // null이면 아직 승부 안남.
    }

    private Result solution(int[][] input) {

        int maxRow = input.length;
        int maxCol = input[0].length;
        Map<Point, Boolean> checkedPointMapByRight = new HashMap<>();
        Map<Point, Boolean> checkedPointMapByDiagonalRightDown = new HashMap<>();
        Map<Point, Boolean> checkedPointMapByDiagonalLeftDown = new HashMap<>();
        Map<Point, Boolean> checkedPointMapByDown = new HashMap<>();

        for(int i=1; i <= maxRow; i++) {
            for(int j=1; j <= maxCol; j++) {
                Point startPoint = new Point(i, j);

                List<Point> tempRight = checkPoint(startPoint, checkedPointMapByRight, Direction.RIGHT, input);
                List<Point> tempDiagonalRightDown = checkPoint(startPoint, checkedPointMapByDiagonalRightDown, Direction.DIAGONAL_RIGHT_DOWN, input);
                List<Point> tempDiagonalLeftDown = checkPoint(startPoint, checkedPointMapByDiagonalLeftDown, Direction.DIAGONAL_LEFT_DOWN, input);
                List<Point> tempDown = checkPoint(startPoint, checkedPointMapByDown, Direction.DOWN, input);


                // 확인용 출력
                if(tempRight.size() > 1) {
                    System.out.println(Arrays.toString(tempRight.toArray()));
                }
                if(tempDiagonalRightDown.size() > 1) {
                    System.out.println(Arrays.toString(tempDiagonalRightDown.toArray()));
                }
                if(tempDiagonalLeftDown.size() > 1) {
                    System.out.println(Arrays.toString(tempDiagonalLeftDown.toArray()));
                }
                if(tempDown.size() > 1) {
                    System.out.println(Arrays.toString(tempDown.toArray()));
                }

                // 단, 검은색과 흰색이 동시에 이기거나 검은색 또는 흰색이 두 군데 이상에서 동시에 이기는 경우는 입력으로 들어오지 않는다.
                if(tempRight.size() == 5) {
                    Point p = tempRight.get(0);
                    return new Result(p.getValueFrom(input), p.row, p.column);
                }
                if(tempDiagonalRightDown.size() == 5) {
                    Point p = tempDiagonalRightDown.get(0);
                    return new Result(p.getValueFrom(input), p.row, p.column);
                }
                if(tempDiagonalLeftDown.size() == 5) {
                    // 가장 왼쪽이 배열의 가장 마지막 요소
                    Point p = tempDiagonalLeftDown.get(tempDiagonalLeftDown.size()-1);
                    return new Result(p.getValueFrom(input), p.row, p.column);
                }
                if(tempDown.size() == 5) {
                    Point p = tempDown.get(0);
                    return new Result(p.getValueFrom(input), p.row, p.column);
                }
            }
        }
        return null;
    }

    private List<Point> checkPoint(Point startPoint, Map<Point, Boolean> checkedPointMap,
                                   Direction direction, int[][] originInput) {
        if(checkedPointMap.getOrDefault(startPoint, false)) return Collections.emptyList();

        checkedPointMap.put(startPoint, true);
        List<Point> temp = new ArrayList<>(Arrays.asList(startPoint));
        loopCheckPoint(startPoint, direction, checkedPointMap, originInput, temp);
        return temp;
    }

    private void loopCheckPoint(Point startPoint,
                                Direction direction,
                                Map<Point, Boolean> checkedPointMap,
                                int[][] input,
                                List<Point> temp) {
        if (startPoint.getValueFrom(input) != 0 &&
            getConditionByDirection(direction, startPoint, input.length, input[0].length)
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

    private boolean getConditionByDirection(Direction direction, Point startPoint, int maxRow, int maxColumn) {
        if(Direction.RIGHT == direction) {
            return startPoint.column + 1 <= maxColumn;
        } else if(Direction.DIAGONAL_RIGHT_DOWN == direction) {
            return startPoint.column + 1 <= maxColumn &&
                    startPoint.row + 1 <= maxRow;
        } else if(Direction.DIAGONAL_LEFT_DOWN == direction) {
            return startPoint.column - 1 >= 1 &&
                    startPoint.row + 1 <= maxRow;
        }
        // if Direction.DOWN
        return startPoint.row + 1 <= maxRow;
    }

    enum Direction {
        RIGHT,
        DIAGONAL_RIGHT_DOWN,
        DIAGONAL_LEFT_DOWN,
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
                return new Point(this.row, this.column + 1);
            } else if(direction == Direction.DIAGONAL_RIGHT_DOWN) {
                return new Point(this.row + 1, this.column + 1);
            } else if(direction == Direction.DIAGONAL_LEFT_DOWN) {
                return new Point(this.row + 1, this.column - 1);
            } else if(direction == Direction.DOWN) {
                return new Point(this.row + 1, this.column);
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
