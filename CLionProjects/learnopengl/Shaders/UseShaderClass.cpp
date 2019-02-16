////
//// Created by Neo Woodley on 2019-02-07.
////
//
//#include <iostream>
//
//// GLEW
//#define GLEW_STATIC 1
//
//#include <GL/glew.h>
//
//// GLFW
//#include <GLFW/glfw3.h>
//
//// Other includes
//#include "Shaders/Shader.h"
//
//// 函数声明
//void key_callback(GLFWwindow *window, int key, int scancode, int action, int mode);
//
//// Window维数
//const GLuint WIDTH = 800, HEIGHT = 600;
//
//// main函数，这是我们start application的入口并且进行game loop
//int main() {
//	// 初始化GLFW
//	glfwInit();
//	// 为GLFW设置所有需要的选项
//	glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
//	glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
//	glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
//	glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
//	glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);  // To make Mac OS happy
//
//	// 创建一个GLFWwindow对象，供我们调用GLFW的函数方法
//	GLFWwindow *window = glfwCreateWindow(WIDTH, HEIGHT, "LearningOpenGL", nullptr, nullptr);
//	glfwMakeContextCurrent(window);
//
//	// 设置需要的callback函数
//	glfwSetKeyCallback(window, key_callback);
//
//	// 设置true，使得GLFW知道使用一个最新的方法来获取函数指针并进行扩展
//	glewExperimental = GL_TRUE;
//	// 初始化GLEW来设置OpenGL函数指针
//	glewInit();
//
//	// 定义viewport维数
//	glViewport(0, 0, WIDTH, HEIGHT);
//
//	// 创建并编译我们的着色器程序shader program
//	Shader ourShader("Shaders/useshaderclass.vs", "Shaders/useshaderclass.frag");
//
//	// 设置vertex顶点数据、buffer和属性指针
//	GLfloat vertices[] = {
//			// Positions       // Color
//			0.5f, -0.5f, 0.0f, 1.0f, 0.0f, 0.0f,
//			-0.5f, -0.5f, 0.0f, 0.0f, 1.0f, 0.0f,
//			0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 1.0f
//	};
//	GLuint VBO, VAO;
//	glGenVertexArrays(1, &VAO);
//	glGenBuffers(1, &VBO);
//	// 首先绑定顶点数组对象VAO，然后绑定顶点缓冲对象VBO和属性指针
//	glBindVertexArray(VAO);
//	glBindBuffer(GL_ARRAY_BUFFER, VBO);
//	glBufferData(GL_ARRAY_BUFFER, sizeof(vertices), vertices, GL_STATIC_DRAW);
//
//	// 位置属性
//	glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 6 * sizeof(GLfloat), (GLvoid *) 0);
//	glEnableVertexAttribArray(0);
//	// 颜色属性
//	glVertexAttribPointer(1, 3, GL_FLOAT, GL_FALSE, 6 * sizeof(GLfloat), (GLvoid *) (3 * sizeof(GLfloat)));
//	glEnableVertexAttribArray(1);
//
//	// 解绑VAO
//	glBindVertexArray(0);
//
//	// Game loop
//	while (!glfwWindowShouldClose(window)) {
//		// 检查是否有任何event被activiated（按键被点击、鼠标移动）并call相应的response函数
//		glfwPollEvents();
//
//		// Render
//		// 清除colorbuffer
//		glClearColor(0.2f, 0.3f, 0.3f, 1.0f);
//		glClear(GL_COLOR_BUFFER_BIT);
//
//		// 渲染三角形
//		ourShader.Use();
//		glBindVertexArray(VAO);
//		glDrawArrays(GL_TRIANGLES, 0, 3);
//		glBindVertexArray(0);
//
//		// Swap the screen buffers
//		glfwSwapBuffers(window);
//	}
//
//	// 尽可能de-allcate所有的资源一旦它们完成了它们的生命周期
//	glDeleteVertexArrays(1, &VAO);
//	glDeleteBuffers(1, &VBO);
//	// Terminate GLFW，清除任何被GLFW分配的资源
//	glfwTerminate();
//	return 0;
//}
//
//// Is called whenever a key is pressed/release via GLFW
//void key_callback(GLFWwindow *window, int key, int scancode, int action, int mode) {
//	if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
//		glfwSetWindowShouldClose(window, GL_TRUE);
//	}
//}
//
//
////#include <external/glad/glad.h>
////#include <GLFW/glfw3.h>
////
//////#include <learnopengl/shader_s.h>
////#include "Shader.h"
////#include <iostream>
////
////void framebuffer_size_callback(GLFWwindow* window, int width, int height);
////void processInput(GLFWwindow *window);
////
////// settings
////const unsigned int SCR_WIDTH = 800;
////const unsigned int SCR_HEIGHT = 600;
////
////int main()
////{
////	// glfw: initialize and configure
////	// ------------------------------
////	glfwInit();
////	glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
////	glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
////	glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
////
////#ifdef __APPLE__
////	glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE); // uncomment this statement to fix compilation on OS X
////#endif
////
////	// glfw window creation
////	// --------------------
////	GLFWwindow* window = glfwCreateWindow(SCR_WIDTH, SCR_HEIGHT, "LearnOpenGL", NULL, NULL);
////	if (window == NULL)
////	{
////		std::cout << "Failed to create GLFW window" << std::endl;
////		glfwTerminate();
////		return -1;
////	}
////	glfwMakeContextCurrent(window);
////	glfwSetFramebufferSizeCallback(window, framebuffer_size_callback);
////
////	// glad: load all OpenGL function pointers
////	// ---------------------------------------
////	if (!gladLoadGLLoader((GLADloadproc)glfwGetProcAddress))
////	{
////		std::cout << "Failed to initialize GLAD" << std::endl;
////		return -1;
////	}
////
////	// build and compile our shader program
////	// ------------------------------------
////	Shader ourShader("useshaderclass.vs", "useshaderclass.frag"); // you can name your shader files however you like
////
////	// set up vertex data (and buffer(s)) and configure vertex attributes
////	// ------------------------------------------------------------------
////	float vertices[] = {
////			// positions         // colors
////			0.5f, -0.5f, 0.0f,  1.0f, 0.0f, 0.0f,  // bottom right
////			-0.5f, -0.5f, 0.0f,  0.0f, 1.0f, 0.0f,  // bottom left
////			0.0f,  0.5f, 0.0f,  0.0f, 0.0f, 1.0f   // top
////	};
////
////	unsigned int VBO, VAO;
////	glGenVertexArrays(1, &VAO);
////	glGenBuffers(1, &VBO);
////	// bind the Vertex Array Object first, then bind and set vertex buffer(s), and then configure vertex attributes(s).
////	glBindVertexArray(VAO);
////
////	glBindBuffer(GL_ARRAY_BUFFER, VBO);
////	glBufferData(GL_ARRAY_BUFFER, sizeof(vertices), vertices, GL_STATIC_DRAW);
////
////	// position attribute
////	glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 6 * sizeof(float), (void*)0);
////	glEnableVertexAttribArray(0);
////	// color attribute
////	glVertexAttribPointer(1, 3, GL_FLOAT, GL_FALSE, 6 * sizeof(float), (void*)(3 * sizeof(float)));
////	glEnableVertexAttribArray(1);
////
////	// You can unbind the VAO afterwards so other VAO calls won't accidentally modify this VAO, but this rarely happens. Modifying other
////	// VAOs requires a call to glBindVertexArray anyways so we generally don't unbind VAOs (nor VBOs) when it's not directly necessary.
////	// glBindVertexArray(0);
////
////
////	// render loop
////	// -----------
////	while (!glfwWindowShouldClose(window))
////	{
////		// input
////		// -----
////		processInput(window);
////
////		// render
////		// ------
////		glClearColor(0.2f, 0.3f, 0.3f, 1.0f);
////		glClear(GL_COLOR_BUFFER_BIT);
////
////		// render the triangle
////		ourShader.Use();
////		glBindVertexArray(VAO);
////		glDrawArrays(GL_TRIANGLES, 0, 3);
////
////		// glfw: swap buffers and poll IO events (keys pressed/released, mouse moved etc.)
////		// -------------------------------------------------------------------------------
////		glfwSwapBuffers(window);
////		glfwPollEvents();
////	}
////
////	// optional: de-allocate all resources once they've outlived their purpose:
////	// ------------------------------------------------------------------------
////	glDeleteVertexArrays(1, &VAO);
////	glDeleteBuffers(1, &VBO);
////
////	// glfw: terminate, clearing all previously allocated GLFW resources.
////	// ------------------------------------------------------------------
////	glfwTerminate();
////	return 0;
////}
////
////// process all input: query GLFW whether relevant keys are pressed/released this frame and react accordingly
////// ---------------------------------------------------------------------------------------------------------
////void processInput(GLFWwindow *window)
////{
////	if (glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS)
////		glfwSetWindowShouldClose(window, true);
////}
////
////// glfw: whenever the window size changed (by OS or user resize) this callback function executes
////// ---------------------------------------------------------------------------------------------
////void framebuffer_size_callback(GLFWwindow* window, int width, int height)
////{
////	// make sure the viewport matches the new window dimensions; note that width and
////	// height will be significantly larger than specified on retina displays.
////	glViewport(0, 0, width, height);
////}
//
//



#include <iostream>

// GLEW
#define GLEW_STATIC
#include <GL/glew.h>

// GLFW
#include <GLFW/glfw3.h>

// Other includes
#include "Shader.h"


// Function prototypes
void key_callback(GLFWwindow* window, int key, int scancode, int action, int mode);

// Window dimensions
const GLuint WIDTH = 800, HEIGHT = 600;

// The MAIN function, from here we start the application and run the game loop
int main()
{
	// Init GLFW
	glfwInit();
	// Set all the required options for GLFW
	glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
	glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
	glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
	glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
	glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);  // To make Mac OS happy

	// Create a GLFWwindow object that we can use for GLFW's functions
	GLFWwindow* window = glfwCreateWindow(WIDTH, HEIGHT, "LearnOpenGL", nullptr, nullptr);
	glfwMakeContextCurrent(window);

	// Set the required callback functions
	glfwSetKeyCallback(window, key_callback);

	// Set this to true so GLEW knows to use a modern approach to retrieving function pointers and extensions
	glewExperimental = GL_TRUE;
	// Initialize GLEW to setup the OpenGL Function pointers
	glewInit();

	// Define the viewport dimensions
	glViewport(0, 0, WIDTH, HEIGHT);


	// Build and compile our shader program
	Shader ourShader("/Users/neowoodley/Projects/CLionProjects/learnopengl/Shaders/useshaderclass.vs", "/Users/neowoodley/Projects/CLionProjects/learnopengl/Shaders/useshaderclass.frag");


	// Set up vertex data (and buffer(s)) and attribute pointers
	GLfloat vertices[] = {
			// Positions         // Colors
			0.5f, -0.5f, 0.0f,   1.0f, 0.0f, 0.0f,  // Bottom Right
			-0.5f, -0.5f, 0.0f,   0.0f, 1.0f, 0.0f,  // Bottom Left
			0.0f,  0.5f, 0.0f,   0.0f, 0.0f, 1.0f   // Top
	};
	GLuint VBO, VAO;
	glGenVertexArrays(1, &VAO);
	glGenBuffers(1, &VBO);
	// Bind the Vertex Array Object first, then bind and set vertex buffer(s) and attribute pointer(s).
	glBindVertexArray(VAO);

	glBindBuffer(GL_ARRAY_BUFFER, VBO);
	glBufferData(GL_ARRAY_BUFFER, sizeof(vertices), vertices, GL_STATIC_DRAW);

	// Position attribute
	glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 6 * sizeof(GLfloat), (GLvoid*)0);
	glEnableVertexAttribArray(0);
	// Color attribute
	glVertexAttribPointer(1, 3, GL_FLOAT, GL_FALSE, 6 * sizeof(GLfloat), (GLvoid*)(3 * sizeof(GLfloat)));
	glEnableVertexAttribArray(1);

	glBindVertexArray(0); // Unbind VAO


	// Game loop
	while (!glfwWindowShouldClose(window))
	{
		// Check if any events have been activiated (key pressed, mouse moved etc.) and call corresponding response functions
		glfwPollEvents();

		// Render
		// Clear the colorbuffer
		glClearColor(0.2f, 0.3f, 0.3f, 1.0f);
		glClear(GL_COLOR_BUFFER_BIT);

		// Draw the triangle
		ourShader.Use();
		glBindVertexArray(VAO);
		glDrawArrays(GL_TRIANGLES, 0, 3);
		glBindVertexArray(0);

		// Swap the screen buffers
		glfwSwapBuffers(window);
	}
	// Properly de-allocate all resources once they've outlived their purpose
	glDeleteVertexArrays(1, &VAO);
	glDeleteBuffers(1, &VBO);
	// Terminate GLFW, clearing any resources allocated by GLFW.
	glfwTerminate();
	return 0;
}

// Is called whenever a key is pressed/released via GLFW
void key_callback(GLFWwindow* window, int key, int scancode, int action, int mode)
{
	if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS)
		glfwSetWindowShouldClose(window, GL_TRUE);
}