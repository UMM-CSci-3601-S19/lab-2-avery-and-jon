package umm3601.todo;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class FilterToDosByContainsFromDB {

  @Test
  public void filterToDosByContains() throws IOException {
    ToDoDatabase db = new ToDoDatabase("src/main/data/todos.json");
    ToDo[] allToDos = db.listToDos(new HashMap<>());

    ToDo[] containingIncididunt = db.filterToDosByContains(allToDos, "incididunt");
    assertEquals("Incorrect number of todos returned", 76, containingIncididunt.length);
  }

  @Test
  public void listToDosWithContainsFilter() throws IOException {
    ToDoDatabase db = new ToDoDatabase("src/main/data/todos.json");
    Map<String, String[]> queryParams = new HashMap<>();

    queryParams.put("contains", new String[]{"incididunt"});
    ToDo[] containingIncididunt = db.listToDos(queryParams);
    assertEquals("Incorrect number of todos returned", 76, containingIncididunt.length);
  }
}
