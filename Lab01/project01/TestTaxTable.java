package project1;

import java.util.Vector;

public class TestTaxTable {

	public static void main(String[] args) {
		//记录税率
		double[]rate = { 0.03, 0.1, 0.2, 0.25, 0.3, 0.35, 0.45 };
		//记录第一档税的间隔
		double[]limit = { 36000, 144000, 300000, 420000, 660000, 960000 };
		//存放年/月收入
		Vector<Integer>year_income = new Vector<Integer>();
		Vector<Integer>month_income = new Vector<Integer>();
		//记录年/月税额
		Vector<Integer>year_tax = new Vector<Integer>();
		Vector<Integer>month_tax = new Vector<Integer>();
		//填入年/月收入
		for (int j = 6; j*10000 <= 1000000; j++) {
			year_income.add(j*10000);
			month_income.add(j*10000/12);
		}
		
		
		//计算每年/每月税额
		for (int i = 0; i < year_income.size(); i++) {
			int remain = year_income.elementAt(i) - 60000;//除去60000剩下的部分需要交税
			int tex = 0;
			if (remain > 0) {//需要交税
				//看需要交税的部分到达哪一档（grade=0表示0~36000档，1表示36000~144000）
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
				//根据超出金额所处档位计算税额
				if (grade == 0) {//第一档
					tex += (int)(remain * rate[grade]);
				}
				else {//2~7档
					tex += (int)(limit[0] * rate[0]);//第一档部分单独算
					for (int j = 1; j < grade; j++) {//计算2~grade-1级的税额
						tex += (int)((limit[j] - limit[j-1]) * rate[j]);
					}
					remain -= limit[grade - 1];
					tex += (int)(remain * rate[grade]);
				}				
			}
			//将税额存入数组
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
