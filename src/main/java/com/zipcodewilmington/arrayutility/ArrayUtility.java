package com.zipcodewilmington.arrayutility;


import java.lang.reflect.Array;
import java.util.stream.Stream;

/**
 * Created by leon on 3/6/18.
 */
public class ArrayUtility<T> {

    T[] inputArray;

    public ArrayUtility(T[] inputArray) {
        this.inputArray = inputArray;
    }

    public T[] mergeArrays(T[] arrayToMerge) {
        return (T[]) Stream.concat(Stream.of(inputArray), Stream.of(arrayToMerge)).toArray();
    }

    public Integer countDuplicatesInMerge(T[] arrayToMerge, T valueToEvaluate) {
        return getNumberOfOccurrences(valueToEvaluate, mergeArrays(arrayToMerge));

    }

    public T getMostCommonFromMerge(T[] arrayToMerge) {
        T[] mergedArray = mergeArrays(arrayToMerge);
        int max = 0;
        for(int i = 1; i <  mergedArray.length; i++){
            if (countDuplicatesInMerge(arrayToMerge, mergedArray[i]) >
                    (countDuplicatesInMerge(arrayToMerge, mergedArray[max]))){
                max = i;
            }
        }
        return mergedArray[max];
    }


    public Integer getNumberOfOccurrences(T valueToEvaluate) {
        return Math.toIntExact(Stream.of(inputArray).filter(i -> i == valueToEvaluate).count());
    }

    public Integer getNumberOfOccurrences(T valueToEvaluate, T[] array) {
        return Math.toIntExact(Stream.of(array).filter(i -> i == valueToEvaluate).count());
    }

    public T[] removeValue(T valueToRemove) {
        Stream<T> stream = Stream.of(inputArray);
        Object[] objects = stream.filter(t -> !t.equals(valueToRemove)).toArray();
        T[] answer = (T[]) Array.newInstance(inputArray.getClass().getComponentType(), objects.length);
        System.arraycopy(objects, 0, answer, 0, objects.length);
        return answer;
    }
}
