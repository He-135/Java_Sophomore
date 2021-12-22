package question04;

import java.util.Vector;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Buffer extends Application {
	private static Vector<Integer>messages = new Vector<Integer>();
	private boolean empty = true;
	private boolean full =  false;
	private final static int maxSize = 5;
	private static boolean put = false;
	private static boolean get = false;
	private static Integer getMes;
	private static Integer putMes;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane borderPane = new BorderPane();
		TextField[]textFields = new TextField[5];
		TextField textFieldPub = new TextField();
		TextField textFieldSub = new TextField();
		
		EventHandler<ActionEvent> eventHandler = e->{
			Label label1 = new Label ("Publisher");
			textFieldPub.setText("null");//初始为空
			if(put) {//如果位于写入状态则设为写入的数据
				textFieldPub.setText(putMes.toString());
			}
			VBox vBox1 = new VBox();
			vBox1.getChildren().addAll(label1, textFieldPub);
			borderPane.setLeft(vBox1);
			
			Label label2 = new Label ("Buffer");
			int i = 0;
			for (; i < messages.size(); i++) {//写入buffer中的内容
				textFields[i] = new TextField(messages.get(i).toString());
			}
			for(; i < maxSize; i++) {//空余部分为空
				textFields[i] = new TextField("null");
			}
			VBox vBox2 = new VBox();
			vBox2.getChildren().add(label2);
			for (int j = 0; j < textFields.length; j++) {
				vBox2.getChildren().add(textFields[j]);
			}
			borderPane.setCenter(vBox2);
			
			Label label3 = new Label ("Subscriber");
			textFieldSub.setText("null");
			if(get) {//如果位于读取状态则设为写入的数据
				textFieldSub.setText(getMes.toString());
			}
			VBox vBox3 = new VBox();
			vBox3.getChildren().addAll(label3, textFieldSub);
			borderPane.setRight(vBox3);
			
		};
		
		Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));//每隔1s刷新一次
		animation.setCycleCount(Timeline.INDEFINITE);//无限次循环
		animation.play();
		
		
		Scene scene = new Scene(borderPane, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	public static void main(String[] args) {
		SyncObject syncObject = new SyncObject();
		Buffer buffer = new Buffer();
		Publisher publisher = new Publisher();
		publisher.setBuffer(buffer);
		publisher.setSyncObject(syncObject);
		Subscriber subscriber = new Subscriber();
		subscriber.setBuffer(buffer);
		subscriber.setSyncObject(syncObject);
		new Thread(publisher).start();
		new Thread(subscriber).start();
		launch(args);
	}
	
	public void putMessage(int mes) {
		put = true;
		get = false;
		putMes = mes;
		messages.add(mes);
		empty = false;
		if(messages.size() == maxSize) {
			full = true;
		}
	}
	
	public int getMessage(int pos) {
		get = true;
		put = false;
		getMes = messages.remove(pos);
		full = false;
		if (messages.isEmpty()) {
			empty = true;
		}
		return getMes;
	}

	
	public boolean isEmpty() {
		return empty;
	}

	
	public boolean isFull() {
		return full;
	}

	public int getSize() {
		return messages.size();
	}
	
}


class SyncObject{}
