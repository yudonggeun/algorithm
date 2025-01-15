const fs = require('fs')
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n")


const [size, command] = input.shift().split(" ")
const array = Array.from({length: size}).map((value, index) => index + 1)

for(let i = 0; i < command; i++){
    const [from, to] = input.shift().split(" ")
    swap(array, from - 1, to - 1)
}

console.log(array.join(" "))

function swap(array, from, to){
    while(from < to){
        [array[from], array[to]] = [array[to], array[from]]
        from++
        to--
    }
}
