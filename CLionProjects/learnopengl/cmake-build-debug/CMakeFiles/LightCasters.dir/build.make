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
include CMakeFiles/LightCasters.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/LightCasters.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/LightCasters.dir/flags.make

CMakeFiles/LightCasters.dir/LightCasters/SmoothSpotlight.cpp.o: CMakeFiles/LightCasters.dir/flags.make
CMakeFiles/LightCasters.dir/LightCasters/SmoothSpotlight.cpp.o: ../LightCasters/SmoothSpotlight.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/LightCasters.dir/LightCasters/SmoothSpotlight.cpp.o"
	/Library/Developer/CommandLineTools/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/LightCasters.dir/LightCasters/SmoothSpotlight.cpp.o -c /Users/neowoodley/Projects/CLionProjects/learnopengl/LightCasters/SmoothSpotlight.cpp

CMakeFiles/LightCasters.dir/LightCasters/SmoothSpotlight.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/LightCasters.dir/LightCasters/SmoothSpotlight.cpp.i"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/neowoodley/Projects/CLionProjects/learnopengl/LightCasters/SmoothSpotlight.cpp > CMakeFiles/LightCasters.dir/LightCasters/SmoothSpotlight.cpp.i

CMakeFiles/LightCasters.dir/LightCasters/SmoothSpotlight.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/LightCasters.dir/LightCasters/SmoothSpotlight.cpp.s"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/neowoodley/Projects/CLionProjects/learnopengl/LightCasters/SmoothSpotlight.cpp -o CMakeFiles/LightCasters.dir/LightCasters/SmoothSpotlight.cpp.s

# Object files for target LightCasters
LightCasters_OBJECTS = \
"CMakeFiles/LightCasters.dir/LightCasters/SmoothSpotlight.cpp.o"

# External object files for target LightCasters
LightCasters_EXTERNAL_OBJECTS =

LightCasters: CMakeFiles/LightCasters.dir/LightCasters/SmoothSpotlight.cpp.o
LightCasters: CMakeFiles/LightCasters.dir/build.make
LightCasters: external/glfw-3.1.2/src/libglfw3.a
LightCasters: external/libGLEW_1130.a
LightCasters: CMakeFiles/LightCasters.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable LightCasters"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/LightCasters.dir/link.txt --verbose=$(VERBOSE)
	/Applications/CLion.app/Contents/bin/cmake/mac/bin/cmake -E copy /Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug/./LightCasters /Users/neowoodley/Projects/CLionProjects/learnopengl/LightCasters/

# Rule to build all files generated by this target.
CMakeFiles/LightCasters.dir/build: LightCasters

.PHONY : CMakeFiles/LightCasters.dir/build

CMakeFiles/LightCasters.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/LightCasters.dir/cmake_clean.cmake
.PHONY : CMakeFiles/LightCasters.dir/clean

CMakeFiles/LightCasters.dir/depend:
	cd /Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/neowoodley/Projects/CLionProjects/learnopengl /Users/neowoodley/Projects/CLionProjects/learnopengl /Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug /Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug /Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug/CMakeFiles/LightCasters.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/LightCasters.dir/depend

