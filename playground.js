var gcd = function (a, b) {
    return a ? gcd(b % a, a) : b;
}

var lcm = function (a, b) {
    return a * b / gcd(a, b);
}

function b(i){
  return i.toString(2)
}

function hashb(arr) {
    s = ""
    for(let i = 0; i < arr.length; i++) {
        s = s + arr[i] + " = " + b(arr[i]) +", "
    }

    console.log(s)
}

function pn(vaddr, start, offset) {
  console.log(((vaddr-start) >> 12) + offset)
}

function rr(arr) {
    count = arr.length
    wt = new Array(count).fill(0);

    while(count > 0) {
        currCount = 0;
        for( let i = 0; i < 4; i++) {
            if(arr[i] > 0) {
                currCount++;
            }
        }

        for( let i = 0; i < 4; i++) {
            if(arr[i] > 0) {
                arr[i]--;
                wt[i] += currCount;
            }
        }
        count = currCount;
    }
    console.log("wt(hrs): |",...wt,"|",wt.reduce((a,b)=>a+b)/wt.length)
}

function toABC(i) {
    arr = ['A','B','C','D']
    return arr[i]
}

function stride(initial, tickets, itrs=7) {
    stride = tickets.map(n=>tickets.reduce(lcm)/n)
    s = ""
    for(let i = 1; i <= itrs; i++) {
        min = Math.min(...initial)
        minIndex = initial.indexOf(min)
        if(i > 1){
            s = s + toABC(minIndex) + ":" + initial[minIndex] +" | "
        }
        initial[minIndex] += stride[minIndex]
    }

    console.log(s)
}

function rms(pOverT) {
    product = pOverT.reduce((a,b)=>a*b)
    ans = Math.min(product, pOverT.reduce(lcm))
    console.log(ans)
}

// Calculate Pagenum(vaddr, start, offset)
pn( 0x08053984, 0x0804d000, 0x3c)
console.log('==--==--==--==--==--==--==')
// Check hashing binary (array like)
hashb([4,36,12,20,44])
console.log('==--==--==--==--==--==--==')
// Round Robin
rr([10,7,11,7])
console.log('==--==--==--==--==--==--==')
// Stride Scheduling (initial, tickets)
stride([5,15,21,31],[4,7,8,10])
console.log('==--==--==--==--==--==--==')
// rate-monotonic scheduler (P/T array)
rms([7,5.5,13,8])