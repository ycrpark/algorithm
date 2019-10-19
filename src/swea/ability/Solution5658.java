package swea.ability;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * SW Expert Academy Problem Solving<br/>
 * 5658. 보물상자 비밀번호
 */
public class Solution5658 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int numCount = Integer.parseInt(st.nextToken());
			int resultIdx = Integer.parseInt(st.nextToken()) - 1;
			char[] nums = br.readLine().toCharArray();
			
			Set<Integer> resultNums = new HashSet<>();
			int wordLength = numCount / 4;
			for(int i = 0; i < wordLength; i++) {
				for(int j = 0; j < 4; j++) {
					int startIdx = j * wordLength + i;
					int endIdx = startIdx + wordLength;
					if(endIdx > numCount) {
						resultNums.add(Integer.parseInt(String.valueOf(Arrays.copyOfRange(nums, startIdx, numCount)) + String.valueOf(Arrays.copyOfRange(nums, 0, i)), 16));
					} else {
						resultNums.add(Integer.parseInt(String.valueOf(Arrays.copyOfRange(nums, startIdx, endIdx)), 16));
					}
				}
			}
			
			System.out.println("#" + tc + " " + resultNums.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()).get(resultIdx));
		}
	}
	
}
