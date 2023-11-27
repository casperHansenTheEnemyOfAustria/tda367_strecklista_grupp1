
sudo psql -U postgres -d strecklista -c "DROP SCHEMA public CASCADE;"
sudo psql -U postgres -d strecklista -c "CREATE SCHEMA public;"
sudo psql -U postgres -d strecklista -c "\i ./src/main/resources/postgresql/tables.sql"
