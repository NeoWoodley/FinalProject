# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.13

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /Applications/CLion.app/Contents/bin/cmake/mac/bin/cmake

# The command to remove a file.
RM = /Applications/CLion.app/Contents/bin/cmake/mac/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /Users/neowoodley/Projects/CLionProjects/learnopengl

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/Transformations.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/Transformations.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/Transformations.dir/flags.make

CMakeFiles/Transformations.dir/Transformations/Transformations.cpp.o: CMakeFiles/Transformations.dir/flags.make
CMakeFiles/Transformations.dir/Transformations/Transformations.cpp.o: ../Transformations/Transformations.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/Transformations.dir/Transformations/Transformations.cpp.o"
	/Library/Developer/CommandLineTools/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/Transformations.dir/Transformations/Transformations.cpp.o -c /Users/neowoodley/Projects/CLionProjects/learnopengl/Transformations/Transformations.cpp

CMakeFiles/Transformations.dir/Transformations/Transformations.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/Transformations.dir/Transformations/Transformations.cpp.i"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/neowoodley/Projects/CLionProjects/learnopengl/Transformations/Transformations.cpp > CMakeFiles/Transformations.dir/Transformations/Transformations.cpp.i

CMakeFiles/Transformations.dir/Transformations/Transformations.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/Transformations.dir/Transformations/Transformations.cpp.s"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/neowoodley/Projects/CLionProjects/learnopengl/Transformations/Transformations.cpp -o CMakeFiles/Transformations.dir/Transformations/Transformations.cpp.s

# Object files for target Transformations
Transformations_OBJECTS = \
"CMakeFiles/Transformations.dir/Transformations/Transformations.cpp.o"

# External object files for target Transformations
Transformations_EXTERNAL_OBJECTS =

Transformations: CMakeFiles/Transformations.dir/Transformations/Transformations.cpp.o
Transformations: CMakeFiles/Transformations.dir/build.make
Transformations: external/glfw-3.1.2/src/libglfw3.a
Transformations: external/libGLEW_1130.a
Transformations: CMakeFiles/Transformations.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable Transformations"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/Transformations.dir/link.txt --verbose=$(VERBOSE)
	/Applications/CLion.app/Contents/bin/cmake/mac/bin/cmake -E copy /Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug/./Transformations /Users/neowoodley/Projects/CLionProjects/learnopengl/Transformations/

# Rule to build all files generated by this target.
CMakeFiles/Transformations.dir/build: Transformations

.PHONY : CMakeFiles/Transformations.dir/build

CMakeFiles/Transformations.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/Transformations.dir/cmake_clean.cmake
.PHONY : CMakeFiles/Transformations.dir/clean

CMakeFiles/Transformations.dir/depend:
	cd /Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/neowoodley/Projects/CLionProjects/learnopengl /Users/neowoodley/Projects/CLionProjects/learnopengl /Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug /Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug /Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug/CMakeFiles/Transformations.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/Transformations.dir/depend

