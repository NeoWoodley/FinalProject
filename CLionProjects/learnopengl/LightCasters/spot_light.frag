#version 330 core
struct Material {
    sampler2D diffuse;
    sampler2D specular;
    float     shininess;
};

struct Light {
    vec3 position;  // 聚光所在的位置（手电筒所在的位置）
    vec3 direction;  // 聚光所指向的方向，有点类似于法线，由光源作为指向出发点
    float cutOff;  // 切光角的余弦值

    float constant;
    float linear;
    float quadratic;

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
    // 光所指向的方向：由片段位置指向光源的位置
    vec3 lightDir = normalize(light.position - FragPos);

    // Check if lighting is inside the spotlight cone
    // 计算lightDir和取反的direction向量的点乘（它是取反过的因为我们想要向量指向光源，而不是从光源作为指向出发点。
    // 计算lightDir和取反的direction向量的点乘（它是取反过的因为我们想要向量指向光源，而不是从光源作为指向出发点。
    float theta = dot(lightDir, normalize(-light.direction));

    // 计算LightDir向量和SpotDir向量之间的角度theta，与切光角phi进行对比，
    // 以决定是否在聚光的内部
    if(theta > light.cutOff) // Remember that we're working with angles as cosines instead of degrees so a '>' is used.
    {
        // Ambient
        vec3 ambient = light.ambient * vec3(texture(material.diffuse, TexCoords));

        // Diffuse
        vec3 norm = normalize(Normal);
        float diff = max(dot(norm, lightDir), 0.0);
        vec3 diffuse = light.diffuse * diff * vec3(texture(material.diffuse, TexCoords));

        // Specular
        vec3 viewDir = normalize(viewPos - FragPos);
        vec3 reflectDir = reflect(-lightDir, norm);
        float spec = pow(max(dot(viewDir, reflectDir), 0.0), material.shininess);
        vec3 specular = light.specular * spec * vec3(texture(material.specular, TexCoords));

        // Attenuation
        float distance    = length(light.position - FragPos);
        float attenuation = 1.0f / (light.constant + light.linear * distance + light.quadratic * (distance * distance));

        // ambient  *= attenuation;  // Also remove attenuation from ambient, because if we move too far, the light in spotlight would then be darker than outside (since outside spotlight we have ambient lighting).
        diffuse  *= attenuation;
        specular *= attenuation;

        color = vec4(ambient + diffuse + specular, 1.0f);
    }
    else    // else, use ambient light so scene isn't completely dark outside the spotlight.否则使用环境光，使得场景不至于完全黑暗
        color = vec4(light.ambient * vec3(texture(material.diffuse, TexCoords)), 1.0f);
}