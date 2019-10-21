package swea.ability;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SW Expert Academy Problem Solving<br/>
 * 5656. 벽돌 깨기
 */
public class Solution5656 {
	private static int N;
	private static int W;
	private static int[][] map;
	private static int[][] currMap;
	private static int[] idxes;
	private static int H;
	private static int[] dx = {-1, 0, 1, 0};
	private static int[] dy = {0, 1, 0, -1};
	private static int totalResult;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			currMap = new int[H][W];
			for(int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			idxes = new int[N];
			totalResult = Integer.MAX_VALUE;
			dfs(0);
			
			System.out.println("#" + tc + " " + totalResult);
		}
	}
	
	static void dfs(int cnt) {
		if(cnt >= N) {
			for(int i = 0; i < H; i++) {
				System.arraycopy(map[i], 0, currMap[i], 0, W);
			}
			
			for(int i = 0; i < N; i++) {
				int currIdx = idxes[i];
				for(int ii = 0; ii < H; ii++) {
					int val = currMap[ii][currIdx];
					if(val > 0) {
						bumb(ii, currIdx);
						
						for(int b = 0; b < W; b++) {
							int emptyIIdx = -1;
							int emptyJIdx = -1;
							for(int a = H - 1; a >= 0; a--) {
								if(currMap[a][b] == 0) {
									if(emptyIIdx == -1 && emptyJIdx == -1) {
										emptyIIdx = a;
										emptyJIdx = b;
									}
								} else {
									if(emptyIIdx >= 0 && emptyJIdx >= 0) {
										currMap[emptyIIdx][emptyJIdx] = currMap[a][b];
										currMap[a][b] = 0;
										a = emptyIIdx;
										emptyIIdx = -1;
										emptyJIdx = -1;
									}
								}
							}
						}
						
						break;
					}
				}
			}
			
			int result = 0;
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					if(currMap[i][j] > 0) {
						result++;
					}
				}
			}
			if(totalResult > result) {
				totalResult = result;
			}
			return;
		}
		
		for(int i = 0; i < W; i++) {
			idxes[cnt] = i;
			dfs(cnt + 1);
		}
	}
	
	static void bumb(int i, int j) {
		int val = currMap[i][j];
		if(val > 0) {
			currMap[i][j] = 0;
			if(val > 1) {
				for(int ii = 0; ii < 4; ii++) {
					int nextI = i;
					int nextJ = j;
					for(int iii = 1; iii < val; iii++) {
						nextI += dx[ii];
						nextJ += dy[ii];
						if(nextI >= 0 && nextI < H && nextJ >= 0 && nextJ < W) {
							bumb(nextI, nextJ);
						} else {
							break;
						}
					}
				}
			}
		}
	}
}
