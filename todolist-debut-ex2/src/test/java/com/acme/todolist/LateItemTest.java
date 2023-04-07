package com.acme.todolist;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.acme.todolist.domain.TodoItem;

@SpringBootTest
public class LateItemTest {
	
	static final String LATE = "[LATE!]";
	
	@Test
	public void test_islate_is_true() {
		// On créé un todoItem qui a 50 heures
		Instant time = Instant.now().minus(50,ChronoUnit.HOURS);
		TodoItem todoItem = new TodoItem("1", time, "test_islate_is_true");
		
		// On vérifie que le finalContent du todoItem contient bien le "[LATE]" rajouté
		assertTrue(todoItem.finalContent().contains(LATE));
	}
	
	@Test
	public void test_islate_is_false() {
		// On créé un todoItem qui a moins de 24 heures
		Instant time = Instant.now();
		TodoItem todoItem = new TodoItem("2", time, "test_islate_is_false");
	
		// On vérifie que le finalContent du todoItem ne contient pas "[LATE]"
		assertFalse(todoItem.finalContent().contains(LATE));
	}
}
