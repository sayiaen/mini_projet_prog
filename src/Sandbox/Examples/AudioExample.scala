import javax.sound.sampled.{AudioSystem, Clip}

class AudioExample(path: String) {
  var audioClip: Clip = _

  try {
    // Utilisation de la classe actuelle pour charger la ressource
    val url = classOf[AudioExample].getResource(path)
    if (url == null) throw new IllegalArgumentException(s"Fichier non trouvé : $path")

    val audioStream = AudioSystem.getAudioInputStream(url)

    audioClip = AudioSystem.getClip
    audioClip.open(audioStream)
  } catch {
    case e: Exception =>
      e.printStackTrace()
  }

  def play(): Unit = {
    try {
      if (audioClip != null) {
        audioClip.stop() // Arrête si déjà en lecture
        audioClip.setFramePosition(0) // Retour au début
        audioClip.start()
      }
    } catch {
      case e: Exception =>
        e.printStackTrace()
    }
  }
}