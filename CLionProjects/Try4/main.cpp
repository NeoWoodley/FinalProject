////#include <iostream>
////
////int main() {
////	std::cout << "Hello, World!" << std::endl;
////	return 0;
////}
//
//#include <stdio.h>
//#include <stdlib.h>
//#include <GL/glew.h>
//#include <GLFW/glfw3.h>
//#include <glm/glm.hpp>
//#include <iostream>
//using namespace glm;
//using namespace std;
//int main()
//{
//	// Initialise GLFW
//	glewExperimental = true; // Needed for core profile
//	if( !glfwInit() )
//	{
//		fprintf( stderr, "Failed to initialize GLFW\n" );
//		return -1;
//	}
//	glfwWindowHint(GLFW_SAMPLES, 4); // 4x antialiasing
//	glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3); // We want OpenGL 3.3
//	glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
//	glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE); // To make MacOS happy; should not be needed
//	glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE); // We don't want the old OpenGL
//
//// Open a window and create its OpenGL context
//	GLFWwindow* window; // (In the accompanying source code, this variable is global for simplicity)
//	window = glfwCreateWindow( 1024, 768, "Tutorial 01", NULL, NULL);
//	if( window == NULL ){
//		fprintf( stderr, "Failed to open GLFW window. If you have an Intel GPU, they are not 3.3 compatible. Try the 2.1 version of the tutorials.\n" );
//		glfwTerminate();
//		return -1;
//	}
//	glfwMakeContextCurrent(window); // Initialize GLEW
//	glewExperimental=true; // Needed in core profile
//	if (glewInit() != GLEW_OK) {
//		fprintf(stderr, "Failed to initialize GLEW\n");
//		return -1;
//	}
//	glfwSetInputMode(window, GLFW_STICKY_KEYS, GL_TRUE);
//
//	do{
//		// Clear the screen. It's not mentioned before Tutorial 02, but it can cause flickering, so it's there nonetheless.
//		glClear( GL_COLOR_BUFFER_BIT );
//
//		// Draw nothing, see you in tutorial 2 !
//
//		// Swap buffers
//		glfwSwapBuffers(window);
//		glfwPollEvents();
//
//	} // Check if the ESC key was pressed or the window was closed
//	while( glfwGetKey(window, GLFW_KEY_ESCAPE ) != GLFW_PRESS &&
//	       glfwWindowShouldClose(window) == 0 );
//}


#include <GL/glut.h>
#include <cstdio>
#include <cstdlib>
#include <cmath>

void init(){
	glClearColor(1.0, 1.0, 1.0, 0.0);   //Set display-window color to white.

	glMatrixMode(GL_PROJECTION);        //Set projection parameters.
	gluOrtho2D(0.0, 200.0, 0.0, 150.0);
}

void lineSegment(){
	glClear(GL_COLOR_BUFFER_BIT);   //Clear display window.

	glColor3f(1.0, 0.0, 0.0);       //Set line segment color to red.
	glBegin(GL_LINES);
	glVertex2i(180, 45);        //Specify line-segment geometry
	glVertex2i(10, 145);
	glEnd();

	glFlush();      //Process all OpenGL routines as quickly as possible.
}

int main(int argc, char **argv) {
	glutInit(&argc, argv);                          //Initialize GLUT.
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);    //Set display mode.
	glutInitWindowPosition(50, 100);    //Set top-left display-window position.
	glutInitWindowSize(400, 300);       //Set display-window width and height.
	glutCreateWindow("An example OpenGL Program");  //Create display window.

	init();                         //Execute initialization procedure.
	glutDisplayFunc(lineSegment);   //Send graphics to display window.
	glutMainLoop();                 //Display everything and wait.
	return 0;
}
