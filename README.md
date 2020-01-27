# mobile-FE


Om de git de ontclusterfucken op jouw laptop zijn er twee opties:

optie 1:

open terminal in project folder (rechtsklik project open in terminal)
voer nu de volgende commando's op volgorde uit:

  1.  git checkout master
  2.  git fetch
  3.  git pull
  4.  git reset --hard
  5.  git rm -r --cached .
  6.  git add .
  7.  delete vervolgens alle lokale branches behalve de master.





optie 2:

Gooi je gehele lokale project weg (ook van je schijf) en download hem opnieuw van git.

-------------------
uitleg bij optie 1:

stap 1 t/m 3  update je master naar de huidige.

stap 4 t/m 5  zorgt ervoor dat alle lokaal toegevoegde files vergeten worden (bestanden bijven wel bestaan)

stap 6        voegt alle files toe rekening houdende met de .gitignore. (dus alle local, .gradle en .idea files niet)

stap 7        is om te voorkomen dat er per ongeluk nog "vervuilde" branches gemerged worden
