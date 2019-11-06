package swea.ability;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SW Expert Academy Problem Solving<br/>
 * 4014. 활주로 건설
 */
public class Solution4014 {
	private static int N;
	private static int X;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int result = 0;
			for(int i = 0; i < N; i++) {
				if(check(map[i])) {
					result++;
				}
			}
			
			int[] arr = new int[N];
			for(int j = 0; j < N; j++) {
				for(int i = 0; i < N; i++) {
					arr[i] = map[i][j];
				}
				if(check(arr)) {
					result++;
				}
			}
			
			
			System.out.println("#" + tc + " " + result);
		}
	}
	
	static boolean check(int[] arr) {
		boolean valid = true;
		int beforeVal = 0;
		int beforeSize = 0;
		boolean[] visit = new boolean[N];
		for(int j = 0; j < N; j++) {
			int val = arr[j];
			if(beforeVal == 0) {
				beforeVal = val;
				beforeSize = 1;
			} else if(beforeVal == val) {
				beforeSize++;
			} else {
				if(beforeVal < val) {
					if(val - beforeVal == 1) {
						if(beforeSize >= X) {
							for(int ii = j - 1; ii >= j - X; ii--) {
								if(visit[ii]) {
									valid = false;
									break;
								}
								visit[ii] = true;
							}
							if(!valid) {
								break;
							}
						} else {
							valid = false;
							break;
						}
					} else {
						valid = false;
						break;
					}
				}
				beforeVal = val;
				beforeSize = 1;
			}
		}
		
		if(valid) {
			beforeVal = 0;
			beforeSize = 0;
			for(int j = N - 1; j >= 0; j--) {
				int val = arr[j];
				if(beforeVal == 0) {
					beforeVal = val;
					beforeSize = 1;
				} else if(beforeVal == val) {
					beforeSize++;
				} else {
					if(beforeVal < val) {
						if(val - beforeVal == 1) {
							if(beforeSize >= X) {
								for(int ii = j + 1; ii <= j + X; ii++) {
									if(visit[ii]) {
										valid = false;
										break;
									}
									visit[ii] = true;
								}
								if(!valid) {
									break;
								}
							} else {
								valid = false;
								break;
							}
						} else {
							valid = false;
							break;
						}
					}
					beforeVal = val;
					beforeSize = 1;
				}
			}
		}
		
		return valid;
	}
}
