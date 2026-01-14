

class Game {

  // Variables
  var state: String = "menu" //Variable pour gérer les états du jeu

  val grid = new Grid()
  val disp = new Display(grid)

  val logic = new GameLogic(disp, grid)
  val ui = new UIManager(disp)


  def run(): Unit = {
    while (true) {

      state match {
        case "menu" =>
          state = ui.updateMenu()
          disp.drawMenu
          if (state == "playing") logic.initGame(ui.level, ui.difficulty)
        case "playing" =>
          state = logic.updateGame()
          disp.drawGame(logic.score)
        case "gameover" =>
          state = ui.updateGameOver()
          disp.drawGameOver(logic.score)
          if (state == "playing") logic.initGame(ui.level, ui.difficulty)
        case "settings" =>
          state = ui.updateSettings()
          disp.drawSettings

        case _ => null
      }

      disp.fg.syncGameLogic(60) //Taux de rafraichissement 60 Hz
    }
  }


}
