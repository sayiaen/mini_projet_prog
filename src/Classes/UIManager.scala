// Cette classe permet de gérer la logique des différents menus



class UIManager(val disp: Display) {

  //Zone cliquable dans l'affichage
  val menu_btnJouer: Button = new Button(494, 1021, 1099, 1197, "Jouer")
  val gameover_btnMenu: Button = new Button(819, 1317, 1295, 1495, "Menu")
  val gameover_btnRejoouer: Button = new Button(298, 1320, 776, 1495, "Rejouer")
  val set_btnMenu: Button = new Button(525, 1434, 1075, 1561, "Jouer")

  val menu_btnSettings: Button = new Button(498, 1252, 1106, 1416, "Jouer")
  val set_level1: Button = new Button(488, 465, 1102, 558, "level1")
  val set_level2: Button = new Button(488, 587, 1102, 674, "level2")
  val set_level3: Button = new Button(488, 702, 1102, 795, "level3")
  val set_level4: Button = new Button(488, 826, 1102, 911, "level4")
  val set_level5: Button = new Button(488, 931, 1102, 1028, "level5")
  val set_facile: Button = new Button(476, 1071, 678, 1172, "facile")
  val set_moyen: Button = new Button(476, 1071, 894, 1172, "moyen")
  val set_difficile: Button = new Button(476, 1071, 1130, 1172, "difficile")

  var level: String = "level1"
  var difficulty: String = "medium"

  //Les fonctions ci dessous permettent de gérer les interactions dans les différents interfaces

  def updateSettings(): String = {

    if (set_level1.isClicked(disp.mouse)) {
      level = "level1"
      println("level set to level1")
    }

    if (set_level2.isClicked(disp.mouse)) {
      level = "level2"
      println("level set to level2")
    }

    if (set_level3.isClicked(disp.mouse)) {
      level = "level3"
      println("level set to level3")
    }

    if (set_level4.isClicked(disp.mouse)) {
      level = "level4"
      println("level set to level4")
    }

    if (set_level5.isClicked(disp.mouse)) {
      level = "level5"
      println("level set to level5")
    }
    if (set_facile.isClicked(disp.mouse)) {
      difficulty = "easy"
      println("set to easy")
    }

    if (set_moyen.isClicked(disp.mouse)) {
      difficulty = "medium"
      println("set to medium")
    }

    if (set_difficile.isClicked(disp.mouse)) {
      difficulty = "hard"
      println("set to hard")
    }


    if (set_btnMenu.isClicked(disp.mouse)) return "menu"
    "settings"

  }

  def updateGameOver():String = {
      if (gameover_btnMenu.isClicked(disp.mouse)) {
      return "menu"
    }
    if (gameover_btnRejoouer.isClicked(disp.mouse)) {
      return "playing"
    }
    "gameover"
  }



  def updateMenu(): String = {

    if (disp.keyInput.isEnterPressed || menu_btnJouer.isClicked(disp.mouse)) {
      return "playing"
    }
    if (menu_btnSettings.isClicked(disp.mouse)) {
      return "settings"
    }
    "menu"
  }

}
