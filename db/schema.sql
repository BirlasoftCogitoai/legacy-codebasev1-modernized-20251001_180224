```sql
-- Legacy EGP Database Schema
-- Target: PostgreSQL 13.x

-- Drop tables if they exist (for clean reinstall)
DROP TABLE IF EXISTS egp_notes CASCADE;
DROP TABLE IF EXISTS egp_case_records CASCADE;
DROP TABLE IF EXISTS egp_customers CASCADE;
DROP TABLE IF EXISTS egp_users CASCADE;
DROP TABLE IF EXISTS egp_audit_log CASCADE;

-- Users table (modern authentication with bcrypt)
CREATE TABLE egp_users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(60) NOT NULL, -- bcrypt hash length
    email VARCHAR(100) NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    role VARCHAR(20) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Customers table
CREATE TABLE egp_customers (
    customer_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Case Records table
CREATE TABLE egp_case_records (
    case_id SERIAL PRIMARY KEY,
    customer_id INT NOT NULL REFERENCES egp_customers(customer_id) ON DELETE CASCADE,
    case_description TEXT NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Notes table
CREATE TABLE egp_notes (
    note_id SERIAL PRIMARY KEY,
    case_id INT NOT NULL REFERENCES egp_case_records(case_id) ON DELETE CASCADE,
    note_text TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Audit Log table
CREATE TABLE egp_audit_log (
    audit_id SERIAL PRIMARY KEY,
    action TEXT NOT NULL,
    performed_by VARCHAR(50) NOT NULL,
    performed_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
```