#version 330 core
layout (location = 0) in vec3 position;  // 顶点位置
layout (location = 1) in vec3 normal;  // 法线向量
layout (location = 2) in vec2 texCoords;  // 纹理坐标

out vec3 Normal;
out vec3 FragPos;
out vec2 TexCoords;

uniform mat4 model;
uniform mat4 view;
uniform mat4 projection;

void main()
{
    gl_Position = projection * view *  model * vec4(position, 1.0f);
    FragPos = vec3(model * vec4(position, 1.0f));
    Normal = mat3(transpose(inverse(model))) * normal;
    TexCoords = texCoords;
}