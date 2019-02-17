/**
 * Function to get all the todos!
 */
function getAllToDos() {
  console.log("Getting all the todos.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos", function (returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

/**
 * Request all todos unless a limit is specified
 */
function getAllToDosWithLimit() {
  console.log("Getting all the todos.");

  var httpClient = new HttpClient();
  var limit = document.getElementById('limit').value;

  if (limit === "") {
    httpClient.get("/api/todos", function (returned_json) {
      document.getElementById('jsonDump').innerHTML = returned_json;
    });
  } else {
    httpClient.get("/api/todos?limit=" + limit, function (returned_json) {
      document.getElementById('jsonDump').innerHTML = returned_json;
    });
  }
}

/**
 * Wrapper to make generating http requests easier. Should maybe be moved
 * somewhere else in the future!.
 *
 * Based on: http://stackoverflow.com/a/22076667
 * Now with more comments!
 */
function HttpClient() {
  // We'll take a URL string, and a callback function.
  this.get = function (aUrl, aCallback) {
    var anHttpRequest = new XMLHttpRequest();

    // Set a callback to be called when the ready state of our request changes.
    anHttpRequest.onreadystatechange = function () {

      /**
       * Only call our 'aCallback' function if the ready state is 'DONE' and
       * the request status is 200 ('OK')
       *
       * See https://httpstatuses.com/ for HTTP status codes
       * See https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/readyState
       *  for XMLHttpRequest ready state documentation.
       *
       */
      if (anHttpRequest.readyState === 4 && anHttpRequest.status === 200)
        aCallback(anHttpRequest.responseText);
    };

    anHttpRequest.open("GET", aUrl, true);
    anHttpRequest.send(null);
  }
}
