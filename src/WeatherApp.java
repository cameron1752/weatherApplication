import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class WeatherApp extends Application {

    // Constants
    private static final int MAX_RECORDS = 7;
    
    // Variables and Instances of Classes
    private WeatherRecord weeklyDataArray[] = new WeatherRecord[MAX_RECORDS];        //store all student records
    private int nextDay = 0;         // location of next empty position in the array
    private int numDays = 0;         // number of input records
    private int nextGraphic = 0; 
    
    // for images
    HostServices host = getHostServices(); 
  
    // for weather info
    private String xmlDayName;    // temporary storage for name of weekday from xml
    private String xmlForecast;   //temporary storage for forecast from xml
    private int xmlHighTemp;   //temporary storage for high temperature from xml
    private int xmlLowTemp;    // temporary storage for low temperature from xml
   
    // pane declaration
    private VBox mainPane;
    private GridPane buttonPane;
    private WeatherPane weatherPane;
    public VBox stats;
    @Override // Override the start method in the Application class
    
    
    
    public void start(Stage primaryStage) throws  ClassNotFoundException {
        
//        System.out.println("DocumentBase: " + host.getDocumentBase());
        
        mainPane = new VBox();
        buttonPane = new GridPane();
        weatherPane = new WeatherPane(this, host.getDocumentBase()); // Create a pane
       
        // read XML data from file and store in weeklyDataArray 
        readXMLFile("Weather.xml");


        
        // button declaration and positioning
        Button showAll = new Button("Show All");
        buttonPane.setRowIndex(showAll, 5);
        buttonPane.setColumnIndex(showAll, 6);
        Button statsView = new Button("More Stats");
        buttonPane.setRowIndex(statsView, 2);
        buttonPane.setColumnIndex(statsView, 3);
        Button back = new Button("Back");
        
        // Declaration of radio buttons
        RadioButton radioButtonS = new RadioButton("Sunday");
        RadioButton radioButtonM = new RadioButton("Monday");
        RadioButton radioButtonT = new RadioButton("Tuesday");
        RadioButton radioButtonW = new RadioButton("Wednesday");
        RadioButton radioButtonTh = new RadioButton("Thursday");
        RadioButton radioButtonF = new RadioButton("Friday");
        RadioButton radioButtonSa = new RadioButton("Saturday");
        
        // Label for radio buttons
        Label selectionLabel = new Label();
        selectionLabel.setText("Please select a day:");
        
        // not sure if I added this or not, it looks  like I did lol but still not sure why
        Label blank = new Label(); // just in case :)
        blank.setText("");
                
        // creates a group for the radio buttons so at least one has to be checked
        ToggleGroup radioGroup = new ToggleGroup();
        radioButtonS.setToggleGroup(radioGroup);
        radioButtonM.setToggleGroup(radioGroup);
        radioButtonT.setToggleGroup(radioGroup);
        radioButtonW.setToggleGroup(radioGroup);
        radioButtonTh.setToggleGroup(radioGroup);
        radioButtonF.setToggleGroup(radioGroup);
        radioButtonSa.setToggleGroup(radioGroup);
        
        // adding buttons to initial pane
        buttonPane.getChildren().add(radioButtonS);
        buttonPane.getChildren().add(radioButtonM);
        buttonPane.getChildren().add(radioButtonT);
        buttonPane.getChildren().add(radioButtonW);
        buttonPane.getChildren().add(radioButtonTh);
        buttonPane.getChildren().add(radioButtonF);
        buttonPane.getChildren().add(radioButtonSa);
        buttonPane.getChildren().add(showAll);
        buttonPane.getChildren().add(statsView);

        // radioButton functionality
        radioButtonS.setOnAction(e -> {
            weatherPane.drawGraphics(weeklyDataArray, findIndexOfDay("Sunday"));
                });
        radioButtonM.setOnAction(e -> {
            weatherPane.drawGraphics(weeklyDataArray, findIndexOfDay("Monday"));
                });
        radioButtonT.setOnAction(e -> {
            weatherPane.drawGraphics(weeklyDataArray, findIndexOfDay("Tuesday"));
                });
        radioButtonW.setOnAction(e -> {
            weatherPane.drawGraphics(weeklyDataArray, findIndexOfDay("Wednesday"));
                });
        radioButtonTh.setOnAction(e -> {
            weatherPane.drawGraphics(weeklyDataArray, findIndexOfDay("Thursday"));
                });
        radioButtonF.setOnAction(e -> {
            weatherPane.drawGraphics(weeklyDataArray, findIndexOfDay("Friday"));
                });
        radioButtonSa.setOnAction(e -> {
            weatherPane.drawGraphics(weeklyDataArray, findIndexOfDay("Saturday"));
                });
        
        // showAll functionality
        showAll.setOnAction(e -> {
            weatherPane.drawGraphicsWeekly(weeklyDataArray); 
        });
        
        // statsView functionality
        statsView.setOnAction(e -> { 
           weatherPane.drawStats(weeklyDataArray);
        });
        
        
        // the following two vboxes are used to make two columns
        VBox rbContainerLeft = new VBox(selectionLabel, radioButtonM, 
        radioButtonT, radioButtonW, radioButtonTh);
        buttonPane.add(rbContainerLeft, 0, 0);
        rbContainerLeft.setSpacing(5);
        rbContainerLeft.setPadding(new Insets(10, 175, 10, 20));
        rbContainerLeft.setAlignment(Pos.CENTER_LEFT);
        
        VBox rbContainerRight = new VBox(radioButtonF, radioButtonSa, 
        radioButtonS, showAll, statsView);
        buttonPane.add(rbContainerRight, 1, 0);
        rbContainerRight.setSpacing(5);
        rbContainerRight.setPadding(new Insets(72, 10, 10, 10)); 
        
         // add panes to mainPane
        mainPane.getChildren().add(buttonPane);
        mainPane.getChildren().add(weatherPane);
        
        // Create a scene and place it in the stage
        Scene scene = new Scene(mainPane, 500, 500);
        primaryStage.setTitle("Weather App"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
        
    } // end start
    
    public int findIndexOfDay (String s) {
        for (int i = 0; i < 7; i++) {
            if (weeklyDataArray[i].getdayName().equals(s)) {
                return i;
            } // end if
        } // end for loop
        return -1;
    } // end findIndexOfDay
    
   
    /**
     * The main method is only needed for the IDE with limited JavaFX support.
     * Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    } // end main

    //the method reads info from the input XML file, and then stores it in the studentArray[] 
    public void readXMLFile(String filename) {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setValidating(true);
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(new File(filename));
            NodeList list = document.getElementsByTagName("dailyWeather");

            //This for loop gathers all the student attributes, puts them in a WeatherRecord object
            //then stores that student in the weekArray
            for (int i = 0; i < list.getLength(); i++) {
                Element element = (Element) list.item(i);
                 
                xmlDayName = element.getAttribute("name");
                xmlForecast = element.getAttribute("forecast");
                xmlHighTemp = getHighTemp(element);
                xmlLowTemp = getLowTemp(element);
                WeatherRecord myWeather = new WeatherRecord(xmlDayName, xmlForecast, xmlHighTemp, xmlLowTemp);

                // store student record in array
                weeklyDataArray[nextDay] = myWeather;
                
                // add code here to write weeklyDataArray to java database 
                
                
                // increment number of student records and move to next position in studentArray
                numDays++;
                nextDay++;
                
                System.out.println(myWeather.toString());

            }//end for loop loading the studentArray[] with full student records

        }//end try block
        catch (ParserConfigurationException parserException) {
            parserException.printStackTrace();
        }//end catch block
        catch (SAXException saxException) {
            saxException.printStackTrace();
        }//end catch block
        catch (IOException ioException) {
            ioException.printStackTrace();
        }//end catch block

    }//end readFile()

    //RETURNS THE HIGH TEMP OF DAILY WEATHER
    public int getHighTemp(Element parent) {
        NodeList child = parent.getElementsByTagName("highTemp"); // only 1 per day - stores in a 1 length arraylist
        Node childTextNode = child.item(0).getFirstChild();
        return Integer.parseInt(childTextNode.getNodeValue());
    }

    //RETURNS THE LOW TEMP OF DAILY WEATHER   
    public int getLowTemp(Element parent) {
        NodeList child = parent.getElementsByTagName("lowTemp");
        Node childTextNode = child.item(0).getFirstChild();
        return Integer.parseInt(childTextNode.getNodeValue());
    }


}