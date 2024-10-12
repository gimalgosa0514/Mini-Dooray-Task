-- member 생성
insert into member (member_id, member_password, member_email)
values ('aaa', 'aaa123', 'aaa@nhnacademy.com'),
       ('bbb', 'bbb123', 'bbb@nhnacademy.com'),
       ('ccc', 'ccc123', 'ccc@nhnacademy.com'),
       ('ddd', 'ddd123', 'ddd@nhnacademy.com'),
       ('eee', 'eee123', 'eee@nhnacademy.com');


-- project 생성
insert into project (project_name, project_status, project_manager_id)
values ('project1', 'ACTIVE', 'aaa'),
       ('project2', 'ACTIVE', 'aaa'),
       ('project3', 'ACTIVE', 'bbb'),
       ('project4', 'ACTIVE', 'ccc'),
       ('project5', 'ACTIVE', 'ddd');


-- projectMember 생성
insert into project_member (member_id, project_id)
values ('aaa', 1),
       ('aaa', 2),
       ('bbb', 3),
       ('ccc', 4),
       ('ddd', 5),
       ('bbb', 1),
       ('ddd', 1),
       ('eee', 2),
       ('aaa', 3),
       ('ccc', 3),
       ('ddd', 3),
       ('eee', 3),
       ('bbb', 4);


