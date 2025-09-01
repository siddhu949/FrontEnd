const searchInput=document.getElementById('search');
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
  },
  {
    "toDoId": 3,
    "taskDescription": "Call plumber",
    "date": "24-02-2025",
    "time": "16:00:00",
    "status": "inprogress",
    "currentDate": "02-24-2025 16:00:00"
  },
  {
    "toDoId": 4,
    "taskDescription": "Attend yoga class",
    "date": "25-02-2025",
    "time": "07:30:00",
    "status": "completed",
    "currentDate": "02-25-2025 07:30:00"
  },
  {
    "toDoId": 5,
    "taskDescription": "Submit tax documents",
    "date": "26-02-2025",
    "time": "11:00:00",
    "status": "pending",
    "currentDate": "02-26-2025 11:00:00"
  }
];
//list declaring
//var toDoId = toDoList.length;
//var toDoId = 1;
var editingRow = null;
function getMaxToDoId(max,value,index,array){
  return max.toDoId>value.toDoId ?max:value;
}
 console.log("max Obj :"+toDoList.reduce(getMaxToDoId));
 var maxObj =toDoList.reduce(getMaxToDoId);
  console.log("max Id :"+maxObj.toDoId);
   var toDoId = maxObj.toDoId; 
   var toDoId = maxObj ? maxObj.toDoId : 0;//this is used to update the next maxobj id
//creating map
function renderListTable(value, index, array) {

  //create a table new row using document method craeteElement
  var newToDoTr = document.createElement('tr');
  //create cell using td 
  var newTodoIdTd = document.createElement('td');
  newTodoIdTd.innerText = value.toDoId;
  //create again cell using toDoId
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

  // Action button cell
  var newActionTd = document.createElement('td');
  var actionBtn = document.createElement('button');
  actionBtn.style.cursor = "pointer";

  // Set initial button text based on current status
  if (value.status === "pending") {
    actionBtn.innerText = "In Progress";
  } else if (value.status === "inprogress") {
    actionBtn.innerText = "Completed";
  } else if (value.status === "completed") {
    actionBtn.style.display = "none"; // Hide if already completed
  }


  // Attach event listener to change status and button text
//  actionBtn.addEventListener('click', function () {
//   if (newstatusTd.innerText === "pending") {
//     newstatusTd.innerText = "inprogress";
//     actionBtn.innerText = "Completed";
//   } else if (newstatusTd.innerText === "inprogress") {
//     newstatusTd.innerText = "completed";
//     actionBtn.style.display = "none";
//   }
// });
// actionBtn.addEventListener('click', function () {
//   if (newstatusTd.innerText === "pending") {
//     newstatusTd.innerText = "inprogress";
//     actionBtn.innerText = "Completed";

//     // Update the status in toDoList
//     var task = toDoList.find(function (item) {
//       return item.toDoId === value.toDoId;
//     });
//     if (task) task.status = "inprogress";

//   } else if (newstatusTd.innerText === "inprogress") {
//     newstatusTd.innerText = "completed";
//     actionBtn.style.display = "none";

//     // Update the status in toDoList
//     var task = toDoList.find(function (item) {
//       return item.toDoId === value.toDoId;
//     });
//     if (task) task.status = "completed";
//   }
// });
actionBtn.addEventListener('click', function (event) {
  console.log("Event type:", event.type);         // → click
  console.log("Clicked element:", event.target);  // → the button (actionBtn)

  // Example: Highlight the button briefly when clicked
  event.target.style.backgroundColor = "lightgreen";
  setTimeout(function () {
    event.target.style.backgroundColor = ""; // reset after 0.5s
  }, 500);

  if (newstatusTd.innerText === "pending") {
    newstatusTd.innerText = "inprogress";
    actionBtn.innerText = "Completed";

    // Update the status in toDoList
    var task = toDoList.find(function (item) {
      return item.toDoId === value.toDoId;
    });
    if (task) task.status = "inprogress";

  } else if (newstatusTd.innerText === "inprogress") {
    newstatusTd.innerText = "completed";
    actionBtn.style.display = "none";

    // Update the status in toDoList
    var task = toDoList.find(function (item) {
      return item.toDoId === value.toDoId;
    });
    if (task) task.status = "completed";
  }
});



  newActionTd.appendChild(actionBtn);
  var editBtn = document.createElement('button');

  editBtn.className = 'editBtn';
  editBtn.innerHTML = `<img src="edit_svg.svg" alt="Edit" width="16" height="16">`;
  editBtn.style.marginTop = "10px";
  editBtn.style.marginrign = "5px";

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
      timeCell: newtargetTimeTd,
    };

  });


  //appendchild which add to the existing table row using varname./document created for that element.appendchild
  newToDoTr.appendChild(newTodoIdTd);
  newToDoTr.appendChild(newtaskDescptionTd);
  newToDoTr.appendChild(newtargetDateTd);
  newToDoTr.appendChild(newtargetTimeTd);
  newToDoTr.appendChild(newstatusTd);
  newToDoTr.appendChild(newcurrentDateTd);
  newToDoTr.appendChild(newActionTd);
  newToDoTr.appendChild(editBtn);


  // now we should appendchild means we should add the row to table means new row
  var table = document.getElementById('toDoTable');
  table.appendChild(newToDoTr);

  
 console.log("Task:", value.taskDescription);
  console.log("Index:", index);
  // console.log("Total Tasks:", array.length);

}

function renderTodoItems(list) {
  list.map(renderListTable);
}
renderTodoItems(toDoList);
function toDoAdd(taskDesc, targetDate, targetTime, status, currentDate) {
  //store taskDescription in array
  //  todoDesc = document.getElementById("taskDesc").value;
  //       targetDate = document.getElementById("targetDate").value;
  //       status = document.getElementById("status").value;
toDoId++;//increament toDoId
  console.log(taskDesc, targetDate, targetTime, status, currentDate);


  var taskObj = {
    toDoId: toDoId,
    taskDescription: taskDesc,
    date: targetDate,
    time: targetTime,
    status: status || "pending",
    currentDate: currentDate

  };
  //pushing into empty list
  toDoList.push(taskObj);
  renderListTable(taskObj);//render only new task
  //iterate and retriving particular col
  for (var i = 0; i < toDoList.length; i++) {
    console.log(toDoList[i].taskDescription);
  }
  /* var taskDescption=document.getElementById('Taskdesc').value;
   var targetDate=document.getElementById('TargetDate').value;
   var status=document.getElementById('status').value;
   var currentDate=document.getElementById("currentDate").value;*/

  //   //create a table new row using document method craeteElement
  //   var newToDoTr = document.createElement('tr');
  //   //create cell using td 
  //   var newTodoIdTd = document.createElement('td');
  //   newTodoIdTd.innerText = toDoId;
  //   //create again cell using toDoId
  //   var newtaskDescptionTd = document.createElement('td');
  //   newtaskDescptionTd.innerText = taskDesc;

  //   var newtargetDateTd = document.createElement('td');
  //   newtargetDateTd.innerText = targetDate;

  //   var newtargetTimeTd = document.createElement('td');
  //   newtargetTimeTd.innerText = targetTime;

  //   var newstatusTd = document.createElement('td');
  //   newstatusTd.innerText = 'pending';

  //   var newcurrentDateTd = document.createElement('td');
  //   newcurrentDateTd.innerText = currentDate;

  //   // Action button cell
  //   var newActionTd = document.createElement('td');
  //   var actionBtn = document.createElement('button');
  //   actionBtn.innerText = "In Progress";
  //   actionBtn.style.cursor = "pointer";

  //   // Attach event listener to change status and button text
  //   actionBtn.addEventListener('click', function () {
  //     if (newstatusTd.innerText === "pending") {
  //       newstatusTd.innerText = "in_progress";
  //       actionBtn.innerText = "Completed";
  //     } else if (newstatusTd.innerText === "in_progress") {
  //       newstatusTd.innerText = "completed";
  //       actionBtn.disabled = true;
  //       //actionBtn.innerText = ""; or remove it: 
  //       actionBtn.style.display = "none";
  //     }
  //   });
  // var editBtn = document.createElement('button');

  // editBtn.className = 'editBtn';
  // editBtn.innerHTML = `<img src="edit_svg.svg" alt="Edit" width="16" height="16">`;
  // editBtn.style.marginTop="10px";
  // editBtn.style.marginrign="5px";

  // editBtn.addEventListener('click', function () {
  //   document.getElementById('taskDesc').value = newtaskDescptionTd.innerText;
  //   var [dd, mm, yyyy] = newtargetDateTd.innerText.split("-");
  //   document.getElementById('targetDate').value = `${yyyy}-${mm}-${dd}`;
  //   document.getElementById('targetTime').value = newtargetTimeTd.innerText;
  //   document.getElementById('submit').innerText = "Update";

  // editingRow = {
  //   row: newToDoTr,
  //   descCell: newtaskDescptionTd,
  //   dateCell: newtargetDateTd,
  //   timeCell: newtargetTimeTd,
  // };

  // });


  //   //appendchild which add to the existing table row using varname./document created for that element.appendchild
  //   newToDoTr.appendChild(newTodoIdTd);
  //   newToDoTr.appendChild(newtaskDescptionTd);
  //   newToDoTr.appendChild(newtargetDateTd);
  //   newToDoTr.appendChild(newtargetTimeTd);
  //   newToDoTr.appendChild(newstatusTd);
  //   newToDoTr.appendChild(newcurrentDateTd);
  //   newActionTd.appendChild(actionBtn);
  //   newToDoTr.appendChild(newActionTd);
  //   newToDoTr.appendChild(editBtn);


  //   // now we should appendchild means we should add the row to table means new row
  //   var table = document.getElementById('toDoTable');
  //   table.appendChild(newToDoTr);

  //   toDoId++;//increament toDoId
  //   console.log(taskDesc, targetDate, targetTime, status, currentDate);

}
//we use event listener to call the function when button is clicked
//it is used for the page reload prevention in this method

function submitTask(event) {
  event.preventDefault(); // stop form reload

  var taskDescription = document.getElementById('taskDesc').value;
  var rawtargetDate = document.getElementById('targetDate').value;
  var parts = rawtargetDate.split("-");
  var targetDate = parts[2] + "-" + parts[1] + "-" + parts[0];
  var targetTime = document.getElementById('targetTime').value;
  var now = new Date();
  var currentDate = now.toLocaleDateString() + " " + now.toLocaleTimeString();

  if (taskDescription === "" || targetDate === "" || targetTime === "") {
    alert("Please fill all the fields");
    return;
  }

  // 👇 Check if editing
  if (editingRow !== null) {
    //  Update the same row
    editingRow.descCell.innerText = taskDescription;
    editingRow.dateCell.innerText = targetDate;
    editingRow.timeCell.innerText = targetTime;


    editingRow = null;
    document.getElementById('submit').innerText = "Add Task";
  } else {
    //  Add new row
    var status = 'pending';
    toDoAdd(taskDescription, targetDate, targetTime, "pending", currentDate);
  }

  // Clear form
  document.getElementById('taskDesc').value = "";
  document.getElementById('targetDate').value = "";
  document.getElementById('targetTime').value = "";
  //document.getElementById('status').value = "";
}

//event which calls after loading all images and all
window.onload = function () {
  var today = new Date().toISOString().split("T")[0];//iso8601 format date to object and split into two parts from 0 date
  document.getElementById("targetDate").min = today;

};
searchInput.addEventListener("input",function(){
  var query=this.value.trim().toLowerCase();
  //filter function using value 
  var filteredList =toDoList.filter(function(value,index,array){
    return (
      value.taskDescription.toLowerCase().includes(query)||
      value.status.toLowerCase().includes(query)
    );
  });
//clear the existing row
var table=document.getElementById("toDoTable");
while(table.rows.length > 1){
  table.deleteRow(1);
}
// table.innerHTML="";
//rendering the filtered list
renderTodoItems(filteredList);
});
