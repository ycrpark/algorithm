package swea.ability;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SW Expert Academy Problem Solving
 * 1949. 등산로 조성
 */
public class Solution1949 {
	private static int[] di = {-1, 0, 1, 0};
	private static int[] dj = {0, 1, 0, -1};
	private static int N;
	private static int K;
	private static boolean useK = false;
	private static int[][] map;
	private static boolean[][] visits;
	private static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			// 맵 크기
			N = Integer.parseInt(st.nextToken());
			// 깍을수있는 높이
			K = Integer.parseInt(st.nextToken());
			
			// 시작 높이
			int maxHeight = 0;
			map = new int[N][N];
			visits = new boolean[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					int height = Integer.parseInt(st.nextToken());
					 map[i][j] = height;
					if(height > maxHeight) {
						maxHeight = height;
					}
				}
			}
			
			result = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == maxHeight) {
						dfs(i, j, 0);
					}
				}
			}
			
			System.out.println("#" + tc + " " + result);
		}
	}
	
	private static void dfs(int i, int j, int length) {
		visits[i][j] = true;
		length++;
		int height = map[i][j];
		// 종료 여부
		boolean last = true;
		for(int z = 0; z < 4; z++) {
			int nextI = i + di[z];
			int nextJ = j + dj[z];
			if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= N || visits[nextI][nextJ]) {
				continue;
			}
			
			int nextHeight = map[nextI][nextJ];
			
			// 다음 진행
			if(height > nextHeight) {
				dfs(nextI, nextJ, length);
				last = false;
			}
			
			// 높이 깎고 다음 진행
			if(!useK && height <= nextHeight && nextHeight - K < height) {
				useK = true;
				map[nextI][nextJ] = height - 1;
				dfs(nextI, nextJ, length);
				map[nextI][nextJ] = nextHeight;
				useK = false;
				last = false;
			}
			
		}
		
		// 종료 시
		if(last && result < length) {
			result = length;
		}
		length--;
		visits[i][j] = false;
	}
}
