package swea.ability;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SW Expert Academy Problem Solving<br/>
 * 2117. 홈 방범 서비스
 */
public class Solution2117 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][N];
			
			int totalHouseCnt = 0;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					int val = Integer.parseInt(st.nextToken());
					map[i][j] = val;
					if(val == 1) {
						totalHouseCnt++;
					}
				}
			}
			
			int resultHouse = 0;
			int maxK = N % 2 == 0 ? N + 1 : N;
			boolean isEnd = false;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					int houseCnt = 0;
					for(int K = 1; K <= maxK; K++) {
						for(int x = i - K + 1; x < N; x++) {
							if(x < i + K && x >= 0) {
								int len = K - 1 - Math.abs(i - x);
								int y1 = j - len;
								
								if(y1 >= 0 && y1 < N) {
									int val = map[x][y1];
									if(val == 1) {
										houseCnt++;
									}
								}
								
								int y2 = j + len;
								if(y1 != y2 && y2 >= 0 && y2 < N) {
									int val = map[x][y2];
									if(val == 1) {
										houseCnt++;
									}
								}
							}
						}
						if(houseCnt > 0) {
							int opPrice = K * K + (K - 1) * (K - 1);
							int price = houseCnt * M;
							if(price - opPrice >= 0) {
								if(resultHouse < houseCnt) {
									resultHouse = houseCnt;
									if(resultHouse >= totalHouseCnt) {
										isEnd = true;
										break;
									}
								}
							}
						}
					}
					if(isEnd) {
						break;
					}
				}
				if(isEnd) {
					break;
				}
			}
			
			System.out.println("#" + tc + " " + resultHouse);
		}
	}
	
}
