from animals import Sheep
from animals import Wolf

sheep = Sheep( (5,5) )
wolf = Wolf( (0,0) )

grass_pos = (10, 10)

while(wolf.position() != sheep.position()):
    sheep.moves_toward(grass_pos)
    wolf.moves_toward(sheep.position())
    print(wolf)
    print(sheep)

