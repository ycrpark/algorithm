package swea.ability;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SW Expert Academy Problem Solving<br/>
 * 4013. 특이한 자석
 */
public class Solution4013 {
	private static int[] points;
	private static int[][] maps;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		for(int tc = 1; tc <= T; tc++) {
			int K = Integer.parseInt(br.readLine());
			
			maps = new int[4][8];
			for(int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				int[] map = maps[i];
				for(int j = 0; j < 8; j++) {
					map[j] = Integer.parseInt(st.nextToken());
				}
			}
			
			points = new int[4];
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken()) - 1;
				int way = Integer.parseInt(st.nextToken());
				move(idx, way, 0);
			}
			
			int totalScore = 0;
			int score = 1;
			for(int i = 0; i < 4; i++) {
				if(maps[i][points[i]] == 1) {
					totalScore += score;
				}
				score *= 2;
			}
			
			System.out.println("#" + tc + " " + totalScore);
		}
	}
	
	static void move(int idx, int way, int next) {
		int point = points[idx];
		int leftVal = getVal(idx, false);
		int rightVal = getVal(idx, true);
		
		if(way == 1) {
			point--;
			if(point < 0) {
				point = 7;
			}
		} else {
			point++;
			if(point > 7) {
				point = 0;
			}
		}
		
		points[idx] = point;
		
		if(next < 1 && idx > 0) { // <<<
			int nextVal = getVal(idx - 1, true);
			if(leftVal != nextVal) {
				move(idx - 1, way * -1, -1);
			}
		}
		if(next > -1 && idx < 3) {
			int nextVal = getVal(idx + 1, false);
			if(rightVal != nextVal) {
				move(idx + 1, way * -1, 1);
			}
		}
	}
	
	static int getVal(int idx, boolean right) {
		int valIdx = points[idx] + (right ? 2 : 6);
		if(valIdx > 7) {
			valIdx -= 8;
		}
		return maps[idx][valIdx];
	}
}

