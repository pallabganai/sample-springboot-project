INSERT INTO users (username, password, enabled)
values ('user','pass',1);

INSERT INTO authorities (username, authority)
values ('user', 'ROLE_USER');

--INSERT INTO MYAPP_USER (id, user_name, password, active, roles)
--values (1, 'myappuser','myapppass', true, 'ROLE_USER,ROLE_ADMIN');