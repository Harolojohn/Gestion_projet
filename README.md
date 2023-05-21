# Gestion_projet

Pour demarrer le Backend (API)

1-Cloner le projet

git clone https://github.com/Harolojohn/Gestion_projet.git

2-se deplacer dans le repertoire de l'api

cd Gestion_projet/API_GP

3-builder l'image

docker build --tag api_gp .

4-Demarrer le conteneur

docker run -p 9050:9050 api_gp
