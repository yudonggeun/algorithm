const fs = require('fs')
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString()

const lines = input.split("\n");

const n = parseInt(lines.shift())

const numbers = lines.shift().split(" ").map(i => parseInt(i))

// 1개가 노출되었을 때 최소 값
let oneMin = 50
let oneMax = 0
for (let i = 0; i < 6; i++) {
    oneMin = Math.min(oneMin, numbers[i])
    oneMax = Math.max(oneMax, numbers[i])
}

// 2개가 노출되었을 때 최소 값
let twoMin = 100
const twoCase = [[0, 1], [0, 2], [0, 3], [0, 4], [1, 2], [1, 3], [1, 5], [2, 4], [2, 5], [3, 4], [3, 5], [4, 5]]
for (const [a, b] of twoCase) {
    twoMin = Math.min(twoMin, numbers[a] + numbers[b])
}

// 3개가 노출되었을 때 최소 값
let threeMin = 200
let threeCase = [[0, 1, 2], [0, 1, 3], [0, 3, 4], [0, 2, 4], [1, 3, 5], [1, 2, 5], [2, 4, 5], [3, 4, 5]]
for (const [a, b, c] of threeCase) {
    const number = numbers[a] + numbers[b] + numbers[c]
    threeMin = Math.min(threeMin, number)
}

if (n === 1) {
    const sum = numbers.reduce((acc, num) => acc + num, 0)
    console.log(sum - oneMax)
} else {
    const threeDice = 4
    const twoDice = Math.max(8 * (n - 1) - 4, 0)
    const oneDice = Math.max((n - 1) * (n - 2) * 5 - (n - 2), 0)

    console.log(threeDice * threeMin + twoDice * twoMin + oneMin * oneDice)
}

