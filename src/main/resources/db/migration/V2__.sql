INSERT INTO instrument (id,instrument_name,voice) VALUES (5,'Clarinete','1');
INSERT INTO instrument (id,instrument_name,voice) VALUES (6,'Clarinete','2');
INSERT INTO instrument (id,instrument_name,voice) VALUES (1,'Director','Principal');
INSERT INTO instrument (id,instrument_name,voice) VALUES (17,'Fagot','1');
INSERT INTO instrument (id,instrument_name,voice) VALUES (7,'Flauta','1');
INSERT INTO instrument (id,instrument_name,voice) VALUES (8,'Flauta','Principal');
INSERT INTO instrument (id,instrument_name,voice) VALUES (14,'Oboe','1');
INSERT INTO instrument (id,instrument_name,voice) VALUES (11,'Saxofón','1');
INSERT INTO instrument (id,instrument_name,voice) VALUES (12,'Saxofón','2');
INSERT INTO instrument (id,instrument_name,voice) VALUES (9,'Trombón','1');
INSERT INTO instrument (id,instrument_name,voice) VALUES (10,'Trombón','2');
INSERT INTO instrument (id,instrument_name,voice) VALUES (15,'Trompa','1');
INSERT INTO instrument (id,instrument_name,voice) VALUES (16,'Trompa','2');
INSERT INTO instrument (id,instrument_name,voice) VALUES (2,'Trompeta','1');
INSERT INTO instrument (id,instrument_name,voice) VALUES (3,'Trompeta','2');
INSERT INTO instrument (id,instrument_name,voice) VALUES (4,'Trompeta','3');
INSERT INTO instrument (id,instrument_name,voice) VALUES (13,'Tuba','1');


INSERT INTO role (id,description,name) VALUES (1,'Administrador del sistema','ADMIN');
INSERT INTO role (id,description,name) VALUES (2,'Usuario músico','MUSICIAN');


INSERT INTO user (active,band_join_date,birth_date,system_signup_date,id,last_login_date,email,first_name,last_name,notes,password_hash,phone,profile_picture_url,second_last_name) VALUES (1,'2010-09-12','1980-01-01','2025-09-12',1,NULL,'admin@bandas.com','Admin','System','Usuario administrador inicial','$2a$10$KZnvbeA3LHZ692wLFGhD.uyw/AiNlMUeotU5IItWT9ft1jkA9bRsW','600000000',NULL,'User');
INSERT INTO user (active,band_join_date,birth_date,system_signup_date,id,last_login_date,email,first_name,last_name,notes,password_hash,phone,profile_picture_url,second_last_name) VALUES (1,'2023-08-04','2000-04-07','2025-09-04',2,NULL,'ismael.gomez.4@bandas.com','Ismael','Gómez','Usuario de prueba generado automáticamente','$2a$10$WefFSGcYwDqBpHX5JD1l9eNui6SM2GJWZW2K1Si5uGZWbbiodjTuW','74505089',NULL,'López');
INSERT INTO user (active,band_join_date,birth_date,system_signup_date,id,last_login_date,email,first_name,last_name,notes,password_hash,phone,profile_picture_url,second_last_name) VALUES (1,'2021-07-31','1986-08-20','2025-08-09',3,NULL,'elena.blazquez.3@bandas.com','Elena','Blázquez','Usuario de prueba generado automáticamente','$2a$10$vbfRdjpxZHzVFJNSBCUDN.y15IjW8gPe2.A5XF.HItwSA22PKjZ5e','74257413',NULL,'Romero');
INSERT INTO user (active,band_join_date,birth_date,system_signup_date,id,last_login_date,email,first_name,last_name,notes,password_hash,phone,profile_picture_url,second_last_name) VALUES (1,'2016-08-25','2007-12-20','2025-07-27',4,NULL,'marta.sanchez.19@bandas.com','Marta','Sánchez','Usuario de prueba generado automáticamente','$2a$10$E/eN8Q7gZaHymCWxiusDleQMeR4tU9bA3Xd/gICa6fHEeb530eMV6','84440265',NULL,'Alonso');
INSERT INTO user (active,band_join_date,birth_date,system_signup_date,id,last_login_date,email,first_name,last_name,notes,password_hash,phone,profile_picture_url,second_last_name) VALUES (1,'2024-08-21','1988-04-21','2025-09-03',5,NULL,'sofia.gomez.6@bandas.com','Sofía','Gómez','Usuario de prueba generado automáticamente','$2a$10$f6B0h.tc52t3UjWpyH9G1eDt.NJemvwD7H/kjPo4Cf4eV.nlJT3U.','00228576',NULL,'Alonso');
INSERT INTO user (active,band_join_date,birth_date,system_signup_date,id,last_login_date,email,first_name,last_name,notes,password_hash,phone,profile_picture_url,second_last_name) VALUES (1,'2016-09-03','1998-11-15','2025-07-30',6,NULL,'alejandro.martinez.21@bandas.com','Alejandro','Martínez','Usuario de prueba generado automáticamente','$2a$10$dLxvWXPhgj/weD3dvqVA/.PlIbFdPX.NczZP015C/BGO3CIvuEAP2','95786795',NULL,'Hernández');
INSERT INTO user (active,band_join_date,birth_date,system_signup_date,id,last_login_date,email,first_name,last_name,notes,password_hash,phone,profile_picture_url,second_last_name) VALUES (1,'2022-08-31','1985-01-14','2025-09-06',7,NULL,'ismael.gomez.11@bandas.com','Ismael','Gómez','Usuario de prueba generado automáticamente','$2a$10$Vnek7/S/TFwz9IYZp54R3O75sVhvEwQ2xAsWf6.fC3kyRvqSHUVp.','29602570',NULL,'Pérez');
INSERT INTO user (active,band_join_date,birth_date,system_signup_date,id,last_login_date,email,first_name,last_name,notes,password_hash,phone,profile_picture_url,second_last_name) VALUES (1,'2016-08-16','2002-10-11','2025-09-04',8,NULL,'sara.martinez.8@bandas.com','Sara','Martínez','Usuario de prueba generado automáticamente','$2a$10$byjD9rhpHgGlRozz1nRF5elnG8b1w2ATrrLH3itgc9.VUnnBjwkri','66204409',NULL,'Martín');
INSERT INTO user (active,band_join_date,birth_date,system_signup_date,id,last_login_date,email,first_name,last_name,notes,password_hash,phone,profile_picture_url,second_last_name) VALUES (1,'2023-08-08','1989-10-22','2025-08-25',9,NULL,'miguel.martinez.22@bandas.com','Miguel','Martínez','Usuario de prueba generado automáticamente','$2a$10$hDjSA33Cd60QWMh9DIE0Q.K6ej3Py21zF.pwyaPWqKcMf6Sdx5yd.','56672364',NULL,'Díaz');
INSERT INTO user (active,band_join_date,birth_date,system_signup_date,id,last_login_date,email,first_name,last_name,notes,password_hash,phone,profile_picture_url,second_last_name) VALUES (1,'2024-07-31','2003-12-05','2025-08-12',10,NULL,'david.martinez.19@bandas.com','David','Martínez','Usuario de prueba generado automáticamente','$2a$10$loV2v2SN35e5dU.zBRCM8.a2PqsOBrr8O5ro/PGiUVUBVJXuzH6Cm','60111558',NULL,'Blázquez');
INSERT INTO user (active,band_join_date,birth_date,system_signup_date,id,last_login_date,email,first_name,last_name,notes,password_hash,phone,profile_picture_url,second_last_name) VALUES (1,'2018-09-12','1993-04-25','2025-09-10',11,NULL,'david.gomez.20@bandas.com','David','Gómez','Usuario de prueba generado automáticamente','$2a$10$ckYA3vnv.KjKwNE4OI75VexowsP.W0cNiax2oAS6m.zWNmHhQ4q6S','66186031',NULL,'Sánchez');
INSERT INTO user (active,band_join_date,birth_date,system_signup_date,id,last_login_date,email,first_name,last_name,notes,password_hash,phone,profile_picture_url,second_last_name) VALUES (1,'2016-09-07','1984-07-20','2025-08-06',12,NULL,'alejandro.garcia.0@bandas.com','Alejandro','García','Usuario de prueba generado automáticamente','$2a$10$5GB1dfJhSNjvcCsVN/R2B.AbcOeQrZm6eAtosyfjmbgr2HPKbGQqS','68894440',NULL,'Hernández');
INSERT INTO user (active,band_join_date,birth_date,system_signup_date,id,last_login_date,email,first_name,last_name,notes,password_hash,phone,profile_picture_url,second_last_name) VALUES (1,'2023-08-12','1996-05-25','2025-08-17',13,NULL,'carmen.garcia.1@bandas.com','Carmen','García','Usuario de prueba generado automáticamente','$2a$10$XBGNPXWqx4VdOn6uKYw64./VEUMv.7gelTorLy5vXn7UBqHLXgTtS','47045226',NULL,'González');
INSERT INTO user (active,band_join_date,birth_date,system_signup_date,id,last_login_date,email,first_name,last_name,notes,password_hash,phone,profile_picture_url,second_last_name) VALUES (1,'2019-08-16','1981-05-08','2025-09-05',14,NULL,'laura.rodriguez.10@bandas.com','Laura','Rodríguez','Usuario de prueba generado automáticamente','$2a$10$cY.zXerjwZtGh4.m9Ue5K.t6CLYdNL3.GTjBuJOqvGGfRVlAJ6If.','62007265',NULL,'Ruiz');
INSERT INTO user (active,band_join_date,birth_date,system_signup_date,id,last_login_date,email,first_name,last_name,notes,password_hash,phone,profile_picture_url,second_last_name) VALUES (1,'2019-09-09','2004-05-20','2025-08-20',15,NULL,'paula.gonzalez.1@bandas.com','Paula','González','Usuario de prueba generado automáticamente','$2a$10$Tvqc.j90XNgRF.4QYHAgUOIPxeCZ8tFDOiy3Jli5IPCwd9IFyvPxe','69514754',NULL,'Pérez');
INSERT INTO user (active,band_join_date,birth_date,system_signup_date,id,last_login_date,email,first_name,last_name,notes,password_hash,phone,profile_picture_url,second_last_name) VALUES (1,'2025-08-28','2006-09-15','2025-08-01',16,NULL,'ismael.romero.22@bandas.com','Ismael','Romero','Usuario de prueba generado automáticamente','$2a$10$AWX9Wjek.PqO2hXXSPdZxezRLuPpxBOySj9mj2vE6eGoM4AjLi4Ii','56830656',NULL,'Martínez');
INSERT INTO user (active,band_join_date,birth_date,system_signup_date,id,last_login_date,email,first_name,last_name,notes,password_hash,phone,profile_picture_url,second_last_name) VALUES (1,'2024-08-24','1984-07-15','2025-08-03',17,NULL,'maria.gutierrez.19@bandas.com','María','Gutiérrez','Usuario de prueba generado automáticamente','$2a$10$KuZzNGyFuAQJSORzqcoKmusT.5TW0PHWn8uaXDQNb0LNob/8o5sX.','55844135',NULL,'Álvarez');
INSERT INTO user (active,band_join_date,birth_date,system_signup_date,id,last_login_date,email,first_name,last_name,notes,password_hash,phone,profile_picture_url,second_last_name) VALUES (1,'2022-07-25','1990-08-10','2025-07-27',18,NULL,'maria.gomez.10@bandas.com','María','Gómez','Usuario de prueba generado automáticamente','$2a$10$caqOKDmMidFbReyvhwsu2uIZvdTeXHpbllDiOiCJOg8e25.URr1UC','12696991',NULL,'Ruiz');
INSERT INTO user (active,band_join_date,birth_date,system_signup_date,id,last_login_date,email,first_name,last_name,notes,password_hash,phone,profile_picture_url,second_last_name) VALUES (1,'2021-09-02','1989-12-01','2025-08-31',19,NULL,'hugo.perez.23@bandas.com','Hugo','Pérez','Usuario de prueba generado automáticamente','$2a$10$ySmtz4Vbg5QxGPb.teQsXeMkHft6hzzFr3ZmsJc9fgXxscVMV1PLm','04876262',NULL,'Martínez');
INSERT INTO user (active,band_join_date,birth_date,system_signup_date,id,last_login_date,email,first_name,last_name,notes,password_hash,phone,profile_picture_url,second_last_name) VALUES (1,'2023-07-27','1982-06-26','2025-08-02',20,NULL,'david.munoz.7@bandas.com','David','Muñoz','Usuario de prueba generado automáticamente','$2a$10$cDugdkdimb6mDLymMUEPhOmCFrvkaBIB94DEITTxXxBw/fi64lhhi','20525860',NULL,'Díaz');



INSERT INTO user_roles (roles_id,user_id) VALUES (1,1);
INSERT INTO user_roles (roles_id,user_id) VALUES (2,1);
INSERT INTO user_roles (roles_id,user_id) VALUES (2,2);
INSERT INTO user_roles (roles_id,user_id) VALUES (2,3);
INSERT INTO user_roles (roles_id,user_id) VALUES (2,4);
INSERT INTO user_roles (roles_id,user_id) VALUES (2,5);
INSERT INTO user_roles (roles_id,user_id) VALUES (1,6);
INSERT INTO user_roles (roles_id,user_id) VALUES (2,6);
INSERT INTO user_roles (roles_id,user_id) VALUES (2,7);
INSERT INTO user_roles (roles_id,user_id) VALUES (2,8);
INSERT INTO user_roles (roles_id,user_id) VALUES (2,9);
INSERT INTO user_roles (roles_id,user_id) VALUES (2,10);
INSERT INTO user_roles (roles_id,user_id) VALUES (1,11);
INSERT INTO user_roles (roles_id,user_id) VALUES (2,11);
INSERT INTO user_roles (roles_id,user_id) VALUES (2,12);
INSERT INTO user_roles (roles_id,user_id) VALUES (2,13);
INSERT INTO user_roles (roles_id,user_id) VALUES (2,14);
INSERT INTO user_roles (roles_id,user_id) VALUES (2,15);
INSERT INTO user_roles (roles_id,user_id) VALUES (1,16);
INSERT INTO user_roles (roles_id,user_id) VALUES (2,16);
INSERT INTO user_roles (roles_id,user_id) VALUES (2,17);
INSERT INTO user_roles (roles_id,user_id) VALUES (2,18);
INSERT INTO user_roles (roles_id,user_id) VALUES (2,19);
INSERT INTO user_roles (roles_id,user_id) VALUES (2,20);



INSERT INTO user_instruments (instruments_id,users_id) VALUES (1,1);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (15,2);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (12,3);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (14,3);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (2,4);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (5,4);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (14,4);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (8,5);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (7,6);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (11,6);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (7,7);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (14,7);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (8,8);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (9,9);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (11,9);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (12,9);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (8,10);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (16,10);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (17,10);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (6,11);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (9,11);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (3,12);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (9,12);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (12,12);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (9,13);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (12,13);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (14,13);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (10,14);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (5,15);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (14,15);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (7,16);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (6,17);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (11,17);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (2,18);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (11,19);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (13,19);
INSERT INTO user_instruments (instruments_id,users_id) VALUES (10,20);

