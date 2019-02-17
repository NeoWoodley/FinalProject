cmake_minimum_required(VERSION 3.13)
project(learnopengl)

#set(CMAKE_CXX_STANDARD 14)
#
#add_executable(learnopengl main.cpp)

#cmake_minimum_required(VERSION 3.13)
#project(Try4_1)

find_package(OpenGL REQUIRED)
#find_package(GLUT REQUIRED)

if (CMAKE_BINARY_DIR STREQUAL CMAKE_SOURCE_DIR)
	message(FATAL_ERROR "Please select another Build Directory ! (and give it a clever name, like bin_Visual2012_64bits/)")
endif ()
if (CMAKE_SOURCE_DIR MATCHES " ")
	message("Your Source Directory contains spaces. If you experience problems when compiling, this can be the cause.")
endif ()
if (CMAKE_BINARY_DIR MATCHES " ")
	message("Your Build Directory contains spaces. If you experience problems when compiling, this can be the cause.")
endif ()

# lib
link_directories(${CMAKE_SOURCE_DIR}/lib)

# Compile external dependencies
add_subdirectory(external)

# On Visual 2005 and above, this module can set the debug working directory
cmake_policy(SET CMP0026 OLD)
list(APPEND CMAKE_MODULE_PATH "${CMAKE_SOURCE_DIR}/external/rpavlik-cmake-modules-fe2273")
include(CreateLaunchers)
include(MSVCMultipleProcessCompile) # /MP

if (INCLUDE_DISTRIB)
	add_subdirectory(distrib)
endif (INCLUDE_DISTRIB)

# 添加头文件路径
include_directories(
		external/AntTweakBar-1.16/include/
		external/glfw-3.1.2/include/
		# GLM是一个只有头文件的库，我们只需要包含对应的头文件就行了，不用链接和编译
		external/glm-0.9.7.1/
		external/glew-1.13.0/include/
		external/assimp-3.0.1270/include/
		external/bullet-2.81-rev2613/src/
		# 我自己添加的
		external/soil/include/
#		external/lib/
#		external/glad/
		.
)

## GLAD ###
#add_library(GLAD "glad.c")
#set(LIBS ${LIBS} GLAD)

# 我自己添加的
# 设置SOIL库目录
#link_directories(lib/)
# 要链接的库
link_libraries(soil)

set(ALL_LIBS
		${OPENGL_LIBRARY}
		glfw
		GLEW_1130
		# 我自己添加的
#		soil
#		glad
		)

add_definitions(
		-DTW_STATIC
		-DTW_NO_LIB_PRAGMA
		-DTW_NO_DIRECT3D
		-DGLEW_STATIC
		-D_CRT_SECURE_NO_WARNINGS
)

# test1
add_executable(test1
		test1/test1.cpp test1/ReadFile.cpp test1/ReadFile.h)
target_link_libraries(test1
		${ALL_LIBS}
		)
# Xcode and Visual working directories
set_target_properties(test1 PROPERTIES XCODE_ATTRIBUTE_CONFIGURATION_BUILD_DIR
		"${CMAKE_CURRENT_SOURCE_DIR}/test1/")
create_target_launcher(test1 WORKING_DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/test1/")

# HelloWindow
add_executable(HelloWindow
		HelloWindow/HelloWindow.cpp)
# 目标文件与库文件进行链接
target_link_libraries(HelloWindow
		${ALL_LIBS}
		)
# Xcode and Visual working directories
set_target_properties(HelloWindow PROPERTIES XCODE_ATTRIBUTE_CONFIGURATION_BUILD_DIR
		"${CMAKE_CURRENT_SOURCE_DIR}/HelloWindow/")
create_target_launcher(HelloWindow WORKING_DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/HelloWindow/")

# HelloTriangle
add_executable(HelloTriangle
		HelloTriangle/HelloTriangle.cpp
#		HelloTriangle/TwoTriangles.cpp
#		HelloTriangle/TwoDifferentTriangles.cpp
		)
target_link_libraries(HelloTriangle
		${ALL_LIBS}
		)
# Xcode and Visual working directories
set_target_properties(HelloTriangle PROPERTIES XCODE_ATTRIBUTE_CONFIGURATION_BUILD_DIR
		"${CMAKE_CURRENT_SOURCE_DIR}/HelloTriangle/")
create_target_launcher(HelloTriangle WORKING_DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/HelloTriangle/")

# HelloSquare
add_executable(HelloSquare
		HelloSquare/HelloSquare.cpp)
target_link_libraries(HelloSquare
		${ALL_LIBS}
		)
# Xcode and Visual working directories
set_target_properties(HelloSquare PROPERTIES XCODE_ATTRIBUTE_CONFIGURATION_BUILD_DIR
		"${CMAKE_CURRENT_SOURCE_DIR}/HelloSquare/")
create_target_launcher(HelloSquare WORKING_DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/HelloSquare/")

# Shaders
add_executable(Shaders
		#		Shaders/Shaders.cpp
#		Shaders/ColorfulShaders.cpp
#		Shaders/useshaderclass.vs
#		Shaders/useshaderclass.frag
		Shaders/UseShaderClass.cpp)
target_link_libraries(Shaders
		${ALL_LIBS}
		)
# Xcode and Visual working directories
set_target_properties(Shaders PROPERTIES XCODE_ATTRIBUTE_CONFIGURATION_BUILD_DIR
		"${CMAKE_CURRENT_SOURCE_DIR}/Shaders/")
create_target_launcher(Shaders WORKING_DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/Shaders/")

# Textures
add_executable(Textures
#		Textures/Textures.cpp
		Textures/TwoTextures.cpp)
target_link_libraries(Textures
		${ALL_LIBS}
		)
# Xcode and Visual working directories
set_target_properties(Textures PROPERTIES XCODE_ATTRIBUTE_CONFIGURATION_BUILD_DIR
		"${CMAKE_CURRENT_SOURCE_DIR}/Textures/")
create_target_launcher(Textures WORKING_DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/Textures/")

# Transformations
add_executable(Transformations
		Transformations/Transformations.cpp
		)
target_link_libraries(Transformations
		${ALL_LIBS}
		)
# Xcode and Visual working directories
set_target_properties(Transformations PROPERTIES XCODE_ATTRIBUTE_CONFIGURATION_BUILD_DIR
		"${CMAKE_CURRENT_SOURCE_DIR}/Transformations/")
create_target_launcher(Transformations WORKING_DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/Transformations/")

# CoordinateSystems
add_executable(CoordinateSystems
		CoordinateSystems/CoordinateSystems.cpp
		)
target_link_libraries(CoordinateSystems
		${ALL_LIBS}
		)
# Xcode and Visual working directories
set_target_properties(CoordinateSystems PROPERTIES XCODE_ATTRIBUTE_CONFIGURATION_BUILD_DIR
		"${CMAKE_CURRENT_SOURCE_DIR}/CoordinateSystems/")
create_target_launcher(CoordinateSystems WORKING_DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/CoordinateSystems/")

# Camera
add_executable(Camera
#		Camera/CameraCircle.cpp
#		Camera/CameraFree.cpp
#		Camera/EulerAngle.cpp
#		Camera/Camera.cpp
#		Camera/Camera.h
		Camera/UseCameraClass.cpp BasicLighting/BasicLighting.cpp)
target_link_libraries(Camera
		${ALL_LIBS}
		)
# Xcode and Visual working directories
set_target_properties(Camera PROPERTIES XCODE_ATTRIBUTE_CONFIGURATION_BUILD_DIR
		"${CMAKE_CURRENT_SOURCE_DIR}/Camera/")
create_target_launcher(Camera WORKING_DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/Camera/")

# Colors
add_executable(Colors
		Colors/Colors.cpp)
target_link_libraries(Colors
		${ALL_LIBS}
		)
# Xcode and Visual working directories
set_target_properties(Colors PROPERTIES XCODE_ATTRIBUTE_CONFIGURATION_BUILD_DIR
		"${CMAKE_CURRENT_SOURCE_DIR}/Colors/")
create_target_launcher(Colors WORKING_DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/Colors/")

# BasicLighting
add_executable(BasicLighting
#		BasicLighting/BasicLighting.cpp
		BasicLighting/SpecularHighlight.cpp)
target_link_libraries(BasicLighting
		${ALL_LIBS}
		)
# Xcode and Visual working directories
set_target_properties(BasicLighting PROPERTIES XCODE_ATTRIBUTE_CONFIGURATION_BUILD_DIR
		"${CMAKE_CURRENT_SOURCE_DIR}/BasicLighting/")
create_target_launcher(BasicLighting WORKING_DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/BasicLighting/")

# Materials
add_executable(Materials
		Materials/Materials.cpp)
target_link_libraries(Materials
		${ALL_LIBS}
		)
# Xcode and Visual working directories
set_target_properties(Materials PROPERTIES XCODE_ATTRIBUTE_CONFIGURATION_BUILD_DIR
		"${CMAKE_CURRENT_SOURCE_DIR}/Materials/")
create_target_launcher(Materials WORKING_DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/Materials/")

# LightingMaps
add_executable(LightingMaps
		LightingMaps/LightingMaps.cpp)
target_link_libraries(LightingMaps
		${ALL_LIBS}
		)
# Xcode and Visual working directories
set_target_properties(LightingMaps PROPERTIES XCODE_ATTRIBUTE_CONFIGURATION_BUILD_DIR
		"${CMAKE_CURRENT_SOURCE_DIR}/LightingMaps/")
create_target_launcher(LightingMaps WORKING_DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/LightingMaps/")

# LightCasters
add_executable(LightCasters
#		LightCasters/DirectionalLight.cpp
#		LightCasters/PointLight.cpp
#		LightCasters/SpotLight.cpp
		LightCasters/SmoothSpotlight.cpp)
target_link_libraries(LightCasters
		${ALL_LIBS}
		)
# Xcode and Visual working directories
set_target_properties(LightCasters PROPERTIES XCODE_ATTRIBUTE_CONFIGURATION_BUILD_DIR
		"${CMAKE_CURRENT_SOURCE_DIR}/LightCasters/")
create_target_launcher(LightCasters WORKING_DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/LightCasters/")

# MultipleLights
add_executable(MultipleLights
		MultipleLights/MultipleLights.cpp)
target_link_libraries(MultipleLights
		${ALL_LIBS}
		)
# Xcode and Visual working directories
set_target_properties(MultipleLights PROPERTIES XCODE_ATTRIBUTE_CONFIGURATION_BUILD_DIR
		"${CMAKE_CURRENT_SOURCE_DIR}/MultipleLights/")
create_target_launcher(MultipleLights WORKING_DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/MultipleLights/")

# Mesh
add_executable(Mesh
		Mesh/Mesh.cpp)
target_link_libraries(Mesh
		${ALL_LIBS}
		)
# Xcode and Visual working directories
set_target_properties(Mesh PROPERTIES XCODE_ATTRIBUTE_CONFIGURATION_BUILD_DIR
		"${CMAKE_CURRENT_SOURCE_DIR}/Mesh/")
create_target_launcher(Mesh WORKING_DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/Mesh/")

SOURCE_GROUP(common REGULAR_EXPRESSION ".*/common/.*")
SOURCE_GROUP(shaders REGULAR_EXPRESSION ".*/.*shader$")


if (NOT ${CMAKE_GENERATOR} MATCHES "Xcode")
	add_custom_command(
			TARGET test1 POST_BUILD
			COMMAND ${CMAKE_COMMAND} -E copy
			"${CMAKE_CURRENT_BINARY_DIR}/${CMAKE_CFG_INTDIR}/test1${CMAKE_EXECUTABLE_SUFFIX}"
			"${CMAKE_CURRENT_SOURCE_DIR}/test1/"
	)
	add_custom_command(
			TARGET HelloWindow POST_BUILD
			COMMAND ${CMAKE_COMMAND} -E copy
			"${CMAKE_CURRENT_BINARY_DIR}/${CMAKE_CFG_INTDIR}/HelloWindow${CMAKE_EXECUTABLE_SUFFIX}"
			"${CMAKE_CURRENT_SOURCE_DIR}/HelloWindow/"
	)
	add_custom_command(
			TARGET HelloTriangle POST_BUILD
			COMMAND ${CMAKE_COMMAND} -E copy
			"${CMAKE_CURRENT_BINARY_DIR}/${CMAKE_CFG_INTDIR}/HelloTriangle${CMAKE_EXECUTABLE_SUFFIX}"
			"${CMAKE_CURRENT_SOURCE_DIR}/HelloTriangle/"
	)
	add_custom_command(
			TARGET HelloSquare POST_BUILD
			COMMAND ${CMAKE_COMMAND} -E copy
			"${CMAKE_CURRENT_BINARY_DIR}/${CMAKE_CFG_INTDIR}/HelloSquare${CMAKE_EXECUTABLE_SUFFIX}"
			"${CMAKE_CURRENT_SOURCE_DIR}/HelloSquare/"
	)
	add_custom_command(
			TARGET Shaders POST_BUILD
			COMMAND ${CMAKE_COMMAND} -E copy
			"${CMAKE_CURRENT_BINARY_DIR}/${CMAKE_CFG_INTDIR}/Shaders${CMAKE_EXECUTABLE_SUFFIX}"
			"${CMAKE_CURRENT_SOURCE_DIR}/Shaders/"
	)
	add_custom_command(
			TARGET Textures POST_BUILD
			COMMAND ${CMAKE_COMMAND} -E copy
			"${CMAKE_CURRENT_BINARY_DIR}/${CMAKE_CFG_INTDIR}/Textures${CMAKE_EXECUTABLE_SUFFIX}"
			"${CMAKE_CURRENT_SOURCE_DIR}/Textures/"
	)
	add_custom_command(
			TARGET Transformations POST_BUILD
			COMMAND ${CMAKE_COMMAND} -E copy
			"${CMAKE_CURRENT_BINARY_DIR}/${CMAKE_CFG_INTDIR}/Transformations${CMAKE_EXECUTABLE_SUFFIX}"
			"${CMAKE_CURRENT_SOURCE_DIR}/Transformations/"
	)
	add_custom_command(
			TARGET CoordinateSystems POST_BUILD
			COMMAND ${CMAKE_COMMAND} -E copy
			"${CMAKE_CURRENT_BINARY_DIR}/${CMAKE_CFG_INTDIR}/CoordinateSystems${CMAKE_EXECUTABLE_SUFFIX}"
			"${CMAKE_CURRENT_SOURCE_DIR}/CoordinateSystems/"
	)
	add_custom_command(
			TARGET Camera POST_BUILD
			COMMAND ${CMAKE_COMMAND} -E copy
			"${CMAKE_CURRENT_BINARY_DIR}/${CMAKE_CFG_INTDIR}/Camera${CMAKE_EXECUTABLE_SUFFIX}"
			"${CMAKE_CURRENT_SOURCE_DIR}/Camera/"
	)
	add_custom_command(
			TARGET Colors POST_BUILD
			COMMAND ${CMAKE_COMMAND} -E copy
			"${CMAKE_CURRENT_BINARY_DIR}/${CMAKE_CFG_INTDIR}/Colors${CMAKE_EXECUTABLE_SUFFIX}"
			"${CMAKE_CURRENT_SOURCE_DIR}/Colors/"
	)
	add_custom_command(
			TARGET BasicLighting POST_BUILD
			COMMAND ${CMAKE_COMMAND} -E copy
			"${CMAKE_CURRENT_BINARY_DIR}/${CMAKE_CFG_INTDIR}/BasicLighting${CMAKE_EXECUTABLE_SUFFIX}"
			"${CMAKE_CURRENT_SOURCE_DIR}/BasicLighting/"
	)
	add_custom_command(
			TARGET Materials POST_BUILD
			COMMAND ${CMAKE_COMMAND} -E copy
			"${CMAKE_CURRENT_BINARY_DIR}/${CMAKE_CFG_INTDIR}/Materials${CMAKE_EXECUTABLE_SUFFIX}"
			"${CMAKE_CURRENT_SOURCE_DIR}/Materials/"
	)
	add_custom_command(
			TARGET LightingMaps POST_BUILD
			COMMAND ${CMAKE_COMMAND} -E copy
			"${CMAKE_CURRENT_BINARY_DIR}/${CMAKE_CFG_INTDIR}/LightingMaps${CMAKE_EXECUTABLE_SUFFIX}"
			"${CMAKE_CURRENT_SOURCE_DIR}/LightingMaps/"
	)
	add_custom_command(
			TARGET LightCasters POST_BUILD
			COMMAND ${CMAKE_COMMAND} -E copy
			"${CMAKE_CURRENT_BINARY_DIR}/${CMAKE_CFG_INTDIR}/LightCasters${CMAKE_EXECUTABLE_SUFFIX}"
			"${CMAKE_CURRENT_SOURCE_DIR}/LightCasters/"
	)
	add_custom_command(
			TARGET MultipleLights POST_BUILD
			COMMAND ${CMAKE_COMMAND} -E copy
			"${CMAKE_CURRENT_BINARY_DIR}/${CMAKE_CFG_INTDIR}/MultipleLights${CMAKE_EXECUTABLE_SUFFIX}"
			"${CMAKE_CURRENT_SOURCE_DIR}/MultipleLights/"
	)
	add_custom_command(
			TARGET Mesh POST_BUILD
			COMMAND ${CMAKE_COMMAND} -E copy
			"${CMAKE_CURRENT_BINARY_DIR}/${CMAKE_CFG_INTDIR}/Mesh${CMAKE_EXECUTABLE_SUFFIX}"
			"${CMAKE_CURRENT_SOURCE_DIR}/Mesh/"
	)
elseif (${CMAKE_GENERATOR} MATCHES "Xcode")

endif (NOT ${CMAKE_GENERATOR} MATCHES "Xcode")
