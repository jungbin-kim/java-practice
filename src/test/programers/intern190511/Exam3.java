package test.programers.intern190511;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Exam3 {
    @Test
    public void crossPointTest() {
        Point p1 = new Point(2,3);
        Point p2 = new Point(2,2);
        Point p3 = new Point(2,2);
        Point p4 = new Point(2,1);

        System.out.println(crossPoint(p1, p2, p3, p4));
    }

    // 수학적 사고 능력. 직선의 조건. 두 직선간 교차 조건 구현 능력.
    @Test
    public void main() {
        int[] pattern = {1,2,5,8,9};
//        int[] pattern = {1,6,8,3,4};
//        int[] pattern = {2,5,1,3};
//        int[] pattern = {6,5,7,3,9};
//        int[] pattern = {5,3};

        Map<Integer, Point> m = init();
        // pattern -> 좌표로 변경
        List<Point> transformed = Arrays.stream(pattern).boxed().map(i -> m.get(i)).collect(Collectors.toList());

        // 좌표를 라인 객체로 변경
        ArrayList<Line> lines = new ArrayList<>();
        for(int i = 0; i < transformed.size() - 1; i++) {
            lines.add(new Line(transformed.get(i), transformed.get(i+1)));
        }

        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < lines.size(); i++) {
            Line targetLine = lines.get(i);
            int count = 0;
            for(int j=0; j < lines.size(); j++) {
                if(i != j) {
                    Line compareLine = lines.get(j);
                    if(crossPoint(targetLine.p1, targetLine.p2, compareLine.p1, compareLine.p2)) {
                        count += 1;
                    }
                }
            }
            result.add(count);
        }
        System.out.println(result);
    }

    public Map<Integer, Point> init() {
        Map<Integer, Point> map = new HashMap<>();
        for(Integer i = 0; i < 9; i++ ) {
            int x = i % 3;
            int y = 3 - (i / 3);
            map.put(i+1, new Point(x+1,y));
        }
        return map;
    }

    class Line {
        Point p1, p2;
        public Line(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        @Override
        public String toString() {
            return "Line{" +
                    "p1=" + p1 +
                    ", p2=" + p2 +
                    '}';
        }
    }

    class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public boolean crossPoint(Point p1Line1, Point p2Line1, Point p3Line2, Point p4Line2) {
        if(p1Line1.equals(p3Line2) || p1Line1.equals(p4Line2) || p2Line1.equals(p3Line2) || p2Line1.equals(p4Line2)) {
            return true;
        }
        // 1 공식 1: 점의 좌표 4개 => https://zetawiki.com/wiki/%EB%91%90_%EC%A7%81%EC%84%A0%EC%9D%98_%EA%B5%90%EC%B0%A8%EC%A0%90
        int xUp = (p1Line1.x * p2Line1.y - p1Line1.y * p2Line1.x) * (p3Line2.x - p4Line2.x) - (p1Line1.x - p2Line1.x) * (p3Line2.x * p4Line2.y - p3Line2.y * p4Line2.x);
        int yUp = (p1Line1.x * p2Line1.y - p1Line1.y * p2Line1.x)*(p3Line2.y - p4Line2.y) - (p1Line1.y - p2Line1.y)*(p3Line2.x * p4Line2.y - p3Line2.y * p4Line2.x);
        int down = (p1Line1.x - p2Line1.x) * (p3Line2.y - p4Line2.y) - (p1Line1.y - p2Line1.y) * (p3Line2.x - p4Line2.x);

        double crossX = xUp / (double) down;
        double crossY = yUp / (double) down;

        boolean compareXLine1 = p1Line1.x < p2Line1.x ? p1Line1.x <= crossX && crossX <= p2Line1.x : p2Line1.x <= crossX && crossX <= p1Line1.x;
        boolean compareYLine1 = p1Line1.y < p2Line1.y ? p1Line1.y <= crossY && crossY <= p2Line1.y : p2Line1.y <= crossY && crossY <= p1Line1.y;
        boolean compareXLine2 = p3Line2.x < p4Line2.x ? p3Line2.x <= crossX && crossX <= p4Line2.x : p4Line2.x <= crossX && crossX <= p3Line2.x;
        boolean compareYLine2 = p3Line2.y < p4Line2.y ? p3Line2.y <= crossY && crossY <= p4Line2.y : p4Line2.y <= crossY && crossY <= p3Line2.y;

        return compareXLine1 && compareYLine1 && compareXLine2 && compareYLine2;
    }
}
