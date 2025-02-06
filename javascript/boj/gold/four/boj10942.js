const fs = require('fs')
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n")

const n = parseInt(input[0])
const numbers = input[1].split(" ").map(Number)
const m = parseInt(input[2])

const dp = Array.from({length: n}).map(_ =>
    Array.from({length: n}).map(_ => 2))

const answer = []
for (let i = 0; i < m; i++) {
    const [from, to] = input[3 + i].split(" ").map(i => parseInt(i) - 1)
    const result = isPalindrome(from, to)
    answer.push(result)
}

console.log(answer.join("\n"))

function isPalindrome(s, e) {
    if (e <= s) {
        return 1;
    }
    if (dp[s][e] !== 2) {
        return dp[s][e];
    }
    if (numbers[s] === numbers[e] && isPalindrome(s + 1, e - 1)) {
        return dp[s][e] = 1
    } else {
        return dp[s][e] = 0
    }
}
