package swea.ability;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * SW Expert Academy Problem Solving<br/>
 * 2115. 벌꿀채취
 */
public class Solution2115 {
	private static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			arr = new int[M];
			
			map = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			totalResult = 0;
			cache.clear();
			for(int ax = 0; ax < N; ax++) {
				for(int ay = 0; ay <= N - M; ay++) {
					for(int bx = ax; bx < N; bx++) {
						int startBy = ax == bx ? ay + M : 0;
						for(int by = startBy; by <= N - M; by++) {
							run(ax, ay, bx, by);
						}
					}
				}
			}
			
			System.out.println("#" + tc + " " + totalResult);
		}
	}
	
	static int totalResult = 0;
	
	static void run(int ax, int ay, int bx, int by) {
		int result = 0;
		
		int idx = 0;
		int[] subMap = map[ax];
		for(int i = ay; i < ay + M; i++) {
			arr[idx++] = subMap[i];
		}
		maxScore = 0;
		String key = getKey();
		Integer value = cache.get(key);
		if(value != null) {
			maxScore = value;
		} else {
			dfs(0);
			cache.put(key, maxScore);
		}
		result += maxScore;
		
		idx = 0;
		subMap = map[bx];
		for(int i = by; i < by + M; i++) {
			arr[idx++] = subMap[i];
		}
		maxScore = 0;
		key = getKey();
		value = cache.get(key);
		if(value != null) {
			maxScore = value;
		} else {
			dfs(0);
			cache.put(key, maxScore);
		}
		result += maxScore;
		
		if(totalResult < result) {
			totalResult = result;
		}
	}
	
	private static String getKey() {
		Arrays.sort(arr);
		StringBuilder sb = new StringBuilder();
		for(int val : arr) {
			sb.append(val);
		}
		return sb.toString();
	}
	
	static int[] arr;
	static int maxScore;
	static int totalScore;
	static int totalCnt;
	private static int C;
	private static int[][] map;
	
	static Map<String, Integer> cache = new HashMap<>();
	static void dfs(int idx) {
		if(idx >= arr.length) {
			if(maxScore < totalScore) {
				maxScore = totalScore;
			}
			return;
		}
		
		dfs(idx + 1);
		
		int val = arr[idx];
		totalCnt += val;
		if(totalCnt <= C) {
			int powVal = val * val;
			
			totalScore += powVal;
			
			dfs(idx + 1);
			
			totalScore -= powVal;
		}
		totalCnt -= val;
	}
	
}
