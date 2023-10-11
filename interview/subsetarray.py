arr = [3,7,5,6,2]

def subsetA(arr:list):
    arr.sort(reverse=True)
    bSum = sum(arr)

    setA = []
    aSum = 0
    for i in range(0, len(arr)):
        setA.append(arr[i])
        aSum += arr[i]
        bSum -= arr[i]

        if aSum > bSum:
            return setA
        
print(subsetA(arr))