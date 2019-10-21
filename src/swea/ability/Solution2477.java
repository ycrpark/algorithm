package swea.ability;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SW Expert Academy Problem Solving<br/>
 * 2477. 차량 정비소
 */
public class Solution2477 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			Desk[] recepts = new Desk[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				Desk desk = new Desk();
				desk.no = i;
				desk.time = Integer.parseInt(st.nextToken());
				recepts[i - 1] = desk;
			}
			
			Desk[] repairs = new Desk[M];
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= M; i++) {
				Desk desk = new Desk();
				desk.no = i;
				desk.time = Integer.parseInt(st.nextToken());
				repairs[i - 1] = desk;
			}
			
			Queue<Customer> waitRecepts = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= K; i++) {
				Customer customer = new Customer();
				customer.no = i;
				customer.arrTime = Integer.parseInt(st.nextToken());
				waitRecepts.offer(customer);
			}
			
			Queue<Customer> waitRepairs = new LinkedList<>();
			
			int time = 0;
			int endCnt = 0;
			int result = 0;
			while(endCnt < K) {
				for(int i = 0; i < repairs.length; i++) {
					Desk repair = repairs[i];
					if(repair.customer != null) {
						if(--repair.customer.time == 0) {
							if(repair.customer.receptNo == A && repair.customer.repairNo == B) {
								result += repair.customer.no;
							}
							repair.customer = null;
							endCnt++;
						}
					}
				}
				
				while(!waitRepairs.isEmpty()) {
					boolean isEnd = true;
					Customer customer = waitRepairs.peek();
					for(int i = 0; i < repairs.length; i++) {
						Desk repair = repairs[i];
						if(repair.customer == null) {
							repair.customer = customer;
							customer.repairNo = repair.no;
							customer.time = repair.time;
							waitRepairs.poll();
							isEnd = false;
							break;
						}
					}
					if(isEnd) {
						break;
					}
				}
				
				for(int i = 0; i < recepts.length; i++) {
					Desk recept = recepts[i];
					if(recept.customer != null) {
						if(--recept.customer.time == 0) {
							waitRepairs.offer(recept.customer);
							recept.customer = null;
						}
					}
				}
				
				while(!waitRecepts.isEmpty()) {
					Customer customer = waitRecepts.peek();
					if(customer.arrTime > time) {
						break;
					}
					
					boolean isEnd = true;
					for(int i = 0; i < recepts.length; i++) {
						Desk recept = recepts[i];
						if(recept.customer == null) {
							recept.customer = customer;
							customer.receptNo = recept.no;
							customer.time = recept.time;
							waitRecepts.poll();
							isEnd = false;
							break;
						}
					}
					if(isEnd) {
						break;
					}
				}
				
				time++;
			}
			
			System.out.println("#" + tc + " " + (result > 0 ? result : -1));
		}
	}
	
}

class Desk {
	int no;
	int time;
	Customer customer;
}

class Customer {
	int no;
	int arrTime;
	int receptNo;
	int repairNo;
	int time;
}