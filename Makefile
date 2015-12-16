J = java
JC = javac
JAR = jar
JDOC = javadoc
JUNIT = org.junit.runner.JUnitCore

JFLAGS = -Xlint:unchecked
CLASSPATH = lib/*:.jar

JARFILE = rsa.jar
MAIN = RSA.CLI
DOCS = doc
SOURCE = src
OUTPUT = build

SOURCES = $(shell find $(SOURCE) -name '*.java')
CLASSES = $(patsubst $(SOURCE)/%.java,$(OUTPUT)/%.class, $(SOURCES))
TESTS = $(filter %Tests.class, $(CLASSES))

define get_class_name
$(subst .class,,$(subst /,.,$(patsubst $(OUTPUT)/%,%,$(1))))
endef

all: $(CLASSES) $(JARFILE)

clean:
	rm -r $(OUTPUT)
	rm -r $(JARFILE)

test:
	$(J) -cp $(OUTPUT):$(CLASSPATH) $(JUNIT) $(call get_class_name,$(TESTS))

docs:
	$(JDOC) -d $(DOCS) -classpath $(OUTPUT):$(CLASSPATH) $(SOURCES)

$(JARFILE): $(CLASSES)
	$(JAR) cfe $(JARFILE) $(MAIN) -C $(OUTPUT) .

$(OUTPUT)/%.class: $(SOURCE)/%.java
	@mkdir -p $(@D)
	$(JC) -g $(JFLAGS) -cp $(SOURCE):$(CLASSPATH) -d $(OUTPUT) $<
