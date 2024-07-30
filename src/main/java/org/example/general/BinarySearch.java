package org.example.general;

public class BinarySearch {

    // 이진 탐색 : 같은 원소가 여러개인 경우 가장 왼쪽에 있는 원소를 반환
    public int case1(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    // 이진 탐색 : 같은 원소가 여러개인 경우 가장 오른쪽에 있는 원소를 반환
    public int case2(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    private int case3(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] == target) {
                right = mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    private int case4(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            if((left + right) % 2 == 1) mid++;

            if (arr[mid] == target) {
                left = mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();

        // case 1
        int[] arr = {1, 1, 7, 7, 7, 7, 7, 7, 9};
        int target = 7;
        int case1 = binarySearch.case1(arr, target);
        System.out.println("case1 : " + (case1 == 2) + " case1 : " + case1);

        // case 2
        int case2 = binarySearch.case2(arr, target);
        System.out.println("case2 : " + (case2 == 7) + " case2 : " + case2);

        // case3 : target이 없는 경우 자신보다 작은 값 중 제일 큰 값을 반환
        int case3 = binarySearch.case2(arr, 6);
        System.out.println("case3 : " + (case3 == 1) + " case3 : " + case3);

        // case4 : target이 있다면 가장 작은 타켓을 없다면 자신보다 작은 값 중에서 가장 큰 타켓을 반환
        int case4 = binarySearch.case3(arr, 7);
        System.out.println("case4 : " + (case4 == 2) + " case4 : " + case4);

        int case4_1 = binarySearch.case3(arr, 6);
        System.out.println("case4_1 : " + (case4_1 == 1) + " case4_1 : " + case4_1);

        // case5 : target이 있다면 가장 큰 타켓을 없다면 자신보다 큰 값 중에서 가장 작은 타켓을 반환
        int case5 = binarySearch.case4(arr, 7);
        System.out.println("case5 : " + (case5 == 7) + " case5 : " + case5);

        int case5_1 = binarySearch.case4(arr, 8);
        System.out.println("case5_1 : " + (case5_1 == 8) + " case5_1 : " + case5_1);
    }

}
