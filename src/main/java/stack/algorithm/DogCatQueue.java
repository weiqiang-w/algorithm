package stack.algorithm;

import java.util.LinkedList;
import java.util.Queue;


public class DogCatQueue {
    private Queue<PetEnterQueue> dogQ;
    private Queue<PetEnterQueue> catQ;
    private long count;

    public DogCatQueue() {
        this.dogQ = new LinkedList<>();
        this.catQ = new LinkedList<>();
        this.count = 0;
    }

    public void add(Pet pet) {
        if ("cat".equals(pet.getType())) {
            catQ.add(new PetEnterQueue(pet, count++));
        } else if ("dog".equals(pet.getType())) {
            dogQ.add(new PetEnterQueue(pet, count++));
        } else {
            throw new RuntimeException("Not a cat or a Dog");
        }
    }

    public Dog pollDog() {
        if (dogQ.isEmpty()) {
            throw new RuntimeException("Dog queue is empty");
        }
        return (Dog) dogQ.poll().getPet();
    }

    public Cat pollCat() {
        if (catQ.isEmpty()) {
            throw new RuntimeException("Cat queue is empty");
        }
        return (Cat) catQ.poll().getPet();
    }

    public Pet pollAll() {
        if (!isEmpty()) {
            if (dogQ.peek().getCount() > catQ.peek().getCount()) {
                return catQ.poll().getPet();
            } else {
                return dogQ.poll().getPet();
            }
        } else if (!dogQ.isEmpty()) {
            return dogQ.poll().getPet();
        } else if (!catQ.isEmpty()) {
            return catQ.poll().getPet();
        } else {
            throw new RuntimeException("Queue is empty");
        }

    }

    public boolean isEmpty() {
        return dogQ.isEmpty() && catQ.isEmpty();
    }

    public static void main(String[] args) {
    }
}

class PetEnterQueue {
    private Pet pet;
    private long count;

    public PetEnterQueue() {
    }

    public PetEnterQueue(Pet pet, long count) {
        this.pet = pet;
        this.count = count;
    }

    public Pet getPet() {
        return pet;
    }

    public long getCount() {
        return count;
    }

    public String getPetEnterType() {
        return this.pet.getType();
    }
}


class Pet {
    private String type;

    public Pet(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

class Cat extends Pet {
    public Cat() {
        super("cat");
    }
}

class Dog extends Pet {
    public Dog() {
        super("dog");
    }
}
