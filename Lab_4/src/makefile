# makefile src

.SUFFIXES: .class .java

PACK=src4
TARG=Test
JC=javac -g
JM=java -ea -jar
JR=jar -cfe

OBJ=$(PACK)\Time.class \
	$(PACK)\Date.class \
    $(PACK)\Test.class

.java.class:
	$(JC) $<

run: build
	$(JM) $(TARG).jar

build: 	$(OBJ)
	$(JR) $(TARG).jar $(PACK).Test $(OBJ)

clean:
	for %f in ($(OBJ)) do del %f
	del $(TARG).jar

$(PACK)\src.Date.class: $(PACK)\Date.java makefile
$(PACK)\src.Time.class: $(PACK)\Time.java makefile
$(PACK)\src.Test.class: $(PACK)\Test.java makefile

# eof makefile src