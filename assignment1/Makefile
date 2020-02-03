NAME = percolation

ALGS_JAR = ../include/algs4.jar
CLASSPATH = build:$(ALGS_JAR)

SRC = $(wildcard src/*.java)
CLASSES = classes

SRC_PATH = src

BUILD_DIRS = build

all: $(BUILD_DIRS) $(CLASSES) $(ZIP) 
	@java -cp $(CLASSPATH) Main

$(CLASSES): 
	javac -cp $(CLASSPATH) -d build $(SRC)

$(BUILD_DIRS):
	@mkdir -p $@

clean:
	@rm -rf $(BUILD_DIRS)

.PHONY: build clean classes
