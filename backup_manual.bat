: : Gera uma pasta chamada "backup_manual", pode ser restaurada pelo pgAdmin como DIRECTORY.
: : Automatize com o Task Scheduler do Windows.
cd backup_util
set PGPASSWORD=postgres
pg_dump -Fd --username=postgres --dbname=BancoTrabalho2 -f backup_manual