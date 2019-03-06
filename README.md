# teste-geofusion-jp



##Crie um build do projeto desafio seguindo as instruções 

##Crie um container com o Docker file dentro do projeto.
docker build -f DockerFile -t teste-geofusion-jp:v1 .

##Use o comando docker ps para listar seu id do container e salve os arquivos no root.

docker ps
CONTAINER ID        IMAGE                   COMMAND                  CREATED             STATUS              PORTS                    NAMES
06c6c9a5d208        teste-geofusion-jp:v7   "java -jar teste-geo…"   10 seconds ago      Up 8 seconds        0.0.0.0:8080->8080/tcp   lucid_archimedes

docker cp potencial.csv 06c6c9a5d208:/potencial.csv
docker cp concorrentes.csv 06c6c9a5d208:/concorrentes.csv
docker cp populacao.json 06c6c9a5d208:/populacao.json
docker cp bairros.csv 06c6c9a5d208:/bairros.csv
docker cp eventos_de_fluxo.csv 06c6c9a5d208:/eventos_de_fluxo.csv

##faça as request via postman e vizualise os retornos:

http://localhost:8080/api/teste/geofusion/densidadeDemografica
http://localhost:8080/api/teste/geofusion/getCadaDiaSemana
http://localhost:8080/api/teste/geofusion/getPeriodoDaNoite
http://localhost:8080/api/teste/geofusion/getPeriodoDaManha
http://localhost:8080/api/teste/geofusion/getPeriodoTotal

