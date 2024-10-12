function simulate(entries) {
    // Write your code here
    let res = [];
    for(let i = 0; i < entries.length; i++) {
        let zero = false;
        if(i - 3 >= 0 && entries[i-3] >= entries[i]) {
            zero = true;
        }
        
        if(i + 4 < entries.length && entries[i+4] >= entries[i]) {
            zero = true;
        }
        
        res[i] = zero? 0: entries[i];
    }
    return res;
}
  
const records = [1, 2, 0, 5, 0, 2, 4, 3, 3, 3];
console.log(simulate(records));
// Expected output
// [1, 0, 0, 5, 0, 0, 0, 3, 3, 0]

// test on reduce
records.reduce((a, c) => {
    a += c;
    return a;
},0)


function checkDigit(membershipId) {
    if(typeof membershipId !== "string") membershipId = String(membershipId);
    
    let sum = membershipId.split("").reduce((prev, curr) => prev+parseInt(curr),0);

    return sum > 9? checkDigit(sum): sum;
}

console.log(checkDigit("55555"))

// if(1<>2) {console.log("TRUE1")};
if(1&&0) {console.log("TRUE2")};
if(1==1) {console.log("TRUE3")};
if(1=="1") {console.log("TRUE4")};
if(1===1) {console.log("TRUE5")};


function setup() {
    var lis = document.querySelectorAll('ul li');
    for (var i = 0; i < lis.length; i++) {
      lis[i].addEventListener('click', function(e) {
        // Write your code here
        $(this).parent().prepend($(this));
      });
    }
  }
  
  // Example case. 
  document.body.innerHTML = `
  <ul>
    <li>A</li>
    <li>B</li>
    <li>C</li>
  </ul>`;
  
  setup();
  
  document.getElementsByTagName("li")[2].click();
  console.log(document.body.innerHTML);