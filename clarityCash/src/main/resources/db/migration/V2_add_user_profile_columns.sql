-- Add profile columns to users table
ALTER TABLE users 
ADD COLUMN objectives TEXT,
ADD COLUMN salary DECIMAL(15,2),
ADD COLUMN finance_division_type VARCHAR(20),
ADD COLUMN bills_percentage INTEGER,
ADD COLUMN expenses_percentage INTEGER,
ADD COLUMN investments_percentage INTEGER;