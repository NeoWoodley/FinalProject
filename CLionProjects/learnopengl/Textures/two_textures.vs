#version 330 core
layout (location = 0) in vec3 position;
layout (location = 1) in vec3 color;
layout (location = 2) in vec2 texCoord;

out vec3 ourColor;
out vec2 TexCoord;

void main() {
    gl_Position = vec4(position, 1.0f);
    ourColor = color;
    // 编辑顶点着色器来自动翻转y轴坐标，替换TexCoord的值为：
    TexCoord = vec2(texCoord.x, 1.0 - texCoord.y);
}