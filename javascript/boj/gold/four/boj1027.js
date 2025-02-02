const fs = require('fs')
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n")

const n = parseInt(input.shift())
const building = input.shift().split(" ").map(i => parseInt(i))

const dp = Array.from({length: n}).map(_ => 0)

for(let x = 0; x < n; x++){
    dp[x] += getLeftCount(building, x) + getRightCount(building, x)
}

const result = dp.reduce((acc, num) => {
    return Math.max(acc, num)
}, 0)
console.log(result)

function getLeftCount(building, fx) {
    if (fx === 0) return 0;
    let count = 1;
    const fy = building[fx]
    for (let tx = fx - 2; tx > -1; tx--) {
        const ty = building[tx]
        // 1차 방정식 기울기, 절편 구하기
        const a = (ty - fy) / (tx - fx)
        const b = fy - (a * fx)

        let isHit = true
        for (let mx = fx - 1; mx > tx; mx--) {
            const my = a * mx + b
            if (my <= building[mx]) {
                isHit = false
                break;
            }
        }

        if (isHit) {
            count++
        }
    }
    return count;
}

function getRightCount(building, fx) {
    if (fx === building.length - 1) return 0;
    let count = 1;
    const fy = building[fx]
    for (let tx = fx + 2; tx < building.length; tx++) {
        const ty = building[tx]
        // 1차 방정식 기울기, 절편 구하기
        const a = (ty - fy) / (tx - fx)
        const b = fy - (a * fx)

        let isHit = true
        for (let mx = fx + 1; mx < tx; mx++) {
            const my = a * mx + b
            if (my <= building[mx]) {
                isHit = false;
                break;
            }
        }

        if (isHit) {
            count++
        }
    }
    return count;
}
