// constructor of animal
function Animal (name) {
    this.name = name;
}

Animal.prototype.makeSound = function () {
    console.log(`${this.name} made a sound`);
}

// constructor of dog
function Dog (name, breed) {
    Animal.call(this, name);
    this.breed = breed;
    this.bark = function () {
        console.log("woof! woof!");
    }
}

Dog.prototype = Object.create(Animal.prototype);

let curry = new Dog("Curry","Corgi");

curry.bark();
curry.makeSound();