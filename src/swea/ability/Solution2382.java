package swea.ability;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SW Expert Academy Problem Solving<br/>
 * 2382. 미생물 격리
 */
public class Solution2382 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] dx = {0, -1, 1, 0, 0};
		int[] dy = {0, 0, 0, -1, 1};
		int[] dt = {0, 2, 1, 4, 3};
		
		int T = read(st);
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = read(st); // 셀의 개수
			int M = read(st); // 격리 시간
			int K = read(st); // 군집 개수
			int total = N * N;
			
			Info[] map = new Info[total];
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				map[read(st) * N + read(st)] = new Info(read(st), read(st));
			}
			
			for(int time = 1; time <= M; time++) {
				Info[] tempMap = new Info[total];
				for(int i = 0; i < total; i++) {
					Info info = map[i];
					if(info != null) {
						info.maxCnt = info.cnt;
						int nextI = i / N + dx[info.way];
						int nextJ = i % N + dy[info.way];
						// 끝 지점 도달
						if(nextI < 1 || nextJ < 1 || nextI > N - 2 || nextJ > N - 2) {
							info.cnt /= 2;
							info.way = dt[info.way];
							tempMap[nextI * N + nextJ] = info;
						} else {
							Info tempInfo = tempMap[nextI * N + nextJ];
							if(tempInfo == null) {
								tempMap[nextI * N + nextJ] = info;
							} else {
								// 겹치면
								if(info.maxCnt > tempInfo.maxCnt) {
									info.cnt += tempInfo.cnt;
									tempMap[nextI * N + nextJ] = info;
								} else {
									tempInfo.cnt += info.cnt;
								}
							}
						}
					}
				}
				map = tempMap;
			}
			int result = 0;
			for(int i = 0; i < total; i++) {
				Info info = map[i];
				if(info != null) {
					result += info.cnt;
				}
			}
			System.out.println("#" + tc + " " + result);
		}
	}
	
	static class Info {
		int cnt;
		int maxCnt;
		int way;
		public Info(int cnt, int way) {
			this.cnt = cnt;
			this.way = way;
		}
	}
	
	private static int read(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}
