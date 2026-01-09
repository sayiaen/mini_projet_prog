# Souris

Pour capturer les actions de la souris, on utilise deux types d'écouteurs : `MouseListener` (clics) et `MouseMotionListener` (mouvements).

### 1\. Détecter les clics (MouseListener)

C'est la méthode la plus courante pour interagir avec des boutons ou des menus.

Scala

```
import java.awt.event.{MouseAdapter, MouseEvent}

display.addMouseListener(new MouseAdapter() {
  override def mousePressed(e: MouseEvent): Unit = {
    // Récupérer la position du clic
    val x = e.getX
    val y = e.getY
    
    // Savoir quel bouton a été cliqué
    val bouton = e.getButton // 1: Gauche, 2: Molette, 3: Droit
    
    if (bouton == MouseEvent.BUTTON1) {
      println(s"Clic gauche à : $x, $y")
    }
  }
})
```

### 2\. Détecter le mouvement (MouseMotionListener)

Utile si vous voulez qu'un objet suive le curseur de la souris (ex: une raquette de Pong ou un viseur).

Scala

```
var mouseX = 0
var mouseY = 0

display.addMouseMotionListener(new MouseAdapter() {
  override def mouseMoved(e: MouseEvent): Unit = {
    mouseX = e.getX
    mouseY = e.getY
  }
})
```

---

## 🛠️ Fonctions utiles de l'objet `MouseEvent`

Lorsqu'un événement de souris se produit, l'objet `e` contient des informations précieuses :

- `e.getX` **/** `e.getY` : Coordonnées du curseur au moment de l'action.
- `e.getClickCount` : Permet de détecter un double-clic (si la valeur est 2).
- `e.getButton` : Identifie le bouton (constantes `MouseEvent.BUTTON1`, `BUTTON2`, `BUTTON3`).

---

## 💡 Exemple : Créer un bouton cliquable

Dans un jeu, on n'a pas de vrais "boutons HTML". Il faut vérifier si le clic est à l'intérieur d'une zone rectangulaire.

Scala

```
// Dans votre boucle ou votre MouseListener
override def mousePressed(e: MouseEvent): Unit = {
  val mx = e.getX
  val my = e.getY
  
  // Coordonnées de notre bouton
  val btnX = 100
  val btnY = 50
  val btnW = 200
  val btnH = 40
  
  // Vérification de collision Point-Rectangle
  if (mx >= btnX && mx <= btnX + btnW && my >= btnY && my <= btnY + btnH) {
    println("Bouton cliqué !")
    // Lancer l'action du jeu ici
  }
}
```

---

## ⚠️ Différence importante : Clicked vs Pressed

- `mousePressed` : S'active dès que vous enfoncez le bouton (plus réactif pour les jeux).
- `mouseReleased` : S'active quand vous relâchez le bouton.
- `mouseClicked` : S'active uniquement après un enfoncement ET un relâchement complet (moins utilisé dans les jeux d'action).