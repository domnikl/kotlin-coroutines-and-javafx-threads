import javafx.application.Application
import javafx.application.Application.launch
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import javafx.stage.Stage
import kotlinx.coroutines.*
import kotlinx.coroutines.javafx.JavaFx
import kotlin.coroutines.CoroutineContext

class MillisElapsedCounter(private val observer: Observer) : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default

    fun start() {
        launch {
            var millisElapsed = 0

            while (true) {
                delay(1000)
                millisElapsed += 1000
                observer.notify(millisElapsed)
            }
        }
    }

    interface Observer {
        fun notify(millisElapsed: Int)
    }
}

class MyApp : Application(), MillisElapsedCounter.Observer, CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.JavaFx

    private val button = Label().also { it.text = "waiting for 0s" }

    override fun start(primaryStage: Stage?) {
        MillisElapsedCounter(this).start()

        val pane = Pane().also {
            it.children.add(button)
        }

        primaryStage?.scene = Scene(pane, 200.0, 200.0)
        primaryStage?.show()
    }

    override fun notify(millisElapsed: Int) {
        launch {
            button.text = "waiting for ${millisElapsed / 1000}s"
        }
    }
}

fun main() {
    launch(MyApp::class.java)
}
