package question01;


public class test {

	public static void main(String[] args) {
		Person[]person = new Person[4];
		person[0] = new Person("WangLin");
		person[1] = new Student("HeJunYuan");
		person[2] = new Employee("GaoKangWen");
		person[3] = new Staff("XiaoLi");
		for (int i = 0; i < person.length; i++) {
			System.out.println(person[i].toString());
		}
	}

}
