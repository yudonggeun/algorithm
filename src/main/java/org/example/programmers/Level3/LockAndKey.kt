//https://school.programmers.co.kr/learn/courses/30/lessons/60059?language=kotlin
class LockAndKey {

    var lock: Array<IntArray>? = null
    var key: Array<IntArray>? = null

    fun solution(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
        this.lock = lock
        this.key = key

        val range = -lock.lastIndex..lock.lastIndex
        repeat(4) {
            for(row in range)
                for(col in range)
                    if (isMatch(this.key!!, row, col)) return true
            this.key = rotation(this.key!!)
        }
        return false;
    }

    private fun rotation(key: Array<IntArray>): Array<IntArray> = Array(key.size) { row ->
        IntArray(key.size) { col ->
            key[col][key.lastIndex - row]
        }
    }

    private fun isMatch(key: Array<IntArray>, i: Int, j: Int): Boolean {
        lock!!.forEachIndexed {r, array ->
            array.forEachIndexed {c , lockPoint ->
                val keyPoint = getKey(key, r - i, c - j)
                if (lockPoint + keyPoint != 1) return false
            }
        }
        return true;
    }

    private fun getKey(key: Array<IntArray>, r: Int, c: Int): Int = try {
        key[r][c]
    } catch (e: ArrayIndexOutOfBoundsException) {
        0
    }
}