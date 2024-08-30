import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import java.awt.Dimension
import io.github.bf2_mod_calculator.main_page.MainPage
import io.github.bf2_mod_calculator.parser.MainTableContentProvider
import io.github.bf2_mod_calculator.theme.AppTheme

fun main() = application {
    Window(
        title = "Light Rebalance Calculator",
        state = rememberWindowState( placement = WindowPlacement.Maximized),
//        state = rememberWindowState(width = 1366.dp, height = 768.dp, placement = WindowPlacement.Fullscreen),
        onCloseRequest = ::exitApplication,
    ) {
        window.minimumSize = Dimension(350, 600)

        val mainTableContentProvider = MainTableContentProvider()

//        App()

        AppTheme {
//          DropdownWithFilterPreview()
//            SelectableTablePreview()
//            MainPage(mainTableContentProvider.content())
//            MainStub()
            MainPage()
//            aaa()
//            MyApp()
        }
    }
}