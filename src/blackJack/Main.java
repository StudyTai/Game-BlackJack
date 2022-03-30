package blackJack;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		ArrayList<Integer> num = new ArrayList<Integer>();
		ArrayList<Integer> bnum = new ArrayList<Integer>();
		Scanner scanner = new Scanner(System.in);
		Random r = new Random();
		Trump trump = new Trump();
		int startnum = 0;
		int nc = 1;
		int count = 0;
		ArrayList<Integer> player = trump.Number();
		int sum = 0;
		System.out.println("～ブラックジャック～");
		System.out.println("～あなたのカード～");
		for(int i=0;i<3;i++) {
			if(i==0) {
				continue;
			}
			String startcard = trump.change(player.get(i));
			int startcardnum = trump.changenum(player.get(i));
			System.out.println(i+"枚目："+startcard);
			sum += startcardnum;
			num.add(startcardnum);
			count++;
			player.remove(i);
		}
		
		int trumpnum = trump.total(num,count);
		System.out.println("合計："+trumpnum);
		System.out.println("もう一枚引きますか？(1：はい,それ以外：いいえ)");
		int toadd = scanner.nextInt();
		while (toadd==1) {
			nc +=1;
			String card = trump.change(player.get(nc));
			int cardnum = trump.changenum(player.get(nc));
			System.out.println(nc+1+"枚目："+card);
	        sum += cardnum;
	        num.add(cardnum);
	        count++;
	        System.out.println("合計："+trump.total(num, count));
	        if(nc>=49) {
	        	break;
	        }else {
	        	System.out.println("もう一枚引きますか？(1：はい,それ以外：いいえ)");
				int foradd = scanner.nextInt();
				if(foradd!=1) {
					break;
				}
	        }
			
		}
		trumpnum = trump.total(num, count);
		System.out.println("～敵のカード～");
		count = 0;
		for(int i=1;i<=2;i++) {
			String startcard = trump.change(player.get(50-i));
			startnum = trump.changenum(player.get(50-i));
			System.out.println(i+"枚目："+startcard);
			bnum.add(startnum);
			count++;
			
		}
		int btrumpnum = trump.total(bnum, count);
		int bd = Math.abs(btrumpnum-21);
		int pd = Math.abs(trumpnum-21);
		System.out.println("敵から21への近さ："+bd);
		System.out.println("あなたから21への近さ："+pd);
		if(bd-pd>0 && trumpnum > 21) {
			System.out.println("バースト！あなたの負けです！");
		}else if(bd-pd>0 && trumpnum<=21) {
			System.out.println("あなたの勝ちです！");
		}else if(pd-bd>0 && btrumpnum>21) {
			System.out.println("バースト！あなたの勝ちです！");
		}else if(pd-bd>0 && btrumpnum<=21) {
			System.out.println("あなたの負けです！");
		}else {
			System.out.println("同点です！");
			
		}
	}

}
