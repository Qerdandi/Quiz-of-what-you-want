# Quiz-of-what-you
Ce jeu est un quiz personnalisable développé en "speed run" sur 48 heures complètes. Il vous permet de créer un ou plusieurs quiz dans un même jeu et de pouvoir mélanger tous les thèmes que vous souhaitez.

# Informations :
Le jeu a été codé en Javafx sur Intellij en suivant le principe du pattern MVP (modèle présentation vue). Le code source est disponible dans ce repositorie et il est obligatoire de télécharger tout le dossier pour pouvoir l'ouvrir sur Intellij ou tout autre logiciel d'édition de code.
Le second dossier "build" contient uniquement le jeu compilé en fichier.jar ainsi que les fichiers textes nécessaires au bon fonctionnement du jeu : c'est sa base de donnée. Si vous souhaitez uniquement jouer ou créer des quiz, vous pouvez n'installer que ce dossier. 

# Prise en main :
1) Afin de pouvoir personnaliser votre quiz vous devez tout d'abord créer un fichier texte avec le nom de votre thème (c'est plus simple pour la suite). Dans ce fichier, vous allez inscrire chaque réponse les unes en dessous des autres.
Remarque : veillez à ne pas laisser d'espace au bout de la réponse, cela fausserait les réponses lors de la pratique du quiz.

2) Installer ensuite les images qui font office de question. c'est images doivent impérativement detenir le même nom que la réponse associée dans le fichier texte créé précédemment. Ces images doivent avoir le format .png ou .jpg pour être prises en charge par le jeu.
Remarque : Je vous conseille de mettre chaque groupe d'image associé à un thème dans un dossier propre à ce dernier pour des questions de lisibilité. 

3) Il ne reste plus qu'une seule étape. Ouvrir le fichier texte préinstallé nommé "txtFileAndPath.txt". Dans ce fichier, vous allez inscrire sur chaque ligne le fichier texte du thème contenant la réponse et sur cette même ligne séparée par un point-virgule ";", le chemin menant aux images correspondantes au thème. Le point de départ de ce chemin est là où se trouve le fichier "txtFileAndPath.txt" lui-même.
Remarque : il ne faudra en aucun cas modifier le nom de ce fichier

# Exemple :
1) Je crée le thème : "anime.txt" qui contient à chaque ligne ma réponse :
steins;gate
code geass

2) Je crée un dossier dans le répertoire où se trouve mon jeu que j'appelle "Anime" (par exemple) qui contient les questions (images) :
steins;gate.png
code geass.jpg

3) J'ouvre le fichier "txtFileAndPath.txt" et je complète une ligne par thème (dans mon cas, je n'ai qu'un seul thème) :
anime.txt;Anime/

Et maintenant, il suffit d'ouvrir le jeu en .jar pour ainsi profiter du quiz que l'on vient de créer...
