package model.helper;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PositionTest {
	@Test
	public void test1() {
		List<Position> positions = Arrays.asList(new Position(1, 1), new Position(1, 2), new Position(1, 3),
				new Position(2
						, 5), new Position(4, 2));
		assertEquals(false, new Position(1, 0).isInPosisiontList(positions));
	}
	@Test
	public void test2() {
		List<Position> positions = Arrays.asList(new Position(1, 1), new Position(1, 2), new Position(1, 3),
				new Position(2
						, 5), new Position(4, 2));
		assertEquals(true, new Position(1, 1).isInPosisiontList(positions));
	}
}