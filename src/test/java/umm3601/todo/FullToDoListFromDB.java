package umm3601.todo;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class FullToDoListFromDB {


  @Test
  public void totalToDoCount() throws IOException {
    ToDoDatabase db = new ToDoDatabase("src/main/data/todos.json");
    ToDo[] allToDos = db.listToDos(new HashMap<>());
    assertEquals("Incorrect total number of users", 300, allToDos.length);
  }

  @Test
  public void firstUserInFullList() throws IOException {
    ToDoDatabase db = new ToDoDatabase("src/main/data/todos.json");
    ToDo[] allToDos = db.listToDos(new HashMap<>());
    ToDo firstToDo = allToDos[0];
    assertEquals("Incorrect id", "58895985a22c04e761776d54", firstToDo._id);
    assertEquals("Incorrect owner", "Blanche", firstToDo.owner);
    assertEquals("Incorrect status", false, firstToDo.status);
    assertEquals("Incorrect body", "In sunt ex non tempor cillum commodo amet incididunt anim qui commodo quis. Cillum non labore ex sint esse.", firstToDo.body);
    assertEquals("Incorrect category", "software design", firstToDo.category);
  }
}
