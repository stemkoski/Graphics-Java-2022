// when using this material,
//  choose whether to use different color
//  at each vertex
in vec3 fragmentColor;

// or a single solid color for all vertices
uniform vec3 color;

// use vertex color data?
uniform bool useVertexColor;

// access texture pixel data, calculates (or "samples") the correct color based on filters.
uniform sampler2D tex;
// texture coordinates (incoming data from vertex shader)
in vec2 UV;

// secondary texture to generate random / "noise" values
uniform sampler2D noise;
// also need time data to generate animated effects
uniform float time;

// final color sent to GPU
out vec4 fragColor;

void main()
{
   // default is to use "color" variable (defaults to white)
   fragColor = vec4(color, 1);

   // if true, tint the base color with vertex colors.
   // when base color is white, this does not affect vertex colors.
   if (useVertexColor)
      fragColor *= vec4(fragmentColor, 1);

   // alter UV coordinates based on noise texture
   vec4 noiseData = texture( noise, UV ) - vec4(0.5,0.5,0.5,0.5);
   vec2 noiseUV = UV + sin(time) * vec2(noiseData.r, noiseData.g);

   // combine with color of sampled pixel at this location
   fragColor *= texture( tex, noiseUV ); 
	
   
}