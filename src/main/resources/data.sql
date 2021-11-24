/* Recording users */
insert into workers(name, last_name, dni, birthday, active) VALUES ('Paloma', 'Bajo', '77865432H', '1966-2-14', true);
insert into workers(name, last_name, dni, birthday, active) VALUES ('Marco', 'Ibiza', '76895543J', '1995-7-14', true);
insert into workers(name, last_name, dni, birthday, active) VALUES ('Paco', 'Pil', '78673321G', '1992-03-22', false);
insert into workers(name, last_name, dni, birthday, active) VALUES ('Montse', 'Cortizas', '67543210P', '1977-11-11', true);
insert into workers(name, last_name, dni, birthday, active) VALUES ('Juan', 'Martín', '47656689H', '1975-12-29', true);
/* Recording tasks */
insert into it_tasks(title, description, finished, delivery_date, user_id) VALUES ('Backend BBVA', 'Terminar Backend BBVA', true, '2021-08-13',1);
insert into it_tasks(title, description, finished, delivery_date, user_id) VALUES ('Test BBVA', 'Testear BBVA', false, '2021-08-14',2);
insert into it_tasks(title, description, finished, delivery_date, user_id) VALUES ('Frontend Unicaja', 'Terminar Backend Unicaja', false, '2021-08-15',3);
insert into it_tasks(title, description, finished, delivery_date, user_id) VALUES ('Test Frontend Unicaja', 'Testear Backend Unicaja', true, '2021-08-16',4);
insert into it_tasks(title, description, finished, delivery_date, user_id) VALUES ('Error crítico ING', 'Solucionar error crítico ING', false, '2021-08-17',5);
insert into it_tasks(title, description, finished, delivery_date, user_id) VALUES ('Despliegue ING', 'Despliegue actualización ING', false, '2021-08-18',5);
/* Recording tags */
insert into tags(name, color) VALUES ('Backend', 'GREEN');
insert into tags(name, color) VALUES ('Backend', 'ORANGE');
insert into tags(name, color) VALUES ('Backend', 'RED');
insert into tags(name, color) VALUES ('Backend', 'BLACK');
insert into tags(name, color) VALUES ('Frontend', 'GREEN');
insert into tags(name, color) VALUES ('Frontend', 'ORANGE');
insert into tags(name, color) VALUES ('Frontend', 'RED');
insert into tags(name, color) VALUES ('Frontend', 'BLACK');
/* Recording billing_info */
insert into billing_info(total, user_id) VALUES (55500, 1);
insert into billing_info(total, user_id) VALUES (17000, 2);
insert into billing_info(total, user_id) VALUES (23400, 3);
insert into billing_info(total, user_id) VALUES (22400, 4);
insert into billing_info(total, user_id) VALUES (32500, 5);
/* Recording tasks_tags */
insert into tasks_tags(id_task, id_tag) VALUES (1, 1);
insert into tasks_tags(id_task, id_tag) VALUES (2, 2);
insert into tasks_tags(id_task, id_tag) VALUES (2, 6);
insert into tasks_tags(id_task, id_tag) VALUES (3, 5);
insert into tasks_tags(id_task, id_tag) VALUES (4, 6);
insert into tasks_tags(id_task, id_tag) VALUES (5, 8);
insert into tasks_tags(id_task, id_tag) VALUES (6, 8);
insert into tasks_tags(id_task, id_tag) VALUES (6, 4);