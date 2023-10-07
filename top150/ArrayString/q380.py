from collections import defaultdict
import random


class RandomizedSet:

    def __init__(self):
        self.d = defaultdict(int)
        self.l = []

    # T: O(1), S: O(N), N: length of values in self.l/self.d
    def insert(self, val: int) -> bool:
        if val not in self.d.keys():
            self.l.append(val)
            self.d[val] = len(self.l) - 1
            return True
        else:
            return False

    # T: O(1), S: O(N), N: length of values in self.l/self.d
    def remove(self, val: int) -> bool:
        if val not in self.d.keys():
            return False
        else:
            idx = self.d[val]
            if idx != len(self.l) - 1:
                last_element = self.l[-1]
                self.l[idx] = last_element
                self.d[last_element] = idx
            self.l.pop()
            del self.d[val]
            return True

    # T: O(1), S: O(N), N: length of values in self.l/self.d
    def getRandom(self) -> int:
        return random.choice(self.l)
