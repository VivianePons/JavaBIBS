

class Rational:

    # on déclare ici des champs statiques pour les minimaux / maximaux créés
    _minimal = None
    _maximal = None

    @staticmethod
    def minimal_created():
        r"""
        Return the minimal rational that was created
        """
        return Rational._minimal

    @staticmethod
    def maximal_created():
        r"""
        Return the maximal rational that was created
        """
        return Rational._maximal

    # la méthode gdc existe en réalité dans le package math, je laisse
    # cependant l'exemple d'une méthode statique interne
    @staticmethod
    def _gdc(a, b):
        from math import gcd
        return gcd(a,b)

    # En python, le constructeur s'appelle toujours __init__
    # le premier argument (self) correspond à l'instance qu'on est en
    # train d'initialiser
    # Comme on a pas la possibilité de faire de la surcharge avec plusieurs
    # constructeurs comme en Java, on utilise des paramètres optionnels
    def __init__(self, n = 0, d = 1, simplify = True):
        r"""
        Construct a rational number with given numerator and denominator
        by simplifying the fraction the simplifying is done only if
        `simplify` is True

        INPUT:

        - ``n`` -- an integer, the numerator (default: 0)
        - ``d`` -- an integer, the denominator (default: 1)
        - ``simplify`` -- a boolean, whether the simplification should be
          computed (default: true)
        """

        if(type(n) == int):

            # cas d = 0
            if d == 0:
                raise ValueError("Denominator must be non zero")

            # cas n = 0
            if n == 0:
                d = 1

            # vérification du signe
            if d < 0:
                n,d = -n, -d

            # simplification
            if d != 1:
                div = Rational._gdc(n, d) if n > 0 else Rational._gdc(-n, d)
                n, d = n // div, d // div # on utilise des divisions entières

            # Enregistrement de n et d comme champs internes de l'instance

            # En python, on ne peut pas déclarer des variables comme "final"
            # comme on ne veut pas que le code client modifie n et d, on les
            # crée comme "variables internes" en faisant commencer leur nom par
            # un "_"

            # la convention python fait qu'on utilise des noms commencant
            # par un "_" pour tout ce qui est "privé". Attention, ce n'est
            # pas RELLEMENT privé, on donne simplement une indication que c'est
            # du code interne que le code client ne devrait pas utiliser
            self._n = n
            self._d = d

            # mise à jour des valeurs statiques

            if Rational._minimal is None or self < Rational._minimal:
                Rational._minimal = self
            if Rational._maximal is None or self > Rational._maximal:
                Rational._maximal = self

        elif type(n) == str:
            parts = n.split("/")
            if len(parts) == 1:
                self.__init__(int(n))
            elif len(parts) == 2:
                n,d = int(parts[0]), int(parts[1])
                self.__init__(n,d)
            else:
                raise ValueError(f"Cannot create a rational from string {n}")
        else:
            raise ValueError(f"Does not know how to create a rational from {n}")


    # Nous n'avions pas ajouté de méthodes d'accès à n et d dans la version
    # Java. En effet, les champs étaient publics et finaux
    # Il n'y a pas de notion de "final" en python, et les champs sont simplement
    # "privés", on crée donc des accès publics en lecture

    def numerator(self):
        r"""
        Return the numerator of the rational
        """
        return self._n

    def denominator(self):
        r"""
        Return the denominator of the rational
        """
        return self._d

    def inverse(self):
        r"""
        Return the inverse of self
        """
        return Rational(self._d, self._n, False)

    def value(self):
        r"""
        Return the approximated value of the rational
        """
        return self._n / self._d

    # La méthode __repr__ est un équivalent du toString to Java
    def __repr__(self):
        if self._d != 1:
            return str(self._n) + "/" + str(self._d)
        else:
            return str(self._n)

    # l'égalité des objets est testée en surchageant la méthode __eq__
    # ce qui permet de tester directement avec le ==
    def __eq__(self, other):
        return isinstance(other,Rational) and self._n == other._n and self._d == other._d


    # python ne demande pas systématiquement d'implémenter la méthode
    # __hash__ même si l'égalité est implantée
    # les objets pythons n'ont pas de méthode __hash__ par défaut (contrairement au Java)
    # le fait d'implanter la méthode INDIQUE que l'objet n'est pas "modifiable"
    # et peut donc être ajouté à une structre de type ensemble
    # de fait, on implémente "l'interface" de hachage
    def __hash__(self):
        return hash(str(self))

    # En python, il est possible de surcharger les opérateurs ce qui peut
    # rendre plus simple l'utilisation de l'objet

    # là aussi, c'est comme si on implantait certaines interfaces qui
    # permettent d'utiler des fonctions génériques du python (sum, sort, etc)

    # opérations

    def __add__(self, other):
        return Rational(self._n * other.denominator() + other.numerator() * self._d, self._d * other.denominator())

    def __mul__(self, other):
        return Rational(self._n * other.numerator(), self._d * other.denominator())

    def __neg__(self):
        return Rational(-self._n, self._d, False)

    def __sub__(self, other):
        return self + (-other)

    def __truediv__(self, other):
        return self * other.inverse()

    # comparaisons

    def __lt__(self, other):
        return self.value() < other.value()

    def __le__(self, other):
        return self == other or self < other










