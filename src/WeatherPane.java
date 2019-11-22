
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.chart.*;

public class WeatherPane extends Pane {

    private String imgPath;
    private WeatherApp myApp;

    public WeatherPane(WeatherApp a, String i) {
        imgPath = i;
        myApp = a;
    }

    public void drawGraphics(WeatherRecord[] wInfo, int index) {

        getChildren().clear(); // clear the pane for next graphic

        // add code to display the input name of the weekday contained in  wInfo parameter
        Text dayText = new Text(220, 75, wInfo[index].getdayName());
        dayText.setFill(Color.BLACK);  // Change the pen color
        getChildren().add(dayText);

        // add code to display the high  temperature contained in  wInfo parameter
        Text highText = new Text(228, 240, Integer.toString(wInfo[index].getHighTemp()));
        highText.setFill(Color.RED);  // Change the pen color
        getChildren().add(highText);

        // add code to display the low temperature contained in  wInfo parameter
        Text lowText = new Text(228, 260, Integer.toString(wInfo[index].getLowTemp()));
        lowText.setFill(Color.BLUE);  // Change the pen color
        getChildren().add(lowText);

        switch (wInfo[index].getForecast()) // use forecast from wInfo parameter to draw appropriate graphics
        {

            case "Sun":         // Code to draw the graphics for Sun
                Text sunText = new Text(220, 215, "Sunny");
                sunText.setFill(Color.RED);  // Change the pen color
                getChildren().add(sunText);
                dayText.setFill(Color.RED);

                ImageView sun = new ImageView(imgPath + "images/" + wInfo[index].getForecast() + ".gif");
                sun.setFitHeight(100);
                sun.setFitWidth(100);
                sun.setX(190);
                sun.setY(100);
                getChildren().add(sun);
                // Change the wording of the text above and add code here to display graphics for sunny weather

                break;

            case "Wind":   // Code to draw the graphics for Clouds

                Text windText = new Text(220, 215, "Windy");
                windText.setFill(Color.BLUE);  // Change the pen color      
                getChildren().add(windText);
                dayText.setFill(Color.BLUE);

                ImageView wind = new ImageView(imgPath + "images/" + wInfo[index].getForecast() + ".png");
                wind.setFitHeight(100);
                wind.setFitWidth(100);
                wind.setX(190);
                wind.setY(100);
                getChildren().add(wind);
                // Change the wording of the text above and add code here to display graphics for cloudy weather

                break;

            case "Rain":   // Code to draw the graphics for Snow
                Text rainText = new Text(220, 215, "Rainy");
                rainText.setFill(Color.GREEN);  // Change the pen color
                getChildren().add(rainText);
                dayText.setFill(Color.GREEN);

                ImageView rain = new ImageView(imgPath + "images/" + wInfo[index].getForecast() + ".gif");
                rain.setFitHeight(100);
                rain.setFitWidth(100);
                rain.setX(190);
                rain.setY(100);
                getChildren().add(rain);
                // Change the wording of the text above and add code here to display graphics for snowy weather

                break;

            case "Snow":  // Code to draw the graphics for rain
                Text snowText = new Text(220, 215, "Snow");
                snowText.setFill(Color.MAGENTA);  // Change the pen color
                getChildren().add(snowText);
                dayText.setFill(Color.MAGENTA);

                ImageView snow = new ImageView(imgPath + "images/" + wInfo[index].getForecast() + ".png");
                snow.setFitHeight(100);
                snow.setFitWidth(100);
                snow.setX(190);
                snow.setY(100);
                getChildren().add(snow);
                // Change the wording of the text above and add code here to display graphics for  rainy weather

                break;

            case "Clouds":   // Code to draw the graphics for wind
                Text cloudText = new Text(220, 215, "Cloudy");
                cloudText.setFill(Color.TOMATO);  // Change the pen color
                getChildren().add(cloudText);
                dayText.setFill(Color.TOMATO);

                ImageView clouds = new ImageView(imgPath + "images/" + wInfo[index].getForecast() + ".png");
                clouds.setFitHeight(100);
                clouds.setFitWidth(100);
                clouds.setX(190);
                clouds.setY(100);
                getChildren().add(clouds);
                // Change the wording of the text above and add code here to display graphics for windy weather

                break;
            // Change the wording of the text above and add code here to display graphics for windy weather

            default:
                System.out.println("Error");
        } // end switch

    }

    // function drawWeatherWeekly to draw weather data for week
    public void drawGraphicsWeekly(WeatherRecord[] wInfo) {

        getChildren().clear(); // clear the pane for next graphic

        int index = 0;
        int xPos = 10;
        String names[] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        // for loop to print the 7 day
        for (int i = 0; i < 7; i++) {

            index = myApp.findIndexOfDay(names[i]);

            // add code to display the input name of the weekday contained in  wInfo parameter
            Text dayText = new Text(xPos + 5, 90, wInfo[index].getdayName());
            dayText.setFill(Color.BLACK);  // Change the pen color
            getChildren().add(dayText);

            // add code to display the high  temperature contained in  wInfo parameter
            Text highText = new Text(xPos + 20, 210, Integer.toString(wInfo[index].getHighTemp()));
            highText.setFill(Color.RED);  // Change the pen color
            getChildren().add(highText);

            // add code to display the low temperature contained in  wInfo parameter
            Text lowText = new Text(xPos + 20, 230, Integer.toString(wInfo[index].getLowTemp()));
            lowText.setFill(Color.BLUE);  // Change the pen color
            getChildren().add(lowText);

            switch (wInfo[index].getForecast()) // use forecast from wInfo parameter to draw appropriate graphics
            {

                case "Sun":         // Code to draw the graphics for Sun
                    Text sunText = new Text(xPos + 10, 190, "Sunny");
                    sunText.setFill(Color.RED);  // Change the pen color
                    getChildren().add(sunText);
                    dayText.setFill(Color.RED);

                    ImageView sun = new ImageView(imgPath + "images/" + wInfo[index].getForecast() + ".gif");
                    sun.setFitHeight(60);
                    sun.setFitWidth(60);
                    sun.setX(xPos);
                    sun.setY(100);
                    getChildren().add(sun);
                    // Change the wording of the text above and add code here to display graphics for sunny weather

                    break;

                case "Wind":   // Code to draw the graphics for Clouds

                    Text windText = new Text(xPos + 10, 190, "Windy");
                    windText.setFill(Color.BLUE);  // Change the pen color      
                    getChildren().add(windText);
                    dayText.setFill(Color.BLUE);

                    ImageView wind = new ImageView(imgPath + "images/" + wInfo[index].getForecast() + ".png");
                    wind.setFitHeight(60);
                    wind.setFitWidth(60);
                    wind.setX(xPos);
                    wind.setY(100);
                    getChildren().add(wind);
                    // Change the wording of the text above and add code here to display graphics for cloudy weather

                    break;

                case "Rain":   // Code to draw the graphics for Snow
                    Text rainText = new Text(xPos + 10, 190, "Rainy");
                    rainText.setFill(Color.GREEN);  // Change the pen color
                    getChildren().add(rainText);
                    dayText.setFill(Color.GREEN);

                    ImageView rain = new ImageView(imgPath + "images/" + wInfo[index].getForecast() + ".gif");
                    rain.setFitHeight(60);
                    rain.setFitWidth(60);
                    rain.setX(xPos);
                    rain.setY(100);
                    getChildren().add(rain);
                    // Change the wording of the text above and add code here to display graphics for snowy weather

                    break;

                case "Snow":  // Code to draw the graphics for rain
                    Text snowText = new Text(xPos + 10, 190, "Snow");
                    snowText.setFill(Color.MAGENTA);  // Change the pen color
                    getChildren().add(snowText);
                    dayText.setFill(Color.MAGENTA);

                    ImageView snow = new ImageView(imgPath + "images/" + wInfo[index].getForecast() + ".png");
                    snow.setFitHeight(60);
                    snow.setFitWidth(60);
                    snow.setX(xPos);
                    snow.setY(100);
                    getChildren().add(snow);
                    // Change the wording of the text above and add code here to display graphics for  rainy weather

                    break;

                case "Clouds":   // Code to draw the graphics for wind
                    Text cloudText = new Text(xPos + 10, 190, "Cloudy");
                    cloudText.setFill(Color.TOMATO);  // Change the pen color
                    getChildren().add(cloudText);
                    dayText.setFill(Color.TOMATO);

                    ImageView clouds = new ImageView(imgPath + "images/" + wInfo[index].getForecast() + ".png");
                    clouds.setFitHeight(60);
                    clouds.setFitWidth(60);
                    clouds.setX(xPos);
                    clouds.setY(100);
                    getChildren().add(clouds);
                    // Change the wording of the text above and add code here to display graphics for windy weather

                    break;
                // Change the wording of the text above and add code here to display graphics for windy weather
                default:

            } // end switch

            xPos = xPos + 70;
        } // end for loop
    }

    public void drawStats(WeatherRecord[] wInfo) { // todo code for stats
        Stage stage = new Stage();
        stage.setTitle("Statistics");

        Group group = new Group();
        Pane stats = new Pane();

        int avgHighNum = 0;
        int avgLowNum = 0;

        int sunNum = 0;
        int windNum = 0;
        int rainNum = 0;
        int snowNum = 0;
        int cloudNum = 0;

        for (int i = 0; i < 7; i++) {
            switch (wInfo[i].getForecast())
            {
                case "Sun":
                    sunNum++;
                    break;
                case "Wind":
                    windNum++;
                    break;
                case "Rain":
                    rainNum++;
                    break;
                case "Snow":
                    snowNum++;
                    break;
                case "Clouds":
                    cloudNum++;
                    break;
                default:
                    System.out.println("Error");
            } // end switch
        } // end for loop

        for (int i = 0; i < 7; i++) {
            avgHighNum = (avgHighNum + wInfo[i].getHighTemp());
            avgLowNum = (avgLowNum + wInfo[i].getLowTemp());
        }

        VBox vbox = new VBox(group, stats);
        Scene scene = new Scene(vbox, 500, 500);

        ObservableList<PieChart.Data> pieChartData // reference oracle documentation
                = FXCollections.observableArrayList(
                        new PieChart.Data("Sun", sunNum++),
                        new PieChart.Data("Wind", windNum++),
                        new PieChart.Data("Rain", rainNum++),
                        new PieChart.Data("Snow", snowNum++),
                        new PieChart.Data("Clouds", cloudNum++));

        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Forecast Statistics");

        Text avgHigh = new Text();
        avgHigh.setText("Average High Temp: " + Integer.toString(avgHighNum / 7));
        avgHigh.setX(50);
        avgHigh.setY(45);

        Text avgLow = new Text();
        avgLow.setX(325);
        avgLow.setY(45);
        avgLow.setText("Average Low Temp: " + Integer.toString(avgLowNum / 7));

        group.getChildren().add(chart);
        stats.getChildren().addAll(avgHigh, avgLow);
        stage.setScene(scene);
        stage.show();
    }

}
