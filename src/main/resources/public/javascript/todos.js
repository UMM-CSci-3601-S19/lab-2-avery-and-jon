/**
 * Filter todos based on any provided criteria
 */
function filterToDos() {
  console.log("Getting all the todos.");

  var queryString = buildQueryString();
  var httpClient = new HttpClient();
  httpClient.get("/api/todos" + queryString, function (returnedJson) {
    document.getElementById("jsonDump").innerHTML = returnedJson;
  });
}

/**
 * Construct the query string based on any inputs with values
 */
function buildQueryString() {
  var queryString = "?";
  var filterOptions = document.getElementsByClassName("filter-option");
  var i;
  for (i = 0; i < filterOptions.length; i++) {
    var filterOption = filterOptions[i];
    if (filterOption.value !== "") {
      queryString += filterOption.id + "=" + filterOption.value + "&";
    }
  }

  // drop last "&" of queryString
  return queryString.substring(0,queryString.length-1);
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
