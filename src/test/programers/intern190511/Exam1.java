package test.programers.intern190511;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Exam1 {

    // 자료구조 Array => Map, 조건에 따른 계산을 할 수 있는가?
    @Test
    public void main() {
//        int[] s = {1,2,4,3,3};
        int[] s = {2,3,4,4,2,1,3,1};
        Map<Integer, Long> m = Arrays.stream(s)
                .boxed()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        System.out.println(m);

        int taxiCount = 0;
        int group4Num = m.getOrDefault(4,0l).intValue();
        int group3Num = m.getOrDefault(3,0l).intValue();
        int group2Num = m.getOrDefault(2,0l).intValue();
        int group1Num = m.getOrDefault(1,0l).intValue();

        taxiCount += group4Num;

        int diffNumGroup1to3 = group1Num - group3Num;
        taxiCount += group3Num;

        int divide2Group2Num = group2Num / 2;
        taxiCount += divide2Group2Num;

        int remainGroup2Num = group2Num % 2;
        int remainGroup1Num = diffNumGroup1to3 - 2;
        if(remainGroup1Num >= 0 || remainGroup2Num > 0) {
            // group1Num이 더 클 경우
            taxiCount += 1;
        }

        if(remainGroup1Num > 0) {
            taxiCount += (remainGroup1Num / 4) + 1;
        }

        System.out.println(taxiCount);

    }


}
