# 🌍 Country Selector Dropdown

This is a simple web application that allows users to select a country from a dropdown. The dropdown supports **search**, shows **country names dynamically**, and highlights the **selected country**.

All logic (HTML, CSS, JS, and data) can be included inside **one single HTML file**, making it easy to run without setup.

---

## 🧾 Features

- 🔍 Searchable country dropdown
- ✅ Highlights selected country
- 📦 Loads countries from an object list (no typing manually)
- ⚙️ Written using basic `var`, `for` loop, and `getElementById` (no `let`, `const`, `map`, or arrow functions)
- 🎯 Perfect for JavaScript beginners

---

## 🧱 Project Structure

If you are using a **single HTML file**, the structure looks like this:

```html
<!DOCTYPE html>
<html>
  <head>
    <style> ... </style>        <!-- CSS here -->
  </head>
  <body>
    <div class="wrapper"> ... </div>   <!-- HTML structure -->

    <script>
      // JavaScript logic here
      // Fetch or hardcoded country object list
    </script>
  </body>
</html>
```

---

## 📦 Sample Country List (as JS Objects)

```js
var countries = [
  { "name": "India", "code": "IN", "continent": "Asia" },
  { "name": "United States", "code": "US", "continent": "North America" },
  { "name": "Germany", "code": "DE", "continent": "Europe" },
  { "name": "Brazil", "code": "BR", "continent": "South America" },
  ...
];
```

Only the `name` field is used for display in the dropdown.

---

## 🚀 How to Run

1. Open `index.html` in any modern browser.
2. Click the dropdown button labeled "Select Country".
3. Start typing to search for a country.
4. Click on a country to select it.

**No server required. Works fully offline.**

---

## 👨‍💻 Technologies Used

- **HTML** – Structure
- **CSS** – Styling
- **JavaScript** – Functionality (with only `var`, `for`, `getElementById`)

---

## 📌 Tips for Beginners

| Concept         | Used |
|----------------|------|
| `var` keyword  | ✅ Yes |
| `getElementById` | ✅ Yes |
| `for` loop     | ✅ Yes |
| `querySelector`, `let`, `map` | ❌ No |
| `fetch()` (optional) | ✅ Can be added |

---

## 📁 Optional (Separate JSON File Version)

If you want to store 500+ countries separately:

1. Create a `countries.json` file with:
```json
[
  { "name": "India", "code": "IN", "continent": "Asia" },
  { "name": "Australia", "code": "AU", "continent": "Oceania" }
]
```

2. Fetch it in JS:
```js
fetch("countries.json")
  .then(function(res) {
    return res.json();
  })
  .then(function(list) {
    for (var i = 0; i < list.length; i++) {
      countries.push(list[i].name);
    }
    addCountry();
  });
```

---

## 🙌 Credits

Created as a mini project for learning basic DOM, data loading, and search functionality using plain JavaScript.
