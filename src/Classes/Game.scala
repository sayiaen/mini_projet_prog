

class Game {

  // Variables
  var state: String = "menu" //Variable pour gérer les états du jeu

  val grid = new Grid() //creation de la grille du jeu
  val disp = new Display(grid) //creation de la gestion de l'interface

  val logic = new GameLogic(disp, grid) //creation de la logique de jeu
  val ui = new UIManager(disp) //creation du manager de user interface

  //cette fonction permet de lancer le jeu et de naviguer entre les différents états

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
