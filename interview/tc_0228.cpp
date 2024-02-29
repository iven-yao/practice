#include <map>
#include <iostream>

template<typename K, typename V>
class interval_map {
	friend void IntervalMapTest();
	V m_valBegin;
	
public:
	std::map<K,V> m_map;
	interval_map(V const& val)
	: m_valBegin(val)
	{}

	void assign( K const& keyBegin, K const& keyEnd, V const& val ) {
		if (!keyBegin || !keyEnd || !val) return;
		if ((keyBegin >= keyEnd)) return;

		if (m_map.empty()) {
			if(val == m_valBegin) return;
			m_map[keyBegin] = val;
			m_map[keyEnd] = m_valBegin;
		} else {
			
			auto itBeginOrPrev = --m_map.upper_bound(keyBegin);
			auto itBeginNext = m_map.upper_bound(keyBegin);
			auto itEndOrPrev = --m_map.upper_bound(keyEnd);
			auto itEndNext = m_map.upper_bound(keyEnd);

			// record first element
			K prevKey = itBeginOrPrev->first;
			V prevVal = itBeginOrPrev->second;
			// check updated end value
			V endVal = m_valBegin;
			if(itEndOrPrev->first <= keyEnd) {
				endVal = itEndOrPrev->second;
			}
			
			// clean out middle
			m_map.erase(itBeginNext, itEndNext);
			
			// add new pairs
			if((prevKey >= keyBegin && val != m_valBegin) || (prevKey < keyBegin && val!= prevVal)) {
				m_map[keyBegin] = val;
			}
			if(val != endVal) {
				m_map[keyEnd] = endVal;
			}


		}
	}

	// look-up of the value associated with key
	V const& operator[]( K const& key ) const {
		auto it=m_map.upper_bound(key);
		if(it==m_map.begin()) {
			return m_valBegin;
		} else {
			return (--it)->second;
		}
	}
};

void printmap(interval_map<int, char> M) {
	for (int i = 0; i <= 10; ++i) {
        std::cout << M[i] << ",";
    }
	std::cout << std::endl;

	for(const auto& elem : M.m_map)
	{
	std::cout << "(" << elem.first << "," << elem.second << "),";
	}

	std::cout << std::endl;
	std::cout << "===============================================" <<std::endl;
}


int main() {

    interval_map<int, char> M('A');
    M.assign(3, 6, 'B');
	printmap(M);

    M.assign(0, 2, 'C');
	printmap(M);

    M.assign(0, 3, 'D');
	printmap(M);
	
    M.assign(1, 4, 'E');
	printmap(M);

    M.assign(3, 5, 'F');
	printmap(M);
	
	M.assign(4, 6, 'G');
	printmap(M);

	M.assign(6, 8, 'H');
	printmap(M);

	M.assign(7, 9, 'I');
	printmap(M);

	M.assign(-1, 10, 'A');
	printmap(M);

	M.assign(2, 5, 'A');
	printmap(M);

	M.assign(2, 5, NULL);
	printmap(M);

	M.assign(10, 5, 'B');
	printmap(M);

    return 0;
}


/**
 *Task Description
interval_map<K,V> is a data structure that associates keys of type K with values of type V. It is designed to be used efficiently in situations where intervals of consecutive keys are associated with the same value. Your task is to implement the assign member function of this data structure, which is outlined below.

interval_map<K, V> is implemented on top of std::map. For more information on std::map, you may refer to cppreference.com.

Each key-value-pair (k,v) in interval_map<K,V>::m_map means that the value v is associated with all keys from k (including) to the next key (excluding) in m_map. The member interval_map<K,V>::m_valBegin holds the value that is associated with all keys less than the first key in m_map.

Example: Let M be an instance of interval_map<int,char> where

M.m_valBegin=='A',
M.m_map=={ (1,'B'), (3,'A') },
Then M represents the mapping

...
-2 -> 'A'
-1 -> 'A'
0 -> 'A'
1 -> 'B'
2 -> 'B'
3 -> 'A'
4 -> 'A'
5 -> 'A'
...
The representation in the std::map must be canonical, that is, consecutive map entries must not contain the same value: ..., (3,'A'), (5,'A'), ... is not allowed. Likewise, the first entry in m_map must not contain the same value as m_valBegin. Initially, the whole range of K is associated with a given initial value, passed to the constructor of the interval_map<K,V> data structure.

Key type K

besides being copyable and assignable, is less-than comparable via operator<, and
does not implement any other operations, in particular no equality comparison or arithmetic operators.
Value type V

besides being copyable and assignable, is equality-comparable via operator==, and
does not implement any other operations.
 * 
 */