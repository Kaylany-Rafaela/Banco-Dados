: : Gera uma pasta chamada "backup", pode ser restaurada pelo pgAdmin como DIRECTORY.
: : Automatize com o Task Scheduler do Windows.
set PGPASSWORD=postgres
pg_dump -Fd --username=postgres --dbname=BancoTrabalho2 -f backup