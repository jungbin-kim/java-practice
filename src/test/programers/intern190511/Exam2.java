package test.programers.intern190511;

import org.junit.jupiter.api.Test;

import java.util.*;

public class Exam2 {
    private String[] directions = {"LEFT", "RIGHT", "WRAP"};

    // 증가 규칙성 파악. 중복성 제거. Sort
    @Test
    public void main() {
        int N = 3;
        List arr = new ArrayList<>(loop(N));
        Collections.sort(arr);
        System.out.println(arr);
    }

    public Set<String> loop(int n) {
        Set<String> set = new HashSet<>();
        if(n == 1) {
            set.add("()");
            return set;
        }
        for(String direction: directions) {
            for (String src : loop(n - 1)) {
                set.add(next(direction, src));
            }
        }
        return set;
    }

    public String next(String direction, String source) {
        if(direction.equals("LEFT")) {
            return "()" + source;
        } else if(direction.equals("RIGHT")) {
            return source + "()";
        }
        // direction.equals("WRAP"))
        return "(" + source + ")";
    }

}
