PROJET DOUANE TEMPORELLE - JAVA

1) Ouvrir Android Studio.
2) Choisir Open et sélectionner le dossier DouaneTemporelle décompressé.
3) Attendre Gradle Sync.
4) Ouvrir :
   app/src/main/java/com/example/douanetemporelle/StudentConfig.java
5) Remplacer :
   MATRICULE = "XXXXXXXX" par le matricule réel (au moins 4 chiffres).
   INITIALS = "AH" par les initiales de l'étudiant choisi.
6) IMPORTANT : remplacer aussi le suffixe _ah dans les IDs XML et les références Java
   par les mêmes initiales en minuscules si elles sont différentes de ah.
   Exemple : initiales MK => tv_titre_main_mk, edt_nom_mk, R.id.edt_nom_mk.
7) Lancer l'application sur un émulateur ou un téléphone Android.

TEST DU CRITERE NOMBRE PREMIER :
- Motif : Modification du passé
- Naissance : 1990
- Destination : 1997
=> différence = 7 (premier), le bouton Approuver doit être désactivé.

TEST NORMAL :
- Motif : Tourisme
- Naissance : 1990
- Destination : 2050
=> bouton Approuver reste activé.

NOTE :
Le projet utilise Android Gradle Plugin 8.7.3, compileSdk 35 et Java 17.
Si Android Studio demande d'installer Android SDK 35, accepter l'installation.
