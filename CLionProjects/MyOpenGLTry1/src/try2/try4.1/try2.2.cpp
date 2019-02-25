//
// Created by Neo Woodley on 2019-02-22.
//

#include <GLTools.h>  // OpenGL toolkit
#include <GLMatrixStack.h>
#include <GLFrame.h>
#include <GLFrustum.h>
#include <GLGeometryTransform.h>
#include <GLBatch.h>
#include <StopWatch.h>
#include <math.h>

#define FREEGLUT_STATIC  //在windows和linux上，使用freeglut静态版本，需要添加这一行，否则会出现错误

#include <GL/glut.h>
//#include <glut.h>

#pragma comment(lib, "gltools.lib")//要加上这一行链接一下gltools库
#define NUM_SPHERES 50
GLFrame spheres[NUM_SPHERES];
GLShaderManager shaderManager;          // 着色器管理器
GLMatrixStack modelViewMatrix;        // 模型视图矩阵堆栈
GLMatrixStack projectionMatrix;       // 投影矩阵堆栈
GLFrustum viewFrustum;            // 透视投影会用到
GLGeometryTransform transformPipeline;      // 几何变换管线
//GLTriangleBatch torusBatch;    // 圆环
GLBatch floorBatch;    // 地板
GLTriangleBatch sphereBatch;   // 球
GLFrame cameraFrame;   // 摄像机
void SetupRC() {
	shaderManager.InitializeStockShaders();  //初始化着色器管理器
	glEnable(GL_DEPTH_TEST);  //开启深度测试
	glClearColor(0.0f, 0.0f, 0.0f, 1.0f);  //背景设置成黑色
//	gltMakeTorus(torusBatch, 0.4f, 0.15f, 30, 30);  //创建花环
	gltMakeSphere(sphereBatch, 0.1f, 26, 13);  //创建球
	floorBatch.Begin(GL_LINES, 324);  //创建地板
	// 为地板生成位置向量
	for (GLfloat x = -20.0; x <= 20.0f; x += 0.5) {
		floorBatch.Vertex3f(x, -0.55f, 20.0f);
		floorBatch.Vertex3f(x, -0.55f, -20.0f);
		floorBatch.Vertex3f(20.0f, -0.55f, x);
		floorBatch.Vertex3f(-20.0f, -0.55f, x);
	}
	floorBatch.End();
	for (int i = 0; i < NUM_SPHERES; i++) {//随机球的位置
		GLfloat x = ((GLfloat) ((rand() % 400) - 200) * 0.1f);
		GLfloat z = ((GLfloat) ((rand() % 400) - 200) * 0.1f);
		spheres[i].SetOrigin(x, 0.0f, z);
	}
}

void ChangeSize(int nWidth, int nHeight) {
	glViewport(0, 0, nWidth, nHeight); //窗口变换的时候，重新设置视口
	viewFrustum.SetPerspective(35.0f, float(nWidth) / float(nHeight), 1.0f, 100.0f);//设置透视投影的参数
	projectionMatrix.LoadMatrix(viewFrustum.GetProjectionMatrix());  //获取透视投影矩阵放到透视投影矩阵堆栈中
	transformPipeline.SetMatrixStacks(modelViewMatrix, projectionMatrix);//渲染管线将两者放进去
}

void RenderScene(void) {
	static GLfloat vFloorColor[] = {0.0f, 1.0f, 0.0f, 1.0f};//初始化颜色
	static GLfloat vTorusColor[] = {1.0f, 0.0f, 0.0f, 1.0f};
	static GLfloat vSphereColor[] = {0.0f, 0.0f, 1.0f, 1.0f};
	static CStopWatch rotTimer;
	float yRot = rotTimer.GetElapsedSeconds() * 60.0f;//根据时间来确定旋转的角度
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	modelViewMatrix.PushMatrix();
	M3DMatrix44f mCamera;
	cameraFrame.GetCameraMatrix(mCamera);//摄像机帧获取位置
	modelViewMatrix.PushMatrix(mCamera);//模型视图矩阵把摄像机放入栈底
	M3DVector4f vLightPos = {0.0f, 10.0f, 5.0f, 1.0f};//光源位置
	M3DVector4f vLightEyePos;
	m3dTransformVector4(vLightEyePos, vLightPos, mCamera);
	shaderManager.UseStockShader(GLT_SHADER_FLAT,
	                             transformPipeline.GetModelViewProjectionMatrix(),
	                             vFloorColor);//使用点光源着色器
	floorBatch.Draw();//绘制地板
//	for (int i = 0; i < NUM_SPHERES; i++) {
//		modelViewMatrix.PushMatrix();
//		modelViewMatrix.MultMatrix(spheres[i]);//更新球的位置
//		shaderManager.UseStockShader(GLT_SHADER_POINT_LIGHT_DIFF, transformPipeline.GetModelViewMatrix(),
//		                             transformPipeline.GetProjectionMatrix(), vLightEyePos, vSphereColor);
//		sphereBatch.Draw();
//		modelViewMatrix.PopMatrix();
//	}
	
	modelViewMatrix.Translate(0.0f, 0.0f, -2.5f); //向内移动2.5距离
	modelViewMatrix.PushMatrix();//保存移动
	modelViewMatrix.Rotate(yRot, 0.0f, 1.0f, 0.0f);//旋转
	shaderManager.UseStockShader(GLT_SHADER_POINT_LIGHT_DIFF, transformPipeline.GetModelViewMatrix(),
	                             transformPipeline.GetProjectionMatrix(), vLightEyePos, vTorusColor);
//	torusBatch.Draw();//绘制花环
	modelViewMatrix.PopMatrix();//pop掉前面的旋转，加入新的旋转
	
	modelViewMatrix.Rotate(yRot * -2.0f, 0.0f, 1.0f, 0.0f);
	modelViewMatrix.Translate(0.8f, 0.0f, 0.0f);
	shaderManager.UseStockShader(GLT_SHADER_POINT_LIGHT_DIFF, transformPipeline.GetModelViewMatrix(),
	                             transformPipeline.GetProjectionMatrix(), vLightEyePos, vSphereColor);
	sphereBatch.Draw();
	modelViewMatrix.PopMatrix();
	
	modelViewMatrix.PopMatrix();//清空堆栈
	glutSwapBuffers();
	glutPostRedisplay();
}

void SpecialKeys(int key, int x, int y)//处理摄像机的移动
{
	float linear = 0.1f;
	float angle = float(m3dDegToRad(5.0f));
	if (key == GLUT_KEY_UP)
		cameraFrame.MoveForward(linear);
	if (key == GLUT_KEY_DOWN)
		cameraFrame.MoveForward(-linear);
	if (key == GLUT_KEY_LEFT)
		cameraFrame.RotateWorld(angle, 0.0f, 1.0f, 0.0f);
	if (key == GLUT_KEY_RIGHT)
		cameraFrame.RotateWorld(-angle, 0.0f, 1.0f, 0.0f);
}

int main(int argc, char *argv[]) {
	gltSetWorkingDirectory(argv[0]);
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
	glutInitWindowSize(800, 600);
	glutCreateWindow("OpenGL SphereWorld");
	glutSpecialFunc(SpecialKeys);
	glutReshapeFunc(ChangeSize);
	glutDisplayFunc(RenderScene);
	GLenum err = glewInit();
	if (GLEW_OK != err) {
		fprintf(stderr, "GLEW Error: %s\n", glewGetErrorString(err));
		return 1;
	}
	SetupRC();
	glutMainLoop();
	return 0;
}
