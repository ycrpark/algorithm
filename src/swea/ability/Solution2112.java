package swea.ability;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SW Expert Academy Problem Solving<br/>
 * 2112. 보호 필름
 */
public class Solution2112 {
	private static int insertCnt;
	private static int currCnt;
	private static int D;
	private static int[][] map;
	private static int result;
	private static int W;
	private static int K;
	private static int[] fillA;
	private static int[] fillB;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[D][W];
			for(int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			fillA = new int[W];
			fillB = new int[W];
			Arrays.fill(fillB, 1);
			
			result = -1;
			currCnt = 0;
			for(insertCnt = 0; insertCnt <= K; insertCnt++) {
				dfs(0);
			}
			
			System.out.println("#" + tc + " " + result);
		}
	}
	
	static void dfs(int idx) {
		if(result == -1) {
			if(insertCnt == currCnt) {
				boolean valid = true;
				for(int j = 0; j < W; j++) {
					int before = -1;
					int sameCnt = 1;
					boolean tempValid = false;
					for(int i = 0; i < D; i++) {
						int val = map[i][j];
						if(val == before) {
							sameCnt++;
						} else {
							before = val;
							sameCnt = 1;
						}
						if(sameCnt >= K) {
							tempValid = true;
							break;
						}
					}
					if(!tempValid) {
						valid = false;
						break;
					}
				}
				
				if(valid) {
					result = insertCnt;
				}
			} else {
				currCnt++;
				for(int i = idx; i < D; i++) {
					int[] temp = map[i];
					
					map[i] = fillA;
					dfs(i + 1);
					map[i] = fillB;
					dfs(i + 1);
					
					map[i] = temp;
				}
				currCnt--;
			}
		}
	}
}
