package umm3601.todo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import spark.Request;
import spark.Response;

import static umm3601.Util.buildSuccessJsonResponse;

public class ToDoController {

  private final Gson gson;
  private ToDoDatabase database;

  public ToDoController(ToDoDatabase database) {
    gson = new Gson();
    this.database = database;
  }

  public JsonObject getToDos(Request req, Response res) {
    res.type("application/json");
    ToDo[] allToDos = database.listToDos(req.queryMap().toMap());
    return buildSuccessJsonResponse("toDos", gson.toJsonTree(allToDos));
  }
}
