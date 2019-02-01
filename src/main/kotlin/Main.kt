import javafx.application.Application
import javafx.application.Application.launch
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import javafx.stage.Stage

class MyApp : Application() {
    private val button = Label().also { it.text = "waiting for 0s" }

    override fun start(primaryStage: Stage?) {
        val pane = Pane().also {
            it.children.add(button)
        }

        primaryStage?.scene = Scene(pane,200.0,200.0)
        primaryStage?.show()
    }
}

fun main() {
    launch(MyApp::class.java)
}
