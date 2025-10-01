```sql
-- Legacy EGP Database Seed Data
-- Sample data for development and testing

-- Insert sample users (passwords are bcrypt hashed)
INSERT INTO egp_users (username, password_hash, email, first_name, last_name, role, active) VALUES
('admin', '$2a$12$D4G5f18o7aMMfwasBl0pOeF1cC4xkZxwZxZxZxZxZxZxZxZxZx', 'admin@egp.gov', 'System', 'Administrator', 'ADMIN', true),
('jsmith', '$2a$12$D4G5f18o7aMMfwasBl0pOeF1cC4xkZxwZxZxZxZxZxZxZxZxZxZxZx', 'john.smith@egp.gov', 'John', 'Smith', 'USER', true);

-- Insert sample customers
INSERT INTO egp_customers (first_name, last_name, email, phone) VALUES
('Jane', 'Doe', 'jane.doe@egp.gov', '123-456-7890'),
('Alice', 'Johnson', 'alice.johnson@egp.gov', '098-765-4321');

-- Insert sample case records
INSERT INTO egp_case_records (customer_id, case_description, status) VALUES
(1, 'Case for Jane Doe', 'OPEN'),
(2, 'Case for Alice Johnson', 'CLOSED');

-- Insert sample notes
INSERT INTO egp_notes (case_id, note_text) VALUES
(1, 'Initial note for Jane Doe case'),
(2, 'Initial note for Alice Johnson case');
```