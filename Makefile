# Author: Glenn Skelton
# Last Modified: November 11, 2018
# Simple make file for building Secret Santa and dependencies

JC = javac
JVM = java
SRC = SecretSanta.java Pair.java SecretSantaGUI.java
DFLAG = -d artifacts/
SFLAG = -sourcepath src/
CPFLAG = -cp artifacts/
TEST = TestSecretSanta.java TestPair.java
OBJ = $(SRC:.java=.class) $(TEST:.java=.class)
ifeq ($(OS),Windows_NT)
	UNAME := Windows
else
	UNAME := $(shell uname)
endif

ifeq (UNAME,Linux)
	DIR_SIZE := $(ls artifacts/ | wc -l)
endif
ifeq (UNAME,Darwin)
	DIR_SIZE := $(ls artifacts/ | wc -l)
endif
ifeq (UNAME,Windows)
	DIR_SIZE := $(dir artifacts/ | find /c ".class")
endif

#------------------------ MAKE RULES -------------------------#
run: Pair SecretSanta SecretSantaGUI
	$(JVM) $(CPFLAG) SecretSantaGUI

all: Pair SecretSanta TestPair TestSecretSanta

main: $(addprefix src/,SecretSantaGUI.java) Pair SecretSanta
	$(JC) $(DFLAG) $(CPFLAG) $(addprefix src/,SecretSantaGUI.java)

Pair: $(addprefix src/,Pair.java)
	$(JC) $(DFLAG) $(addprefix src/,Pair.java)

SecretSanta: $(addprefix src/,SecretSanta.java) Pair
	$(JC) $(DFLAG) $(SFLAG) $(addprefix src/,SecretSanta.java)

SecretSantaGUI: $(addprefix src/,SecretSantaGUI.java)
	$(JC) $(DFLAG) $(CPFLAG) $(addprefix src/,SecretSantaGUI.java)

TestSecretSanta: $(addprefix src/,TestSecretSanta.java) Pair SecretSanta
	$(JC) $(DFLAG) $(CPFLAG) $(addprefix src/,TestSecretSanta.java)

TestPair: $(addprefix src/,TestPair.java) Pair SecretSanta
	$(JC) $(DFLAG) $(CPFLAG) $(addprefix src/,TestPair.java)

#-------------------------- TESTS ----------------------------#
test1:
	$(JVM) $(CPFLAG) TestPair

test2:
	$(JVM) $(CPFLAG) TestSecretSanta


#------------------------- CLEAN UP --------------------------#
clean:
ifeq ($(UNAME),Linux)
ifneq ($(DIR_SIZE), 0)
	$(RM) $(addprefix artifacts/,$(OBJ))
else
	@echo No files to remove
endif
endif
ifeq ($(UNAME),Darwin)
ifneq ($(DIR_SIZE), 0)
	$(RM) $(addprefix artifacts/,$(OBJ))
else
	@echo No files to remove
endif
endif
# haven't finished the windows side yet
ifeq ($(UNAME),Windows)
	$(del artifacts/*.class)
endif

.PHONY:
	clean all
