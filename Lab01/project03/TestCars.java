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
		Car[]cars = new Car[4];//���ڴ��Car���ʵ������
		for (int i = 1; i <= cars.length; i++) {
			cars[i-1] = new Car(i * 10);
		}
		TextField[]textFields = new TextField[4];//��������С���ٶ�
		for (int i = 0; i < textFields.length; i++) {
			textFields[i] = new TextField(String.valueOf(cars[i].getSpeed()));//��ʼΪС����Ĭ���ٶ�
			int k = i;
			textFields[i].textProperty().addListener(new ChangeListener<String>() {//�����ı��������ֵ��������Ӧ��С��

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					cars[k].setSpeed(Integer.valueOf(newValue));
					
				}
			});
			textFields[i].setPrefWidth(50);
		}
		FlowPane fPane = new FlowPane();//���ı���Ͱ�ť��ӵ����涥��
		for (int i = 0; i < textFields.length; i++) {
			fPane.getChildren().addAll(new Label("Car" + (i + 1)), textFields[i]);
		}
		Button btn_goButton = new Button("GO!");//���ڿ�ʼС���ƶ�
		fPane.getChildren().add(btn_goButton);
		fPane.setHgap(5);
		//����ͼƬ��ΪС��
		Image image1 = new Image("file:china.jfif", 120, 60, true, true);
		ImageView imageView1 = new ImageView(image1);
		Image image2 = new Image("file:china.jfif", 120, 60, true, true);
		ImageView imageView2 = new ImageView(image2);
		Image image3 = new Image("file:china.jfif", 120, 60, true, true);
		ImageView imageView3 = new ImageView(image3);
		Image image4 = new Image("file:china.jfif", 120, 60, true, true);
		ImageView imageView4 = new ImageView(image4);
		//����ͼƬ�ĳ�ʼλ��
		imageView1.setY(30);
		imageView2.setY(130);
		imageView3.setY(230);
		imageView4.setY(330);
			
		Pane pane = new Pane();
		pane.getChildren().add(fPane);
		
		pane.getChildren().addAll(imageView1, imageView2, imageView3, imageView4);
		//���ö���·��
		PathTransition pTransition1 = new PathTransition();		
		pTransition1.setPath(new Line(45, 60, 555, 60));//��ֱ����Ϊ·��
		pTransition1.setNode(imageView1);//���ó�ʼ���
		pTransition1.setCycleCount(1);//����ѭ������
		
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
		
		//���������Ӧ
		btn_goButton.setOnMouseClicked(e->{		
			pTransition1.setDuration(Duration.millis(cars[0].getSpeed() / 0.005));//�����ƶ��ٶ�
			pTransition1.play();//��ʼ�ƶ�
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

//Car�����ڴ��泵����Ϣ
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
