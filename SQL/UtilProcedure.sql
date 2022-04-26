
select * from post

--스토리 신고
CREATE OR REPLACE PROCEDURE addStoryReport(
    p_reporter IN report.reporter_id%TYPE, 
    p_reportType IN report.report_type%TYPE,
    p_reason IN report.reason%TYPE,
    p_story_num IN report.story_num%TYPE,
    p_status out number
)
IS
    v_reported varchar2(30) := '';
     v_status number(5) := 1;
BEGIN
    select userid into v_reported from story where story_num = p_story_num;

    insert into report (report_num, reporter_id, reported_id, story_num, reason, report_type)
    values (report_seq.nextVal, p_reporter, v_reported, p_story_num, p_reason, p_reportType);
    commit;
    p_status := v_status;
exception when others then
    v_status := 0;
    p_status := v_status;
    rollback;    
END;

select * from report

--포스트신고
CREATE OR REPLACE PROCEDURE addPostReport(
    p_reporter IN report.reporter_id%TYPE, 
    p_reportType IN report.report_type%TYPE,
    p_reason IN report.reason%TYPE,
    p_post_num IN report.post_num%TYPE,
    p_status out number
)
IS
    v_reported varchar2(30) := '';
    v_status number(5) := 1;
BEGIN
    select userid into v_reported from post where post_num = p_post_num;

    insert into report (report_num, reporter_id, reported_id, post_num, reason, report_type)
    values (report_seq.nextVal, p_reporter, v_reported, p_post_num, p_reason, p_reportType);
    
    commit;
    p_status := v_status;
exception when others then
    v_status := 0;
    p_status := v_status;
    rollback;    
END;

--유저 신고
CREATE OR REPLACE PROCEDURE addUserReport(
    p_reporter IN report.reporter_id%TYPE, 
    p_reported IN report.reported_id%TYPE,
    p_reportType IN report.report_type%TYPE,
    p_reason IN report.reason%TYPE,
    p_status out number
)
IS
    v_status number(5) := 1;
BEGIN
    insert into report (report_num, reporter_id, reported_id, report_type, reason)
    values (report_seq.nextVal, p_reporter, p_reported, p_reportType, p_reason);
    
    commit;
    p_status := v_status;
exception when others then
    v_status := 0;
    p_status := v_status;
    rollback;    
END;


