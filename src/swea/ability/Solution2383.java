package swea.ability;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SW Expert Academy Problem Solving<br/>
 * 2383. 점심 식사시간
 */
public class Solution2383 {
	private static int infoCnt;
	private static List<Info1> infos;
	private static Queue<Info1> queue1;
	private static Queue<Info1> queue2;
	private static int hole1x;
	private static int hole1y;
	private static int hole2x;
	private static int hole2y;
	private static int hole1Len;
	private static int hole2Len;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			infos = new ArrayList<>();
			hole1x = -1;
			hole1y = 0;
			hole1Len = 0;
			queue1 = new PriorityQueue<>((info1, info2) -> info1.time - info2.time);
			hole2x = 0;
			hole2y = 0;
			hole2Len = 0;
			queue2 = new PriorityQueue<>((info1, info2) -> info1.time - info2.time);
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					int value = Integer.parseInt(st.nextToken());
					if(value == 1) {
						infos.add(new Info1(i, j));
					} else if(value > 1) {
						if(hole1x == -1) {
							hole1x = i;
							hole1y = j;
							hole1Len = value;
						} else {
							hole2x = i;
							hole2y = j;
							hole2Len = value;
						}
					}
				}
			}
			
			infoCnt = infos.size();
			System.out.println("#" + tc + " " + dfs(0));
		}
	}
	
	private static int dfs(int idx) {
		if(idx >= infoCnt) {
			for(Info1 info : infos) {
				if(info.hole == 1) {
					info.time = Math.abs(info.x - hole1x) + Math.abs(info.y - hole1y);
					queue1.offer(info);
				} else {
					info.time = Math.abs(info.x - hole2x) + Math.abs(info.y - hole2y);
					queue2.offer(info);
				}
			}
			Queue<Info1> stair1 = new LinkedList<>();
			Queue<Info1> stair2 = new LinkedList<>();
			int time = 1;
			int endCnt = 0;
			while(true) {
				endCnt += checkStair(stair1, time);
				endCnt += checkStair(stair2, time);
				
				if(endCnt >= infoCnt) {
					break;
				}
				
				checkQueue(queue1, stair1, time, hole1Len);
				checkQueue(queue2, stair2, time, hole2Len);
				time++;
			}
			return time;
		}
		Info1 info = infos.get(idx);
		info.hole = 1;
		int time1 = dfs(idx + 1);
		
		info.hole = 2;
		int time2 = dfs(idx + 1);
		return Math.min(time1, time2);
	}
	
	private static int checkStair(Queue<Info1> stair, int time) {
		Info1 info = stair.peek();
		if(info != null && info.time <= time) {
			stair.poll();
			return 1 + checkStair(stair, time);
		}
		return 0;
	}
	
	private static void checkQueue(Queue<Info1> queue, Queue<Info1> stair, int time, int holeLen) {
		Info1 info = queue.peek();
		if(info != null && time >= info.time && stair.size() < 3) {
			info = queue.poll();
			info.time = time + holeLen + (time == info.time ? 1 : 0);
			stair.offer(info);
			checkQueue(queue, stair, time, holeLen);
		}
	}
	
}
class Info1 {
	int x;
	int y;
	int time;
	int hole;
	public Info1(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
