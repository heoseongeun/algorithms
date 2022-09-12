package sort;

import java.lang.reflect.Array;
import java.util.Arrays;

// 시간 복잡도 : O(nlogn)
public class MergeSort {
    public static void main(String[] args) {
        int[] output = mergeSort(new int[]{3, 1, 21});
        System.out.println(Arrays.toString(output)); // --> [1, 3, 21]
    }

    public static int[] mergeSort(int[] arr) {
        sort(0, arr.length - 1, arr, new int[arr.length]);
        return arr;
    }

    public static void sort(int start, int end, int[] arr, int[] temp) {
        if(start < end) {
            int mid = (start + end) / 2;
            sort(start, mid, arr, temp);
            sort(mid + 1, end, arr, temp);

            int left = start;
            int right = mid + 1;
            int idx = left;

            // 왼쪽 배열과 오른쪽 배열을 다 순회할 때까지
            while(left <= mid || right <= end) {
                if(right > end || (left <= mid && arr[left] < arr[right])) {
                    temp[idx++] = arr[left++];
                }
                else {
                    temp[idx++] = arr[right++];
                }
            }

            for(int i = start; i <= end; i++) {
                arr[i] = temp[i];
            }
        }
        return;
    }
}
