
# factorial 0~9
fac = []
fac.append(1)

for i in range(1, 10):
    fac.append(fac[i-1]*i)

# print(fac)

# team up from id 
def team_up(id):
    team = []
    leader = 0
    while id not in team:
        leader = max(leader, id)
        team.append(id)
        id = next(id)

    return team, leader

def next(id):
    next_id = 0
    while(id > 0):
        digit = id % 10
        id = id // 10
        next_id += fac[digit]
    
    return next_id

def strength(team:list, leader):
    return leader * len(team)

team, leader = team_up(4)
print(strength(team, leader))
    