const fs = require('fs')
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString()

const INF = 1_000_000_000
const n = parseInt(input)

const dp =
    Array.from({length: 1024}).map(_ =>
        Array.from({length: n}).map(_ =>
            Array.from({length: 10}).map(_ => 0))
    )

for (let i = 0; i < 10; i++) {
    dp[1 << i][0][i] = 1
}

for (let bit = 0; bit < 1024; bit++) {
    for (let p = 0; p < n - 1; p++) {
        for (let i = 1; i < 9; i++) {
            dp[bit | (1 << (i - 1))][p + 1][i - 1] += dp[bit][p][i];
            dp[bit | (1 << (i + 1))][p + 1][i + 1] += dp[bit][p][i];
            dp[bit | (1 << (i - 1))][p + 1][i - 1] %= INF;
            dp[bit | (1 << (i + 1))][p + 1][i + 1] %= INF;
        }
        dp[bit | 2][p + 1][1] += dp[bit][p][0];
        dp[bit | 256][p + 1][8] += dp[bit][p][9];
        dp[bit | 2][p + 1][1] %= INF;
        dp[bit | 256][p + 1][8] %= INF;
    }
}


function getSum(n) {
    let sum = 0
    for (let i = 1; i < 10; i++) {
        const e = dp[1023][n - 1][i]
        sum = (sum + e) % INF
    }
    return sum
}

const sum = getSum(n)
console.log(sum)
