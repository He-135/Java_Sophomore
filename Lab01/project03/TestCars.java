package project3;



import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TestCars extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Car[]cars = new Car[4];//用于存放Car类的实例对象
		for (int i = 1; i <= cars.length; i++) {
			cars[i-1] = new Car(i * 10);
		}
		TextField[]textFields = new TextField[4];//用来输入小车速度
		for (int i = 0; i < textFields.length; i++) {
			textFields[i] = new TextField(String.valueOf(cars[i].getSpeed()));//初始为小车的默认速度
			int k = i;
			textFields[i].textProperty().addListener(new ChangeListener<String>() {//读入文本框输入的值并赋给对应的小车

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					cars[k].setSpeed(Integer.valueOf(newValue));
					
				}
			});
			textFields[i].setPrefWidth(50);
		}
		FlowPane fPane = new FlowPane();//将文本框和按钮添加到界面顶部
		for (int i = 0; i < textFields.length; i++) {
			fPane.getChildren().addAll(new Label("Car" + (i + 1)), textFields[i]);
		}
		Button btn_goButton = new Button("GO!");//用于开始小车移动
		fPane.getChildren().add(btn_goButton);
		fPane.setHgap(5);
		//四张图片作为小车
		Image image1 = new Image("file:china.jfif", 120, 60, true, true);
		ImageView imageView1 = new ImageView(image1);
		Image image2 = new Image("file:china.jfif", 120, 60, true, true);
		ImageView imageView2 = new ImageView(image2);
		Image image3 = new Image("file:china.jfif", 120, 60, true, true);
		ImageView imageView3 = new ImageView(image3);
		Image image4 = new Image("file:china.jfif", 120, 60, true, true);
		ImageView imageView4 = new ImageView(image4);
		//设置图片的初始位置
		imageView1.setY(30);
		imageView2.setY(130);
		imageView3.setY(230);
		imageView4.setY(330);
			
		Pane pane = new Pane();
		pane.getChildren().add(fPane);
		
		pane.getChildren().addAll(imageView1, imageView2, imageView3, imageView4);
		//设置多条路径
		PathTransition pTransition1 = new PathTransition();		
		pTransition1.setPath(new Line(45, 60, 555, 60));//以直线作为路径
		pTransition1.setNode(imageView1);//设置初始结点
		pTransition1.setCycleCount(1);//设置循环次数
		
		PathTransition pTransition2 = new PathTransition();		
		pTransition2.setPath(new Line(45, 160, 555, 160));
		pTransition2.setNode(imageView2);
		pTransition2.setCycleCount(1);
		
		PathTransition pTransition3 = new PathTransition();		
		pTransition3.setPath(new Line(45, 260, 555, 260));
		pTransition3.setNode(imageView3);
		pTransition3.setCycleCount(1);
		
		PathTransition pTransition4 = new PathTransition();		
		pTransition4.setPath(new Line(45, 360, 555, 360));
		pTransition4.setNode(imageView4);
		pTransition4.setCycleCount(1);
		
		//鼠标点击则响应
		btn_goButton.setOnMouseClicked(e->{		
			pTransition1.setDuration(Duration.millis(cars[0].getSpeed() / 0.005));//设置移动速度
			pTransition1.play();//开始移动
			pTransition2.setDuration(Duration.millis(cars[1].getSpeed() / 0.005));
			pTransition2.play();
			pTransition3.setDuration(Duration.millis(cars[2].getSpeed() / 0.005));
			pTransition3.play();
			pTransition4.setDuration(Duration.millis(cars[3].getSpeed() / 0.005));
			pTransition4.play();
		});
		
		
		
		
		
		Scene scene = new Scene(pane, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}

//Car类用于储存车辆信息
class Car{
	private double speed;
	public Car(double speed) {
		this.speed = speed;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	
}
