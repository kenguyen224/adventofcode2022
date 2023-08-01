/**

Write a function:

class Solution { public int solution(int[] A); }

that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.

For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.

Given A = [1, 2, 3], the function should return 4.

Given A = [−1, −3], the function should return 1.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−1,000,000..1,000,000].
 */
internal class Solution {

    fun solution1(A: IntArray): Int {
        val sortedSet = A.toSortedSet()
        for (i in 1..sortedSet.last()) {
            if (!sortedSet.contains(i)) {
                return i
            }
        }
        return if (sortedSet.last() > 0) sortedSet.last() + 1 else 1
    }

    fun solution2(A: IntArray): Int {
        val set = A.toSet()
        set.forEachIndexed { index, _ ->
            if (!set.contains(index + 1)) {
                return index + 1
            }
        }
        val max = set.maxOrNull() ?: 0
        return if (max > 0) max + 1 else 1
    }
}

fun main() {

}
