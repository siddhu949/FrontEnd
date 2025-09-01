const searchInput = document.getElementById('search');
let editingId = null;

// Display live date and time
setInterval(() => {
    const now = new Date();
    document.getElementById("distime").innerText = now.toLocaleTimeString();
    document.getElementById("disdate").innerText = now.toLocaleDateString('en-US', {
        weekday: 'long', year: 'numeric', month: 'long', day: 'numeric'
    });
}, 1000);

// Load tasks from servlet
function loadTodos() {
    fetch('/todo')
        .then(res => res.json())
        .then(todos => {
            const table = document.getElementById('toDoTable');
            table.innerHTML = `
                <tr>
                    <th>ID</th>
                    <th>Task Description</th>
                    <th>Target Date</th>
                    <th>Target Time</th>
                    <th>Status</th>
                    <th>Created At</th>
                    <th>Actions</th>
                </tr>
            `;
            todos.forEach(todo => renderTodoRow(todo));
        })
        .catch(err => console.error(err));
}

// Render single row
function renderTodoRow(todo) {
    const table = document.getElementById('toDoTable');
    const tr = document.createElement('tr');
    tr.dataset.id = todo.todo_id;

    tr.innerHTML = `
        <td>${todo.todo_id}</td>
        <td>${todo.todoDescription}</td>
        <td>${todo.todoTargetDate}</td>
        <td>${todo.todoTargetTime}</td>
        <td>${todo.todoStatus}</td>
        <td>${todo.createdDate}</td>
        <td>
            <button class="statusBtn">${todo.todoStatus === 'completed' ? 'Done' : todo.todoStatus === 'pending' ? 'In Progress' : 'Completed'}</button>
            <button class="editBtn">Edit</button>
            <button class="deleteBtn">Delete</button>
        </td>
    `;
    table.appendChild(tr);

    const statusBtn = tr.querySelector('.statusBtn');
    statusBtn.addEventListener('click', () => changeStatus(todo.todo_id));

    const editBtn = tr.querySelector('.editBtn');
    editBtn.addEventListener('click', () => editTask(todo));

    const deleteBtn = tr.querySelector('.deleteBtn');
    deleteBtn.addEventListener('click', () => deleteTask(todo.todo_id));
}

// Add or Update task
function submitTask(event) {
    event.preventDefault();
    const taskDescription = document.getElementById('taskDesc').value;
    const targetDate = document.getElementById('targetDate').value;
    const targetTime = document.getElementById('targetTime').value;

    if (!taskDescription || !targetDate) {
        alert("Please fill all required fields");
        return;
    }

    const data = {
        todoDescription: taskDescription,
        todoTargetDate: targetDate,
        todoTargetTime: targetTime,
        todoStatus: 'pending'
    };

    const url = '/todo';
    const method = editingId ? 'PUT' : 'POST';
    if (editingId) data.todo_id = editingId;

    fetch(url, {
        method: method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
    .then(res => {
        if (res.ok) {
            loadTodos();
            editingId = null;
            document.getElementById('submit').innerText = "Submit Task";
            document.getElementById('taskDesc').value = '';
            document.getElementById('targetDate').value = '';
            document.getElementById('targetTime').value = '';
        } else {
            alert("Error submitting task");
        }
    })
    .catch(err => console.error(err));
}

// Edit task
function editTask(todo) {
    editingId = todo.todo_id;
    document.getElementById('taskDesc').value = todo.todoDescription;
    document.getElementById('targetDate').value = todo.todoTargetDate;
    document.getElementById('targetTime').value = todo.todoTargetTime;
    document.getElementById('submit').innerText = "Update Task";
}

// Delete task
function deleteTask(id) {
    if (!confirm("Are you sure you want to delete this task?")) return;

    fetch('/todo', {
        method: 'DELETE',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ todo_id: id })
    })
    .then(res => {
        if (res.ok) loadTodos();
        else alert("Error deleting task");
    });
}

// Change status
function changeStatus(id) {
    fetch(`/todo/status`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ todo_id: id })
    })
    .then(res => {
        if (res.ok) loadTodos();
        else alert("Error updating status");
    });
}

// Search/filter tasks
searchInput.addEventListener('input', () => {
    const query = searchInput.value.trim().toLowerCase();
    const table = document.getElementById('toDoTable');
    Array.from(table.rows).forEach((row, idx) => {
        if (idx === 0) return; // skip header
        const text = row.innerText.toLowerCase();
        row.style.display = text.includes(query) ? '' : 'none';
    });
});

// On page load
window.onload = () => {
    const today = new Date().toISOString().split("T")[0];
    document.getElementById("targetDate").min = today;
    loadTodos();
};
