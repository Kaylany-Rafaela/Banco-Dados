: : Esse batch file salva no local C:\Program Files\PostgreSQL\17\backups\BancoTrabalho2. Crie essas pastas primeiro.
: : Automatize com o Task Scheduler do Windows
set PGPASSWORD=postgres
pg_dump --username=postgres --dbname=BancoTrabalho2 --clean --file=..\backups\BancoTrabalho2\backup.sql