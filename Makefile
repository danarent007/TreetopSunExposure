JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	src/treetopsunexposure/RecursiveProcess.java\
	src/treetopsunexposure/TreetopSunExposure.java
	

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
