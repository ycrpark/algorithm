package swea.ability;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1949 {
	
	private static int result;
	private static int tempResult;
	private static boolean[][] visit;
	private static int N;
	private static int[] nextI = {-1, 0, 1, 0};
	private static int[] nextJ = {0, 1, 0, -1};
	private static int[][] map;
	private static int K;
	private static boolean useK = false;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int tcCnt = Integer.parseInt(st.nextToken());
		for(int tcNo = 1; tcNo <= tcCnt; tcNo++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int max = 0;
			map = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					int curr = Integer.parseInt(st.nextToken());
					map[i][j] = curr;
					if(curr > max) {
						max = curr;
					}
				}
			}
			
			result = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == max) {
						tempResult = 1;
						visit = new boolean[N][N];
						visit[i][j] = true;
						
						dfs(i, j);
						
						visit[i][j] = false;
					}
				}
			}
			
			System.out.println("#" + tcNo + " " + result);
		}
	}
	
	private static void dfs(int i, int j) {
		int currVal = map[i][j];
		for(int z = 0; z < 4; z++) {
			int ni = i + nextI[z];
			int nj = j + nextJ[z];
			
			boolean goNext = false;
			if(ni >= 0 && ni < N && nj >= 0 && nj < N && !visit[ni][nj]) {
				int nextVal = map[ni][nj];
				boolean currUseK = false;
				if(nextVal < currVal) {
					goNext = true;
				} else if(!useK && nextVal - K < currVal) {
					currUseK = true;
					goNext = true;
				}
				
				if(goNext) {
					goNext = true;
					visit[ni][nj] = true;
					tempResult++;
					
					int originNextVal = 0;
					if(currUseK) {
						useK = true;
						originNextVal = nextVal;
						map[ni][nj] = currVal - 1;
					}
					
					dfs(ni, nj);
					
					if(currUseK) {
						visit[ni][nj] = false;
						map[ni][nj] = originNextVal;
						useK = false;
					}
					
					tempResult--;
					visit[ni][nj] = false;
				}
			}
			if(!goNext) {
				if(tempResult > result) {
					result = tempResult;
				}
			}
		}
	}
	
}
