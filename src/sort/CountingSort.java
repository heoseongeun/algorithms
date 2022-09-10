package sort;

import java.util.Arrays;

/**
 * O(n) 정렬 알고리즘은 비교를 하지 않음
 *
 * 조건 1. 0 이상의 값만 가능 (배열의 인덱스가 양수만 존재)
 * 조건 2. 값의 범위가 크지 않아야 함 (메모리 사이즈 고려)
 *
 * 배열 [3,3,1,2] 에서 각 숫자의 개수를 계산한 배열은 [0,1,1,2] 이다.
 * 이를 토대로 개수에 맞춰 나열하면, [1,2,3,3] 이 된다.
 *
 * ex) 수능 시험 성적 정렬
 */
public class CountingSort {
    public static void main(String[] args) {
        int[] output = countingSort(new int[]{3, 1, 21});
        System.out.println(Arrays.toString(output)); // --> [1, 3, 21]
    }

    public static int[] countingSort(int[] arr) {
        // 입력 배열의 최댓값 + 1의 크기를 가진 counting array 생성
        // 최댓값 찾는 과정에서 O(n)
        int[] countArr = new int[Arrays.stream(arr).max().getAsInt() + 1];

        // countArr 에 입력 배열의 각 숫자 개수 계산
        for(int i: arr) countArr[i]++;

        // countingArray 를 누적합 배열로 변경
        for(int i = 1; i < countArr.length; i++) {
            countArr[i] += countArr[i - 1];
        }

        // 입력 배열과 동일한 크기의 결과 배열 생성
        int[] result = new int[arr.length];

        // 입력 배열과 누적합 배열을 이용한 정렬 수행
        // stable 유지를 위해 맨 마지막 값부터 채우기
        // stable? [2, 1, 3, 1]에서 처음과 두번 째의 1이 순서 유지
        for(int i = arr.length - 1; i >= 0; i--) {
            int data = arr[i];
            result[countArr[data] - 1] = data;
            countArr[data]--;
        }

        // unstable
//        for(int i: arr) {
//            result[countArr[i] - 1] = i;
//            countArr[i]--;
//        }

        return result;
    }

}
