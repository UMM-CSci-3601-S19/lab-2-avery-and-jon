package umm3601.todo;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class FilterToDosByOwnerFromDB {

  @Test
  public void filterToDosByOwners() throws IOException {
    ToDoDatabase db = new ToDoDatabase("src/main/data/todos.json");
    ToDo[] allToDos = db.listToDos(new HashMap<>());

    ToDo[] ownedByBlanche = db.filterToDosByContains(allToDos, "Blanche");
    assertEquals("Incorrect number of todos returned", 76, ownedByBlanche.length);
  }

  @Test
  public void listToDosWithOwnersFilter() throws IOException {
    ToDoDatabase db = new ToDoDatabase("src/main/data/todos.json");
    Map<String, String[]> queryParams = new HashMap<>();

    queryParams.put("owner", new String[]{"Blanche"});
    ToDo[] ownedByBlanche = db.listToDos(queryParams);
    assertEquals("Incorrect number of todos returned", 76, ownedByBlanche.length);
  }
}
