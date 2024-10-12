class Animal {
    constructor(name) {
        this.name = name;
    }

    sayHi() {
        console.log(`${this.name} say hi to you`);
    }
}

class Dog extends Animal {
    constructor(name) {
        super(name);
    }

    bark() {
        console.log("Woof!");
    }

    sitDown() {
        console.log(`${this.name} sit down`);
    }
}

class Dachshund extends Dog {
    constructor(name) {
        super(name);
    }

    sayHi = () => {
        console.log(`${this.name} ignored you.`)
    }

    bark = () => {
        console.log("*(broken flute sound)*");
    }

    sitDown = () => {
        console.log("???");
    }

}

let smallB = new Dachshund("小逼");
smallB.sayHi();
smallB.bark();
smallB.sitDown();

let sake = new Dog("Sake");
sake.bark();
sake.sayHi();
sake.sitDown();