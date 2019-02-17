package umm3601.todo;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Tests umm3601.todo.ToDoDatabase filterToDosByLimit
 * and listToDos with _limit_ query parameters
 */
public class FilterToDosByLimitFromDB {

  @Test
  public void filterToDosByLimit() throws IOException {
    ToDoDatabase db = new ToDoDatabase("src/main/data/todos.json");
    ToDo[] allToDos = db.listToDos(new HashMap<>());

    ToDo[] limit7ToDos = db.filterToDosByLimit(allToDos, 7);
    assertEquals("Incorrect number of todos", 7, limit7ToDos.length);

    ToDo[] limit0ToDos = db.filterToDosByLimit(allToDos, 0);
    assertEquals("Incorrect number of todos", 0, limit0ToDos.length);

    ToDo[] limit1000ToDos = db.filterToDosByLimit(allToDos, 1000);
    assertEquals("Incorrect number of todos", allToDos.length, limit1000ToDos.length);
  }
}
