package swea.ability;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SW Expert Academy Problem Solving<br/>
 * 4008. 숫자 만들기
 */
public class Solution4008 {
	private static int N;
	private static int[] resources;
	private static int[] nums;
	private static int[] uses;
	private static int max;
	private static int min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			resources = new int[4];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 4; i++) {
				resources[i] = Integer.parseInt(st.nextToken());
			}
			
			nums = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			uses = new int[N - 1];
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			dfs(0);
			
			System.out.println("#" + tc + " " + Math.abs(max - min));
		}
	}
	
	static void dfs(int idx) {
		if(idx > N - 2) {
			int result = nums[0];
			for(int i = 0; i < N - 1; i++) {
				int use = uses[i];
				int nextNum = nums[i + 1];
				if(use == 0) {
					result += nextNum;
				} else if(use == 1) {
					result -= nextNum;
				} else if(use == 2) {
					result *= nextNum;
				} else {
					result /= nextNum;
				}
			}
			
			if(result > max) {
				max = result;
			}
			if(result < min) {
				min = result;
			}
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int resourceCnt = resources[i];
			if(resourceCnt > 0) {
				resources[i]--;
				uses[idx] = i;
				dfs(idx + 1);
				resources[i]++;
			}
		}
	}
}
