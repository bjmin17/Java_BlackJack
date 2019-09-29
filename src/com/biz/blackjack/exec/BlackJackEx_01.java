package com.biz.blackjack.exec;

import com.biz.blackjack.service.BlackJackService;
import com.biz.blackjack.service.BlackJackServiceImpV1;
import com.biz.blackjack.service.BlackJackServiceImpV2;
import com.biz.blackjack.service.BlackJackServiceImpV3;

public class BlackJackEx_01 {

	
	public static void main(String[] args) {
		
		BlackJackService bj = new BlackJackServiceImpV1();
		bj = new BlackJackServiceImpV2();
		bj = new BlackJackServiceImpV3();
//		System.out.println("블랙잭을 시작하시겠습니까? (Y/N)");
//		System.out.print("입력 >> ");
//		Scanner scanner = new Scanner(System.in);
//		String strMenu = scanner.nextLine();
//		
//		if(strMenu.equalsIgnoreCase("Y")) {
//		while(true) {
//				bj.dealerdraw();
//				
//				bj.playerdraw();
		bj.start();
		bj.repeat();
		bj.compare();
			
			
			
			
			
//		}
		
		
			
	}
}
