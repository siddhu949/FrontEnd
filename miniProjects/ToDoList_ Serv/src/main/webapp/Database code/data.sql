USE todoserv;

-- Lookup table
CREATE TABLE IF NOT EXISTS todo_status (
    status_code CHAR(1) PRIMARY KEY,        -- P / I / C
    status_name VARCHAR(50) NOT NULL,       -- Pending / In Progress / Completed
    status_desc VARCHAR(255)                -- Extra details
);

-- Insert default statuses
INSERT INTO todo_status (status_code, status_name, status_desc) VALUES
('P', 'Pending', 'Task is pending, system default'),
('I', 'In Progress', 'Task is currently in progress'),
('C', 'Completed', 'Task has been completed')
ON DUPLICATE KEY UPDATE status_name = VALUES(status_name);

-- Main todo table
CREATE TABLE IF NOT EXISTS todo_items (
    todo_id INT AUTO_INCREMENT PRIMARY KEY,
    todo_title VARCHAR(255) NOT NULL,
    todo_desc TEXT,
    target_datetime DATETIME NOT NULL,

    -- FK to lookup table
    todo_status_code CHAR(1) NOT NULL DEFAULT 'P',
    CONSTRAINT fk_status FOREIGN KEY (todo_status_code) 
        REFERENCES todo_status(status_code)
        ON UPDATE CASCADE 
        ON DELETE RESTRICT,

    -- Audit fields
    created_by VARCHAR(100),
    created_on DATETIME DEFAULT CURRENT_TIMESTAMP,
    modified_by VARCHAR(100),
    modified_on DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
select * from todo_items;

CREATE TABLE users (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100),
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

