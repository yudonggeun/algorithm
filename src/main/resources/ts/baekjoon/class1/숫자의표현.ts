/**
 * 슬라이딩 윈도우
 * https://school.programmers.co.kr/learn/courses/30/lessons/12924?language=javascript
 */
function solution(n) {
    let answer = 1
    let start = 0
    let end = 1
    let sum = 1

    while (start <= Math.ceil(n)) {
        if (sum === n) {
            answer++
            end++
            sum += end
        } else if (sum < n) {
            end++
            sum += end
        } else {
            sum -= start
            start++
        }
    }
    return answer
}