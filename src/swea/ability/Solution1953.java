package swea.ability;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SW Expert Academy Problem Solving<br/>
 * 1953. 탈주범 검거
 */
public class Solution1953 {
	public static void main(String[] args) throws IOException {
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		boolean[][] pipes = {{},
				{true, true, true, true}, // 1
				{true, false, true, false}, // 2
				{false, true, false, true}, // 3
				{true, true, false, false}, // 4
				{false, true, true, false}, // 5
				{false, false, true, true}, // 6
				{true, false, false, true}}; // 7
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			// 세로 크기
			int N = Integer.parseInt(st.nextToken());
			// 가로 크기
			int M = Integer.parseInt(st.nextToken());
			// 멘홀 세로 위치
			int R = Integer.parseInt(st.nextToken());
			// 멘홀 가로 위치
			int C = Integer.parseInt(st.nextToken());
			// 소요 시간
			int L = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][M];
			boolean[][] visits = new boolean[N][M];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					int value = Integer.parseInt(st.nextToken());
					if(value > 0) {
						map[i][j] = value;
					}
				}
			}
			
			Queue<Info> queue = new LinkedList<>();
			Info startInfo = new Info(R, C, 1);
			visits[startInfo.x][startInfo.y] = true;
			queue.add(startInfo);
			
			int result = 0;
			// bfs
			while(!queue.isEmpty()) {
				Info info = queue.poll();
				result++;
				
				if(info.time < L) {
					int pipeNo = map[info.x][info.y];
					for(int z = 0; z < 4; z++) {
						if(pipes[pipeNo][z]) {
							int nextX = info.x + dx[z];
							int nextY = info.y + dy[z];
							if(nextX >= 0 && nextY >= 0 && nextX < N && nextY < M && map[nextX][nextY] > 0 && !visits[nextX][nextY]) {
								int nextPipeNo = map[nextX][nextY];
								if(pipes[nextPipeNo][(z + 2) % 4]) {
									Info nextInfo = new Info(nextX, nextY, info.time + 1);
									visits[nextInfo.x][nextInfo.y] = true;
									queue.add(nextInfo);
								}
							}
						}
					}
				}
			}
			
			System.out.println("#" + tc + " " + result);
		}
	}
}

class Info {
	int x;
	int y;
	int time;
	public Info(int x, int y, int time) {
		this.x = x;
		this.y = y;
		this.time = time;
	}
}
