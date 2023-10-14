def q118(row):
    res = []
    res.append([1])

    for i in range(1, row):
        res.append(next(res[i-1]))

    print(res)

def next(list:list):
    next_list = []
    next_list.append(1)
    for i in range(0, len(list)-1):
        next_list.append(list[i] + list[i+1])
    next_list.append(1)

    return next_list

q118(5)