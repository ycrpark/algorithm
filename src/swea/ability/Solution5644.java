package swea.ability;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SW Expert Academy Problem Solving<br/>
 * 5644. 무선 충전
 */
public class Solution5644 {
	private static int[] dx = {0, 0, 1, 0, -1};
	private static int[] dy = {0, -1, 0, 1, 0};
	private static int[][] bcInfos;
	private static int bcCount;
	private static int ax;
	private static int ay;
	private static int bx;
	private static int by;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int moveCount = Integer.parseInt(st.nextToken());
			bcCount = Integer.parseInt(st.nextToken());
			
			int[] moveA = new int[moveCount];
			int[] moveB = new int[moveCount];
			bcInfos = new int[bcCount][4];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < moveCount; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < moveCount; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0; i < bcCount; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 4; j++) {
					bcInfos[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ax = 1;
			ay = 1;
			bx = 10;
			by = 10;
			int score = 0;
			
			for(int time = 0; time < moveCount; time++) {
				score += getScore();
				ax += dx[moveA[time]];
				ay += dy[moveA[time]];
				bx += dx[moveB[time]];
				by += dy[moveB[time]];
			}
			score += getScore();
			
			System.out.println("#" + tc + " " + score);
		}
	}
	
	public static int getScore() {
		int score = 0;
		for(int i = 0; i < bcCount; i++) {
			int bcIdxOfA = -1;
			if(contains(ax, ay, bcInfos[i])) {
				bcIdxOfA = i;
			}
			for(int j = 0; j < bcCount; j++) {
				int bcIdxOfB = -1;
				if(contains(bx, by, bcInfos[j])) {
					bcIdxOfB = j;
				}
				
				// bc 중복
				if(bcIdxOfA != -1 && bcIdxOfA == bcIdxOfB) {
					if(score < bcInfos[j][3]) {
						score = bcInfos[j][3];
					}
				} else {
					int tempScore = 0;
					if(bcIdxOfA != -1) {
						tempScore += bcInfos[i][3];
					}
					if(bcIdxOfB != -1) {
						tempScore += bcInfos[j][3];
					}
					if(score < tempScore) {
						score = tempScore;
					}
				}
			}
		}
		return score;
	}
	
	public static boolean contains(int x, int y, int[] bc) {
		return bc[2] >= Math.abs(x - bc[0]) + Math.abs(y - bc[1]);
	}
}
