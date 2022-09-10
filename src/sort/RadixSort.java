package sort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.IntStream;

// O(n) 정렬 알고리즘은 비교를 하지 않음
// 기수 정렬은 내부적으로 계수 정렬(Counting Sort) 사용
public class RadixSort {
    public static void main(String[] args) {
        int[] output = radixSortWithCountingSort(new int[]{3, 1, 21});
        System.out.println(Arrays.toString(output)); // --> [1, 3, 21]
    }

    // 시간 복잡도 : O(dn)
    // d는 데이터의 자릿수
    private static int[] radixSort(int[] arr) {
        Queue<Integer>[] bucket = new LinkedList[10];
        for(int i = 0; i < 10; i++) {
            bucket[i] = new LinkedList<>();
        }

        int factor = 1;
        int max = Arrays.stream(arr).max().getAsInt();
        // 최댓값 자릿수 크기 만큼 반복
        for(int digit = 1; max / digit >= 1; digit *= 10) {

            for (int i = 0; i < arr.length; i++) {
                bucket[(arr[i] / factor) % 10].add(arr[i]);
            }

            for (int i = 0, j = 0; i < 10; i++) {
                while (!bucket[i].isEmpty()) {
                    arr[j++] = bucket[i].poll();
                }
            }

            factor *= 10;
        }

        return arr;
    }

    public static int[] radixSortWithCountingSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();

        for(int digit = 1; digit <= max; digit *= 10) {
            arr = countingSort(arr, digit);
        }

        return arr;
    }

    public static int[] countingSort(int[] arr, int digit) {
        int[] result = new int[arr.length]; // 결과 배열 생성
        int[] counting = new int[10]; // 카운팅 배열

        for (int i = 0; i < arr.length; i++) {
            int num = (arr[i] / digit) % 10; // 해당 자리수의 숫자 추출
            counting[num]++;
        }

        // 누적합
        for (int i = 1; i < counting.length; i++) {
            counting[i] += counting[i - 1];
        }

        // stable
        for (int i = arr.length - 1; i >= 0; i--) {
            // 현재 배열 자릿수 구하기
            int num = (arr[i] / digit) % 10;
            // 해당 자릿수를 인덱스로 counting 배열 요소를 1 빼서 결과 배열에 삽입
            result[--counting[num]] = arr[i];
        }

        return result;
    }
}
