package umm3601.todo;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class ToDoDatabase {
  private ToDo[] allToDos;

  public ToDoDatabase(String userDataFile) throws IOException {
    Gson gson = new Gson();
    FileReader reader = new FileReader(userDataFile);
    allToDos = gson.fromJson(reader, ToDo[].class);
  }

  public ToDo[] listToDos(Map<String, String[]> queryParams) {
    ToDo[] filteredToDos = allToDos;

    if (queryParams.containsKey("limit")) {
      int limit = Integer.parseInt(queryParams.get("limit")[0]);
      filteredToDos = filterToDosByLimit(filteredToDos, limit);
    }

    return filteredToDos;
  }

  public ToDo[] filterToDosByLimit(ToDo[] toDos, int limit) {
    ToDo[] firstXToDos;
    // case where provided limit is greater than (or equal to) the length of the array to filter
    if (limit >= toDos.length) { firstXToDos = toDos; }
    else { firstXToDos = Arrays.copyOfRange(toDos,0, limit); }
    return firstXToDos;
  }
}
