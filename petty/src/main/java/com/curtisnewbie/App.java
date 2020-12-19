package com.curtisnewbie;

import com.curtisnewbie.callback.OnClose;
import com.curtisnewbie.config.PropertiesLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.curtisnewbie.config.PropertyConstants.*;

/**
 * <p>
 * JavaFx Application
 * </p>
 *
 * @author yongjie.zhuang
 */
public class App extends Application {

    private static final PropertiesLoader properties = PropertiesLoader.getInstance();
    private static final String VERSION = properties.get(APP_VERSION);
    private static final String TITLE = properties.get(APP_TITLE);
    private static final String FXML_FILE = properties.get(APP_UI_FXML);
    private final int DEF_WIDTH = Integer.parseInt(properties.get(APP_DEF_WIDTH));
    private final int DEF_HEIGHT = Integer.parseInt(properties.get(APP_DEF_HEIGHT));
    private final int MIN_WIDTH = Integer.parseInt(properties.get(APP_MIN_WIDTH));
    private final int MIN_HEIGHT = Integer.parseInt(properties.get(APP_MIN_HEIGHT));

    private static Stage primaryStage;
    private static Parent parent;
    private static List<OnClose> onCloseList = new ArrayList<>();

    @Override
    public void init() throws Exception {
        ClassLoader classLoader = this.getClass().getClassLoader();
        try (InputStream fxmlIn = classLoader.getResourceAsStream(FXML_FILE);) {
            FXMLLoader loader = new FXMLLoader();
            App.parent = loader.load(fxmlIn);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        App.primaryStage = stage;
        Scene s = new Scene(parent);
        stage.setScene(s);
        stage.setTitle(String.format("%s %s", TITLE, VERSION));
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setWidth(DEF_WIDTH);
        stage.setHeight(DEF_HEIGHT);
        stage.show();
        stage.setOnCloseRequest(e -> {
            // activate all registered callbacks
            App.invokesOnCloseList();
            // exit application
            System.exit(0);
        });
        //        stage.getIcons().add(ImageUtil.TITLE_ICON);
        System.out.printf("-------------- %s-%s Up And Running ------------- \n", TITLE, VERSION);
    }

    /**
     * Register a callback for closing certain resources for application shutdown
     *
     * @param oc callback
     */
    public static void registerOnClose(OnClose oc) {
        App.onCloseList.add(oc);
    }

    /**
     * Invokes all registered callbacks
     */
    private static void invokesOnCloseList() {
        App.onCloseList.forEach(OnClose::close);
    }

    public static Stage getPrimaryStage() {
        return App.primaryStage;
    }

    public static void main(String... args) {
        System.out.printf("-------------- Initialising %s-%s ------------- \n", TITLE, VERSION);
        launch(args);
    }
}
