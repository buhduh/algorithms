NAME = percolation

ALGS_JAR = ../include/algs4.jar
CLASSPATH = build:$(ALGS_JAR)

SRC = $(wildcard src/*.java)
ZIP_SRC = $(filter-out src/Main.java, $(SRC))
CLASSES = classes

ZIP = build/percolation.zip

SRC_PATH = src

BUILD_DIRS = build

all: $(BUILD_DIRS) $(CLASSES) zip
	@java -cp $(CLASSPATH) Main
	
#might as well make sure it compiles at least
zip: $(CLASSES) $(ZIP) 
	@echo > /dev/null	

$(ZIP): $(ZIP_SRC) 
	@zip $@ $^	

$(CLASSES): 
	javac -cp $(CLASSPATH) -d build $(SRC)

$(BUILD_DIRS):
	@mkdir -p $@

clean:
	@rm -rf $(BUILD_DIRS)

.PHONY: build clean classes zip
