package umm3601.todo;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class FilterToDosByStatusFromDB {

  @Test
  public void filterToDosByStatus() throws IOException {
    ToDoDatabase db = new ToDoDatabase("src/main/data/todos.json");
    ToDo[] allToDos = db.listToDos(new HashMap<>());

    ToDo[] completeToDos = db.filterToDosByStatus(allToDos, "complete");
    ToDo[] incompleteToDos = db.filterToDosByStatus(allToDos, "incomplete");

    assertEquals("Number of complete and incomplete todos don't add up", 300, completeToDos.length + incompleteToDos.length);
  }

  @Test
  public void listToDosWithStatusFilter() throws IOException {
    ToDoDatabase db = new ToDoDatabase("src/main/data/todos.json");
    Map<String, String[]> queryParams = new HashMap<>();

    queryParams.put("status", new String[]{"complete"});
    ToDo[] completeToDos = db.listToDos(queryParams);

    queryParams.put("status", new String[]{"incomplete"});
    ToDo[] incompleteToDos = db.listToDos(queryParams);
    assertEquals("Incorrect number of todos", 300, completeToDos.length + incompleteToDos.length);
  }
}
