CREATE TABLE board (
    board_id INT AUTO_INCREMENT PRIMARY KEY,
    food_type VARCHAR(255),
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    location VARCHAR(255),
    content TEXT NOT NULL
);