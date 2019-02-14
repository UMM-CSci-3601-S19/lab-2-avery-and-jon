package umm3601.todo;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class ToDoDatabase {
  private ToDo[] allToDos;

  public ToDoDatabase(String userDataFile) throws IOException {
    Gson gson = new Gson();
    FileReader reader = new FileReader(userDataFile);
    allToDos = gson.fromJson(reader, ToDo[].class);
  }

  public ToDo[] listToDos(Map<String, String[]> queryParams) {
    ToDo[] filteredUsers = allToDos;

    return filteredUsers;
  }
}
