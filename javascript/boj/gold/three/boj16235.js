const fs = require('fs')
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n")
let i = 0;

const [n, m, k] = input[i++].split(" ").map(s => parseInt(s))

const nearVector = [
    [-1, -1], [-1, 0], [-1, 1], [0, 1],
    [0, -1], [1, -1], [1, 0], [1, 1]
]
const a = Array.from({length: n});
const map = Array.from({length: n})
    .map(_ => Array.from({length: n}).map(_ => 5))

for (let j = 0; j < n; j++) {
    a[j] = input[i++].split(" ").map(i => parseInt(i))
}

let heap = []
for (let j = 0; j < m; j++) {
    const [x, y, z] = input[i++].split(" ").map(i => parseInt(i))
    heap.push([x - 1, y - 1, z, 0])
}

heap.sort((a, b) => {
    return b[2] - a[2]
})

for (let year = 0; year < k; year++) {

    // spring
    for (let i = heap.length - 1; i > -1; i--) {
        const [x, y, z, _] = heap[i]
        if (map[x][y] < z) {
            heap[i][3] = -1
        } else {
            map[x][y] -= z
            heap[i][2]++
        }
    }

    // summer
    heap = heap.filter(tree => {
        if (tree[3] === -1) {
            const [x, y, z, _] = tree
            map[x][y] += Math.floor(z / 2)
            return false
        } else {
            return true
        }
    })

    // autumn
    heap.forEach(tree => {
        const [x, y, z, _] = tree
        if (z % 5 === 0) {
            for (const [r, c] of nearVector) {
                if (isRange(x + r, y + c, n)) {
                    heap.push([x + r, y + c, 1, 0])
                }
            }
        }
    })

    // winter
    for (let r = 0; r < n; r++) {
        for (let c = 0; c < n; c++) {
            map[r][c] += a[r][c]
        }
    }
}

console.log(heap.length)

function isRange(r, c, n) {
    return 0 <= r && 0 <= c && r < n && c < n
}