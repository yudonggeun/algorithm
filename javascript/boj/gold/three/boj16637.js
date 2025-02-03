const fs = require('fs')
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n")

const length = parseInt(input[0])
const expression = input[1]

let answer = Number.MIN_SAFE_INTEGER
dfs(1, expression, length, parseInt(expression.charAt(0)))
console.log(answer)

function dfs(i, expression, length, sum) {
    if (i >= length) {
        answer = Math.max(answer, sum)
        return
    }
    const operator = expression.charAt(i)
    const rightNum = parseInt(expression.charAt(i + 1))

    // 괄호를 입력하지 않아 순서가 바뀌지 않는 경우
    dfs(i + 2, expression, length, cal(sum, rightNum, operator))

    if (i + 3 < length) {
        // 괄호를 입력하여 순서를 바꾸는 경우
        const nextOperator = expression.charAt(i + 2)
        const nextRightNum = parseInt(expression.charAt(i + 3))
        dfs(i + 4, expression, length, cal(sum, cal(rightNum, nextRightNum, nextOperator), operator))
    }

}

function cal(a, b, operator) {
    if (operator === '+') {
        return a + b
    } else if (operator === '-') {
        return a - b
    } else if (operator === '*') {
        return a * b
    } else {
        console.error(operator)
        throw Error("not support : " + operator)
    }
}
