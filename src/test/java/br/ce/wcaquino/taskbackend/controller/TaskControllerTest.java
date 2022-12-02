package br.ce.wcaquino.taskbackend.controller;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

public class TaskControllerTest {

	@Test
	public void naoDeveSalvarTarefaSemDescrição() throws ValidationException {
		Task todo = new Task();
		todo.setDueDate(LocalDate.now());
		// todo.setTask("Descrição");
		TaskController controller = new TaskController();

		try {
			controller.save(todo);
		} catch (ValidationException e) {
			assertEquals("Fill the task description", e.getMessage());
		}
	}

	@Test
	public void naoDeveSalvarTarefaSemData() {
		Task todo = new Task();
		// todo.setDueDate(LocalDate.now());
		todo.setTask("Descrição");
		TaskController controller = new TaskController();

		try {
			controller.save(todo);
		} catch (ValidationException e) {
			assertEquals("Fill the due date", e.getMessage());
		}
	}

	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		Task todo = new Task();
		todo.setDueDate(LocalDate.of(2010, 03, 03));
		todo.setTask("Descrição");
		TaskController controller = new TaskController();

		try {
			controller.save(todo);
		} catch (ValidationException e) {
			assertEquals("Due date must not be in past", e.getMessage());
		}
	}

}
