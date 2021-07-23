package test;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/*
�� �⼮���� 20��
�⼮���� = 200 * �⼮��
�հ� = ����+����+����+�⼮����
��� = �հ� / 5
���� = �հ谡 ���� ����� 1��
���� ������� 90-100 A, 80-89 B, 70-79 C, 60-69 D, 60�̸� F
���б� ��� ���� ��� ������ 85���̻��̰� �ἮȽ���� 1ȸ������ �л��� ������ O �ƴϸ� ����
*/
class Student {
	int snum;
	int[] score = new int[3];
	int cnt; // �Ἦ��
	int sum;
	int avg;
	int rank = 1; 
	String grade;
	boolean scholarship; // ���б� ����
	
	Student(int snum, int[] score) {
		this.snum = snum;
		this.score = score;
		this.cnt = new Random().nextInt(4);
		this.sum = score[0] + score[1] + score[2] + (200*(20-this.cnt)/20);  // 200*(20-this.cnt)/20 : �⼮����
		this.avg = this.sum / 5;
		setGrade(); // ���� ���ϱ�
		setScholarship();  // ���б� ���� �Ǵ�
	}

	void setGrade() {
		// ���� ������� 90-100 A, 80-89 B, 70-79 C, 60-69 D, 60�̸� F
		if (this.avg >= 90) {
			this.grade = "A";
		} else if (this.avg >= 80) {
			this.grade = "B";
		} else if (this.avg >= 70) {
			this.grade = "C";
		} else if (this.avg >= 60) {
			this.grade = "D";
		} else {
			this.grade = "F";
		}
	}
	void setScholarship() {
		// ���б� ��� ���� ��� ������ 85���̻��̰� �ἮȽ���� 1ȸ������ �л��� ������ O �ƴϸ� ����
		if (this.avg >= 85 && cnt <= 1) {
			this.scholarship = true;
		} else {
			this.scholarship = false;
		}
	}
	boolean getScholarship() {
		return this.scholarship;
	}
	@Override
	public String toString() {
		return this.snum + "\t" + this.score[0] + "\t" + this.score[1] + "\t" + this.score[2]
				+ "\t" + this.cnt + "\t" + this.sum + "\t" + this.avg + "\t" + this.rank + "\t" + this.grade
				 + "\t" + (this.scholarship ? "���б�" :" ");
	}
}









public class Test {
	
	public static void main(String[] args) {
		
		ArrayList<Student> list = new ArrayList<Student>();  // �л� ���� ���� ���
		Scanner sc = new Scanner(System.in);
		int totalKorean = 0;
		int totalMath = 0;
		int totalEnglish = 0;
		
		ArrayList<Integer> schoList = new ArrayList<Integer>(); // ���б� ����� ����
		
		while (true) {
			System.out.println("------------------------------");
			System.out.println("���� �����ϴ� ���α׷�");
			System.out.println("1. ���� �Է��ϱ�");
			System.out.println("2. ��ü ���� ����ϱ�");
			System.out.println("3. ������ ����ϱ�");
			System.out.println("4. �����ϱ�");
			System.out.println();
			System.out.print("�޴��� �����ϼ���>> ");
			int num = sc.nextInt();
			System.out.println("------------------------------");
			
			if (num == 1) {
				System.out.print("�й� �Է�>> ");
				int snum = sc.nextInt();
				System.out.print("���� ���� �Է�>> ");
				int korean = sc.nextInt();
				System.out.print("���� ���� �Է�>> ");
				int english = sc.nextInt();
				System.out.print("���� ���� �Է�>> ");
				int math = sc.nextInt();
				Student stu = new Student(snum, new int[] {korean, math, english});
				for (int i=0; i<list.size(); i++) {
					if (stu.sum > list.get(i).sum) {
						list.get(i).rank++;
					} else if (stu.sum < list.get(i).sum) {
						stu.rank++;
					}
				}
				list.add(stu);
				
				if (stu.getScholarship()) { // ���� ���б� ����ڸ�
					schoList.add(snum);
				}
				
				// �Է� �޴� ��� ����/����/���� ���� �ջ� ��� ����
				totalKorean += korean;
				totalMath += math;
				totalEnglish += english;
				
			} else if (num == 2) {
				System.out.println("------------------------------ ��ü ���� ------------------------------");
				System.out.println("�й�\t����\t����\t����\t�ἮȽ��\t�հ�\t���\t����\t����\t���бݿ���");
				int totalSum = 0; // �� �л��� �հ� ��
				int totalAvg = 0; // �� �л� ��� ��
				for (Student stu : list) {
					totalSum += stu.sum;
					totalAvg += stu.avg;
					System.out.println(stu);
				}
				System.out.println("�հ�\t"+totalKorean+"\t"+totalEnglish+"\t"+totalMath+"\t0"+"\t"+totalSum+"\t"+totalAvg+"\t0");
				System.out.println("���\t"+(totalKorean/list.size())+"\t"+(totalEnglish/list.size())+"\t"+(totalMath/list.size())+"\t0"+"\t"+(totalSum/list.size())+"\t"+(totalAvg/list.size())+"\t0");
				System.out.println("----------------------------------------------------------------------");
			} else if (num == 3) {
				System.out.println("-------------- ���б� ����� -------------");
				System.out.println("�й�" + schoList);
				System.out.println("----------------------------------------");
			} else if (num == 4) {
				System.out.println("�پ����ε� ȭ����!��");
				break;
			} else {
				System.out.println("�߸��� �Է��Դϴ�.");
				continue;
			}
	
		}

	}
	
}
