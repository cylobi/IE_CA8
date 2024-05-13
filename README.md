## Creating Database and user

```sql
mysql> create database mizdooni_ca5; -- Creates the new database
mysql> create user 'springuser'@'%' identified by 'ThePassword'; -- Creates the user
mysql> grant all on mizdooni_ca5.* to 'springuser'@'%'; -- Gives all privileges to the new user on the newly created database
```
