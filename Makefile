

JC = javac
JVM = java
SRC = SecretSanta.java Pair.java
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
all: Pair SecretSanta TestPair TestSecretSanta

SecretSanta: $(addprefix src/,SecretSanta.java) Pair
	$(JC) $(DFLAG) $(SFLAG) $(addprefix src/,SecretSanta.java)

Pair: $(addprefix src/,Pair.java)
	$(JC) $(DFLAG) $(addprefix src/,Pair.java)

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
	$(RM) $(addprefix artifacts/,$(OBJ))
endif
ifeq ($(UNAME),Darwin)
	$(RM) $(addprefix artifacts/,$(OBJ))
endif
ifeq ($(UNAME),Windows)
	$(del artifacts/*.class)
endif

.PHONY:
	clean all
