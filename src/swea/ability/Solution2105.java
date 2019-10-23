package swea.ability;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SW Expert Academy Problem Solving<br/>
 * 2105. 디저트 카페
 */
public class Solution2105 {
	private static boolean[][] visit;
	private static int[][] map;
	private static int N;
	private static int startA;
	private static int startB;
	private static int result;
	private static int cnt;
	private static int way;
	private static final int[] wayA = {1, 1, -1, -1};
	private static final int[] wayB = {1, -1, -1, 1};
	private static boolean[] set = new boolean[101];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			map = new int[N][N];
			visit = new boolean[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			result = 0;
			for(int i = 0; i < N - 2; i++) {
				for(int j = 1; j < N - 1; j++) {
					cnt = 0;
					way = 0;
					startA = i;
					startB = j;
					set = new boolean[101];
					dfs(i, j);
				}
			}
			
			System.out.println("#" + tc + " " + (result > 0 ? result : -1));
		}
	}
	
	private static void dfs(int a, int b) {
		if(cnt > 0 && startA == a && startB == b) {
			if(result < cnt) {
				result = cnt;
			}
			return;
		}
		
		int val = map[a][b];
		if(set[val]) {
			return;
		}
		
		set[val] = true;
		cnt++;
		visit[a][b] = true;
		
		int nextA = a + wayA[way];
		int nextB = b + wayB[way];
		if(nextA >= 0 && nextA < N && nextB >= 0 && nextB < N) {
			dfs(nextA, nextB);
		}
		
		if(cnt > 1 && way < 3) {
			way++;
			nextA = a + wayA[way];
			nextB = b + wayB[way];
			if(nextA >= 0 && nextA < N && nextB >= 0 && nextB < N) {
				dfs(nextA, nextB);
			}
			way--;
		}
		
		visit[a][b] = false;
		cnt--;
		set[val] = false;
	}
}
