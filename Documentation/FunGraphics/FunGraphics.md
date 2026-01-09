# FunGraphics

Ce document résume les commandes essentielles de **FunGraphics** et met en lumière les **pièges fréquents** (performances et logique) rencontrés dans les fichiers `DottedFlag.scala`, `Hangman_ui.scala` et `Lab4_Part3.scala`.

## 1\. Initialisation Rapide

Pour commencer un projet graphique, il faut toujours importer la librairie et instancier la fenêtre.

```
import hevs.graphics.FunGraphics
import java.awt.Color // Indispensable pour gérer les couleurs

// 1. Définir les dimensions (Largeur, Hauteur)
val WIDTH = 800
val HEIGHT = 600

// 2. Créer la fenêtre principale
val display = new FunGraphics(WIDTH, HEIGHT, "Mon Titre")
```

## 2\. Les Primitives de Dessin (API)

Les méthodes les plus utilisées dans vos labos (`Hangman`, `DottedFlag`).

| Action | Commande | Description |
| --- | --- | --- |
| **Pixel** | `display.setPixel(x, y)` | Allume un seul pixel à la couleur active. |
| **Couleur** | `display.setColor(Color.red)` | Change le "crayon" pour **tout** ce qui suit. |
| **Ligne** | `display.drawLine(x1, y1, x2, y2)` | Trace une ligne (utilisé pour la potence du Pendu). |
| **Cercle (vide)** | `display.drawCircle(x,` y, `r)` | Trace le contour seulement. |
| **Cercle (plein)** | _Voir section "Le Piège"_ | FunGraphics n'a pas de `fillCircle` natif simple, il faut le coder. |
| **Texte** | `display.drawString(x, y, "Txt", ...)` | Affiche du texte (Attention: `x,y` est le coin bas-gauche du texte). |
| **Nettoyer** | `display.clear()` | Efface tout l'écran (Indispensable pour les jeux/animations). |

> **Rappel Coordonnées :** L'origine `(0,0)` est en **Haut-Gauche**.
> 
> - `x` augmente vers la Droite.
> - `y` augmente vers le **Bas**.

## 3\. ⚠️ LE GRAND PIÈGE : Performance & Algorithme

C'est le cœur du **Labo 4 (DottedFlag)**. Comment dessiner un disque plein efficacement ?

### L'approche Naïve (À ÉVITER)

C'est le code souvent fourni par défaut qui fait laguer l'ordinateur. **Pourquoi c'est mauvais ?** Pour dessiner un petit point de 5px, il teste les 307'200 pixels de l'écran (640\*480).

```
// ❌ MAUVAIS : Scanne tout l'écran pour un seul cercle
def drawDisc(r: Int, cx: Int, cy: Int): Unit = {
  for (x <- 0 until 640) {         // <--- Piège ici
    for (y <- 0 until 480) {       // <--- et ici
      if ((cx-x)*(cx-x) + (cy-y)*(cy-y) <= r*r) 
        display.setPixel(x,y)
    }
  }
}
```

### L'approche Optimisée ("Bounding Box")

On ne scanne que le carré qui entoure le cercle. **Gain :** Pour un rayon de 10px, on fait ~400 tests au lieu de 300'000. C'est **1000x plus rapide**.

```
// ✅ BON : Ne scanne que la zone nécessaire
def fastDrawDisc(r: Int, cx: Int, cy: Int, c: Color): Unit = {
  display.setColor(c)
  // On borne les boucles autour du centre (cx, cy) +/- rayon
  for (x <- (cx - r) to (cx + r)) {
    for (y <- (cy - r) to (cy + r)) {
      // Formule de Pythagore pour voir si on est dans le cercle
      if ((cx-x)*(cx-x) + (cy-y)*(cy-y) <= r*r) {
        display.setPixel(x, y) // Attention aux bords d'écran (voir ci-dessous)
      }
    }
  }
}
```

## 4\. Autres Pièges Fréquents (Troubleshooting)

### A. Crash `IndexOutOfBounds` (Le pixel hors écran)

Si vous dessinez un cercle au bord de l'écran avec `fastDrawDisc`, `x` peut devenir négatif (ex: `10` - `20 = -10`). `setPixel(-10, ...)` fera crasher le programme ou lancera une exception.

**La solution (Clipping) :** Ajouter une vérification avant de poser le pixel :

```
if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
    display.setPixel(x, y)
}
```

### B. Le "Stateful" Color Trap

`display.setColor` change une variable globale.

- **Scénario :** Vous dessinez un cercle rouge. Ensuite, vous voulez dessiner une ligne noire, mais vous oubliez de remettre `setColor(Color.black)`.
- **Résultat :** Votre ligne sera rouge.
- **Conseil :** Toujours grouper la couleur avec l'ordre de dessin.

### C. Le Piège des Maths (Trigonométrie)

Vu dans `Lab4_Part3.scala` (Polygones). La librairie `Math` de Java/Scala (cos, sin) utilise des **RADIANS**, pas des degrés.

```
// ❌ Faux si vous pensez en degrés
val h = r * Math.cos(360 / n) 

// ✅ Correct
val alpha = (2 * Math.PI) / n  // Conversion implicite ou calcul direct en PI
val h = r * Math.cos(alpha)
```

### D. Superposition dans les jeux (Hangman)

Dans `Hangman_ui.scala`, la fonction `updateGrahpicsViews()` est appelée dans une boucle `while`.

- **Problème :** Si vous n'utilisez pas `display.clear()` au début de la fonction, le nouveau texte s'écrit par-dessus l'ancien, créant un pâté noir illisible.
- **Ordre correct :**
    1.  `clear()`
    2.  Dessiner le décor (lignes fixes)
    3.  Dessiner les éléments dynamiques (bonhomme, lettres)
    4.  (FunGraphics gère le "double buffering" automatiquement, donc pas de clignot