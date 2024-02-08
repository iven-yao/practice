/**
 * @param {number[]} cards
 * @return {boolean}
 */
var judgePoint24 = function(cards) {
    if(cards.length == 1) {
        if(cards[0] == 24) return true;
        return false;
    }

    var res = false;
    for(let i = 0; i < cards.length; i++) {
        for(let j = 0; j < i; j++) {
            let newCards = [];
            newCards.push(cards[i]+cards[j], cards[i]-cards[j], cards[j]-cards[i], cards[i]*cards[j]);
            if(cards[i] != 0 && cards[j]%cards[i] == 0) newCards.push(cards[j]/cards[i]);
            if(cards[j] != 0 && cards[i]%cards[j] == 0) newCards.push(cards[i]/cards[j]);

            let newList = [];
            for(let k = 0; k < cards.length; k++) {
                if(k != i && k != j) {
                    newList.push(cards[k]);
                }
            }
            
            for(card of newCards) {
                newList.push(card);
                res = res || judgePoint24(newList);
                newList.pop();
            }
        }
    }

    return res;
};