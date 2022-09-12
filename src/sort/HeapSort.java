package sort;

import java.util.Arrays;
import java.util.PriorityQueue;

// 시간 복잡도 : O(nlogn)
// 완전 이진 트리
public class HeapSort {
    public static void main(String[] args) {
        int[] output = heapSort(new int[]{3, 1, 21});
        System.out.println(Arrays.toString(output)); // --> [1, 3, 21]
    }

    public static int[] heapSort(int[] arr) {
        // 힙 정렬에 사용될 힙(우선순위 큐) 선언
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>();

//        높은 숫자가 우선 순위인 int 형 우선순위 큐 선언
//        PriorityQueue<Integer> priorityQueueHighest = new PriorityQueue<>(Collections.reverseOrder());

        // 배열의 값을 힙에 모두 할당
        for(int i = 0; i < arr.length; i++) {
            heap.add(arr[i]);
        }

        // 힙에서 우선순위가 가장 높은 원소(root노드)를 하나씩 배열에 할당
        for(int i = 0; i < arr.length; i++) {
            arr[i] = heap.poll();
        }

        return arr;
    }


}
