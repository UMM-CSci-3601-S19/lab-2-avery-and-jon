package umm3601.todo;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class FilterToDosByCategory {

  @Test
  public void filterToDosByCategory() throws IOException {
    ToDoDatabase db = new ToDoDatabase("src/main/data/todos.json");
    ToDo[] allToDos = db.listToDos(new HashMap<>());

    ToDo[] categorySoftwareDesign = db.filterToDosByCategory(allToDos, "software design");
    assertEquals("Incorrect number of todos returned", 74, categorySoftwareDesign.length);
  }

  @Test
  public void listToDosWithCategoryFilter() throws IOException {
    ToDoDatabase db = new ToDoDatabase("src/main/data/todos.json");
    Map<String, String[]> queryParams = new HashMap<>();

    queryParams.put("category", new String[]{"software design"});
    ToDo[] categorySoftwareDesign = db.listToDos(queryParams);
    assertEquals("Incorrect number of todos returned", 74, categorySoftwareDesign.length);
  }
}

