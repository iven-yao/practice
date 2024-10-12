const nestedObject = {
    data: {
      info: {
        stuff: {
          thing: {
            stuff: {
              someNumber: 23,
            },
            moreStuff: {
              magicNumber: 44,
              something: "foo2",
            },
          },
        },
      },
    },
};

const contains = (obj: Object, targetValue: string|number):boolean => {
    for(const key in obj) {
        if(typeof (obj as any)[key] === 'object') {
            if(contains((obj as any)[key], targetValue)) {
                return true;
            }
        } else if((obj as any)[key] === targetValue) {
            return true;
        }
    }

    return false;
}

console.log(contains(nestedObject, 44));
console.log(contains(nestedObject, 22));