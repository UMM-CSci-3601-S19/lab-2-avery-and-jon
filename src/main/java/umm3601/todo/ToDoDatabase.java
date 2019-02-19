package umm3601.todo;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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

    if (queryParams.containsKey("status")) {
      String status = queryParams.get("status")[0];
      filteredToDos = filterToDosByStatus(filteredToDos, status);
    }
    if (queryParams.containsKey("contains")) {
      String sub = queryParams.get("contains")[0];
      filteredToDos = filterToDosByContains(filteredToDos, sub);
    }
    if (queryParams.containsKey("owner")) {
      String owner = queryParams.get("owner")[0];
      filteredToDos = filterToDosByOwner(filteredToDos, owner);
    }
    if (queryParams.containsKey("category")) {
      String category = queryParams.get("category")[0];
      filteredToDos = filterToDosByCategory(filteredToDos, category);
    }
    if (queryParams.containsKey("limit")) {
      int limit = Integer.parseInt(queryParams.get("limit")[0]);
      filteredToDos = filterToDosByLimit(filteredToDos, limit);
    }

    ToDo[] sortedToDos = sortToDos(filteredToDos, queryParams.get("orderBy")[0]);

    return sortedToDos;
  }

  public ToDo[] filterToDosByLimit(ToDo[] toDos, int limit) {
    ToDo[] firstXToDos;
    // case where provided limit is greater than (or equal to) the length of the array to filter
    if (limit >= toDos.length) { firstXToDos = toDos; }
    else { firstXToDos = Arrays.copyOfRange(toDos,0, limit); }
    return firstXToDos;
  }

  public ToDo[] filterToDosByStatus (ToDo[] toDos, String status) {
    boolean statusBoolean;
    if (status.equals("complete")) {
      statusBoolean = true;
    } else {
      statusBoolean = false;
    }
    return Arrays.stream(toDos).filter(x -> x.status == statusBoolean).toArray(ToDo[]::new);
  }

  public ToDo[] filterToDosByContains (ToDo[] toDos, String sub) {
    return Arrays.stream(toDos).filter(x -> x.body.contains(sub)).toArray(ToDo[]::new);
  }

  public ToDo[] filterToDosByOwner (ToDo[] toDos, String owner) {
    return Arrays.stream(toDos).filter(x -> x.owner.equals(owner)).toArray(ToDo[]::new);
  }

  public ToDo[] filterToDosByCategory (ToDo[] toDos, String category) {
    return Arrays.stream(toDos).filter(x -> x.category.equals(category)).toArray(ToDo[]::new);
  }

  // used Option 4 found in https://dev.to/codebyamir/sort-a-list-of-objects-by-field-in-java-3coj
  public ToDo[] sortToDos(ToDo[] toDos, String sortBy) {
    ToDo[] sortedToDos;
    if (sortBy.equals("body")) {
       sortedToDos = Arrays.stream(toDos).sorted(Comparator.comparing(ToDo::getBody)).toArray(ToDo[]::new);
    } else if (sortBy.equals("status")) {
      sortedToDos = Arrays.stream(toDos).sorted(Comparator.comparing(ToDo::getStatus)).toArray(ToDo[]::new);
    } else if (sortBy.equals("category")) {
      sortedToDos = Arrays.stream(toDos).sorted(Comparator.comparing(ToDo::getCategory)).toArray(ToDo[]::new);
    } else {
      // default sort by owner
      sortedToDos = Arrays.stream(toDos).sorted(Comparator.comparing(ToDo::getOwner)).toArray(ToDo[]::new);
    }

    return sortedToDos;
  }
}
