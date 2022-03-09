in vec3 position;
in vec3 vertexColor;
out vec3 fragmentColor;

void main()
{
    gl_Position = vec4(position, 1);
    fragmentColor = vertexColor;
}