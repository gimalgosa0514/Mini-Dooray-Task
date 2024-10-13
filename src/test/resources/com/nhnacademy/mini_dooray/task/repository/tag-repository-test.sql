-- project 생성
insert into project (project_name, project_status, project_manager_id)
values ('test1', 'ACTIVE', 'manager1'),
       ('test2', 'ACTIVE', 'manager2');


-- tag 생성
insert into tag (tag_name, project_id)
values ('태그1', 1),
       ('태그2', 1),
       ('태그3', 1),
       ('태그1', 2),
       ('태그2', 2);