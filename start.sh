docker exec -i demo-mysql-1 mysql -u root --password=password -e "create database platform" 
docker exec -i demo-mysql-1 mysql -u root --password=password platform < createTable.sql
