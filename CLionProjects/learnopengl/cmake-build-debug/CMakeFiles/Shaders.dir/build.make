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
include CMakeFiles/Shaders.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/Shaders.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/Shaders.dir/flags.make

CMakeFiles/Shaders.dir/Shaders/UseShaderClass.cpp.o: CMakeFiles/Shaders.dir/flags.make
CMakeFiles/Shaders.dir/Shaders/UseShaderClass.cpp.o: ../Shaders/UseShaderClass.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/Shaders.dir/Shaders/UseShaderClass.cpp.o"
	/Library/Developer/CommandLineTools/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/Shaders.dir/Shaders/UseShaderClass.cpp.o -c /Users/neowoodley/Projects/CLionProjects/learnopengl/Shaders/UseShaderClass.cpp

CMakeFiles/Shaders.dir/Shaders/UseShaderClass.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/Shaders.dir/Shaders/UseShaderClass.cpp.i"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/neowoodley/Projects/CLionProjects/learnopengl/Shaders/UseShaderClass.cpp > CMakeFiles/Shaders.dir/Shaders/UseShaderClass.cpp.i

CMakeFiles/Shaders.dir/Shaders/UseShaderClass.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/Shaders.dir/Shaders/UseShaderClass.cpp.s"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/neowoodley/Projects/CLionProjects/learnopengl/Shaders/UseShaderClass.cpp -o CMakeFiles/Shaders.dir/Shaders/UseShaderClass.cpp.s

# Object files for target Shaders
Shaders_OBJECTS = \
"CMakeFiles/Shaders.dir/Shaders/UseShaderClass.cpp.o"

# External object files for target Shaders
Shaders_EXTERNAL_OBJECTS =

Shaders: CMakeFiles/Shaders.dir/Shaders/UseShaderClass.cpp.o
Shaders: CMakeFiles/Shaders.dir/build.make
Shaders: external/glfw-3.1.2/src/libglfw3.a
Shaders: external/libGLEW_1130.a
Shaders: CMakeFiles/Shaders.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable Shaders"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/Shaders.dir/link.txt --verbose=$(VERBOSE)
	/Applications/CLion.app/Contents/bin/cmake/mac/bin/cmake -E copy /Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug/./Shaders /Users/neowoodley/Projects/CLionProjects/learnopengl/Shaders/

# Rule to build all files generated by this target.
CMakeFiles/Shaders.dir/build: Shaders

.PHONY : CMakeFiles/Shaders.dir/build

CMakeFiles/Shaders.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/Shaders.dir/cmake_clean.cmake
.PHONY : CMakeFiles/Shaders.dir/clean

CMakeFiles/Shaders.dir/depend:
	cd /Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/neowoodley/Projects/CLionProjects/learnopengl /Users/neowoodley/Projects/CLionProjects/learnopengl /Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug /Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug /Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug/CMakeFiles/Shaders.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/Shaders.dir/depend
