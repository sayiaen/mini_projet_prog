# Clavier

## ⌨️ Gestion du Clavier

Pour un mouvement fluide, on utilise des variables de type `Boolean`.

### 1\. Variables d'état

Scala

```
var leftPressed = false
var rightPressed = false
```

### 2\. Le KeyManager

Scala

```
import java.awt.event.{KeyAdapter, KeyEvent}

display.setKeyManager(new KeyAdapter() {
  // Quand on appuie sur une touche
  override def keyPressed(e: KeyEvent): Unit = {
    if (e.getKeyCode == KeyEvent.VK_LEFT)  leftPressed = true
    if (e.getKeyCode == KeyEvent.VK_RIGHT) rightPressed = true
  }

  // Quand on relâche la touche
  override def keyReleased(e: KeyEvent): Unit = {
    if (e.getKeyCode == KeyEvent.VK_LEFT)  leftPressed = false
    if (e.getKeyCode == KeyEvent.VK_RIGHT) rightPressed = false
  }
})
```

### Touches courantes (`KeyEvent.VK_...`)

- **Directions :** `VK_UP`, `VK_DOWN`, `VK_LEFT`, `VK_RIGHT`
- **Lettres :** `VK_W`, `VK_A`, `VK_S`, `VK_D` / `VK_Z`, `VK_Q`, `VK_S`, `VK_D`
- **Spécial :** `VK_SPACE`, `VK_ENTER`, `VK_ESCAPE`