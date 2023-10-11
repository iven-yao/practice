
input = [4,7,1,4]

def insert(arr:list):
    diff = diffArr(arr)
    maxDiff = max(diff)
    indexMax = diff.index(maxDiff)
    tobeInsert = (arr[indexMax]+arr[indexMax+1])//2
    #insert
    arr.insert(indexMax+1, tobeInsert)

    # print(arr)
    return cost(arr)


def cost(arr:list):
    total = 0

    for i in range(0, len(arr)-1):
        diff = arr[i]-arr[i+1]
        total += diff*diff
    return total

def diffArr(arr:list):
    diff = []
    for i in range(0, len(arr)-1):
        diff.append(abs(arr[i]-arr[i+1]))
    return diff

print(insert(input))