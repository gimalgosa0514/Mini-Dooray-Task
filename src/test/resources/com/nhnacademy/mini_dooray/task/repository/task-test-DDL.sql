--- 프로젝트 정보 삽입
INSERT INTO project(project_id,project_name,project_manager_id,project_status)
    VALUES (1,"프로젝트 1","test2","ACTIVE"),
           (2,"2번 프로젝트","test1","TERMINATED"),
           (3,"test project3","test2","SUSPENDED");

INSERT INTO milestone(milestone_id,milestone_name,project_id,milestone_startline,milestone_deadline)
    VALUES (1,"1주차 목표",1,NOW(),NOW()),
           (2,"2주차 목표",1,NOW(),NOW()),
           (3,"3주차 목표",1,NOW(),NOW()),
           (4,"최종",3,NOW(),NOW());

INSERT INTO task(task_id,task_title,task_content,member_id,project_id,milestone_id)
    VALUES (1,"제목","내용","test2",1,NULL),
           (2,"2번 업무","업무 내용2","test2",1,2),
           (3,"3333","내용3","test1",2,null);