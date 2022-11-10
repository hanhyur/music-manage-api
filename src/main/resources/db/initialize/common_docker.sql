-- 도커
docker run --restart=always -d -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=niceday1234!' -e 'TZ=Asia/Seoul' --name mssql-2017 -p 1433:1433 -d mcr.microsoft.com/azure-sql-edge