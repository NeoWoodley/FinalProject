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
include CMakeFiles/HelloWindow.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/HelloWindow.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/HelloWindow.dir/flags.make

CMakeFiles/HelloWindow.dir/HelloWindow/HelloWindow.cpp.o: CMakeFiles/HelloWindow.dir/flags.make
CMakeFiles/HelloWindow.dir/HelloWindow/HelloWindow.cpp.o: ../HelloWindow/HelloWindow.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/HelloWindow.dir/HelloWindow/HelloWindow.cpp.o"
	/Library/Developer/CommandLineTools/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/HelloWindow.dir/HelloWindow/HelloWindow.cpp.o -c /Users/neowoodley/Projects/CLionProjects/learnopengl/HelloWindow/HelloWindow.cpp

CMakeFiles/HelloWindow.dir/HelloWindow/HelloWindow.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/HelloWindow.dir/HelloWindow/HelloWindow.cpp.i"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/neowoodley/Projects/CLionProjects/learnopengl/HelloWindow/HelloWindow.cpp > CMakeFiles/HelloWindow.dir/HelloWindow/HelloWindow.cpp.i

CMakeFiles/HelloWindow.dir/HelloWindow/HelloWindow.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/HelloWindow.dir/HelloWindow/HelloWindow.cpp.s"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/neowoodley/Projects/CLionProjects/learnopengl/HelloWindow/HelloWindow.cpp -o CMakeFiles/HelloWindow.dir/HelloWindow/HelloWindow.cpp.s

# Object files for target HelloWindow
HelloWindow_OBJECTS = \
"CMakeFiles/HelloWindow.dir/HelloWindow/HelloWindow.cpp.o"

# External object files for target HelloWindow
HelloWindow_EXTERNAL_OBJECTS =

HelloWindow: CMakeFiles/HelloWindow.dir/HelloWindow/HelloWindow.cpp.o
HelloWindow: CMakeFiles/HelloWindow.dir/build.make
HelloWindow: external/glfw-3.1.2/src/libglfw3.a
HelloWindow: external/libGLEW_1130.a
HelloWindow: CMakeFiles/HelloWindow.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable HelloWindow"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/HelloWindow.dir/link.txt --verbose=$(VERBOSE)
	/Applications/CLion.app/Contents/bin/cmake/mac/bin/cmake -E copy /Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug/./HelloWindow /Users/neowoodley/Projects/CLionProjects/learnopengl/HelloWindow/

# Rule to build all files generated by this target.
CMakeFiles/HelloWindow.dir/build: HelloWindow

.PHONY : CMakeFiles/HelloWindow.dir/build

CMakeFiles/HelloWindow.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/HelloWindow.dir/cmake_clean.cmake
.PHONY : CMakeFiles/HelloWindow.dir/clean

CMakeFiles/HelloWindow.dir/depend:
	cd /Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/neowoodley/Projects/CLionProjects/learnopengl /Users/neowoodley/Projects/CLionProjects/learnopengl /Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug /Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug /Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug/CMakeFiles/HelloWindow.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/HelloWindow.dir/depend

