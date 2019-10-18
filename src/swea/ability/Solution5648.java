package swea.ability;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * SW Expert Academy Problem Solving<br/>
 * 5648. 원자 소멸 시뮬레이션
 */
public class Solution5648 {
	public static void main(String[] args) throws IOException {
		int[] dx = {0, 0, -1, 1};
		int[] dy = {1, -1, 0, 0};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= t; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			Map<Integer, Info2> map = new HashMap<>();
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				Info2 info = new Info2();
				info.x = Integer.parseInt(st.nextToken()) * 2 + 2000;
				info.y = Integer.parseInt(st.nextToken()) * 2 + 2000;
				info.way = Integer.parseInt(st.nextToken());
				info.energy = Integer.parseInt(st.nextToken());
				
				map.put(info.getKey(), info);
			}
			
			int score = 0;
			Map<Integer, Info2> tempMap = null;
			Set<Integer> exists = null;
			for(int i = 0; i < 4000; i++) {
				tempMap = new HashMap<>();
				exists = new HashSet<>();
				for(Info2 info : map.values()) {
					info.x = info.x + dx[info.way];
					info.y = info.y + dy[info.way];
					if(info.x < 0 || info.y < 0 || info.x > 4000 || info.y > 4000) {
						continue;
					}
					
					int key = info.getKey();
					if(exists.contains(key)) {
						score += info.energy;
						Info2 origin = tempMap.get(key);
						if(origin != null) {
							tempMap.remove(key);
							score += origin.energy;
						}
					} else {
						exists.add(key);
						tempMap.put(key, info);
					}
				}
				
				if(tempMap.isEmpty()) {
					break;
				}
				
				map = tempMap;
			}
			System.out.println("#" + tc + " " + score);
		}
	}
}

class Info2 {
	int x;
	int y;
	int way;
	int energy;
	
	public int getKey() {
		return x * 4001 + y;
	}
}