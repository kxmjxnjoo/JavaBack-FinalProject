-- num, title, img, category_type
insert into post_category (num, title, category_type)
	values (post_category_detail_seq.nextVal, '알고리즘', 0);
insert into post_category (num, title, category_type)
	values (post_category_detail_seq.nextVal, '데이터구조', 0);
insert into post_category (num, title, category_type)
	values (post_category_detail_seq.nextVal, '네트워크', 0);
insert into post_category (num, title, category_type)
	values (post_category_detail_seq.nextVal, 'DB', 0);
insert into post_category (num, title, category_type)
	values (post_category_detail_seq.nextVal, '컴퓨테이션이론', 0);
insert into post_category (num, title, category_type)
	values (post_category_detail_seq.nextVal, '운영체제', 0);


insert into post_category (num, title, category_type)
	values (post_category_detail_seq.nextVal, 'Front', 2);
insert into post_category (num, title, category_type)
	values (post_category_detail_seq.nextVal, 'Back', 2);
insert into post_category (num, title, category_type)
	values (post_category_detail_seq.nextVal, 'DevOps', 2);


-- post num, title, summary, content, main_category
insert into post (num, title, summary, content, main_category)
	values (post_seq.nextVal, 'TITLE 1', 'SUMMARY 1', 'CONTENT 1', 10);
insert into post (num, title, summary, content, main_category)
	values (post_seq.nextVal, 'TITLE 2', 'SUMMARY 2', 'CONTENT 2', 11);
insert into post (num, title, summary, content, main_category)
	values (post_seq.nextVal, 'TITLE 3', 'SUMMARY 3', 'CONTENT 3', 12);
insert into post (num, title, summary, content, main_category)
	values (post_seq.nextVal, 'TITLE 4', 'SUMMARY 4', 'CONTENT 4', 13);
insert into post (num, title, summary, content, main_category)
	values (post_seq.nextVal, 'TITLE 5', 'SUMMARY 5', 'CONTENT 5', 14);

insert into post (num, title, summary, content, main_category)
	values (post_seq.nextVal, 'TITLE 6', 'SUMMARY 6', 'CONTENT 6', 15);
insert into post (num, title, summary, content, main_category)
	values (post_seq.nextVal, 'TITLE 7', 'SUMMARY 7', 'CONTENT 7', 16);
insert into post (num, title, summary, content, main_category)
	values (post_seq.nextVal, 'TITLE 8', 'SUMMARY 8', 'CONTENT 8', 17);
insert into post (num, title, summary, content, main_category)
	values (post_seq.nextVal, 'TITLE 9', 'SUMMARY 9', 'CONTENT 9', 18);
insert into post (num, title, summary, content, main_category)
	values (post_seq.nextVal, 'TITLE 10', 'SUMMARY 11', 'CONTENT 10', 16);