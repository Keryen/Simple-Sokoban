package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.model.exceptions.IllegalGameObjectException;
import es.upm.pproject.sokoban.model.exceptions.InvalidLevelException;

public class TestExceptions {

	@Nested
	class Constructors {

		@Test
		@DisplayName("Testing IllegalGameObjectException")
		public void TestIllegalGameObjectException() {
			IllegalGameObjectException e = new IllegalGameObjectException("");
			assertNotNull(e);
		}

		@Test
		@DisplayName("Testing InvalidLevelException")
		public void TestInvalidLevelException() {
			InvalidLevelException e = new InvalidLevelException("");
			assertNotNull(e);
		}
	}
	
}
