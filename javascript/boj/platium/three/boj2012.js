const fs = require('fs')
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n")

const n = parseInt(input[0])
const seq = input[1]

const dp = Array.from({length: n}).map(_ =>
    Array.from({length: n}).map(_ => -1))

const answer = solve(0, seq.length - 1)
if(answer >= 100000){
    console.log(String(answer % 100000).padStart(5, '0'))
} else {
    console.log(answer)
}

function solve(s, e) {
    if (s > e) {
        return 1
    }
    if (dp[s][e] !== -1) return dp[s][e]

    let sum = 0
    for (let m = s + 1; m <= e; m += 2) {
        const a = isPair(seq[s], seq[m])
        const b = solve(s + 1, m - 1)
        const c = solve(m + 1, e)
        sum = (sum + a * b * c) % 1000000
    }

    dp[s][e] = sum
    return sum
}

function isPair(l, r) {
    if (l === '?' && r === '?') {
        return 3
    }

    if (
        (l === '(' && r === ')') ||
        (l === '{' && r === '}') ||
        (l === '[' && r === ']') ||
        (l === '?' && r === ')') ||
        (l === '?' && r === '}') ||
        (l === '?' && r === ']') ||

        (l === '(' && r === '?') ||
        (l === '{' && r === '?') ||
        (l === '[' && r === '?')
    ) {
        return 1
    }
    return 0
}