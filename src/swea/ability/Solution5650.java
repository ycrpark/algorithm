package swea.ability;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * SW Expert Academy Problem Solving<br/>
 * 5650. 핀볼 게임
 */
public class Solution5650 {
	static int x = 0;
	static int y = 0;
	static int score = 0;
	static int way = 0;
	static int result = 0;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	private static int[][] map;
	
	static int i = 0;
	static int j = 0;
	private static int N;
	private static Map<Integer, Integer> worm = null;
	private static int[][] blockWay = {{},
			{2, 3, 1, 0},
			{1, 3, 0, 2},
			{3, 2, 0, 1},
			{2, 0, 3, 1},
			{2, 3, 0, 1}};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int t = Integer.parseInt(st.nextToken());
		for(int tc = 1; tc <= t; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// set wormhole
			worm = new HashMap<>();
			Map<Integer, Integer> tempWorm = new HashMap<>();
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					int value = map[i][j];
					if(value > 5) {
						if(tempWorm.get(value) == null) {
							tempWorm.put(value, i * N + j);
						} else {
							worm.put(i * N + j, tempWorm.get(value));
							worm.put(tempWorm.get(value), i * N + j);
						}
					}
				}
			}
			
			result = 0;
			
			for(i = 0; i < N; i++) {
				for(j = 0; j < N; j++) {
					if(map[i][j] != 0) {
						continue;
					}
					for(int w = 0; w < 4; w++) {
						x = i;
						y = j;
						score = 0;
						way = w;
						dfs();
					}
				}
			}
			
			
			System.out.println("#" + tc + " " + result);
		}
	}
	
	public static void dfs() {
		int nextX = x + dx[way];
		int nextY = y + dy[way];
		
		// wall
		if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) {
			way = (way + 2) % 4;
			score++;
			x = nextX;
			y = nextY;
			dfs();
			return;
		}
		
		// end
		if((map[nextX][nextY] == -1 || nextX == i && nextY == j)) {
			if(result < score) {
				result = score;
			}
			return;
		}
		
		// wormhole
		int nextValue = map[nextX][nextY];
		if(nextValue > 5) {
			int nextLoc = worm.get(nextX * N + nextY);
			x = nextLoc / N;
			y = nextLoc % N;
			dfs();
			return;
		}
		
		// space
		if(nextValue == 0) {
			x = nextX;
			y = nextY;
			dfs();
			return;
		}
		
		// block
		way = blockWay[nextValue][way];
		x = nextX;
		y = nextY;
		score++;
		dfs();
	}
}
