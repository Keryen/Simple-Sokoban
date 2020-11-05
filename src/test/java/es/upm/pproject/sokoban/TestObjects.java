package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.model.objects.*;

public class TestObjects {

	@Nested
	class ConstructorsTests {

		@Test
		@DisplayName("Testing Box Object")
		public void TestBox() throws IOException {
			Box box = new Box();
			assertNotNull(box);
		}

		@Test
		@DisplayName("Testing EmptySpace Object")
		public void TestEmptySpace() throws IOException {
			EmptySpace emptySpace = new EmptySpace();
			assertNotNull(emptySpace);
		}

		@Test
		@DisplayName("Testing Floor Object")
		public void TestFloor() throws IOException {
			Floor floor = new Floor();
			assertNotNull(floor);
		}

		@Test
		@DisplayName("Testing GoalPosition Object")
		public void TestGoalPosition() throws IOException {
			GoalPosition goalPosition = new GoalPosition();
			assertNotNull(goalPosition);
		}

		@Test
		@DisplayName("Testing Player Object")
		public void TestPlayer() throws IOException {
			Player player = new Player("Player");
			assertNotNull(player);
		}

		@Test
		@DisplayName("Testing Wall Object")
		public void TestWall() throws IOException {
			Wall wall = new Wall();
			assertNotNull(wall);
		}
	}

	@Nested
	class GameObjectTests {

		es.upm.pproject.sokoban.model.objects.GameObject obj;
		BufferedImage image;

		@BeforeEach
		public void Init() throws IOException {
			image = ImageIO.read(new File("resources/images/GoalPosition.png"));
			obj = new es.upm.pproject.sokoban.model.objects.GameObject(image);
		}

		@Test
		@DisplayName("Constructor of gameObject")
		public void TestConstructor() {
			assertNotNull(obj);
		}

		@Test
		@DisplayName("Constructor of gameObject")
		public void TestGetImage() {
			assertEquals(image, obj.getImage());
		}

		@Test
		@DisplayName("Constructor of gameObject")
		public void TestSetImage() throws IOException {
			image = ImageIO.read(new File("resources/images/Wall.png"));
			obj.setImage(image);
			assertEquals(image, obj.getImage());
		}
	}

	@Nested
	class PlayerTests {
		Player player;

		@BeforeEach
		public void Construction() throws IOException {
			player = new Player("Player");
		}

		@Test
		@DisplayName("Testing Method isOnGoalPosition")
		public void TestIsOnGoalPosition() throws IOException {
			assertFalse(player.isOnGoalPosition());
		}

		@Test
		@DisplayName("Testing Method setOnGoalPosition")
		public void TestSetOnGoalPosition() throws IOException {
			player.setOnGoalPosition(true);
			assertTrue(player.isOnGoalPosition());
		}

		@Test
		@DisplayName("Testing Setters and Getters")
		public void TestSettersAndGetters() throws IOException {
			int i = 1;
			int j = 1;
			player.setI(i);
			player.setJ(j);
			assertEquals(player.getI(), i);
			assertEquals(player.getJ(), j);
		}
	}

	@Nested
	class BoxTests {
		Box box;

		@BeforeEach
		public void Construction() throws IOException {
			box = new Box();
		}

		@Test
		@DisplayName("Testing Method isOnGoalPosition")
		public void TestIsOnGoalPosition() throws IOException {
			assertFalse(box.isOnGoalPosition());
		}

		@Test
		@DisplayName("Testing Method setOnGoalPosition")
		public void TestSetOnGoalPosition() throws IOException {
			box.setOnGoalPosition(true);
			assertTrue(box.isOnGoalPosition());
			box.setOnGoalPosition(false);
			assertFalse(box.isOnGoalPosition());
		}
	}

}
