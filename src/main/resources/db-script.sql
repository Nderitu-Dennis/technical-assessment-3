-- ============================================
-- 1. Departments
-- ============================================
CREATE TABLE departments (
    department_id INT PRIMARY KEY AUTO_INCREMENT,
    department_name VARCHAR(100) NOT NULL UNIQUE,
    is_active BIT NOT NULL DEFAULT 1
);

INSERT INTO departments (department_name) VALUES
('HR'),
('IT'),
('Finance'),
('Sales');

-- ============================================
-- 2. Employees
-- ============================================
CREATE TABLE employees (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_code VARCHAR(50) NOT NULL UNIQUE,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100),
    department_id INT NOT NULL,
    date_of_joining DATE,
    is_active BIT NOT NULL DEFAULT 1,
    CONSTRAINT fk_employees_department FOREIGN KEY (department_id) REFERENCES departments(department_id)
);

INSERT INTO employees (employee_code, first_name, last_name, department_id, date_of_joining) VALUES
('E001', 'Alice', 'Smith', 1, '2020-01-10'),
('E002', 'Bob', 'Johnson', 1, '2019-03-15'),
('E003', 'Charlie', 'Brown', 2, '2021-07-01'),
('E004', 'David', 'Williams', 2, '2022-02-20'),
('E005', 'Eva', 'Davis', 3, '2018-11-05'),
('E006', 'Frank', 'Miller', 4, '2020-06-30');

-- ============================================
-- 3. Leave Types
-- ============================================
CREATE TABLE leave_types (
    leave_type_id INT PRIMARY KEY AUTO_INCREMENT,
    leave_type_code VARCHAR(20) NOT NULL UNIQUE,
    leave_type_name VARCHAR(100) NOT NULL,
    is_paid_leave BIT NOT NULL DEFAULT 1,
    is_active BIT NOT NULL DEFAULT 1
);

INSERT INTO leave_types (leave_type_code, leave_type_name) VALUES
('CL', 'Casual Leave'),
('ML', 'Medical Leave'),
('EL', 'Earned Leave');

-- ============================================
-- 4. Employee Leave Quota
-- ============================================
CREATE TABLE employee_leave_quota (
    quota_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    leave_type_id INT NOT NULL,
    leave_year INT NOT NULL,
    total_allocated DECIMAL(5,2) NOT NULL,
    total_used DECIMAL(5,2) NOT NULL DEFAULT 0,
    CONSTRAINT fk_quota_employee FOREIGN KEY (employee_id) REFERENCES employees(employee_id),
    CONSTRAINT fk_quota_leave_type FOREIGN KEY (leave_type_id) REFERENCES leave_types(leave_type_id),
    CONSTRAINT uq_employee_leave_year UNIQUE (employee_id, leave_type_id, leave_year)
);

-- Example quotas for 2025
INSERT INTO employee_leave_quota (employee_id, leave_type_id, leave_year, total_allocated) VALUES
(1, 1, 2025, 10),  -- Alice, Casual Leave
(1, 2, 2025, 8),   -- Alice, Medical Leave
(1, 3, 2025, 15),  -- Alice, Earned Leave
(2, 1, 2025, 10),
(2, 2, 2025, 8),
(2, 3, 2025, 12),
(3, 1, 2025, 12),
(3, 2, 2025, 10),
(3, 3, 2025, 15),
(4, 1, 2025, 10),
(4, 2, 2025, 8),
(4, 3, 2025, 10),
(5, 1, 2025, 15),
(5, 2, 2025, 10),
(5, 3, 2025, 20),
(6, 1, 2025, 10),
(6, 2, 2025, 8),
(6, 3, 2025, 12);

-- ============================================
-- 5. Leave Applications (empty, filled via form)
-- ============================================
CREATE TABLE leave_applications (
    leave_application_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    leave_type_id INT NOT NULL,
    from_date DATE NOT NULL,
    to_date DATE NOT NULL,
    total_days DECIMAL(5,2) NOT NULL,
    reason VARCHAR(500) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'Pending',
    applied_on DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    approved_by INT,
    approved_on DATETIME,
    CONSTRAINT fk_leave_app_employee FOREIGN KEY (employee_id) REFERENCES employees(employee_id),
    CONSTRAINT fk_leave_app_leave_type FOREIGN KEY (leave_type_id) REFERENCES leave_types(leave_type_id)
);

-- ============================================
-- 6. Optional: Holidays
-- ============================================
CREATE TABLE holidays (
    holiday_id INT PRIMARY KEY AUTO_INCREMENT,
    holiday_date DATE NOT NULL UNIQUE,
    description VARCHAR(200) NOT NULL
);

INSERT INTO holidays (holiday_date, description) VALUES
('2025-01-01', 'New Year''s Day'),
('2025-12-25', 'Christmas Day');
