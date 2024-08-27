-- Create the database
CREATE DATABASE exam_distribution_test;
go
USE exam_distribution_test;
go

-- exam_distribution_test.staff definition
CREATE TABLE staff (
    status TINYINT NULL,
    created_date BIGINT NULL,
    last_modified_date BIGINT NULL,
    id VARCHAR(36) NOT NULL,
    account_fe VARCHAR(255) NULL,
    account_fpt VARCHAR(255) NULL,
    name VARCHAR(255) NULL,
    staff_code VARCHAR(255) NULL,
    PRIMARY KEY (id)
);

-- exam_distribution_test.facility definition
CREATE TABLE facility (
    status TINYINT NULL,
    created_date BIGINT NULL,
    last_modified_date BIGINT NULL,
    id VARCHAR(36) NOT NULL,
    code VARCHAR(255) NULL,
    name VARCHAR(255) NULL,
    PRIMARY KEY (id)
);

-- exam_distribution_test.department definition
CREATE TABLE department (
    status TINYINT NULL,
    created_date BIGINT NULL,
    last_modified_date BIGINT NULL,
    id VARCHAR(36) NOT NULL,
    code VARCHAR(255) NULL,
    name VARCHAR(255) NULL,
    PRIMARY KEY (id)
);

-- exam_distribution_test.department_facility definition
CREATE TABLE department_facility (
    status TINYINT NULL,
    created_date BIGINT NULL,
    last_modified_date BIGINT NULL,
    id VARCHAR(36) NOT NULL,
    id_department VARCHAR(36) NULL,
    id_facility VARCHAR(36) NULL,
    id_staff VARCHAR(36) NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_department) REFERENCES department (id),
    FOREIGN KEY (id_facility) REFERENCES facility (id),
    FOREIGN KEY (id_staff) REFERENCES staff (id)
);

-- exam_distribution_test.major definition
CREATE TABLE major (
    status TINYINT NULL,
    created_date BIGINT NULL,
    last_modified_date BIGINT NULL,
    id VARCHAR(36) NOT NULL,
    name VARCHAR(255) NULL,
    code VARCHAR(255) NULL,
    PRIMARY KEY (id)
);

-- exam_distribution_test.major_facility definition
CREATE TABLE major_facility (
    status TINYINT NULL,
    created_date BIGINT NULL,
    last_modified_date BIGINT NULL,
    id VARCHAR(36) NOT NULL,
    id_department_facility VARCHAR(36) NULL,
    id_major VARCHAR(36) NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_department_facility) REFERENCES department_facility (id),
    FOREIGN KEY (id_major) REFERENCES major (id)
);

-- exam_distribution_test.staff_major_facility definition
CREATE TABLE staff_major_facility (
    status TINYINT NULL,
    created_date BIGINT NULL,
    last_modified_date BIGINT NULL,
    id VARCHAR(36) NOT NULL,
    id_major_facility VARCHAR(36) NULL,
    id_staff VARCHAR(36) NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_major_facility) REFERENCES major_facility (id),
    FOREIGN KEY (id_staff) REFERENCES staff (id)
);

-- Insert data into the staff table
INSERT INTO staff (id, status, created_date, last_modified_date, account_fe, account_fpt, name, staff_code)
VALUES
('550e8400-e29b-41d4-a716-446655440000', 1, 1627849200000, 1627935600000, 'fe_account1@fe.edu.vn', 'fpt_account1@fpt.edu.vn', 'John wick', 'ST001'),
('550e8400-e29b-41d4-a716-446655440001', 1, 1627849200000, 1627935600000, 'fe_account2@fe.edu.vn', 'fpt_account2@fpt.edu.vn', 'Top1 Flo', 'ST002'),
('550e8400-e29b-41d4-a716-446655440002', 1, 1627849200000, 1627935600000, 'fe_account3@fe.edu.vn', 'fpt_account3@fpt.edu.vn', 'Lục Luật', 'ST003');

-- Insert data into the facility table
INSERT INTO facility (id, status, created_date, last_modified_date, code, name)
VALUES
('550e8400-e29b-41d4-a716-446655440010', 1, 1627849200000, 1627935600000, 'FAC001', 'HN'),
('550e8400-e29b-41d4-a716-446655440011', 1, 1627849200000, 1627935600000, 'FAC002', 'HCM');

-- Insert data into the department table
INSERT INTO department (id, status, created_date, last_modified_date, code, name)
VALUES
('550e8400-e29b-41d4-a716-446655440020', 1, 1627849200000, 1627935600000, 'DEP001', 'Department One'),
('550e8400-e29b-41d4-a716-446655440021', 1, 1627849200000, 1627935600000, 'DEP002', 'Department Two'),
('550e8400-e29b-41d4-a716-446655440022', 1, 1627849200000, 1627935600000, 'DEP003', 'Department Three');

-- Insert data into the department_facility table
INSERT INTO department_facility (id, status, created_date, last_modified_date, id_department, id_facility, id_staff)
VALUES
('550e8400-e29b-41d4-a716-446655440030', 1, 1627849200000, 1627935600000, '550e8400-e29b-41d4-a716-446655440020', '550e8400-e29b-41d4-a716-446655440010', '550e8400-e29b-41d4-a716-446655440000'),
('550e8400-e29b-41d4-a716-446655440031', 1, 1627849200000, 1627935600000, '550e8400-e29b-41d4-a716-446655440021', '550e8400-e29b-41d4-a716-446655440011', '550e8400-e29b-41d4-a716-446655440001'),
('550e8400-e29b-41d4-a716-446655440032', 1, 1627849200000, 1627935600000, '550e8400-e29b-41d4-a716-446655440022', '550e8400-e29b-41d4-a716-446655440011', '550e8400-e29b-41d4-a716-446655440002');

-- Insert data into the major table
INSERT INTO major (id, status, created_date, last_modified_date, name, code)
VALUES
('550e8400-e29b-41d4-a716-446655440040', 1, 1627849200000, 1627935600000, 'Major One', 'MAJ001'),
('550e8400-e29b-41d4-a716-446655440041', 1, 1627849200000, 1627935600000, 'Major Two', 'MAJ002'),
('550e8400-e29b-41d4-a716-446655440042', 1, 1627849200000, 1627935600000, 'Major Three', 'MAJ003');

-- Insert data into the major_facility table
INSERT INTO major_facility (id, status, created_date, last_modified_date, id_department_facility, id_major)
VALUES
('550e8400-e29b-41d4-a716-446655440050', 1, 1627849200000, 1627935600000, '550e8400-e29b-41d4-a716-446655440030', '550e8400-e29b-41d4-a716-446655440040'),
('550e8400-e29b-41d4-a716-446655440051', 1, 1627849200000, 1627935600000, '550e8400-e29b-41d4-a716-446655440031', '550e8400-e29b-41d4-a716-446655440041'),
('550e8400-e29b-41d4-a716-446655440052', 1, 1627849200000, 1627935600000, '550e8400-e29b-41d4-a716-446655440032', '550e8400-e29b-41d4-a716-446655440042');

-- Insert data into the staff_major_facility table
INSERT INTO staff_major_facility (id, status, created_date, last_modified_date, id_major_facility, id_staff)
VALUES
('550e8400-e29b-41d4-a716-446655440060', 1, 1627849200000, 1627935600000, '550e8400-e29b-41d4-a716-446655440050', '550e8400-e29b-41d4-a716-446655440000'),
('550e8400-e29b-41d4-a716-446655440061', 1, 1627849200000, 1627935600000, '550e8400-e29b-41d4-a716-446655440051', '550e8400-e29b-41d4-a716-446655440001'),
('550e8400-e29b-41d4-a716-446655440062', 1, 1627849200000, 1627935600000, '550e8400-e29b-41d4-a716-446655440052', '550e8400-e29b-41d4-a716-446655440002');



select*from facility
select*from staff
select*from department
select*from major
select*from major_facility
select*from department_facility
select*from staff_major_facility

INSERT INTO staff (id, status, created_date, last_modified_date, account_fe, account_fpt, name, staff_code)
VALUES
('550e8400-e29b-41d4-a716-446655440003', 1, 1627849200000, 1627935600000, 'fe_account4@fe.edu.vn', 'fpt_account4@fpt.edu.vn', 'Alice Johnson', 'ST004'),
('550e8400-e29b-41d4-a716-446655440004', 0, 1627849200000, 1627935600000, 'fe_account5@fe.edu.vn', 'fpt_account5@fpt.edu.vn', 'Bob Smith', 'ST005'),
('550e8400-e29b-41d4-a716-446655440005', 1, 1627849200000, 1627935600000, 'fe_account6@fe.edu.vn', 'fpt_account6@fpt.edu.vn', 'Cathy Brown', 'ST006'),
('550e8400-e29b-41d4-a716-446655440006', 0, 1627849200000, 1627935600000, 'fe_account7@fe.edu.vn', 'fpt_account7@fpt.edu.vn', 'David Lee', 'ST007'),
('550e8400-e29b-41d4-a716-446655440007', 1, 1627849200000, 1627935600000, 'fe_account8@fe.edu.vn', 'fpt_account8@fpt.edu.vn', 'Eva Green', 'ST008'),
('550e8400-e29b-41d4-a716-446655440008', 1, 1627849200000, 1627935600000, 'fe_account9@fe.edu.vn', 'fpt_account9@fpt.edu.vn', 'Frank Miles', 'ST009'),
('550e8400-e29b-41d4-a716-446655440009', 0, 1627849200000, 1627935600000, 'fe_account10@fe.edu.vn', 'fpt_account10@fpt.edu.vn', 'Gina Ray', 'ST010'),
('550e8400-e29b-41d4-a716-446655440010', 1, 1627849200000, 1627935600000, 'fe_account11@fe.edu.vn', 'fpt_account11@fpt.edu.vn', 'Harry Kane', 'ST011'),
('550e8400-e29b-41d4-a716-446655440011', 0, 1627849200000, 1627935600000, 'fe_account12@fe.edu.vn', 'fpt_account12@fpt.edu.vn', 'Ivy Queen', 'ST012'),
('550e8400-e29b-41d4-a716-446655440012', 1, 1627849200000, 1627935600000, 'fe_account13@fe.edu.vn', 'fpt_account13@fpt.edu.vn', 'Jack Frost', 'ST013');
