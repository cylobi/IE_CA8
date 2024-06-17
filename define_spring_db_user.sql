create database if not exists mizdooni_ca5;
create user if not exists 'springuser'@'%' identified by 'ThePassword';
grant all on mizdooni_ca5.* to 'springuser'@'%';
FLUSH Privileges;