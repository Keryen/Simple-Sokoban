package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.model.auxiliar.Direction;
import es.upm.pproject.sokoban.model.auxiliar.DirectionsFactory;
import es.upm.pproject.sokoban.model.auxiliar.Pair;

public class TestAux {

	@Nested
	class ConstructorsTests {

		@Test
		@DisplayName("Direction")
		public void TestDirections() {
			Direction d = Direction.DOWN;
			assertNotNull(d);
		}

		@Test
		@DisplayName("DirectionsFactory")
		public void TestDirectionsFactory() {
			DirectionsFactory df = new DirectionsFactory();
			assertNotNull(df);
		}

		@Test
		@DisplayName("Pair")
		public void TestPair() {
			Pair<Object, Object> p = new Pair<Object, Object>(
					null, null);
			assertNotNull(p);
		}
	}

	@Nested
	class DirectionTests {

		@Test
		@DisplayName("Testing all directions")
		public void TestAll() {
			Direction up = Direction.UP;
			Direction down = Direction.DOWN;
			Direction left = Direction.LEFT;
			Direction right = Direction.RIGHT;
			assertNotNull(up);
			assertNotNull(down);
			assertNotNull(left);
			assertNotNull(right);
		}
	}

	@Nested
	class DirectionFactoryTests {

		DirectionsFactory dirf;

		@BeforeEach
		public void Construction() {
			dirf = new DirectionsFactory();
		}

		@Test
		@DisplayName("Test nextPosition")
		public void TestNextPositionRight() {
			Pair<Integer, Integer> p1 = new Pair<Integer, Integer>(
					0, 1);

			assertEquals(p1.getRight(), dirf.nextPosition(0, 0, Direction.RIGHT).getRight());
		}

		@Test
		@DisplayName("Test nextPosition")
		public void TestNextPositionLeft() {
			Pair<Integer, Integer> p3 = new Pair<Integer, Integer>(
					0, 0);

			assertEquals(p3.getLeft(), dirf.nextPosition(0, 1, Direction.LEFT).getLeft());
		}

		@Test
		@DisplayName("Test nextPosition")
		public void TestNextPositionUp() {
			Pair<Integer, Integer> p3 = new Pair<Integer, Integer>(
					0, 0);

			assertEquals(p3.getRight(), dirf.nextPosition(1, 0, Direction.UP).getRight());
		}

		@Test
		@DisplayName("Test nextPosition")
		public void TestNextPositionDown() {
			Pair<Integer, Integer> p2 = new Pair<Integer, Integer>(
					1, 0);

			assertEquals(p2.getLeft(), dirf.nextPosition(0, 0, Direction.DOWN).getLeft());
		}

	}

	@Nested
	class PairTests {

		Pair<Integer, Integer> pair;

		@BeforeEach
		public void Construction() {
			pair = new Pair<Integer, Integer>(11, 50);
		}

		@Nested
		class get {

			@Test
			@DisplayName("Testing getLeft")
			public void TestgetLeft() {
				Integer number = 11;
				assertEquals(number,pair.getLeft());
			}

			@Test
			@DisplayName("Testing getRigth")
			public void TestgetRight() {
				Integer number = 50;
				assertEquals(number,pair.getRight());
			}
		}

		@Nested
		class set {
			@Test
			@DisplayName("Testing setLeft")
			public void TestsetLeft() {
				Integer number = 50;
				pair.setLeft(number);
				assertEquals(number,pair.getLeft());
			}

			@Test
			@DisplayName("Testing setRigth")
			public void TestsetRight() {
				Integer number = 11;
				pair.setRight(11);
				assertEquals(number,pair.getLeft());
			}
		}
	}
}
