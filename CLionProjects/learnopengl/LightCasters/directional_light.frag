#version 330 core
struct Material {
    sampler2D diffuse;
    sampler2D specular;
    float     shininess;
};

// 定义一个光的方向向量来模拟一个定向光（光源的每条光线接近于平行），而不是使用光的位置向量，
// 直接使用光的方向向量来代替lightDir向量和position向量的计算
struct Light {
    // 现在不需要光源位置了，因为它是无限远的定向光
    //vec3 position;
    vec3 direction;

    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
};

in vec3 FragPos;
in vec3 Normal;
in vec2 TexCoords;

out vec4 color;

uniform vec3 viewPos;
uniform Material material;
uniform Light light;

void main()
{
    // Ambient
    vec3 ambient = light.ambient * vec3(texture(material.diffuse, TexCoords));

    // Diffuse
    vec3 norm = normalize(Normal);
    // vec3 lightDir = normalize(light.position - FragPos);
    // 目前使用的光照计算需要光的方向作为一个来自片段朝向光源的方向，所以需要取反操作
    // 现在lightDir是一个方向向量指向光源，并确保对向量进行标准化处理
    vec3 lightDir = normalize(-light.direction);
    float diff = max(dot(norm, lightDir), 0.0);
    vec3 diffuse = light.diffuse * diff * vec3(texture(material.diffuse, TexCoords));

    // Specular
    vec3 viewDir = normalize(viewPos - FragPos);
    vec3 reflectDir = reflect(-lightDir, norm);
    float spec = pow(max(dot(viewDir, reflectDir), 0.0), material.shininess);
    vec3 specular = light.specular * spec * vec3(texture(material.specular, TexCoords));

    color = vec4(ambient + diffuse + specular, 1.0f);
}