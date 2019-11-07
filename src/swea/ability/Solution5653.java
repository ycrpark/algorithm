package swea.ability;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * SW Expert Academy Problem Solving<br/>
 * 5653. 줄기세포배양
 */
public class Solution5653 {
	private static Info3[][] infos;
	private static Info3[][] tempInfos;
	private static int[] dx = {-1, 0, 1, 0};
	private static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			infos = new Info3[350][350];
			int currTime = 0;
			for(int i = 150; i < 150+N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 150; j < 150+M ; j++) {
					int val = Integer.parseInt(st.nextToken());
					if(val > 0) {
						Info3 info = new Info3(i, j, currTime, val);
						infos[i][j] = info;
					}
				}
			}
			
			for(currTime++; currTime <= K; currTime++) {
				tempInfos = new Info3[350][350];
				for(Info3[] rowInfos : infos) {
					for(Info3 info : rowInfos) {
						if(info != null && info.live) {
							if(currTime >= info.deadTime) {
								info.live = false;
							}
							
							if(!info.actived && currTime > info.activeTime) {
								info.actived = true;
								for(int i = 0; i < 4; i++) {
									int nextX = info.x + dx[i];
									int nextY = info.y + dy[i];
									
									Info3 nextInfo = infos[nextX][nextY];
									if(nextInfo == null) {
										Info3 tempNextInfo = tempInfos[nextX][nextY];
										if(tempNextInfo == null || tempNextInfo.life < info.life) {
											tempInfos[nextX][nextY] = new Info3(nextX, nextY, currTime, info.life);
										}
									}
								}
							}
						}
					}
				}
				for(Info3[] rowInfos : tempInfos) {
					for(Info3 info : rowInfos) {
						if(info != null) {
							infos[info.x][info.y] = info;
						}
					}
				}
				
			}
			
			int result = 0;
			for(Info3[] rowInfos : infos) {
				for(Info3 info : rowInfos) {
					if(info != null && info.live) {
						result++;
					}
				}
			}
			
			System.out.println("#" + tc + " " + result);
		}
		
		
	}
	
	static Info getInfo(Map<Integer, Map<Integer, Info>> infos, int i, int j) {
		Map<Integer, Info> rowInfos = infos.get(i);
		if(rowInfos == null) {
			return null;
		} else {
			return rowInfos.get(j);
		}
	}
	
	static void putInfo(Map<Integer, Map<Integer, Info>> infos, int i, int j, Info info) {
		Map<Integer, Info> rowInfos = infos.get(i);
		if(rowInfos == null) {
			rowInfos = new HashMap<>();
			infos.put(i, rowInfos);
		}
		rowInfos.put(j, info);
	}
	
}

class Info3 {
	boolean live;
	int createTime;
	int activeTime;
	int deadTime;
	int life;
	int x;
	int y;
	boolean actived;
	
	public Info3(int x, int y, int currTime, int life) {
		live = true;
		createTime = currTime;
		activeTime = currTime + life;
		deadTime = currTime + life * 2;
		this.life = life;
		this.x = x;
		this.y = y;
	}
}