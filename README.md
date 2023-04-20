# DAILY BUGLE
Daily Bugle est une application de flux d'actualités


## Architecture :
Une clean architecture en data-domain-ui.
La clean architecture est un critère indispensable pour avoir une application décomposée en différentes couches à la responsabilité restreinte et indépendante des autres couches. Les classes sont ainsi moins complexes. Le code est donc maintenable, testable et évolutif

## Modularité :
J'utilise beaucoup de module dans l'application, ce qui rend le développement plus long mais en contre partie :
- Chaque module est plus facilement réutilisable.
- Améliore les temps de compilation. 
- Oblige à respecter la clean architecture (je connais les dépendances nécessaire à chaque modules).


## Gestion d'erreur :
J'ai ajouté un interceptor qui transforme l'erreur de l'api en erreur. Ça évite d'avoir dans chaque model de réponse le code dupliqué de l'erreur que nous transmet l'api. 

J'utilise les Result pour propager une réponse et/ou l'erreur survenu.  Ça évite d'avoir des try catch dans toute l'application et de gérer une réponse comme un flux avec un flux de succès et un autre d'échec. 


## Tests:
- J'ai réalisé quelques tests pour montrer que mon architecture permet de tester les différentes classes facilement (et donc que je respecte bien la clean architecture). 
- Sur un vrai projet, j'aurais testé en priorité la couche data, domain et les viewmodels.
- Je ne réalise généralement pas de test UI parce que c'est une partie qui est trop évolutif. Parcontre j'ai été amené à en réaliser pour généré le baseline profile pour augmenter les performances de l'application. Ça permet au playstore de savoir quel classe precompilé à l'installation de l'app. 


## Librairies :
### Compose
Comme le dit Google, c'est un toolkit qui permet de générer plus rapidement la UI : 
- plus performant parce que il n'y a plus l'interprétation du XML.
-  plus lisible et intuitif
-  Mieux intégré dans une clean architecture

### Koin
C'est une librairie d'injection de dépendance léger et moins intrusif dans le code. Même si hilt est pas mal dans son genre, koin a été l'un des premiers à être développé pour kotlin en utilisant les avantages de ce langage.

### Rétrofit 
Le plus connu et le plus personnalisable entre les adapter, la prise en charge de rx, des flow etc. 

### Coil
Le plus complet qui fonctionne avec compose


## Évolution possible :
- Le pull to refresh pour rafraîchir les données dans la home
- Une gestion hors ligne en sauvegardant la réponse de l'api news en base de donnée
- le mode darkJ'ai ajouté un interceptor qui transforme l'erreur de l'api en erreur. Ça évite d'avoir dans chaque model de réponse le code dupliqué de l'erreur que nous transmet l'api. 
