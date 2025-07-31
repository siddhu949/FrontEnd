var toDoList = [
  {
    "toDoId": 1,
    "taskDescription": "Buy milk",
    "date": "22-02-2025",
    "time": "14:30:00",
    "status": "inprogress",
    "currentDate": "02-22-2025 14:30:00"
  },
  {
    "toDoId": 2,
    "taskDescription": "Finish project report",
    "date": "23-02-2025",
    "time": "09:00:00",
    "status": "pending",
    "currentDate": "02-23-2025 09:00:00"
  }
];

var toDoId = toDoList.length;
var editingRow = null;

setInterval(function () {
  var date = new Date();
  var time = date.toLocaleTimeString();
  document.getElementById("distime").innerText = time;
}, 1000);

setInterval(function () {
  var now = new Date();
  var dateString = now.toLocaleDateString('en-US', {
    weekday: 'long',
    year: 'numeric',
    month: 'long',
    day: 'numeric'
  });
  document.getElementById("disdate").innerText = dateString;
}, 1000);

// Render Row
function renderListTable(value) {
  var newToDoTr = document.createElement('tr');

  var newTodoIdTd = document.createElement('td');
  newTodoIdTd.innerText = value.toDoId;

  var newtaskDescptionTd = document.createElement('td');
  newtaskDescptionTd.innerText = value.taskDescription;

  var newtargetDateTd = document.createElement('td');
  newtargetDateTd.innerText = value.date;

  var newtargetTimeTd = document.createElement('td');
  newtargetTimeTd.innerText = value.time;

  var newstatusTd = document.createElement('td');
  newstatusTd.innerText = value.status;

  var newcurrentDateTd = document.createElement('td');
  newcurrentDateTd.innerText = value.currentDate;

  // Action button
  var newActionTd = document.createElement('td');
  var actionBtn = document.createElement('button');
  actionBtn.innerText = "In Progress";
  actionBtn.style.cursor = "pointer";
  actionBtn.addEventListener('click', function () {
    if (newstatusTd.innerText === "pending") {
      newstatusTd.innerText = "inprogress";
      actionBtn.innerText = "Completed";
    } else if (newstatusTd.innerText === "inprogress") {
      newstatusTd.innerText = "completed";
      actionBtn.disabled = true;
      actionBtn.style.display = "none";
    }
  });
  newActionTd.appendChild(actionBtn);

  // Edit Button
  var editBtn = document.createElement('button');
  editBtn.className = 'editBtn';
  editBtn.innerHTML = `<img src="edit_svg.svg" alt="Edit" width="16" height="16">`;
  editBtn.style.marginTop = "10px";
  editBtn.style.marginRight = "5px";

  editBtn.addEventListener('click', function () {
    document.getElementById('taskDesc').value = newtaskDescptionTd.innerText;

    var [dd, mm, yyyy] = newtargetDateTd.innerText.split("-");
    document.getElementById('targetDate').value = `${yyyy}-${mm}-${dd}`;

    document.getElementById('targetTime').value = newtargetTimeTd.innerText;
    document.getElementById('submit').innerText = "Update";

    editingRow = {
      row: newToDoTr,
      descCell: newtaskDescptionTd,
      dateCell: newtargetDateTd,
      timeCell: newtargetTimeTd
    };
  });

  newToDoTr.appendChild(newTodoIdTd);
  newToDoTr.appendChild(newtaskDescptionTd);
  newToDoTr.appendChild(newtargetDateTd);
  newToDoTr.appendChild(newtargetTimeTd);
  newToDoTr.appendChild(newstatusTd);
  newToDoTr.appendChild(newcurrentDateTd);
  newToDoTr.appendChild(newActionTd);
  newToDoTr.appendChild(editBtn);

  document.getElementById('toDoTable').appendChild(newToDoTr);
}

// Initial render
function renderTodoItems(list) {
  list.forEach(renderListTable);
}
renderTodoItems(toDoList);

// Add ToDo
function toDoAdd(taskDesc, targetDate, targetTime, status, currentDate) {
  toDoId++;
  var taskObj = {
    toDoId: toDoId,
    taskDescription: taskDesc,
    date: targetDate,
    time: targetTime,
    status: status,
    currentDate: currentDate
  };
  toDoList.push(taskObj);
  renderListTable(taskObj);
}

// Form Submission
function submitTask(event) {
  event.preventDefault();

  var taskDesc = document.getElementById('taskDesc').value.trim();
  var rawDate = document.getElementById('targetDate').value;
  var rawTime = document.getElementById('targetTime').value;

  if (!taskDesc || !rawDate || !rawTime) {
    alert("Please fill all the fields");
    return;
  }

  var parts = rawDate.split("-");
  var targetDate = parts[2] + "-" + parts[1] + "-" + parts[0];

  var now = new Date();
  var currentDate = now.toLocaleDateString() + " " + now.toLocaleTimeString();

  if (editingRow !== null) {
    editingRow.descCell.innerText = taskDesc;
    editingRow.dateCell.innerText = targetDate;
    editingRow.timeCell.innerText = rawTime;
    editingRow = null;
    document.getElementById('submit').innerText = "Add Task";
  } else {
    var status = "pending";
    toDoAdd(taskDesc, targetDate, rawTime, status, currentDate);
  }

  // Clear form
  document.getElementById('taskDesc').value = "";
  document.getElementById('targetDate').value = "";
  document.getElementById('targetTime').value = "";
}

// Set min date for date input
window.onload = function () {
  var today = new Date().toISOString().split("T")[0];
  document.getElementById("targetDate").min = today;
};
