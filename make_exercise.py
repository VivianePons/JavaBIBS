#!/usr/bin/env python

import sys
import os
import re

DEST = "exercices"

NOTINC = "// NOT INCLUDED"

IGNORES = {"bin", ".settings", "output", ".idea", "out",".run"}
SIMPLE_COPY = {"txt"}
SOLUTION_COPY = {"java"}

def file_extension(filename):
    p = filename.rfind(".")
    if p == -1:
        return ""
    return filename[p+1:]

def solution_copy(src_file_name, dest_file_name):
    src_file = open(src_file_name, "r")
    dest_file = open(dest_file_name, "w")

    B = "/// BEGIN SOLUTION"
    E = "/// END SOLUTION"

    BC = "/// BEGIN COMMENTEE"
    EC = "/// END COMMENTEE"

    BUC = "/* BEGIN UNCOMMENT"
    EUC = "END UNCOMMENT */"


    sol = 0
    com = 0
    ucom = 0
    line = src_file.readline()
    while line != "":
        write = True
        if line.find(BUC) >= 0:
            write = False
            ucom +=1
        if line.find(EUC) >= 0:
            write = False
            ucom -= 1
        if line.find(B) >= 0:
            sol+=1
        if line.find(BC) >= 0:
            com+=1
            line = line.replace(BC,"/*")
        if line.find(EC) >= 0:
            com -= 1
            line = line.replace(EC,"*/")
        if sol == 0 and write:
            dest_file.write(line)
        if line.find(E) >= 0:
            sol -= 1
        if sol < 0:
            print(f"WARNING: end solution without begin in {src_file_name}")
            sol = 0
        if com < 0:
            print(f"WARNING: end commentee without begin in {src_file_name}")
            com = 0
        if ucom < 0:
            print(f"WARNING: end uncomment without begin in {src_file_name}")
            ucom = 0
        line = src_file.readline()
    src_file.close()
    dest_file.close()

    if sol > 0:
        print(f"WARNING: missing end solution in {src_file_name}")
    if com > 0:
        print(f"WARNING: missing end commentee in {src_file_name}")
    if ucom > 0:
        print(f"WARNING: missing end un comment in {src_file_name}")

def make_exercise(src, dest, path):
    src_file = src + "/" + "/".join(path)
    dest_file = dest + "/" + "/".join(path)
    if os.path.isfile(src_file):
        ext = file_extension(path[-1])
        if ext in SIMPLE_COPY:
            cmd = f"cp {src_file} {dest_file}"
            os.system(cmd)
        elif ext in SOLUTION_COPY:
            src_file_test = open(src_file, "r")
            line = src_file_test.readline()
            src_file_test.close()
            if line.find(NOTINC) == -1:
                solution_copy(src_file, dest_file)
    elif os.path.isdir(src_file):
        if not os.path.isdir(dest_file):
            os.mkdir(dest_file)
        for filename in os.listdir(src_file):
            if not filename in IGNORES:
                make_exercise(src, dest, path + [filename])


if len(sys.argv) < 2:
    print("Please specify src folder")
else:
    src = sys.argv[1]
    if src[-1] == "/":
        src = src[:-1]
    src = src.split("/")
    make_exercise(src[0], DEST, src[1:])


