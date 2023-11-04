package com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Main extends Application {

//	static final int TAILLE_POLICE_DEFAUT = 12;
//	File fichierOuvert = null;
//	Stage fermetureStage;
//	int numeroLigne = 1;

	TabPane tabPane = new TabPane();
	
	Stage window;
	
	
	Scene defaultScene;
	Scene settingsScene;
	
	
	String defaultFontFamily;
	String defaultFontStyle;
	String defaultOpenFileMode;
	
	
	int defaultFontSize;
	
	
	boolean defaultBreakLineOnEditor;
	
	
	ObservableList<String> openFileModesList = FXCollections.observableArrayList("Ouvrir dans un nouvel onglet","Ouvir dans une nouvelle fenetre");
	ObservableList<String> fontNamesList     = FXCollections.observableArrayList("Calibri","Cambria","Arial Nova","Arial","Agency FB");
	ObservableList<String> fontStylesList    = FXCollections.observableArrayList("Normal","Italic");
	
	
	/**
	 * Here you have the default configuration of the app
	 */
	@Override
		public void init(){
			try {
				super.init();
				
				/**
				 * You can customise the first state of the app here				
				 */
				
				defaultFontFamily = fontNamesList.get(0);                           // Calibri
				defaultFontStyle = fontStylesList.get(0);                           // Normal
				defaultFontSize = 18;                                               // police par défaut
				defaultBreakLineOnEditor = true;                                    // retout automatique à la ligne activée
				defaultOpenFileMode = openFileModesList.get(0);                     // "Ouvrir dans un nouvel onglet" par défaut
			}catch (Exception e) {
				System.exit(0);
			}
		}
	
	
	

	public static void main(String[] args) {
		launch(args);
	}

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;

//		/*--------------------Les sous ménus du ménu Fichier-----------------*/
//		String[] fileMenuNoms = { "Nouvel Onglet", "Nouvelle fenêtre", "Ouvrir", "Enregister", "Enregister sous",
//				"Enregister tout", "Mise en page", "Imprimer", "Fermer l'onglet", "Fermer la fenêtre", "Quitter" };
//		MenuItem[] fileMenuItem = new MenuItem[fileMenuNoms.length];
//		for (int i = 0; i < fileMenuNoms.length; i++) {
//			fileMenuItem[i] = new MenuItem(fileMenuNoms[i]);
//		}
//		/*--------------------Les sous ménus du ménu Modifier-----------------*/
//		String[] modifierMenuNoms = { "Annuler", "Rétablir", "Couper", "Copier", "Coller", "Supprimer", "Rechercher",
//				"Rechercher suivant", "Rechercher précédent", "Remplacer", "Atteindre", "Tout sélectionner",
//				"Heure/Date", "Police" };
//		MenuItem[] modifierMenuItem = new MenuItem[modifierMenuNoms.length];
//		for (int i = 0; i < modifierMenuNoms.length; i++) {
//			modifierMenuItem[i] = new MenuItem(modifierMenuNoms[i]);
//		}
//		/*--------------------Les sous ménus du ménu Affichage-----------------*/
//		/*--------------------Le sous ménu zoom-----------------*/
//		MenuItem zoomIn = new MenuItem("Zoom avant");
//		MenuItem zoomOut = new MenuItem("Zoom arrière");
//		MenuItem zoomDefault = new MenuItem("Restaurer le zoom par défaut");
//
//		Menu zoom = new Menu("Zoom ");
//		zoom.getItems().addAll(zoomIn, zoomOut, zoomDefault);
//		/*--------------------Le sous ménu de barre d'état-----------------*/
//		CheckMenuItem barreEtatMenuItem = new CheckMenuItem("Barre d'etat");
//
//		/*--------------------Le sous ménu de retour à la ligne-----------------*/
//		CheckMenuItem retourLigne = new CheckMenuItem("Retour automatique à la ligne");
//
//		/*--------------------Création des ménus-----------------*/
//		/*----------Fichier-----------*/
//		Menu fileMenu = new Menu("Fichier");
//		fileMenu.getItems().addAll(fileMenuItem);
//		/*----------Modifer-----------*/
//		Menu modifierMenu = new Menu("Modifier");
//		modifierMenu.getItems().addAll(modifierMenuItem);
//		/*----------Affichage-----------*/
//		Menu affichageMenu = new Menu("Affichage");
//		affichageMenu.getItems().addAll(zoom, barreEtatMenuItem, retourLigne);
//
//		/*--------------------Création du header de notre acceuil-----------------*/
//
//		MenuBar menuBar = new MenuBar(fileMenu, modifierMenu, affichageMenu);
//		Button parametres = new Button("Settings");
//
//		HBox header = new HBox(menuBar, parametres);
//		HBox.setHgrow(menuBar, Priority.ALWAYS);
//
//		/*---------------------Le centre de notre acceuil------------------------*/

//		
//		TextArea zoneDeTexte = new TextArea();
//		
//
//		zoneDeTexte.setOnDragOver(event -> {
//			if (event.getDragboard().hasString()) {
//				event.acceptTransferModes(TransferMode.ANY);
//			}
//		});
//		zoneDeTexte.setOnDragDropped(event -> {
//			int positionCursor = zoneDeTexte.getCaretPosition();
//			zoneDeTexte.insertText(positionCursor, event.getDragboard().getString());
//		});

//		/*---------------------Le footer de notre acceuil------------------------*/
//		Label ligneCol = new Label("Ln1,Col 1");
//		ligneCol.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
//
//		Label zoomText = new Label("100%");
//
//		HBox footer = new HBox(ligneCol, zoomText);
//		HBox.setHgrow(ligneCol, Priority.ALWAYS);
//		/*--------------------Ajout des racourcis claviers--------------*/
//		fileMenuItem[0].setAccelerator(KeyCombination.valueOf("Ctrl+N"));
//		fileMenuItem[1].setAccelerator(KeyCombination.valueOf("Ctrl+SHIFT+N"));
//		fileMenuItem[2].setAccelerator(KeyCombination.valueOf("Ctrl+O"));
//		fileMenuItem[3].setAccelerator(KeyCombination.valueOf("Ctrl+S"));
//		fileMenuItem[4].setAccelerator(KeyCombination.valueOf("Ctrl+SHIFT+S"));
//		fileMenuItem[5].setAccelerator(KeyCombination.valueOf("Ctrl+Alt+S"));
//		fileMenuItem[7].setAccelerator(KeyCombination.valueOf("Ctrl+P"));
//		fileMenuItem[8].setAccelerator(KeyCombination.valueOf("Ctrl+W"));
//		fileMenuItem[9].setAccelerator(KeyCombination.valueOf("Ctrl+SHIFT+W"));
//
//		modifierMenuItem[0].setAccelerator(KeyCombination.valueOf("Ctrl+Z"));
//		modifierMenuItem[1].setAccelerator(KeyCombination.valueOf("Ctrl+Y"));
//		modifierMenuItem[2].setAccelerator(KeyCombination.valueOf("Ctrl+X"));
//		modifierMenuItem[3].setAccelerator(KeyCombination.valueOf("Ctrl+C"));
//		modifierMenuItem[4].setAccelerator(KeyCombination.valueOf("Ctrl+V"));
//		modifierMenuItem[5].setAccelerator(KeyCombination.valueOf("Suppr"));
//		modifierMenuItem[6].setAccelerator(KeyCombination.valueOf("Ctrl+F"));
//		modifierMenuItem[7].setAccelerator(KeyCombination.valueOf("F3"));
//		modifierMenuItem[8].setAccelerator(KeyCombination.valueOf("SHIFT+F3"));
//		modifierMenuItem[9].setAccelerator(KeyCombination.valueOf("Ctrl+H"));
//		modifierMenuItem[10].setAccelerator(KeyCombination.valueOf("Ctrl+G"));
//		modifierMenuItem[11].setAccelerator(KeyCombination.valueOf("Ctrl+A"));
//		modifierMenuItem[12].setAccelerator(KeyCombination.valueOf("F5"));
//
//		zoomIn.setAccelerator(KeyCombination.valueOf("Ctrl+ ADD"));
//		zoomOut.setAccelerator(KeyCombination.valueOf("Ctrl+ Subtract"));
//		zoomDefault.setAccelerator(KeyCombination.valueOf("Ctrl+0"));

//		
//		/*----------------------Gestion des évennements sur les menus---------------*/
//
//		/*------------Fichier-------------*/
//		fileMenuItem[10].setOnAction(event -> {
//			Platform.exit();
//		});
//		fileMenuItem[9].setOnAction(event -> {
//			primaryStage.close();
//		});
//		fileMenuItem[4].setOnAction(event->{
//			FileChooser saveChooser = new FileChooser();
//			saveChooser.setTitle("Enrégister sous");
//			saveChooser.setInitialFileName("Nouveau Document.txt");
//			saveChooser.getExtensionFilters().add(new ExtensionFilter("Fichier txt","*.txt") );
//			saveChooser.showSaveDialog(primaryStage);
//		});
//		fileMenuItem[3].setOnAction(event -> {
//			try {
//				BufferedWriter br = new BufferedWriter(new FileWriter(fichierOuvert));
//				br.write(zoneDeTexte.getText());
//				br.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});
//		fileMenuItem[2].setOnAction(event -> {
//			FileChooser fileChooser = new FileChooser();
//			File fichierChoisi = fileChooser.showOpenDialog(primaryStage);
//			if (fichierChoisi != null) {
//				try {
//					fichierOuvert = fichierChoisi;
//					BufferedReader br = new BufferedReader(new FileReader(fichierChoisi));
//					String line = "";
//					zoneDeTexte.clear();
//					while (line != null) {
//						line = br.readLine();
//						if (line != null)
//							zoneDeTexte.appendText("\n" /* + numeroLigne++  + "  " */+ line);
//					}
//					br.close();
//					primaryStage.setTitle(fichierOuvert.getName());
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		fileMenuItem[1].setOnAction(event->{
//			try {
//				new Main().start(new Stage());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});
//
//		/*------------Modifier-------------*/
//
//		modifierMenuItem[0].disableProperty().bind(zoneDeTexte.undoableProperty().not());
//		modifierMenuItem[1].disableProperty().bind(zoneDeTexte.redoableProperty().not());
////			Timeline boucle = new Timeline(new KeyFrame(new Duration(5), e->{
////				Clipboard presse = Clipboard.getSystemClipboard();
////				modifierMenuItem[3].setDisable(!modifierMenuItem[3].isDisable());
////			}));
////			boucle.setCycleCount(Animation.INDEFINITE);
////			boucle.play();
//
////			modifierMenuItem[1].disableProperty().bind(zoneDeTexte.property);
//
//		modifierMenuItem[0].setOnAction(event -> {
//			zoneDeTexte.undo();
//		});
//		modifierMenuItem[1].setOnAction(event -> {
//			zoneDeTexte.redo();
		
//		});
//		modifierMenuItem[2].setOnAction(event -> {
//			zoneDeTexte.cut();
//		});
//		modifierMenuItem[3].setOnAction(event -> {
//			zoneDeTexte.copy();
//		});
//		modifierMenuItem[4].setOnAction(event -> {
//			zoneDeTexte.paste();
//		}); // 15:11 28/05/2023
//		modifierMenuItem[5].setOnAction(event -> {
//			zoneDeTexte.replaceSelection("");
//		});
//		modifierMenuItem[12].setOnAction(event -> {
//			SimpleDateFormat format = new SimpleDateFormat("HH:mm  dd/MM/yyyy");
//			String date = format.format(new Date());
//			int positionCursor = zoneDeTexte.getCaretPosition();
//			zoneDeTexte.insertText(positionCursor, date);
//		});
//		modifierMenuItem[11].setOnAction(event -> {
//			zoneDeTexte.selectAll();
//		});
//		modifierMenuItem[10].setOnAction(event -> {
//			Alert d = new Alert(AlertType.INFORMATION);
//			d.setHeaderText("");
//			d.showAndWait();
//		});
//		/*------------Affichage-------------*/
//		zoomIn.setOnAction(event -> {
//			zoneDeTexte.setFont(Font.font(zoneDeTexte.getFont().getSize() + 2));
//		});
//		zoomOut.setOnAction(event -> {
//			zoneDeTexte.setFont(Font.font(zoneDeTexte.getFont().getSize() - 2));
//		});
//		zoomDefault.setOnAction(event -> {
//			zoneDeTexte.setFont(Font.font(TAILLE_POLICE_DEFAUT));
//		});

		/*---------------------Le root,scene et autres------------------------*/
//		BorderPane root = new BorderPane(zoneDeTexte);
//		root.setTop(header);
//		root.setBottom(footer);
//		tab.setContent(root);

		/*---------------------A la fermeture de l'application------------------------*/
		Onglet tab = new Onglet("Untitled");
		
//		onglets.add(tab);
		
//		tabPane.getTabs().addAll(onglets);

		tabPane.getTabs().addAll(tab);
//		tab.setOnCloseRequest(event -> {
//			if(tabPane.getTabs().size() == 1) {
//				event.consume();
//				if (new OnClose().display()) {
//					window.close();
//				}
//			}
//		});
		StackPane root = new StackPane(tabPane);

		defaultScene = new Scene(root);
		
		root.prefWidthProperty().bind(window.widthProperty().subtract(10));
		root.prefHeightProperty().bind(window.heightProperty());

		window.setMaximized(true);
		window.setScene(defaultScene);
		/*
		 * Here the code of the scene of settings
		 */
		
		
		/*
		 * Settings root here
		 */
		
		
		
//		BorderPane settingsPane = new BorderPane();
		
		
		
		
		
		/*
		 * The title of the settings root
		 */
		Label settingsTitleLabel = new Label("Paramètres");
		settingsTitleLabel.setStyle("-fx-padding:0 0 20px 0");
		settingsTitleLabel.setFont(Font.font(28));
		
		Button backButton = new Button("<-");
		backButton.setMaxSize(30,25);
		
		HBox topSettingsBox = new HBox(10,new StackPane(backButton),settingsTitleLabel);
		
		
		ImageView view11 = new ImageView(new Image(getClass().getResource("ressources/images/icons8-chercher-50.png").toString(), 20, 20, true, true));
		
		Label label11 = new Label("thème d'application");
		
		TitledPane titledPane1 = new TitledPane();
		titledPane1.setText("Sélectionnez le thème d'aplication à afficher");
		
		
		Label label12 = new Label("Sélectionnez le thème d'aplication à afficher");
		
		VBox vbox1 = new VBox(0);
		vbox1.getChildren().addAll(label11,label12);
		vbox1.setPadding(new Insets(0,0, 0, 15));
		
		
		HBox hbox1 = new HBox(0, new StackPane(view11),vbox1);
		
		HBox.setHgrow(vbox1, Priority.SOMETIMES);
		
		
		VBox hidden1 = new VBox(10);
		
		RadioButton clairButton = new RadioButton("Clair");
		RadioButton sombreButton = new RadioButton("Sombre");
		RadioButton systemButton = new RadioButton("Utiliser les paramètres systemes");
		
		clairButton.setSelected(true);
		
		new ToggleGroup().getToggles().addAll(clairButton,sombreButton,systemButton);
		
		hidden1.getChildren().addAll(clairButton,sombreButton,systemButton);
		
		hidden1.setStyle("-fx-padding:20px;");
		
		titledPane1.setContent(hidden1);
		titledPane1.setGraphic(hbox1);
		titledPane1.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		
		
		VBox card1 = new VBox(2);
		
		card1.getChildren().addAll(titledPane1);
		card1.setStyle("-fx-font-size:14px");
		
		
		
		
		
		
		ImageView view21 = new ImageView(new Image(getClass().getResource("ressources/images/icons8-trois-points-50.png").toString(), 20, 20, true, true));
		
		Label label21 = new Label("Police");
		label21.setPadding(new Insets(0, 0, 0, 15));
		label21.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
//		Button more2 = new Button();
		
		HBox hbox2 = new HBox(0, new StackPane(view21),label21);
		
		HBox.setHgrow(label21, Priority.SOMETIMES);
		
		
		VBox hidden2 = new VBox(10);
		
		TitledPane titledPane2 = new TitledPane("Police",hidden2);
		titledPane2.setGraphic(hbox2);
		titledPane2.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		
		
		Label hiddenLabel21 = createLabel("Famille");
		ChoiceBox<String> hiddenChoiceBox1 = new ChoiceBox<>(fontNamesList.sorted());
		hiddenChoiceBox1.setValue(defaultFontFamily);
		hiddenChoiceBox1.setPrefWidth(150);
		
		
		FlowPane flowPane21 = new FlowPane();
		flowPane21.setHgap(15);
		flowPane21.setPadding(new Insets(0, 0, 20, 20));
		flowPane21.setVgap(10);
		
		HBox hiddenHBox21 = createSettingsHBox(hiddenLabel21,hiddenChoiceBox1);
		
		
		Label hiddenLabel22 = createLabel("Style");		
		ChoiceBox<String> hiddenChoiceBox2 = new ChoiceBox<>(fontStylesList);
		hiddenChoiceBox2.setValue(defaultFontStyle);
		
		hiddenChoiceBox2.setPrefWidth(150);
		
		
		
		HBox hiddenHBox22 = createSettingsHBox(hiddenLabel22,hiddenChoiceBox2);
		
		FlowPane flowPane22 = new FlowPane();
		
		
		flowPane22.setHgap(15);
		flowPane22.setPadding(new Insets(0, 0, 10, 20));
		
		flowPane22.setVgap(10);
		
		Label hiddenLabel23 = createLabel("Taille");		
		ObservableList<Integer> size = FXCollections.observableArrayList(8,9,10,11,12,14,16,18,20,22,24,26,28,36,48,72);		
		ChoiceBox<Integer> hiddenChoiceBox3 = new ChoiceBox<>(size);
		hiddenChoiceBox3.setValue(18);
		
		hiddenChoiceBox3.setPrefWidth(150);
		
		HBox hiddenHBox23 = createSettingsIntegerHBox(hiddenLabel23, hiddenChoiceBox3);
		
		
		Label showTextStateLabel = new Label("Les sons des vagues de l'océan apaisent mon âme");
		
		
		FlowPane flowPane23 = new FlowPane();
		
		hidden2.getChildren().addAll(hiddenHBox21,hiddenHBox22,hiddenHBox23,new StackPane(showTextStateLabel));
		hidden2.setStyle("-fx-padding:20px;-fx-gap:7px");
		
		flowPane23.setHgap(15);
		flowPane23.setPadding(new Insets(0, 0, 10, 20));
		
		flowPane23.setVgap(10);
		
		VBox card2 = new VBox(10);
		
		card2.getChildren().addAll(hbox2,titledPane2);
		card2.setStyle("-fx-font-size:14px");
		
		
		/**
		 * Here we have the settings for settings wraping
		 */
		
		CheckBox wrapingCheckBox = new CheckBox("Activé");
		
		//settings default status for the checkbox to reflect defaultBreakLineOnEditor
		wrapingCheckBox.setSelected(defaultBreakLineOnEditor);
		
		wrapingCheckBox.textProperty().bind(Bindings.when(wrapingCheckBox.selectedProperty()).then("Activé").otherwise("Désactivé"));
		wrapingCheckBox.selectedProperty().addListener((obs,old,newVal)->{
			defaultBreakLineOnEditor = newVal;
			updateTabsWrap();
		});
		
		
		Label breakWordsLabel = new Label("Retour automatique à la ligne");
		breakWordsLabel.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(breakWordsLabel, Priority.ALWAYS);
		
		
		HBox breakWordsHBox = new HBox(5);
		breakWordsHBox.getChildren().addAll(breakWordsLabel,wrapingCheckBox);
		breakWordsHBox.setPadding(new Insets(10));
		
		
		TitledPane breakWordsTitledPane = new TitledPane();
		breakWordsTitledPane.setGraphic(breakWordsHBox);
		breakWordsTitledPane.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		breakWordsTitledPane.setCollapsible(false);
		
		/**
		 * Here we have the part of settings that refers to open new file
		 */
		
		Label openFileModeSettingsLabel = new Label("Ouverture de fichiers");
		openFileModeSettingsLabel.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(openFileModeSettingsLabel, Priority.ALWAYS);
		
		ChoiceBox<String> openFileModesettingsChoiceBox = new ChoiceBox<>(openFileModesList);
		openFileModesettingsChoiceBox.setValue(defaultOpenFileMode);
		
		
		openFileModesettingsChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs,old,newVal)->{
			defaultOpenFileMode = newVal;
			updateTabsOpenMode();
		});
		
		HBox saveSettingsHBox = new HBox(10, openFileModeSettingsLabel,openFileModesettingsChoiceBox);
		
		TitledPane openFileModeSettingsTitledPane = new TitledPane();
		openFileModeSettingsTitledPane.setGraphic(saveSettingsHBox);
		openFileModeSettingsTitledPane.setCollapsible(false);
		openFileModeSettingsTitledPane.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		
		saveSettingsHBox.prefWidthProperty().bind(openFileModeSettingsTitledPane.widthProperty().subtract(20));
		
		
		
		VBox pane = new VBox(topSettingsBox,card1,card2,breakWordsTitledPane,openFileModeSettingsTitledPane);
		pane.setSpacing(10);
		
		
		breakWordsHBox.prefWidthProperty().bind(pane.widthProperty().subtract(20));
		
		
		Label proposLabel = new Label("A propos de cette application");
		
		Label winLabel = new Label("Bloc-notes Windows 11.2305.18.0");
		Label copyRigthLabel = new Label("c 2023 Microsoft.Tous droits réservés");
		
		Hyperlink link1 = new Hyperlink("Termes du contrat de license logiciel Microsoft"); 
		Hyperlink link2 = new Hyperlink("Contrat de services Microsoft");
		Hyperlink link3 = new Hyperlink("Déclaration de confidentialité Microsoft");
		
		Label commentLabel = new Label("Envoyer des commentaires");
		
		Label helpLabel  = new Label("Aide");
		
		
		VBox aside = new VBox(10,proposLabel,winLabel,copyRigthLabel,link1,link2,link3,commentLabel,helpLabel);
		aside.setPadding(new Insets(70, 0, 0, 0));
		aside.setMinWidth(300);
		
		
		
		FlowPane flowPane = new FlowPane(pane,aside);
		flowPane.setVgap(10);
		flowPane.setHgap(10);
		flowPane.setPadding(new Insets(30,20,0,50));
		
		pane.setMinWidth(900);
		
		
		
//		pane.prefWidthProperty().bind(flowPane.widthProperty().subtract(500));
		
		flowPane.widthProperty().addListener((obs,old,newVal)->{
			if(newVal.doubleValue() < 900) {
				pane.setMinWidth(400);
				pane.setPrefWidth(newVal.doubleValue()-60);
				aside.setPadding(new Insets(10));
//				pane.prefWidthProperty().bind(flowPane.widthProperty().subtract(40));
			}else {
				pane.setMinWidth(800);
				aside.setPadding(new Insets(60, 0, 0, 0));
				pane.setPrefWidth(newVal.doubleValue()-100-aside.getMinWidth());
//				pane.prefWidthProperty().bind(flowPane.widthProperty().subtract(500));
			}
		});
		
		pane.widthProperty().addListener((obs,old,newVal)->{
			if(newVal.doubleValue() > 600) {
				hiddenHBox21.getChildren().clear();
				flowPane21.getChildren().clear();
				hiddenHBox21.getChildren().addAll(hiddenLabel21,hiddenChoiceBox1);
				
				
				hiddenHBox22.getChildren().clear();
				hiddenHBox22.getChildren().clear();
				hiddenHBox22.getChildren().addAll(hiddenLabel22,hiddenChoiceBox2);
				
				
				hiddenHBox23.getChildren().clear();
				hiddenHBox23.getChildren().clear();
				hiddenHBox23.getChildren().addAll(hiddenLabel23,hiddenChoiceBox3);
				
				
				hidden2.getChildren().clear();
				hidden2.getChildren().addAll(hiddenHBox21,hiddenHBox22,hiddenHBox23,new StackPane(showTextStateLabel));
			}else {
				hiddenHBox21.getChildren().clear();
				flowPane21.getChildren().clear();
				flowPane21.getChildren().addAll(hiddenLabel21,hiddenChoiceBox1);
				
				hiddenHBox22.getChildren().clear();
				flowPane22.getChildren().clear();
				flowPane22.getChildren().addAll(hiddenLabel22,hiddenChoiceBox2);
				
				
				hiddenHBox23.getChildren().clear();
				flowPane23.getChildren().clear();
				flowPane23.getChildren().addAll(hiddenLabel23,hiddenChoiceBox3);
				
				
				hidden2.getChildren().clear();
				hidden2.getChildren().addAll(flowPane21,flowPane22,flowPane23,new StackPane(showTextStateLabel));
			}
		});
		
		
		
		/**
		 * 
		 * ici nous avons les actions sur les boutons et autres
		 * 
		 * 
		 * 
		 */ 
		
		backButton.setOnAction(e-> switchScene());
		
		
		hiddenChoiceBox1.getSelectionModel().selectedItemProperty().addListener((obs,oldValue,newValue)->{
			for(Tab onglet : tabPane.getTabs()) {
				changeFont(((Onglet) onglet).zoneDeTexte,hiddenChoiceBox3.getValue(),hiddenChoiceBox1.getValue(),hiddenChoiceBox2.getValue());
			}
			changeFont(showTextStateLabel,hiddenChoiceBox3.getValue(),hiddenChoiceBox1.getValue(),hiddenChoiceBox2.getValue());
		});
		
		hiddenChoiceBox2.getSelectionModel().selectedItemProperty().addListener((obs,oldValue,newValue)->{
			for(Tab onglet : tabPane.getTabs()) {
				changeFont(((Onglet) onglet).zoneDeTexte,hiddenChoiceBox3.getValue(),hiddenChoiceBox1.getValue(),hiddenChoiceBox2.getValue());

			}
			changeFont(showTextStateLabel,hiddenChoiceBox3.getValue(),hiddenChoiceBox1.getValue(),hiddenChoiceBox2.getValue());
		});
		
		hiddenChoiceBox3.getSelectionModel().selectedItemProperty().addListener((obs,oldValue,newValue)->{
			for(Tab onglet : tabPane.getTabs()) {
				changeFont(((Onglet) onglet).zoneDeTexte,hiddenChoiceBox3.getValue(),hiddenChoiceBox1.getValue(),hiddenChoiceBox2.getValue());

			}
			changeFont(showTextStateLabel,hiddenChoiceBox3.getValue(),hiddenChoiceBox1.getValue(),hiddenChoiceBox2.getValue());
		});
		
		
		
//		settingsScene = new Scene(flowPane,Screen.getPrimary().getBounds().getWidth(),Screen.getPrimary().getBounds().getHeight());
		
		settingsScene = new Scene(flowPane);
		
		flowPane.prefWidthProperty().bind(window.widthProperty().subtract(17)); // pour le param 17 ceci n'est qu'un constat
		flowPane.prefHeightProperty().bind(window.heightProperty());
		
		
//		window.setScene(settingsScene);
		
		
		/**
		 * Ici nous avons les actions sur les liens de aside dans settings
		 */
		
		link1.setOnAction(e->{
			 getHostServices().showDocument("https://github.com/princeavo/");
		});
		
		
		/*
		 * *************************************************************The end of settings code 
		 */
		
		window.setMinWidth(480);
		window.show();
		window.centerOnScreen();

		window.setOnCloseRequest(event -> {
			if (!isAllSaved()) {
				event.consume();
				boolean[] g = new OnClose().display();
				if (g[0]) {
					if (g[1]) {
						try {
							saveAll();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					window.close();
				}
			}

		});
	}
	void removeDefaultTab() {
		try {
			tabPane.getTabs().remove(0);
		}catch (Exception e) {
		}
		
	}
	
	void updateTabsOpenMode() {
		for (Tab t : tabPane.getTabs()) {
			((Onglet) t).setOpenModeIndex(openFileModesList.indexOf(defaultOpenFileMode));
		}
	}
	
	void updateTabsWrap() {
		for (Tab t : tabPane.getTabs()) {
			((Onglet) t).updateWrap();
		}
	}
	
	Label createLabel(String text) {
		Label hiddenLabel = new Label(text);	
		
		hiddenLabel.setPrefHeight(30);
		hiddenLabel.setMinWidth(200);
		hiddenLabel.setMaxWidth(Double.MAX_VALUE);
		
//		hiddenLabel.setStyle("-fx-background-color:#428591;");
		
		return hiddenLabel;
	}
	
	HBox createSettingsHBox(Label hiddenLabel, ChoiceBox<String> hiddenChoiceBox) {
		HBox hiddenHBox = new HBox(10);
		hiddenHBox.getChildren().addAll(hiddenLabel,hiddenChoiceBox);
		HBox.setHgrow(hiddenLabel, Priority.ALWAYS);
		
		return hiddenHBox;
	}
	
	HBox createSettingsIntegerHBox(Label hiddenLabel, ChoiceBox<Integer> hiddenChoiceBox) {
		HBox hiddenHBox = new HBox(10);
		hiddenHBox.getChildren().addAll(hiddenLabel,hiddenChoiceBox);
		HBox.setHgrow(hiddenLabel, Priority.ALWAYS);
		
		return hiddenHBox;
	}
	
	void changeFont(Node node,int size,String name,String style) {
		try {
			if(!fontNamesList.contains(name))
				name = fontNamesList.get(0);
			if(!fontStylesList.contains(style))
				style = fontStylesList.get(0);
			if(size<=10)
				size = defaultFontSize;
			node.setStyle("-fx-font-family:"+ name + ";" + "-fx-font-size:"+ size + "px" + ";" + "-fx-font-style:"+ style + ";");
		}catch (Exception e) {
		}
		
	}

	@SuppressWarnings("unused")
	void newOnglet() {
//		onglets.add(new Onglet("Untitled"));
		tabPane.getTabs().add(new Onglet("Untitled"));
	}

	@SuppressWarnings("unused")
	void newOnglet(File file) {
//		onglets.add(new Onglet(file.getName(), file));
		tabPane.getTabs().add(new Onglet(file.getName(), file));
	}

	@SuppressWarnings("unused")
	void saveAll() throws Exception {
		for (Tab t : tabPane.getTabs()) {
			((Onglet) t).save();
		}
	}

	boolean isAllSaved() {
		for (Tab t : tabPane.getTabs()) {
			if (!((Onglet) t).isSaved())
				return false;
		}
		return true;
	}
	
	void switchScene(){
		if(window.getScene().equals(settingsScene)){
			window.setScene(defaultScene);
		}else {
			window.setScene(settingsScene);
		}
	}

	private class OnClose {
		boolean[] close = { false/* si oui on va fermer */, false/* Si oui on veut enrégistrer */ };
		Stage stage = new Stage(StageStyle.TRANSPARENT);
		Button cancelButton = new Button("Annuler");
		Button notSaveButton = new Button("Ne pas enrégistrer");
		Button saveButton;

		public OnClose() {
			Label appName = new Label("Bloc notes");
			Label information = new Label("Voulez-vous enrégistrer les modifications de ?");
			saveButton = new Button("Enrégistrer");
			HBox boutonBox = new HBox(20);
			boutonBox.setPadding(new Insets(10));
			boutonBox.getChildren().addAll(saveButton, notSaveButton, cancelButton);

			VBox fermetureRoot = new VBox(10);
			fermetureRoot.setPadding(new Insets(30, 30, 10, 30));
			fermetureRoot.getChildren().addAll(appName, information, boutonBox);

			Scene fermetureScene = new Scene(fermetureRoot);
			stage.setScene(fermetureScene);
			stage.initModality(Modality.APPLICATION_MODAL);
		}

		public boolean[] display() {
			cancelButton.setOnAction(e -> {
				stage.close();
			});
			saveButton.setOnAction(e -> {
				try {
					saveAll();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				stage.close();
				close[1] = true;
				close[0] = true;
			});
			notSaveButton.setOnAction(e -> {
				stage.close();
				close[0] = true;
				close[1] = false;
			});
			stage.centerOnScreen();
			stage.showAndWait();
			return close;
		}

	}

	private class Onglet extends Tab {
		static final int TAILLE_POLICE_DEFAUT = 14;
		File fichierOuvert = null;
		int numeroLigne = 1;
		TextArea zoneDeTexte = new TextArea();
		TextField rechercherField = new TextField();
		TextField remplacerField = new TextField();
		boolean fileSaved = true;
		boolean automatiquementOuvert = false;
		private CheckMenuItem retourLigne; // pour réflecter le status du retour automatique à la ligne
		private int openModeIndex;

		public Onglet(String title, File file) {
			this(title);
			fichierOuvert = file;
			this.open();
		}

		public Onglet(String title) {
			
			//Set default configuration for wraping
			zoneDeTexte.setWrapText(defaultBreakLineOnEditor);
			//Set default font
			changeFont(zoneDeTexte,defaultFontSize,defaultFontFamily,defaultFontStyle);
			//default openMode
			openModeIndex = openFileModesList.indexOf(defaultOpenFileMode);
			
			
			
			/*--------------------Les sous ménus du ménu Fichier-----------------*/
			String[] fileMenuNoms = { "Nouvel Onglet", "Nouvelle fenêtre", "Ouvrir", "Enregister", "Enregister sous",
					"Enregister tout", "Mise en page", "Imprimer", "Fermer l'onglet", "Fermer la fenêtre", "Quitter" };
			MenuItem[] fileMenuItem = new MenuItem[fileMenuNoms.length];
			for (int i = 0; i < fileMenuNoms.length; i++) {
				fileMenuItem[i] = new MenuItem(fileMenuNoms[i]);
			}
			/*--------------------Les sous ménus du ménu Modifier-----------------*/
			String[] modifierMenuNoms = { "Annuler", "Rétablir", "Couper", "Copier", "Coller", "Supprimer",
					"Rechercher", "Rechercher suivant", "Rechercher précédent", "Remplacer", "Atteindre",
					"Tout sélectionner", "Heure/Date", "Police" };
			MenuItem[] modifierMenuItem = new MenuItem[modifierMenuNoms.length];
			for (int i = 0; i < modifierMenuNoms.length; i++) {
				modifierMenuItem[i] = new MenuItem(modifierMenuNoms[i]);
			}
			/*--------------------Les sous ménus du ménu Affichage-----------------*/
			/*--------------------Le sous ménu zoom-----------------*/
			MenuItem zoomIn = new MenuItem("Zoom avant");
			MenuItem zoomOut = new MenuItem("Zoom arrière");
			MenuItem zoomDefault = new MenuItem("Restaurer le zoom par défaut");

			Menu zoom = new Menu("Zoom ");
			zoom.getItems().addAll(zoomIn, zoomOut, zoomDefault);
			/*--------------------Le sous ménu de barre d'état-----------------*/
			CheckMenuItem barreEtatMenuItem = new CheckMenuItem("Barre d'etat");

			/*--------------------Le sous ménu de retour à la ligne-----------------*/
			retourLigne = new CheckMenuItem("Retour automatique à la ligne");
			retourLigne.setSelected(defaultBreakLineOnEditor);

			/*--------------------Création des ménus-----------------*/
			/*----------Fichier-----------*/
			Menu fileMenu = new Menu("_Fichier");
			fileMenu.getItems().addAll(fileMenuItem);
			/*----------Modifer-----------*/
			Menu modifierMenu = new Menu("_Modifier");
			modifierMenu.getItems().addAll(modifierMenuItem);
			/*----------Affichage-----------*/
			Menu affichageMenu = new Menu("_Affichage");
			affichageMenu.getItems().addAll(zoom, barreEtatMenuItem, retourLigne);

			/*--------------------Création du header de notre acceuil-----------------*/

			MenuBar menuBar = new MenuBar(fileMenu, modifierMenu, affichageMenu);
			Button parametres = new Button("_Settings");
			parametres.setOnAction(e -> {
				switchScene();
			});

			HBox header = new HBox(menuBar, parametres);
			HBox.setHgrow(menuBar, Priority.ALWAYS);

			/*---------------------Le centre de notre acceuil------------------------*/

			this.setText(title);

//	 		zoneDeTexte.setStyle("-fx-control-inner-background: #222;-fx-text-fill:white;");
			
			
			//Déclaration des menuItems du clic droit sur la zone de texte
			MenuItem contextMenuCancel = new MenuItem("Annuler");
			MenuItem contextMenuRedo = new MenuItem("Suivant");
			MenuItem contextMenuCopy = new MenuItem("Copier");
			MenuItem contextMenuPaste = new MenuItem("Coller");
			MenuItem contextMenuCut = new MenuItem("Couper");
			MenuItem contextMenuSelectAll = new MenuItem("Tout sélectionner");
			MenuItem contextMenuDelete = new MenuItem("Supprimer");
			
			//Déclaration des actions sur les menuItems du clic droit sur la zone de texte
			contextMenuCancel.setOnAction(e-> zoneDeTexte.undo());
			contextMenuCopy.setOnAction(e-> zoneDeTexte.copy());
			contextMenuPaste.setOnAction(e-> zoneDeTexte.paste());
			contextMenuCut.setOnAction(e-> zoneDeTexte.cut());
			contextMenuSelectAll.setOnAction(e-> zoneDeTexte.selectAll());
			contextMenuDelete.setOnAction(e-> zoneDeTexte.replaceSelection(""));
			
			CheckMenuItem contextMenuSensDeLecture = new CheckMenuItem("Lecture de gauche à droite");
			
			contextMenuSensDeLecture.setOnAction(event->{
//				zoneDeTexte.setStyle("-fx-text-align:right");
			});
			
			ContextMenu contextMenu = new ContextMenu(contextMenuCancel,contextMenuRedo,new SeparatorMenuItem(),contextMenuCopy,contextMenuPaste,contextMenuCut,contextMenuDelete,new SeparatorMenuItem(),contextMenuSelectAll,new SeparatorMenuItem(),contextMenuSensDeLecture);
			
			zoneDeTexte.setContextMenu(contextMenu);
			
			zoneDeTexte.setFont(Font.font(TAILLE_POLICE_DEFAUT));
			
			zoneDeTexte.textProperty().addListener((obs, o, n) -> {
				if(automatiquementOuvert)
					automatiquementOuvert = false;
				fileSaved = false;
			});
			zoneDeTexte.setOnDragOver(event -> {
				if (event.getDragboard().hasString()) {
					event.acceptTransferModes(TransferMode.ANY);
				} else if (event.getDragboard().hasFiles()) {
					event.acceptTransferModes(TransferMode.COPY);
				}
			});
			zoneDeTexte.setOnDragDropped(event -> {
				if (event.getDragboard().hasFiles()) {
					List<File> files = event.getDragboard().getFiles();
					for (File file : files) {
						if (!file.isDirectory()) {
							newOnglet(file);
						}
						
					}
				} else {
					int positionCursor = zoneDeTexte.getCaretPosition();
					zoneDeTexte.insertText(positionCursor, event.getDragboard().getString());
				}
			});
			
			/*---------------------Le footer de notre acceuil------------------------*/
			Label ligneCol = new Label("Ln1,Col 1");
			ligneCol.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			zoneDeTexte.caretPositionProperty().addListener((obs, o, n) -> {
				ligneCol.setText(String.format("Ln%d,Col%d", getCaretRowsNum(), getCaretColsNum()));
			});
			
			//Par défaut nous mettons le retour automatique à true
			zoneDeTexte.setWrapText(true);
			
			Label zoomText = new Label("100%");
			Label savingLabel = new Label();
			
			HBox footer1 = new HBox(20);
			footer1.getChildren().addAll(savingLabel, zoomText);
			
			HBox footer = new HBox(ligneCol, footer1);
			HBox.setHgrow(ligneCol, Priority.ALWAYS);
			/*--------------------Ajout des racourcis claviers--------------*/
			fileMenuItem[0].setAccelerator(KeyCombination.valueOf("Ctrl+N"));
			fileMenuItem[1].setAccelerator(KeyCombination.valueOf("Ctrl+SHIFT+N"));
			fileMenuItem[2].setAccelerator(KeyCombination.valueOf("Ctrl+O"));
			fileMenuItem[3].setAccelerator(KeyCombination.valueOf("Ctrl+S"));
			fileMenuItem[4].setAccelerator(KeyCombination.valueOf("Ctrl+SHIFT+S"));
			fileMenuItem[5].setAccelerator(KeyCombination.valueOf("Ctrl+Alt+S"));
			fileMenuItem[7].setAccelerator(KeyCombination.valueOf("Ctrl+P"));
			fileMenuItem[8].setAccelerator(KeyCombination.valueOf("Ctrl+W"));
			fileMenuItem[9].setAccelerator(KeyCombination.valueOf("Ctrl+SHIFT+W"));
			
			modifierMenuItem[0].setAccelerator(KeyCombination.valueOf("Ctrl+Z"));
			modifierMenuItem[1].setAccelerator(KeyCombination.valueOf("Ctrl+Y"));
			modifierMenuItem[2].setAccelerator(KeyCombination.valueOf("Ctrl+X"));
			modifierMenuItem[3].setAccelerator(KeyCombination.valueOf("Ctrl+C"));
			modifierMenuItem[4].setAccelerator(KeyCombination.valueOf("Ctrl+V"));
			modifierMenuItem[5].setAccelerator(KeyCombination.valueOf("Suppr"));
			modifierMenuItem[6].setAccelerator(KeyCombination.valueOf("Ctrl+F"));
			modifierMenuItem[7].setAccelerator(KeyCombination.valueOf("F3"));
			modifierMenuItem[8].setAccelerator(KeyCombination.valueOf("SHIFT+F3"));
			modifierMenuItem[9].setAccelerator(KeyCombination.valueOf("Ctrl+H"));
			modifierMenuItem[10].setAccelerator(KeyCombination.valueOf("Ctrl+G"));
			modifierMenuItem[11].setAccelerator(KeyCombination.valueOf("Ctrl+A"));
			modifierMenuItem[12].setAccelerator(KeyCombination.valueOf("F5"));
			
			zoomIn.setAccelerator(KeyCombination.valueOf("Ctrl+ ADD"));
			zoomOut.setAccelerator(KeyCombination.valueOf("Ctrl+ Subtract"));
			zoomDefault.setAccelerator(KeyCombination.valueOf("Ctrl+0"));
			
			/*----------------------Gestion des évennements sur les menus---------------*/
			
			/*------------Fichier-------------*/
			fileMenuItem[10].setOnAction(event -> {
				Platform.exit();
			});
			fileMenuItem[9].setOnAction(event -> {
				boolean[] g = new OnClose().display();
				if (g[0]) {
					if (g[1]) {
						try {
							saveAll();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					window.close();
				}
			});
			fileMenuItem[8].setOnAction(event -> {
				if (tabPane.getTabs().size() == 1) {
					event.consume();
					boolean[] g = new OnClose().display();
					if (g[0]) {
						if (g[1]) {
							try {
								save();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						window.close();
					}
				} else {
					boolean[] g = new OnClose().display();
					if (g[0]) {
						if (g[1]) {
							try {
								save();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						tabPane.getTabs().remove(this);
					}
				}
			});
			fileMenuItem[7].setOnAction(event -> {
				PrinterJob printerJob = PrinterJob.createPrinterJob();
				printerJob.printPage(zoneDeTexte);
				printerJob.endJob();
			});
			fileMenuItem[4].setOnAction(event -> {
				FileChooser saveChooser = new FileChooser();
				saveChooser.setTitle("Enrégister sous");
				saveChooser.setInitialFileName("Nouveau Document.txt");
				saveChooser.getExtensionFilters().add(new ExtensionFilter("Fichier txt", "*.txt"));
				File save = saveChooser.showSaveDialog(window);
				
				try {
					if (save != null && save.createNewFile()) {
						saveAs(save);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			fileMenuItem[3].setOnAction(event -> {
				try {
					save();
					savingLabel.setText("Saving to " + fichierOuvert.getAbsolutePath());
					new Thread(() -> {
						try {
							Thread.sleep(1500);
							Platform.runLater(() -> {
								savingLabel.setText("");
							});
						} catch (InterruptedException e) {
							
						}
					}).start();
				} catch (Exception e) {
					
				}
			});
			fileMenuItem[2].setOnAction(event -> {
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new ExtensionFilter("Fichiers textes", "*.txt"));
				File fichierChoisi = fileChooser.showOpenDialog(window);
				if (fichierChoisi != null) {
					if(openModeIndex == 0) {
						tabPane.getTabs().add(new Onglet(fichierChoisi.getName(), fichierChoisi));
						tabPane.getSelectionModel().selectNext();
					}else {
						try {
							Main otherWindow = new Main();
							otherWindow.start(new Stage());
							otherWindow.tabPane.getTabs().add(new Onglet(fichierChoisi.getName(), fichierChoisi));
							otherWindow.removeDefaultTab();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
//					this.setText(fichierChoisi.getName());
//					try {
//						fichierOuvert = fichierChoisi;
//						BufferedReader br = new BufferedReader(new FileReader(fichierChoisi));
//						String line = "";
//						zoneDeTexte.clear();
//						while ((line = br.readLine()) != null) {
//							zoneDeTexte.appendText(/* + numeroLigne++ + "  " */  line+"\n");
//						}
//						br.close();
//						fileSaved = true;
//						zoneDeTexte.positionCaret(0);
//						automatiquementOuvert = true;
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
				}
			});
			fileMenuItem[1].setOnAction(event -> {
				try {
					new Main().start(new Stage());
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			fileMenuItem[0].setOnAction(event -> {
				newOnglet();
				tabPane.getSelectionModel().selectNext();
			});

			/*------------Modifier-------------*/

			modifierMenuItem[0].disableProperty().bind(zoneDeTexte.undoableProperty().not());
			modifierMenuItem[1].disableProperty().bind(zoneDeTexte.redoableProperty().not());
//	 			Timeline boucle = new Timeline(new KeyFrame(new Duration(5), e->{
//	 				Clipboard presse = Clipboard.getSystemClipboard();
//	 				modifierMenuItem[3].setDisable(!modifierMenuItem[3].isDisable());
//	 			}));
//	 			boucle.setCycleCount(Animation.INDEFINITE);
//	 			boucle.play();

//	 			modifierMenuItem[1].disableProperty().bind(zoneDeTexte.property);

			modifierMenuItem[0].setOnAction(event -> {
				new Alert(AlertType.CONFIRMATION).showAndWait();
				if(!automatiquementOuvert) {
//					new Alert(AlertType.INFORMATION).showAndWait();
					zoneDeTexte.undo();
				}
			});
			zoneDeTexte.setOnKeyPressed(event->{
				event.consume();
				if(event.isControlDown() && event.getCode().equals(KeyCode.Z)) {
					if(automatiquementOuvert)
						event.consume();
//					new Alert(AlertType.ERROR).showAndWait();
				}
			});
			modifierMenuItem[1].setOnAction(event -> {
				zoneDeTexte.redo();
			});
			modifierMenuItem[2].setOnAction(event -> {
				zoneDeTexte.cut();
			});
			modifierMenuItem[3].setOnAction(event -> {
				zoneDeTexte.copy();
			});
			modifierMenuItem[4].setOnAction(event -> {
				zoneDeTexte.paste();
			}); // 15:11 28/05/2023
			modifierMenuItem[5].setOnAction(event -> {
				zoneDeTexte.replaceSelection("");
			});
			/*
			 * Cette partie est ce qui apparait qaund nous voulons rechercher quelque chose
			 */

			Button b1 = new Button("B1");
			b1.setPrefSize(40, 20);
			b1.setStyle("-fx-background-color:#555");

			Button b2 = new Button("B2");
			b2.setPrefSize(30, 10);
			b2.setStyle("-fx-background-color:#555");

			Button b3 = new Button("B3");
			b3.setPrefSize(30, 10);
			b3.setStyle("-fx-background-color:#555");

			Button b4 = new Button("B4");
			b4.setPrefSize(40, 20);
			b4.setStyle("-fx-background-color:#555");

			Button b5 = new Button("B5");
			b5.setPrefSize(40, 20);
			b5.setStyle("-fx-background-color:#555");

			Button b6 = new Button("B6");
			b6.setPrefSize(40, 20);
			b6.setStyle("-fx-background-color:#555");

			Button b7 = new Button("B7");
			b7.setPrefSize(40, 20);
			b7.setStyle("-fx-background-color:#555");

			HBox b36 = new HBox(4, b4, b5, b6, b7);
			b36.setAlignment(Pos.CENTER);

			rechercherField.setStyle("-fx-background-color:#888");
			remplacerField.setStyle("-fx-background-color:#888");
			remplacerField.setFont(Font.font(15));

			rechercherField.setPromptText("Rechercher");
			rechercherField.setPadding(new Insets(0, 70, 0, 0));
			rechercherField.setMinHeight(30);
			rechercherField.setFont(Font.font(15));
			rechercherField.focusedProperty().addListener((ob, o, n) -> {
				if (Boolean.TRUE.equals(n)) {
					rechercherField.setStyle("-fx-background-color:#666");
					rechercherField.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							new CornerRadii(0), new BorderWidths(0, 0, 3, 0))));
				} else {
					rechercherField.setBorder(null);
					rechercherField.setStyle("-fx-background-color:#888");
				}
			});

			b2.backgroundProperty().bind(rechercherField.backgroundProperty());
			b3.backgroundProperty().bind(rechercherField.backgroundProperty());

			b2.visibleProperty().bind(rechercherField.textProperty().isEmpty().not());

			HBox boutonsDansLeRechercherFiel = new HBox(3, b2, b3);
			boutonsDansLeRechercherFiel.setAlignment(Pos.CENTER_RIGHT);
			boutonsDansLeRechercherFiel.setMaxSize(75, 20);
			boutonsDansLeRechercherFiel.setPadding(new Insets(5));

			StackPane stackPane = new StackPane();
			stackPane.getChildren().addAll(rechercherField, boutonsDansLeRechercherFiel);
			StackPane.setAlignment(boutonsDansLeRechercherFiel, Pos.CENTER_RIGHT);

			HBox.setHgrow(stackPane, Priority.ALWAYS);

			HBox line1 = new HBox(5, b1, stackPane, b36);
			line1.setAlignment(Pos.CENTER);

			remplacerField.setPromptText("remplacer");
			remplacerField.prefWidthProperty().bind(rechercherField.widthProperty().add(-20));

			Button remplacerButton = new Button("remplacer");
			remplacerButton.setPrefSize(80, 20);

			Button remplacerToutButton = new Button("remplacer tout");
			remplacerToutButton.setPrefSize(100, 20);

			HBox line2 = new HBox(5, remplacerField, remplacerButton, remplacerToutButton);
			line2.setPadding(new Insets(0, 0, 0, 40));

			VBox vbox = new VBox(5, line1, line2);
			vbox.setStyle("-fx-background-color:#555;-fx-background-radius:10px");
			vbox.setPadding(new Insets(10));

			vbox.setMaxSize(800, 50);

			StackPane center = new StackPane(zoneDeTexte);

			StackPane.setAlignment(vbox, Pos.TOP_CENTER);

			RotateTransition transition = new RotateTransition(Duration.seconds(0.3), b1);

			rechercherField.setOnAction(event -> search());

			b1.setOnAction(event -> {
				if (b1.getRotate() == 0) {
					transition.setByAngle(-180);
				} else {
					transition.setByAngle(180);

				}
				transition.setCycleCount(1);
				transition.play();

				if (vbox.getChildren().contains(line2)) {
					vbox.getChildren().remove(line2);
					Timeline timeline = new Timeline(new KeyFrame(Duration.millis(30), e -> {
						if (zoneDeTexte.getPadding().getTop() > 30) {
							zoneDeTexte.setPadding(new Insets(zoneDeTexte.getPadding().getTop() - 10, 0, 0, 0));
						}
					}));
					timeline.setCycleCount(3);
					timeline.play();
					timeline.setOnFinished(e -> {
						zoneDeTexte.setPadding(new Insets(60, 0, 0, 0));
					});
				} else {
					vbox.getChildren().add(line2);
					Timeline timeline = new Timeline(new KeyFrame(Duration.millis(30), e -> {
						zoneDeTexte.setPadding(new Insets(zoneDeTexte.getPadding().getTop() + 20, 0, 0, 0));
					}));
					timeline.setCycleCount(3);
					timeline.play();
					timeline.setOnFinished(e -> {
						zoneDeTexte.setPadding(new Insets(90, 0, 0, 0));
					});
				}

			});
			b2.setOnAction(event -> {
				rechercherField.clear();
			});
			b3.setOnAction(event -> {
				search();
			});
			b4.setOnAction(event -> {
				search();
			});
			b5.setOnAction(event -> {
				searchPrevious();
			});
			b7.setOnAction(event -> {
				Timeline timeline = new Timeline(new KeyFrame(Duration.millis(60), e -> {
					if (zoneDeTexte.getPadding().getTop() > 0) {
						zoneDeTexte.setPadding(new Insets(zoneDeTexte.getPadding().getTop() - 20, 0, 0, 0));
					}
				}));
				timeline.setCycleCount(5);
				timeline.play();
				timeline.setOnFinished(e -> {
					zoneDeTexte.setPadding(new Insets(0));
				});
				center.getChildren().remove(vbox);
			});

			remplacerButton.setOnAction(event -> {
				searchAndReplace();
			});
			remplacerToutButton.setOnAction(event -> {
				searchAndReplaceAll();
			});
			/* C'est la fin de la partie qui vient quand nous faisons une recherche */
			modifierMenuItem[6].setOnAction(event -> {
				if (!center.getChildren().contains(vbox)) {
					zoneDeTexte.setPadding(new Insets(vbox.getMaxHeight() + 40, 0, 0, 0));
					center.getChildren().add(vbox);
					rechercherField.requestFocus();
				} else {
					zoneDeTexte.setPadding(new Insets(0));
				}
			});
			modifierMenuItem[10].setOnAction(event -> {
				Label atteindreLabel = new Label("Atteindre la ligne");
				atteindreLabel.setFont(Font.font(18));
				atteindreLabel.setTextAlignment(TextAlignment.LEFT);
				atteindreLabel.setStyle("-fx-text-fill:white");

				Label numLabel = new Label("Numéro de ligne");
				numLabel.setStyle("-fx-text-fill:white");
				numLabel.setFont(Font.font(14));

				TextField field = new TextField();
				field.setStyle("-fx-background-color:#333;-fx-text-fill:white");
				field.setPrefSize(310, 30);

				Button atteindreButton = new Button("Atteindre");
				atteindreButton
						.setStyle("-fx-background-radius:5px;-fx-text-fill:white;-fx-background-color:blueviolet");
				atteindreButton.setPrefSize(150, 30);

				Button annulerButton = new Button("Annuler");
				annulerButton.setStyle("-fx-background-radius:5px;-fx-text-fill:white;-fx-background-color:#444");
				annulerButton.setPrefSize(150, 30);

				HBox buttonBox = new HBox(10);
				buttonBox.setAlignment(Pos.CENTER);
				buttonBox.getChildren().addAll(atteindreButton, annulerButton);
				buttonBox.setPadding(new Insets(20));
				buttonBox.setStyle("-fx-background-color:#343434");
				buttonBox.setPrefSize(370, 80);

				Pane pane = new Pane(atteindreLabel, numLabel, field, buttonBox);
				pane.setStyle("-fx-background-color:#444;");

				Scene scene = new Scene(pane, 370, 220);

				buttonBox.setLayoutY(140);
				buttonBox.setLayoutX(0);

				field.setLayoutX(30);
				field.setLayoutY(90);

				numLabel.setLayoutX(30);
				numLabel.setLayoutY(60);

				atteindreLabel.setLayoutX(30);
				atteindreLabel.setLayoutY(20);

				field.setText(getCaretRowsNum() + "");

				Stage atteindreStage = new Stage(StageStyle.TRANSPARENT);
				atteindreStage.initOwner(window);
				atteindreStage.setScene(scene);

				annulerButton.setOnAction(e -> atteindreStage.close());
				atteindreButton.setOnAction(e -> {
					placeCaretOnARow(Integer.parseInt(field.getText()));
				});

				atteindreStage.showAndWait();
				atteindreStage.centerOnScreen();
				atteindreStage.setAlwaysOnTop(true);
				atteindreStage.setResizable(false);

//	 			Alert d = new Alert(AlertType.INFORMATION);
//	 			d.setHeaderText("");
//	 			d.showAndWait();
			});
			modifierMenuItem[11].setOnAction(event -> {
				zoneDeTexte.selectAll();
			});
			modifierMenuItem[12].setOnAction(event -> {
				SimpleDateFormat format = new SimpleDateFormat("HH:mm  dd/MM/yyyy");
				String date = format.format(new Date());
				int positionCursor = zoneDeTexte.getCaretPosition();
				zoneDeTexte.insertText(positionCursor, date);
			});
			/*------------Affichage-------------*/
			zoomIn.setOnAction(event -> {
				changeFont(zoneDeTexte,(int)zoneDeTexte.getFont().getSize() +  2,zoneDeTexte.getFont().getName(),zoneDeTexte.getFont().getStyle());
//				System.out.println("zoom in  " + zoneDeTexte.getFont().getSize());
//				zoneDeTexte.setFont(Font.font(zoneDeTexte.getFont().getSize() + 2));
			});
			zoomOut.setOnAction(event -> {
				zoneDeTexte.setFont(Font.font(zoneDeTexte.getFont().getSize() - 2));
			});
			zoomDefault.setOnAction(event -> {
				zoneDeTexte.setFont(Font.font(TAILLE_POLICE_DEFAUT));
			});
			retourLigne.setOnAction(event -> {
				zoneDeTexte.setWrapText(!zoneDeTexte.isWrapText());
			});

			/*---------------------Le root,scene et autres------------------------*/
			
			
			vbox.setOnKeyPressed(event->{
				if(event.getCode().equals(KeyCode.ESCAPE)) {
					if(center.getChildren().contains(vbox)) {
						Timeline timeline = new Timeline(new KeyFrame(Duration.millis(60), e -> {
							if (zoneDeTexte.getPadding().getTop() > 0) {
								zoneDeTexte.setPadding(new Insets(zoneDeTexte.getPadding().getTop() - 20, 0, 0, 0));
							}
						}));
						timeline.setCycleCount(5);
						timeline.play();
						timeline.setOnFinished(e -> {
							zoneDeTexte.setPadding(new Insets(0));
						});
						center.getChildren().remove(vbox);
					}
				}
			});
			
			
			
			
			

//	 		 SimpleIntegerProperty nombreColonneRechercherTexte = new SimpleIntegerProperty();
//	 		
//	 		GridPane grille = new GridPane();
//	 		grille.setHgap(5);
//	 		grille.setVgap(5);
//	 		grille.setStyle("-fx-background-color:red");
//	 		grille.setMaxSize(700, 50);
//	 		grille.setAlignment(Pos.CENTER);
//	 		
//	 		grille.add(new Button("B1"),0,0);
//	 		
//	 		nombreColonneRechercherTexte.addListener((ob,o,n)->{
//	 			grille.add(new TextField("Rechercher"),1, 0, nombreColonneRechercherTexte.intValue(), 1);
//		 		grille.add(new Button("B2"),nombreColonneRechercherTexte.intValue(), 0, 1, 1);
//		 		grille.add(new Button("B3"),nombreColonneRechercherTexte.intValue()+1, 0, 1, 1);
//		 		grille.add(new Button("B4"),nombreColonneRechercherTexte.intValue()+2, 0, 1, 1);
//		 		grille.add(new Button("B5"),nombreColonneRechercherTexte.intValue()+3, 0, 1, 1);
//		 		grille.add(new TextField("Remplacer"),1, 1, nombreColonneRechercherTexte.intValue()-1, 1);
//		 		grille.add(new Button("Remplacer"),nombreColonneRechercherTexte.intValue(), 1, 2, 1);
//		 		grille.add(new Button("Remplacer tout"),nombreColonneRechercherTexte.intValue()+2, 1, 3, 1);
//	 		});
//	 		
//	 		
//	 		nombreColonneRechercherTexte.set(10);

			BorderPane root = new BorderPane(center);
			root.setTop(header);
			root.setBottom(footer);
			this.setContent(root);

			this.setOnCloseRequest(event -> {
				if (tabPane.getTabs().size() == 1) {
					if (!fileSaved) {
						event.consume();
						boolean[] g = new OnClose().display();
						if (g[0]) {
							if (g[1]) {
								try {
									save();
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							window.close();
						}
					} else {
						window.close();
					}
				} else {
					if (!fileSaved) {
						boolean[] g = new OnClose().display();
						if (g[0]) {
							if (g[1]) {
								try {
									save();
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						} else {
							event.consume();
						}
					}

				}
			});
			
			
//			
//			
//			FlowPane policePane = new FlowPane(20, 20);
//			
//			Label label1 = new Label("La police sheg c'egyqs ddjhcuhucdu ");
////			label1.setMinWidth(700);
//			label1.setStyle("-fx-background-color:yellow");
//			
//			Hyperlink site = new Hyperlink("www.oracle.com"); 
//			
//			
//			policePane.getChildren().add(site);
//			
//			Label label2 = new Label("La ");
//			label2.setStyle("-fx-background-color:green");
////			label2.setMinWidth(250);
////			label2.setMaxWidth(250);
//			
//			policePane.getChildren().addAll(label1,label2);
//			
//			this.setContent(policePane);
			
		}
		public void updateWrap() {
			zoneDeTexte.setWrapText(defaultBreakLineOnEditor);
			retourLigne.setSelected(defaultBreakLineOnEditor);
		}

		private int search() {// It's means also searchNext()
			String texteARechercher = rechercherField.getText();
//			if(zoneDeTexte.getSelectedText().equals(texteARechercher)) {
//				int indiceDebut = zoneDeTexte.getText(zoneDeTexte.getText().indexoft, TAILLE_POLICE_DEFAUT)
//			}
			int indiceDeDebutMotRecherche = zoneDeTexte.getText().toLowerCase()
					.indexOf(texteARechercher.trim().toLowerCase(), zoneDeTexte.getCaretPosition());
			if (indiceDeDebutMotRecherche != -1) {
				zoneDeTexte.selectRange(indiceDeDebutMotRecherche,
						indiceDeDebutMotRecherche + texteARechercher.trim().length());
			} else {
				indiceDeDebutMotRecherche = zoneDeTexte.getText().toLowerCase()
						.indexOf(texteARechercher.trim().toLowerCase());
				if (indiceDeDebutMotRecherche != -1)
					zoneDeTexte.selectRange(indiceDeDebutMotRecherche,
							indiceDeDebutMotRecherche + texteARechercher.length());
			}
			return indiceDeDebutMotRecherche;
		}

		private void searchPrevious() {
			String texteARechercher = rechercherField.getText();
			int indiceMax = zoneDeTexte.getCaretPosition();
			if (zoneDeTexte.getSelectedText().equalsIgnoreCase(texteARechercher.trim())) {
				indiceMax = zoneDeTexte.getCaretPosition() - texteARechercher.trim().length();
			}
			int indiceDeDebutMotRecherche = zoneDeTexte.getText(0, indiceMax).toLowerCase()
					.lastIndexOf(texteARechercher.trim().toLowerCase());
			if (indiceDeDebutMotRecherche != -1) {
				zoneDeTexte.selectRange(indiceDeDebutMotRecherche,
						indiceDeDebutMotRecherche + texteARechercher.trim().length());
			} else {
				indiceDeDebutMotRecherche = zoneDeTexte.getText().toLowerCase()
						.lastIndexOf(texteARechercher.trim().toLowerCase());
				if (indiceDeDebutMotRecherche != -1)
					zoneDeTexte.selectRange(indiceDeDebutMotRecherche,
							indiceDeDebutMotRecherche + texteARechercher.trim().length());
			}
		}

		private void searchAndReplaceAll() {// Je vais d'abord déplacer la position du curseur avannt de faire l'appel
											// de la fonction search.Je dois aussi revoir la fonction de Ctrl+Z
			while (search() != -1) {
				zoneDeTexte.replaceSelection(remplacerField.getText());
			}
		}

		private void searchAndReplace() {
			int positionCursor = zoneDeTexte.getCaretPosition();
			if((positionCursor = positionCursor - rechercherField.getText().trim().length()) > 0 && zoneDeTexte.getSelectedText().equalsIgnoreCase(rechercherField.getText().trim()) ) {
				zoneDeTexte.positionCaret(positionCursor);
			}
			int positionDeDebut = search();
			if (positionDeDebut != -1) {
				
				
				zoneDeTexte.replaceText(positionDeDebut, positionDeDebut + rechercherField.getText().trim().length(),
						remplacerField.getText());
				zoneDeTexte.selectPreviousWord();
			}
		}

		private void open() {
			try {
				this.setText(fichierOuvert.getName());
				BufferedReader br = new BufferedReader(new FileReader(fichierOuvert));
				String line = "";
				zoneDeTexte.clear();
				while ((line = br.readLine()) != null) {
					zoneDeTexte.appendText( /* + numeroLigne++ + "  " */line + "\n");
				}
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			fileSaved = true;

//			Alert df = new Alert(AlertType.CONFIRMATION);
//			df.setHeaderText("Ouverture réussie");
//			df.showAndWait();
		}

		private void saveAs(File file) {
			try {
				BufferedWriter br = new BufferedWriter(new FileWriter(file));
				br.write(zoneDeTexte.getText());
				br.close();
				fileSaved = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private void save() throws Exception {
			if (fichierOuvert != null) {
				if (!fichierOuvert.exists())
					fichierOuvert.createNewFile();
				try {
					BufferedWriter br = new BufferedWriter(new FileWriter(fichierOuvert));
					br.write(zoneDeTexte.getText());
					br.close();
					fileSaved = true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new ExtensionFilter("Fichier texte", "*.txt"));
				fichierOuvert = fileChooser.showSaveDialog(window);
				this.setText(fichierOuvert.getName());
				if (fichierOuvert != null) {
					saveAs(fichierOuvert);
				}
			}

		}

		private boolean isSaved() {
			return fileSaved;
		}

		private int getCaretRowsNum() {
			// Ma logique c'est de compter le nombre de sauts de ligne "\n" avant la
			// position du curseur et je ferai plus 1 puisque sur la premiere ligne je dois
			// retourner 1
			int positionCursor = zoneDeTexte.getCaretPosition();
			Alert d = new Alert(AlertType.INFORMATION);
			d.setHeaderText(positionCursor + "");
			d.setContentText(zoneDeTexte.getText().length() + "");

			if (positionCursor == 0) {
				return 1;
			}
			try {
				StringBuffer buffer = new StringBuffer(zoneDeTexte.getText().substring(0, positionCursor));
				int taille1 = buffer.length();
				while (buffer.indexOf("\n") > -1) {
					buffer.deleteCharAt(buffer.indexOf("\n"));
				}
				int taille2 = buffer.length();
				return taille1 - taille2 + 1;
			} catch (Exception e) {
//				d.showAndWait();
				StringBuffer buffer = new StringBuffer(
						zoneDeTexte.getText().substring(0, zoneDeTexte.getText().length()));
				int taille1 = buffer.length();
				while (buffer.indexOf("\n") > -1) {
					buffer.deleteCharAt(buffer.indexOf("\n"));
				}
				int taille2 = buffer.length();
				return taille1 - taille2 + 1;
			}
		}

		private int getCaretColsNum() {
			int indiceDeDebutDeLigne = zoneDeTexte.getText(0, zoneDeTexte.getCaretPosition()).lastIndexOf("\n") + 1;
//	 		int indiceDeFinDeLigne = zoneDeTexte.getText().indexOf("\n", indiceDeDebutDeLigne);
			return zoneDeTexte.getCaretPosition() - indiceDeDebutDeLigne + 1;
		}
		/**
		 * Ma logique c'est que si tu veux placer le curseur sur la ligne n je vais le
			placer à l'indice immédiatement supérieur à l'indice correspondant
			à l'indice de la (n-1)ieme "\n" saut de ligne dans le texte contenu dans le
			textarea
		 * 
		 * @param row : numéro de ligne sur laquelle on veut placer le curseur
		 */

		private void placeCaretOnARow(int row) {
			// Ma logique c'est que si tu veux placer le curseur sur la ligne n je vais le
			// placer à l'indice immédiatement supérieur à l'indice correspondant
			// à l'indice de la (n-1)ieme "\n" saut de ligne dans le texte contenu dans le
			// textarea
			if (row <= 1) {
				zoneDeTexte.positionCaret(0);
			} else {
				int nombreDeSautDeLigne = row - 1;
				int positionFinaleCursor = zoneDeTexte.getText().indexOf("\n") + 1;
				while (nombreDeSautDeLigne > 1) {
					positionFinaleCursor = zoneDeTexte.getText().indexOf("\n", positionFinaleCursor) + 1;
					nombreDeSautDeLigne--;
				}
				zoneDeTexte.positionCaret(positionFinaleCursor);
			}
		}
		
		public void setOpenModeIndex(int openModeIndex) {
			if(openModeIndex < openFileModesList.size()) {
				this.openModeIndex = openModeIndex;
			}
		}

	}

//	
//	 BufferedReader br = new BufferedReader(new FileReader(file));
//     for (int i = 0; i < 6; i++) {
//         for (int j = 0; j < 7; j++) {
//             tab[i][j] = br.readLine().charAt(0);
//         }
//     }
//	
//     BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
//     for (int i = 0; i < 6; i++) {
//         for (int j = 0; j < 7; j++) {
//             char q = tab[i][j];
//             bw.write(q);
//             bw.newLine();
//         }
//     }
//     bw.close();
//	

}
