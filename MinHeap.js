function heappush(heap, newKey) {
    heap.push(newKey);

    let curr = heap.length -1;

    // comparing till root or we exit in the middle
    while(curr > 0) {
        let parent = Math.floor((curr-1)/2)
        if(heap[curr] < heap[parent]) {
            let tmp = heap[curr]
            heap[curr] = heap[parent]
            heap[parent] = tmp
            curr = parent
        } else {
            break
        }
    }
}

function heappop(heap) {
    const n = heap.length;
    let tmp = heap[0]
    heap[0] = heap[n-1]
    heap[n-1] = tmp

    const popped = heap.pop();

    let curr = 0;
    // comparing down to leaf
    while(2*curr+1 < heap.length) {
        const leftChild = 2*curr +1;
        const rightChild = 2*curr+2;
        const minChild = (rightChild < heap.length && heap[rightChild] < heap[leftChild]) ? rightChild : leftChild;
        if(heap[minChild] < heap[curr]) {
            //swap
            [heap[minChild], heap[curr]] = [heap[curr], heap[minChild]]
            curr = minChild
        } else {
            break
        }
    }

    return popped;
}

function heapify(arr) {
    const heap = []
    for(let item of arr) {
        heappush(heap, item)
    }

    return heap
}

