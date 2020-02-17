package es.abel.dam.tests;

import es.abel.dam.Launcher;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.assertj.core.internal.bytebuddy.asm.Advice;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.TableViewMatchers;

import javax.swing.text.TableView;

public class Tests extends ApplicationTest {

    @Before
    public void setup() throws Exception{
        ApplicationTest.launch(Launcher.class);
    }

    @Override
    public void start(Stage stage) throws Exception{
        stage.show();
    }

    /**
     * Test que añade una cuenta. Si existe la cuenta con el mismo nombre no añadira una nueva
     */
    @Test
    public void testAñadirCuenta(){
        clickOn("#menuCuentas");
        clickOn("#menuAñadirCuenta");
        clickOn("#btnLoginAccept");

        clickOn("#treeViewMail");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);

        clickOn("#tablaMails");
    }

    /**
     * Test que cambia el tema de la aplicacion
     */
    @Test
    public void testTemas()  {
        clickOn("#menuVentana");
        clickOn("#miTemas");

        clickOn("#cbTemas");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#btnAceptar");
    }
}
