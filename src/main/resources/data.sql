create table TBLBBPARAM (
     id int generated by default as identity (start with 1) primary key,
     env varchar(32) not null,
     client_id varchar(2048) not null,
     client_secret varchar(2048) not null,
     developer_application_key varchar(255) not null,
     registration_acess_token varchar(2048) not null,
     access_token varchar(2048) null,
     access_token_date datetime null
);

INSERT INTO TBLBBPARAM(client_id, client_secret, developer_application_key, registration_acess_token, env)
VALUES (
           'client_id',
           'client_secret',
           'developer_application_key',
           '{Base64(client_id:client_secret)}',
           'DEVELOPMENT'
       );

INSERT INTO TBLBBPARAM(client_id, client_secret, developer_application_key, registration_acess_token, env)
VALUES (
           'client_id',
           'client_secret',
           'developer_application_key',
           '{Base64(client_id:client_secret)}',
           'PRODUCTION'
       );