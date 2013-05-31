#!bin/sh
dbname="diploma"
user="${1}"
dropdb -h localhost -U ${user} ${dbname}
createdb -h localhost -U ${user} ${dbname}
psql -h localhost -q -U ${user} -d $dbname -f recreateTables.sql
echo -e "tables recreated."
