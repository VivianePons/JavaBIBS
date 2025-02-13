# comme en Java, la structure d'import correspond à la structure des repertoires
# ici, le premier rational correspond au repertoire (qui se trouve dans le repertoire
# ou on execte le fichier) et le deuxième au fichier rational.py
# Contrairement au java, il peut y avoir plusieurs classes définies dans
# un seul fichier, on peut importer une ou plusieurs définition
from rational.rational import Rational

def lire_rationnel():
    s = input("Entrez un rationnel : ")
    while True:
        try:
            r = Rational(s)
            return r
        except:
            print(f"Impossible de convertir {s} en rationnel")
            s = input("Entrez un rationnel : ")

r1 = lire_rationnel()
print("Rationnel ", r1)

r2 = lire_rationnel()
print("Rationnel ", r2)

print("Comme on a surchargé les opérateurs, on peut directement effectuer les opérations")

print(r1, " + ", r2, " = ", r1+r2)
print(r1, " * ", r2, " = ", r1*r2)
print(r1, " - ", r2, " = ", r1-r2)
print(r1, " / ", r2, " = ", r1/r2)

print("Comme on a surchargé la comparaison, les fonctions min et max du python peuvent être appliquées")

print("Le minimum est :", min(r1,r2))
print("Le maximum est : ", max(r1,r2))

r3 = lire_rationnel()
print("Rationnel ", r3)

print("Je peux les mettre dans une liste")

L = [r1, r2, r3]

print(L)

print("Et la trier avec la méthode sort")

L.sort()

print(L)

print("Comme j'ai surchargé la méthode de hash, je peux les mettre dans un ensemble (ou comme clé d'un disctionnaire)")

E = {r1, r2, r3}

print(E)

print("Cependant, le fonctionnement de python est dynamique et non controlé (contrairement au Java), je peux donc contourner l'utilisation prévue en manipulant les champs internes des objets")

n = r1.numerator()
d = r1.denominator()

r1._n = 100
r1._d = 100

print("Ainsi je crée un rationnel défecteux...")
print(r1)

E.add(r1)

r1._n = n
r1._d = d

print("Et en modifiant une variable qui ne devrait pas l'être, je peux avoir 2 fois le même rationnel dans mon ensemble (ce qui n'est pas possible avec une utilisation normale)")
print(E)





