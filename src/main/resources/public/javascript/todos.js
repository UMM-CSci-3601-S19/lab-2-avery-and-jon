/**
 * Function to get all the todos!
 */
function getAllToDos() {
  console.log("Getting all the todos.");

  /* currently throws an error in the console:
  * Uncaught ReferenceError: HttpClient is not defined
    at HTMLButtonElement.getAllToDos (todos.js:7)
  */
  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos", function (returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}
