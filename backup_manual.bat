: : Gera uma pasta chamada "backup_manual", pode ser restaurada pelo pgAdmin como DIRECTORY.
cd backup_util
set PGPASSWORD=postgres
pg_dump -Fd --username=postgres --dbname=BancoTrabalho2 -f backup_manual