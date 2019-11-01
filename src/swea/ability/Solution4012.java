package swea.ability;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SW Expert Academy Problem Solving<br/>
 * 4012. 요리사
 */
public class Solution4012 {
	private static int N;
	private static boolean[] div;
	private static int[][] map;
	private static int result;
	private static int resourceA;
	private static int resourceB;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			resourceA = N / 2;
			resourceB = N / 2;
			div = new boolean[N];
			result = Integer.MAX_VALUE;
			dfs(0);
			System.out.println("#" + tc + " " + result);
		}
		
	}
	
	static void dfs(int idx) {
		if(idx >= N) {
			int scoreA = 0;
			int scoreB = 0;
			for(int i = 0; i < N; i++) {
				if(!div[i]) {
					for(int j = 0; j < N; j++) {
						if(!div[j] && i != j) {
							scoreA += map[i][j];
						}
					}
				} else {
					for(int j = 0; j < N; j++) {
						if(div[j] && i != j) {
							scoreB += map[i][j];
						}
					}
				}
			}
			
			int score = Math.abs(scoreA - scoreB);
			if(result > score) {
				result = score;
			}
			return;
		}
		
		if(resourceA > 0) {
			resourceA--;
			dfs(idx + 1);
			resourceA++;
		}
		
		if(resourceB > 0 && idx > 0) {
			resourceB--;
			div[idx] = true;
			dfs(idx + 1);
			div[idx] = false;
			resourceB++;
		}
	}
}
