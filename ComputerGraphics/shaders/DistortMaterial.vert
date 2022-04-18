in vec3 position;
in vec3 vertexColor;
out vec3 fragmentColor;

uniform mat4 modelMatrix;
uniform mat4 viewMatrix;
uniform mat4 projectionMatrix;

uniform vec2 repeatUV;
in vec2 vertexUV;
out vec2 UV;

void main()
{
    gl_Position = projectionMatrix * viewMatrix * modelMatrix * vec4(position, 1);
    // pass along data to the fragment shader; get interpolated along the way
    fragmentColor = vertexColor;
    UV = repeatUV * vertexUV;
}