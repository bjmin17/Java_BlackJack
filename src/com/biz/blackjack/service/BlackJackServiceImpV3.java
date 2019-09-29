package com.biz.blackjack.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.biz.blackjack.domain.BlackJackVO;

public class BlackJackServiceImpV3 implements BlackJackService {

	List<String> alist;
	List<Integer> blist;
	List<String> clist;
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
	
	String[] strAAr;
	

	public BlackJackServiceImpV3() {
		alist = new ArrayList<String>();
		blist = new ArrayList<Integer>();
		clist = new ArrayList<String>();
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

	// 카드를 조합해서 두 장씩 주는 메서드
	@Override
	public void start() {
		// TODO 시작하면서 카드리스트 만드는 부분
		intNum = 4;
		// 카드 구성요소
		// 문양과 숫자 합치기
		String[] strAr = {"스페이드", "클로버", "다이아몬드", "하트"};
		String[] strNum = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10","J","Q","K"};
		
		int[] intNum = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
		for(int i = 0 ; i < 4 ; i++) {
			for(int j = 0; j < 13; j++) {
				alist.add(strAr[i] + " " + strNum[j] + ":" +intNum[j]); // alist에 52장 담기
			}
		}
		Collections.shuffle(alist); // alist에 담긴 52장 섞기
		
		for(String st : alist) {
			
			String[] strAAr = st.split(":");
			int intANum = Integer.valueOf(strAAr[1]);
			blist.add(intANum); // alist를 :기준으로 쪼개어 카드가 나타내는 값을 blist에 담기
			clist.add(strAAr[0]); // alist의 카드 종류와 번호를 보여주는 리스트
		}

		int intSumD = 0; // 딜러의 카드 총합
		int intSumP = 0; // 플레이어의 카드 총합
		
		/*
		 * 딜러와 게이머가 순차적으로 한 장씩 갖는 부분
		 */
		intSumD += blist.get(0) + blist.get(2);
			
		dealer.setIntNum(intSumD);
		System.out.println("딜러가 받은 카드 : " + clist.get(0) + ", " + clist.get(2));
		System.out.println("딜러의 시작 카드 합 : " + intSumD);

		intSumP += blist.get(1) + blist.get(3);
		
		player.setIntNum(intSumP);
		System.out.println("게이머가 받은 카드 : " + clist.get(1) + ", " + clist.get(3));
		System.out.println("게이머의 시작 카드 합 : " + intSumP);
		System.out.println("==========================\n"); 
	} // start end

	// 딜러가 카드 뽑는 부분
	@Override
	public boolean dealerdraw() {
		// TODO 딜러 카드 뽑기
		System.out.println("지금까지 나온 딜러의 카드 총합 : " + dealer.getIntNum() + "\n");
		if(dealer.getIntNum() < 17) {
			
			System.out.println("딜러의 차례, 카드 총합이 17보다 작기 때문에 카드를 뽑아야 합니다. 진행하시려면 1을 누르세요");
			System.out.print("입력 >> ");
			String strMenuD = scanner.nextLine();
			
			if(strMenuD.equalsIgnoreCase("1")) {
				System.out.println("==========================");
				System.out.println("딜러카드 총합이 17보다 작기 때문에 카드를 추가로 뽑는다\n");
				System.out.println("이번에 나온 딜러의 카드 : " + intNum + "번째 카드인 " + clist.get(intNum)); // alist는 순차적으로 52장 생성
				dealer.setIntNum(dealer.getIntNum() + blist.get(intNum++)); // blist는 alist를 섞은 리스트
				System.out.println("지금까지 나온 딜러의 카드 총합 : " + dealer.getIntNum());
				System.out.println("==========================\n"); 
			} else {
//				this.dealerdraw(); // 바꿔야할 부분
				return false;
			}
		} else {
//			intCountD = 1;
			System.out.println("딜러 카드의 총합이 17이상이기 때문에 카드를 뽑지 않고 진행\n");
		}
		return true;
	} // dealerdraw


	// 게이머가 카드를 뽑고 그 카드를 점수에 더하는 부분
	@Override
	public void playerdraw() {
		// TODO 게이머 카드 뽑기

		System.out.println("이번에 나온 게이머의 카드 : " + intNum + "번째 카드인 " + clist.get(intNum));
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
		// TODO 딜러와 게이머 점수 비교
		
		System.out.println("==========================");
		System.out.println("점수 비교 ");
		System.out.println("딜러 : " + dealer.getIntNum() + " 게이머 : " + player.getIntNum());
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
			if(intCountP == 1) break;

			if(this.dealerdraw()) { // 딜러 카드 총합이 17보다 작으면 무조건 한번 더 뽑게 만든 부분
				if(dealer.getIntNum() > 21) {
					System.out.println("딜러의 카드 총합이 21이 넘었으므로 게이머의 승리");
					System.out.println("점수 비교 ");
					System.out.println("딜러 : " + dealer.getIntNum() + " 게이머 : " + player.getIntNum());
				} else {
					System.out.println("지금까지 나온 게이머의 카드 총합 : " + player.getIntNum() + "\n");
					System.out.println("게이머의 차례, 카드를 한장 더 뽑으려면 1을, 그만 뽑으려면 아무키나 입력하세요");
					System.out.print("입력 >> ");
					strMenuP = scanner.nextLine();
					if(strMenuP.equalsIgnoreCase("1")) {
						this.playerdraw();
					} else {
						intCountP=1;
					}
				}
			} else {
				this.dealerdraw();
			}
		}
	}
}