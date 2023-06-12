
function once(func) {
    // code here
    var first = true;
    return function(){
        if(first) {
            first = false;
            func();
        } else {
            console.log("");
        }
    };
}

var runOnce = once(function(){console.log('RAN!');});

console.log("first--");
runOnce(); //print RAN!
console.log("second--");
runOnce(); //nothing
console.log("third--");
runOnce(); //nothing

/////////////////////////////////////////////////////////////

const text = 'Supplyframe is cool';

function solution(str, numLines=3) {
    var strs = [];
    for(var i = 0; i < numLines; i++) {
        strs.push('');
    }

    var cycle = numLines*2-2;
    for(var i = 0; i < str.length; i++) {
        var step = (i+1)%cycle;
        if(step >= numLines) step = cycle-step;

        for(var j = 0; j < numLines; j++) {
            if(j == step) {
                strs[j] += str.charAt(i);
            } else {
                strs[j] += ' ';
            }
        }
    }

    for(var i = 0; i < numLines; i++) {
        console.log(strs[i]);
    }
}

solution(text, 5);