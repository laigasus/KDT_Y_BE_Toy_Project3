alter user 'root'@'localhost' identified with caching_sha2_password by 'root';
flush privileges;

create database if not exists travel;

use travel;