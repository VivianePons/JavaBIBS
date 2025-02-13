from rational.rational import Rational
import traceback

# Il existe des infrastuctures de tests spécifiques à python tout comme
# JUnit en java
# Cependant, pouréviter d'avoir à installer quoi que ce soit et comme ce
# n'est pas l'objectif du cours, ici j'écris simplement une liste de
# propriété à vérifier

class bcolors:
    HEADER = '\033[95m'
    OKBLUE = '\033[94m'
    OKCYAN = '\033[96m'
    OKGREEN = '\033[92m'
    WARNING = '\033[93m'
    FAIL = '\033[91m'
    ENDC = '\033[0m'
    BOLD = '\033[1m'
    UNDERLINE = '\033[4m'

def print_error(error):
    print(bcolors.FAIL +"TEST FAILED : " + error + bcolors.ENDC)

success = True
case = "Création des rationnels"
print(case)
try:
    assert(Rational(1,2)._n == 1)
    assert(Rational(1,2)._d == 2)
    assert(Rational(2,4)._n == 1)
    assert(Rational(2,4)._d == 2)
    assert(Rational(-1,-2)._n == 1)
    assert(Rational(-1,-2)._d == 2)
    assert(Rational(2)._n == 2)
    assert(Rational(2)._d == 1)
    assert(Rational(1,-2)._n == -1)
    assert(Rational(1,-2)._d == 2)
    assert(Rational()._n == 0)
    assert(Rational()._d == 1)
except AssertionError:
    print_error(case)
    print(traceback.format_exc())
    success = False

try:
    Rational(1,0)
    print_error(case)
    print("No exception raised on Rational(1,0)")
    success = False
except ValueError:
    pass

case = "Création depuis une chaine de caractères"
print(case)
try:
    assert(Rational("1/2")._n == 1)
    assert(Rational("1/2")._d == 2)
    assert(Rational("2/4")._n == 1)
    assert(Rational("2/4")._d == 2)
    assert(Rational("-1/2")._n == -1)
    assert(Rational("-1/2")._d == 2)
    assert(Rational("2")._n == 2)
    assert(Rational("2")._d == 1)
    assert(Rational("0")._n == 0)
    assert(Rational("0")._d == 1)
except AssertionError:
    print_error(case)
    print(traceback.format_exc())
    success = False

try:
    Rational("1/0")
    print_error(case)
    print("No exception raised on Rational('1/0')")
    success = False
except ValueError:
    pass

case = "Numerator / denominator"
print(case)
try:
    assert(Rational(1,2).numerator() == 1)
    assert(Rational(1,2).denominator() == 2)
except AssertionError:
    print_error(case)
    print(traceback.format_exc())
    success = False

case = "Tests égalités rationnels"
print(case)
try:
    assert(Rational(1,2) == Rational(1,2))
    assert(Rational(1,2) == Rational(2,4))
    assert(Rational(1,2) != Rational(3,2))
    assert(Rational(1,2) != Rational(1,3))
    assert(Rational(-1,2) == Rational(1,-2))
    assert(Rational(1,2) != Rational(-1,2))
    assert(Rational(4) == Rational(8,2))
    assert(Rational() == Rational(0,3))
except AssertionError:
    print_error(case)
    print(traceback.format_exc())
    success = False

case = "Minimal / maximal created"
print(case)
try:
    Rational(-10)
    Rational(10)
    assert(Rational.minimal_created() == Rational(-10))
    assert(Rational.maximal_created() == Rational(10))
except AssertionError:
    print_error(case)
    print(traceback.format_exc())
    success = False

case = "Inverse"
print(case)
try:
    assert(Rational(1,2).inverse() == Rational(2))
    assert(Rational(-1,2).inverse() == Rational(-2))
except AssertionError:
    print_error(case)
    print(traceback.format_exc())
    success = False

case = "Value"
print(case)
try:
    assert(Rational(1,2).value() == .5)
    assert(Rational(-1,2).value() == -.5)
    assert(Rational(2).value() == 2)
except AssertionError:
    print_error(case)
    print(traceback.format_exc())
    success = False

case = "Repr"
print(case)
try:
    assert(str(Rational(1,2)) == "1/2")
    assert(str(Rational(-1,2)) == "-1/2")
    assert(str(Rational(2)) == "2")
    assert(str(Rational()) == "0")
except AssertionError:
    print_error(case)
    print(traceback.format_exc())
    success = False

case = "Hash"
print(case)
try:
    assert(hash(Rational(1,2)) == hash(Rational(1,2)))
    assert(hash(Rational(1,2)) != hash(Rational(3,2)))
    assert(hash(Rational(1,2)) != hash(Rational(1,3)))
except AssertionError:
    print_error(case)
    print(traceback.format_exc())
    success = False

case = "Opérations"
print(case)
try:
    assert(Rational(1,2) + Rational(1,2) == Rational(1))
    assert(Rational(1,2) + Rational(3,4) == Rational(5,4))
    assert(Rational(1,2) - Rational(1,2) == Rational())
    assert(Rational(1,2) - Rational(1,4) == Rational(1,4))
    assert(-Rational(1,2) == Rational(-1,2))
    assert(Rational(1,2) * Rational(3,2) == Rational(3,4))
    assert(Rational(1,2) / Rational(3,2) == Rational(1,3))
except AssertionError:
    print_error(case)
    print(traceback.format_exc())
    success = False

case = "Comparaisons"
print(case)
try:
    assert(Rational(1,2) <= Rational(1,2))
    assert(Rational(1,2) <= Rational(3,4))
    assert(not Rational(3,4) <= Rational(1,2))
    assert(Rational(-1,2) <= Rational())
    assert(not Rational(1,2) < Rational(1,2))
    assert(Rational(1,2) < Rational(3,4))
    assert(not Rational(3,4) < Rational(1,2))
    assert(Rational(-1,2) < Rational())
    assert(Rational(1,2) >= Rational(1,2))
    assert(not Rational(1,2) >= Rational(3,4))
    assert(Rational(3,4) >= Rational(1,2))
    assert(not Rational(-1,2) >= Rational())
    assert(not Rational(1,2) > Rational(1,2))
    assert(not Rational(1,2) > Rational(3,4))
    assert(Rational(3,4) > Rational(1,2))
    assert(not Rational(-1,2) > Rational())
except AssertionError:
    print_error(case)
    print(traceback.format_exc())
    success = False



if(success):
    print(bcolors.OKGREEN + "All tests passed!" + bcolors.ENDC)
else:
    print(bcolors.FAIL + "Errors in tests" +bcolors.ENDC)
