package umm3601.todo;

public class ToDo {
  String _id;
  String owner;
  boolean status;
  String body;
  String category;

  public String getBody() {
    return this.body;
  }

  public boolean getStatus() {
    return this.status;
  }

  public String getCategory() {
    return this.category;
  }

  public String getOwner() {
    return this.owner;
  }
}

