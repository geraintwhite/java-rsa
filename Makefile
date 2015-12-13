J = java
JC = javac
JDOC = javadoc
JUNIT = org.junit.runner.JUnitCore

JFLAGS = -Xlint:unchecked
CLASSPATH = lib/*:.jar

DOCS = doc
SOURCE = src
OUTPUT = build

SOURCES = $(shell find $(SOURCE) -name '*.java')
CLASSES = $(patsubst $(SOURCE)/%.java,$(OUTPUT)/%.class, $(SOURCES))
TESTS = $(filter %Tests.class, $(CLASSES))

ARGS = $(filter-out $@, $(MAKECMDGOALS))

define get_class_name
	$(subst .class,, $(subst /,., $(patsubst $(OUTPUT)/%,%, $(1))))
endef

all: $(CLASSES)

clean:
	rm -r $(OUTPUT)/*

test:
	$(eval ARGS = $(if $(ARGS), $(ARGS), $(call get_class_name, $(TESTS))))
	$(J) -cp $(OUTPUT):$(CLASSPATH) $(JUNIT) $(ARGS)

docs:
	$(JDOC) -d $(DOCS) -classpath $(OUTPUT):$(CLASSPATH) -sourcepath $(SOURCES)

run:
	$(J) -cp $(OUTPUT):$(CLASSPATH) $(ARGS)

$(OUTPUT)/%.class: $(SOURCE)/%.java
	@mkdir -p $(@D)
	$(JC) -g $(JFLAGS) -cp $(SOURCE):$(CLASSPATH) -d $(OUTPUT) $<
