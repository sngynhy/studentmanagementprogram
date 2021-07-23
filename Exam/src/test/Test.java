package test;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/*
총 출석일은 20일
출석점수 = 200 * 출석율
합계 = 국어+영어+수학+출석점수
평균 = 합계 / 5
석차 = 합계가 높은 사람이 1등
학점 평균점수 90-100 A, 80-89 B, 70-79 C, 60-69 D, 60미만 F
장학금 대상 조건 평균 점수가 85점이상이고 결석횟수가 1회이하인 학생은 수상대상 O 아니면 공백
*/
class Student {
	int snum;
	int[] score = new int[3];
	int cnt; // 결석일
	int sum;
	int avg;
	int rank = 1; 
	String grade;
	boolean scholarship; // 장학금 여부
	
	Student(int snum, int[] score) {
		this.snum = snum;
		this.score = score;
		this.cnt = new Random().nextInt(4);
		this.sum = score[0] + score[1] + score[2] + (200*(20-this.cnt)/20);  // 200*(20-this.cnt)/20 : 출석점수
		this.avg = this.sum / 5;
		setGrade(); // 학점 구하기
		setScholarship();  // 장학금 여부 판단
	}

	void setGrade() {
		// 학점 평균점수 90-100 A, 80-89 B, 70-79 C, 60-69 D, 60미만 F
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
		// 장학금 대상 조건 평균 점수가 85점이상이고 결석횟수가 1회이하인 학생은 수상대상 O 아니면 공백
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
				 + "\t" + (this.scholarship ? "장학금" :" ");
	}
}









public class Test {
	
	public static void main(String[] args) {
		
		ArrayList<Student> list = new ArrayList<Student>();  // 학생 성적 저장 목록
		Scanner sc = new Scanner(System.in);
		int totalKorean = 0;
		int totalMath = 0;
		int totalEnglish = 0;
		
		ArrayList<Integer> schoList = new ArrayList<Integer>(); // 장학금 대상자 저장
		
		while (true) {
			System.out.println("------------------------------");
			System.out.println("성적 관리하는 프로그램");
			System.out.println("1. 성적 입력하기");
			System.out.println("2. 전체 성적 출력하기");
			System.out.println("3. 수상자 출력하기");
			System.out.println("4. 종료하기");
			System.out.println();
			System.out.print("메뉴를 선택하세요>> ");
			int num = sc.nextInt();
			System.out.println("------------------------------");
			
			if (num == 1) {
				System.out.print("학번 입력>> ");
				int snum = sc.nextInt();
				System.out.print("국어 점수 입력>> ");
				int korean = sc.nextInt();
				System.out.print("영어 점수 입력>> ");
				int english = sc.nextInt();
				System.out.print("수학 점수 입력>> ");
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
				
				if (stu.getScholarship()) { // 만약 장학금 대상자면
					schoList.add(snum);
				}
				
				// 입력 받는 즉시 국어/영어/수학 점수 합산 결과 저장
				totalKorean += korean;
				totalMath += math;
				totalEnglish += english;
				
			} else if (num == 2) {
				System.out.println("------------------------------ 전체 성적 ------------------------------");
				System.out.println("학번\t국어\t영어\t수학\t결석횟수\t합계\t평균\t석차\t학점\t장학금여부");
				int totalSum = 0; // 전 학생의 합계 합
				int totalAvg = 0; // 전 학생 평균 합
				for (Student stu : list) {
					totalSum += stu.sum;
					totalAvg += stu.avg;
					System.out.println(stu);
				}
				System.out.println("합계\t"+totalKorean+"\t"+totalEnglish+"\t"+totalMath+"\t0"+"\t"+totalSum+"\t"+totalAvg+"\t0");
				System.out.println("평균\t"+(totalKorean/list.size())+"\t"+(totalEnglish/list.size())+"\t"+(totalMath/list.size())+"\t0"+"\t"+(totalSum/list.size())+"\t"+(totalAvg/list.size())+"\t0");
				System.out.println("----------------------------------------------------------------------");
			} else if (num == 3) {
				System.out.println("-------------- 장학금 대상자 -------------");
				System.out.println("학번" + schoList);
				System.out.println("----------------------------------------");
			} else if (num == 4) {
				System.out.println("☆앞으로도 화이팅!☆");
				break;
			} else {
				System.out.println("잘못된 입력입니다.");
				continue;
			}
	
		}

	}
	
}
