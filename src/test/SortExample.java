package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

class SortExample {
	/*
	 * Comparable과 Comparator 인터페이스의 차이는 무엇인가?
	 * - Comparable: 자연스러운 순서로 정렬할 때 사용
	 * - Comparator: 원하는대로 정렬순서를 지정하고 싶은 곳에 사용 
	 */
	
	// int 타입의 배열을 순서대로 정렬
	@Test
	public void sortInts() {
		final int[] numbers = { -3, -5, 1, 7, 4, -2 };
		final int[] expected = { -5, -3, -2, 1, 4, 7 };
		Arrays.sort(numbers);
		assertArrayEquals(expected, numbers);
	}
	
	// 객체를 순서대로 정렬. String class 는 Comparable 인터페이스 구현.
	@Test
	void sortObjects() {
		final String[] strings = {"z", "x", "y", "abc", "zzz", "zazzy"};
		final String[] expected = {"abc", "x", "y", "z", "zazzy", "zzz"};
		Arrays.sort(strings);
		assertArrayEquals(expected, strings);
	}
	
	@Test
	void sortCustomObjects() {
		final List<Human> dds = Arrays.asList(new Human(1, "1", 10), new Human(2, "2", 4),new Human(4, "4", 20), new Human(3, "3", 15));
		final List<Human> ddExpected = Arrays.asList(new Human(2, "2", 4), new Human(1, "1", 10), new Human(3, "3", 15), new Human(4, "4", 20));
		// 정렬 전
		dds.forEach(u -> System.out.println(u.toString()));
		// 정렬
		Collections.sort(dds, new SortByAgeDesc());
		// 정렬 결과 출력
		dds.forEach(u -> System.out.println(u.toString()));
		
		assertArrayEquals(ddExpected.toArray(), dds.toArray());
	}
	
	// 테스트 모델
	class Human {
		private int id;
		private String name;
		private int age;
		
		Human(int id, String name, int age) {
			this.id = id;
			this.name = name;
			this.age = age;
		}
		public String toString() {
			return "{ id: " + this.id + ", name: " + this.name + ", age: " + this.age + " }";
		}
	    public boolean equals(Object obj){
	        Human h = (Human) obj;
	        return this.name.equalsIgnoreCase(h.name) && this.id == h.id && this.age == h.age;
	    }
	    public int hashCode(){
	        return this.id;
	    }
	}
	
	class SortByAgeDesc implements Comparator<Human> {
		@Override
		public int compare(Human o1, Human o2) {
			return o1.age - o2.age;
		}
		
	}
	
}
