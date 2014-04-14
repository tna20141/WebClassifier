package classifier;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import jfx.messagebox.MessageBox;
import javafx.stage.Stage;


public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button classifyButton;

    @FXML
    private Label classifyLabel;

    @FXML
    private TextField classifyTextField;

    @FXML
    private Label configLabel;

    @FXML
    private TextField configTextField;

    @FXML
    private Button testButton;

    @FXML
    private Label testLabel;

    @FXML
    private Button trainButton;

    @FXML
    private Label trainLabel;

    @FXML
    private Button updateButton;

    private Stage primaryStage;

    private String path;

    @FXML
    void classifyHandler(MouseEvent event) {
        Trainer trainer = Trainer.getInstance();
        if (!trainer.trained()) {
            MessageBox.show(this.primaryStage, "Chưa luyện dữ liệu!", "Lỗi", MessageBox.ICON_ERROR);
            return;
        }

        int category;

        try {
            File file = new File(this.classifyTextField.getText());
            category = trainer.classify(file);
        } catch (Exception e) {
            MessageBox.show(this.primaryStage, "Lỗi khi phân loại file:\n" +
                    e.getMessage(), "Lỗi", MessageBox.ICON_ERROR);
            return;
        }

        String categoryLabel = trainer.getCategoryLabel(category);

        this.classifyLabel.setText("File thuộc phân loại: " + categoryLabel);
    }

    @FXML
    void testHandler(MouseEvent event) {
        Trainer trainer = Trainer.getInstance();
        if (!trainer.trained()) {
            MessageBox.show(this.primaryStage, "Chưa luyện dữ liệu!", "Lỗi", MessageBox.ICON_ERROR);
            return;
        }

        double result;
        try {
            result = trainer.test();
        } catch (Exception e) {
            MessageBox.show(this.primaryStage, "Lỗi khi phân loại bộ dữ liệu test:\n" +
                    e.getMessage(), "Lỗi", MessageBox.ICON_ERROR);
            return;
        }

        result = (double)Math.round(result*10000)/100.0;
        this.testLabel.setText("Độ chính xác khi phân loại bộ dữ liệu test: " + result + "%");
    }

    @FXML
    void trainHandler(MouseEvent event) {
        Trainer trainer = Trainer.getInstance();
        if (!trainer.initialized()) {
            MessageBox.show(this.primaryStage, "Bộ luyện chưa được khởi tạo!", "Lỗi", MessageBox.ICON_ERROR);
            return;
        }

        try {
            trainer.train();
        } catch (Exception e) {
            MessageBox.show(this.primaryStage, "Lỗi khi luyện:\n" +
                    e.getMessage(), "Lỗi", MessageBox.ICON_ERROR);
            return;
        }

        this.trainLabel.setText("Đã luyện!");
    }

    @FXML
    void updateHandler(MouseEvent event) {
        Trainer trainer = Trainer.getInstance();
        String configFile = this.configTextField.getText();
        if (!(new File(configFile).isAbsolute()))
            configFile = this.path + "/" + configFile;

        this.trainLabel.setText("");

        try {
            trainer.init(configFile);
        } catch (Exception e) {
            MessageBox.show(this.primaryStage, "Không khởi tạo được bộ luyện. Lỗi:\n" + e.getMessage() +
                    "\n\nXin xem lại ở phần tùy chỉnh", "Lỗi", MessageBox.ICON_ERROR);
            this.trainLabel.setText("Bộ luyện chưa được khởi tạo!");
            this.configLabel.setText("Có lỗi xảy ra. Xin xem lại file cấu hình.");
            return;
        }

        this.configLabel.setText("Cấu hình OK!");
    }

    @FXML
    void initialize() {
        assert classifyButton != null : "fx:id=\"classifyButton\" was not injected: check your FXML file 'sample.fxml'.";
        assert classifyLabel != null : "fx:id=\"classifyLabel\" was not injected: check your FXML file 'sample.fxml'.";
        assert classifyTextField != null : "fx:id=\"classifyTextField\" was not injected: check your FXML file 'sample.fxml'.";
        assert configLabel != null : "fx:id=\"configLabel\" was not injected: check your FXML file 'sample.fxml'.";
        assert configTextField != null : "fx:id=\"configTextField\" was not injected: check your FXML file 'sample.fxml'.";
        assert testButton != null : "fx:id=\"testButton\" was not injected: check your FXML file 'sample.fxml'.";
        assert testLabel != null : "fx:id=\"testLabel\" was not injected: check your FXML file 'sample.fxml'.";
        assert trainButton != null : "fx:id=\"trainButton\" was not injected: check your FXML file 'sample.fxml'.";
        assert trainLabel != null : "fx:id=\"trainLabel\" was not injected: check your FXML file 'sample.fxml'.";
        assert updateButton != null : "fx:id=\"updateButton\" was not injected: check your FXML file 'sample.fxml'.";

        this.path = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        this.path = (new File(this.path)).getParent();
        String configFile = this.path + "/data/config.xml";
        this.configTextField.setText(configFile);

        this.primaryStage = Main.getPrimaryStage();

        updateHandler(null);
    }

}
