package cinebat_proy1;

import java.util.function.UnaryOperator;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.util.converter.IntegerStringConverter;

/**
 *
 * @author Cucho
 */
public class Alerta {
 
    public static void display(String title, String message){
        Stage window = new Stage();
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        
        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Aceptar");
        closeButton.setOnAction(e -> window.close());
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
    
    public static void integerTextField(TextField textField) {
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([0-9]*)?")) {
                return change;
            }
            display("ERROR","Cáracter inválido");
            return null;
        };
        textField.setTextFormatter(
                new TextFormatter<>(
                        new IntegerStringConverter(), 0, integerFilter));
    }
}
