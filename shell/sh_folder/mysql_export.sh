echo enter the DB name to export
read db_name
mysqldump -u root -p $db_name > ~/desktop/$db_name.sql