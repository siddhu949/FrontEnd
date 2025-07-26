setInterval(function () {
  var date = new Date()
  var time = date.toLocaleTimeString();
  document.getElementById("distime").innerText = time;
}, 1000)
setInterval(function () {
  var now = new Date();

  // Format: Saturday, July 20, 2025
  var dateString = now.toLocaleDateString('en-US', {
    weekday: 'long',
    year: 'numeric',
    month: 'long',
    day: 'numeric'
  });

  document.getElementById("disdate").innerText = dateString;
}, 1000);
var toDoList = [];//list declaring
var toDoId = 1;
var editingRow = null;
function toDoAdd(taskDesc, targetDate, targetTime, status, currentDate) {
  //store task in array
  var taskObj = {
    id: toDoId,
    taskDescription: taskDesc,
    date: targetDate,
    time: targetTime,
    status: status,
    createdAt: currentDate

  };
  //pushing into empty list
  toDoList.push(taskObj);
  //iterate and retriving particular col
  for (var i = 0; i < toDoList.length; i++) {
    console.log(toDoList[i].taskDescription);
  }
  /* var taskDescption=document.getElementById('Taskdesc').value;
   var targetDate=document.getElementById('TargetDate').value;
   var status=document.getElementById('status').value;
   var currentDate=document.getElementById("currentDate").value;*/

  //create a table new row using document method craeteElement
  var newToDoTr = document.createElement('tr');
  //create cell using td 
  var newTodoIdTd = document.createElement('td');
  newTodoIdTd.innerText = toDoId;
  //create again cell using id
  var newtaskDescptionTd = document.createElement('td');
  newtaskDescptionTd.innerText = taskDesc;

  var newtargetDateTd = document.createElement('td');
  newtargetDateTd.innerText = targetDate;

  var newtargetTimeTd = document.createElement('td');
  newtargetTimeTd.innerText = targetTime;

  var newstatusTd = document.createElement('td');
  newstatusTd.innerText = 'pending';

  var newcurrentDateTd = document.createElement('td');
  newcurrentDateTd.innerText = currentDate;

  // Action button cell
  var newActionTd = document.createElement('td');
  var actionBtn = document.createElement('button');
  actionBtn.innerText = "In Progress";
  actionBtn.style.cursor = "pointer";

  // Attach event listener to change status and button text
  actionBtn.addEventListener('click', function () {
    if (newstatusTd.innerText === "pending") {
      newstatusTd.innerText = "in_progress";
      actionBtn.innerText = "Completed";
    } else if (newstatusTd.innerText === "in_progress") {
      newstatusTd.innerText = "completed";
      actionBtn.disabled = true;
      actionBtn.innerText = ""; // or remove it: actionBtn.style.display = "none";
    }
  });
var editBtn = document.createElement('button');
editBtn.innerText = "Edit";
editBtn.style.marginLeft = "10px";
editBtn.style.cursor = "pointer";

editBtn.addEventListener('click', function () {
  document.getElementById('taskDesc').value = newtaskDescptionTd.innerText;
  var [dd, mm, yyyy] = newtargetDateTd.innerText.split("-");
  document.getElementById('targetDate').value = `${yyyy}-${mm}-${dd}`;
  document.getElementById('targetTime').value = newtargetTimeTd.innerText;
  document.getElementById('submitBtn').innerText = "Update";

  // Add edit note placeholder if not exists
  if (!newtaskDescptionTd.querySelector('.edit-note')) {
    var note = document.createElement('div');
    note.className = "edit-note";
    note.style.fontSize = "10px";
    note.style.color = "gray";
    newtaskDescptionTd.appendChild(note);
  }

  editingRow = {
    descCell: newtaskDescptionTd,
    dateCell: newtargetDateTd,
    timeCell: newtargetTimeTd,
    noteDiv: newtaskDescptionTd.querySelector('.edit-note')
  };
});


  //appendchild which add to the existing table row using varname./document created for that element.appendchild
  newToDoTr.appendChild(newTodoIdTd);
  newToDoTr.appendChild(newtaskDescptionTd);
  newToDoTr.appendChild(newtargetDateTd);
  newToDoTr.appendChild(newtargetTimeTd);
  newToDoTr.appendChild(newstatusTd);
  newToDoTr.appendChild(newcurrentDateTd);
  newActionTd.appendChild(actionBtn);
  newToDoTr.appendChild(newActionTd);

  // now we should appendchild means we should add the row to table means new row
  var table = document.getElementById('toDoTable');
  table.appendChild(newToDoTr);

  toDoId++;//increament id
  console.log(taskDesc, targetDate, targetTime, status, currentDate);

}
//we use event listener to call the function when button is clicked
//it is used for the page reload prevention in this method

function submitTask(event) {
  event.preventDefault(); // stop the page from reloading
  var taskDescription = document.getElementById('taskDesc').value;
  var rawtargetDate = document.getElementById('targetDate').value;
  var parts = rawtargetDate.split("-");
  var targetDate = parts[2] + "-" + parts[1] + "-" + parts[0];
  var targetTime = document.getElementById('targetTime').value;
  var status = 'pending';
  var now = new Date();
  var currentDate = now.toLocaleDateString() + " " + now.toLocaleTimeString();
  //validation-we use loop to check the input fields
  if (taskDescription === "" || targetDate === "" || targetTime === "") {
    alert("Please fill all the fields");
    return;
  }
  //calling or invoke the function
  toDoAdd(taskDescription, targetDate, targetTime, status, currentDate);
  //after adding the task we should clear the input fields
  document.getElementById('taskDesc').value = "";
  document.getElementById('targetDate').value = "";
  document.getElementById('targetTime').value = "";
  document.getElementById('status').value = "";
}
//event which calls after loading all images and all
window.onload = function () {
  var today = new Date().toISOString().split("T")[0];//iso8601 format date to object and split into two parts from 0 date
  document.getElementById("targetDate").min = today;

};