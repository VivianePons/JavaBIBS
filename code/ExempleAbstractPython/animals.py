from enum import Enum

# En python, on peut utiliser des types énumérés en héritant de la classe
# enum

class AnimalType(Enum):
    HERBIVORE = 0,
    CARNIVORE = 1

# Comme python n'est pas déclaratif, on aura pas d'interface explicite
# On pourra dirrectement créer une "classe abstraite" dont hériteront
# les classes concrètes

# Le nom de la classe indique que c'est une classe abstraite
# mais cependant ça ne bloque pas la possibilité de l'instancier
class AbstractAnimal():

    def __init__(self, animal_type, initial_position, speed):
        self._animal_type = animal_type
        self.set_position(initial_position)
        self.set_speed(speed)


    def animal_type(self):
        return self._animal_type

    def set_position(self, position):
        self._position = position

    def position(self):
        return self._position

    def set_speed(self, speed):
        self._speed = speed

    def speed(self):
        return self._speed

    def moves_toward(self, target):
        x,y = self._position
        xt, yt = target
        xdiff, ydiff = xt - x, yt - y
        if xdiff == 0 and ydiff == 0:
            return False
        if xdiff > ydiff:
            sx = -1 if xdiff < 0 else 1
            self.set_position( (x + sx * min(self._speed, abs(xdiff)), y) )
        else:
            sy = -1 if ydiff < 0 else 1
            self.set_position( (x, y + sy * min(self._speed, abs(ydiff))) )
        return True

class Sheep(AbstractAnimal):

    def __init__(self, initial_position):
        super().__init__(AnimalType.HERBIVORE, initial_position, 1)

    def __repr__(self):
        return "A Sheep at position " + str(self.position())

    def can_eat(self, other):
        return False

class Wolf(AbstractAnimal):

    def __init__(self, initial_position):
        super().__init__(AnimalType.CARNIVORE, initial_position, 2)

    def __repr__(self):
        return "A Wolf at position " + str(self.position())

    def can_eat(self, other):
        return other.animal_type() == AnimalType.HERBIVORE



