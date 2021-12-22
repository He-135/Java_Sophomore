package project1;

import java.util.Vector;

public class TestTaxTable {

	public static void main(String[] args) {
		//��¼˰��
		double[]rate = { 0.03, 0.1, 0.2, 0.25, 0.3, 0.35, 0.45 };
		//��¼��һ��˰�ļ��
		double[]limit = { 36000, 144000, 300000, 420000, 660000, 960000 };
		//�����/������
		Vector<Integer>year_income = new Vector<Integer>();
		Vector<Integer>month_income = new Vector<Integer>();
		//��¼��/��˰��
		Vector<Integer>year_tax = new Vector<Integer>();
		Vector<Integer>month_tax = new Vector<Integer>();
		//������/������
		for (int j = 6; j*10000 <= 1000000; j++) {
			year_income.add(j*10000);
			month_income.add(j*10000/12);
		}
		
		
		//����ÿ��/ÿ��˰��
		for (int i = 0; i < year_income.size(); i++) {
			int remain = year_income.elementAt(i) - 60000;//��ȥ60000ʣ�µĲ�����Ҫ��˰
			int tex = 0;
			if (remain > 0) {//��Ҫ��˰
				//����Ҫ��˰�Ĳ��ֵ�����һ����grade=0��ʾ0~36000����1��ʾ36000~144000��
				int grade = 0;
				if (remain > 960000) {
					grade = 6;
				}
				else {
					for (int j = 0; j < limit.length; j++) {
						if(remain <= limit[j]) {
							grade = j;
							break;
						}
					}
				}
				//���ݳ������������λ����˰��
				if (grade == 0) {//��һ��
					tex += (int)(remain * rate[grade]);
				}
				else {//2~7��
					tex += (int)(limit[0] * rate[0]);//��һ�����ֵ�����
					for (int j = 1; j < grade; j++) {//����2~grade-1����˰��
						tex += (int)((limit[j] - limit[j-1]) * rate[j]);
					}
					remain -= limit[grade - 1];
					tex += (int)(remain * rate[grade]);
				}				
			}
			//��˰���������
			year_tax.add(tex);
			month_tax.add(tex/12);
		}
		
		System.out.println("Annual Income\tAnnual Tax\tMonthly Income\tMonthly Tax");
		for (int i = 0; i < year_income.size(); i++) {
			System.out.println(year_income.elementAt(i)+"\t\t"+year_tax.elementAt(i)+"\t\t"
		+month_income.elementAt(i)+"\t\t"+month_tax.elementAt(i));
		}
	}

}
