## Creating Database and user

```sql
mysql> create database mizdooni_ca5; -- Creates the new database
mysql> create user 'springuser'@'%' identified by 'ThePassword'; -- Creates the user
mysql> grant all on mizdooni_ca5.* to 'springuser'@'%'; -- Gives all privileges to the new user on the newly created database
```

## Create Cert for https

```
keytool -genkey -alias <alias> -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650

```
