package umm3601.todo;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.*;

public class FilterToDosByStatusFromDB {

  @Test
  public void filterToDosByStatus() throws IOException {
    ToDoDatabase db = new ToDoDatabase("src/main/data/todos.json");
    ToDo[] allToDos = db.listToDos(new HashMap<>());

    ToDo[] completeToDos = db.filterToDosByStatus(allToDos, "complete");
    ToDo[] incompleteToDos = db.filterToDosByStatus(allToDos, "incomplete");
    
    assertEquals("Number of complete and incomplete todos don't add up", allToDos.length, completeToDos.length + incompleteToDos.length);
  }

  @Test
  public void listToDosWithLimitFilter() {

  }
}
