package com.biz.blackjack.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.biz.blackjack.domain.BlackJackVO;

public class BlackJackServiceImpV1 implements BlackJackService {

	List<String> alist;
	List<Integer> blist;
	List<Integer> dealerlist;
	List<Integer> playerlist;
	
	Random rnd;
	Scanner scanner;
	
	BlackJackVO dealer;
	BlackJackVO player;
	int intNum;
	String strMenuD;
	String strMenuP;
	int intCountD;
	int intCountP;
	

	public BlackJackServiceImpV1() {
		alist = new ArrayList<String>();
		blist = new ArrayList<Integer>();
		dealerlist = new ArrayList<Integer>();
		playerlist = new ArrayList<Integer>();
		
		scanner = new Scanner(System.in);
		rnd = new Random();
		
		dealer = new BlackJackVO();
		player= new BlackJackVO();
		
		strMenuD = "";
		strMenuP = "";
		intCountD = 0;
		intCountP = 0;
		
		
	}

	@Override
	public void start() {

		intNum = 4;
		// 카드 구성요소
		// 문양과 숫자 합치기
		String[] strAr = {"스페이드", "클로버", "다이아몬드", "하트"};
		String[] strNum = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10","J","Q","K"};
		int[] intNum = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
		for(int i = 0 ; i < 4 ; i++) {
			for(int j = 0; j < 13; j++) {
				alist.add(strAr[i] + strNum[j] + ":" +intNum[j]); // alist에 52장 담기
			}
		}
		Collections.shuffle(alist); // alist에 담긴 52장 섞기
//		System.out.println(alist); // alist 표시
		for(String st : alist) {
			
			String[] strAAr = st.split(":");
			int intANum = Integer.valueOf(strAAr[1]);
			blist.add(intANum); // alist를 :기준으로 쪼개어 카드가 나타내는 값을 blist에 담기
		}
		
		
		int intSumD = 0; // 딜러의 카드 총합
		int intSumP = 0; // 플레이어의 카드 총합
		for(int i = 0 ; i < 3 ; i++) {
			intSumD += blist.get(i);
			
			dealer.setIntNum(intSumD);
			System.out.println("딜러가 받은 카드 : " + alist.get(i));
			System.out.println("딜러의 시작 카드 합 : " + intSumD);

			intSumP += blist.get(++i);
			
			player.setIntNum(intSumP);
			System.out.println("게이머가 받은 카드 : " + alist.get(i));
			System.out.println("게이머의 시작 카드 합 : " + intSumP);
			System.out.println();
			
		}

	} // start end

	@Override
	public boolean dealerdraw() {//딜러 21점 넘으면 끝나게
		// TODO Auto-generated method stub
		System.out.println("지금까지 나온 딜러의 카드 총합 : " + dealer.getIntNum() + "\n");
			if(dealer.getIntNum() < 17) {
				
				System.out.println("딜러의 차례, 카드 총합이 17보다 작기 때문에 카드를 뽑아야 합니다. 진행하시려면 1을 누르세요");
				System.out.print("입력 >> ");
				String strMenuD = scanner.nextLine();
				
				if(strMenuD.equalsIgnoreCase("1")) {
					System.out.println("==========================");
					System.out.println("딜러카드 총합이 17보다 작기 때문에 카드를 추가로 뽑는다\n");
					System.out.println("이번에 나온 딜러의 카드 : " + alist.get(intNum)); // alist는 순차적으로 52장 생성
					dealer.setIntNum(dealer.getIntNum() + blist.get(intNum++)); // blist는 alist를 섞은 리스트
					System.out.println("지금까지 나온 딜러의 카드 총합 : " + dealer.getIntNum());
					System.out.println("==========================\n"); 
				} else {
					
//					this.dealerdraw(); // 바꿔야할 부분
					return false;
				}
				
			} else {
				intCountD = 1;
				System.out.println("딜러 카드의 총합이 17이상이기 때문에 카드를 뽑지 않고 진행\n");
				return false;
			}
			return true;
				

	} // dealerdraw


	@Override
	public void playerdraw() {
		// TODO Auto-generated method stub

			System.out.println("이번에 나온 게이머의 카드 : " + alist.get(intNum));
			player.setIntNum(player.getIntNum() + blist.get(intNum++));
			System.out.println("지금까지 나온 게이머의 카드 총합 : " + player.getIntNum() + "\n");
			System.out.println("==========================\n");
			if(player.getIntNum() > 21) {
				System.out.println("게이머 카드 합이 21점을 넘음");
				System.out.println("딜러 승리!!");
			}
	} // playerdraw end
	
	@Override
	public void compare() {
		// TODO Auto-generated method stub
		
		System.out.println("==========================");
		System.out.println("점수 비교 딜러 : " + dealer.getIntNum() + " 게이머 : " + player.getIntNum());
		if(player.getIntNum() > 21) {
			System.out.println("게이머 카드 총합이 21을 넘었으므로 딜러 승리");
		} else if(dealer.getIntNum() > 21){
			System.out.println("딜러 카드 합이 21점을 넘음");
			System.out.println("게이머 승리!!");
		} else {
			if(dealer.getIntNum() > player.getIntNum()) { 
				System.out.println("딜러 승");
			} else if(dealer.getIntNum() < player.getIntNum()) {
				System.out.println("게이머 승");
			} else if(dealer.getIntNum() == player.getIntNum()) {
				System.out.println("무승부");
			}
		}
		System.out.println("==========================");
	} // compare end

	
	public void repeat() {
		while(true) {
			if(dealer.getIntNum() > 21 || player.getIntNum() > 21) 
				break;
			if(intCountD==1 && intCountP==1) break;

			this.dealerdraw();
			if(dealer.getIntNum() > 21) {
				System.out.println("딜러의 카드 총합이 21이 넘었으므로 게이머의 승리");
				System.out.println("점수 비교 딜러 : " + dealer.getIntNum() + " 게이머 : " + player.getIntNum());
			} else {
				System.out.println("지금까지 나온 게이머의 카드 총합 : " + player.getIntNum() + "\n");
				System.out.println("게이머의 차례, 카드를 한장 더 뽑으려면 1을, 그만 뽑으려면 아무키나 입력하세요");
				System.out.print("입력 >> ");
				strMenuP = scanner.nextLine();
				if(strMenuP.equalsIgnoreCase("1")) {
					this.playerdraw();
				} 
					intCountP=1;
			}
		}
	}
}