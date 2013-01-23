C:
cd "C:\Program Files (x86)\pgAdmin III\1.16"
pg_dump.exe --clean --inserts -F p -h 141.13.6.76 -p 5433 -U "WIP-SFGmbH" "WIP-SFGmbH">"C:\Users\denis\workspace\SF_GmbH_P1\src\de\sfgmbh\init\reset.sql"