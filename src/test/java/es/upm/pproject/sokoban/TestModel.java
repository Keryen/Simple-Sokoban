package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.model.ActionManager;
import es.upm.pproject.sokoban.model.GameLevel;
import es.upm.pproject.sokoban.model.InputManager;
import es.upm.pproject.sokoban.model.Map;
import es.upm.pproject.sokoban.model.OutputManager;
import es.upm.pproject.sokoban.model.SokobanImpl;
import es.upm.pproject.sokoban.model.auxiliar.Direction;
import es.upm.pproject.sokoban.model.exceptions.IllegalGameObjectException;
import es.upm.pproject.sokoban.model.exceptions.InvalidLevelException;
import es.upm.pproject.sokoban.model.objects.Box;
import es.upm.pproject.sokoban.model.objects.Floor;
import es.upm.pproject.sokoban.model.objects.GameObject;
import es.upm.pproject.sokoban.model.objects.GoalPosition;
import es.upm.pproject.sokoban.model.objects.Player;
import es.upm.pproject.sokoban.model.objects.Wall;

public class TestModel {

	@Nested
	class GameLevelTests {

		@Nested
		class GameLevelConstructors{
			@Test
			@DisplayName("Test GameLevel constructor")
			public void gameLevelConstructorTest() throws FileNotFoundException, IllegalGameObjectException, InvalidLevelException {
				int i = 0; //First level
				int j = 0; //No initial score
				GameLevel gl = new GameLevel(i,j,"Player");
				assertNotNull(gl);
			}

			@Test
			@DisplayName("Test Game Level Loader constructor")
			public void gameLevelLoadTest()
					throws IllegalGameObjectException, InvalidLevelException, FileNotFoundException {
				int i = 0; //First level
				int j = 0; //No initial score
				GameLevel gl = new GameLevel(i,j,"Player");
				gl.movePlayer(Direction.LEFT);
				gl.movePlayer(Direction.UP);
				gl.movePlayer(Direction.DOWN);
				gl.movePlayer(Direction.RIGHT);
				gl.movePlayer(Direction.RIGHT);
				gl.movePlayer(Direction.RIGHT);
				gl.movePlayer(Direction.RIGHT);
				gl.saveLevel(1);
				GameLevel loadedLevel = new GameLevel(1,"Player");
				assertNotNull(loadedLevel);
			}
		}

		@Nested
		class GameLevelExceptions{
			@Test
			@DisplayName("Test more boxes than points")
			public void gameLevelExceptionTest91() {
				int i = 91; //Test level 91
				int j = 0; //No initial score
				assertThrows(InvalidLevelException.class, () -> {
					new GameLevel(i,j,"Player");
				});
			}
			@Test
			@DisplayName("Test no boxes and no points")
			public void gameLevelExceptionTest92() {
				int i = 92; //Test level 92
				int j = 0; //No initial score
				assertThrows(InvalidLevelException.class, () -> {
					new GameLevel(i,j,"Player");
				});
			}
			@Test
			@DisplayName("Test no players")
			public void gameLevelExceptionTest93() {
				int i = 93; //Test level 93
				int j = 0; //No initial score
				assertThrows(InvalidLevelException.class, () -> {
					new GameLevel(i,j,"Player");
				});
			}
			@Test
			@DisplayName("Test 2 players")
			public void gameLevelExceptionTest94() {
				int i = 94; //Test level 94
				int j = 0; //No initial score
				assertThrows(InvalidLevelException.class, () -> {
					new GameLevel(i,j,"Player");
				});
			}
			@Test
			@DisplayName("Character not handeled")
			public void gameLevelExceptionTest95() {
				int i = 95; //Test level 95
				int j = 0; //No initial score
				assertThrows(IllegalGameObjectException.class, () -> {
					new GameLevel(i,j,"Player");
				});
			}
		}

		@Nested
		class GameLevelSave{
			@Test
			@DisplayName("Test correct save without folder created")
			public void gameLevelCorrectSaveTest1() throws FileNotFoundException, IllegalGameObjectException, InvalidLevelException {
				int i = 0; //Initial level
				int j = 0; //No initial score
				GameLevel gl = new GameLevel(i,j,"Player");
				assertTrue(gl.saveLevel(1));
			}

			@Test
			@DisplayName("Test correct save with folder created")
			public void gameLevelCorrectSaveTest2() throws FileNotFoundException, IllegalGameObjectException, InvalidLevelException {
				int i = 0; //Initial level
				int j = 0; //No initial score
				GameLevel gl = new GameLevel(i,j,"Player");
				assertTrue(gl.saveLevel(1));
			}

			@Test
			@DisplayName("Test correct save with movements done")
			public void gameLevelCorrectSaveTest3() throws FileNotFoundException, IllegalGameObjectException, InvalidLevelException {
				int i = 2; //Initial level
				int j = 0; //No initial score
				GameLevel gl = new GameLevel(i,j,"Player");
				gl.movePlayer(Direction.RIGHT);
				gl.movePlayer(Direction.RIGHT);
				gl.movePlayer(Direction.RIGHT);
				gl.movePlayer(Direction.RIGHT);
				gl.movePlayer(Direction.RIGHT);
				gl.movePlayer(Direction.UP);
				gl.movePlayer(Direction.RIGHT);
				gl.movePlayer(Direction.DOWN);
				gl.movePlayer(Direction.DOWN);
				gl.movePlayer(Direction.DOWN);
				gl.movePlayer(Direction.DOWN);
				gl.movePlayer(Direction.DOWN);
				gl.movePlayer(Direction.RIGHT);
				gl.movePlayer(Direction.DOWN);
				gl.movePlayer(Direction.DOWN);
				gl.undoMovement();
				gl.undoMovement();
				gl.undoMovement();
				gl.undoMovement();
				gl.undoMovement();
				gl.undoMovement();
				gl.undoMovement();
				gl.undoMovement();
				assertTrue(gl.saveLevel(1));
			}

			@Test
			@DisplayName("Test incorrect save < 1")
			public void gameLevelIncorrectSaveTest1() throws FileNotFoundException, IllegalGameObjectException, InvalidLevelException {
				int i = 0; //Initial level
				int j = 0; //No initial score
				GameLevel gl = new GameLevel(i,j,"Player");
				assertFalse(gl.saveLevel(0));
			}

			@Test
			@DisplayName("Test incorrect save > 3")
			public void gameLevelIncorrectSaveTest2() throws FileNotFoundException, IllegalGameObjectException, InvalidLevelException {
				int i = 0; //Initial level
				int j = 0; //No initial score
				GameLevel gl = new GameLevel(i,j,"Player");
				assertFalse(gl.saveLevel(4));
			}
		}

		@Nested
		class GameLevelMovements{

			GameLevel gl;

			@BeforeEach
			public void Initialize() throws FileNotFoundException, IllegalGameObjectException, InvalidLevelException {
				gl = new GameLevel(0,0,"Player");
			}

			@Test
			@DisplayName("Test if we can move correctly")
			public void gameLevelMovementTest() {
				assertTrue(gl.movePlayer(Direction.DOWN)); //We should be able to move down
				assertFalse(gl.movePlayer(Direction.DOWN)); //We shouldn't be able to move down
			}


			@Test
			@DisplayName("Test undo with no movements done")
			public void gameLevelUndoMovementTest1() {
				assertFalse(gl.undoMovement());
			}

			@Test
			@DisplayName("Test undo with movements done")
			public void gameLevelUndoTest2() {
				gl.movePlayer(Direction.DOWN);
				assertTrue(gl.undoMovement());
			}

			@Test
			@DisplayName("Test if we can finish the first level")
			public void gameLevelFinishTest() {
				assertFalse(gl.isFinished());
				for (int i = 0; i < 9; i++) {
					gl.movePlayer(Direction.RIGHT);
				}
				gl.undoMovement();
				gl.undoMovement();
				gl.movePlayer(Direction.RIGHT);
				gl.movePlayer(Direction.RIGHT);
				assertTrue(gl.isFinished());
			}
		}
	}

	@Nested
	class InputManagerTests {

		@Nested
		class Constructor {
			@Test
			@DisplayName("Test InputManagerTest constructor")
			public void gameLevelConstructorTest() throws IllegalGameObjectException, InvalidLevelException, FileNotFoundException {
				InputManager input = new InputManager("resources/levels/level_2.txt");
				assertNotNull(input);
			}
		}

		@Nested
		class Methods {

			InputManager input;			

			@BeforeEach
			public void init() throws FileNotFoundException {
				input = new InputManager("resources/levels/level_2.txt");
			}

			@Nested
			class Getters {
				@Test
				@DisplayName("getLevelName Testing")
				public void getLevelNameTest() {
					assertNull(input.getLevelName());
				}
				@Test
				@DisplayName("getnRows Testing")
				public void getnRowsTest() {
					assertSame(0, input.getRows());
				}
				@Test
				@DisplayName("getnColumns Testing")
				public void getnColumsTest() {
					assertSame(0, input.getColumns());
				}
				@Test
				@DisplayName("getMovementHistory Testing")
				public void getMovementHistoryTest() {
					assertNull(input.getMovementHistory());
				}
				@Test
				@DisplayName("getLevelScore Testing")
				public void getLevelScoreTest() {
					assertSame(0, input.getLevelScore());
				}
				@Test
				@DisplayName("getGameScore Testing")
				public void getGameScoreTest() {
					assertSame(0, input.getGameScore());
				}
				@Test
				@DisplayName("getLevelNumber Testing")
				public void getLevelNumberTest() {
					assertSame(0, input.getLevelNumber());
				}
			}

			@Nested
			class Readers {
				@Test
				@DisplayName("readLevel Testing")
				public void readLevelTest() {
					input.readLevel();
					assertEquals("Workshop Panic", input.getLevelName());
					assertSame(8, input.getColumns());
					assertSame(9, input.getRows());
				}
				@Test
				@DisplayName("readNextLine Testing")
				public void readBoardCharacter() {
					input.readLevel();
					// read a character at the first, random, and last position
					assertEquals('.', input.readBoardCharacter(0));
					assertEquals('+', input.readBoardCharacter(4));
					assertEquals('.', input.readBoardCharacter(7));
					// read another line of the level
					input.readBoardCharacter(0);
					input.readBoardCharacter(0);
					assertEquals('W', input.readBoardCharacter(2));
				}

				@Test
				@DisplayName("close Testing")
				public void close() {
					input.close();
					assertThrows(Exception.class, () -> {
						input.readLevel();
					});
				}
			}

			@Test
			@DisplayName("ReadSaveFile Testing")
			public void readSavedFile() throws FileNotFoundException {
				input = new InputManager("resources/saves/test/save_2.txt");
				input.readLevel();
				int i = input.getRows();
				while (i-- > 0) {
					input.readBoardCharacter(0);
				}
				input.readSavedFile();
				assertEquals("2-3,2-2;2-2,2-3;2-3,2-2;2-2,2-3;2-3,2-2;2-2,2-3;2-3,2-2;2-2,2-3;",input.getMovementHistory());
				assertEquals(8, input.getLevelScore());
				assertEquals(0, input.getGameScore());
				assertEquals(0, input.getLevelNumber());
			}
		}
	}

	@Nested
	class MapTests {

		GameObject [][] gb = new GameObject[5][5];
		Player p1;
		Map m;
		int Rows = 5;
		int Columns = 5;
		Wall w;

		@BeforeEach	
		public void init() throws IOException {

			p1 = new Player("Player");
			w = new Wall();

			gb[0][0] = new Wall();
			gb[0][1] = new Wall();
			gb[0][2] = new Wall();
			gb[0][3] = new Wall();
			gb[0][4] = new Wall();

			gb[1][0] = new Wall();
			gb[1][1] = p1;
			gb[1][2] = new Floor();
			gb[1][3] = new Floor();
			gb[1][4] = new Wall();

			gb[2][0] = new Wall();
			gb[2][1] = new Floor();
			gb[2][2] = new Box();
			gb[2][3] = new Floor();
			gb[2][4] = new Wall();

			gb[3][0] = new Wall();
			gb[3][1] = new Floor();
			gb[3][2] = new Floor();
			gb[3][3] = new GoalPosition();
			gb[3][4] = new Wall();

			gb[4][0] = new Wall();
			gb[4][1] = new Wall();
			gb[4][2] = new Wall();
			gb[4][3] = new Wall();
			gb[4][4] = new Wall();

			m = new Map(gb, Rows, Columns, p1);
		}

		@Test
		@DisplayName("Map Contructor")
		public void MapConstructor() {
			assertNotNull(m);
		}

		@Nested
		class getters {
			@Test
			@DisplayName("Map getter")
			public void getMap() {
				GameObject go [][] = m.getBoard();
				assertNotNull(go);
			}

			@Test
			@DisplayName("Map rows getter")
			public void getRows() {
				int r = m.getRows();
				assertEquals(5, r);
			}

			@Test
			@DisplayName("Map columns getter")
			public void getColumns() {
				int c = m.getColumns();
				assertEquals(5, c);
			}

			@Test
			@DisplayName("Map player getter")
			public void getPlayer() {
				Player p = m.getPlayer();
				assertEquals(p, p1);
			}

			@Test
			@DisplayName("Map Board position getter")
			public void getBoardPosition() {
				assertTrue(gb[0][0].isWall());
			}

		}

		@Test
		@DisplayName("Map Board setter")
		public void setBoardPosition() {
			m.setBoardPosition(1,3, w);
			assertTrue(gb[1][3].isWall());
		}

	}

	@Nested
	class SokobanImplementationTests {
		@Test
		@DisplayName("Test SokobanImpl constructor")
		public void SokobanImplTest() {
			SokobanImpl sokobanImpl = new SokobanImpl();
			assertNotNull(sokobanImpl);
		}

		SokobanImpl sokobanImpl;

		@Nested
		class GenerateLevelTest{
			@BeforeEach
			public void init () {
				sokobanImpl = new SokobanImpl();
			}
			@Test
			@DisplayName("Test 1 generateLevel function")
			public void generateLevel1Test() {
				assertSame(1,sokobanImpl.generateLevel(0));
			}
			@Test
			@DisplayName("Test 2 generateLevel function")
			public void generateLevel2Test() {
				assertSame(-1,sokobanImpl.generateLevel(95));
			}
			@Test
			@DisplayName("Test 3 generateLevel function")
			public void generateLevel3Test() {
				assertSame(-1,sokobanImpl.generateLevel(91));
			}
			@Test
			@DisplayName("Test 4 generateLevel function")
			public void generateLevel4Test() {
				assertSame(0,sokobanImpl.generateLevel(117));
			}
		}

		@BeforeEach
		public void init() {
			sokobanImpl = new SokobanImpl();
			sokobanImpl.generateLevel(0);
		}

		@Nested
		class MovePlayerTest{
			@Test
			@DisplayName("Test 1 movePlayer function")
			public void movePlayer1Test() {
				assertTrue(sokobanImpl.movePlayer(Direction.UP));
			}
			@Test
			@DisplayName("Test 2 movePlayer function")
			public void movePlayer2Test() {
				sokobanImpl.movePlayer(Direction.UP);
				assertFalse(sokobanImpl.movePlayer(Direction.UP));
			}
		}

		@Nested
		class IsFinishedTest{
			@Test
			@DisplayName("Test 1 isFinished function")
			public void isFinished1Test() {
				assertFalse(sokobanImpl.isFinished());
			}
			@Test
			@DisplayName("Test 2 isFinished function")
			public void isFinished2Test() {
				sokobanImpl.movePlayer(Direction.RIGHT);
				sokobanImpl.movePlayer(Direction.RIGHT);
				sokobanImpl.movePlayer(Direction.RIGHT);
				sokobanImpl.movePlayer(Direction.RIGHT);
				sokobanImpl.movePlayer(Direction.RIGHT);
				sokobanImpl.movePlayer(Direction.RIGHT);
				sokobanImpl.movePlayer(Direction.RIGHT);
				sokobanImpl.movePlayer(Direction.RIGHT);
				assertTrue(sokobanImpl.isFinished());
			}
		}
		@Nested
		class UndoMovementTest{
			@Test
			@DisplayName("Test 1 undoMovement function")
			public void undoMovement1Test() {
				assertFalse(sokobanImpl.undoMovement());
			}

			@Test
			@DisplayName("Test 2 undoMovement function")
			public void undoMovement2Test() {
				sokobanImpl.movePlayer(Direction.RIGHT);
				assertTrue(sokobanImpl.undoMovement());
			}
		}

		@Nested
		class SaveLevelTest{
			@BeforeEach
			public void init() {
				sokobanImpl.generateLevel(0);
			}
			@Test
			@DisplayName("Test 1 saveLevel function")
			public void saveLevelTest1() {
				assertTrue(sokobanImpl.saveLevel(1));
			}
			@Test
			@DisplayName("Test 2 saveLevel function")
			public void saveLevelTest2() {
				assertFalse(sokobanImpl.saveLevel(4));
			}
		}

		@Nested
		class LoadLevelTest{
			@BeforeEach
			public void init() {
				sokobanImpl.saveLevel(1);
			}
			@Test
			@DisplayName("Test 1 loadLevel function")
			public void loadLevelTest1() {
				assertTrue(sokobanImpl.loadLevel(1));
			}
			@Test
			@DisplayName("Test 2 loadLevel function")
			public void loadLevelTest2() {
				assertFalse(sokobanImpl.loadLevel(4));
			}
		}

		@Test
		@DisplayName("Test readInstructions function")
		public void readInstructionsTest() throws FileNotFoundException {
			StringBuilder res = new StringBuilder();
			try (Scanner scan = new Scanner(new File("resources/Instructions.txt"))) {
				res.append("<html>");
				while (scan.hasNext()) {
					res.append(scan.nextLine() + "<br/>");		
				}
				res.append("</html>");
			}
			System.out.println(res.toString());
			System.out.println(sokobanImpl.readInstructions());
			assertEquals(res.toString(),sokobanImpl.readInstructions());
		}

		@Nested
		class Getters {
			@Test
			@DisplayName("Test getLevelName function")
			public void getLevelNameTest() {
				assertEquals("Tutorial", sokobanImpl.getLevelName());
			}
			@Test
			@DisplayName("Test getLevelNumber function")
			public void getLevelNumberTest() {
				assertSame(0, sokobanImpl.getLevelNumber());
			}
			@Test
			@DisplayName("Test getMap function")
			public void getMapTest() throws FileNotFoundException, IllegalGameObjectException, InvalidLevelException {
				GameObject[][] map = sokobanImpl.getMap();
				assertTrue(map[2][2].isPlayer());
			}
			@Test
			@DisplayName("Test getGameScore function")
			public void getGameScoreTest() {
				assertSame(0, sokobanImpl.getGameScore());
			}
			@Test
			@DisplayName("Test getGameScore function")
			public void getLevelScoreTest() {
				assertSame(0, sokobanImpl.getLevelScore());
			}
		}

		@Test
		@DisplayName("Test resetGameScore function")
		public void getLevelScoreTest() {
			sokobanImpl.resetGameScore();
			assertSame(0, sokobanImpl.getGameScore());
		}

		@Test
		@DisplayName("Test Textures")
		public void texturesTest(){

			assertEquals(sokobanImpl.getTexture(), "Player");

			sokobanImpl.setTexture(0);
			assertEquals(sokobanImpl.getTexture(), "Player");

			sokobanImpl.setTexture(1);
			assertEquals(sokobanImpl.getTexture(), "Chocobo");

			sokobanImpl.setTexture(2);
			assertEquals(sokobanImpl.getTexture(), "Mario");

			sokobanImpl.setTexture(3);
			assertEquals(sokobanImpl.getTexture(), "Red");

			sokobanImpl.setTexture(4);
			assertEquals(sokobanImpl.getTexture(), "Sans");
		}
	}

	@Nested
	class OutputManagerTests {
		@Test
		@DisplayName("Test OutputManager constructor")
		public void OutputManagerTest() throws FileNotFoundException, UnsupportedEncodingException {
			OutputManager output = new OutputManager("resources/saves/test/save_" + 1 + ".txt");
			assertNotNull(output);
		}

		@Test
		@DisplayName("Test 1 saveFile function")
		public void saveFileTest1() throws FileNotFoundException, IllegalGameObjectException, InvalidLevelException, UnsupportedEncodingException {
			GameLevel level = new GameLevel(0,0,"Player");
			Map map = new Map(level.getMap(), 5, 11,null);
			ActionManager actionManager = new ActionManager();
			OutputManager output = new OutputManager("resources/saves/test/save_" + 1 + ".txt");
			output.saveFile(level, map, actionManager);
			Scanner file = new Scanner(new File("resources/levels/level_" + 0 + ".txt"));
			Scanner saved = new Scanner(new File("resources/saves/test/save_" + 1 + ".txt"));
			while(file.hasNextLine() && saved.hasNextLine()) {
				assertEquals(file.nextLine(), saved.nextLine());
			}
			file.close();
			saved.close();
		}

		@Test
		@DisplayName("Test 2 saveFile function")
		public void saveFileTest2() throws FileNotFoundException, IllegalGameObjectException, InvalidLevelException, UnsupportedEncodingException {
			GameLevel level = new GameLevel(2,0,"Player");
			Map map = new Map(level.getMap(), 9, 8,null);
			ActionManager actionManager = new ActionManager();
			OutputManager output = new OutputManager("resources/saves/test/save_" + 1 + ".txt");
			output.saveFile(level, map, actionManager);
			Scanner file = new Scanner(new File("resources/levels/level_" + 2 + ".txt"));
			Scanner saved = new Scanner(new File("resources/saves/test/save_" + 1 + ".txt"));
			while(file.hasNextLine() && saved.hasNextLine()) {
				assertEquals(file.nextLine(), saved.nextLine());
			}
			file.close();
			saved.close();
		}
	}
}
