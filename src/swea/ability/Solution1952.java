package swea.ability;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SW Expert Academy Problem Solving<br/>
 * 1952. 수영장
 */
public class Solution1952 {
	private static int result;
	private static int[] prices;
	private static int[] plans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		prices = new int[4];
		plans = new int[12];
		
		int T = Integer.parseInt(st.nextToken());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 4; i++) {
				prices[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 12; i++) {
				plans[i] = Integer.parseInt(st.nextToken());
			}
			
			// 1년 이용권 등록
			result = prices[3];
			dfs(0, 0);
			System.out.println("#" + tc + " " + result);
		}
	}
	
	private static void dfs(int idx, int price) {
		if(idx > 11) {
			if(result > price) {
				result = price;
			}
			return;
		}
		
		// 1일권
		dfs(idx + 1, price + plans[idx] * prices[0]);
		// 1개월권
		dfs(idx + 1, price + prices[1]);
		// 3개월권
		dfs(idx + 3, price + prices[2]);
	}
}
