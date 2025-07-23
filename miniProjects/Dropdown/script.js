// Select the first element with class="wrapper"
var wrapper = document.getElementsByClassName("wrapper")[0];

// Inside wrapper, select the first element with class="select-btn"
var selectBtn = document.getElementsByClassName("select-btn")[0];

// Select the first <input> element (for searching)
var searchInp = document.getElementsByTagName("input")[0];

// Select the first element with class="options" (ul to list countries)
var options = document.getElementsByClassName("options")[0];

// Declare an empty list to store all country names
var countries = [];

// 🧠 Function to add countries to the dropdown menu
// This will run after data is loaded from JSON
function addCountry(selectedCountry) {
  options.innerHTML = ""; // Clear previous list items inside <ul>

  // Loop through all countries in the list
  for (var i = 0; i < countries.length; i++) {
    var country = countries[i]; // Current country
    var isSelected = (country === selectedCountry) ? "selected" : ""; // Highlight selected

    // Create a new <li> element for each country
    var li = document.createElement("li");
    li.innerText = country;           // Set the text inside the <li>
    li.className = isSelected;        // Add "selected" class if needed
    li.setAttribute("onclick", "updateName(this)"); // Add click event
    options.appendChild(li);          // Add <li> to the <ul>
  }
}

// Function called when a country <li> is clicked
function updateName(selectedLi) {
  searchInp.value = ""; // Clear the search input field
  addCountry(selectedLi.innerText); // Rebuild the list with the selected one highlighted
  wrapper.classList.remove("active"); // Close the dropdown
  selectBtn.getElementsByTagName("span")[0].innerText = selectedLi.innerText; // Set selected text
}

// This runs every time you type in the search input
searchInp.addEventListener("keyup", function () {
  var keyword = searchInp.value.toLowerCase(); // Convert input to lowercase
  options.innerHTML = ""; // Clear existing country list

  var found = false; // Flag to check if any country matched

  // Loop through all countries to find matches
  for (var i = 0; i < countries.length; i++) {
    var country = countries[i];

    // If country starts with the keyword (case-insensitive)
    if (country.toLowerCase().indexOf(keyword) === 0) {
      var isSelected = (country === selectBtn.getElementsByTagName("span")[0].innerText) ? "selected" : "";

      // Create <li> for matched country
      var li = document.createElement("li");
      li.innerText = country;
      li.className = isSelected;
      li.setAttribute("onclick", "updateName(this)");
      options.appendChild(li);
      found = true;
    }
  }

  // If no match found, show message
  if (!found) {
    var p = document.createElement("p");
    p.innerText = "Oops! Country not found";
    p.style.marginTop = "10px";
    options.appendChild(p);
  }
});

// Toggle dropdown menu when the button is clicked
selectBtn.addEventListener("click", function () {
  wrapper.classList.toggle("active"); // Show/hide content
});

//  Load countries from countries.json using fetch (your requirement)
// This happens automatically when the page loads
// fetch("countries.json")
//   .then(function(res) {
//     return res.json();
//   })
//   .then(function(list) {
//     // extract names from object list
//     countries = [];

//     for (var i = 0; i < list.length; i++) {
//       countries.push(list[i].name); // only store country names
//     }
//    console.log(countries);
//     addCountry(); // display dropdown
//   });
fetch("countries.json")
  .then(function(res) {
    return res.json(); // Read JSON content
  })
  .then(function(list) {
    countries = list;  // Store in our countries array
    addCountry();      // Show the list in the UI
  });