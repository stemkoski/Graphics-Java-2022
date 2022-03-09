// when using this material,
//  choose whether to use different color
//  at each vertex
in vec3 fragmentColor;

// or a single solid color for all vertices
uniform vec3 color;

// use vertex color data?
uniform bool useVertexColor;

// final color sent to GPU
out vec4 fragColor;

void main()
{
   if (useVertexColor)
      fragColor = vec4(fragmentColor, 1);
   else
      fragColor = vec4(color, 1);
}