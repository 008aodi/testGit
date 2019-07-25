package util.sort;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author zhangpeng
 *  @author Administrator  牌序算法工具类
 */
public class UtilMySort {
    /**
     * 冒炮排序法
     * @param arr
     */
    public static void sortMaopao(int arr[]){
        for( int i = 0 ; i < arr.length - 1 ; i++ ){
            for(int j = 0;j < arr.length - 1 - i ; j++){
                int temp = 0;
                if(arr[j] < arr[j + 1]){
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 冒炮排序改进
     * @param arr
     */
    public static void sortMaoPaoYouHua(int arr[]){
        for( int i = 0;i < arr.length - 1 ; i++ ){
            //增加一个是否有序标识
            boolean isSort = true;
            for( int j = 0;j < arr.length - 1 - i ; j++ ){
                int temp = 0;
                if(arr[j] < arr[j + 1]){
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSort = false;
                }
            }
            if(isSort){
                break;
            }
        }
    }

    /**
     * 选择排序
     * @param arr
     */
    public static void sortXuanZe(int arr[]){
        for( int i = 0;i < arr.length ; i++ ){
            int min = i;//最小元素的下标
            for(int j = i + 1;j < arr.length ; j++ ){
                if(arr[j] < arr[min]){
                    min = j;//找最小值
                }
            }
            //交换位置
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }

    /**
     * 插入排序法
     * @param arr
     */
    public static void sortChaRu(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int value = arr[i];
            int j = 0;//插入的位置
            for (j = i-1; j >= 0; j--) {
                if (arr[j] > value) {
                    arr[j+1] = arr[j];//移动数据
                } else {
                    break;
                }
            }
            arr[j+1] = value; //插入数据
        }
    }

    /**
     * 希尔排序
     * @param arr
     */
    public static void sort(int[] arr) {
        int length = arr.length;
        //区间
        int gap = 1;
        while (gap < length) {
            gap = gap * 3 + 1;
        }
        while (gap > 0) {
            for (int i = gap; i < length; i++) {
                int tmp = arr[i];
                int j = i - gap;
                //跨区间排序
                while (j >= 0 && arr[j] > tmp) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = tmp;
            }
            gap = gap / 3;
        }
    }
    /**
     * 归并排序
     * @param arr 排序数组
     * @param tempArr 临时存储数组
     * @param startIndex 排序起始位置
     * @param endIndex 排序终止位置
     */
    private static void sort(int[] arr,int[] tempArr,int startIndex,int endIndex){
        if(endIndex <= startIndex){
            return;
        }
        //中部下标
        int middleIndex = startIndex + (endIndex - startIndex) / 2;

        //分解
        sort(arr,tempArr,startIndex,middleIndex);
        sort(arr,tempArr,middleIndex + 1,endIndex);

        //归并
        merge(arr,tempArr,startIndex,middleIndex,endIndex);
    }

    /**
     * 归并
     * @param arr 排序数组
     * @param tempArr 临时存储数组
     * @param startIndex 归并起始位置
     * @param middleIndex 归并中间位置
     * @param endIndex 归并终止位置
     */
    private static void merge(int[] arr, int[] tempArr, int startIndex, int middleIndex,int endIndex) {
        //复制要合并的数据
        for (int s = startIndex; s <= endIndex; s++) {
            tempArr[s] = arr[s];
        }

        int left = startIndex;//左边首位下标
        int right = middleIndex + 1;//右边首位下标
        for (int k = startIndex; k <= endIndex; k++) {
            if(left > middleIndex){
                //如果左边的首位下标大于中部下标，证明左边的数据已经排完了。
                arr[k] = tempArr[right++];
            } else if (right > endIndex){
                //如果右边的首位下标大于了数组长度，证明右边的数据已经排完了。
                arr[k] = tempArr[left++];
            } else if (tempArr[right] < tempArr[left]){
                arr[k] = tempArr[right++];//将右边的首位排入，然后右边的下标指针+1。
            } else {
                arr[k] = tempArr[left++];//将左边的首位排入，然后左边的下标指针+1。
            }
        }
    }

    /**
     *快速排序-单边扫描
     * @param arr
     * @param startIndex
     * @param endIndex
     */
    private static void sortFastA(int[] arr,int startIndex, int endIndex) {
        if (endIndex <= startIndex) {
            return;
        }
        //切分
        int pivotIndex = partitionA(arr,startIndex,endIndex);
        sortFastA(arr,startIndex, pivotIndex-1);
        sortFastA(arr, pivotIndex+1, endIndex);
    }

    private static int partitionA(int[] arr, int startIndex, int endIndex) {
        int pivot = arr[startIndex];//取基准值
        int mark = startIndex;//Mark初始化为起始下标

        for(int i=startIndex+1; i<=endIndex; i++){
            if(arr[i]<pivot){
                //小于基准值 则mark+1，并交换位置。
                mark ++;
                int p = arr[mark];
                arr[mark] = arr[i];
                arr[i] = p;
            }
        }
        //基准值与mark对应元素调换位置
        arr[startIndex] = arr[mark];
        arr[mark] = pivot;
        return mark;
    }

    /**
     * 快速排序-双边扫描
     * @param arr
     */
    private static void sortFastB(int[] arr, int startIndex, int endIndex) {
        if (endIndex <= startIndex) {
            return;
        }
        //切分
        int pivotIndex = partitionB(arr, startIndex,endIndex);
        sortFastB(arr,startIndex,pivotIndex-1);
        sortFastB(arr, pivotIndex+1, endIndex);
    }
    private static int partitionB(int[] arr, int startIndex, int endIndex) {
        int left = startIndex;
        int right = endIndex;
        int pivot = arr[startIndex];//取第一个元素为基准值

        while (true) {
            //从左往右扫描
            while (arr[left] <= pivot) {
                left++;
                if (left == right) {
                    break;
                }
            }

            //从右往左扫描
            while (pivot < arr[right]) {
                right--;
                if (left == right) {
                    break;
                }
            }

            //左右指针相遇
            if (left >= right) {
                break;
            }

            //交换左右数据
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }

        //将基准值插入序列
        int temp = arr[startIndex];
        arr[startIndex] = arr[right];
        arr[right] = temp;
        return right;
    }

    /**
     * 堆排序
     * @param arr
     */
    public static void sortHeap(int[] arr) {
        int length = arr.length;
        //构建堆
        buildHeap(arr, length);
        for ( int i = length - 1; i > 0; i-- ) {
            //将堆顶元素与末位元素调换
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            //数组长度-1 隐藏堆尾元素
            length--;
            //将堆顶元素下沉 目的是将最大的元素浮到堆顶来
            sink(arr,0,length);
        }
    }
    private static void buildHeap(int[] arr, int length) {
        for (int i = length / 2; i >= 0; i--) {
            sink(arr,i,length);
        }
    }
    /**
     * 下沉调整
     * @param arr 数组
     * @param index 调整位置
     * @param length 数组范围
     */
    private static void sink(int[] arr, int index, int length) {
        int leftChild = 2 * index + 1;//左子节点下标
        int rightChild = 2 * index + 2;//右子节点下标
        int present = index;//要调整的节点下标
        //下沉左边
        if (leftChild < length && arr[leftChild] > arr[present]) {
            present = leftChild;
        }
        //下沉右边
        if (rightChild < length && arr[rightChild] > arr[present]) {
            present = rightChild;
        }
        //如果下标不相等 证明调换过了
        if (present != index) {
            //交换值
            int temp = arr[index];
            arr[index] = arr[present];
            arr[present] = temp;

            //继续下沉
            sink(arr, present, length);
        }
    }

    /**
     * 计算排序
     * @param arr
     */
    public static void sortJiShu(int[] arr) {
        //找出数组中的最大值
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //初始化计数数组
        int[] countArr = new int[max + 1];
        //计数
        for (int i = 0; i < arr.length; i++) {
            countArr[arr[i]]++;
            arr[i] = 0;
        }
        //排序
        int index = 0;
        for (int i = 0; i < countArr.length; i++) {
            if (countArr[i] > 0) {
                arr[index++] = i;
            }
        }
    }

    /**
     * 稳定排序
     * @param arr
     */
    public static void sortWenDing(int[] arr) {
        //找出数组中的最大值
        int max = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        //初始化计数数组
        int[] countArr = new int[max + 1];

        //计数
        for (int i = 0; i < arr.length; ++i) {
            countArr[arr[i]]++;
        }

        //顺序累加
        for (int i = 1; i < max + 1; ++i) {
            countArr[i] = countArr[i-1] + countArr[i];
        }

        //排序后的数组
        int[] sortedArr = new int[arr.length];

        //排序
        for (int i = arr.length - 1; i >= 0; --i) {
            sortedArr[countArr[arr[i]]-1] = arr[i];
            countArr[arr[i]]--;
        }

        //将排序后的数据拷贝到原数组
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = sortedArr[i];
        }
    }

    /**
     * 桶排序
     * @param arr
     */
    public static void sortTong(int[] arr){
        //最大最小值
        int max = arr[0];
        int min = arr[0];
        int length = arr.length;
        for(int i=1; i<length; i++) {
            if(arr[i] > max) {
                max = arr[i];
            } else if(arr[i] < min) {
                min = arr[i];
            }
        }
        //最大值和最小值的差
        int diff = max - min;
        //桶列表
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>();
        for(int i = 0; i < length; i++){
            bucketList.add(new ArrayList<>());
        }
        //每个桶的存数区间
        float section = (float) diff / (float) (length - 1);
        //数据入桶
        for(int i = 0; i < length; i++){
            //当前数除以区间得出存放桶的位置 减1后得出桶的下标
            int num = (int) (arr[i] / section) - 1;
            if(num < 0){
                num = 0;
            }
            bucketList.get(num).add(arr[i]);
        }
        //桶内排序
        for(int i = 0; i < bucketList.size(); i++){
            //jdk的排序速度当然信得过
            Collections.sort(bucketList.get(i));
        }
        //写入原数组
        int index = 0;
        for(ArrayList<Integer> arrayList : bucketList){
            for(int value : arrayList){
                arr[index] = value;
                index++;
            }
        }
    }

    /**
     * 基数排序
     * @param arr
     */
    public static void sortBaseNumber(int[] arr){
        int length = arr.length;
        //最大值
        int max = arr[0];
        for(int i = 0;i < length;i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        //当前排序位置
        int location = 1;
        //桶列表
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>();
        //长度为10 装入余数0-9的数据
        for(int i = 0; i < 10; i++){
            bucketList.add(new ArrayList());
        }
        while(true)
        {
            //判断是否排完
            int dd = (int)Math.pow(10,(location - 1));
            if(max < dd){
                break;
            }
            //数据入桶
            for(int i = 0; i < length; i++)
            {
                //计算余数 放入相应的桶
                int number = ((arr[i] / dd) % 10);
                bucketList.get(number).add(arr[i]);
            }
            //写回数组
            int nn = 0;
            for (int i=0;i<10;i++){
                int size = bucketList.get(i).size();
                for(int ii = 0;ii < size;ii ++){
                    arr[nn++] = bucketList.get(i).get(ii);
                }
                bucketList.get(i).clear();
            }
            location++;
        }
    }
}
