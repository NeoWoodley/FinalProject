#version 330 core
struct Material {
    sampler2D diffuse;
    sampler2D specular;
    float     shininess;
};

struct Light {
    vec3 position;  // 聚光所在的位置（手电筒所在的位置）
    vec3 direction;  // 聚光所指向的方向，有点类似于法线，由光源作为指向出发点
    float cutOff;  // 切光角的余弦值（内圆锥）
    float outerCutOff;  // 外圆锥的余弦值

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
    // Ambient
    vec3 ambient = light.ambient * vec3(texture(material.diffuse, TexCoords));

    // Diffuse
    vec3 norm = normalize(Normal);
    // 光所指向的方向：由片段位置指向光源的位置
    vec3 lightDir = normalize(light.position - FragPos);
    float diff = max(dot(norm, lightDir), 0.0);
    vec3 diffuse = light.diffuse * diff * vec3(texture(material.diffuse, TexCoords));

    // Specular
    vec3 viewDir = normalize(viewPos - FragPos);
    vec3 reflectDir = reflect(-lightDir, norm);
    float spec = pow(max(dot(viewDir, reflectDir), 0.0), material.shininess);
    vec3 specular = light.specular * spec * vec3(texture(material.specular, TexCoords));

    // spotlight(soft edges)
    // 我们基本是根据θ在外余弦和内余弦之间插值。
    // 由于我们现在有了一个亮度值，当在聚光外的时候是个负的，当在内部圆锥以内大于1。
    // 如果我们适当地把这个值固定，我们在片段着色器中就再不需要if-else了，我们可以简单地用计算出的亮度值乘以光的元素
    // 计算lightDir和取反的direction向量的点乘（它是取反过的因为我们想要向量指向光源，而不是从光源作为指向出发点。
    float theta = dot(lightDir, normalize(-light.direction));
    float epsilon = light.cutOff - light.outerCutOff;  // 内部圆锥cutOff和外部圆锥outerCutOff的余弦值的差
    // clamp函数会把第一个参数固定在0.0和1.0之间，保证亮度值不会超出[0,1]以外
    float intensity = clamp((theta - light.outerCutOff) / epsilon,0.0, 1.0);
    // We’ll leave ambient unaffected so we always have a little
    diffuse *= intensity;
    specular *= intensity;

    // Attenuation
    float distance    = length(light.position - FragPos);
    float attenuation = 1.0f / (light.constant + light.linear * distance + light.quadratic * (distance * distance));

     ambient  *= attenuation;  // Also remove attenuation from ambient, because if we move too far, the light in spotlight would then be darker than outside (since outside spotlight we have ambient lighting).
    diffuse  *= attenuation;
    specular *= attenuation;

    color = vec4(ambient + diffuse + specular, 1.0f);
}