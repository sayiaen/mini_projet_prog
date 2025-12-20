

object Main extends App {

  val snakeDisplay = new SnakeDisplay

  while(true) {
    snakeDisplay.snakeKeyboard.getKeyPressed() match {
    case 's' => snakeDisplay.directionBas = true
    }
    snakeDisplay.snakeKeyboard.getKeyRealeased() match {
      case 's' => snakeDisplay.directionBas = false
    }
    snakeDisplay.game()

  }
  }
