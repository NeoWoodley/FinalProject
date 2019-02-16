cmake_minimum_required(VERSION 3.13)
project(Try4)

set(CMAKE_CXX_STANDARD 14)

set(SOURCE_FILES try1.cpp try1.cpp)
add_executable(${PROJECT_NAME} ${SOURCE_FILES})

find_package(OpenGL REQUIRED)
find_package(GLUT REQUIRED)

set(GLAD_INCLUDE_DIRS /Users/neowoodley/Projects/CLionProjects/Try4/glad)
include_directories(${OPENGL_INCLUDE_DIRS} ${GLUT_INCLUDE_DIRS} ${GLAD_INCLUDE_DIRS})

target_link_libraries(${PROJECT_NAME} ${OPENGL_LIBRARIES} ${GLUT_LIBRARY} ${GLAD_INCLUDE_DIRS})

